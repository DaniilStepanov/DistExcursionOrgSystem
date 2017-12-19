package com.kspt.eos.logic;

import com.kspt.eos.entity.User;

public class LUser extends User {

    public void subMoney(int amount){
        int money = getMoney();
        setMoney(money - amount);
    }
    public void addMoney(int amount){
        int money = getMoney();
        setMoney(money + amount);
    }
}
