package com.example.control;

import com.example.mapper.DocMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.PageBean;
import com.example.utils.BookUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "MyFileServlet", value = "/MyFileServlet")
public class MyFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        System.out.println("我的文件");

        SqlSession session = BookUtils.getSqlSession();
        DocMapper docMapper = session.getMapper(DocMapper.class);
        int count = 0;
        int pageSize = 3, currentPage = 1;
        count=docMapper.getDocCount((Integer) request.getSession().getAttribute("user_id"));
        System.out.println("总记录数:"+count);
        String currPage = request.getParameter("currentPage");
        if(currPage != null && !"".equals(currPage))
            currentPage = Integer.parseInt(currPage);


        try{
            int totalPage = (int) Math.ceil(count*1.0 / pageSize);
            System.out.println("页数:"+totalPage);

            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("currentPage",(currentPage-1)*pageSize);
            map.put("pageSize",pageSize);
            map.put("user_id",(Integer) request.getSession().getAttribute("user_id"));

            PageBean pb = new PageBean();
            pb.setEmps(docMapper.getMyDoc(map));
            pb.setCount(count);
            pb.setCurrentPage(currentPage);
            pb.setPageSize(pageSize);
            pb.setTotalPages(totalPage);
            request.setAttribute("pb",pb);
        }catch(Exception e){
            e.printStackTrace();
        }
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
