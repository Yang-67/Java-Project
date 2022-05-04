package com.fenye.dao;



import com.fenye.JavaBean.Meeting;
import com.fenye.JavaBean.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class UserDao {
    public int login(String user_account, String user_pwd)
    {
        int flag = -1;
        Connection conn = null;
        try {
            conn = new DBClass().getConn();

            String sql = "select * from users where user_account = ? and user_pwd = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, user_account);
            preparedStatement.setString(2, user_pwd);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                flag=rs.getInt("user_id");
            }
            return flag;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }

    public String find_name(int user_id)
    {
        String user_name = null;
        Connection conn = null;
        try {
            conn = new DBClass().getConn();

            String sql = "select * from users where user_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, user_id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user_name=rs.getString("user_name");
            }
            return user_name;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return user_name;
    }




    public List<User> searchUser(String user_name, String user_account)
    {
//        System.out.println(user_account);

        List<User> list = new ArrayList<>();

        Connection conn = null;
        try {
            conn = new DBClass().getConn();

            User user = new User();

            String sql = "select * from users where user_name = ?";
            String sql1 = "select * from users where user_account=?";
            String sql2 = "select * from users where user_name=? and user_account = ?";
//姓名 账号名 联系电话 电子邮件
            PreparedStatement preparedStatement = null;

            if (!"".equals(user_name) && !"".equals(user_account))
            {
                preparedStatement = conn.prepareStatement(sql2);
                preparedStatement.setString(1, user_name);
                preparedStatement.setString(2, user_account);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    user.setUser_name(rs.getString("user_name"));
                    user.setUser_account(rs.getString("user_account"));
                    user.setUser_phone(rs.getString("user_phone"));
                    user.setUser_email(rs.getString("user_email"));
                    list.add(user);
                }

            } else if (!"".equals(user_account))
            {
                preparedStatement = conn.prepareStatement(sql1);
                preparedStatement.setString(1, user_account);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    user.setUser_name(rs.getString("user_name"));
                    user.setUser_account(rs.getString("user_account"));
                    user.setUser_phone(rs.getString("user_phone"));
                    user.setUser_email(rs.getString("user_email"));
                    list.add(user);
                }
            }
            else if (!"".equals(user_name))
            {
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, user_name);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    user.setUser_name(rs.getString("user_name"));
                    user.setUser_account(rs.getString("user_account"));
                    user.setUser_phone(rs.getString("user_phone"));
                    user.setUser_email(rs.getString("user_email"));
                    list.add(user);
                }
            }
            else
            {
                System.out.println("不可为空");
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    public boolean CloseUserAccount(String user_account)
    {
        boolean flag = false;
        Connection conn = null;
        try {
            conn = new DBClass().getConn();
            String sql = "select * from users where user_account = ?";
            String sql1 = "update users set user_state = 0 where user_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement.setString(1, user_account);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                preparedStatement1.setString(1, rs.getString("user_id"));
                int i = preparedStatement1.executeUpdate();
                if (i > 0)
                {
                    flag = true;
                    System.out.println("关闭成功");
                }
                else
                {
                    flag = false;
                }
            }
            return flag;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public boolean ChangeUserStateOpen(String user_account)
    {
        System.out.println(0);
        boolean flag = false;
        Connection conn = null;
        System.out.println(1);
        try {
            conn = new DBClass().getConn();
            String sql = "select * from users where user_account = ?";
            String sql1 = "update users set user_state = 2 where user_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement.setString(1, user_account);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                preparedStatement1.setString(1, rs.getString("user_id"));
                int i = preparedStatement1.executeUpdate();
                if (i > 0)
                {
                    flag = true;
                    System.out.println("通过成功");
                }
                else
                {
                    flag = false;
                }
            }
            return flag;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public boolean ChangeUserStateClose(String user_account)
    {

        boolean flag = false;
        Connection conn = null;

        try {
            conn = new DBClass().getConn();
            String sql = "select * from users where user_account = ?";
            String sql1 = "update users set user_state = 0 where user_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement.setString(1, user_account);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                preparedStatement1.setString(1, rs.getString("user_id"));
                int i = preparedStatement1.executeUpdate();
                if (i > 0)
                {
                    flag = true;
                    System.out.println("通过成功");
                }
                else
                {
                    flag = false;
                }
            }
            return flag;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getuserbydid(int did) throws Exception {
        Connection conn = null;
        conn = new DBClass().getConn();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from users where department_id = ? ");
        preparedStatement.setInt(1, did);
        ResultSet rs = preparedStatement.executeQuery();
        List<User> list = new ArrayList<>();
        while(rs.next()) {
            User user = new User();
            user.setUser_id(Integer.parseInt(rs.getString("user_id")));
            user.setUser_name(rs.getString("user_name"));
            list.add(user);
        }
        rs.close();
        preparedStatement.close();
        conn.close();
        return list;
    }

}
