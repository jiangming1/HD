<%@ page language="java"
	import="java.util.*,shan.HDHealthManagement.po.Benner"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>修改轮播图</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="scripts/jquery/jquery-1.7.1.js"></script>
<script type="text/javascript" src="scripts/Myjquery.form.js"></script>
<link rel="stylesheet" href="layui/css/layui.css" media="all">
<script src="layui/layui.js"></script>
</head>
<body>
	<form id="form" method="post" action="benner/edit.do"
		class="layui-form layui-form-pane">
		<%
			Benner benner = (Benner) request.getAttribute("benner");
		%>
		<input type="text" name="id" style="display:none;"
			value="<%=benner.getId()%>">
		<div class="layui-form-item">
			<label class="layui-form-label">名称</label>
			<div class="layui-input-block">
				<input type="text" name="name" value="<%=benner.getName()%>"
					placeholder="请输入" lay-verify="required" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">url</label>
			<div class="layui-input-block">
				<input type="text" name="url" value="<%=benner.getUrl()%>"
					placeholder="请输入" lay-verify="required" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		

		<input type="text" id="fileUrl" name="fileUrl" style="display:none;"
			value="<%=benner.getFileUrl()%>">
		<div class="layui-form-item">
			<img height="100" width="100" id="file"
				src="<%=benner.getFileUrl()%>">
				<button type="button" class="layui-btn" id="upload">
			<i class="layui-icon">&#xe67c;</i>上传图片
		</button>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="userform">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
	<script>
		layui.use('form', function() {
			var form = layui.form;
			form.on('submit(userform)', function(data) {
				$("#form").ajaxSubmit(
						function(data) {
							if (data == "<pre>suc</pre>") {
								layer.alert('保存成功!', function() {
									var index = parent.layer
											.getFrameIndex(window.name);
									parent.layer.close(index);
								});
							} else {
								layer.alert('保存失败!');
							}
						});
				return false;
			});
		});
		layui.use('upload', function() {
			var upload = layui.upload;
			upload.render({
				elem : '#upload',
				accept : 'image',
				acceptMime : 'image/jpg, image/png',
				url : 'benner/upload.do',
				done : function(res) {
					$("#fileUrl").val(res.data);
					$("#file").attr("src", res.data);
				},
				error : function() {
					alert("文件上传失败");
				}
			});
		});
	</script>
</html>
