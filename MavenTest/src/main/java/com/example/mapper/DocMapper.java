package com.example.mapper;

import com.example.pojo.Doc;

import java.util.List;
import java.util.Map;

public interface DocMapper {
//    添加文件信息
    int addDoc(Doc doc);
//    查询可下载文件
    List<Doc> getDocById(int user_id);
    //查询页数
    int getDocCount(int count);
    //查询我的文件
    List<Doc> getMyDoc(Map<String, Integer> map);
}
