package com.example.Test02;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "Updata_doServlet", value = "/Updata_doServlet")
public class Updata_doServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        int age = Integer.parseInt(request.getParameter("age"));
        Double weight = Double.parseDouble(request.getParameter("weight"));
        Double hight = Double.parseDouble(request.getParameter("hight"));
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","1249");
            PreparedStatement preparedStatement = connection.prepareStatement("update stu_info set name=?, sex=?, age=?, weight=?,hight=? where id=?");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,sex);
            preparedStatement.setInt(3,age);
            preparedStatement.setDouble(4,weight);
            preparedStatement.setDouble(5,hight);
            preparedStatement.setInt(6,Integer.parseInt(id));
            if(preparedStatement.executeUpdate()>0){
                response.sendRedirect("ListAll03Servlet");

            }
            else{
                out.print("修改商品失败");
            }
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
