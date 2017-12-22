package com.kspt.eos.controller;

import com.kspt.eos.entity.*;
import com.kspt.eos.exception.MyException;
import com.kspt.eos.logic.ErrorCodes;
import com.kspt.eos.logic.LExcursion;
import com.kspt.eos.logic.LOrganizator;
import com.kspt.eos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class OrganizatorController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ExcursionObjectRepository excursionObjectRepository;
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    ReceiptRepository receiptRepository;
    @Autowired
    OrganizatorRepository organizatorRepository;
    @Autowired
    ExcursionRepository excursionRepository;

    @RequestMapping(value = "rest/organizator/{login}")
    public Organizator getOrganizator(@PathVariable String login) {
        return organizatorRepository.findByUserLogin(login).orElseThrow(
                () -> new MyException.NotFoundException(login));
    }

    @RequestMapping(value = "rest/organizator/addOrganizator", method = RequestMethod.POST)
    public void addOrganizator(@RequestParam String login, @RequestParam String name,
                          @RequestParam String passwd) {
        User u = new User();
        u.setLogin(login);
        u.setPassword(passwd);
        u.setName(name);
        u.setMoney(100);
        Organizator o = new Organizator();
        o.setUser(u);
        userRepository.save(u);
        organizatorRepository.save(o);
    }

    @RequestMapping(value = "rest/organizator/{login}/excursion")
    public Excursion getExcursion(@PathVariable String login) {
        Optional<Organizator> orgOpt = organizatorRepository.findByUserLogin(login);
        if (!orgOpt.isPresent())
            return null;
        LExcursion excursion = new LExcursion(orgOpt.get().getExcursion());
        return excursion.getExcursion();
    }

    @RequestMapping(value = "rest/organizator/{login}/allDrivers")
    public List<Driver> getAllDrivers(@PathVariable String login) {
        return driverRepository.findAll();
    }

    @RequestMapping(value = "rest/organizator/{login}/addExcursionObject",  method = RequestMethod.POST)
    public void addExcursionObject(@PathVariable String login, @RequestParam String description) {
        Optional<Organizator> orgOpt = organizatorRepository.findByUserLogin(login);
        if (!orgOpt.isPresent())
            return;
        Excursion e = orgOpt.get().getExcursion();
        ExcursionObject eo = new ExcursionObject();
        eo.setDescription(description);
        eo.setExcursion(e);
        e.getObjects().add(eo);
        excursionObjectRepository.save(eo);
        excursionRepository.save(e);
    }

    @RequestMapping(value = "rest/organizator/{login}/startExcursion")
    public void startExcursion(@PathVariable String login) {
        Optional<Organizator> orgOpt = organizatorRepository.findByUserLogin(login);
        if (!orgOpt.isPresent())
            return;
        LOrganizator org = new LOrganizator(orgOpt.get());
        int status = org.beginExcursion();
        if (status != 0)
            throw new MyException(status);
        receiptRepository.save(orgOpt.get().getExcursion().getReceipt());
        driverRepository.save(orgOpt.get().getExcursion().getDriver());
        excursionRepository.save(orgOpt.get().getExcursion());
        organizatorRepository.save(orgOpt.get());
        userRepository.save(orgOpt.get().getUser());
        userRepository.save(orgOpt.get().getExcursion().getDriver().getUser());
    }

    @RequestMapping(value = "rest/organizator/{login}/endExcursion")
    public void endExcursion(@PathVariable String login) {
        System.out.println("LOL");
        Optional<Organizator> orgOpt = organizatorRepository.findByUserLogin(login);
        if (!orgOpt.isPresent())
            return;
        LOrganizator org = new LOrganizator(orgOpt.get());
        org.endExcursion();
        System.out.println(orgOpt.get().getExcursion().getName());
        driverRepository.save(orgOpt.get().getExcursion().getDriver());
        orgOpt.get().getExcursion().setDriver(null);
        excursionRepository.save(orgOpt.get().getExcursion());
        organizatorRepository.save(orgOpt.get());
    }

    @RequestMapping(value = "rest/organizator/sendOffer", method = RequestMethod.POST)
    public void sendOffer(@RequestParam String orgLogin, @RequestParam String driverId,
                          @RequestParam String money) {
        System.out.println(orgLogin + driverId);
        Optional<Organizator> orgOpt = organizatorRepository.findByUserLogin(orgLogin);
        if (!orgOpt.isPresent())
            return;
        Optional<Driver> driverOpt = driverRepository.findByUserId(Long.decode(driverId));
        if (!driverOpt.isPresent())
            return;
        LOrganizator org = new LOrganizator(orgOpt.get());
        int status = org.sendOfferToDriver(driverOpt.get(), Integer.parseInt(money));
        if (status != ErrorCodes.success)
            throw new MyException(status);
        System.out.println("Driver info= " + driverOpt.get().getExcursion().getName());
        excursionRepository.save(orgOpt.get().getExcursion());
        driverRepository.save(driverOpt.get());
        organizatorRepository.save(orgOpt.get());
    }

    @RequestMapping(value = "rest/organizator/{login}/addNewExcursion", method = RequestMethod.POST)
    public void addNewExcursion(@PathVariable String login, @RequestParam String name,
                                @RequestParam String minTourists, @RequestParam String maxTourists,
                                @RequestParam String equipment) {
        System.out.println("adding Excursion");
        Optional<Organizator> orgOpt = organizatorRepository.findByUserLogin(login);
        if (!orgOpt.isPresent())
            return;
        Excursion e = new Excursion();
        e.setName(name);
        excursionRepository.save(e);
        LExcursion lExcursion = new LExcursion(e);
        Date departureDate = Date.valueOf("2027-01-01");
        int error_code = lExcursion.setExcursionInfo(Integer.parseInt(minTourists), Integer.parseInt(maxTourists), equipment, departureDate, 1);
        System.out.println("error code = " + error_code);
        if (error_code != 0)
            throw new MyException(ErrorCodes.getError(error_code));
        LOrganizator org = new LOrganizator(orgOpt.get());
        org.setExcursion(e);
        excursionRepository.save(e);
    }

}
