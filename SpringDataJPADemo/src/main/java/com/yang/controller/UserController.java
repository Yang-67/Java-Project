package com.yang.controller;

import com.yang.pojo.User;
import com.yang.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public User insertUser(User user){
        return userRepository.save(user);
    }

    @GetMapping("/all")
    public List<User> getAll(){
        return userRepository.findAll();
    }

}
