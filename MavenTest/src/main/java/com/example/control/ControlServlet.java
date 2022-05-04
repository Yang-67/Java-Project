package com.example.control;

import com.alibaba.fastjson.JSON;
import com.example.dao.BookDao;
import com.example.mapper.DocMapper;
import com.example.mapper.UserMapper;
import com.example.maventest.Book;
import com.example.pojo.Doc;
import com.example.pojo.User;
import com.example.utils.BookUtils;
import com.example.utils.Upload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "ControlServlet", value = "/ControlServlet")
public class ControlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("文件下载");
//        System.out.println(request.getParameter("ID"));
        String fileName = request.getParameter("doc_name");
        //获取要下载的文件所在目录的路径
        String dir = getServletContext().getRealPath("/upload");
        System.out.println(dir);
        //获取要下载的文件
        File file = new File(dir, fileName);
        if(!file.exists()){
            System.out.print("文件"+fileName+"不存在");
            return ;
        }
        String realname = fileName.substring(fileName.indexOf("_")+1);
        fileName = URLEncoder.encode(fileName,"utf-8");
        //重要参数：设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment; filename="+realname);
        //浏览器保存的文件名
        //读取要下载的文件，保存到文件输入流
        FileInputStream fis = new FileInputStream(file);
        //创建输出流
        OutputStream os = response.getOutputStream();
        //创建缓冲区
        byte[] buffer = new byte[1024];
        int len;
        while ((len =fis.read(buffer))>0){
            os.write(buffer,0,len);
        }
        //关闭文件输入流
        fis.close();
        //关闭输出流
        os.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        int flag= Integer.parseInt(request.getParameter("flag"));
        if(flag == 1){
            String user_name = request.getParameter("name");
            String user_pwd = request.getParameter("password");
//            out.print(user_name + "=" + user_pwd);
            SqlSession session = BookUtils.getSqlSession();
            UserMapper userMapper = session.getMapper(UserMapper.class);
            Map<String, String> map = new HashMap<String, String>();
            map.put("user_name",user_name);
            map.put("user_pwd",user_pwd);
            User user = userMapper.getUser(map);
            if(user != null){
                request.getSession().setAttribute("user_id",user.getUser_id());
                out.print("true");
            }
            System.out.println(user);
            session.close();
        }
        if(flag == 2){
            request.getSession().setAttribute("one",request.getParameter("one"));
//            System.out.println(request.getParameter("one"));
        }
        if(flag == 3){
            System.out.println("进入上传");
            int doc_authority = Integer.parseInt((String) request.getSession().getAttribute("one")) ;
            System.out.println("权限:"+doc_authority);
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload sfu = new ServletFileUpload(factory);
            sfu.setHeaderEncoding("utf-8");
            try {
                sfu.setFileSizeMax(1024*1024*5);
                sfu.setSizeMax(1024*1024*10);
                List<FileItem> fileItems = sfu.parseRequest(request);
                String uploadPath = this.getServletContext().getRealPath("/upload");//上传路径
                List<String> doc_name1=new Upload().upload_doc(fileItems,uploadPath);
                if(doc_name1 != null){
                    System.out.println("上传成功");
                    System.out.println("登录者ID:"+request.getSession().getAttribute("user_id"));
                    for(String doc_name:doc_name1){
                        System.out.println("文件名:"+doc_name);
                        int user_id = (int) request.getSession().getAttribute("user_id");
                        if(doc_authority == 1){
                            doc_authority=user_id;
                        }
                        SqlSession session1 = BookUtils.getSqlSession();
                        DocMapper docMapper = session1.getMapper(DocMapper.class);
                        if (docMapper.addDoc(new Doc(user_id,doc_name,doc_authority)) > 0) {

                            System.out.println("插入成功");
                        }
                        session1.commit();//提交事务
                        session1.close();
                    }
                    out.print("上传成功");
                    String url = request.getContextPath()+"/upload.html";
                    out.println("<a href='"+response.encodeURL(url)+"'>返回</a>");
                }
            } catch (FileUploadBase.FileSizeLimitExceededException e) {
                out.print("文件过大，不能超过5MB");
                String url = request.getContextPath()+"/upload.html";
                out.println("<a href='"+response.encodeURL(url)+"'>返回</a>");
                System.out.println("文件过大，不能超过5MB");
            } catch (FileUploadBase.SizeLimitExceededException e) {
                out.print("总文件大小不能超过10MB");
                String url = request.getContextPath()+"/upload.html";
                out.println("<a href='"+response.encodeURL(url)+"'>返回</a>");
                System.out.println("总文件大小不能超过10MB");
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }
        if(flag == 4){
            System.out.println("可下载文件列表");
            SqlSession session3 = BookUtils.getSqlSession();
            DocMapper docMapper = session3.getMapper(DocMapper.class);
            int user_id= (int) request.getSession().getAttribute("user_id");
            List<Doc> docs = docMapper.getDocById(user_id);
            String contentJSON = JSON.toJSONString(docs);
            out.println(contentJSON);
            for(Doc doc : docs)
                System.out.println(doc);
            session3.close();
        }
    }
}
