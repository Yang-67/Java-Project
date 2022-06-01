package com.yang.swaggerdemo.controller;

import com.yang.swaggerdemo.pojo.Test;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "测试Controller", tags = {"测试访问接口"})
@RequestMapping("/test")
public class TestController {
    @ApiOperation(value = "添加一个学生信息")
    @PostMapping("/addFilm")
    @ApiResponses(value = {@ApiResponse(code = 1000, message = "成功"), @ApiResponse(code = 1001, message = "失败"),
            @ApiResponse(code = 1002, response = Test.class, message = "缺少参数")})
    public String addFilm(@ApiParam("发起者姓名") @RequestParam("userName") String userName,
                          @ApiParam(value = "分数", allowEmptyValue = true) @RequestParam("score") Short score,
                          @ApiParam("发布时间") @RequestParam(value = "publishTime", required = false) String publishTime) {
        return "success";
    }
}
