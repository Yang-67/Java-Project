package com.example.mapper;

import com.example.pojo.User;

import java.util.Map;

public interface UserMapper {
    //判断登录者
    User getUser(Map<String, String> map);
}
