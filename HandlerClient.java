package com.bittch.chatroom.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Auther:CHAOQIWEN
 */
public class HandlerClient implements Runnable {

    //维护所有的连接到服务端的客户端对象
    private static final Map<String,Socket> ONLINE_CLIENT_MAP = new ConcurrentHashMap<>();

    private final Socket client;

    public HandlerClient(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {

        try {
            InputStream clientInput = client.getInputStream();
            Scanner scanner = new Scanner(clientInput);
            //消息按行读取
            //1 、注册：register : <userName>  register:张三
            //2 、群聊：groupChat: <message>   groupChat:大家好
            //3 、私聊：privateChat: <userName>:<message>    privateChat:你好
            //4、 退出：bye

            while (true) {
                String data = scanner.nextLine();

                if (data.startsWith("register：")) {
                    String userName = data.split(":")[1];
                    register(userName);
                    continue;

                }
                if (data.startsWith("groupChat：")) {
                    String message = data.split(":")[1];
                    groupChat(message);
                    continue;
                }
                if (data.startsWith("privateChat:")) {
                    String[] segments = data.split(":");
                    String targetUserName = segments[1];
                    String message = segments[2];
                    privateChat(targetUserName, message);
                    continue;
                }

                if (data.equals("bye")) {
                    bye();
                    continue;

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //退出

    private void bye() {
        for(Map.Entry<String,Socket> entry:ONLINE_CLIENT_MAP.entrySet()){
            Socket target = entry.getValue();
            if(target.equals(this.client)){
                ONLINE_CLIENT_MAP.remove(entry.getKey());
                break;
            }
        }
        printOnlineClient();;
    }


    private String getCurrentUserName(){
        for(Map.Entry<String,Socket> entry:ONLINE_CLIENT_MAP.entrySet()){
            Socket target = entry.getValue();
            if(target.equals(this.client)){
                return entry.getKey();
            }
        }
        return "";
    }
    //私聊，给targetUserName发送message消息

    private void privateChat(String targetUserName,String message) {
        Socket target = ONLINE_CLIENT_MAP.get(targetUserName);
        if(target == null){
            this.sendMessage(this.client,"没这个人"+targetUserName,false);
        }else {
            String currentUserName = this.getCurrentUserName();
            this.sendMessage(target,"<"+currentUserName+":说>"+message,true);
        }
    }
    //群聊发送message
    private void groupChat(String message) {
        for(Map.Entry<String,Socket> entry:ONLINE_CLIENT_MAP.entrySet()){
            Socket target = entry.getValue();
            if(target.equals(this.client)){
                continue;
            }
            this.sendMessage(target,message,true);
        }

    }

    //以userName为key注册当前用户
    private void register(String userName)  {
        ONLINE_CLIENT_MAP.put(userName,client);
        printOnlineClient();
        this.sendMessage(this.client,userName+"注册成功",false);


    }

    private  void sendMessage(Socket target,String message ,boolean prefix){

        OutputStream clientOutput = null;
        try {
            clientOutput =target.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(clientOutput);
            if(prefix){
                String currentUserName = this.getCurrentUserName();
                writer.write("<"+currentUserName+":说>"+message+"\n");
            }else {
                writer.write(message+"\n");
            }

            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();

        }


    }
    //打印在线客户端
    private  void printOnlineClient(){
        System.out.println("在线人数"+ONLINE_CLIENT_MAP.size()+"用户名如下");
        for(String userName : ONLINE_CLIENT_MAP.keySet()){
            System.out.println(userName);
        }
    }
}
