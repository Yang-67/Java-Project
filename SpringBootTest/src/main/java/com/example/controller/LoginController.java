package com.example.controller;

import cn.hutool.core.util.StrUtil;
import com.example.bean.admin;
import com.example.common.Constants;
import com.example.common.Result;
import com.example.controller.entity.adminDto;
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
    public Result selectAll(@RequestBody admin dto){
        String adminId = dto.getAdminId();
        String adminPwd = dto.getAdminPwd();
        if(StrUtil.isBlank(adminId) || StrUtil.isBlank(adminPwd)){
            return Result.error(Constants.CODE_400,"参数错误");
        }
        adminDto adDto = adminService.login(dto);
        return Result.success(adDto);
    }

//    注册
    @PostMapping("signUser")
    public String signUser(@RequestBody admin dto){
        System.out.println(dto.toString());
        return "1";
    }
}
