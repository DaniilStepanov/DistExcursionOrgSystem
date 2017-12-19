package com.kspt.eos.logic;

import com.kspt.eos.entity.Excursion;
import com.kspt.eos.entity.Organizator;

public class LOrganizator extends Organizator {

    public void endExcursion(){
        Excursion excursion = getExcursion();
        if(excursion.getDriver() != null) {
            excursion.getDriver().setFree(true);
        }
        //excursion.endExcursion();
    }


//
//    public int sendOfferToDriver(Driver d, int price){
//        if(this.excursion.getDriver() != null && this.excursion.getDriver().isAgree())
//            return ErrorCodes.excursionAlreadyHaveADriver;
//        if(this.getMoney() < price)
//            return ErrorCodes.wrongMoney;
//        if (!d.isFree())
//            return ErrorCodes.driverIsBusy;
//        if (d.getVehicle() == null)
//            return ErrorCodes.driverWIthoutVehicle;
//        if (!d.getVehicle().isChecked())
//            return ErrorCodes.driversVehicle;
//        if (d.getVehicle().getCapacity() < excursion.getMaxTourists())
//            return ErrorCodes.capacity;
//        d.setGivenPrice(price);
//        d.setExc(excursion);
//        excursion.setDriver(d);
//        return ErrorCodes.success;
//    }
}
