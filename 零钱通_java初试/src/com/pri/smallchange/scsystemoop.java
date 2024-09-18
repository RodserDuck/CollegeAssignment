package com.pri.smallchange;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * 第一次使用OOP编写项目
 * 此类是完成各个功能的类
 */
public class scsystemoop {
    boolean loop = true;
    Scanner scanner = new Scanner(System.in);
    String key = "";
    String details="--------------------零钱通明细--------------------";
    double money=0;
    double balance=0;
    Date date =null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String note="";
    public void mainMenu()
    {
        do {
            System.out.println("\n=========零钱通菜单oop========");
            System.out.println("\t\t\t1.零钱通明细");
            System.out.println("\t\t\t2.收益入账");
            System.out.println("\t\t\t3.消费");
            System.out.println("\t\t\t4退      出");
            System.out.println("请选择（1-4）：");
            key = scanner.next();
            switch (key) {
                case "1":
                    this.detail();
                    break;
                case "2":
                    this.income();
                    break;
                case "3":
                    this.pay();
                    break;
                case "4":
                    this.exit();
                    break;
                default:
                    System.out.println("菜单选择有误，请重新选择");
            }
        } while (loop);
    }
    public void detail() {
        System.out.println(details);
    }

    public void income()
    {
        System.out.print("收益入账收益入账金额：");
        money = scanner.nextDouble();
        if(money<=0){
            System.out.println("收益入账金额需要大于0");
            return;
        }
        balance += money;
        date = new Date();
        details +="\n收益入账\t+" + money + "\t"+ sdf.format(date) + "\t" + balance;
        return;
    }
    public void pay(){
        System.out.println("消费金额：");
        money = scanner.nextDouble();
        if(money<=0||money>balance){
            System.out.println("您的消费金额应该在0-"+balance);
            return;
        }
        System.out.println("消费说明：");
        note = scanner.next();
        balance -= money;
        details +="\n" + note + "\t-" + money + "\t"+ sdf.format(date) +"\t"+ balance;
        return;
    }
    public void exit(){
        String choice = "";
        while(true)
        {
            choice = scanner.next();
            System.out.println("你确定要退出吗？ y/n");
            if("y".equals(choice)||"n".equals(choice))
                break;
        }
        if(choice.equals("y"))
            loop = false;
    }
}

