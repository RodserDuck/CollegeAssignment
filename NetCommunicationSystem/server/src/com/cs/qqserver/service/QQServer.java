package com.cs.qqserver.service;

import com.cs.common.Message;
import com.cs.common.MessageType;
import com.cs.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class QQServer {

    private ServerSocket ss = null;
    //创建一个集合，存放多个用户，如果这些用户登录，认为其合法
    //ConcurrentHashMap处理的线程安全，即线程同步处理，在多线程情况下是安全的
    private static ConcurrentHashMap<String, User> vaildUser = new ConcurrentHashMap<>();
    private AnnouncementThreads announcementThreads=new AnnouncementThreads();

    static {

        vaildUser.put("100", new User("100", "123456"));
        vaildUser.put("200", new User("200", "123456"));
        vaildUser.put("300", new User("300", "123456"));
        vaildUser.put("澪", new User("澪", "123456"));
        vaildUser.put("唯", new User("唯", "123456"));
        vaildUser.put("梓", new User("梓", "123456"));

    }

    //验证用户是否有效的方法
    private boolean checkUser(String userId, String passwd) {
        User user = vaildUser.get(userId);
        if (user == null) {
            return false;
        }
        if(!user.getPasswd().equals(passwd)) {
            return false;
        }
        return true;
    }

    public QQServer() {
        try {
            System.out.println("服务器在9999端口监听...");
            announcementThreads.start();
            ss = new ServerSocket(9999);
            while (true) {
            Socket socket = ss.accept();
                ObjectInputStream ois =
                        new ObjectInputStream(socket.getInputStream());
            //得到socket关联的的对象输出流
            ObjectOutputStream oos =
                    new ObjectOutputStream(socket.getOutputStream());
            User u = (User) ois.readObject();
            //创建一个Message对象，准备回复客户端
            Message message = new Message();

            //公告推送
            //无数据库
                //验证用户
            if(checkUser(u.getUserId(), u.getPasswd())) {
                message.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);
                //将message对象回复
                oos.writeObject(message);
                //创建一个线程和客户端保持通信，该线程需要持有socket对象
                ServerConnectClientThread serverConnectClientThread =
                        new ServerConnectClientThread(socket, u.getUserId());
                //启动该线程
                serverConnectClientThread.start();
                //把该线程对象放入到一个集合中
                ManageClientThreads.addClientThread(u.getUserId(),serverConnectClientThread);
            }else {//登陆失败
                System.out.println("用户" + u.getUserId() + "密码" + u.getPasswd()+"验证失败");
                message.setMesType(MessageType.MESSAGE_LOGIN_FAILED);
                oos.writeObject(message);
                socket.close();
            }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            //如果服务端退出了while循环，说明服务器端不再监听
            try {
                ss.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
