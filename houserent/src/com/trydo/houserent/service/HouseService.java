package com.trydo.houserent.service;

import com.trydo.houserent.domain.House;
import com.trydo.houserent.utils.Utility;

import java.awt.*;

public class HouseService {
    private House[] houses;
    private int houseNums = 0;
    private int idCounter = 0;

    public HouseService(int size) {
        houses = new House[size];//指定数组大小
    }

    public House findById(int id) {
        if (houseNums == 0) {
            System.out.println("当前无房屋可查找");
            return null;
        }
        for (int i = 0; i < houses.length; i++) {
            if (houses[i].getId() == id) {
                return houses[i];
            }
        }
        return null;
    }

    public House[] list() {
        return houses;
    }

    public boolean del(int delId) {
        int index = -1;
        for (int i = 0; i < houseNums; i++) {
            if (houses[i].getId() == delId) {
                index = i;//使用index纪录
            }
        }
        if (index == -1) {
            return false;
        } else {
            for (int i = index; i < houseNums - 1; i++) {
                houses[i] = houses[i + 1];
            }
            houses[--houseNums] = null;
            return true;
        }
    }

    public boolean add(House newHouse) {
        if (houseNums == houses.length) {
            System.out.println("数组已满，不能再添加了。");
            return false;
        }
        houses[houseNums++] = newHouse;
        newHouse.setId(++idCounter);
        return true;
    }
}
