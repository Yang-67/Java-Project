<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <script src="webjars/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript">
       function show_img(id){
           alert(id);
           $("#dialog").show(99);
       }
    </script>
</head>
<body>

<table cellspacing="0" border="1" >
    <tr style="background-color: skyblue">
        <td>文件ID</td>
        <td>文件名</td>
        <td>下载权限</td>
        <td>操作</td>
        <td>预览</td>
    </tr>
    <c:forEach items="${pb.emps}" var="emp" varStatus="vs">
        <tr ${vs.count%2==1?"style='background-color:orange'" : "style='background-color:white'"}>
            <td>${emp.doc_id}</td>
            <td>${emp.doc_name}</td>
            <td><c:set var="authority" value="${emp.doc_authority}"></c:set>
                <c:choose>
                    <c:when test="${authority==0}">公开下载</c:when>
                    <c:otherwise>非公开下载</c:otherwise>
                </c:choose>
            </td>
            <td>更改下载权限</td>
            <td><button id=${emp.doc_name} onclick="show_img(id)">预览</button></td>
        </tr>
        <div style="width:200px;height:200px;border:solid 1px #000;position:absolute;right:10px;top:100px; line-height:200px;text-align:center;display:none" id="dialog">
            我是浮动的窗口
        </div>
    </c:forEach>
</table>
<div class="page">
    <a href="${pageContext.request.contextPath}/MyFileServlet?currentPage=${pb.currentPage==1?1:pb.currentPage-1}">&lt;&lt;上一页</a>
    &nbsp;&nbsp;第${pb.currentPage}页/共${pb.totalPages}页&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/MyFileServlet?currentPage=${pb.currentPage==pb.totalPages?pb.totalPages:pb.currentPage+1}">下一页&gt;&gt;</a>
</div>


</body>
</html>