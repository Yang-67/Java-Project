package dao;

import beans.BookDetail;
import beans.User;

import java.util.List;

import beans.Admin;
import beans.College;
import beans.Major;

import java.util.List;
import java.util.Map;

public interface BasicMapper {
    //读取所有学生信息
    List<User> getAllStudent();
    //interface3.1
    List<College>getTotalCollege();
    //interface3.2
    List<Major>getTotalMajor();
}
