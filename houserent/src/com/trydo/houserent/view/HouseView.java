package com.trydo.houserent.view;

import com.trydo.houserent.domain.House;
import com.trydo.houserent.service.HouseService;
import com.trydo.houserent.utils.Utility;

public class HouseView {
    private boolean loop = true;
    private char key = ' ';
    private HouseService houseService = new HouseService(10);

    public void exit() {
        char c = Utility.readConfirmSelection();
        if (c == 'Y') {
            loop = false;
        }
    }

    public void addHouse() {
        System.out.println("========添加房屋========");
        System.out.println("姓名：");
        String name = Utility.readString(8);
        System.out.println("电话：");
        String phone = Utility.readString(12);
        System.out.println("地址：");
        String address = Utility.readString(16);
        System.out.println("月租：");
        int rent = Utility.readInt();
        System.out.println("状态：");
        String state = Utility.readString(3);
        House newHouse = new House(0, name, phone, address, rent, state);
        if (houseService.add(newHouse)) {
            System.out.println("======添加房屋成功======");
        } else {
            System.out.println("添加房屋失败");
        }
    }

    public void delHouse() {
        System.out.println("=======删除房屋信息========");
        System.out.println("请输入待删除的房屋编号（-1退出）：");
        int delId = Utility.readInt();
        if (delId == -1) {
            System.out.println("放弃删除房屋信息");
            return;
        }
        //必须输入Y/N
        char Choice = Utility.readConfirmSelection();

        if (Choice == 'Y') {
            if (houseService.del(delId)) {
                System.out.println("======删除房屋信息成功=====");
            } else {
                System.out.println("=======房屋编号不存在======");
            }
        } else {
            System.out.println("放弃删除");
        }
    }

    public void listHoses() {
        System.out.println("============房屋列表============");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态（未出租/已出租）");
        House[] houses = houseService.list();
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] == null)
                break;
            System.out.println(houses[i]);
        }
    }

    public void findHouse() {
        System.out.println("=======查找房屋信息=======");
        System.out.println("请输入要查找的id:");
        int findId = Utility.readInt();
        House house = houseService.findById(findId);
        if (house != null) {
            System.out.println(house);
        } else {
            System.out.println("======查找房屋信息不存在======");
        }

    }

    public void updata() {
        System.out.println("=========修改房屋信息=========");
        System.out.println("请选择待修改房屋编号（-1表示退出）");
        int updateId = Utility.readInt();
        if (updateId == -1) {
            System.out.println("放弃修改房屋信息");
            return;
        }
        House house = houseService.findById(updateId);
        if (house == null) {
            System.out.println("修改的房屋信息不存在");
            return;
        }
        System.out.println("姓名(" + house.getName() + "):");
        String name = Utility.readString(8, "");
        if (!"".equals(name)) {
            house.setName(name);
        }
        System.out.println("电话(" + house.getPhone() + "):");
        String phone = Utility.readString(12);
        if (!"".equals(phone)) {
            house.setPhone(phone);
        }
        System.out.println("地址(" + house.getAddress() + "):");
        String address = Utility.readString(16);
        if (!"".equals(address)) {
            house.setAddress(address);
        }
        System.out.println("租金(" + house.getRent() + "):");
        int rent = Utility.readInt();
        if (rent != -1) {
            house.setRent(rent);
        }
        System.out.println("电话(" + house.getState() + "):");
        String state = Utility.readString(3);
        if (!"".equals(state)) {
            house.setState(state);
        }
    }

    public void mainMenu() {
        do {
            System.out.println("============房屋出租系统菜单============");
            System.out.println("\t\t\t1 新 增 房 源");
            System.out.println("\t\t\t2 查 找 房 屋");
            System.out.println("\t\t\t3 删 除 房 屋 信 息");
            System.out.println("\t\t\t4 修 改 房 屋 信 息");
            System.out.println("\t\t\t5 房 屋 列 表");
            System.out.println("\t\t\t6 退      出");
            System.out.println("请输入你的选择（1-6）");
            key = Utility.readChar();
            switch (key) {
                case '1':
                    addHouse();
                    break;
                case '2':
                    findHouse();
                    break;
                case '3':
                    delHouse();
                    break;
                case '4':
                    updata();
                    break;
                case '5':
                    listHoses();
                    break;
                case '6':
                    exit();
                    break;
            }
        } while (loop);
    }
}
