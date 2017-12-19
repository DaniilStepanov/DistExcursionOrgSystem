package com.kspt.eos.controller;

import com.kspt.eos.entity.User;
import com.kspt.eos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by kivi on 03.12.17.
 */
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping({"rest/{login}"})
    public User getUser(@PathVariable String login) {
        User u = new User();
        u.setName("admin");
        u.setPassword("admin");
        u.setLogin("admin");
        userRepository.save(u);
        User user = userRepository.findByLogin(login).get();
        return user;
    }

}