package com.cs.qqserver.service;

import com.cs.common.Message;
import com.cs.common.MessageType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

/**
 *该类对应的对象和某个客户端保持通信
 */
public class ServerConnectClientThread extends Thread {
    private Socket socket;
    private String userId;//连接到服务端的用户Id

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    public Socket getSocket() {
        return socket;
    }

    public void run() {

        while (true) {
            System.out.println("服务端和客户端" + userId + "保持通信，读取数据...");
            try {
                ObjectInputStream ois =
                        new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                if(message.getMesType().equals(MessageType.MESSAGE_GET_ONLINE_FRIEND))
                {
                    //客户端要在线用户列表
                    System.out.println(message.getSender() + "要在线用户列表");
                    String onlineUser = ManageClientThreads.getOnlineUser();
                //返回message
                //构建一个Message对象，返回给客户端
                    Message message2 = new Message();
                    message2.setMesType(MessageType.MESSAGE_RET_ONLINE_FRIEND);
                    message2.setContent(onlineUser);
                    message2.setGetter(message.getSender());
                    //返回给客户端
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message2);

                }else if(message.getMesType().equals(MessageType.MESSAGE_CLIENT_EXIT)){

                    System.out.println(message.getSender()+"退出系统");
                    //将这个客户端对应线程，从集合删除
                    ManageClientThreads.removeServerConnectClientThread(message.getSender());
                    socket.close();//关闭连接
                    //退出线程
                    break;
                }else if(message.getMesType().equals(MessageType.MESSAGE_COMM_MES)){
                    //根据message获取getter id，然后再得到对应线程
                    ServerConnectClientThread serverConnectClientThread = ManageClientThreads.getClientThread(message.getGetter());
                    ObjectOutputStream oos = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                    oos.writeObject(message);//转发，如果客户不在线，可以保存到数据库，实现离线留言
                }else if(message.getMesType().equals(MessageType.MESSAGE_TO_ALL_MES)){
                    HashMap<String, ServerConnectClientThread> hm = ManageClientThreads.getHm();
                    Iterator<String> iterator = hm.keySet().iterator();
                    while(iterator.hasNext()){
                        //取出在线用户的ID
                        String onlineUserId = iterator.next().toString();
                        if(!onlineUserId.equals(message.getSender())){//排除发送用户
                            //进行转发message
                            ObjectOutputStream oos =
                                    new ObjectOutputStream(hm.get(onlineUserId).getSocket().getOutputStream());
                            oos.writeObject(message);
                        }
                    }
                } else if (message.getMesType().equals(MessageType.MESSAGE_FILE_MES)) {
                    //根据getterId获取到对应的线程，将message对象转发
                    ServerConnectClientThread serverConnectClientThread = ManageClientThreads.getClientThread(message.getGetter());
                    ObjectOutputStream oos = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                    oos.writeObject(message);

                } else{
                    System.out.println("其他类型暂不处理");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
