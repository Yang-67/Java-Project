package com.fenye.servlet;

import com.fenye.dao.Department_addDao;
import com.fenye.dao.MeetingDao;
import com.fenye.entity.PageBean;
import com.fenye.service.EmpService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ReserveServlet", value = "/ReserveServlet")
public class ReserveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        MeetingDao meetingDao = new MeetingDao();
        int userid= (int) request.getSession().getAttribute("user_id");
        String username = (String)request.getSession().getAttribute("user_name");
        meetingDao.setUser_id(userid);//登录者的id
        meetingDao.setUser_name(username);//登录者的姓名

        int pageSize = 3;//初始化每页显示的记录数
        int currentPage = 1;//当前页
        String currPage = request.getParameter("currentPage");
        if(currPage != null && !"".equals(currPage)){
            currentPage = Integer.parseInt(currPage);
        }
        EmpService es = new EmpService();
        PageBean pb2 = es.findReserve(currentPage, pageSize);
        request.setAttribute("pb2",pb2);
        request.getRequestDispatcher("/reserve_meeting.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        int meeting_id = Integer.parseInt(request.getParameter("meetingid"));
        String cencal = request.getParameter("cencal");
        MeetingDao dao = new MeetingDao();
        if(dao.Cencal_Meeting(meeting_id,cencal)){
            out.println("取消成功");
        }
        else{
            out.println("取消失败");
        }
    }
}
