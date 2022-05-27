package com.example.redisdemo;

import com.alibaba.fastjson.JSONObject;
import com.example.redisdemo.pojo.User;
import com.example.redisdemo.utils.RedisCache;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisDemoApplicationTests {

    @Autowired
//    @Qualifier("redisTemplate")
    private RedisCache redisCache;

    @Test
    void contextLoads() throws JsonProcessingException {
        User user = new User("yang",1);
        redisCache.setCacheObject("user",user);
        System.out.println(redisCache.getCacheObject("user").toString());
    }

}
