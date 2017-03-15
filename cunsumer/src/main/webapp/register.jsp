<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<form action="UserController/testInsert.do" method="post">
	<table>
		<tr>
			<td>姓名</td>
			<td><input name="uname"/></td>
		</tr>
		<tr>
			<td><input type="submit" value="注册"/></td>
			<td><input type="reset" value="重写"/></td>
		</tr>
	</table>
	</form>
</body>
</html>