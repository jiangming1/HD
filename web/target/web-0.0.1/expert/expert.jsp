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
<title>专家管理</title>
<script type="text/javascript"
	src="scripts/jquery/jquery-1.7.1.js"></script>
 <link rel="stylesheet" href="layui/css/layui.css" media="all">
 <script src="layui/layui.all.js"></script>
</head>
<body>
	<div  class="layui-form-item">
		<button onclick="add()" class="layui-btn">增加</button>
		<button onclick="dels()" class="layui-btn">删除</button>
		<button onclick="select()" class="layui-btn" style="margin-left:190px;">搜索</button>
		<input id="select" type="text" placeholder="搜索" class="layui-input" style="width:160px;margin-top: -37px;margin-left:160px;">
	</div>
	<table class="layui-hide" id="dg" lay-filter="dgtool"></table>
<script type="text/html" id="bar2">
	<div class="layui-btn-group">
		<button lay-event="edit" class="layui-btn layui-btn-sm">修改</button>
		<button lay-event="del" class="layui-btn layui-btn-sm">删除</button>
	</div>
</script>
	<script type="text/javascript">
	var table;
	function add(){
		layer.open({
			  type: 2,
			  title:"添加专家",
			  moveOut:true,
			  area: ['350px', '250px'],
			  offset: '100px',
			  content: 'expert/add.jsp'
			});
	}
	function select(){
		var name = $("#select").val();
		table.reload("dg",{
			where: { name:name},
			url : "expert/getAllByName.do",});
	}
	function edit(id){
		layer.open({
			  type: 2,
			  title:"修改专家",
			  moveOut:true,
			  area: ['350px', '250px'],
			  offset: '100px',
			  content: 'expert/editJsp.do?id='+id
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
		$.post("expert/dels.do",{ids:ids},function(data){
			if(data=="suc"){
				layer.msg("删除成功!",function(){
					table.reload('dg');
				});
			}else{
				layer.alert("删除失败!");
			}
		});
	}
		layui.use('table', function() {
			table = layui.table;
			table.render({
				elem : '#dg',
				defaultToolbar:[],
				cellMinWidth: 100,
				url : 'expert/getAllByPage.do',
				method:"post",
				page : true,
				cols : [[{
					field : 'id',
					title : 'id',
					type:'checkbox'
				}, {
					field : 'hospital',
					title : '所属医院',
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
