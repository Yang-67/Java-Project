package com.yang.controller;

import com.yang.pojo.User;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Test {
    @Autowired
    private RedissonClient redisson;

    @GetMapping("/test")
    private void test(){
//        RBucket<User> yang = redisson.getBucket("yang");
        List<User> userList = new ArrayList<>();
        userList.add(new User("杨鹏飞",18));
        userList.add(new User("杨鹏飞",18));
        userList.add(new User("杨鹏飞",18));
        userList.add(new User("杨鹏飞",18));

        RList<User> peng = redisson.getList("peng", TypedJsonJacksonCodec.INSTANCE);
        peng.add(new User("杨鹏飞",18));
        peng.addAll(userList);

//        yang.set(new User("杨鹏飞",18));
    }
}
