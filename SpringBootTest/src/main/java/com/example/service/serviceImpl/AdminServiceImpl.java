package com.example.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bean.admin;
import com.example.common.Constants;
import com.example.controller.entity.adminDto;
import com.example.exception.ServiceException;
import com.example.mapper.AdminMapper;
import com.example.service.AdminService;
import com.example.utils.PageResponse;
import com.example.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, admin> implements AdminService {

    @Autowired
    private AdminMapper adminBean;

    @Override
    public admin getPwdById(String adminId) {
        return (admin) adminBean.selectById(adminId);
    }

    @Override
    public PageResponse<admin> getProductPage(Integer page, Integer count, String adminName, String adminAddress,String adminEmail) {
        Page<admin> pager = new Page<>(page, count);
        QueryWrapper<admin> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(adminName))
            query.like("admin_name",adminName);
        if(StringUtils.isNotEmpty(adminAddress))
            query.like("admin_address",adminAddress);
        if(StringUtils.isNotEmpty(adminEmail))
            query.like("admin_email",adminEmail);
        IPage<admin> paging = adminBean.selectPage(pager,query);
        return new PageResponse(paging.getTotal(), paging.getRecords(), paging.getCurrent(), paging.getSize());
    }

    @Override
    public int deleteById(String adminId) {
        return adminBean.deleteById(adminId);
    }
    
    @Override
    public int insertAdmin(admin dto) {
//        JSONObject jsonObject = JSONObject.parseObject(dto);
//        JSONObject admin1 = jsonObject.getJSONObject("data");
//        admin adm = admin1.toJavaObject(admin.class);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        dto.setAdminId(""+dto.getAdminIdentity()+dto.getAdminSex()+sdf.format(date));
        dto.setAdminPwd(dto.getAdminPhone());
        return adminBean.insert(dto);
    }

    @Override
    public int updateAdmin(admin dto) {
        System.out.println(dto.toString());
        return adminBean.updateById(dto);
    }

//    登录
    @Override
    public adminDto login(admin dto){
        QueryWrapper<admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_id",dto.getAdminId());
        queryWrapper.eq("admin_pwd",dto.getAdminPwd());
        admin one;
        try{
            one=getOne(queryWrapper);
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if(one!=null){
            BeanUtil.copyProperties(one,dto,true);
            String token = TokenUtils.genToken(one.getAdminId(),one.getAdminPwd());
            adminDto adDto = new adminDto(dto.getAdminName(),token);
            BeanUtil.copyProperties(one,adDto,true);
            return adDto;
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }
}
