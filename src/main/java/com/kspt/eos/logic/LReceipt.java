package com.kspt.eos.logic;

import com.kspt.eos.entity.Receipt;

public class LReceipt extends Receipt {

    public String printReceiptInString(){
        String res = "";
        res += "Organizer: " +getExcursion().getOrganizator().getUser().getLogin() +
                "\nDriver: "+ getExcursion().getDriver().getUser().getLogin() + "\n\nPaid "+
                getSum() + "\n";
        return res;
    }
}
