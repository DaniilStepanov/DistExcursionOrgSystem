package com.kspt.eos.logic;

import com.kspt.eos.entity.Driver;
import com.kspt.eos.entity.Vehicle;

public class LDriver extends Driver{

    public void addVehicle(String model, int mileage, int capacity, String numbers) {
        Vehicle vehicle = new Vehicle();
        vehicle.setModel(model);
        vehicle.setMilage(mileage);
        vehicle.setCapacity(capacity);
        vehicle.setNumbers(numbers);
        setVehicle(vehicle);
    }

    public boolean checkVehicle() throws Exception{
        Vehicle vehicle = getVehicle();
        vehicle.setChecked(true);
        if(vehicle.isChecked()){
            return true;
        }
        else{
            vehicle = null;
            return false;
        }
    }

    public void agree(){
        setAgree(true);
        setFree(false);
    }

    public void disagree(){
        setGivenPrice(-1);
        setAgree(false);
        setExcursion(null);
    }

}
