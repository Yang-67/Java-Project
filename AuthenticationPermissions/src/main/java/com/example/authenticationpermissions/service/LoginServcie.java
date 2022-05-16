package com.example.authenticationpermissions.service;


import com.example.authenticationpermissions.domain.Result;
import com.example.authenticationpermissions.domain.User;

public interface LoginServcie {
    Result login(User user);

    Result logout();

}
