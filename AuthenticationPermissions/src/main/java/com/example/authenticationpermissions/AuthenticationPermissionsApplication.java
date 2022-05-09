package com.example.authenticationpermissions;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.authenticationpermissions.mapper")
public class AuthenticationPermissionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationPermissionsApplication.class, args);
    }

}
