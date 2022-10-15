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
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class RedisDemoApplicationTests {

    @Autowired
//    @Qualifier("redisTemplate")
    private RedisCache redisCache;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final ObjectMapper mapper = new ObjectMapper();
    @Test
    @Transactional
    void contextLoads() throws JsonProcessingException {
//        stringRedisTemplate.opsForValue().set("peng","feier");
        List<User> list = new ArrayList<>();
        list.add(new User("yang",1));
        list.add(new User("peng",2));
        list.add(new User("fei",3));
        redisTemplate.opsForList().leftPush("user2",list);
        System.out.println("list:"+list);
        List<User> str = redisTemplate.opsForList().range("user2",0,-1);
        System.out.println("str:"+str);

//        User user = new User("yang",1);
//        Map<String,String> map = new HashMap<>();
//        map.put("name","yang");
//        map.put("age","18");
//        stringRedisTemplate.opsForHash().putAll("map",map);
//        redisCache.setCacheObject("user1",user);
//        System.out.println(redisCache.getCacheObject("user1").toString());
    }

    private String getToken(String k) {
        return "Token";
    }

    private List<User> getServices(String env) {
        return new ArrayList<>();
    }




//    private LoadingCache<String, String> tokenCache = Caffeine.newBuilder()
//            .maximumSize(2)
//            .expireAfterWrite(6, TimeUnit.HOURS)
//            .build(this::getToken);
//
//    private AsyncLoadingCache<String, List<Application>> serviceCache = Caffeine.newBuilder()
//            .maximumSize(50)
//            .expireAfterWrite(1, TimeUnit.HOURS)
//            .buildAsync(k -> this.getServices(k).get());

}
