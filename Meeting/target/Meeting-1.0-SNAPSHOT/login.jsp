<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="ind.css">
</head>
<body>
<div class="all">
    <div class="all1">
        <div class="top1">
            <br>
            <span>&emsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎访问会议管理系统</span>
        </div>
        <div class="left_login"></div>
        <div class="right_login">
            <center>
                <br><br><br><br>
            <h3>用户登录</h3>
            <form action="login" method="post">
                用户名：<input type="text" name="user_account"><br><br>
                密&emsp;码：<input type="password" name="user_pwd"><br><br>
                <input type="submit" value="登录">&emsp;&emsp;
                <button type="button" name="sign" ><a href="sign_in.jsp">注册</a></button>
            </form>
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
