package com.example.ajaxtextupd;

import com.alibaba.fastjson.JSON;
import com.example.javaClass.Content;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ListAllServlet", value = "/ListAllServlet")
public class ListAllServlet extends HttpServlet {
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
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/discuss", "root", "1249");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM content ORDER BY top DESC,time DESC");
            Content contents = null;
            List list = new ArrayList();
            while (resultSet.next()){
                PreparedStatement preparedStatement2 = connection.prepareStatement("select * from user where user_id=?");
                preparedStatement2.setInt(1, resultSet.getInt(1));
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                if(resultSet2.next()){
                    contents = new Content();
                    if (((Integer) request.getSession().getAttribute("user_id")) != null){
                        if( ((Integer) request.getSession().getAttribute("user_id")) == resultSet.getInt(1)){
                            contents.setUser_id(1);
                        }
                        contents.setIdentity(((Integer) request.getSession().getAttribute("user_sf")));
                        contents.setName(((String) request.getSession().getAttribute("user_name")));
                    }
                    contents.setUsername(resultSet2.getString(2));

                    contents.setContent_id(resultSet.getInt(2));
                    contents.setTitle(resultSet.getString(3));
                    contents.setContent(resultSet.getString(4));
                    contents.setAttribute(resultSet.getInt(5));
                    contents.setTop(resultSet.getInt(6));
                    contents.setImp(resultSet.getInt(7));
//                    str.substring(0, 3)
                    contents.setTime((resultSet.getString(8)).substring(0, 16));
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
}
