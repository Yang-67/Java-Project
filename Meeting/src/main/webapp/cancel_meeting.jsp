<%--
  Created by IntelliJ IDEA.
  User: 86136
  Date: 2021/11/16
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>取消会议</title>
    <link rel="stylesheet" href="ind.css">
    <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function (){

        })
        function yes(id){
            var temp = $("#cencal").val();
            // alert( temp.length );
                $.ajax({
                    type:"post",
                    url:"ReserveServlet",
                    data:{
                        meetingid:id,
                        cencal:temp
                    },
                    datatype:"json",
                    success:function (data){
                        alert(data);
                        // location.reload();
                    }
                })
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
            <span style="margin-left:25px">个人中心 > 会议信息</span>
            <h4 style="margin-left:50px">会议信息</h4>
            <div style="margin-left:25px">
                <c:forEach items="${meeting}" var="emp" varStatus="vs">
                    会议名称：&emsp;&emsp;&emsp;&emsp;${emp.meeting_name}
                    <br>
                    预计参加人数：&emsp;&emsp;${emp.meeting_count}
                    <br>
                    预计开始时间：&emsp;&emsp;${emp.meeting_start}
                    <br>
                    预计结束时间：&emsp;&emsp;${emp.meeting_end}
                    <br>
                    会议说明：&emsp;&emsp;&emsp;&emsp;${emp.meeting_describe}
                    <br>
                    <c:if test="${not empty emp.meeting_cancel_res}">
                        取消原因：&emsp;&emsp;&emsp;&emsp;${emp.meeting_cancel_res}<br>
                    </c:if>
                    参会人员：&emsp;&emsp;&emsp;&emsp;
                </c:forEach>
            </div>

            <center>
                <table cellspacing="0" cellpadding="0" border="0" style="text-align:center">
                    <tr style="background-color:skyblue ;text-align:center">
                        <td width="200px">姓名</td>
                        <td width="300px">电话</td>
                        <td width="300px">邮箱</td>
                    </tr>
                    <c:forEach items="${pb44.users}" var="emp" varStatus="vs">
                        <tr ${vs.count%2==1?"style='text-align:center;background-color:white'":"style='text-align:center;background-color:#f1f1f1'"}>
                            <td>${emp.user_name}</td>
                            <td>${emp.user_phone}</td>
                            <td>${emp.user_email}</td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="page">
                    <a href="${pageContext.request.contextPath}/CancelServlet?meeting_id=${meeting_id}&currentPage=${pb44.currentPage==1?1:pb44.currentPage-1}">&lt;&lt;上一页</a>
                    &nbsp;&nbsp;第${pb44.currentPage}页/共${pb44.totalPages}页&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/CancelServlet?meeting_id=${meeting_id}&currentPage=${pb44.currentPage==pb44.totalPages?pb44.totalPages:pb44.currentPage+1}">下一页&gt;&gt;</a>
                </div>
                <br>
            </center>
            <h4 style="margin-left:50px">会议取销</h4>
            <div  style="margin-left:25px" id="cen">
                取消原因：<br>
                &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<textarea rows="5" cols="100" id="cencal" placeholder="30字以内" maxlength="30"></textarea><br>
                &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<button type="button" id=${meeting_id} onclick="yes(id)">确认取消</button>
            </div>
            <br>
        </div>
        <div class="bottom">
            <div>
                更多问题，欢迎联系<a href="javascript:;">管理员</a>
            </div>
        </div>
    </div>

</div>
</body>
</html>
