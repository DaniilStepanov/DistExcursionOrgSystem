package com.kspt.eos.logic;

import com.kspt.eos.entity.Driver;
import com.kspt.eos.entity.Vehicle;

public class LDriver {

    public LDriver(Driver d) {
        this.driver = d;
    }
    public void addVehicle(String model, int mileage, int capacity, String numbers) {
        Vehicle vehicle = new Vehicle();
        vehicle.setModel(model);
        vehicle.setMilage(mileage);
        vehicle.setCapacity(capacity);
        vehicle.setNumbers(numbers);
        driver.setVehicle(vehicle);
    }

    public boolean checkVehicle() throws Exception{
        Vehicle vehicle = driver.getVehicle();
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
        driver.setAgree(true);
        driver.setFree(false);
    }

    public void disagree(){
        driver.setGivenPrice(0);
        driver.setAgree(false);
        driver.setExcursion(null);
    }

    public LUser getLUser() { return new LUser(driver.getUser()); }

    private Driver driver;

    public Driver getDriver() {
        return driver;
    }
}
