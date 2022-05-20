package com.example.authenticationpermissions;

import com.example.authenticationpermissions.mapper.MenuMapper;
import com.sun.org.apache.bcel.internal.util.BCELComparator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class AuthenticationPermissionsApplicationTests {

    @Autowired
    private MenuMapper menuMapper;
    @Test
    void contextLoads() {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        System.out.println(passwordEncoder.encode("123"));

        List<String> list = menuMapper.selectPermsByUserId(1L);
        System.out.println(list);
    }

}
