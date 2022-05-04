package com.fenye.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "OutServlet", value = "/OutServlet")
public class OutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("user_id");
        request.getSession().removeAttribute("user_name");
        request.getSession().removeAttribute("user_account");
        request.getSession().removeAttribute("user_name_search");
        request.getSession().removeAttribute("user_name_search");
        request.getSession().removeAttribute("user_state_search");

        response.sendRedirect("login.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
