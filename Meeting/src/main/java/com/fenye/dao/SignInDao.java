package com.fenye.dao;

import com.fenye.JavaBean.MeetingRoom;
import com.fenye.entity.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class SignInDao {
    public boolean Sign_in(String user_name,String user_pwd1,String user_account,String user_phone,String user_email,String department_user){
        try {
            Connection connection = null;
            connection = new DBClass().getConn();
            Class.forName("com.mysql.jdbc.Driver");
            PreparedStatement preparedstatement1 = connection.prepareStatement("select * from departments where department_name='"+department_user+"'");
            ResultSet rs = preparedstatement1.executeQuery();
            if(rs.next()){
//                System.out.println(rs.getInt("department_id"));
                String sql1 = "insert into users(department_id,user_account,user_pwd,user_name,user_phone,user_email,user_identify,user_state) values(?,?,?,?,?,?,0,1)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql1);
                preparedStatement.setInt(1,rs.getInt("department_id"));
                preparedStatement.setString(2,user_account);
                preparedStatement.setString(3,user_pwd1);
                preparedStatement.setString(4,user_name);
                preparedStatement.setString(5,user_phone);
                preparedStatement.setString(6,user_email);
                if(preparedStatement.executeUpdate()>0){
                    return true;
                }
                preparedStatement.close();
            }
            preparedstatement1.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Department> searchdepartments() {
        List<Department> list = new ArrayList<Department>();
        Connection connection=null;
        Department department=null;
        try{
            connection = new DBClass().getConn();
            PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM departments");
            ResultSet rs = preparedstatement.executeQuery();
            ResultSet resultSet = preparedstatement.executeQuery();
            while(resultSet.next()) {
                department = new Department();
                department.setDepartment_name(resultSet.getString("department_name"));
                list.add(department);
            }
            connection.close();
            preparedstatement.close();
            rs.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int find_user_account(String user_account) {
        Connection connection=null;
        Department department=null;
        try{
            connection = new DBClass().getConn();
            PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM users where user_account="+user_account);
            ResultSet rs = preparedstatement.executeQuery();
            ResultSet resultSet = preparedstatement.executeQuery();
            while(resultSet.next()) {
                return 1;
            }
            connection.close();
            preparedstatement.close();
            rs.close();
        }catch(Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }
}
