package com.kspt.eos.logic;

import com.kspt.eos.entity.Excursion;
import com.kspt.eos.entity.Receipt;

import java.util.ArrayList;

public class PaySystem {
    public static Receipt pay(LExcursion e, int sum){
        e.getLOrganizator().getLUser().subMoney(sum);
        e.getLDriver().getLUser().addMoney(sum);
        Receipt r = new Receipt();
        r.setExcursion(e.getExcursion());
        r.setSum(sum);
        recps.add(r);
        return r;
    }

    public static Receipt getCreatedReceipt(int uid, Excursion e, int sum){
        Receipt r = new Receipt();
        r.setExcursion(e);
        r.setSum(sum);
        return r;
    }

    public static Receipt getReceipt(Excursion e){
        for(Receipt rec : recps){
            if(rec.getExcursion() == e)
                return rec;
        }
        return null;
    }
    private static ArrayList<Receipt> recps = new ArrayList<Receipt>();
}