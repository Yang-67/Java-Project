package com.example.authenticationpermissions.service;


import com.example.authenticationpermissions.domain.ResponseResult;
import com.example.authenticationpermissions.domain.User;

public interface LoginServcie {
    ResponseResult login(User user);

    ResponseResult logout();

}
