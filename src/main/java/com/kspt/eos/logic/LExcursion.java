package com.kspt.eos.logic;

import com.kspt.eos.entity.Excursion;
import com.kspt.eos.entity.ExcursionObject;
import com.kspt.eos.entity.Receipt;
import com.kspt.eos.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class LExcursion {

    public LExcursion(Excursion e) {
        this.e = e;
    }

    public int setExcursionInfo(int minTourists, int maxTourists,
                                String equipment, java.sql.Date departureDate, int status){
        e.setMinTourists(minTourists);
        e.setMaxTourists(maxTourists);
        e.setEquipment(equipment);
        e.setDepartureDate(departureDate);
        if (minTourists > maxTourists)
            return 15;
        Date d = new Date();
        if (departureDate.before(d))
            return 14;
        e.setStatus(status);
        return 0;
    }


    public int beginExcursion(){
        LDriver d = getLDriver();
        if (d.getDriver() == null)
            return ErrorCodes.nullptr;
        Set<User> users = e.getUsers();
        if (!d.getDriver().isAgree())
            return ErrorCodes.driverIsNotAgree;
        if (users.size() > e.getMaxTourists())
            return ErrorCodes.maxTourists;
        if (users.size() < e.getMinTourists())
            return ErrorCodes.minTourists;
        getLOrganizator().payToDriver(d.getDriver(), d.getDriver().getGivenPrice());
        e.setStatus(2);
        return ErrorCodes.success;
    }

    public int endExcursion(){
        e.setStatus(3);
        return ErrorCodes.success;
    }

    public void addExcursionObject(ExcursionObject obj){
        e.getObjects().add(obj);
    }

    public void updateDescription(){
        e.setDescription(printExcursionInStr().toString());
    }

    public StringBuilder printExcursionInStr(){
        StringBuilder res = new StringBuilder("");
        res.append("Excursion number ").append(e.getId()).append("\n");
        res.append("Organizator is ").append(e.getOrganizator().getUser().getLogin()).append("\nDriver is ");
        if(e.getDriver() == null)
            res.append("No driver\n");
        else
        if(e.getDriver().isAgree())
            res.append(e.getDriver().getUser().getLogin()).append(" (agree)\n");
        else
            res.append(e.getDriver().getUser().getLogin()).append(" (not agree)\n");
        res.append("Excursion objects:\n");
        int ind = 1;
        for (ExcursionObject eo: e.getObjects()){
            res.append(ind).append(". ").append(eo.getDescription());
            ++ind;
        }
        res.append("\n\n");
        res.append("List of participants:\n");
        int i = 0;
        for (User u : e.getUsers()) {
            res.append(i).append(". ").append(u.getLogin()).append("\n");
            ++i;
        }
        res.append("\n\n");
        if (e.isPay() && e.getStatus() != 3) {
            res.append("Excursion is paid \n");
            res.append(getLReceipt().printReceiptInString());
        }
        else {
            res.append("Excursion still not paid");
        }
        return res;
    }

    public int addUser(User u){
        if (e.getStatus() == 0)
            return ErrorCodes.early;
        if(e.getStatus() > 1)
            return ErrorCodes.late;
        for (User us : e.getUsers()){
            if( u.getLogin().equals(us.getLogin()))
                return ErrorCodes.userAlreadyInExcursion;
        }
        if (e.getUsers().size() == e.getMaxTourists())
            return ErrorCodes.maxTourists;
        e.getUsers().add(u);
        return ErrorCodes.success;
    }

    public void delUser(User u){
        e.getUsers().removeIf(us -> us.getLogin().equals(u.getLogin()));
    }

    public String getStringStatus() {
        switch (e.getStatus()){
            case 0:
                return "Setting";
            case 1:
                return "Ready";
            case 2:
                return "In progress";
            case 3:
                return "End";
            default:
                return "";
        }
    }

    public LDriver getLDriver() {
        return new LDriver(e.getDriver());
    }

    public LOrganizator getLOrganizator() {
        return new LOrganizator(e.getOrganizator());
    }

    public LReceipt getLReceipt() {
        return new LReceipt(e.getReceipt());
    }

    public void setPay(boolean b, Receipt receipt){e.setPay(b); e.setReceipt(receipt);}

    public Excursion getExcursion() {
        return e;
    }

    private Excursion e;
}
