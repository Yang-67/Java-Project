package com.example.pojo;

import java.util.List;

public class PageBean {
    private int currentPage;//第几页
    private int pageSize;//一页几条
    private int count;//总记录数
    private int totalPages;//总页数
    private List<Doc> emps;//当前页的数据集合

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

    public List<Doc> getEmps() {
        return emps;
    }

    public void setEmps(List<Doc> emps) {
        this.emps = emps;
    }
}
