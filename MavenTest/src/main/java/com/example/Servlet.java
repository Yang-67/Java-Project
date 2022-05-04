package com.example;

import com.example.dao.BookDao;
import com.example.maventest.Book;
import com.example.utils.BookUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Servlet", value = "/Servlet")
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询全部信息
        {
            SqlSession session = BookUtils.getSqlSession();
            BookDao bookDao = session.getMapper(BookDao.class);
            List<Book> books = bookDao.selectBook();
            for (Book book : books) {
                System.out.println(book);
            }
            session.close();
        }
        //插入数据
//        {
//            SqlSession session1 = BookUtils.getSqlSession();
//            BookDao bookDao1 = session1.getMapper(BookDao.class);
//            int flag = bookDao1.addBook(new Book("A07", "lll", "kkk", 23.1, "fff"));
//            if (flag > 0) {
//                System.out.println("插入成功");
//            }
//            session1.commit();//提交事务
//            session1.close();
//        }
//        //修改数据
        {
            SqlSession session2 = BookUtils.getSqlSession();
            BookDao bookDao2 = session2.getMapper(BookDao.class);
            int flag = bookDao2.updateBook(new Book("A07", "lkl", "klk", 23.1, "flf"));
            if (flag > 0) {
                System.out.println("修改成功");
            }
            session2.commit();//提交事务
            session2.close();
        }
        //根据Bno查数据
        {
            SqlSession session3 = BookUtils.getSqlSession();
            BookDao bookDao3 = session3.getMapper(BookDao.class);
            Book books3 = bookDao3.getBookById("A01");
            System.out.println(books3);
            session3.close();
        }
        //根据Bno删除数据
        {
            SqlSession session4 = BookUtils.getSqlSession();
            BookDao bookDao4 = session4.getMapper(BookDao.class);
            int flag = bookDao4.removeBook("A07");
            if (flag > 0) {
                System.out.println("删除成功");
            }
            session4.commit();//提交事务
            session4.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
