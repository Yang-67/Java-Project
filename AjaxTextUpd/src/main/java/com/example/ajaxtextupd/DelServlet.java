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

@WebServlet(name = "DelServlet", value = "/DelServlet")
public class DelServlet extends HttpServlet {
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
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/discuss", "root", "1249");
                PreparedStatement preparedStatement3 = connection.prepareStatement("delete from is_attribute where title_id=?");
                preparedStatement3.setInt(1,Integer.parseInt(title_id));
                if(preparedStatement3.executeUpdate()>=0){
                    PreparedStatement preparedStatement2 = connection.prepareStatement("delete from reply where title_id=?");
                    preparedStatement2.setInt(1,Integer.parseInt(title_id));
                    if(preparedStatement2.executeUpdate()>=0){
                        PreparedStatement preparedStatement = connection.prepareStatement("delete from content where title_id=?");
                        preparedStatement.setInt(1,Integer.parseInt(title_id));
                        if(preparedStatement.executeUpdate()>0){
                            out.print("删除成功");
                        }
                        else{
                            out.print("删除失败");
                        }
                        preparedStatement.close();
                    }
                    preparedStatement2.close();
                }
                preparedStatement3.close();


                connection.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
