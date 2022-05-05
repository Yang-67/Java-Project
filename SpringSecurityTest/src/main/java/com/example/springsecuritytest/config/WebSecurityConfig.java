package com.example.springsecuritytest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("root")
                .password("123")
                .roles("ADMIN", "DBA")
                .and()
                .withUser("admin")
                .password("123")
                .roles("ADMIN", "USER")
                .and()
                .withUser("sang")
                .password("123")
                .roles("USER");
    }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/admin/**")
                    .hasRole("ADMIN")
                    .antMatchers("/user/**")
                    .access("hasAnyRole('ADMIN','USER')")
                    .antMatchers("/dba/**")
                    .access("hasRole('ADMIN') and hasRole('DBA')")
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
                    .loginProcessingUrl("/login")
                    .permitAll()
                    .and()
                    .csrf()
                    .disable();
        }
    }


