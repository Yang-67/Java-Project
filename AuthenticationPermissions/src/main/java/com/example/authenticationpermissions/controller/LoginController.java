package com.example.authenticationpermissions.controller;

import com.example.authenticationpermissions.domain.ResponseResult;
import com.example.authenticationpermissions.domain.User;
import com.example.authenticationpermissions.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        //登录
        return loginService.login(user);
    }

    @RequestMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
