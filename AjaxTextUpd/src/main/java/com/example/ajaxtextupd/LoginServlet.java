package com.example.ajaxtextupd;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        String user_name = request.getParameter("user_name");
        String user_pwd = request.getParameter("user_pwd");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/discuss","root","1249");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where username=? and password=?");
            preparedStatement.setString(1,user_name);
            preparedStatement.setString(2,user_pwd);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                request.getSession().setAttribute("user_id",resultSet.getInt(1));
                request.getSession().setAttribute("user_name",resultSet.getString(2));
                request.getSession().setAttribute("user_sf",resultSet.getInt(4));
                out.print("true");
            }
            else {
                out.print("用户名或密码错误");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


//        if(!user_name.equals("123")){
//            out.print("用户不存在");
//        }
//        else {
//            out.print("欢迎登录");
//        }
    }
}
