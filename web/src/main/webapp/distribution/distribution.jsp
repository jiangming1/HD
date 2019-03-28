<%@ page language="java" pageEncoding="UTF-8"%>
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
<title>现状分布管理</title>
<script type="text/javascript" src="scripts/jquery/jquery-1.7.1.js"></script>
<link rel="stylesheet" href="layui/css/layui.css" media="all">
<script src="layui/layui.all.js"></script>
</head>
<body>
	<div class="layui-btn-group">
		<button type="button" class="layui-btn" id="upload">
			<i class="layui-icon">&#xe67c;</i>上传
		</button>
	</div>
	<table class="layui-hide" id="dg"></table>
	<script type="text/javascript">
		var table;
		layui.use('table', function() {
			table = layui.table;
			table.render({
				elem : '#dg',
				cellMinWidth : 100,
				defaultToolbar : [],
				url : 'distribution/getAll.do',
				method : "post",
				cols : [ [ {
					field : 'id',
					title : '序号',
					type : 'numbers'
				}, {
					field : 'distribution',
					title : '名称',
				}, {
					field : 'num',
					title : '患病人数',
				} , {
					field : 'memo',
					title : '备注',
				} , {
					field : 'flag',
					title : '类型',
					templet: function(d){
						if(d.flag == 1)
							return "现状分布";
						else if(d.flag == 2)
							return "年龄分布";
				      }
				} ] ]
			});
		});
		layui.use('upload', function() {
			var upload = layui.upload;
			upload.render({
				elem : '#upload',
				accept:'file',
				url : 'distribution/upload.do',
				done : function(res) {
					layer.alert("上传成功");
					table.reload('dg');
				},
				error : function() {
					layer.alert("上传失败");
				}
			});
		});
	</script>
</body>
</html>
