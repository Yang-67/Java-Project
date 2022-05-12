package com.example.ojtest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.ojtest.mapper")
public class OjTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(OjTestApplication.class, args);
    }

}
