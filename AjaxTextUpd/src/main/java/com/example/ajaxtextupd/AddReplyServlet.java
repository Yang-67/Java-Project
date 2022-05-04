package com.example.ajaxtextupd;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "AddReplyServlet", value = "/AddReplyServlet")
public class AddReplyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        if(request.getSession().getAttribute("user_name")!=null){
            String title_id = request.getParameter("title_id");
            String rcontent = request.getParameter("rcontent");
            System.out.println(title_id+rcontent);
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/discuss", "root", "1249");
                PreparedStatement preparedStatement = connection.prepareStatement("insert into reply(user_id,title_id,rcontent,rtime) values(?,?,?,now())");
                preparedStatement.setInt(1,(Integer) request.getSession().getAttribute("user_id"));
                preparedStatement.setString(2,title_id);
                preparedStatement.setString(3,rcontent);
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
