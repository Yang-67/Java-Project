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

@WebServlet(name = "DepartmentServlet", value = "/DepartmentServlet")
public class DepartmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        int pageSize = 3;
        int currentPage = 1;//当前页
        String currPage = request.getParameter("currentPage");
        if(currPage != null && !"".equals(currPage)){
            currentPage = Integer.parseInt(currPage);
        }
        EmpService es = new EmpService();
        PageBean pb5 = es.findDepartment(currentPage, pageSize);
        request.setAttribute("pb5",pb5);
        request.getRequestDispatcher("/department.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //部门的添加
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        int flag = Integer.parseInt(request.getParameter("flag"));
        String departmentname = request.getParameter("departmentname");
        Department_addDao dao = new Department_addDao();
        if(flag==1){
            if(dao.add_Department(departmentname)){
                out.println("添加成功");
            }
            else{
                out.println("添加失败");
            }
        }
        if(flag==2){
            int departmentid= Integer.parseInt(request.getParameter("departmentid"));
            if(dao.edit_Department(departmentname,departmentid)){
                out.println("修改成功");
            }
            else{
                out.println("修改失败");
            }
        }
        if(flag==3){
            int departmentid= Integer.parseInt(request.getParameter("departmentid"));
            if(dao.delete_Department(departmentid)){
                out.println("删除成功");
            }
            else{
                out.println("删除失败");
            }
        }
    }
}
