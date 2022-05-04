<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

<form action="AdminServlet?flag=login" method="post">
    用户名称<input name="username" type="text" ><br>
    用户密码<input name="password" type="password" ><br>
    <input name="login" type="submit" value="登入">
</form>

</body>
</html>