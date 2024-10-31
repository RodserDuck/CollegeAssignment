package com.cs.client.service;

import com.cs.common.Message;
import com.cs.common.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * 该类/对象提供和消息相关的服务方法
 */
public class MessageClientService {


    public void sendMessageToAll(String content,String senderId) {
        //构建message
        Message message = new Message();
        message.setSender(senderId);
        message.setContent(content);
        message.setMesType(MessageType.MESSAGE_TO_ALL_MES);
        message.setSendTime(new Date().toString());
        System.out.println( "你对所有人说" + content);
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(ManageClientConnectServerTread.getClientConnectServerTread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     *
     * @param content 内容
     * @param senderId 发送者
     * @param getterId 接收者
     */
    public void sendMessageToOne(String content,String senderId,String getterId) {
        //构建message
        Message message = new Message();
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setContent(content);
        message.setMesType(MessageType.MESSAGE_COMM_MES);
        message.setSendTime(new Date().toString());
        System.out.println( "你对" + getterId + "说" + content);
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(ManageClientConnectServerTread.getClientConnectServerTread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
