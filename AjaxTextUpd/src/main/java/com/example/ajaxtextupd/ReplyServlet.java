package com.example.ajaxtextupd;

import com.alibaba.fastjson.JSON;
import com.example.javaClass.Content;
import com.example.javaClass.Reply;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ReplyServlet", value = "/ReplyServlet")
public class ReplyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        String title_id = request.getParameter("title_id");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/discuss", "root", "1249");
            PreparedStatement preparedStatement1 = connection.prepareStatement("select * from reply where title_id=? ORDER BY rtime DESC");
            preparedStatement1.setInt(1, Integer.parseInt(title_id));
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            Reply replys = null;
            List list = new ArrayList();
            while (resultSet1.next()) {
                PreparedStatement preparedStatement2 = connection.prepareStatement("select * from user where user_id=? ");
                preparedStatement2.setInt(1, resultSet1.getInt(1));
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                if (resultSet2.next()) {
                    replys = new Reply();
                    replys.setUser_id(resultSet1.getInt(1));
                    replys.setTitle_id(resultSet1.getInt(2));
                    replys.setReply_content(resultSet1.getString(4));
                    replys.setReply_rtime((resultSet1.getString(6)).substring(0, 16));
                    replys.setUser_name(resultSet2.getString(2));
                    list.add(replys);
                }
            }
            resultSet1.close();
            connection.close();
            String contentJSON = JSON.toJSONString(list);
            out.println(contentJSON);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
