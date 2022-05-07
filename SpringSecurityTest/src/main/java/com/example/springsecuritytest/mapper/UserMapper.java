package com.example.springsecuritytest.mapper;

import com.example.springsecuritytest.model.Role;
import com.example.springsecuritytest.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User loadUserByUsername(String username);
    List<Role> getUserRolesByUid(Integer id);
}
