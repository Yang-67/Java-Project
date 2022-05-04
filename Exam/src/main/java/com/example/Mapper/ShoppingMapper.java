package com.example.Mapper;

import com.example.pojo.Shopping;

import java.util.List;
import java.util.Map;

public interface ShoppingMapper {
//    查询商品
    List<Shopping> getShopping();
//    根据商品ID查信息
    List<Shopping> getById(int wareid);
}
