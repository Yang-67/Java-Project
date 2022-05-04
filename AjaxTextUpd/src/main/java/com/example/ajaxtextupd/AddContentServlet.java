package com.example.ajaxtextupd;

import com.example.javaClass.Content;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddContentServlet", value = "/AddContentServlet")
public class AddContentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        if(request.getSession().getAttribute("user_name")!=null)
        {
            String title = request.getParameter("title");
            String content = request.getParameter("content");
//        System.out.println((Integer) request.getSession().getAttribute("user_id"));
//        System.out.println(title+content);
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/discuss", "root", "1249");
                PreparedStatement preparedStatement = connection.prepareStatement("insert into content(user_id,title,content,time) values(?,?,?,now())");
                preparedStatement.setInt(1,(Integer) request.getSession().getAttribute("user_id"));
                preparedStatement.setString(2,title);
                preparedStatement.setString(3,content);
                if(preparedStatement.executeUpdate()>0){
                    out.print("true");
                }
                preparedStatement.close();
                connection.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
