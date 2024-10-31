package com.cs.client.service;

import com.cs.common.Message;
import com.cs.common.MessageType;

import java.io.*;
import java.net.Socket;

public class ClientConnectServerTread extends Thread {
    private Socket socket;

    public ClientConnectServerTread(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        //因为Thread需要再后台和服务器通信，因此我们while循环
        while(true)
        {
            try {
                System.out.println("客户端线程，等待从读取从服务端发送的消息");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                //判断这个message类型，然后做相应的业务处理
                //如果读取到的是服务端返回的在线用户列表
                if(message.getMesType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND))
                {
                    String[] onlineUsers = message.getContent().split(" ");
                    System.out.println("\n=========当前在线用户列表如下=========");
                    for(int i = 0; i < onlineUsers.length; i++)
                    {
                        System.out.println("用户："+onlineUsers[i]);
                    }
                } else if (message.getMesType().equals(MessageType.MESSAGE_COMM_MES)) {
                    //把从服务器转发的消息，显示到控制台
                    System.out.println("\n"+message.getSender()+" 对你说:" + message.getContent());
                }else if(message.getMesType().equals(MessageType.MESSAGE_TO_ALL_MES))
                {
                    System.out.println("\n"+message.getSender()+" 对大家说:" + message.getContent());
                } else if (message.getMesType().equals(MessageType.MESSAGE_FILE_MES)) {
                    System.out.println("\n" + message.getSender() + "给你发送了文件:" +
                            message.getSrc()+ "到" +message.getDest());
                    //取出message的文件字节数组，通过文件输出流写出到磁盘
                    FileOutputStream fileInputStream = new FileOutputStream(message.getDest());
                    fileInputStream.write(message.getFileBytes());
                    fileInputStream.close();
                    System.out.println("\n保存文件成功");
                } else if (message.getMesType().equals(MessageType.MESSAGE_Ann_MES)) {
                    System.out.println("\n服务器公告:"+message.getContent());
                }
                else {
                    System.out.println("是其他类型的message,暂时不处理");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

    }
    public Socket getSocket() {
        return socket;
    }
}
