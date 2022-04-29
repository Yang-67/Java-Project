package com.example.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bean.commodityList;
import com.example.mapper.CommodityListMapper;
import com.example.service.CommodityListService;
import org.springframework.stereotype.Service;

@Service
public class CommodityListServiceImpl extends ServiceImpl<CommodityListMapper, commodityList> implements CommodityListService {
}
