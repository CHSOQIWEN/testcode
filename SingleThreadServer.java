package com.bittch.chatroom.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Auther:CHAOQIWEN
 */
public class SingleThreadServer {
    public static void main(String[] args) {
        try {
            //创建服务器端的ServerSocket，监听6666端口
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("服务器启动..." + serverSocket.getLocalSocketAddress());
            //接收客户端连接
            Socket socket = serverSocket.accept();
            System.out.println("接收到客户端连接" + socket.getRemoteSocketAddress());
            //接收和发送数据
            //接收数据
            InputStream inputStream = socket.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            String message = scanner.nextLine();
            System.out.println("收到客户端消息" + message);
            //发送数据
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            writer.write("你好客户端。\n");
            writer.flush();
            //关闭
            serverSocket.close();
            System.out.println("关闭");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}