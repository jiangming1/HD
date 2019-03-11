<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息管理系统</title>
<script type="text/javascript" src="scripts/jquery/jquery-1.7.1.js"></script>
 <link rel="stylesheet" href="layui/css/layui.css" media="all">
 <script src="layui/layui.all.js"></script>
</head>
<body>
	<table class="layui-hide" id="dg"></table>
	<script type="text/javascript">
		layui.use('table', function() {
			var table = layui.table;
			table.render({
				elem : '#dg',
				cellMinWidth: 100,
				where: { weixinId:<%=request.getParameter("weixinId")%>},
				url : 'weixinUser/record.do',
				method : "post",
				cols : [ [ {
					field : 'id',
					title : 'id',
					type : 'checkbox'
				}, {
					field : 'integral',
					title : '积分',
				}, {
					field : 'memo',
					title : '描述',
				} ] ]
			});
			
		});
	</script>
</body>
</html>
