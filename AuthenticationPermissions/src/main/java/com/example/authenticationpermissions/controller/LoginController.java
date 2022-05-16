package com.example.authenticationpermissions.controller;

import com.example.authenticationpermissions.domain.Result;
import com.example.authenticationpermissions.domain.User;
import com.example.authenticationpermissions.service.LoginServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginServcie loginServcie;

    @PostMapping("/user/login")
    public Result login(@RequestBody User user){
        System.out.println(user.toString());
        //登录
        return loginServcie.login(user);
    }

    @RequestMapping("/user/logout")
    public Result logout(){
        return loginServcie.logout();
    }
}
