package com.example.redisdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TestController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/redis")
    public boolean testRedis(){
        redisTemplate.opsForValue().set("test","hello");//添加一个String类型的数据键test，值hello
        String code = (String) redisTemplate.opsForValue().get("test");
        System.out.println(code);
        return true;
    }

}
