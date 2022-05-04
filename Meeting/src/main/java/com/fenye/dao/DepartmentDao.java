package com.fenye.dao;



import com.fenye.entity.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {
    private static final String url = "jdbc:mysql://localhost:3306/meeting";
    private static final String user = "root";
    private static final String password = "1249";

    public List<Department> findDepartment() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM departments");
        ResultSet rs = preparedStatement.executeQuery();
        List<Department> list = new ArrayList<>();
        while(rs.next()) {
            Department department = new Department();
//            System.out.println(rs.getInt("department_id"));
            department.setDepartment_id(rs.getInt("department_id"));
            department.setDepartment_name(rs.getString("department_name"));
            list.add(department);
        }
        rs.close();
        preparedStatement.close();
        connection.close();
        return list;
    }
}
