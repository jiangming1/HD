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
<title>添加用户</title>
<script type="text/javascript"
	src="scripts/jquery/jquery-1.7.1.js"></script>
	<script type="text/javascript"
	src="scripts/Myjquery.form.js"></script>
<link rel="stylesheet" href="layui/css/layui.css"
	media="all">
<script src="layui/layui.js"></script>
</head>
<body>
	<form id="form" method="post" action="user/add.do" class="layui-form layui-form-pane">
		<div class="layui-form-item">
			<label class="layui-form-label">账号</label>
			<div class="layui-input-block">
				<input type="text" name="userName" placeholder="请输入" lay-verify="required"
					autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">用户名</label>
			<div class="layui-input-block">
				<input type="text" name="name" placeholder="请输入" lay-verify="required|userName"
					autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">密码</label>
			<div class="layui-input-block">
				<input type="password" name="password" placeholder="请输入" lay-verify="required|password"
					autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">电话</label>
			<div class="layui-input-block">
				<input type="text" name="tel" placeholder="请输入" lay-verify="required|phone"
					autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">邮箱</label>
			<div class="layui-input-block">
				<input type="text" name="email" placeholder="请输入" lay-verify="required|email"
					autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">角色</label>
			<input id="role" type="text" name="role" style="display:none;">
			<div class="layui-input-block">
			<% 
				List<Role> list=(List<Role>)request.getAttribute("roles");
				for(Role role:list){
			%>
				<input lay-filter="filter"  type="checkbox" name="<%=role.getId() %>" title='<%=role.getName() %>' />
			<%}%>
					
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="userform">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
	<script>
	var role = [];
		layui.use('form', function(){
		  var form = layui.form;
		  form.on('submit(userform)', function(data){
			  $("#role").val(role);
			  $("#form").ajaxSubmit(function(data){
					if(data=="suc"){
						layer.msg('保存成功!', function(){
							var index = parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
						}); 
					}else{
						layer.msg('保存失败!');
					}
				});
			  return false;
			});
		  form.on('checkbox(filter)', function(data){
			  if(data.elem.checked){
				  role.push(data.elem.name);
			  }else{
				  role.splice(role.indexOf(data.elem.name),1);
			  }
			});   
		  form.verify({
			  userName: function(value, item){
			    if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
			      return '用户名不能有特殊字符';
			    }
			    if(/(^\_)|(\__)|(\_+$)/.test(value)){
			      return '用户名首尾不能出现下划线\'_\'';
			    }
			    if(/^\d+\d+\d$/.test(value)){
			      return '用户名不能全为数字';
			    }
			  }
			  ,password: [
			    /^[\S]{6,16}$/
			    ,'密码必须6到16位，且不能出现空格'
			  ] 
			}); 
		});
		    
</script>
</html>
