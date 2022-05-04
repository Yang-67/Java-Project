package com.fenye.servlet;

import com.fenye.dao.MeetingDao;
import com.fenye.entity.PageBean;
import com.fenye.service.EmpService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Search_MeetingServlet", value = "/Search_MeetingServlet")
public class Search_MeetingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        String meetingname = request.getParameter("meetingname");//会议名称
        String roomname = request.getParameter("roomname");//会议室名称
        String name = request.getParameter("name");//预定者姓名
        String reserve_date1 = request.getParameter("reserve_date1");//预定起始时间
        String reserve_date2 = request.getParameter("reserve_date2");//预定结束时间
        String start_date1 = request.getParameter("start_date1");//会议开始时间
        String start_date2 = request.getParameter("start_date2");//会议结束时间

        System.out.println(meetingname+roomname+name+reserve_date1+reserve_date2+start_date1+start_date2);

        if(meetingname!=null || roomname!=null || name!=null || reserve_date1!=null || reserve_date2!=null || start_date1!=null || start_date2!= null){
            int pageSize = 3;
            int currentPage = 1;//当前页
            String currPage = request.getParameter("currentPage");
            if(currPage != null && !"".equals(currPage)){
                currentPage = Integer.parseInt(currPage);
            }
            EmpService es = new EmpService();
            PageBean pb01 = es.findSearchMeeting(currentPage, pageSize, meetingname,roomname,name,reserve_date1,reserve_date2,start_date1,start_date2);
            request.getSession().setAttribute("pb01",pb01);
        }
        else {
            request.getRequestDispatcher("/search_meeting.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
