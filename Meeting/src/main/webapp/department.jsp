<%--
  Created by IntelliJ IDEA.
  User: 86136
  Date: 2021/11/15
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>部门管理</title>
    <link rel="stylesheet" href="ind.css">
    <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function (){
            $("#add").click(function(){
                var department = $("#department").val();
                if(department != ""){
                    // alert(department)
                    $.ajax({
                        type:"post",
                        url:"DepartmentServlet",
                        data:{
                            flag:1,
                            departmentname:department
                        },
                        datatype:"json",
                        success:function (data){
                            alert(data);
                            location.reload();
                            // $("#add_info").html(data);
                            // $("#department").val("");
                        }
                    })
                }
                else {
                    alert("部门名不能为空")
                }
            })
        })
        var dep_id;
        function deit(id){
            dep_id=id;
            $("#"+id).hide();
            var dep_name=$("#s"+dep_id).html();
            $("#s"+dep_id).html("");
            $("#s"+id).append("<input type='text' id='t"+dep_id+"' value='"+dep_name+"'>");
            $("#o"+id).append("<button type='button' id='r"+id+"'>确定</button><button type='button' id='q"+id+"'>取消</button>");
            $("#q"+id).click(function(){
                $("#"+id).show();
                $("#r"+id).remove();
                this.remove();
                $("#s"+dep_id).html($("#t"+dep_id).val());
            })
            $("#r"+id).click(function(){
                var temp=$("#t"+dep_id).val();
                if(temp == "")
                    alert("部门名不能为空");
                else{
                    $.ajax({
                        type:"post",
                        url:"DepartmentServlet",
                        data:{
                            flag:2,
                            departmentid:dep_id,
                            departmentname:temp
                        },
                        datatype:"json",
                        success:function (data){
                            $("#s"+dep_id).html($("#t"+dep_id).val());
                            alert(data);
                        }
                    })
                }
            })
        }
        function del(id) {
            // alert(dep_id)
            // alert(id.substr(1))
            // $("#w"+id[1]).remove();
            $.ajax({
                type:"post",
                url:"DepartmentServlet",
                data:{
                    flag:3,
                    departmentid:id.substr(1)
                },
                datatype:"json",
                success:function (data){
                    location.reload();
                    alert(data);
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
            <span style="margin-left:25px">人员管理 > 部门管理</span>
<%--            <fieldset id="fieldset" width="950px" height="100px">--%>
<%--                <legend>添加部门</legend>--%>
<%--                部门名称：<input type="text" id="department">&emsp;--%>
<%--                <button id="add">添加</button>--%>
<%--                <span id="add_info"></span>--%>
<%--            </fieldset>--%>
            <h4 style="margin-left:50px">添加部门：</h4>
            <div style="margin-left:25px">
                部门名称：<input type="text" id="department" maxlength="12">&emsp;
                <button id="add">添加</button>
                <span id="add_info"></span>
            </div>

            <h4 style="margin-left:50px">部门信息：</h4>
            <center>
                <table cellspacing="0" cellpadding="0" border="0" style="text-align:center">
                    <tr style="background-color:skyblue ;text-align:center">
                        <td width="300">部门编号</td>
                        <td width="450">部门名称</td>
                        <td width="200">操作</td>
                    </tr>
                    <c:forEach items="${pb5.departments}" var="emp" varStatus="vs">
                        <tr id=w${emp.department_id} ${vs.count%2==1?"style='text-align:center;background-color:white'":"style='text-align:center;background-color:#f1f1f1'"}>
                            <td>${emp.department_id}</td>
                            <td id=s${emp.department_id}>${emp.department_name}</td>
                            <td id=o${emp.department_id}><button type="button" id=${emp.department_id} onclick="deit(id)">编辑</button>&emsp;<button type="button" id=p${emp.department_id} onclick="del(id)">删除</button>&emsp;</td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="page">
                    <a href="${pageContext.request.contextPath}/DepartmentServlet?currentPage=${pb5.currentPage==1?1:pb5.currentPage-1}">&lt;&lt;上一页</a>
                    &nbsp;&nbsp;第${pb5.currentPage}页/共${pb5.totalPages}页&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/DepartmentServlet?currentPage=${pb5.currentPage==pb5.totalPages?pb5.totalPages:pb5.currentPage+1}">下一页&gt;&gt;</a>
                </div>
            </center>
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
