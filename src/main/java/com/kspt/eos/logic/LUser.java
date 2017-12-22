package com.kspt.eos.logic;

import com.kspt.eos.entity.User;

public class LUser {

    public LUser(User user) {
        this.user = user;
    }

    public void subMoney(int amount){
        int money = user.getMoney();
        user.setMoney(money - amount);
    }
    public void addMoney(int amount){
        int money = user.getMoney();
        user.setMoney(money + amount);
    }

    private User user;
}
