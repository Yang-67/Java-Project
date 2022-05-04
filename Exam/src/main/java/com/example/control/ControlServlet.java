package com.example.control;

import com.example.Mapper.ShoppingMapper;
import com.example.pojo.Shopping;
import com.example.utils.OurUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ControlServlet", value = "/ControlServlet")
public class ControlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        SqlSession session = OurUtils.getSqlSession();
        ShoppingMapper shopping = session.getMapper(ShoppingMapper.class);
        int flag = Integer.parseInt(request.getParameter("flag"));
        if(flag==1){
            List<Shopping> shoppings = shopping.getShopping();
            request.setAttribute("shopping",shoppings);
            request.getRequestDispatcher("/shopping.jsp").forward(request,response);
        }
        if(flag==2){
            int wareid = Integer.parseInt(request.getParameter("wareid"));
            List<Shopping> oneshopping = shopping.getById(wareid);
            request.setAttribute("oneshopping",oneshopping);
            request.getRequestDispatcher("/Oneshop.jsp").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
