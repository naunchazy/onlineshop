<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<hr>
	<h2>登录页面</h2>
	<form action="${pageContext.request.contextPath }/servletAutoLogin" method="post">
		<table bordercolor="#ccc">
			<tr>
				<td colspan="2" style="color:red">${message==null?"":message }</td>
			</tr>
			<tr>
				<td>用户名</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="checkbox" name="autoLogin" value="al">自动登录</td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<input type="submit" value="登录">
					<input type="reset" value="重置">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>