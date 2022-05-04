package com.fenye.ELandJSTL;

import com.fenye.entity.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PageServlet")
public class PageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        int pageSize =4;
        int currentPage = 1;

        String currPage = request.getParameter ("currentPage");//从前端上一页或下一页超

        if (currPage != null && !"".equals(currPage))
        {
            currentPage = Integer.parseInt(currPage);
        }

        BookService bs = new BookService();


        PageBean pageBean = bs.findBooksPage(currentPage,pageSize);

        request.setAttribute("pb",pageBean);
        request.getRequestDispatcher("/ListAllRegMessage.jsp").forward(request,response);
    }
}
