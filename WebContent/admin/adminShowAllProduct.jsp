<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.exercise.domain.vo.Requirement" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>adminShowProduct</title>
</head>
<script>
	/*因为做的是批量删除，而非单一删除，
	所以需要用表单将要删除的多个pid参数传递给操作批量删除的Servlet，
	之前的单个删除便不再适用
	function deleteProduct(pid){
		var result=confirm("确定删除商品吗?");
		if(result){ 
			location.href="${pageContext.request.contextPath}/deleteProductServlet?pid="+pid;
		 }
	} */
	//商品的添加
	function addProduct(){
		location.href="${pageContext.request.contextPath}/adminShowCategoryServlet";
	}
	//搜索信息的回显
	window.onload=function(){
		var ih="${requirement.is_hot}";
		var is_hot=document.getElementById("is_hot");
		var options_ih=is_hot.getElementsByTagName("option");
		for(var i=0;i<options_ih.length;i++){
			if(options_ih[i].value==ih){
				options_ih[i].selected=true;
			}
		}
		var cid="${requirement.cid}";
		var c=document.getElementById("cid");
		var options_cid=c.getElementsByTagName("option");
		for(var i=0;i<options_cid.length;i++){
			if(options_cid[i].value==cid){
				options_cid[i].selected=true;
			}
		}
		//全选和全不选
		var allDel=document.getElementById("allDel");//得到全选标签
		var delBoxes=document.getElementsByName("delBox");
		allDel.onchange=function(){
			if(allDel.checked){/* 即全选为选中状态 */
				for(var i=0;i<delBoxes.length;i++){
					delBoxes[i].checked=true;
				}
			}
			if(!allDel.checked){/* 即全选为没选中状态 */
				for(var i=0;i<delBoxes.length;i++){
					delBoxes[i].checked=false;
				}
			}
		}
		//只要有一个选框不为true，则全选框也并不为true
		//只要所有的选框都变成选中，则全选也为true	
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
		/* 另一种【未做出】
		function delSelected(){
			var allDel=document.getElementById("allDel");//得到全选标签
			var delBoxes=document.getElementsByName("delBox");
			var flag=true;
			for(var i=0;i<delBoxes.length;i++){
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
		} */
	}
	function doReset(){
		location.href="${pageContext.request.contextPath}/adminResetSearchServlet";
	}
	
</script>
<body>
	<h2 style="text-align:center">商品列表</h2>
	<table width="100%" cellpadding="0px" cellspacing="0px" border="1px">
		<tr><!-- 条件查询提交的表单 -->
			<form action="${pageContext.request.contextPath }/adminSearchProductServlet" method="post">
				<td colspan="7" align="center">
					商品名称<input name="pname" type="text" size="10" value="${requirement.pname }">
					&nbsp;&nbsp;&nbsp;是否热门
					<select name="is_hot" id="is_hot">
						<option value="">---请选择---</option>
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
					&nbsp;&nbsp;&nbsp;所属分类
					<select name="cid" id="cid">
						<option value="">---请选择---</option>
						<c:forEach items="${categorys }" var="category">
							<option value="${category.cid }">${category.cname }</option>
						</c:forEach>
					</select>
					&nbsp;&nbsp;&nbsp;
					<button type="submit">搜索</button>
					&nbsp;&nbsp;&nbsp;
					<!-- <button type="reset">重置</button> -->
					<input type="reset" onclick="doReset()" value="重置">
				</td>
			</form>
		</tr>
		<tr>
			<td colspan="7" align="right">
				<button onclick="addProduct()">添加商品</button>
			</td>
		</tr>
		<!-- 批量删除需要提交的表单 -->
		<!-- 因为做的是批量删除，而非单一删除，所以需要用表单将要删除的多个pid参数传递给操作批量删除的Servlet，
		之前的单个删除便不再适用 -->
		<form action="${pageContext.request.contextPath }/adminDeleteSomeProductServlet" method="post">
		<tr>
			<td colspan="7" align="right">
				<input type="checkbox" name="allDel" id="allDel"/>全选/全不选&nbsp;&nbsp;&nbsp;
				<input type="submit" value="删除"/>
			</td>
		</tr>
		
		<tr>
			<td style="text-align: center">序号</td>
			<td align="center">图片</td>
			<td align="center">名称</td>
			<td align="center">门店价格</td>
			<td align="center">是否热门</td>
			<td align="center">编辑</td>
			<td align="center">删除</td>
		</tr>
		 <%-- <c:forEach items="${products }" var="product" varStatus="vs"> --%>
		 <c:forEach items="${adminFindProductsByPB.pageData }" var="product" varStatus="vs">
			<tr>
				<!-- 商品序号 -->
				<td align="center">${vs.count }</td>
				<!-- 商品图片 -->
				<td align="center">
					<img src="${pageContext.request.contextPath }/images/${product.pimage }" width="40px" height="45px"/>
				</td>
				<!-- 商品名称 -->
				<td align="center">${product.pname }</td>
				<!-- 商品门店价格 -->
				<td align="center">${product.shop_price }</td>
				<!-- 商品是否热门 -->
				<td align="center">${product.is_hot==1?"是":"否" }</td>
				<!-- 商品的编辑 --><!-- 要将pid转给操作编辑的Servlet,但在删除前需要做该商品信息的回显，所以先转给回显的Servlet，再由回显的jsp页面转给编辑的Servlet -->
				<td align="center">
					<a href="${pageContext.request.contextPath }/adminEditorShowProductServlet?pid=${product.pid }">编辑</a>
				</td>
				<!-- 商品的删除选框 --><!-- 要将pid转给操作删除的Servlet -->
				<td align="center">
					<input type="checkbox" name="delBox" onclick="delSelected()" value="${product.pid }"/>
					<%-- <a href="javascript:void(0)" onclick="deleteProduct('${product.pid}')">删除</a> --%>
				</td>
			</tr>
		</c:forEach> 
		<tr>
			<td colspan="7" align="center">
			<c:if test="${adminFindProductsByPB.currentPage==1 }">
				<span>首页</span>
			</c:if>
			<c:if test="${adminFindProductsByPB.currentPage!=1 }">
				<span><a href="${pageContext.request.contextPath }/adminShowAllProductServlet?currentPage=${adminFindProductsByPB.currentPage-1}">上一页</a></span>
			</c:if>
			<c:forEach begin="1" end="${adminFindProductsByPB.totalPage }" var="page">
				<c:if test="${adminFindProductsByPB.currentPage==page }">
					<span>${page }</span>
				</c:if>
				<c:if test="${adminFindProductsByPB.currentPage!=page }">
					<span><a href="${pageContext.request.contextPath }/adminShowAllProductServlet?currentPage=${page }">${page }</a></span>
				</c:if>
			</c:forEach>
			<c:if test="${adminFindProductsByPB.currentPage!=adminFindProductsByPB.totalPage }">
				<span><a href="${pageContext.request.contextPath }/adminShowAllProductServlet?currentPage=${adminFindProductsByPB.currentPage+1}">下一页</a></span>
			</c:if>
			<c:if test="${adminFindProductsByPB.currentPage==adminFindProductsByPB.totalPage }">
				<span>尾页</span>
			</c:if>
			</td>
		</tr>
		</form>
	</table>
</body>
</html>