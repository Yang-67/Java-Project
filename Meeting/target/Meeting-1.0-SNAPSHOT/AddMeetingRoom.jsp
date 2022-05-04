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
            var $r = $("#reset");
            $r.click(function () {
                $("#room_id").val("");
                $("#room_name").val("");
                $("#room_count").val("");
                $("#room_declare").val("");
            })

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
            <span style="margin-left:25px">会议预定 > 添加会议室</span>
            <br><br>
<%--            <h4 style="margin-left:50px">添加会议室</h4>--%>

            <fieldset style="width:900px; margin-left:50px">
                <legend>添加会议室</legend>

                <form action="funServlet?flag=6" method=post>
                    &emsp;&emsp;门牌号：&emsp;&emsp;&emsp;&emsp;&emsp;<input type='text' id="room_id" name="room_id" placeholder="例如:201" maxlength="3" minlength="1" required><br>
                    &emsp;&emsp;会议室名称：&emsp;&emsp;&emsp;<input type='text' id="room_name" name="room_name" placeholder="例如:第一会议室" required><br>
                    &emsp;&emsp;最多容纳的人数：&emsp;<input type='text' id="room_count" name="room_count" placeholder="填写一个正整数" required
                                                     onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')"><br>
                    <p>&emsp;&emsp;当前状态：
                        &emsp;&emsp;&emsp; <input type="radio" name="room_state" value="启用" checked>启用
                        <input type="radio" name="room_state" value="停用">停用
                    </p>
                    &emsp;&emsp;<span style="vertical-align:top">备注：</span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<textarea name="room_declare" id="room_declare" maxlength="50" minlength="1" style="resize:none; width: 460px; height: 150px;" placeholder="50字以内的文字描述" required ></textarea>
                    <br>
                    &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<center><input type="submit" value="添加"></center>
                </form>
                <center><button id=reset>重置</button></center>

            </fieldset>

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
