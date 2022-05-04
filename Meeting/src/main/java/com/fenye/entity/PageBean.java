package com.fenye.entity;

import java.util.List;
import com.fenye.JavaBean.User;

public class PageBean {
    private int currentPage;//第几页
    private int pageSize;//一页几条
    private int count;//总记录数
    private int totalPages;//总页数
    private int totalPage;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    private List<MeetingOrder> emps;//当前页的数据集合

    private List<Meeting_Info_user> users;//会议人员

    private List<Department> departments;//部门信息
//------
    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    private List<User> user;
//------
    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Meeting_Info_user> getUsers() {
        return users;
    }

    public void setUsers(List<Meeting_Info_user> users) {
        this.users = users;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<MeetingOrder> getEmps() {
        return emps;
    }

    public void setEmps(List<MeetingOrder> emps) {
        this.emps = emps;
    }
}
