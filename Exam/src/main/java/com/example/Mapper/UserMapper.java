package com.example.Mapper;

import com.example.pojo.User;

import java.util.Map;

public interface UserMapper {
    User getUser(Map<String, String> map);
}
