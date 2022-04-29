package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import com.example.bean.admin;
import com.example.common.Result;
import com.example.service.serviceImpl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/echarts")
public class EchartsController {
    @Autowired
    private AdminServiceImpl adminService;

    @GetMapping("/members")
    public Result members() {
        List<admin> list = adminService.list();
        int n1 = 0; // 男
        int n2 = 0; // 女
        for (admin user : list) {
            int sex = user.getAdminSex();
            switch (sex) {
                case 0: n1 += 1; break;
                case 1: n2 += 1; break;
                default: break;
            }
        }
        return Result.success(CollUtil.newArrayList(n1,n2));
    }
}
