package com.yang.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yang
 * @Date 2023/1/2 19:13
 * @Description
 */
@RestController
@RequestMapping("/yang")
@Api(tags = "测试类")
public class TestController {

    @ApiOperation(value = "测试方法1")
    @GetMapping("/test1")
    public void test1() {

    }

    @ApiOperation(value = "测试方法2")
    @PostMapping("/test2")
    public void test2() {

    }

    @ApiOperation(value = "测试方法3")
    @PutMapping("/test3")
    public void test3() {

    }

    @ApiOperation(value = "测试方法4")
    @DeleteMapping("/test4")
    public void test4() {

    }

    @ApiOperation(value = "测试方法5")
    @PostMapping("/test5")
    public void test5() {

    }

    @ApiOperation(value = "测试方法6")
    @DeleteMapping("/test6")
    public void test6() {

    }
}
