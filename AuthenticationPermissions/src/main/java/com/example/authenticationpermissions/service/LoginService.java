package com.example.authenticationpermissions.service;


import com.example.authenticationpermissions.domain.ResponseResult;
import com.example.authenticationpermissions.domain.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();

}
