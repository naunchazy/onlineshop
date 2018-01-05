<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>shoppingcart</title>
<script type="text/javascript" src="js/jquery-1.8.3.js" ></script>
<link rel="stylesheet" href="css/shoppingcart_css.css">
<script>
	//购物车商品的删除
	function deleteSCRecord(pid){
		var result=confirm("确定删除商品吗?");
		if(result){
			location.href="${pageContext.request.contextPath }/servletDelSCRecord?pid="+pid;
		}
	}
	//jQuery实现删除选框的全选和全不选
	$(function(){
		$("#allDel").change(
			function(){
				$(".delOne").attr("checked",this.checked);
			}
		);				
	});
	//只要有一个选框不为true，则全选框也并不为true
	//只要所有的选框都变成选中，则全选也为true
	window.onload=function(){
		var allDel=document.getElementById("allDel");//得到全选标签
		var delBoxes=document.getElementsByName("delOne");
		
		for(var i=0;i<delBoxes.length;i++){
			delBoxes[i].onchange=function(){
				for(var i=0;i<delBoxes.length;i++){
					var flag=true;
					if(!delBoxes[i].checked){
						flag=false;
						break;
					}
				}
				if(flag){
					allDel.checked=true;
				}else{
					allDel.checked=false;
				}
			}
		}
	}
</script>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<hr/>
<h3>我的购物车</h3>
<c:if test="${empty myCart }">
	<p>${message==null?"":message }</p>
	<a href="${pageContext.request.contextPath }/servletShowAllProduct">前往添加商品</a>	
</c:if>	
<c:if test="${!empty myCart }">
<c:if test="${!empty tips }">
	<p align="right" style="color:red">${tips==null?"":tips }</p>
</c:if>
<c:if test="${!empty tip }">
	<p align="right" style="color:red">${tip==null?"":tip }</p>
</c:if>
<table width="100%" cellpadding="0px" cellspacing="0px" border="1px">
<!-- 表单提交到结算ServletSettlement -->
<form action="${pageContext.request.contextPath }/servletSettlement" method="post">
	<tr>
		<td colspan="7" align="left">
			<input type="checkbox" name="allDel" id="allDel"/>全选/全不选&nbsp;&nbsp;&nbsp;
			<!-- <input type="submit" value="删除"/> -->
		</td>
	</tr>
	<tr>
			<td style="text-align: center">序号</td>
			<td align="center">图片</td>
			<td align="center">名称</td>
			<td align="center">价格</td>
			<td align="center">数量</td>
			<td align="center">编辑</td>
			<td align="center">删除</td>
	</tr>
	<!-- 获得商品和其数量的Map,遍历打印出购物车详情 -->
	
	<c:forEach items="${myCart }" var="entry" varStatus="vs">
			<tr align="center">
				<!-- 商品序号 -->			
				<td>
					<input type="checkbox" class="delOne" name="delOne" value="${entry.key.pid }"/>&nbsp;&nbsp;
					${vs.count }
					</td>
				<!-- 商品图片 -->
				<td>
					<img src="${pageContext.request.contextPath }/images/${entry.key.pimage }" width="40px" height="45px"/>
				</td>
				<!-- 商品名称 -->
				<td>${entry.key.pname }</td>
				<!-- 商品门店价格 -->
				<td>${entry.key.shop_price }</td>
				<!-- 商品数量 -->
				<td>${entry.value }</td>
				<!-- 商品的编辑选框-加减数量-->
				<td>
					<a href="${pageContext.request.contextPath }/servletEditSCCount?pid=${entry.key.pid }&count=${entry.value-1 }" >-</a>&nbsp;&nbsp;&nbsp;1&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/servletEditSCCount?pid=${entry.key.pid }&count=${entry.value+1 }">+</a>
				</td>
				<!-- 商品的删除链接 --><!-- 要将pid转给操作的Servlet -->
				<td align="center">
					<a href="javascript:void(0)" onclick="deleteSCRecord('${entry.key.pid}')">删除</a>
				</td>
			</tr>
	</c:forEach>
	<tr>
		<td colspan="7" align="left">
			<!-- 商品的结算 -->
			<input class="settlement" type="submit" value="前往结算>>">
		</td>
	</tr>
</form>
</table>
</c:if>
<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>