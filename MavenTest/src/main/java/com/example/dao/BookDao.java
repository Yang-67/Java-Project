package com.example.dao;

import com.example.maventest.Book;

import java.util.List;

//操作表
public interface BookDao {
    //查询表中数据
    List<Book> selectBook();

    //根据Bno查书
    Book getBookById(String Bno);

    //insert
    int addBook(Book book);

    //修改
    int updateBook(Book book);

    //删除
    int removeBook(String Bno);
}
