package com.cs.client.service;

import com.cs.common.Message;
import com.cs.common.MessageType;

import java.io.*;

/**
 *
 * 该类完成文件传输服务
 */
public class FileClientService {

    /**
     *
     * @param src 源文件
     * @param dest 把该文件传输到对方的那个目录
     * @param senderId 发送用户id
     * @param getterId 接收用户id
     */
    public void sendFileToOne(String src,String dest,String senderId,String getterId){
        //读取src文件
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_FILE_MES);
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setSrc(src);
        message.setDest(dest);

        //需要将文件读取
        FileInputStream fileInputStream = null;
        byte[] fileBytes = new byte[(int)new File(src).length()];
        try {
            fileInputStream = new FileInputStream(src);
            fileInputStream.read(fileBytes);//将src文件读入到程序的字节数组
            //将文件对应的字节数组设置message
            message.setFileBytes(fileBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("\n你给"+ senderId +"发送文件:"+ src + "到对方的" +
                "电脑的目录"+dest);

        //发送

        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(ManageClientConnectServerTread.getClientConnectServerTread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
