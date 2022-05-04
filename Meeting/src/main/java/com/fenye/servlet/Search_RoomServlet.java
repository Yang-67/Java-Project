package com.fenye.servlet;

import com.alibaba.fastjson.JSON;
import com.fenye.JavaBean.MeetingRoom;
import com.fenye.dao.RoomDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Search_RoomServlet", value = "/Search_RoomServlet")
public class Search_RoomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        String starttime=request.getParameter("starttime");
        String endtime=request.getParameter("endtime");
        System.out.println(starttime+"----"+endtime);
        RoomDao roomDao = new RoomDao();
        List<MeetingRoom> list=roomDao.searchRoom(starttime, endtime);
//        request.getSession().setAttribute("list1",list);
        String json = JSON.toJSONString(list);
        System.out.println(json);
        writer.print(json);
    }
}
