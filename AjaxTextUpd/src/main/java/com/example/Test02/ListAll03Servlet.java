package com.example.Test02;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "ListAll03Servlet", value = "/ListAll03Servlet")
public class ListAll03Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<h4>添加</h4>");
        out.print("<form action='AddStudentServlet' method='post'>");
        out.print("姓名<input type='text' name='name' ></br>");
        out.print("性别<input type='text' name='sex' ></br>");
        out.print("年龄<input type='text' name='age' ></br>");
        out.print("身高<input type='text' name='hight' ></br>");
        out.print("体重<input type='text' name='weight' ></br>");
        out.print("<input type='submit' value='添加'>");
        out.print("</form>");


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "1249");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from stu_info");
            out.print("<h3>学生信息</h3>");
            out.print("<table border=1>");
            out.print("<td>序号</td><td>姓名</td><td>性别</td><td>年龄</td><td>身高</td><td>体重</td>");
            while (resultSet.next()){
                out.print("<tr>");
                out.print("<td>"+resultSet.getInt(1)+"</td>");
                out.print("<td>"+resultSet.getString(2)+"</td>");
                out.print("<td>"+resultSet.getString(3)+"</td>");
                out.print("<td>"+resultSet.getInt(4)+"</td>");
                out.print("<td>"+resultSet.getDouble(5)+"</td>");
                out.print("<td>"+resultSet.getDouble(6)+"</td>");
                out.print("<td>"+"<a href='UpdServlet?id="+resultSet.getInt(1)+"'>修改</a>"+"</td>");
                out.print("<td>"+"<a href='DeleteServlet?id="+resultSet.getInt(1)+"'>删除</a>"+"</td>");
                out.print("</tr>");
            }
            out.print("</table>");
            statement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
