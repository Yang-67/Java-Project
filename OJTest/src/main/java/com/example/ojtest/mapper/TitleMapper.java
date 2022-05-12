package com.example.ojtest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ojtest.entity.titleInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TitleMapper extends BaseMapper<titleInfo> {
}
