<%@ page language="java" import="java.util.*,shan.HDHealthManagement.po.Role" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'add.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript"
	src="<%=basePath%>/scripts/jquery/jquery-1.7.1.js"></script>
	<script type="text/javascript"
	src="<%=basePath%>/scripts/Myjquery.form.js"></script>

<script src="<%=basePath %>/layui/layui.js"></script>
<script src="<%=basePath %>/layui/lay/modules/layui-xtree.js"></script>
<link rel="stylesheet" href="<%=basePath %>/layui/css/layui.css"
	media="all">
</head>
<body>
	<form id="form" method="post" action="<%=basePath %>/role/edit.do" class="layui-form layui-form-pane">
	<%
	Role role = (Role)request.getAttribute("role");
	%>
	<input type="text" name="id" style="display:none;" value="<%=role.getId() %>">
		<div class="layui-form-item">
			<label class="layui-form-label">名称</label>
			<div class="layui-input-block">
				<input type="text" name="name" value="<%=role.getName() %>" placeholder="请输入" lay-verify="required"
					autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">描述</label>
			<div class="layui-input-block">
				<input type="text" name="memo" value="<%=role.getMemo() %>" placeholder="请输入" lay-verify="required"
					autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">权限</label>
			<input id="jurisdiction" type="text" name="jurisdiction" style="display:none;">
			<div id="xtree"></div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="userform">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
	<script>
	function getCheck(tree){
		var treeChick = [];
		for(var i =0;i<tree.length;i++){
			treeChick.push(tree[i].value);
		}
		return treeChick;
	}
	
		layui.use('form', function(){
		  var form = layui.form;
		  form.on('submit(userform)', function(data){
			  var treeChick = getCheck(xtree.GetChecked());
			  $("#jurisdiction").val(treeChick);
			  $("#form").ajaxSubmit(function(data){
					if(data=="suc"){
						layer.alert('保存成功!', function(){
							var index = parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
						}); 
					}else{
						layer.alert('保存失败!');
					}
				});
			  return false;
			});
			var xtree = new layuiXtree({
				elem: 'xtree',
		        form: form,
				data: <%=request.getAttribute("data")%>,
				isopen : false
			});
		});
	</script>
</html>
