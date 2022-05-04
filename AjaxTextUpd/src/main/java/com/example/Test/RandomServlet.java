package com.example.Test;

import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RandomServlet", value = "/RandomServlet")
public class RandomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        String number = request.getParameter("number");
        int num =(int) request.getSession().getAttribute("num");
        Random01 random01 = null;
        random01 = new Random01();
        if(Integer.parseInt(number) > num){
            random01.data[random01.count-1] =Integer.parseInt( number);
            out.println("猜数过大,数在1和"+number+"之间");
        }
        if(Integer.parseInt(number) < num){
            random01.data[random01.count-1] =Integer.parseInt( number);
            out.println("猜数过小,数在"+number+"和100之间");
        }
        if(Integer.parseInt(number) == num){
            random01.data[random01.count-1] =Integer.parseInt( number);
//            out.println("猜对了,共猜过"+random01.getCount()+"次");
            int n=1;
            out.println(n);
        }

    }
}
