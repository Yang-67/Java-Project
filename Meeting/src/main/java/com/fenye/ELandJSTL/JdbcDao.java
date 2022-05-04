package com.fenye.ELandJSTL;




import com.fenye.JavaBean.MeetingRoom;
import com.fenye.JavaBean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDao {
    private static final String url = "jdbc:mysql://localhost:3306/meeting";
    private static final String user = "root";
    private static final String password = "1249";

    public List<User> findBookPage(int currentPage, int pageSize) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url,user,password);
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where user_state = 1 LIMIT ?,?");
        preparedStatement.setInt(1,(currentPage-1)*pageSize);
        preparedStatement.setInt(2,pageSize);
        ResultSet rs = preparedStatement.executeQuery();
        List<User> list = new ArrayList<>();
        while(rs.next())
        {
            User user = new User();
            user.setUser_name(rs.getString("user_name"));
            user.setUser_account(rs.getString("user_account"));
            user.setUser_phone(rs.getString("user_phone"));
            user.setUser_email(rs.getString("user_email"));
            list.add(user);
        }
        rs.close();
        preparedStatement.close();
        connection.close();
        return list;
    }

    public int count()
    {
        ResultSet rs = null;
        int num = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            String sql = "select count(*) from users WHERE user_state = 1";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            rs=preparedStatement.executeQuery();
            if(rs.next())
            {
                num=rs.getInt(1);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return num;
    }

    public List<User> SearchUsers(int currentPage, int pageSize, String user_name, String user_account, int user_state) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url,user,password);
        String sql = null;

        System.out.println(user_name);
        System.out.println(user_account);

        PreparedStatement preparedStatement = null;
        if(user_name != null && user_account!=null && !user_name.isEmpty() && !user_account.isEmpty())
        {
            sql = "select * from users where user_name = ? and user_account = ? and user_state = ? LIMIT ?,?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user_name);
            preparedStatement.setString(2,user_account);
            preparedStatement.setInt(3,user_state);
            preparedStatement.setInt(4,(currentPage-1)*pageSize);
            preparedStatement.setInt(5,pageSize);
        }
        else if(user_account!=null && !user_account.isEmpty())
        {
            System.out.println("useraccount不为空");
            sql = "select * from users where user_account = ? and user_state=? LIMIT ?,?";
            System.out.println(1);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user_account);
            System.out.println(2);
            preparedStatement.setInt(2,user_state);
            preparedStatement.setInt(3,(currentPage-1)*pageSize);
            System.out.println(3);
            preparedStatement.setInt(4,pageSize);
        }
        else if (user_name != null && !user_name.isEmpty())
        {
            System.out.println("username不为空");
            sql = "select * from users where user_name = ? and user_state = ? LIMIT ?,?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user_name);
            preparedStatement.setInt(2,user_state);
            preparedStatement.setInt(3,(currentPage-1)*pageSize);
            preparedStatement.setInt(4,pageSize);
        }
        else
        {
            System.out.println("两个都为空");
            return null;
        }
//        System.out.println(sql);

        ResultSet rs = preparedStatement.executeQuery();
        List<User> list = new ArrayList<>();
        while(rs.next())
        {
            User user = new User();
            user.setUser_name(rs.getString("user_name"));
            user.setUser_account(rs.getString("user_account"));
            user.setUser_phone(rs.getString("user_phone"));
            user.setUser_email(rs.getString("user_email"));
            list.add(user);
        }
        rs.close();
        preparedStatement.close();
        connection.close();
        return list;
    }

    public List<MeetingRoom> ListAllMeetingRooms(int currentPage, int pageSize) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url,user,password);
        PreparedStatement preparedStatement = connection.prepareStatement("select * from meeting_room LIMIT ?,?");
        preparedStatement.setInt(1,(currentPage-1)*pageSize);
        preparedStatement.setInt(2,pageSize);
        ResultSet rs = preparedStatement.executeQuery();
        List<MeetingRoom> list = new ArrayList<>();
        while(rs.next())
        {
            MeetingRoom room = new MeetingRoom();
            room.setRoom_id(rs.getInt("room_id"));
            room.setRoom_name(rs.getString("room_name"));
            room.setRoom_count(rs.getInt("room_count"));
            room.setRoom_state(rs.getInt("room_state"));
            room.setRoom_declare(rs.getString("room_declare"));
            list.add(room);
        }
        rs.close();
        preparedStatement.close();
        connection.close();
        return list;
    }

    public List<MeetingRoom> ListRoomDetail(int currentPage, int pageSize, int room_id) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url,user,password);
        PreparedStatement preparedStatement = connection.prepareStatement("select * from meeting_room where room_id = ? ");
//        preparedStatement.setInt(1,(currentPage-1)*pageSize);
//        preparedStatement.setInt(2,pageSize);
        preparedStatement.setInt(1,room_id);
        ResultSet rs = preparedStatement.executeQuery();
        List<MeetingRoom> list = new ArrayList<>();
        while(rs.next())
        {
            MeetingRoom room = new MeetingRoom();
            room.setRoom_id(rs.getInt("room_id"));
            room.setRoom_name(rs.getString("room_name"));
            room.setRoom_count(rs.getInt("room_count"));
            room.setRoom_state(rs.getInt("room_state"));
            room.setRoom_declare(rs.getString("room_declare"));
            list.add(room);
        }
        rs.close();
        preparedStatement.close();
        connection.close();
        return list;
    }
}
