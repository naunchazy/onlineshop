<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.exercise.domain.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>middle</title>
<link rel="stylesheet" href="css/index_css.css">
</head>
<body>
	<!--Ad-->
			<div>
				<img src="images/h5img/ad.png" style="display: none;" id="ad" width="100%"/>
				<input type="button" value="关闭广告" style="display: none;" id="adbt"/>
			</div>
	<!--导航条-->
	<div class="lead">
		<div class="lead_detail">
			<a href="${pageContext.request.contextPath }/servletShowAllProduct"
				title="首页">首页</a> <a href="#" title="手机">手机</a> <a href="#"
				title="家用电器">家用电器</a> <a href="#" title="鞋帽箱包">鞋帽箱包</a>
		</div>
	</div>
	<!--banner-->
	<div>
		<img src="images/h5img/b1.jpg" alt="找不到图片。。"  id="banner" width="100%" />
	</div>
</body>
<script>
		//轮播图
		var timer;
		var i=1;//一定要将i写在showAd方法外面。不然每次都只会显示第一张图片
		window.onload=function(){
			setInterval(changeBanner,3000);
			timer=setTimeout(showAd,5000); 
		}
		function changeBanner(){
			var banner=document.getElementById("banner");
			banner.src="images/h5img/b"+i+".jpg";
			i++;
			if(i==3){
				i=1;
			}
		}
		
		//广告
		var ad=document.getElementById("ad");
		var adbt=document.getElementById("adbt");
		function showAd(){
			ad.style.display="block";
			adbt.style.display="block";
			clearTimeout(timer);
			timer=setTimeout(hiddenAd,10000);
		}
		function hiddenAd(){
			ad.style.display="none";
			adbt.style.display="none";
			clearTimeout(timer);
		}
		document.getElementById("adbt").onclick=function(){
			ad.style.display="none";
			adbt.style.display="none";
			clearTimeout(timer);
		}
</script>
</html>
