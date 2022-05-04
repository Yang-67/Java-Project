<%--
  Created by IntelliJ IDEA.
  User: 86136
  Date: 2021/11/17
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>最新通知</title>
    <link rel="stylesheet" href="css1.css">
    <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        var selDepartments;
        var selEmployees;
        var selSelectedEmployees;
        $(document).ready(function (){
            body_load();

            $("#meeting_name").click(function (){
                var starttime=$("#starttime").val();
                var endtime=$("#endtime").val();
                if(starttime!="" && endtime!=""){
                    var date1 = new Date(starttime);
                    var date2 = new Date(endtime);
                    if(date1.getTime()>date2.getTime()) {
                        alert("开始时间不能大于结束时间");
                    }
                    else {
                        // alert("ok")
                        $.ajax({
                            type:"post",
                            url:"Search_RoomServlet",
                            data:{
                                starttime:starttime,
                                endtime:endtime,
                            },
                            datatype:"json",
                            success:function (data){
                                 // alert(data);
                                $("#meeting_name").empty();
                                var dataObj = eval("("+data+")");
                                for(var i=0;i<dataObj.length;i++){
                                    $("#meeting_name").append("<option id='"+dataObj[i].room_id+"' value='"+dataObj[i].room_id+"'>"+dataObj[i].room_name+"</option>");
                                }

                            }
                        })
                    }
                }
                else {
                    alert("请选择会议开始时间和结束时间");
                }
            })
            $("#reset").click(function(){
                $("#meetingname").val("");
                $("#numofattendents").val("");
                $("#starttime").val("");
                $("#endtime").val("");
                $("#description").val("");
            })


            //预定会议具体实现
            $("#sub").click(function(){
                var select_con=[];//参会人员id
                var meeting_room_name=$("#meeting_name").find("option:selected").text();//会议室名称
                var meeting_room_id=$("#meeting_name").find("option:selected").val();//会议室ID
                var meeting_name=$("#meetingname").val();//会议名称
                var meeting_count=$("#numofattendents").val();;//预计参会人数
                var starttime=$("#starttime").val();;//会议开始时间
                var endtime=$("#endtime").val();;//会议结束时间
                var meeting_subscriber=$("#description").val();;//会议说明

                $("#selSelectedEmployees option").each(function(){
                    var option_con = $(this).val();
                    select_con.push(option_con);
                })
                if(select_con=="" || meeting_room_name=="" || meeting_room_id=="" || meeting_name=="" || meeting_count=="" || starttime=="" || endtime=="" || meeting_subscriber==""){
                    alert("请输入完整信息后预定");
                }
                else{
                    alert("会议名称："+meeting_name+"预计参会人数："+meeting_count+"会议开始时间："+starttime+"会议结束时间："+endtime+"" +
                        "会议室ID"+meeting_room_id+"会议室名称"+meeting_room_name+"会议说明"+meeting_subscriber+"参会人员id"+select_con);
                    $.ajax({
                        type:"post",
                        url:"funServlet",
                        data:{
                            flag:9,
                            meeting_name:meeting_name,
                            meeting_count:meeting_count,
                            starttime:starttime,
                            endtime:endtime,
                            meeting_room_id:meeting_room_id,
                            meeting_room_name:meeting_room_name,
                            meeting_subscriber:meeting_subscriber,
                            select_con:select_con.toString()
                        },
                        dataType:"text",
                        success:function (data){
                            alert(data);
                            location.reload();
                        }
                    })
                }
            })
        });

        function employee(employeeid, employeename) {
            this.employeeid = employeeid;
            this.employeename = employeename;
        }
        function department(departmentid, departmentname, employees) {
            this.departmentid = departmentid;
            this.departmentname = departmentname;
            this.employees = employees;
        }

        function body_load() {
            selDepartments = document.getElementById("selDepartments");
            console.log(selDepartments);
            selEmployees = document.getElementById("selEmployees");
            selSelectedEmployees = document.getElementById("selSelectedEmployees");

            $.post("funServlet",{flag:7} ,function (msg) {
                for (var i = 0; i < msg.length; i++) {
                    var item = msg[i];
                    var dep = document.createElement("option");
                    var data = eval("("+msg+")");
                    // alert("msg为:"+msg);
                    // alert("data为:"+data[i].department_id);
                    dep.value = data[i].department_id;
                    dep.text = data[i].department_name;
                    selDepartments.appendChild(dep);
                }
                // });
            });
        }
        function clearList(list) {
            while (list.childElementCount > 0) {
                list.removeChild(list.lastChild);
            }
        }

        function selectEmployees() {
            for (var i = 0; i < selEmployees.options.length; i++) {
                if (selEmployees.options[i].selected) {
                    addEmployee(selEmployees.options[i]);
                    selEmployees.options[i].selected = false;
                }
            }
        }

        function deSelectEmployees() {
            var elementsToRemoved = new Array();
            var options = selSelectedEmployees.options;
            for (var i = 0; i < options.length; i++) {
                if (options[i].selected) {
                    elementsToRemoved.push(options[i]);
                }
            }
            for (i = 0; i < elementsToRemoved.length; i++) {
                selSelectedEmployees.removeChild(elementsToRemoved[i]);
            }
        }

        function addEmployee(optEmployee) {
            var options = selSelectedEmployees.options;
            var i = 0;
            var insertIndex = -1;
            while (i < options.length) {
                if (optEmployee.value == options[i].value) {
                    return;
                } else if (optEmployee.value < options[i].value) {
                    insertIndex = i;
                    break;
                }
                i++;
            }
            var opt = document.createElement("option");
            opt.value = optEmployee.value;
            opt.text = optEmployee.text;
            opt.selected = true;

            if (insertIndex == -1) {
                selSelectedEmployees.appendChild(opt);
            } else {
                selSelectedEmployees.insertBefore(opt, options[insertIndex]);
            }
        }

        function fillEmployees() {
            //清空左边多选下拉框中的所有元素
            clearList(selEmployees);
            //获取当前选中部门的id
            var departmentid = selDepartments.options[selDepartments.selectedIndex].value;

            $.post("funServlet",{flag:8,department_id: departmentid}, function (msg) {
                for (var i = 0; i < msg.length; i++) {
                    var emp = document.createElement("option");
                    var dataObj = eval("(" + msg + ")");
                    emp.value = dataObj[i].user_id;
                    emp.text = dataObj[i].user_name;
                    selEmployees.appendChild(emp);
                }

            });
        }

    </script>
    <style type="text/css">
        #divfrom {
            float: left;
            width: 150px;
        }

        #divto {
            float: left;
            width: 150px;
        }

        #divoperator {
            float: left;
            width: 50px;
            padding: 60px 5px;
        }

        #divoperator input[type="button"] {
            margin: 10px 0;
        }

        #selDepartments {
            display: block;
            width: 100%;
        }

        #selEmployees {
            display: block;
            width: 100%;
            height: 200px;
        }

        #selSelectedEmployees {
            display: block;
            width: 100%;
            height: 225px;
        }
    </style>
