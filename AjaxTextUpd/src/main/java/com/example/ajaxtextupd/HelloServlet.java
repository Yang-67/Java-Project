package com.example.ajaxtextupd;

import com.alibaba.fastjson.JSON;
import com.example.javaClass.Content;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        System.out.println((String) request.getSession().getAttribute("user_name"));
        System.out.println((Integer) request.getSession().getAttribute("user_id"));
        System.out.println((Integer) request.getSession().getAttribute("user_sf"));
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/discuss", "root", "1249");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from content ORDER BY top,time DESC");
            Content contents = null;
            List list = new ArrayList();
            while (resultSet.next()){
                PreparedStatement preparedStatement2 = connection.prepareStatement("select * from user where user_id=?");
                preparedStatement2.setInt(1, resultSet.getInt(1));
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                if(resultSet2.next()){
                    contents = new Content();
                    contents.setUsername(resultSet2.getString(2));
                    contents.setIdentity(resultSet2.getInt(4));
                    contents.setTitle(resultSet.getString(3));
                    contents.setContent(resultSet.getString(4));
                    contents.setAttribute(resultSet.getInt(5));
//                    contents.setTime(resultSet.getString(8));
                    list.add(contents);
                }
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/discuss", "root", "1249");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from content");
            Content contents = null;
            List list = new ArrayList();
            while (resultSet.next()){
                contents = new Content();
                contents.setTitle(resultSet.getString(3));
                contents.setContent(resultSet.getString(4));
                contents.setAttribute(resultSet.getInt(5));
                list.add(contents);
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

    public void destroy() {
    }
}