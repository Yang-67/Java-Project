package com.fenye.servlet;

import com.fenye.dao.MeetingDao;
import com.fenye.entity.PageBean;
import com.fenye.service.EmpService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AllServlet", value = "/AllServlet")
public class AllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        MeetingDao meetingDao = new MeetingDao();
        int userid= (int) request.getSession().getAttribute("user_id");
        String username = (String)request.getSession().getAttribute("user_name");
        meetingDao.setUser_id(userid);//登录者的id
        meetingDao.setUser_name(username);//登录者的姓名

        int pageSize = 3;
        int currentPage = 1;//当前页
        String currPage = request.getParameter("currentPage");
        if(currPage != null && !"".equals(currPage)){
            currentPage = Integer.parseInt(currPage);
        }
        EmpService es = new EmpService();
        PageBean pb3 = es.findAll(currentPage, pageSize);
        request.setAttribute("pb3",pb3);
        request.getRequestDispatcher("/all_meeting.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
