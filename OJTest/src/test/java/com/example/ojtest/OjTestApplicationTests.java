package com.example.ojtest;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ojtest.entity.solutionInfo;
import com.example.ojtest.entity.user;
import com.example.ojtest.mapper.SolutionMapper;
import com.example.ojtest.mapper.TitleMapper;
import com.example.ojtest.mapper.UserMapper;
import com.example.ojtest.util.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class OjTestApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println(UserUtil.getRandomName());
//        long startTime = System.currentTimeMillis();
//        for(int i=0;i<1000;i++)
//            userMapper.insert(new user(UserUtil.getRandomName(),new Random().nextInt(2), PhoneUtil.getRandomPhone()));
//        long endTime = System.currentTimeMillis();
//        System.out.println("运行时间为："+ (endTime-startTime));
    }




    @Test
    void insertUser(){
        long startTime = System.currentTimeMillis();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(this){
                    for (int i = 0; i < 500; i++)
                        userMapper.insert(new user(UserUtil.getRandomName(),new Random().nextInt(2), PhoneUtil.getRandomPhone()));
                }
            }
        }).start();
        for(int i=0;i<500;i++)
            userMapper.insert(new user(UserUtil.getRandomName(),new Random().nextInt(2), PhoneUtil.getRandomPhone()));
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间为："+ (endTime-startTime));
    }

    @Autowired
    private TitleMapper titleMapper;
    @Test
    void insertTitle(){
        long startTime = System.currentTimeMillis();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(this){
                    for (int i = 0; i < 500; i++)
                        titleMapper.insert(new NumUtil().TitleInfo());
                }
            }
        }).start();
        for(int i=0;i<500;i++)
            titleMapper.insert(new NumUtil().TitleInfo());
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间为："+ (endTime-startTime));
    }

    @Autowired
    private SolutionMapper solutionMapper;
    @Autowired
    private ServiceImp service;
    @Test
    void insertSolution(){
        long startTime = System.currentTimeMillis();
        Random random=new Random();
        List<solutionInfo> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add(new solutionInfo(String.valueOf(random.nextInt(100)),
                    ((int)(Math.random()*(20610-19620+1))+19620),((int)(Math.random()*(999-1+1))+1)));
        }
        service.saveBatch(list);
        long endTime = System.currentTimeMillis();
        System.out.println("插入解题记录运行时间为："+ (endTime-startTime));
    }

    @Test
    void selectAll(){
        long startTime = System.currentTimeMillis();

        long endTime = System.currentTimeMillis();
        System.out.println("插入解题记录运行时间为："+ (endTime-startTime));
    }
}
