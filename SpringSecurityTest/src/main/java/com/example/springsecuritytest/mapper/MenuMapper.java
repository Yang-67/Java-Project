package com.example.springsecuritytest.mapper;


import com.example.springsecuritytest.model.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> getAllMenus();
}
