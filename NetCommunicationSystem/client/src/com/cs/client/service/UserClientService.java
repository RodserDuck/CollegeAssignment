package com.cs.client.service;

import com.cs.common.Message;
import com.cs.common.MessageType;
import com.cs.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 该类完成用户登录验证和用户注册功能
 */
public class UserClientService {

    private User u = new User();//其他地方可能使用user信息
    private Socket socket;

    //根据userId和pwd到服务器验证该用户是否合法
    public boolean checkUser(String userId, String pwd) {
        boolean b = false;
        u.setUserId(userId);
        u.setPasswd(pwd);
        //connect server
        try {
            socket=new Socket(InetAddress.getByName("127.0.0.1"),9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u);

            //读取服务端回复的Message对象
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message ms = (Message) ois.readObject();

            if(ms.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED))
            {
                //创建一个和服务器端保持通信的线程
                ClientConnectServerTread clientConnectServerTread = new ClientConnectServerTread(socket);
                clientConnectServerTread.start();
                //为了后面的扩展，放入到集合里面
                ManageClientConnectServerTread.addClientConnectServerTread(userId, clientConnectServerTread);
                b=true;
            }else {
                socket.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return b;
    }

    //向服务端请求在线用户列表
    public void onlineFriendList(){
        //发送一个Message,类型MESSAGE_GET_ONLINE_FRIEND
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
        message.setSender(u.getUserId());
        //发送给服务器，应该得到当前线程的Socket对应的OutPutStream对象
        try {
        //从管理线程的集合中，通过userId,得到这个线程对象
            ClientConnectServerTread clientConnectServerTread = ManageClientConnectServerTread.getClientConnectServerTread(u.getUserId());
        //通过这个线程得到关联的socket
            Socket socket = clientConnectServerTread.getSocket();
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //退出客户端，并给服务器发送一个退出系统的message对象
    public void logout(){
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_CLIENT_EXIT);
        message.setSender(u.getUserId());//指定退出user该线程

        try {
            //ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            ObjectOutputStream oos=new ObjectOutputStream(ManageClientConnectServerTread.getClientConnectServerTread(u.getUserId()).getSocket().getOutputStream());
            oos.writeObject(message);
            System.out.println(u.getUserId()+"退出系统");
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
