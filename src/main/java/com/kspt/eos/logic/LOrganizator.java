package com.kspt.eos.logic;

import com.kspt.eos.entity.Driver;
import com.kspt.eos.entity.Excursion;
import com.kspt.eos.entity.Organizator;
import com.kspt.eos.entity.Receipt;

public class LOrganizator{

    public LOrganizator(Organizator org) {
        this.org = org;
    }

    public void endExcursion(){

        LExcursion excursion = getLExcursion();
        if(excursion.getExcursion().getDriver() != null) {
            excursion.getExcursion().getDriver().setFree(true);
            excursion.getExcursion().getDriver().setAgree(false);
        }
        excursion.endExcursion();
    }

    public int sendOfferToDriver(Driver d, int price){
        LExcursion excursion = getLExcursion();
        if(excursion.getExcursion().getDriver() != null && excursion.getExcursion().getDriver().isAgree())
            return ErrorCodes.excursionAlreadyHaveADriver;
        if(org.getUser().getMoney() < price)
            return ErrorCodes.wrongMoney;
        if (!d.isFree())
            return ErrorCodes.driverIsBusy;
        if (d.getVehicle() == null)
            return ErrorCodes.driverWIthoutVehicle;
        if (!d.getVehicle().isChecked())
            return ErrorCodes.driversVehicle;
        if (d.getVehicle().getCapacity() < excursion.getExcursion().getMaxTourists())
            return ErrorCodes.capacity;
        d.setGivenPrice(price);
        d.setExcursion(excursion.getExcursion());
        excursion.getExcursion().setDriver(d);
        return ErrorCodes.success;
    }

    public int beginExcursion(){
        if (org.getExcursion() == null)
            return ErrorCodes.excursionIsNull;
        int status = getLExcursion().beginExcursion();
        return status;
    }

    public int setDriver(Driver d){
        if (!d.isAgree())
            return ErrorCodes.driverIsNotAgree;
        org.getExcursion().setDriver(d);
        return ErrorCodes.success;
    }

    public void setExcursion(Excursion e){
        e.setOrganizator(org);
        org.setExcursion(e);
    }

    public void payToDriver(Driver d, int sum){
        if(d.getUser().getLogin().equals(org.getExcursion().getDriver().getUser().getLogin()) &&
                !org.getExcursion().isPay()){
            Receipt rec = PaySystem.pay(getLExcursion(), sum);
            getLExcursion().setPay(true, rec);
        }
    }

    LExcursion getLExcursion() { return new LExcursion(org.getExcursion());}
    LUser getLUser() {return new LUser(org.getUser());}


    private Organizator org;

}
