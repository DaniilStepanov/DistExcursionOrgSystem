package com.kspt.eos.controller;

import com.kspt.eos.entity.Driver;
import com.kspt.eos.entity.User;
import com.kspt.eos.entity.Vehicle;
import com.kspt.eos.exception.MyException;
import com.kspt.eos.logic.LDriver;
import com.kspt.eos.repository.DriverRepository;
import com.kspt.eos.repository.UserRepository;
import com.kspt.eos.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DriverController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    DriverRepository driverRepository;

    @RequestMapping(value = "rest/driver/{login}")
    public Driver getDriver(@PathVariable String login) {
        return driverRepository.findByUserLogin(login).orElseThrow(
                () -> new MyException.NotFoundException(login));
    }

    @RequestMapping(value = "rest/driver/addDriver", method = RequestMethod.POST)
    public void addDriver(@RequestParam String login, @RequestParam String name,
                          @RequestParam String passwd) {
        User u = new User();
        u.setLogin(login);
        u.setPassword(passwd);
        u.setName(name);
        u.setMoney(100);
        Driver d = new Driver();
        d.setUser(u);
        d.setFree(true);
        d.setAgree(false);
        d.setGivenPrice(0);
        userRepository.save(u);
        driverRepository.save(d);
    }


    @RequestMapping(value = "rest/driver/{login}/addVehicle/add", method = RequestMethod.POST)
    public void addVehicle(@PathVariable String login, @RequestParam String model,
                           @RequestParam int milage, @RequestParam int capacity,
                           @RequestParam String numbers) {
        Optional<Driver> d = driverRepository.findByUserLogin(login);
        if (d.isPresent()) {
            Driver driver = d.get();
           /* if (driver.getVehicle() != null)
                vehicleRepository.delete(driver.getVehicle());*/
            Vehicle v = new Vehicle();
            v.setModel(model);
            v.setMilage(milage);
            v.setCapacity(capacity);
            v.setNumbers(numbers);
            v.setChecked(true);
            vehicleRepository.save(v);
            driver.setVehicle(v);
            userRepository.save(driver.getUser());
            driverRepository.save(driver);
        }
    }

    @RequestMapping(value = "rest/driver/{login}/agree")
    public void agree(@PathVariable String login){
        Optional<Driver> driverOpt = driverRepository.findByUserLogin(login);
        if (!driverOpt.isPresent())
            return;
        LDriver driver = new LDriver(driverOpt.get());
        driver.agree();
        driverRepository.save(driverOpt.get());
    }

    @RequestMapping(value = "rest/driver/{login}/disagree")
    public void disagree(@PathVariable String login){
        Optional<Driver> driverOpt = driverRepository.findByUserLogin(login);
        if (!driverOpt.isPresent())
            return;
        LDriver driver = new LDriver(driverOpt.get());
        driver.disagree();
        driverRepository.save(driverOpt.get());
    }

}
