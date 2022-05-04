package com.fenye.service;

import com.fenye.dao.MeetingDao;
import com.fenye.entity.Department;
import com.fenye.entity.MeetingOrder;
import com.fenye.entity.Meeting_Info_user;
import com.fenye.entity.PageBean;

import java.util.List;

public class EmpService {
    //未来七天参加的会议
    public PageBean find_Seven_Meetings_Page(int currentPage, int pageSize){
        try{
            MeetingDao meetingDao = new MeetingDao();
            int count = meetingDao.count_Seven_Meetings();
            System.out.println(count);
            int totalPage = (int) Math.ceil(count*1.0 / pageSize);
            List<MeetingOrder> emps = meetingDao.find_Seven_Meetings(currentPage,pageSize);
            PageBean pb = new PageBean();
            pb.setEmps(emps);
            pb.setCount(count);
            pb.setCurrentPage(currentPage);
            pb.setPageSize(pageSize);
            pb.setTotalPages(totalPage);
            return pb;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //取消的会议
    public PageBean findDelete(int currentPage, int pageSize){
        try{
            MeetingDao meetingDao = new MeetingDao();
            int count = meetingDao.count_Delete_Meetings();
            int totalPage = (int) Math.ceil(count*1.0 / pageSize);
            List<MeetingOrder> emps = meetingDao.find_Delete_Meetings(currentPage,pageSize);
            PageBean pb2 = new PageBean();
            pb2.setEmps(emps);
            pb2.setCount(count);
            pb2.setCurrentPage(currentPage);
            pb2.setPageSize(pageSize);
            pb2.setTotalPages(totalPage);
            return pb2;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //预定的会议
    public PageBean findReserve(int currentPage, int pageSize){
        try{
            MeetingDao meetingDao = new MeetingDao();
            int count = meetingDao.count_Reserve_Meeting();
            int totalPage = (int) Math.ceil(count*1.0 / pageSize);
            List<MeetingOrder> emps = meetingDao.find_Reserve_Meeting(currentPage,pageSize);
            PageBean pb1 = new PageBean();
            pb1.setEmps(emps);
            pb1.setCount(count);
            pb1.setCurrentPage(currentPage);
            pb1.setPageSize(pageSize);
            pb1.setTotalPages(totalPage);
            return pb1;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //我的会议
    public PageBean findAll(int currentPage, int pageSize){
        try{
            MeetingDao meetingDao = new MeetingDao();
            int count = meetingDao.count_All_Meeting();
            int totalPage = (int) Math.ceil(count*1.0 / pageSize);
            List<MeetingOrder> emps = meetingDao.find_All_Meeting(currentPage,pageSize);
            PageBean pb3 = new PageBean();
            pb3.setEmps(emps);
            pb3.setCount(count);
            pb3.setCurrentPage(currentPage);
            pb3.setPageSize(pageSize);
            pb3.setTotalPages(totalPage);
            return pb3;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //会议信息详情
    public PageBean findOne_user(int currentPage, int pageSize, int meeting_id) {
        try{
            MeetingDao meetingDao = new MeetingDao();
            int count = meetingDao.count_One_Meeting_User(meeting_id);
            int totalPage = (int) Math.ceil(count*1.0 / pageSize);
            List<Meeting_Info_user> users = meetingDao.findOne_meeting_user(currentPage,pageSize,meeting_id);
            PageBean pb4 = new PageBean();
            pb4.setUsers(users);
            pb4.setCount(count);
            pb4.setCurrentPage(currentPage);
            pb4.setPageSize(pageSize);
            pb4.setTotalPages(totalPage);
            return pb4;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //部门管理--查询部门信息
    public PageBean findDepartment(int currentPage, int pageSize){
        try{
            MeetingDao meetingDao = new MeetingDao();
            int count = meetingDao.count_Department();
            int totalPage = (int) Math.ceil(count*1.0 / pageSize);
            List<Department> department = meetingDao.find_Department(currentPage,pageSize);
            PageBean pb5 = new PageBean();
            pb5.setDepartments(department);
            pb5.setCount(count);
            pb5.setCurrentPage(currentPage);
            pb5.setPageSize(pageSize);
            pb5.setTotalPages(totalPage);
            return pb5;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //搜索会议
    public PageBean findSearchMeeting(int currentPage, int pageSize, String meetingname, String roomname, String name, String reserve_date1, String reserve_date2, String start_date1, String start_date2) {
        try{
            MeetingDao meetingDao = new MeetingDao();
            int count = meetingDao.count_Search_Meeting(meetingname,roomname,name,reserve_date1,reserve_date2,start_date1,start_date2);
            System.out.println(count);
            int totalPage = (int) Math.ceil(count*1.0 / pageSize);
            List<MeetingOrder> emps = meetingDao.find_searchMeeting(currentPage,pageSize);
            PageBean pb01 = new PageBean();
            pb01.setEmps(emps);
            pb01.setCount(count);
            pb01.setCurrentPage(currentPage);
            pb01.setPageSize(pageSize);
            pb01.setTotalPages(totalPage);
            return pb01;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
