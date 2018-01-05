<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/dtree.css">
</head>
<body>
	<table>
		<tr>
			<td>
				<div class="dTree">
					<a href="javascript:d.openAll()">展开分项</a>|<a href="javascript:d.closeAll()">关闭分项</a>
					<script type="text/javascript" src="../js/dtree.js"></script>
					<script type="text/javascript">
						d=new dTree('d');
						d.add("1",-1,"商城后台管理");
						d.add("11","1","分类管理");
						d.add("111","11","分类详情","${pageContext.request.contextPath}/adminCategoryServlet","","right");
						d.add("12","1","商品管理","");
						d.add("121","12","商品详情","${pageContext.request.contextPath}/adminShowAllProductServlet","","right");
						
						document.write(d);
					</script>
					
				</div>
			</td>
		</tr>
	</table>
</body>
</html>