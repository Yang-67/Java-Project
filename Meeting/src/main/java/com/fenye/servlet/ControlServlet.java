package com.fenye.servlet;



import com.fenye.dao.MeetingDao;
import com.fenye.entity.PageBean;
import com.fenye.service.EmpService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ControlServlet", value = "/ControlServlet")
public class ControlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        MeetingDao meetingDao = new MeetingDao();
        int userid= (int) request.getSession().getAttribute("user_id");
        String username = (String)request.getSession().getAttribute("user_name");
        System.out.println(userid+username);

        meetingDao.setUser_id(userid);//登录者的id
        meetingDao.setUser_name(username);//登录者的姓名
        System.out.println(new MeetingDao().getUser_id()+"---");
        //初始化每页显示的记录数
        int pageSize = 3;
        int currentPage1 = 1;//当前页
        int currentPage2 = 1;//当前页
        String currPage1 = request.getParameter("currentPage1");
        String currPage2 = request.getParameter("currentPage2");
        if(currPage1 != null && !"".equals(currPage1)){
            currentPage1 = Integer.parseInt(currPage1);
        }if(currPage2 != null && !"".equals(currPage2)){
            currentPage2 = Integer.parseInt(currPage2);
        }
        //------------------------------
        EmpService es = new EmpService();
        //未来七天参加的会议
        PageBean pb = es.find_Seven_Meetings_Page(currentPage1, pageSize);
        request.setAttribute("pb",pb);
        System.out.println(pb.toString());
        //取消的会议
        PageBean pb1 = es.findDelete(currentPage2, pageSize);
        request.setAttribute("pb1",pb1);
        System.out.println(pb1.toString());
        //------------------------------
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        int flag = Integer.parseInt(request.getParameter("flag"));//判断功能
        switch(flag){
            case 1 ://撤销的会议
            {
                break;
            }
            case 2 ://我预约的会议
                System.out.println("okk");
                break;
            case 3 ://

                break;
            default :
                break;


        }
    }
}
