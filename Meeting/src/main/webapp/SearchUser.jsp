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
        $(document).ready(function () {
            var $r = $("#reset");
            $r.click(function () {
                $("#user_name").val("");
                $("#user_account").val("");
            });
            var $s = $("#submit");
            $s.click(function () {
                if ($("#user_name").val()==="" && $("#user_account").val()===""){
                    alert("搜索条件不可全为空！");

                }
            })


        })
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
            <span style="margin-left:25px">人员管理 > 搜索员工</span>
<%--            <h4 style="margin-left:50px">搜索员工</h4>--%><br><br>
            <center>
            <fieldset style="width:900px">
                <legend>搜索员工</legend>

        <form action="pageServlet1" method=post>
        姓名：<input type='text' id="user_name" name="user_name" maxlength="12">&emsp;&emsp;
        账号名：<input type='text' id="user_account" name="user_account" maxlength="12"><br>
        <p>状态：
            <input type="radio" name="user_state" value="已批准" checked>已批准
            <input type="radio" name="user_state" value="待审批">待审批
            <input type="radio" name="user_state" value="已关闭">已关闭
        </p>
            <input type="submit" value="查询" id="submit">
        </form>
        <button id=reset>重置</button>

            </fieldset>
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
