package com.example.Test;

import com.alibaba.fastjson.JSON;
import com.example.javaClass.Content;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Test02_addServlet", value = "/Test02_addServlet")
public class Test02_addServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "1249");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM stu_info ");
            Students students= null;
            List list = new ArrayList();
            while (resultSet.next()){
                students = new Students();
                students.setId(resultSet.getInt(1));
                students.setName(resultSet.getString(2));
                students.setSex(resultSet.getString(3));
                students.setAge(resultSet.getInt(4));
                students.setHight(resultSet.getDouble(5));
                students.setHight(resultSet.getDouble(6));
                list.add(students);

            }
            resultSet.close();
            statement.close();
            connection.close();
            String contentJSON = JSON.toJSONString(list);
            out.println(contentJSON);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
