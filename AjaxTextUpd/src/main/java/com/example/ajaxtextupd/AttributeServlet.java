package com.example.ajaxtextupd;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "AttributeServlet", value = "/AttributeServlet")
public class AttributeServlet extends HttpServlet {
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
            int user_id = (Integer) request.getSession().getAttribute("user_id");
            System.out.println(title_id+""+user_id);

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/discuss", "root", "1249");
                //---
                //查出当前title_id的点赞数
                PreparedStatement preparedStatement = connection.prepareStatement("select * from content where title_id=?");
                preparedStatement.setInt(1, Integer.parseInt(title_id));
                ResultSet resultSet1 = preparedStatement.executeQuery();
                while (resultSet1.next()) {

                    PreparedStatement preparedStatement1 = connection.prepareStatement("select * from is_attribute where user_id=? and title_id=?");
                    preparedStatement1.setInt(1, user_id);
                    preparedStatement1.setInt(2, Integer.parseInt(title_id));
                    ResultSet resultSet2 = preparedStatement1.executeQuery();

                    if(resultSet2.next()){
                        PreparedStatement preparedStatement4 = connection.prepareStatement("delete from is_attribute where user_id=? and title_id=?");
                        preparedStatement4.setInt(1,user_id);
                        preparedStatement4.setInt(2,Integer.parseInt(title_id));
                        if(preparedStatement4.executeUpdate()>0){
                            int attribute;
//                        attribute =resultSet1.getInt(5) + 1;
                            attribute = resultSet1.getInt(5) - 1;
                            PreparedStatement preparedStatement2 = connection.prepareStatement("update content set attribute =? where title_id=?");
                            preparedStatement2.setInt(1,attribute);
                            preparedStatement2.setInt(2,Integer.parseInt(title_id));
                            if(preparedStatement2.executeUpdate()>0){
                                out.print("赞"+attribute);
                            }
                            preparedStatement2.close();
                        }
                        preparedStatement4.close();
                    }
                    else {
                        //插入
                        PreparedStatement preparedStatement2 = connection.prepareStatement("insert into is_attribute(user_id,title_id) values(?,?)");
                        preparedStatement2.setInt(1,user_id);
                        preparedStatement2.setInt(2,Integer.parseInt(title_id));
                        if(preparedStatement2.executeUpdate()>0){
//                            response.sendRedirect("ListAllServlet");
                            int attribute;
                            attribute =resultSet1.getInt(5) + 1;
//                            attribute = resultSet1.getInt(5) - 1;
                            PreparedStatement preparedStatement3 = connection.prepareStatement("update content set attribute =? where title_id=?");
                            preparedStatement3.setInt(1,attribute);
                            preparedStatement3.setInt(2,Integer.parseInt(title_id));
                            if(preparedStatement3.executeUpdate()>0){
                                out.print("赞"+attribute);
                            }
                            preparedStatement2.close();

                        }
                        preparedStatement.close();
                        connection.close();
                    }

                    preparedStatement.close();
                    connection.close();
                }

                //----
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
