package com.fenye.JavaBean;

import java.util.List;

public class PageBean_room {

    private int currentPage;
    private int pageSize;
    private int count;
    private int totalPage;

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

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<MeetingRoom> getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(List<MeetingRoom> meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    private List<MeetingRoom> meetingRoom;
}
