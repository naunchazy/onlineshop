<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.8.3.js" ></script>
<script>
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
<h2 style="text-align:center">商品类别表</h2>
<table width="100%" cellpadding="0px" cellspacing="0px" border="1px">
	<tr>
		<td colspan="5" align="right">
			<input type="checkbox" id="allDel"/>全选/全不选&nbsp;&nbsp;&nbsp;
			<input type="submit" value="删除"/>
		</td>
	</tr>
	<tr>
		<td align="center">序号</td>
		<td align="center">类别号</td>
		<td align="center">类别名</td>
		<td align="center">编辑</td>
		<td align="center">删除</td>
	</tr>
	<c:forEach items="${categorys }" var="category" varStatus="vs">
		<tr>
			<td align="center">${vs.count }</td>
			<td align="center">${category.cid }</td>
			<td align="center">${category.cname }</td>
			<td align="center">
				<a href="${pageContext.request.contextPath }/Servlet?cid="+${category.cid }>编辑</a>
			</td>
			<td align="center">
				<input type="checkbox" class="delOne" name="delOne" onclick="delSelected()" value="${category.cid }"/>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>