package com.example.authenticationpermissions.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.authenticationpermissions.domain.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userid);
}
