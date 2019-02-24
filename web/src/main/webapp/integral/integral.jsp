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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息管理系统</title>
<script type="text/javascript"
	src="<%=basePath%>/scripts/jquery/jquery-1.7.1.js"></script>
 <link rel="stylesheet" href="<%=basePath %>/layui/css/layui.css" media="all">
 <script src="<%=basePath %>/layui/layui.all.js"></script>
</head>
<body>
	<table class="layui-hide" id="dg" lay-filter="dgtool"></table>
	<script type="text/html" id="bar2">
	<div class="layui-btn-group">
		<button lay-event="edit" class="layui-btn layui-btn-sm">
		<i class="layui-icon">&#xe642;</i>
		</button>
		<button lay-event="del" class="layui-btn layui-btn-sm">
		<i class="layui-icon">&#xe640;</i>
		</button>
	</div>
	</script>
	<script type="text/javascript">
		layui.use('table', function() {
			var table = layui.table;
			table.render({
				elem : '#dg',
				even: true,
				cellMinWidth: 100,
				url : '<%=basePath%>/integral/getAllByPage.do',
				method : "post",
				page : true,
				cols : [ [ {
					field : 'id',
					title : 'id',
					type : 'checkbox'
				}, {
					field : 'weixinName',
					title : '用户名',
				}, {
					field : 'integral',
					title : '积分',
				}, {
					field : '_operate',
					title : '操作',
					toolbar : "#bar2"
				} ] ]
			});
		});
	</script>
</body>
</html>
