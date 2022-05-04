package com.example.ajaxtextupd;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "OutServlet", value = "/OutServlet")
public class OutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("user_name")!=null){
            request.getSession().removeAttribute("user_id");
            request.getSession().removeAttribute("user_name");
            request.getSession().removeAttribute("user_sf");
        }

    }
}
