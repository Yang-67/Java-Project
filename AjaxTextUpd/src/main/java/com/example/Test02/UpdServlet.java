package com.example.Test02;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "UpdServlet", value = "/UpdServlet")
public class UpdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String id=request.getParameter("id");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","1249");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from stu_info where id=?");
            preparedStatement.setInt(1,Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                out.println("<h3>修改学生信息</h3>");
                out.println("<form action='Updata_doServlet' method='post'>");
                out.println("序号<input type='text' name='id' value=" + resultSet.getInt(1) + " readonly><br>");
                out.println("姓名<input type='text' name='name' value=" + resultSet.getString(2) + " ><br>");
                out.println("性别<input type='text' name='sex' value=" + resultSet.getString(3) + " ><br>");
                out.println("年龄<input type='text' name='age' value=" + resultSet.getInt(4) + " ><br>");
                out.println("身高<input type='text' name='weight' value=" + resultSet.getDouble(5) + " ><br>");
                out.println("体重<input type='text' name='hight' value=" + resultSet.getDouble(6) + " ><br>");
                out.println("<input type='submit' value='确认修改'>");
            }
            else{
                out.print("修改失败");
            }
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
