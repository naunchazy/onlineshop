<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情页</title>
<link rel="stylesheet" href="css/productDetail_css.css">
<script>
	function addSC(){
		var count=document.getElementById("count").value;
		/* location.href="${pageContext.request.contextPath}/servletAddShoppingcart?pid=${productDetail.pid }&count="+count; */
		
		location.href="${pageContext.request.contextPath}/servletAddShoppingcart?pid=${productDetail.pid }&count="+count;
		alert("商品添加成功！");
	}
	function byNow(){
		var count=document.getElementById("count").value;
		location.href="${pageContext.request.contextPath}/servletByNow?pid=${productDetail.pid }&count="+count;
	}
</script>
</head>
<body>
	<div class="all">
		<jsp:include page="top.jsp"></jsp:include>
		<hr>
		<h2>商品详情</h2>
			<div class="left" style="width:500px">
				<!-- 商品详情从ServletProductDetail得来 -->
				<img
				src="${pageContext.request.contextPath }/images/${productDetail.pimage }"
				width="300px" height="300px">
			</div>
			<div class="right" style="width:500px" >
				<div class="">${productDetail.pname }</div>
				<div >${productDetail.pdesc }</div>
				<div class="market_price"><p>市场价格：${productDetail.market_price }</p></div>
				<div class="">店铺价格：${productDetail.shop_price }</div>
				<!-- 数量 count-->
				<br />
				<div>数量<input style="text-align:center" type="text" size="3" onchange="alterCount(this)" name="count" id="count" value="1"></div>
				<div width="100%">
					<input type="button" class="mybtn" value="加入购物车"
						onclick="addSC()">&nbsp;&nbsp;&nbsp;
					<input type="button" class="mybtn" onclick="byNow()" value="立即购买" >
				</div>
			</div>
		<div class="clear"></div>
		<jsp:include page="bottom.jsp"></jsp:include>
	</div>
</body>
</html>