package com.example.ojtest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ojtest.entity.user;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<user> {
    @Insert("insert into title_info(title,content,solution) values(#{title},#{content},#{solution})")
    int insertTitleInfo(@Param("title") String title,@Param("content") String content,@Param("solution") String solution);
}
