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
	<script type="text/javascript">
	var table;

	layui.use('table', function() {
		table = layui.table;
		table.render({
			elem : '#dg',
			url : '<%=basePath%>/user/getAllByPage.do',
			cols : [ [ {
				type:'checkbox'
			},{
				field : 'id',
				title : 'id',
			}, {
				field : 'userName',
				title : '账号',
			}, {
				field : 'name',
				title : '用户名',
			}, {
				field : 'tel',
				title : '电话',
			}, {
				field : 'email',
				title : '邮箱',
			}] ]
		});
	});
	</script>
</body>
</html>
