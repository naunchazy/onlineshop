<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addSuccess</title>
		<script>
			var time = 3;
			var timer;
			window.onload = function(){
				desc();
			}
			function desc(){
				//前提：time>0,把time-1，然后显示在span中.再开启定时器，间隔一秒后再次执行该方法
				if(time>=0){
					var sp1 = document.getElementById("sp1");
					sp1.innerHTML = time;
					time--;
					//设置定时器，timer就表示该定时器
					timer = setTimeout(desc,1000);
				}else{
					location.href = "${pageContext.request.contextPath}/servletShowAllProduct";
					/* location.href = "${pageContext.request.contextPath}/servletProductDetail"; */
				}
			}
			function clearTimer(){
				//清除定时器
				clearTimeout(timer);
				location.href = "${pageContext.request.contextPath}/servletShowAllProduct";
				/* location.href = "${pageContext.request.contextPath}/servletProductDetail"; */
			}

	</script>
</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<p>商品添加成功!</p>
	<span style="color: red;" id="sp1">5</span>秒后返回首页~~~
	<input type="button" onclick="clearTimer()" value="立即返回" />
	<br>
	<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>