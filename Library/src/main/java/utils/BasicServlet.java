package utils;

import beans.User;
import com.alibaba.fastjson.JSON;
import dao.BasicMapper;
import dao.BookMapper;
import org.apache.ibatis.session.SqlSession;

import beans.Admin;
import beans.College;
import beans.Major;
import com.alibaba.fastjson.JSON;
import dao.AdminMapper;
import dao.BasicMapper;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "BasicServlet", value = "/BasicServlet")
public class BasicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            String flag = request.getParameter("flag");
            System.out.println("进入BasicServlet 选择方法：" + flag);
            switch (flag) {
                case "getTotalCollege":
                    out.print(getTotalCollege(request, response));
                    break;
                case "getTotalMajor":
                    out.print(getTotalMajor(request, response));
                    break;
                case "getTotalStudent":
                    out.print(getTotalStudent(request, response));
                    break;
                default:
                    System.out.println("----------------flag error!----------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取所有学生
    private String getTotalStudent(HttpServletRequest request, HttpServletResponse response) {
        SqlSession session = MybatisUtils.getSqlSession();
        try {
            String contentJSON = "-1";
            BasicMapper basicMapper = session.getMapper(BasicMapper.class);
            List<User> users = basicMapper.getAllStudent();
            if(users != null){
                contentJSON = JSON.toJSONString(users);
            }
            return contentJSON;
        } catch (Exception e) {
            System.out.println(e);
            return "-1";
        } finally {
            session.close();
        }
    }

    //获取学院信息3.1
    private String getTotalCollege(HttpServletRequest request, HttpServletResponse response) {
        try {
            Boolean deBug = true;
            String blockName = "getTotalCollege";
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            if (deBug) System.out.println("方法" + blockName + " IN--------------");
            HttpSession session = request.getSession();
            SqlSession sqlSession = MybatisUtils.getSqlSession();
//-----------------------------------------------
            BasicMapper mapper = sqlSession.getMapper(BasicMapper.class);
//        if (deBug) System.out.println("输入数据:admin_id=" + adminId + " admin_pwd=" + adminPwd);
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            List<College> result = mapper.getTotalCollege();
            if (deBug) System.out.println("返回数据：" + JSON.toJSONString(result));
            if (deBug) System.out.println("方法" + blockName + " END--------------");
            sqlSession.close();
            return JSON.toJSONString(result);
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }

    }

    //获取专业信息3.2
    private String getTotalMajor(HttpServletRequest request, HttpServletResponse response) {

        try {
            Boolean deBug = true;
            String blockName = "getTotalMajor";
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            if (deBug) System.out.println("方法" + blockName + " IN--------------");
            HttpSession session = request.getSession();
            SqlSession sqlSession = MybatisUtils.getSqlSession();
//-----------------------------------------------
            BasicMapper mapper = sqlSession.getMapper(BasicMapper.class);
//        if (deBug) System.out.println("输入数据:admin_id=" + adminId + " admin_pwd=" + adminPwd);
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            List<Major> result = mapper.getTotalMajor();
            if (deBug) System.out.println("返回数据：" + JSON.toJSONString(result));
            if (deBug) System.out.println("方法" + blockName + " END--------------");
            sqlSession.close();
            return JSON.toJSONString(result);
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
    }


}
