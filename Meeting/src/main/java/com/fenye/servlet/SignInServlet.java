package com.fenye.servlet;

import com.alibaba.fastjson.JSON;
import com.fenye.JavaBean.MeetingRoom;
import com.fenye.dao.RoomDao;
import com.fenye.dao.SignInDao;
import com.fenye.entity.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SignInServlet", value = "/SignInServlet")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        int flag= Integer.parseInt(request.getParameter("flag"));
        SignInDao signInDao = new SignInDao();
        if(flag==1){
            List<Department> list=signInDao.searchdepartments();
//        request.getSession().setAttribute("list1",list);
            String json = JSON.toJSONString(list);
            System.out.println(json);
            writer.print(json);
        }
        if(flag==2){
            int i = signInDao.find_user_account(request.getParameter("user_account"));
            if(i==1){
                writer.print("该账户不可用");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        String user_name = request.getParameter("user_name");//姓名
        String user_pwd1 = request.getParameter("user_pwd1");//密码
        String user_account= request.getParameter("user_account");//账户名
        String user_phone = request.getParameter("user_phone");//联系电话
        String user_email = request.getParameter("user_email");//电子邮箱
        String department_user = request.getParameter("department_user");//部门名
        SignInDao signInDao = new SignInDao();
        boolean temp = signInDao.Sign_in(user_name,user_pwd1,user_account,user_phone,user_email,department_user);
        if(temp){
            out.println("注册成功!");
        }
        else {
            out.println("注册失败!");
        }
        System.out.println(user_name+" "+user_pwd1+" "+user_account+" "+user_phone+" "+user_email+" "+department_user);
    }
}
