<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload=function(){
		var cid="${editProduct.cid}";
		var at=document.getElementById("at");
		var option=at.getElementsByTagName("option");
		for(var i=0;i<option.length;i++){
			if(option[i].value==cid){
				option[i].selected=true;
			}
		}
		var is_hot="${editProduct.is_hot}";
		var ih=document.getElementById("ih");
		var option_ih=ih.getElementsByTagName("option");
		for(var i=0;i<option_ih.length;i++){
			if(is_hot==option_ih[i].value){
				option_ih[i].selected=true;
			}
		}
	}
</script>
</head>
<body>
	<h3 align="center">编辑商品</h3>
	<form action="${pageContext.request.contextPath }/adminEditorProductServlet?pid=${editProduct.pid}" method="post">
		<table width="600px" align="center" cellpadding="0px" cellspacing="0px" border="1px">
			<!-- <caption>添加商品</caption> -->
			<tr>
				<td align="center">商品名称</td>
				<td>
					<input type="text" name="pname" value="${editProduct.pname }"/>
				</td>
			</tr>
			<tr>
				<td align="center">商场价格</td>
				<td>
					<input type="text" name="market_price" value="${editProduct.market_price }"/>
				</td>
			</tr>
			<tr>
				<td align="center">门店价格</td>
				<td>
					<input type="text" name="shop_price" value="${editProduct.shop_price }"/>
				</td>
			</tr>
			<tr>
				<td align="center">商品图片</td>
				<td>
					<input type="file" name="pimage" value="${editProduct.pimage }"/>
				</td>
			</tr>
			<tr>
				<td align="center">所属分类</td>
				<td id="at">
					<select name="cid">
						<c:forEach items="${editCatagory }" var="catagory">
						<option value="${catagory.cid }">${catagory.cname }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="center">商品描述</td>
				<td>
					<textarea cols="30" rows="10" name="pdesc" >${editProduct.pdesc }</textarea>
				</td>
			</tr>
			<tr>
				<td align="center">是否热门</td>
				<td id="ih">
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