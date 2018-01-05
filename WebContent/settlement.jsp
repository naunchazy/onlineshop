<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order</title>
<link rel="stylesheet" href="css/settlement_css.css">
</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>	
	<hr>
	<h2 >订单详情</h2>
	<table  width="100%" cellpadding="0px" cellspacing="0px" border="1px">
			<tr>
				<td colspan="6" align="left">订单号:${cartOrder[0].oid }</td>
			</tr>
			<tr>
				<td colspan="6" align="left">用户id:${cartOrder[0].uid }</td>
			</tr>
		<tr align="center">
			<td>序号</td>
			<td>商品id</td>
			<td>商品图片</td>
			<td>商品名</td>
			<td>商品价格</td>
			<td>商品数量</td>
		</tr>
		<c:forEach items="${cartOrder }" var="order" varStatus="vs">
			<tr align="center">
				<td>${vs.count }</td>
				<td>${order.pid }</td>
				<td><img src="${pageContext.request.contextPath }/images/${order.pimage }"></td>
				<td>${order.pname }</td>
				<td>${order.shop_price }</td>
				<td>${order.count }</td>
			</tr>
		</c:forEach>
		<tr>
				<td colspan="6" align="right">商品总数:${countSum }</td>
		</tr>
		<tr>
				<td colspan="6" align="right">商品总价:${sumPrice }</td>
		</tr>
		<tr>
				<td colspan="6" align="right"><a href=
				"${pageContext.request.contextPath }/servletPay?oid=${cartOrder[0].oid}&uid=${cartOrder[0].uid}"
				 rel="nofollow" id="Buy" role="button">去支付>></a></td>
		</tr>
	</table>
</body>
</html>