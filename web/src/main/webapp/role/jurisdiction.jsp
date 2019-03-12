<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>设置权限</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="scripts/jquery/jquery-1.7.1.js"></script>
<script src="layui/layui.js"></script>
<script src="layui/lay/modules/layui-xtree.js"></script>
<link rel="stylesheet" href="layui/css/layui.css" media="all">
<style type="text/css">
.layui-form-checkbox span {
	height: auto;
}
</style>
</head>
<body>
	<form class="layui-form">
		<div id="xtree" style="padding: 10px 0 25px 5px;"></div>
		<div class="layui-input-block">
			<button onclick="editJurisdiction()" type="button" class="layui-btn">立即提交</button>
		</div>
	</form>
	<script>
	var xtree;
	function getCheck(tree){
		var treeChick = [];
		for(var i =0;i<tree.length;i++){
			treeChick.push(tree[i].value);
		}
		return treeChick;
	}
	function editJurisdiction(){
		var treeChecked = getCheck(xtree.GetChecked());
		treeChecked = treeChecked.toString();
		$.post("role/editJurisdiction.do",{treeChecked:treeChecked,id:<%=request.getParameter("id")%>},function(data){
			if(data=="suc"){
				layer.alert('保存成功!', function(){
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}); 
			}else{
				layer.alert('保存失败!');
			}
		});
	}
		layui.use('form', function(){
			var form = layui.form;
			xtree = new layuiXtree({
	                elem: 'xtree',
	                form: form,
	                data: "role/jurisdiction.do?id="+<%=request.getParameter("id")%> 
	            });
		});
	</script>
</html>
