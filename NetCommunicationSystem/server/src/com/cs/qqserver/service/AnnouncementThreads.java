package com.cs.qqserver.service;

import com.cs.common.Message;
import com.cs.common.MessageType;
import com.cs.utils.Utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;

public class AnnouncementThreads extends Thread {

    private String announcement;
    private Message message = new Message();
    private int onlineUsers = 0;

    @Override
    public void run() {
        while(true){
            System.out.print("请输入你要推送的消息:");
            onlineUsers = 0;
            announcement = Utility.readString(100);
            message.setContent(announcement);
            message.setMesType(MessageType.MESSAGE_Ann_MES);
            HashMap<String, ServerConnectClientThread> hm = ManageClientThreads.getHm();
            Iterator<String> iterator =hm.keySet().iterator();
            while (iterator.hasNext()) {
                String onlineUserId = iterator.next().toString();
                ObjectOutputStream oos = null;
                onlineUsers++;
                try {
                    oos = new ObjectOutputStream(hm.get(onlineUserId).getSocket().getOutputStream());
                    oos.writeObject(message);
                    oos.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(message.getContent()+"已发送至全部在线用户:共"+onlineUsers+"名");

        }
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }
}
