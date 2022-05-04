package com.fenye.dao;

import com.fenye.entity.Department;
import com.mysql.jdbc.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Department_addDao {
    public boolean add_Department(String departmentname){
        ResultSet rs = null;
        try {
            Connection connection = new DBClass().getConn();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into departments(department_name) values(?)");
            preparedStatement.setString(1,departmentname);
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

    public boolean edit_Department(String departmentname, int department_id) {
        Connection connection = null;
        try {
            connection = new DBClass().getConn();
            PreparedStatement preparedStatement = connection.prepareStatement("update departments set department_name=? where department_id=?");
            preparedStatement.setString(1, departmentname);
            preparedStatement.setInt(2, department_id);
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

    public boolean delete_Department(int departmentid) {
        Connection connection = null;

        try {
            connection = new DBClass().getConn();
            PreparedStatement preparedStatement2 = connection.prepareStatement("delete from users where department_id=?");
            preparedStatement2.setInt(1,departmentid);
            if(preparedStatement2.executeUpdate()>=0){
                PreparedStatement preparedStatement = connection.prepareStatement("delete from departments where department_id=?");
                preparedStatement.setInt(1,departmentid);
                if(preparedStatement.executeUpdate()>0){
                    preparedStatement.close();
                    return true;
                }
                else{
                    preparedStatement.close();
                    return false;
                }
            }
            preparedStatement2.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

}
