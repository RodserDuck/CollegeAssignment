package com.trydo.houserent;

import com.trydo.houserent.view.HouseView;

public class HouseRentApp {
    public static void main(String[] args) {
        //创建houseview对象，并且显示主菜单
        new HouseView().mainMenu();
        System.out.println("====你退出房屋出租系统====");
    }
}
