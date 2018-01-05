<%@ page import="com.exercise.domain.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.exercise.domain.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>HZ1709</title>
		<link rel="stylesheet" href="css/index_css.css">
	</head>
	<body>
		<div class="all">
			<jsp:include page="top.jsp"></jsp:include>
			<jsp:include page="middle.jsp"></jsp:include>
			<!--必买爆款-->
			<div>
				<img src="images/h5img/tittle1.png" alt="找不到图片。。" width="100%"/>
			</div>
			<!--商品列表-->
			<div>
				<%-- <c:if test="${pageBean.pageData.cid }"> --%>
				<c:forEach items="${pageBean.pageData }" var="product">
					<div class="element">
						<img src="images/${product.pimage }" onclick ="location.href='${pageContext.request.contextPath }/servletProductDetail?pid=${product.pid }'" alt="找不到图片。。" >
						<p>${product.pname }<br />店铺价格：￥${product.shop_price }</p>
					</div>
				</c:forEach>
				<%-- </c:if> --%>
			</div>
			<div class="clear"></div>
			<!-- 
			int currentPage;//当前页面
			int productCount;//页面显示条数
			int productTotalCount;//记录总数
			int totalPage;//总页数
			 -->
			<div style="text-align:center">
				<ul id="pageUl">
					<c:if test="${pageBean.currentPage==1 }">
						<li>首页</li>
					</c:if>
					<c:if test="${pageBean.currentPage!=1 }">
						<li><a href="${pageContext.request.contextPath }/servletShowAllProduct?currentPage=${pageBean.currentPage-1}">上一页</a></li>
					</c:if>
					<c:forEach begin="1" end="${pageBean.totalPage }" var="page">
						 <c:if test="${pageBean.currentPage==page }">
						 	<li id="pageLi">${page }</li>
						 </c:if>
						 <c:if test="${pageBean.currentPage!=page }">
						 	<li><a href="${pageContext.request.contextPath }/servletShowAllProduct?currentPage=${page}">${page }</a></li>
						 </c:if>
					</c:forEach>
					<c:if test="${pageBean.currentPage!=pageBean.totalPage }">
						<li><a href="${pageContext.request.contextPath }/servletShowAllProduct?currentPage=${pageBean.currentPage+1}">下一页</a></li>
					</c:if>
					<c:if test="${pageBean.currentPage==pageBean.totalPage }">
						<li>尾页</li>
					</c:if>		
				</ul>
			</div>
			<%-- <c:forEach items="${productList }" var="product">
				<c:if test="'1'.equals(${product.cid })">
					<div class="element">
						<img alt="找不到图片。。" src="${product.pimage }">
						<p>${product.pname }<br />店铺价格：￥${product.shop_price }</p>
					</div>
				</c:if>
			</c:forEach> --%>
			
			
			 <!--生活电器-->
			<div>
				<img src="images/h5img/tittle2.png" alt="找不到图片。。" width="100%"/>
			</div>
			
			<jsp:include page="bottom.jsp"></jsp:include>
		</div>
	</body>
</html>
