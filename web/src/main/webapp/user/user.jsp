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
	<script type="text/html" id="bar1">
	<div class="layui-btn-group">
	<button onclick="add()" class="layui-btn">增加</button>
	<button onclick="dels()" class="layui-btn">删除</button>
	</div>
</script>
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
	var table;
	function add(){
		layer.open({
			  type: 2,
			  title:"添加用户",
			  offset: '100px',
			  moveOut:true,
			  area: ['400px', '450px'],
			  content: 'user/addjsp.do',
			  end:function(){
				  table.reload('dg');
			  }
			});
	}
	function edit(id){
		layer.open({
			  type: 2,
			  title:"修改用户",
			  offset: '100px',
			  moveOut:true,
			  area: ['400px', '450px'],
			  content: 'user/editjsp.do?id='+id,
			  end:function(){
				  table.reload('dg');
			  }
			});
	}
	function dels(){
		var checkStatus = table.checkStatus('dg');
		if(checkStatus.data.length==0){
			layer.alert('请先选择要删除的数据行！', {icon: 2});
			return ;
		}
		var ids = '';
		for(var i=0;i<checkStatus.data.length;i++){
			ids=ids+checkStatus.data[i].id+",";
		}
		$.post("user/dels.do",{ids:ids},function(data){
			if(data=="suc"){
				layer.msg("删除成功!",function(){
					table.reload('dg');
				});
			}else{
				layer.msg("删除失败!");
			}
		});
	}
		layui.use('table', function() {
			table = layui.table;
			table.render({
				elem : '#dg',
				defaultToolbar:[],
				cellMinWidth: 100,
				toolbar:"#bar1",
				url : 'user/getAllByPage.do',
				method:"post",
				page : true,
				cols : [[{
					field : 'id',
					title : 'id',
					type:'checkbox'
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
				}, {
					field : '_operate',
					title : '操作',
					toolbar:"#bar2"
				} ] ]
			});
			table.on('tool(dgtool)', function(obj){
				 var id = obj.data.id;
				 var layEvent = obj.event;
				  if(layEvent === 'edit'){
					  edit(id);
				  } else if(layEvent === 'del'){
					  layer.confirm('真的删除行么', function(index){
						  $.post("user/del.do",{id:id},function(data){
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
