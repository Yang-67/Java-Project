<%--
  Created by IntelliJ IDEA.
  User: 86136
  Date: 2021/12/8
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>商品展示</title>
</head>
<body>
<h3>商品展示</h3>
<table cellspacing="0" border="1">
<tr style="background-color: skyblue">
    <td>物品ID</td>
    <td>物品名称</td>
    <td>物品价格</td>
    <td>物品类型</td>
<%--    <td>物品介绍</td>--%>
<%--    <td>图片路径</td>--%>
    <td>是否推荐</td>
    <td>发布时间</td>
    <td>操作</td>
</tr>
<c:forEach items="${shopping}" var="emp" varStatus="vs">
    <tr ${vs.count%2==1?"style='background-color:orange'" : "style='background-color:white'"}>
        <td>${emp.wareid}</td>
        <td>${emp.warename}</td>
        <td>${emp.wareprice}</td>
        <td>${emp.waretype}</td>
<%--        <td>${emp.wareintro}</td>--%>
<%--        <td>${emp.wareimage}</td>--%>
        <td><c:set var="wareif" value="${emp.wareif}"></c:set>
            <c:choose>
                <c:when test="${wareif==1}">是</c:when>
                <c:otherwise>否</c:otherwise>
            </c:choose>
        </td>
        <td>${emp.dattime}</td>
        <td><a href="ControlServlet?flag=2&wareid=${emp.wareid}" style="text-decoration:none">查看详情</a></td>
    </tr>
</c:forEach>
</table>
</body>
</html>
