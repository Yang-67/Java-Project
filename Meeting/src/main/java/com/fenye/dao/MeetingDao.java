package com.fenye.dao;

import com.fenye.entity.Department;
import com.fenye.entity.MeetingOrder;
import com.fenye.entity.Meeting_Info_user;
import com.mysql.jdbc.ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class MeetingDao {
    private static int user_id;
    private static String user_name;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public static String getUser_name() {
        return user_name;
    }

    public static void setUser_name(String user_name) {
        MeetingDao.user_name = user_name;
    }

    //获取未来7天的会议
    public List<MeetingOrder> find_Seven_Meetings(int currentPage, int pageSize) throws Exception{
        ResultSet rs = null;
        List<MeetingOrder> list = new ArrayList<MeetingOrder>();
        try{
            Connection connection = new DBClass().getConn();
            PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM meeting_order WHERE DATEDIFF(meeting_start,NOW())<=7 AND meeting_start>= NOW() AND meeting_state=1 AND meeting_id in(" +
                    "SELECT meeting_id FROM meeting_info WHERE user_id="+user_id+")  ORDER BY meeting_start  limit ?,?");
            preparedstatement.setInt(1,(currentPage - 1)*pageSize);
            preparedstatement.setInt(2,pageSize);
            rs = (ResultSet) preparedstatement.executeQuery();
            while(rs.next()) {
                MeetingOrder meetingOrder = new MeetingOrder();
                meetingOrder.setMeeting_id(rs.getInt("meeting_id"));
                meetingOrder.setRoom_name(rs.getString("room_name"));
                meetingOrder.setMeeting_name(rs.getString("meeting_name"));
                meetingOrder.setMeeting_start(rs.getString("meeting_start").substring(0, 16));
                meetingOrder.setMeeting_end(rs.getString("meeting_end").substring(0, 16));
                list.add(meetingOrder);
            }
            connection.close();
            preparedstatement.close();
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //获取未来七天会议记录数
    public int count_Seven_Meetings() {
        String sql = "SELECT count(*) FROM meeting_order WHERE DATEDIFF(meeting_start,NOW())<=7 AND meeting_state=1 AND meeting_start>= NOW()" +
                " AND meeting_id in(SELECT meeting_id FROM meeting_info WHERE user_id="+user_id+")  ORDER BY meeting_start ";
        try {
            return Count_num(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //获取取消的会议记录
    public List<MeetingOrder> find_Delete_Meetings(int currentPage, int pageSize) throws Exception{
        ResultSet rs = null;
        List<MeetingOrder> list = new ArrayList<MeetingOrder>();
        try{
            Connection connection = new DBClass().getConn();
            PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM meeting_order WHERE meeting_start>= NOW() AND meeting_state=0 AND meeting_id in(" +
                    "SELECT meeting_id FROM meeting_info WHERE user_id="+user_id+")  ORDER BY meeting_start  limit ?,?");
            preparedstatement.setInt(1,(currentPage - 1)*pageSize);
            preparedstatement.setInt(2,pageSize);
            rs = (ResultSet) preparedstatement.executeQuery();
            while(rs.next()) {
                MeetingOrder meetingOrder = new MeetingOrder();
                meetingOrder.setMeeting_id(rs.getInt("meeting_id"));
                meetingOrder.setRoom_name(rs.getString("room_name"));
                meetingOrder.setMeeting_name(rs.getString("meeting_name"));
                meetingOrder.setMeeting_start(rs.getString("meeting_start").substring(0, 16));
                meetingOrder.setMeeting_end(rs.getString("meeting_end").substring(0, 16));
                meetingOrder.setMeeting_cancel_res(rs.getString("meeting_cancel_res"));
                list.add(meetingOrder);
            }
            connection.close();
            preparedstatement.close();
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    //获取取消会议的记录数
    public int count_Delete_Meetings(){
        String sql = "SELECT count(*) FROM meeting_order WHERE meeting_state=0 AND meeting_start>= NOW()" +
                " AND meeting_id in(SELECT meeting_id FROM meeting_info WHERE user_id="+user_id+")  ORDER BY meeting_start ";
        try {
            return Count_num(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //获取预定的会议记录
    public List<MeetingOrder> find_Reserve_Meeting(int currentPage, int pageSize) {
        ResultSet rs = null;
        List<MeetingOrder> list = new ArrayList<MeetingOrder>();
        try{
            Connection connection = new DBClass().getConn();
            PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM meeting_order " +
                    "WHERE meeting_start>= NOW()AND meeting_state=1 AND meeting_subscriber='"+user_name+"' ORDER BY meeting_start  limit ?,?");
            preparedstatement.setInt(1,(currentPage - 1)*pageSize);
            preparedstatement.setInt(2,pageSize);
            rs = (ResultSet) preparedstatement.executeQuery();
            while(rs.next()) {
                MeetingOrder meetingOrder = new MeetingOrder();
                meetingOrder.setMeeting_id(rs.getInt("meeting_id"));
                meetingOrder.setMeeting_name(rs.getString("meeting_name"));
                meetingOrder.setRoom_name(rs.getString("room_name"));
                meetingOrder.setMeeting_start(rs.getString("meeting_start").substring(0, 16));
                meetingOrder.setMeeting_end(rs.getString("meeting_end").substring(0, 16));
                meetingOrder.setMeeting_order_time(rs.getString("meeting_order_time").substring(0, 16));
                list.add(meetingOrder);
            }
            connection.close();
            preparedstatement.close();
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    //获取预定的会议数
    public int count_Reserve_Meeting() {
        String sql = "SELECT count(*) FROM meeting_order " +
                "WHERE meeting_start>= NOW() AND meeting_state=1 AND meeting_subscriber='"+user_name+"' ORDER BY meeting_start";
        try {
            return Count_num(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //获取我的会议记录
    public List<MeetingOrder> find_All_Meeting(int currentPage, int pageSize) {
        ResultSet rs = null;
        List<MeetingOrder> list = new ArrayList<MeetingOrder>();
        try{
            Connection connection = new DBClass().getConn();
            PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM meeting_order WHERE meeting_state=1 AND meeting_start>= NOW() AND meeting_id in(" +
                    "SELECT meeting_id FROM meeting_info WHERE user_id="+user_id+")  ORDER BY meeting_start  limit ?,?");
            preparedstatement.setInt(1,(currentPage - 1)*pageSize);
            preparedstatement.setInt(2,pageSize);
            rs = (ResultSet) preparedstatement.executeQuery();
            while(rs.next()) {
                MeetingOrder meetingOrder = new MeetingOrder();
                meetingOrder.setMeeting_id(rs.getInt("meeting_id"));
                meetingOrder.setRoom_name(rs.getString("room_name"));
                meetingOrder.setMeeting_name(rs.getString("meeting_name"));
                meetingOrder.setMeeting_subscriber(rs.getString("meeting_subscriber"));
                meetingOrder.setMeeting_start(rs.getString("meeting_start").substring(0, 16));
                meetingOrder.setMeeting_end(rs.getString("meeting_end").substring(0, 16));
                meetingOrder.setMeeting_order_time(rs.getString("meeting_order_time").substring(0, 16));
                list.add(meetingOrder);
            }
            connection.close();
            preparedstatement.close();
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    //获取我的会议的记录数
    public int count_All_Meeting() {
        String sql = "SELECT count(*) FROM meeting_order WHERE meeting_state=1 AND meeting_start>= NOW() AND meeting_id in(" +
                "SELECT meeting_id FROM meeting_info WHERE user_id="+user_id+")  ORDER BY meeting_start";
        try {
            return Count_num(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    //获取记录总数
    public int Count_num(String sql) throws SQLException {
        int num = 0;
        try {
            Connection connection = new DBClass().getConn();
            PreparedStatement preparedstatement = connection.prepareStatement(sql);
            ResultSet rs = (ResultSet) preparedstatement.executeQuery();
            if(rs.next()) {
                num = rs.getInt(1);
            }
            connection.close();
            preparedstatement.close();
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }


    //某一个会议的人员数
    public int count_One_Meeting_User(int meeting_id) {
        String sql = "SELECT count(*) FROM users WHERE user_id IN (SELECT user_id FROM meeting_info WHERE meeting_id="+meeting_id+")";
        try {
            return Count_num(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //查询某个会议的参会人员信息记录
    public List<Meeting_Info_user> findOne_meeting_user(int currentPage, int pageSize, int meeting_id) {
        ResultSet rs = null;
        List<Meeting_Info_user> list = new ArrayList<Meeting_Info_user>();
        try{
            Connection connection = new DBClass().getConn();
            PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM users WHERE user_id IN (SELECT user_id FROM meeting_info WHERE meeting_id="+meeting_id+") limit ?,?");
            preparedstatement.setInt(1,(currentPage - 1)*pageSize);
            preparedstatement.setInt(2,pageSize);
            rs = (ResultSet) preparedstatement.executeQuery();
            while(rs.next()) {
                Meeting_Info_user meeting_info_user = new Meeting_Info_user();
                meeting_info_user.setUser_name(rs.getString("user_name"));
                meeting_info_user.setUser_phone(rs.getString("user_phone"));
                meeting_info_user.setUser_email(rs.getString("user_email"));
                list.add(meeting_info_user);
            }
            connection.close();
            preparedstatement.close();
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<MeetingOrder> findOne_meeting(int meeting_id) throws Exception{
        ResultSet rs = null;
        List<MeetingOrder> list = new ArrayList<MeetingOrder>();
        try{
            Connection connection = new DBClass().getConn();
            PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM meeting_order WHERE  meeting_id=?");
            preparedstatement.setInt(1,meeting_id);
            rs = (ResultSet) preparedstatement.executeQuery();
            while(rs.next()) {
                MeetingOrder meetingOrder = new MeetingOrder();
                meetingOrder.setRoom_name(rs.getString("room_name"));
                meetingOrder.setMeeting_name(rs.getString("meeting_name"));
                meetingOrder.setMeeting_start(rs.getString("meeting_start").substring(0, 16));
                meetingOrder.setMeeting_end(rs.getString("meeting_end").substring(0, 16));
                meetingOrder.setMeeting_count(rs.getInt("meeting_count"));
                meetingOrder.setMeeting_describe(rs.getString("meeting_describe"));
                meetingOrder.setMeeting_cancel_res(rs.getString("meeting_cancel_res"));
                list.add(meetingOrder);
            }
            connection.close();
            preparedstatement.close();
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //查询部门数量
    public int count_Department() {
        String sql = "SELECT count(*) FROM departments";
        try {
            return Count_num(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //查询各部门信息记录
    public List<Department> find_Department(int currentPage, int pageSize) {
        ResultSet rs = null;
        List<Department> list = new ArrayList<Department>();
        try{
            Connection connection = new DBClass().getConn();
            PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM departments limit ?,?");
            preparedstatement.setInt(1,(currentPage - 1)*pageSize);
            preparedstatement.setInt(2,pageSize);
            rs = (ResultSet) preparedstatement.executeQuery();
            while(rs.next()) {
                Department department = new Department();
                department.setDepartment_id(rs.getInt("department_id"));
                department.setDepartment_name(rs.getString("department_name"));
                list.add(department);
            }
            connection.close();
            preparedstatement.close();
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }


    //会议撤销实现
    public boolean Cencal_Meeting(int meeting_id,String cencal) {
        Connection connection = null;
        try {
            connection = new DBClass().getConn();
            PreparedStatement preparedStatement = connection.prepareStatement("update meeting_order set meeting_state=0,meeting_cancel_res=? where meeting_id=?");
            preparedStatement.setString(1, cencal);
            preparedStatement.setInt(2, meeting_id);
            if(preparedStatement.executeUpdate()>0){
                return true;
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    private static String sql_search="";
    //会议搜索
    public List<MeetingOrder> find_searchMeeting(int currentPage, int pageSize) {
//        System.out.println(meetingname+roomname+name+reserve_date1+reserve_date2+start_date1+start_date2);
        System.out.println(sql_search+"--");
        String sql="SELECT * "+sql_search+" limit "+(currentPage - 1)*pageSize+","+pageSize ;
        System.out.println(sql+"++");
        ResultSet rs = null;
        List<MeetingOrder> list = new ArrayList<MeetingOrder>();
        try{
            Connection connection = new DBClass().getConn();
            PreparedStatement preparedstatement = connection.prepareStatement(sql);
            rs = (ResultSet) preparedstatement.executeQuery();
            while(rs.next()) {
                MeetingOrder meetingOrder = new MeetingOrder();
                meetingOrder.setMeeting_id(rs.getInt("meeting_id"));
                meetingOrder.setRoom_name(rs.getString("room_name"));
                meetingOrder.setMeeting_name(rs.getString("meeting_name"));
                meetingOrder.setMeeting_subscriber(rs.getString("meeting_subscriber"));
                meetingOrder.setMeeting_start(rs.getString("meeting_start").substring(0, 16));
                meetingOrder.setMeeting_end(rs.getString("meeting_end").substring(0, 16));
                meetingOrder.setMeeting_order_time(rs.getString("meeting_order_time").substring(0, 16));
                list.add(meetingOrder);
            }
            connection.close();
            preparedstatement.close();
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public int count_Search_Meeting(String meetingname, String roomname, String name, String reserve_date1, String reserve_date2, String start_date1, String start_date2) {
        String meeting_name="",room_name="",meeting_subscriber="",date="",start_date11,start_date22;
        if(meetingname!="")
            meeting_name="and meeting_name like '%"+meetingname+"%' ";
        if(roomname!="")
            room_name="and room_name like '%"+roomname+"%' ";
        if(name!="")
            meeting_subscriber="and meeting_subscriber like '%"+name+"%' ";
        if(reserve_date1!="" && reserve_date2!="")
            date="and meeting_start  between '"+reserve_date1+"' and '"+reserve_date2+"'";
        sql_search="FROM meeting_order WHERE meeting_state=1 "+meeting_name+room_name+meeting_subscriber+date;
        String sql = "SELECT count(*)"+sql_search;
        try {
            return Count_num(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
