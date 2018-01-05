<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>register</title>
		<style>
			.error{
				color:red;
				font-size:15px;
			}
			
		</style>
		<script type="text/javascript" src="js/jquery-1.8.3.js" ></script>
		<script type="text/javascript" src="js/jquery.validate.js" ></script>
		<script type="text/javascript" src="js/messages_zh.js" ></script>
		<script>
			$(function(){
				$("#registerForm").validate({
					rules:{
						username:{
							required:true,
							minlength:2
						},
						password:{   
							required:true,
							minlength:5
						},
						repwd:{
							required:true,
							equalTo:$("input[name='password']")
						},
						email:{
							required:true,
							email:true
						},
						realname:{
							required:true,
							minlength:2
						},
						telephone:{
							required:true,
							number:true,
							rangelength:[7,11]
						}
					},
					messages:{
						username:{
							required:"用户名不能为空",
							minlength:"用户名长度至少2个字符"
						},
						password:{
							required:"密码不能为空",
							minlength:"密码长度至少3个字符"
						},
						repwd:{
							required:"确认密码不能为空",
							equalTo:"两次密码输入不一致"
						},
						email:{
							required:"邮箱不能为空",
							email:"邮箱格式不正确"
						},
						realname:{
							required:"真实姓名不能为空",
							minlength:"真实姓名至少2个字符"
						},
						telephone:{
							required:"电话号码不能为空",
							number:"电话号码必须为数字",
							rangelength:"电话号码输入不正确"
						}
					}				
				});
			});

		</script>
		<script type="text/javascript">
			$(function(){
				$("#username").blur(
					function(){
						$.get(
							"${pageContext.request.contextPath}/servletCheckDupUsername",
							{"username":this.value},
							function(data){
								if(data!=null){
									var val = data.username;
									$("#span1").html("用户名已被注册!");
								}else{
									$("#span1").html("用户名可以使用!");
								}	
							},
							"json"
						);
						
					}		
				);
				
				
			});
		</script>
	</head>
	<body>
		<jsp:include page="top.jsp"></jsp:include>	
		<hr>
		<h2 align="center">会员注册</h2>
		<form onsubmit="return check()" id="registerForm" action="${pageContext.request.contextPath }/servletRegister" method="post">
			<!-- 注册重名 -->
			<!-- <div align="center"><span id="span1" style="color:red"></span></div> -->
			<table align="center" border="1px" cellpadding="0px" cellspacing="0px" width="450px">
				<!-- <caption>会员注册</caption> -->
				<!-- <tr align="center"><td colspan="2"><span id="span1"></span></td></tr> -->
				<tr id="tr1" onmouseover="changeColor(this.id,'over')" onmouseout="changeColor(this.id,'out')">
					<td width="20%" align="center">用户名</td>
					<td><input type="text" name="username" id="username"></td>
				</tr>
				<tr id="tr2" onmouseover="changeColor(this.id,'over')" onmouseout="changeColor(this.id,'out')">
					<td width="20%" align="center">密码</td>
					<td><input type="password" name="password" id="password"></td>
				</tr>
				<tr id="tr3" onmouseover="changeColor(this.id,'over')" onmouseout="changeColor(this.id,'out')">
					<td width="20%" align="center">确认密码</td>
					<td><input type="password" name="repwd"></td>
				</tr>
				<tr id="tr4" onmouseover="changeColor(this.id,'over')" onmouseout="changeColor(this.id,'out')">
					<td width="20%" align="center">真实姓名</td>
					<td><input type="text" name="realname"></td>
				</tr>
				<tr id="tr5" onmouseover="changeColor(this.id,'over')" onmouseout="changeColor(this.id,'out')">
					<td width="20%" align="center">邮箱</td>
					<td><input type="text" name="email"></td>
				</tr>
				<tr id="tr6" onmouseover="changeColor(this.id,'over')" onmouseout="changeColor(this.id,'out')">
					<td width="20%" align="center">电话</td>
					<td><input type="text" name="telephone"></td>
				</tr>
				<tr align="center">
					<td colspan="2">
						<input type="submit" value="注册">
						<input type="reset" value="重置">
					</td>
				</tr>
			</table>
			<div align="center" style="color: #666; font-size: 12px;">网站承诺:用户信息绝不外泄！请放心填写。</div>
		</form>
	</body>
	<script>
	//高光显示
	function changeColor(id,string){
		var v=document.getElementById(id);
		if(string=="over"){
			v.style.backgroundColor="#ffc";
		}
		if(string=="out"){
			v.style.backgroundColor="white";
		}
	}
	</script>
</html>