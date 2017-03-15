<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.id }</td>
				<td>${user.uname }</td>
				<td><a href="UserController/getUserById.do?id=${user.id }">查看</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>