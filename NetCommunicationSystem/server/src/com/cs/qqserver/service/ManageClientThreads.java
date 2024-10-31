package com.cs.qqserver.service;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 该类用于管理和客户端通信的线程
 */
public class ManageClientThreads {
    private static HashMap<String,ServerConnectClientThread> hm = new HashMap<>();
    //添加线程对象到hm集合
    public static void addClientThread(String userId,ServerConnectClientThread serverConnectClientThread){
        hm.put(userId,serverConnectClientThread);
    }

    public static ServerConnectClientThread getClientThread(String userId){
        return hm.get(userId);
    }

    //从集合中移除某个线程对象
    public static void removeServerConnectClientThread(String userId){
        hm.remove(userId);
    }

    public static HashMap<String, ServerConnectClientThread> getHm() {
        return hm;
    }

    //返回用户列表
    public static String getOnlineUser() {
        //集合遍历，遍历hashmap的Key
        Iterator<String> iterator = hm.keySet().iterator();
        String onlineUser = "";
        while (iterator.hasNext()) {
            onlineUser += iterator.next().toString() + " ";
        }
        return onlineUser;
    }
}
