package com.kspt.eos.controller;

import com.kspt.eos.entity.Excursion;
import com.kspt.eos.entity.Organizator;
import com.kspt.eos.entity.User;
import com.kspt.eos.exception.MyException;
import com.kspt.eos.logic.ErrorCodes;
import com.kspt.eos.logic.LExcursion;
import com.kspt.eos.repository.DriverRepository;
import com.kspt.eos.repository.ExcursionRepository;
import com.kspt.eos.repository.OrganizatorRepository;
import com.kspt.eos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    OrganizatorRepository organizatorRepository;
    @Autowired
    ExcursionRepository excursionRepository;

    @RequestMapping(value = "rest/user/addUser", method = RequestMethod.POST)
    public void addUser(@RequestParam String login, @RequestParam String name,
                               @RequestParam String passwd) {
        User u = new User();
        u.setLogin(login);
        u.setPassword(passwd);
        u.setName(name);
        u.setMoney(100);
        userRepository.save(u);
    }

    @RequestMapping("rest/user/{login}")
    public User getUser(@PathVariable String login) {
        System.out.println("Getting user");
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new MyException.NotFoundException(login));
    }

    @RequestMapping("rest/user/{login}/authenticate")
    public int authenticate(@PathVariable String login,
                               @RequestParam("passwd") String passwd) {
        Optional<User> userOpt = userRepository.findByLogin(login);
        if (!userOpt.isPresent())
            return 0;
        User user = userOpt.get();
        if (!user.getPassword().equals(passwd))
            return 0;
        if (driverRepository.findByUserLogin(login).isPresent())
            return 1;
        if (organizatorRepository.findByUserLogin(login).isPresent())
            return 2;
        else
            return 3;
    }

    @RequestMapping("rest/user/{login}/excursions")
    public List<Excursion> getUserExcursions(@PathVariable String login) {
        List <Excursion> res = new ArrayList<Excursion>();
        for (Excursion e: excursionRepository.findAll()) {
            for (User u : e.getUsers()) {
                if (u.getLogin().equals(login))
                    res.add(e);
            }
        }
        return res;
    }

    @RequestMapping("rest/user/allExcursions")
    public List<Excursion> getAllExcursions() {
        System.out.println("Lilka");
        System.out.println(excursionRepository.findAll().size());
        return excursionRepository.findAll();
    }

    @RequestMapping("rest/user/{login}/excursion")
    public Excursion getExcursion(@PathVariable String login,
                                  @PathVariable Long id) {
        System.out.println("LOL");
        System.out.println("ID = " + id);
        return excursionRepository.findById(id).orElseThrow(
                () -> new MyException.NotFoundException(id.toString()));
    }

    @RequestMapping("rest/user/addToExcursion")
    public void addToExcursion(@RequestParam String login, @RequestParam String id) {
        System.out.println(Long.decode(id));
        Optional<User> user = userRepository.findByLogin(login);
        if (!user.isPresent())
            throw new MyException(ErrorCodes.error);
        Optional<Excursion> excursion = excursionRepository.findById(Long.decode(id));
        if (!excursion.isPresent())
            throw new MyException(ErrorCodes.error);
        LExcursion lexc = new LExcursion(excursion.get());
        int error_code = lexc.addUser(user.get());
        System.out.println("error = " + error_code);
        if (error_code != 0)
            throw new MyException(error_code);
        System.out.println("SIZE = " + excursion.get().getUsers().size());
        excursionRepository.save(excursion.get());
    }

    @RequestMapping("rest/user/delFromExcursion")
    public void delFromExcursion(@RequestParam String login, @RequestParam String id) {
        System.out.println(Long.decode(id));
        Optional<User> user = userRepository.findByLogin(login);
        if (!user.isPresent())
            return;
        Optional<Excursion> excursion = excursionRepository.findById(Long.decode(id));
        if (!excursion.isPresent())
            return;
        LExcursion lexc = new LExcursion(excursion.get());
        lexc.delUser(user.get());
        System.out.println(lexc.getExcursion().getUsers().size());
        excursionRepository.save(excursion.get());
    }

    @RequestMapping("rest/excursion/{id}")
    public Excursion getExcursion(@PathVariable Long id) {
        System.out.println("LOL");
        return excursionRepository.findById(id)
                .orElseThrow(() -> new MyException.NotFoundException(id.toString()));
    }

}