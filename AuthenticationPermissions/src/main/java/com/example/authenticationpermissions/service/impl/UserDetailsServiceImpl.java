package com.example.authenticationpermissions.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.authenticationpermissions.domain.LoginUser;
import com.example.authenticationpermissions.domain.User;
import com.example.authenticationpermissions.mapper.MenuMapper;
import com.example.authenticationpermissions.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

//    @Resource
//    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
//        System.out.println("userDetail结果"+username+","+user.toString());
        //如果没有查询到用户就抛出异常
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或者密码错误");
        }

//        List<String> list = new ArrayList<>(Arrays.asList("test","admin"));
//        System.out.println("menuStart:"+"-------");
//        List<String> list = menuMapper.selectPermsByUserId((long) user.getId());
//        System.out.println("menuEnd:"+"-------");
        //把数据封装成UserDetails返回
        return new LoginUser(user);
    }
}
