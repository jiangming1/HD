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
<script type="text/javascript"
	src="scripts/jquery/jquery-1.7.1.js"></script>
 <link rel="stylesheet" href="layui/css/layui.css" media="all">
 <script src="layui/layui.all.js"></script>
</head>
<body>
	<table class="layui-hide" id="dg" lay-filter="dgtool"></table>
	<script type="text/html" id="bar2">
	<div class="layui-btn-group">
		<button lay-event="record" class="layui-btn layui-btn-sm">积分记录</button>
		<button lay-event="del" class="layui-btn layui-btn-sm">删除</button>
	</div>
	</script>
	<script type="text/javascript">
	function record(weixinId){
		layer.open({
			  type: 2,
			  title:"积分记录",
			  moveOut:true,
			  area: ['350px', '250px'],
			  offset: '100px',
			  content: 'weixinUser/record.jsp?weixinId='+weixinId
			});
	}
		layui.use('table', function() {
			var table = layui.table;
			table.render({
				elem : '#dg',
				cellMinWidth: 100,
				url : 'weixinUser/getAllByPage.do',
				method : "post",
				page : true,
				cols : [ [ {
					field : 'id',
					title : 'id',
					type : 'checkbox'
				}, {
					field : 'nickname',
					title : '昵称',
				},{
					field : 'name',
					title : '姓名',
				}, {
					field : 'integral',
					title : '积分',
				},  {
					field : '_operate',
					title : '操作',
					toolbar : "#bar2"
				} ] ]
			});
			table.on('tool(dgtool)', function(obj){
				 var id = obj.data.id;
				 var layEvent = obj.event;
				  if(layEvent === 'record'){
					  record(id);
				  } else if(layEvent === 'del'){
					  layer.confirm('真的删除行么', function(index){
						  $.post("expert/del.do",{id:id},function(data){
							  if(data=="suc"){
								  obj.del();
								  layer.close(index);
							  }else{
								  layer.msg("删除失败!");
								  layer.close(index);
							  }
						  });
					  });
				  }
			});
		});
	</script>
</body>
</html>
