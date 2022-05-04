package com.fenye.servlet;

import com.fenye.dao.Meeting_infoDao;
import com.fenye.dao.UserDao;
import com.fenye.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_account = request.getParameter("user_account");
        String user_pwd = request.getParameter("user_pwd");
        UserDao ud = new UserDao();
        int user_id = ud.login(user_account, user_pwd);
        System.out.println(user_id);
        if (user_id != -1)
        {
            String user_name = ud.find_name(user_id);
            System.out.println(user_name);
            request.getSession().setAttribute("user_name",user_name);//登录者的姓名
            request.getSession().setAttribute("user_account",user_account);//登录者的账户名
            request.getSession().setAttribute("user_id",user_id);//登录者的ID
            Meeting_infoDao meeting_infoDao = new Meeting_infoDao();
            meeting_infoDao.setMeeting_subscriber(user_name);

//            request.setAttribute("user_account",user_account);
            response.sendRedirect("ControlServlet");
//            request.getRequestDispatcher("index.jsp?user_account="+user_account).forward(request,response);

        }
        else
        {
            response.sendRedirect("login.jsp");
//            request.getRequestDispatcher("login.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
