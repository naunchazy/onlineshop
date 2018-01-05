<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.exercise.domain.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/index_css.css">
</head>
<body>
	<c:if test="${empty user }">
		<div>
			<!--logo-->
			<div id=logo_left>
				<img src="images/h5img/mylogo.png" alt="找不到图片。。" />
			</div>
			<div id=logo_right>
				<a href="${pageContext.request.contextPath }/servletShowAllProduct" title="首页">首页</a>
				<a href="login.jsp" title="登录">登录</a> 
				<a href="register.jsp" title="注册">注册</a>
				<a href="${pageContext.request.contextPath }/servletShowShoppingcart" title="购物车">购物车${size+0 }件</a>
				<%-- <a href="${pageContext.request.contextPath }/servletShowShoppingcart" title="购物车">购物车</a> --%>
			</div>
			<div class="clear"></div>
		</div>
	</c:if>
	<c:if test="${!empty user }">
		<div>
			<!--logo-->
			<div id=logo_left>
				<img src="images/h5img/mylogo.png" alt="找不到图片。。" />
			</div>
			<div id=logo_right>
				欢迎您，${user.username }!&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath }/servletShowAllProduct" title="首页">首页</a>&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath }/servletShowShoppingcart" title="购物车">购物车${size+0 }件</a>&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath }/servletCleanSession" title="退出">退出</a>
			</div>
			<div class="clear"></div>
		</div>
	</c:if>
</body>
</html>