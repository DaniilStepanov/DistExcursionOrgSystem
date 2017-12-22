package com.kspt.eos.logic;

import com.kspt.eos.entity.Receipt;

public class LReceipt{

    public LReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public String printReceiptInString(){
        String res = "";
        res += "Organizer: " + receipt.getExcursion().getOrganizator().getUser().getLogin() +
                "\nDriver: "+ receipt.getExcursion().getDriver().getUser().getLogin() + "\n\nPaid "+
                receipt.getSum() + "\n";
        return res;
    }

    private Receipt receipt;
}
