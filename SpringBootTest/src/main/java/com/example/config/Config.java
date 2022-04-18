package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    /*把配置文件中 spring.datasource.initialSize 等属性和 DruidDataSource 中属 性 进行绑定*/
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DruidDataSource druidDataSource () {
        return new DruidDataSource();
    }
}
//没用