package com.example.controller;

import com.example.common.Result;
import com.example.service.CommodityListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@ResponseBody
public class CommodityController {
    @Autowired
    private CommodityListService commodityListService;

    @PostMapping("/list")
    private Result list(){
        return Result.success(commodityListService.list());
    }
}
