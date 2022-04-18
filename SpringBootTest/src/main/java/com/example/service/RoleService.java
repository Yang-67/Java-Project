package com.example.service;

import com.example.bean.role;

import java.util.Collection;
import java.util.List;

public interface RoleService {
    //    查询全部角色信息
    List<role> selectRole();

    int selectRoleCount();

    boolean saveBatch(Collection<role> entityList);
}