</head>
<body onload="fillEmployees()">
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
            <span style="margin-left:25px">会议预定 > 预定会议</span>
            <fieldset style="width:900px ;margin-left:50px">
                <legend>会议信息</legend>
                <table class="formtable">
                    <tr>
                        <td>会议名称：</td>
                        <td>
                            <input type="text" id="meetingname" name="meetingname" maxlength="12"/>
                        </td>
                    </tr>
                    <tr>
                        <td>预计参加人数：</td>
                        <td>
                            <input type="text" id="numofattendents" name="numberofparticipants"
                                   onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')"/>
                        </td>
                    </tr>
                    <tr>
                        <td>预计开始时间：</td>
                        <td>
                            <input class="Wdate" type="text" id="starttime" name="starttime"
                                   onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                        </td>
                    </tr>
                    <tr>
                        <td>预计结束时间：</td>
                        <td>
                            <input class="Wdate" type="text" id="endtime" name="endtime"
                                   onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                        </td>
                    </tr>
                    <tr>
                        <td>会议室名称：</td>
                        <td>
                            <select name="roomid" id="meeting_name">
                                <%--                                <c:forEach items="${list1}" var="mr">--%>
                                <%--                                    <option value="${mr.room_name}">${mr.room_name}</option>--%>
                                <%--                                </c:forEach>--%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>会议说明：</td>
                        <td>
                            <textarea id="description" rows="5" name="description" maxlength="20"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>选择参会人员：</td>
                        <td>
                            <div id="divfrom">
                                <select id="selDepartments" onchange="fillEmployees()">
                                </select>
                                <select id="selEmployees" multiple="true">
                                </select>
                            </div>
                            <div id="divoperator">
                                <input type="button" class="clickbutton" value="&gt;" onclick="selectEmployees()"/>
                                <input type="button" class="clickbutton" value="&lt;" onclick="deSelectEmployees()"/>
                            </div>
                            <div id="divto">
                                <select id="selSelectedEmployees" name="selSelectedEmployees" multiple="true">
                                </select>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="command" colspan="2">
                            <input type="submit" id="sub" class="clickbutton" value="预定会议"/>
                            <input type="reset" class="clickbutton" id="reset" value="重置"/>
                        </td>
                    </tr>
                </table>
            </fieldset>

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