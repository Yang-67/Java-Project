package com.example.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bean.role;
import com.example.mapper.RoleMapper;
import com.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper,role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<role> selectRole() {
        return roleMapper.selectList(null);
    }

    @Override
    public int selectRoleCount() {
        return Math.toIntExact(roleMapper.selectCount(null));
    }


    @Override
    public boolean saveBatch(Collection<role> entityList) {
        return super.saveBatch(entityList);
    }

}
