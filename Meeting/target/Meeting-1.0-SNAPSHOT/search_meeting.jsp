<%--
  Created by IntelliJ IDEA.
  User: 86136
  Date: 2021/11/16
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>搜索会议</title>
    <link rel="stylesheet" href="ind.css">
    <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function (){
            $("#chaxun").click(function(){
                // alert($("#meetingname").val()+$("#roomname").val()+$("#name").val()+$("#reserve_date1").val()+$("#reserve_date2").val()+$("#start_date1").val()+$("#start_date2").val())
                if(($("#reserve_date1").val()=="" || $("#reserve_date2").val()=="")){
                    alert("日期不能为空");
                }
                else {
                    var date1 = new Date($("#reserve_date1").val());
                    var date2 = new Date($("#reserve_date2").val());
                    if(date1.getTime()>date2.getTime()) {
                        alert("开始时间不能大于结束时间");
                    }
                    else {
                        // alert(date1+date2);
                        $.ajax({
                            type:"get",
                            url:"Search_MeetingServlet?meetingname="+$("#meetingname").val()+"&roomname="+$("#roomname").val()+"&name="+$("#name").val() +
                                "&reserve_date1="+$("#reserve_date1").val()+"&reserve_date2="+$("#reserve_date2").val(),
                            datatype:"json",
                            success:function (data){
                                location.reload();
                            }
                        })
                    }
                }
                // if(($("#start_date1").val()=="" || $("#start_date2").val()=="")){
                //     alert("日期不能为空");
                // }
                // $.ajax({
                //     type:"get",
                //     url:"Search_MeetingServlet?meetingname="+$("#meetingname").val()+"&roomname="+$("#roomname").val()+"&name="+$("#name").val(),
                //     // data:{
                //     //     meetingname:$("#meetingname").val(),
                //     //     roomname:$("#roomname").val(),
                //     //     name:$("#name").val(),
                //     //     reserve_date1:$("#reserve_date1").val(),
                //     //     reserve_date2:$("#reserve_date2").val(),
                //     //     start_date1:$("#start_date1").val(),
                //     //     start_date2:$("#start_date2").val()
                //     // },
                //     datatype:"json",
                //     success:function (data){
                //         location.reload();
                //     }
                // })
            })
            $("#chongzhi").click(function(){
                $("#meetingname").val("");
                $("#roomname").val("");
                $("#name").val("");
                $("#reserve_date1").val("");
                $("#reserve_date2").val("");
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
            <span style="margin-left:25px">会议预定 > 搜索会议</span>
            <h4 style="margin-left:50px">搜索会议</h4>
            <form style="margin-left:25px">
                会议名称：<input type="text" id="meetingname" maxlength="12">&emsp;&emsp;
                会议室名称：<input type="text" id="roomname" maxlength="12">&emsp;&emsp;
                预定者姓名：<input type="text" id="name" maxlength="12"><br><br>
                预定日期：从<input type="date" id="reserve_date1">到<input type="date" id="reserve_date2">&emsp;*日期不能为空<br><br>
<%--                会议日期：从<input type="date" id="start_date1">到<input type="date" id="start_date2">&emsp;*日期不能为空<br><br>--%>
                <center>
                <button type="button" id="chaxun">查询</button>&emsp;&emsp;
                <button type="button" id="chongzhi">重置</button>
                </center>
            </form>
            <div id="cao">
                <h4 style="margin-left:50px">查询结果</h4>
                <center>
                    <table cellspacing="0" cellpadding="0" border="0" width="950px" style="text-align:center">
                        <tr style="background-color:skyblue ;text-align:center">
                            <td>会议名称</td>
                            <td>会议室名称</td>
                            <td>起始时间</td>
                            <td>结束时间</td>
                            <td>预定时间</td>
                            <td>预定者</td>
                            <td>操作</td>
                        </tr>

                        <c:forEach items="${pb01.emps}" var="emp" varStatus="vs">
                            <tr ${vs.count%2==1?"style='text-align:center;background-color:white'":"style='text-align:center;background-color:#f1f1f1'"}>
                                <td>${emp.meeting_name}</td>
                                <td>${emp.room_name}</td>
                                <td>${emp.meeting_start}</td>
                                <td>${emp.meeting_end}</td>
                                <td>${emp.meeting_order_time}</td>
                                <td>${emp.meeting_subscriber}</td>
                                <td><button type="button" ><a href="One_MeetingServlet?meeting_id=${emp.meeting_id}">查看详情</a></button></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="page">
                        <a href="${pageContext.request.contextPath}/Search_MeetingServlet?currentPage2=${pb01.currentPage==1?1:pb01.currentPage-1}">&lt;&lt;上一页</a>
                        &nbsp;&nbsp;第${pb01.currentPage}页/共${pb01.totalPages}页&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/Search_MeetingServlet?currentPage2=${pb01.currentPage==pb01.totalPages?pb01.totalPages:pb01.currentPage+1}">下一页&gt;&gt;</a>
                    </div>
                </center>
            </div>
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