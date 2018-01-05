<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>1709后台管理系统</title>
</head>
<frameset rows="12%,*,8%">
	<frame src="${pageContext.request.contextPath }/admin/adminTop.jsp"/>
	<frameset cols="15%,*">
		<frame src="${pageContext.request.contextPath }/admin/adminLeft.jsp"/>
		<frame src="${pageContext.request.contextPath }/admin/adminRight.jsp" name="right"/>
	</frameset>
	<frame src="${pageContext.request.contextPath }/admin/adminBottom.jsp"/>
</frameset>
</html>