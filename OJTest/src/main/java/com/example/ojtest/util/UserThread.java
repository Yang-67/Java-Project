package com.example.ojtest.util;

import com.example.ojtest.entity.user;
import com.example.ojtest.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
@Configuration
public class UserThread implements Runnable{
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Autowired
    private UserMapper userMapper;
    @Override
    public void run() {
        synchronized (this){
            for(int i=0;i<500;i++)
                userMapper.insertTitleInfo("22","22","22");
            }
        }

}
