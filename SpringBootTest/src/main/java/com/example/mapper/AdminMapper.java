package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bean.admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper extends BaseMapper<admin> {

    //查询表admin全部信息
//    List<admin> selectList();
//    T selectById(Serializable id);


}

