package com.example.controller;

import com.example.bean.admin;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@ResponseBody
public class LoginController {

    @Autowired
    private AdminService adminService;

//    登录
    @PostMapping("/login")
    public String selectAll(@RequestBody admin dto){
        System.out.println("信息:"+dto.toString());
        admin list = adminService.getPwdById(dto.getAdminId());
        if(list==null){
            return "0";
        }else{
            if(list.getAdminPwd().equals(dto.getAdminPwd())){
                return "1";
            }
            return "-1";
        }
    }

//    注册
    @PostMapping("signUser")
    public String signUser(@RequestBody admin dto){
        System.out.println(dto.toString());
        return "1";
    }
}
