<%--
  Created by IntelliJ IDEA.
  User: 86136
  Date: 2021/12/8
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>商品详情</title>
</head>
<body>
<h3>商品详情页</h3>
<a href="ControlServlet?flag=1">返回商品页</a><br><br>
    <c:forEach items="${oneshopping}" var="emp">
        物品ID ：${emp.wareid}<br>
        物品名称 ：${emp.warename}<br>
        物品价格 ：${emp.wareprice}<br>
        物品类型 ：${emp.waretype}<br>
        物品介绍 ：<textarea rows="3" cols="20" readonly>${emp.wareintro}</textarea><br>
        图片路径 ：${emp.wareimage}<br>
        是否推荐 ：<c:set var="wareif" value="${emp.wareif}"></c:set>
                <c:choose>
                    <c:when test="${wareif==1}">是</c:when>
                    <c:otherwise>否</c:otherwise>
                </c:choose>
        <br>
        发布时间 ：${emp.dattime}<br>
        <img src="${emp.wareimage}">
    </c:forEach>

</body>
</html>
