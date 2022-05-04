package com.example.ajaxtextupd;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "ImpServlet", value = "/ImpServlet")
public class ImpServlet extends HttpServlet {
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
                PreparedStatement preparedStatement1 = connection.prepareStatement("select * from content where title_id=?");
                preparedStatement1.setInt(1,Integer.parseInt(title_id));
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                while (resultSet1.next()){
                    PreparedStatement preparedStatement = connection.prepareStatement("update content set important=? where title_id=?");
                    if(resultSet1.getInt(7) == 0){
                        preparedStatement.setInt(1,1);
                    }
                    else{
                        preparedStatement.setInt(1,0);
                    }
                    preparedStatement.setInt(2,Integer.parseInt(title_id));
                    if(preparedStatement.executeUpdate()>0){
                        if( resultSet1.getInt(7) == 0)
                            out.print("*加精*标题："+resultSet1.getString(4));
                        else
                            out.print("标题："+resultSet1.getString(4));
                    }
                    preparedStatement.close();
                }
                preparedStatement1.close();
                connection.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
