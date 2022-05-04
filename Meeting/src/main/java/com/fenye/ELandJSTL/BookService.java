package com.fenye.ELandJSTL;


import com.fenye.JavaBean.MeetingRoom;
import com.fenye.JavaBean.PageBean_room;
import com.fenye.JavaBean.User;
import com.fenye.entity.PageBean;

import java.sql.SQLException;
import java.util.List;

public class BookService {
    public PageBean findBooksPage(int currentPage, int pageSize)
    {
        try{
            JdbcDao jdbcDao = new JdbcDao();
            int count = jdbcDao.count();
            int totalPage = (int) Math.ceil(count*1.0/pageSize);
            List<User> users = jdbcDao.findBookPage(currentPage,pageSize);
            PageBean pb = new PageBean();
            pb.setUser(users);
            pb.setCount(count);
            pb.setCurrentPage(currentPage);
            pb.setPageSize(pageSize);
            pb.setTotalPage(totalPage);
            return pb;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PageBean SearchUsers(int currentPage, int pageSize, String user_name, String user_account, int user_state)
    {
        try{
            JdbcDao jdbcDao = new JdbcDao();
            int count = jdbcDao.count();
            int totalPage = (int) Math.ceil(count*1.0/pageSize);
            List<User> users = jdbcDao.SearchUsers(currentPage,pageSize,user_name,user_account,user_state);
            if (users == null)
            {
                return null;
            }
            PageBean pb = new PageBean();
            pb.setUser(users);
            pb.setCount(count);
            pb.setCurrentPage(currentPage);
            pb.setPageSize(pageSize);
            pb.setTotalPage(totalPage);
            return pb;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PageBean_room ListAllMeetingRoom(int currentPage, int pageSize)
    {
        try{
            JdbcDao jdbcDao = new JdbcDao();
            int count = jdbcDao.count();
            int totalPage = (int) Math.ceil(count*1.0/pageSize);
            List<MeetingRoom> meetingRoom = jdbcDao.ListAllMeetingRooms(currentPage,pageSize);
            PageBean_room pb1 = new PageBean_room();
            pb1.setMeetingRoom(meetingRoom);
            pb1.setCount(count);
            pb1.setCurrentPage(currentPage);
            pb1.setPageSize(pageSize);
            pb1.setTotalPage(totalPage);
            return pb1;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PageBean_room ListRoomDetail(int currentPage, int pageSize, int room_id)
    {
        try{
            JdbcDao jdbcDao = new JdbcDao();
            int count = jdbcDao.count();
            int totalPage = (int) Math.ceil(count*1.0/pageSize);
            List<MeetingRoom> meetingRoom = jdbcDao.ListRoomDetail(currentPage,pageSize,room_id);
            PageBean_room pb3 = new PageBean_room();
            pb3.setMeetingRoom(meetingRoom);
            pb3.setCount(count);
            pb3.setCurrentPage(currentPage);
            pb3.setPageSize(pageSize);
            pb3.setTotalPage(totalPage);
            return pb3;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
