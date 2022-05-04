package com.example.Test;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Test03Servlet", value = "/Test03Servlet")
public class Test03Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        String user_name = request.getParameter("user_name");
        if(user_name.equals("aaa@163.com")){
            out.println("邮箱不可用");
        }
        else{
            out.println("邮箱可用");
        }
    }
}
