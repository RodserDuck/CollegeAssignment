package com.cs.client.service;

import java.util.HashMap;
import java.util.Iterator;

/**
 *该类管理客户端连接到服务器端的线程的类
 */
public class ManageClientConnectServerTread {
    private static HashMap<String, ClientConnectServerTread> hm = new HashMap();
    public static void addClientConnectServerTread(String UserId, ClientConnectServerTread ClientConnectServerTread)
    {
        hm.put(UserId, ClientConnectServerTread);
    }
    public static ClientConnectServerTread getClientConnectServerTread(String UserId){
        return hm.get(UserId);
    }

}
