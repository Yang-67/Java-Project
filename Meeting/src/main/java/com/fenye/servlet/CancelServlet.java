package com.fenye.servlet;

import com.fenye.dao.MeetingDao;
import com.fenye.entity.MeetingOrder;
import com.fenye.entity.PageBean;
import com.fenye.service.EmpService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CancelServlet", value = "/CancelServlet")
public class CancelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out =response.getWriter();
        int meeting_id =Integer.parseInt(request.getParameter("meeting_id"));
        request.setAttribute("meeting_id",meeting_id);
        int pageSize = 3;
        int currentPage = 1;//当前页
        String currPage = request.getParameter("currentPage");
        if(currPage != null && !"".equals(currPage)){
            currentPage = Integer.parseInt(currPage);
        }
        //------------------------------
        MeetingDao meetingDao = new MeetingDao();
        try {
            List<MeetingOrder> list =  meetingDao.findOne_meeting(meeting_id);
            request.setAttribute("meeting",list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        EmpService es = new EmpService();
        //分页查询，并返回PageBean对象
        PageBean pb44 = es.findOne_user(currentPage, pageSize, meeting_id);
        request.setAttribute("pb44",pb44);
        request.getRequestDispatcher("/cancel_meeting.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
