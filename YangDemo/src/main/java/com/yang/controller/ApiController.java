package com.yang.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@Api(tags = "这是一个controller")
public class ApiController {

    @ApiOperation(value = "方法1")
    @GetMapping("/test1")
    public void test1() {

     }

    @ApiOperation(value = "方法2")
    @PostMapping("/test2")
    public void test2() {

    }

    @ApiOperation(value = "方法3")
    @PutMapping("/test3")
    public void test3() {

    }

    @ApiOperation(value = "方法4")
    @DeleteMapping("/test4")
    public void test4() {

    }

    @ApiOperation(value = "方法5")
    @GetMapping("/test5")
    public void test5() {

    }

    @ApiOperation(value = "方法6")
    @PutMapping("/test6")
    public void test6() {

    }
}
