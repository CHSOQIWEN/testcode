package com.bittch.chatroom.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端给服务端发送的数据
 * 发送的数据来自命令行的交互式输入
 * Auther:CHAOQIWEN
 */
public class WriteDataToServerThread  extends Thread {
    private  final Socket client;
    public WriteDataToServerThread(Socket client){
        this.client = client;

    }


    @Override
    public void run() {
        try {
            OutputStream clientOutput = this.client.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(clientOutput);
            Scanner scanner = new Scanner(System.in);
            while(true){
                System.out.println("请输入>>");
                String data = scanner.nextLine();
                writer.write(data+"\n");
                writer.flush();
                if(data.equals("bye")){
                    break;
                }


            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
