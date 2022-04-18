package com.example.service;

import com.example.bean.admin;
import com.example.utils.PageResponse;

public interface AdminService {

    //根据id查密码
    admin getPwdById(String adminId);

    //    分页查询
    PageResponse<admin> getProductPage(Integer page, Integer count, String adminName,String adminAddress,String adminEmail);

    //根据id删除人员
    int deleteById(String userId);

    //新增人员
    int insertAdmin(String dto);
    //修改人员信息
    int updateAdmin(String dto);

}
