package com.cs.client.view;

import com.cs.client.service.FileClientService;
import com.cs.client.service.MessageClientService;
import com.cs.client.service.UserClientService;
import com.cs.client.utils.Utility;

public class QQView {

    private boolean loop=true;
    private String key = "";
    private UserClientService userClientService=new UserClientService();
    private MessageClientService messageClientService=new MessageClientService();
    private FileClientService fileClientService=new FileClientService();


    public static void main(String[] args) {
        new QQView().mainMenu();
        System.out.println("客户端退出");
    }

    private void mainMenu(){

        while(loop){
            System.out.println("==========欢迎登录网络通讯系统==========");
            System.out.println("\t\t 1 登陆系统");
            System.out.println("\t\t 9 退出系统");
            System.out.print("请输入你的选择:");

            key = Utility.readString(1);
            //  根据用户输入处理
            switch(key){
                case "1":
                    System.out.println("请输入用户号:");
                    String userId = Utility.readString(50);
                    System.out.println("请输入密 码:");
                    String pwd = Utility.readString(50);
                    //到服务端验证是否合法
                    if(userClientService.checkUser(userId,pwd))
                    {
                        System.out.println("==========欢迎 " + userId+ "=========");
                        //进入到二级菜单
                        while(loop){
                            System.out.println("==========网路通讯系统二级菜单(用户"+userId+")==========");
                            System.out.println("\t\t 1 显示在线用户列表");
                            System.out.println("\t\t 2 群发消息");
                            System.out.println("\t\t 3 私聊消息");
                            System.out.println("\t\t 4 发送文件");
                            System.out.println("\t\t 9 退出系统");
                            System.out.print("请输入你的选择:");
                            key = Utility.readString(1);
                            switch(key){
                                case "1":
                                    userClientService.onlineFriendList();
                                    break;
                                case "2":
                                    System.out.println("请输入想对大家说的话:");
                                    String s=Utility.readString(100);
                                    messageClientService.sendMessageToAll(s,userId);
                                    break;
                                case "3":
                                    System.out.print("请输入想聊天的用户名(在线):");
                                    String getterId = Utility.readString(50);
                                    System.out.print("请输入想说的话:");
                                    String content = Utility.readString(100);
                                    messageClientService.sendMessageToOne(content,userId,getterId);
                                    break;
                                case "4":
                                    System.out.print("请输入你想发送文件的用户(在线):");
                                    getterId = Utility.readString(50);
                                    System.out.print("请输入发送文件的路径(形式 d\\xx.jpg):");
                                    String src = Utility.readString(100);
                                    System.out.print("请输入把文件发送到对应的路径(形式 d\\xx.jpg):");
                                    String dest = Utility.readString(100);
                                    fileClientService.sendFileToOne(src,dest,userId,getterId);
                                    break;
                                case "9":
                                    userClientService.logout();
                                    loop=false;
                                    break;
                            }
                        }
                    }else{
                        System.out.println("==========登陆失败==========");

                    }
                    break;
                case "9":
                    loop=false;
                    break;

            }
        }
    }
}
