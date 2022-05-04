package com.fenye.servlet;



import com.fenye.ELandJSTL.BookService;
import com.fenye.JavaBean.PageBean_room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pageServlet_RoomDetail")
public class PageServlet_RoomDetail extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        int pageSize =4;
        int currentPage = 1;

        String currPage = request.getParameter ("currentPage");//从前端上一页或下一页超
        int room_id = Integer.parseInt(request.getParameter ("room_id"));
//        if (request.getSession().getAttribute("user_name") == null && request.getSession().getAttribute("user_account") == null)
//        {


        if (room_id != 0)
        {
            request.getSession().setAttribute("room_id", room_id);
        }


        if (currPage != null && !"".equals(currPage))
        {
            currentPage = Integer.parseInt(currPage);
        }

        BookService bs = new BookService();

        PageBean_room pageBean3 = bs.ListRoomDetail(currentPage,pageSize,room_id);



        request.setAttribute("pb3",pageBean3);
        request.getRequestDispatcher("/ListRoomDetail.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
