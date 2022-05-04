package com.fenye.servlet;



import com.alibaba.fastjson.JSON;
import com.fenye.ELandJSTL.JdbcDao;
import com.fenye.JavaBean.Meeting;
import com.fenye.JavaBean.User;
import com.fenye.dao.DepartmentDao;
import com.fenye.dao.MeetingRoomDao;
import com.fenye.dao.Meeting_infoDao;
import com.fenye.dao.UserDao;
import com.fenye.entity.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/funServlet")
public class FunServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String flag = request.getParameter("flag");
        PrintWriter out = response.getWriter();
        System.out.println(flag);
//        String user_account = request.getParameter("user_account");
//        String user_account = (String) request.getSession().getAttribute("user_account");
        List<Meeting> list = null;
        List<User> list1 = null;
        UserDao ud = new UserDao();
        JdbcDao jd = new JdbcDao();
        MeetingRoomDao md = new MeetingRoomDao();
        String user_account;
        String user_name;
        int room_id;
        switch (flag)
        {
            case "1"://显示注册审批信息


                break;
            case "2"://搜索员工

                user_name = request.getParameter("user_account");
                user_account = request.getParameter("user_account");
//                list1 = jd.SearchUsers(user_name,user_account);
                request.getSession().setAttribute("searchUser",list1);
                response.sendRedirect("SearchUser.jsp");

                break;
            case "3"://关闭账号
                user_account = request.getParameter("user_account");
                if(ud.CloseUserAccount(user_account))
                {
                    request.getRequestDispatcher("/SearchUser.jsp").forward(request,response);
                }
                else
                {
                    System.out.println("执行失败");
                    response.getWriter().println("关闭失败！");
                }
                break;
            case "4"://审批通过账号
                user_account = request.getParameter("user_account");
                if(ud.ChangeUserStateOpen(user_account))
                {
                    request.getRequestDispatcher("/ListAllRegMessage.jsp").forward(request,response);
                }
                else
                {
                    System.out.println("执行失败");
                    response.getWriter().println("通过审批失败！");
                }
                break;
            case "5"://审批未通过账号
                user_account = request.getParameter("user_account");
                if(ud.ChangeUserStateClose(user_account))
                {
                    request.getRequestDispatcher("/ListAllRegMessage.jsp").forward(request,response);
                }
                else
                {
                    System.out.println("执行失败");
                    response.getWriter().println("审批不通过失败！");
                }
                break;
            case "6"://添加会议室
                room_id = Integer.parseInt(request.getParameter("room_id"));
                String room_name = request.getParameter("room_name");
                int room_count = Integer.parseInt(request.getParameter("room_count"));
                String room_state_t = request.getParameter("room_state");
                int room_state = 1;
                if (room_state_t.equals("停用"))
                {
                    room_state = 0;
                }
                String room_declare = request.getParameter("room_declare");
                System.out.println(room_declare);
                if(md.AddMeetingRoom(room_id,room_name,room_count,room_state,room_declare))
                {
                    request.getRequestDispatcher("/AddMeetingRoom.jsp").forward(request,response);

                }
                else
                {
                    response.getWriter().println("添加失败！");
                }
                break;
                //--------------------------
            case "7"://查询所有部门
                response.setContentType("text/html;charset=UTF-8");
                DepartmentDao departmentDao =new DepartmentDao();
                try {
                    List<Department> list_department = departmentDao.findDepartment();
//                    System.out.println(list_department);
//                    String departmentJSON = JSON.toJSONString(list_department);
                    String departmentJSON= JSON.toJSONString(list_department);
                    out.println(departmentJSON);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "8"://根据部门查询员工
                response.setContentType("text/html;charset=UTF-8");
                int department_id = Integer.parseInt(request.getParameter("department_id"));
                UserDao userDao =new UserDao ();
                try {
                    List<User> list3 = userDao.getuserbydid(department_id);
                    String usersJSON = JSON.toJSONString(list3);
                    out.println(usersJSON);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "9"://添加预定会议记录
                response.setContentType("text/html;charset=UTF-8");
                String select_con=request.getParameter("select_con");//参会人员id
                String meeting_room_name=request.getParameter("meeting_room_name");//会议室名称
                int meeting_room_id=Integer.parseInt(request.getParameter("meeting_room_id"));//会议室ID
                String meeting_name=request.getParameter("meeting_name");//会议名称
                int meeting_count=Integer.parseInt(request.getParameter("meeting_count"));;//预计参会人数
                String starttime=request.getParameter("starttime");//会议开始时间
                String endtime=request.getParameter("endtime");//会议结束时间
                String meeting_subscriber=request.getParameter("meeting_subscriber");//会议说明
//                System.out.println(select_con+meeting_room_name+meeting_room_id+meeting_name+meeting_count+starttime+endtime+meeting_subscriber);
                int result = 1;
                int s = 1;
                Meeting_infoDao mif = new Meeting_infoDao();
                if (mif.AddNewMeetingOrder(meeting_room_id,meeting_room_name,meeting_name,meeting_subscriber,starttime,endtime,meeting_count))
                {
                    String[] user_ids = null;
                    user_ids = select_con.split(",");
                    for (int i = 0; i < user_ids.length; i++)
                    {
                        s = mif.AddMeetingPeople(Integer.parseInt(user_ids[i]));
                        result = s*result;
                        System.out.println("result为"+result);
                    }
                    System.out.println(0);
//                    System.out.println(user_ids[0]);
                    if (result == 1)
                    {
                        out.print("预定成功!");
//                        System.out.println("进来了");
//                        request.getRequestDispatcher("/login.jsp").forward(request,response);
//                        response.sendRedirect("/login.jsp");
                    }
                }
                else
                {
                    out.print("预定失败！");
                }
                break;
                //------------------------
            default:
                System.out.println("进默认");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
