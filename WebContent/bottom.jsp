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
 	<!--网站承诺	-->
	<div>
		<img src="images/h5img/bottom.png" alt="找不到图片。。" width="100%"/>
	</div>
	<!--链接-->
	<div class="link1">
		<a href="#" title="关于我们">关于我们</a>
		<a href="#" title="联系我们">联系我们</a>
		<a href="#" title="招贤纳士">招贤纳士</a>
		<a href="#" title="法律声明">法律声明</a>
		<a href="#" title="友情链接">友情链接</a>
		<a href="#" title="支付方式">支付方式</a>
		<a href="#" title="配送方式">配送方式</a>
		<a href="#" title="广告声明">广告声明</a>
		<a href="${pageContext.request.contextPath }/admin/index.jsp" title="后台登陆">后台登陆</a>
	</div>
	<!--版权信息-->
	<div id="copy">
		Copyright&nbsp;&copy;2010-2017&nbsp;千锋商城&nbsp;版权所有
	</div>
</body>
</html>