package com.example.ajaxtextupd;

import com.alibaba.fastjson.JSON;
import com.example.javaClass.Content;
import com.example.javaClass.Updata;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UpdataServlet", value = "/UpdataServlet")
public class UpdataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        if(request.getSession().getAttribute("user_name")!=null){
            String title_id = request.getParameter("title_id");
            String title = request.getParameter("title");
            String content = request.getParameter("content");
//        System.out.println(title_id);
//        System.out.println(title);
//        System.out.println(content);
            if(title.equals("") || content.equals("")){
                out.println("请输入完整内容");
            }else {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/discuss", "root", "1249");
                    PreparedStatement preparedStatement = connection.prepareStatement("update content set title=?,content=?,time=now() where title_id=?");
                    preparedStatement.setString(1,title);
                    preparedStatement.setString(2,content);
                    preparedStatement.setInt(3,Integer.parseInt(title_id));

                    if(preparedStatement.executeUpdate()>0){
                        out.print("编辑成功");
                    }
                    preparedStatement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }

        }

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
            PreparedStatement preparedStatement1 = connection.prepareStatement("select * from content where title_id=?");
            preparedStatement1.setInt(1,Integer.parseInt(title_id));
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            Updata updata = null;
            List list = new ArrayList();
            while (resultSet1.next()){
                updata = new Updata();
                updata.setTitle(resultSet1.getString(3));
                updata.setContent(resultSet1.getString(4));
                list.add(updata);
            }
            preparedStatement1.close();
            connection.close();
            String contentJSON = JSON.toJSONString(list);
            out.println(contentJSON);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
