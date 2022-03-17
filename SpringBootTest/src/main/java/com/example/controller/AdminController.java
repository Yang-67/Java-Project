package com.example.controller;

import com.example.bean.AdminBean;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdminController {
    @GetMapping("/admin")
    public AdminBean admin(){
        AdminBean admin = new AdminBean("1","z",1,"1",12,"1","1",1,1);
        return admin;
    }
}
