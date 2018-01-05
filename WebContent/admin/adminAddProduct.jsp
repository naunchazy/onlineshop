<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3 align="center">添加商品</h3>
	<form action="${pageContext.request.contextPath }/adminAddProductServlet" method="post">
		<table width="600px" align="center" cellpadding="0px" cellspacing="0px" border="1px">
			<!-- <caption>添加商品</caption> -->
			<tr>
				<td align="center">商品名称</td>
				<td>
					<input type="text" name="pname"/>
				</td>
			</tr>
			<tr>
				<td align="center">商场价格</td>
				<td>
					<input type="text" name="market_price"/>
				</td>
			</tr>
			<tr>
				<td align="center">门店价格</td>
				<td>
					<input type="text" name="shop_price"/>
				</td>
			</tr>
			<tr>
				<td align="center">商品图片</td>
				<td>
					<input type="file" name="pimage"/>
				</td>
			</tr>
			<tr>
				<td align="center">所属分类</td>
				<td>
					<select name="cid">
						<c:forEach items="${categorys }" var="category">
						<option value="${category.cid }">${category.cname }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="center">商品描述</td>
				<td>
					<textarea cols="30" rows="10" name="pdesc"></textarea>
				</td>
			</tr>
			<tr>
				<td align="center">是否热门</td>
				<td>
					<select  name="is_hot">
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="提交" >
					<input type="reset" value="重置" >
					<button type="button" onclick="history:go(-1)" >返回</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>