package com.kspt.eos.logic;

import com.kspt.eos.entity.Excursion;
import com.kspt.eos.entity.ExcursionObject;
import com.kspt.eos.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class LExcursion extends Excursion {

    public int setExcursionInfo(int minTourists, int maxTourists,
                                String equipment, java.sql.Date departureDate, int status){
        setMinTourists(minTourists);
        setMaxTourists(maxTourists);
        setEquipment(equipment);
        setDepartureDate(departureDate);
        if (minTourists > maxTourists)
            return 1;
        Date d = new Date();
        if (departureDate.before(d))
            return 2;
        setStatus(status);
        return 0;
    }

    public int beginExcursion(){
        LDriver driver = getLDriver();
        Set<User> users = getTravellers();
        if (driver.isAgree() == false)
            return ErrorCodes.driverIsNotAgree;
        if (users.size() > getMaxTourists())
            return ErrorCodes.maxTourists;
        if (users.size() < getMinTourists())
            return ErrorCodes.minTourists;
        getLOrganizator().payToDriver(driver, driver.getGivenPrice());
        setStatus(2);
        return ErrorCodes.success;
    }

    public int endExcursion(){
        setStatus(3);
        setDriver(null);
        return ErrorCodes.success;
    }

    public void addExcursionObject(ExcursionObject obj){
        getObjects().add(obj);
    }

    public String printExcursionInStr(){
        String res = "";
        res += "Excursion number " + getId() + "\n";
        res += "Organizator is " + getOrganizator().getUser().getLogin() + "\nDriver is ";
        if(getDriver() == null)
            res += "No driver\n";
        else
        if(getDriver().isAgree())
            res += getDriver().getUser().getLogin() + " (agree)\n";
        else
            res += getDriver().getUser().getLogin() +" (not agree)\n";
        res += "Excursion objects:\n";
        int ind = 1;
        for (ExcursionObject eo: getObjects()){
            res += ind + ". " + eo.printDescriptionInString();
            ++ind;
        }
        res += "\n\n";
        res += "List of participants:\n";
        for (int i = 0; i < getTravellers().size(); ++i){
            res += i + ". " + getTravellers().get(i).getLogin() + "\n";
        }
        res += "\n\n";
        if (isPay() && getStatus() != 3) {
            res += "Excursion is paid \n";
            res += getLReceipt().printReceiptInString();
        }
        else {
            res+= "Excursion still not paid";
        }
        return res;
    }

    public LDriver getLDriver() {
        return (LDriver) getDriver();
    }

    public LOrganizator getLOrganizator() {
        return (LOrganizator) getOrganizator();
    }

    public LReceipt getLReceipt() {
        return (LReceipt) getReceipt();
    }
}
