<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#signInDao").click(function (){
            alert("ok")
            $.ajax({
                type: "post",
                url: "SignInServlet",
                data:{
                    "user_name":$("#user_name").val(),
                    "user_pwd1":$("#user_pwd1").val(),
                    "user_account":$("#user_account").val(),
                    "user_phone":$("#user_phone").val(),
                    "user_email":$("#user_email").val(),
                    "department_user":$("#department_user").val()
                },
                datatype: "json",
                success: function (data) {
                    $("#user_pwd").val("");
                    $("#user_pwd1").val("");
                    $("#result").html(data);
                }
            })
        })

        $("#flash").click(function(){
            $("#user_name").val("");
            $("#user_pwd").val("");
            $("#user_pwd1").val("");
            $("#user_account").val("");
            $("#user_phone").val("");
            $("#user_email").val("");
            $("#pwd").html("");
        })

        $("#department_user").click(function(){
            $.ajax({
                type:"get",
                url:"SignInServlet?flag=1",
                datatype:"json",
                success:function (data){
                    // alert(data);
                    $("#department_user").empty();
                    var dataObj = eval("("+data+")");
                    for(var i=0;i<dataObj.length;i++){
                        $("#department_user").append("<option value='"+dataObj[i].department_name+"'>"+dataObj[i].department_name+"</option>");
                    }

                }
            })
        })
    })

    function pw(){
        var password = $("#user_pwd").val();
        if($("#user_pwd1").val() != password){
            $("#pwd").html("两次密码需一致");
            $("#signInDao").attr("disabled",true);
        }
        else {
            $("#pwd").html("√");
            $("#signInDao").attr("disabled",false);
        }
    }
    function user_ac() {
        var user_account = $("#user_account").val();
        $.ajax({
            type:"get",
            url:"SignInServlet?flag=2&user_account=" + user_account,
            datatype:"json",
            success:function (data){
                    alert(data);
            }
        })
    }
    function pww() {
        if($("#user_pwd").val().length < 7) {
            $("#pwdd").html("");
            $("#pwdd").html("密码不能少于6位");
        }
        else {
            $("#pwdd").html("");
        }
    }
</script>
<html>
<head>
    <title>员工注册</title>
    <link rel="stylesheet" href="ind.css">
</head>
<body>
<div class="all">
    <div class="all1">
        <div class="top1">
            <br>
            <span>&emsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎访问会议管理系统</span>
        </div>

        <center>
            <h3>员工注册</h3>
            <form >
                姓名：&emsp;&emsp;&emsp;<input type="text" name="user_name" id="user_name" maxlength="12" ><br><br>
                账户名：&emsp;&emsp;<input type="text" name="user_account" id="user_account" maxlength="12" onblur="user_ac()"><br><br>
                密码：&emsp;&emsp;&emsp;<input type="password" name="user_pwd" id="user_pwd" maxlength="12" minlength="6" placeholder="密码长度为6-12位" onblur="pww()"><br>
                &emsp;&emsp;&emsp;<span id="pwdd"></span><br>
                确认密码：&emsp;<input type="password" name="user_pwd1" id="user_pwd1" maxlength="12" minlength="6" placeholder="密码长度为6-12位" onblur="pw()"><br>
                &emsp;&emsp;&emsp;<span id="pwd"></span><br>
                联系电话：&emsp;<input type="text" name="user_phone" id="user_phone" maxlength="11" placeholder="最长11位正整数"><br><br>
                电子邮件：&emsp;<input type="email" name="user_email" id="user_email"><br><br>
                所在部门：&emsp;<select name="department_user" id="department_user">
<%--                            <option>销售部</option>--%>
<%--                            <option>人力部</option>--%>
<%--                            <option>财务部</option>--%>
                        </select><br><br>
                <input type="submit" value="注册" name="signInDao" id="signInDao">&emsp;&emsp;
                <input type="button" value="重置" name="flash" id="flash"><br>
                <a href="login.jsp">立即登录</a>
                <div id="result"></div>
            </form>
        </center>

        <div class="bottom">
            <div>
                更多问题，欢迎联系<a href="javascript:;">管理员</a>
            </div>
        </div>
    </div>

</div>
</body>
</html>
