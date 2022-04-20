package com.example.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bean.admin;
import com.example.mapper.AdminMapper;
import com.example.service.AdminService;
import com.example.utils.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AdminServiceImpl implements AdminService {

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
    public int insertAdmin(String dto) {
        JSONObject jsonObject = JSONObject.parseObject(dto);
        JSONObject admin1 = jsonObject.getJSONObject("data");
        admin adm = admin1.toJavaObject(admin.class);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        adm.setAdminId(""+adm.getAdminIdentity()+adm.getAdminSex()+sdf.format(date));
        adm.setAdminPwd(adm.getAdminPhone());
        return adminBean.insert(adm);
    }

    @Override
    public int updateAdmin(String dto) {
        JSONObject jsonObject = JSONObject.parseObject(dto);
        JSONObject admin1 = jsonObject.getJSONObject("data");
        admin adm = admin1.toJavaObject(admin.class);
        System.out.println(adm.toString());
        return adminBean.updateById(adm);
    }
}
