<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>SCT-后台系统</title>
<link href="style/authority/main_css.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="scripts/jquery/jquery-1.7.1.js"></script>
<link rel="stylesheet" href="layui/css/layui.css" media="all">
<script src="layui/layui.all.js"></script>
</head>
<body>
	<div id="top">
		<div id="top_logo">
			<img alt="logo" src="images/common/logo.jpg"
				width="274" height="49" style="vertical-align:middle;">
		</div>
		<div id="top_links">
			<div id="top_op">
				<ul>
					<li><img src="images/common/user.jpg">： <span>admin</span></li>
					<li><img alt="今天是" src="images/common/date.jpg">：<span id="day_day"></span></li>
				</ul>
			</div>
			<div id="top_close">
				<a href="javascript:void(0);" onclick="logout();" target="_parent">
					<img src="images/common/close.jpg"
					style="position: relative; top: 10px; left: 25px;">
				</a>
			</div>
		</div>
	</div>
	<div id="side">
		<jsp:include page="/left.jsp"></jsp:include>
		<jsp:include page="/menu.jsp"></jsp:include>
	</div>
	<div id="top_nav">
		<span id="here_area">当前位置：系统&nbsp; > &nbsp;系统介绍</span>
	</div>
 	<div id="main">
 	<iframe id="rightMain" src="null.jsp" frameborder="no" scrolling="auto" width="100%" height="100%" allowtransparency="true">
 	</iframe>
	</div>
	<script type="text/javascript">
		$(function(){
			getDate();
		});

		/**获得当前日期**/
		function getDate() {
			var time = new Date();
			var myYear = time.getFullYear();
			var myMonth = time.getMonth() + 1;
			var myDay = time.getDate();
			document.getElementById("day_day").innerHTML = myYear + "."
					+ myMonth + "." + myDay;
		}

	</script>
</body>
</html>


