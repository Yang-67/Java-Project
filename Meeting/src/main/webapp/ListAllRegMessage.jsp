<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 86136
  Date: 2021/11/11
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="ind.css">
    <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function (){


        });

        function getQueryString(user_account) {
            var reg = new RegExp("(^|&)" + user_account + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return decodeURI(r[2]); return null;
        }

    </script>
</head>
<body>
<div class="all">
    <div class="all1">
        <div class="top">
            <div class="top1">
                <br>
                <span>&emsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎访问会议管理系统</span>
            </div>
            <div class="top2">
                <div class="top_text">欢迎您,${user_account}&emsp;&emsp;<a href="OutServlet">退出</a></div>
            </div>
        </div>
        <div class="left">
            <ul>
                <li><b>&nbsp;&nbsp;个人中心</b></li>
                <li><a id="a1" href="ControlServlet" >&emsp;&emsp;最新通知</a></li>
                <li><a id="a2" href="ReserveServlet">&emsp;&emsp;我的预定</a></li>
                <li><a id="a3" href="AllServlet">&emsp;&emsp;我的会议</a></li>
                <li><b>&nbsp;&nbsp;人员管理</b></li>
                <li><a id="a4" href="DepartmentServlet">&emsp;&emsp;部门管理</a></li>
                <li><a id="a6" href="PageServlet">&emsp;&emsp;注册审批</a></li>
                <li><a id="a7" href="SearchUser.jsp">&emsp;&emsp;搜索员工</a></li>
                <li><b>&nbsp;&nbsp;会议预定</b></li>
                <li><a id="a8" href="AddMeetingRoom.jsp">&emsp;&emsp;添加会议室</a></li>
                <li><a id="a9" href="pageServlet_MeetingRoom">&emsp;&emsp;查看会议室</a></li>
                <li><a id="a10" href="meeting_order.jsp">&emsp;&emsp;预定会议</a></li>
                <li><a id="a11" href="Search_MeetingServlet">&emsp;&emsp;搜索会议</a></li>
            </ul>
        </div>
        <div class="right" id="right">
            <span style="margin-left:25px">人员管理 > 注册审批</span>
            <h4 style="margin-left:50px">所有待审批注册信息</h4>
            <center>
                <table border="1" width="950px" cellspacing="0" cellpadding="0" style="text-align:center; background-color: #71c1fb">
                    <tr>
                        <td >姓名</td>
                        <td>账号名</td>
                        <td>联系电话</td>
                        <td>电子邮件</td>
                        <td>操作</td>
                    </tr>

                    <c:forEach items="${pb.user}" var="user" varStatus="vs">
                        <tr ${vs.count%2==1?"style='background-color:orange'":"style='background-color:white'"}>
                            <td>${user.user_name}</td>
                            <td>${user.user_account}</td>
                            <td>${user.user_phone}</td>
                            <td>${user.user_email}</td>
                            <td><a href="funServlet?flag=4&user_account=${user.user_account}"><button>通过</button></a><a href="funServlet?flag=5&user_account=${user.user_account}"><button>不通过</button></a></td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="page"></div>

                <a href="PageServlet?currentPage=1" style="text-decoration:none">
                    <button>首页</button>
                </a>
                <a href="PageServlet?currentPage=${pb.currentPage==1?1:pb.currentPage-1}" style="text-decoration:none">
                    <button>上一页</button>
                </a>
                <span>当前第${pb.currentPage}页</span>
                <span>共${pb.totalPage}页</span>
                <a href="PageServlet?currentPage=${pb.currentPage==pb.totalPage?pb.totalPage:pb.currentPage+1}" style="text-decoration:none">
                    <button>下一页</button>
                </a>
                <a href="PageServlet?currentPage=${pb.totalPage}" style="text-decoration:none">
                    <button>尾页</button>
                </a>
            </center>
        </div>
        <div class="bottom">
            <div>
                更多问题，欢迎联系<a href="#">管理员</a>
            </div>
        </div>
    </div>

</div>
</body>
</html>
