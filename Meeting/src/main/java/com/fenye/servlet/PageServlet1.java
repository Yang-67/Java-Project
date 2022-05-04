package com.fenye.servlet;



import com.fenye.ELandJSTL.BookService;
import com.fenye.entity.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pageServlet1")
public class PageServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        int pageSize =4;
        int currentPage = 1;

        String currPage = request.getParameter ("currentPage");//从前端上一页或下一页超
        String user_name;
        String user_account;
        String user_state_t;
        int user_state=2;
//        if (request.getSession().getAttribute("user_name") == null && request.getSession().getAttribute("user_account") == null)
//        {
             user_name = request.getParameter ("user_name");
             user_account = request.getParameter ("user_account");
             user_state_t = request.getParameter ("user_state");
             if (user_state_t.equals("已批准"))
             {
                user_state = 2;
             }
             else if (user_state_t.equals("待审批"))
             {
                 user_state = 1;
             }
             else if(user_state_t.equals("已关闭"))
             {
                 user_state = 0;
             }


             if (user_name != null && !"".equals(user_name))
             {
                 request.getSession().setAttribute("user_name_search", user_name);
             }
             if(user_account != null && !"".equals(user_account))
             {
                 request.getSession().setAttribute("user_account_search", user_account);
             }
            request.getSession().setAttribute("user_state_search", user_state);
//        }
//        else
//        {
//            user_name = (String) request.getSession().getAttribute("user_name");
//            user_account = (String) request.getSession().getAttribute("user_account");
//        }

        if (currPage != null && !"".equals(currPage))
        {
            currentPage = Integer.parseInt(currPage);
        }

        BookService bs = new BookService();

        PageBean pageBean1 = bs.SearchUsers(currentPage,pageSize,user_name,user_account,user_state);

        if (pageBean1 == null)
        {
            request.getRequestDispatcher("/SearchUser.jsp").forward(request,response);
//            System.out.println("pagebean1为空");
        }
        else
        {
            request.setAttribute("pb6",pageBean1);
//            System.out.println("pagebean1不为空");
            request.getRequestDispatcher("/ListSearchUser.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
