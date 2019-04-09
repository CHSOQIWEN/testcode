package com.bittch.chatroom.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * 单线程聊天室：客户端
 * Auther:CHAOQIWEN
 */
public class SingleThreadClient {
    public static void main(String[] args) {
        try {
            //创建Socket客户端，连接指定服务
            Socket socket = new Socket("127.0.0.1",6666);
            System.out.println("客户端创建..."+socket.getLocalSocketAddress());
            //发送数据
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            writer.write("你好服务器\n");
            writer.flush();

            //接收数据
            InputStream inputStream = socket.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            String message = scanner.nextLine();
            System.out.println("接收到服务器消息"+message);
            //关闭
            socket.close();
            System.out.println("客户端关闭" );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
