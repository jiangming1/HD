<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>SCT-后台系统</title>
<link href="<%=basePath%>/style/authority/main_css.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/style/authority/zTreeStyle.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="<%=basePath%>/scripts/jquery/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/scripts/zTree/jquery.ztree.core-3.2.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/scripts/authority/commonAll.js"></script>
</head>
<body>
	<div id="top">
		<div id="top_logo">
			<img alt="logo" src="<%=basePath%>/images/common/logo.jpg"
				width="274" height="49" style="vertical-align:middle;">
		</div>
		<div id="top_links">
			<div id="top_op">
				<ul>
					<li><img alt="当前用户"
						src="<%=basePath%>/images/common/user.jpg">： <span>admin</span>
					</li>
					<li><img alt="今天是" src="<%=basePath%>/images/common/date.jpg">：
						<span id="day_day"></span></li>
				</ul>
			</div>
			<div id="top_close">
				<a href="javascript:void(0);" onclick="logout();" target="_parent">
					<img alt="退出系统" title="退出系统"
					src="<%=basePath%>/images/common/close.jpg"
					style="position: relative; top: 10px; left: 25px;">
				</a>
			</div>
		</div>
	</div>
	<div id="side">
		<div id="left_menu">
			<ul id="TabPage2" style="height:200px; margin-top:50px;">
				<li id="left_tab1" class="selected" title="业务模块"><img
					alt="业务模块" title="业务模块"
					src="<%=basePath%>/images/common/1_hover.jpg" width="33"
					height="31"></li>
				<li id="left_tab2" title="系统管理"><img alt="系统管理" title="系统管理"
					src="<%=basePath%>/images/common/2.jpg" width="33" height="31"></li>
				<li id="left_tab3" title="其他"><img alt="其他" title="其他"
					src="<%=basePath%>/images/common/3.jpg" width="33" height="31">
				</li>
			</ul>

			<div id="nav_show"
				style="position:absolute; bottom:0px; padding:10px;">
				<a href="javascript:;" id="show_hide_btn"> <img alt="显示/隐藏"
					title="显示/隐藏" src="<%=basePath%>/images/common/nav_hide.png"
					width="35" height="35">
				</a>
			</div>
		</div>
		<div id="left_menu_cnt">
			<div id="nav_module">
				<img src="<%=basePath%>/images/common/module_1.png" width="210"
					height="58" />
			</div>
			<div id="nav_resource">
				<ul id="dleft_tab1" class="ztree"></ul>
			</div>
		</div>
	</div>
	<div id="top_nav">
		<span id="here_area">当前位置：系统&nbsp;>&nbsp;系统介绍</span>
	</div>
 	<div id="main">
 	<iframe name="right" id="rightMain" src="user/user.jsp"
			frameborder="no" scrolling="auto" width="100%" height="100%"
			allowtransparency="true">
 	</iframe>
	</div>
	<script type="text/javascript">
		/**退出系统**/
		function logout() {
			if (confirm("您确定要退出本系统吗？")) {
				window.location.href = "login.html";
			}
		}

		/**获得当前日期**/
		function getDate01() {
			var time = new Date();
			var myYear = time.getFullYear();
			var myMonth = time.getMonth() + 1;
			var myDay = time.getDate();
			if (myMonth < 10) {
				myMonth = "0" + myMonth;
			}
			document.getElementById("yue_fen").innerHTML = myYear + "."
					+ myMonth;
			document.getElementById("day_day").innerHTML = myYear + "."
					+ myMonth + "." + myDay;
		}
		
		function treeClick(event, treeId, treeNode){
			var zTree = $.fn.zTree.getZTreeObj("dleft_tab1");
			zTree.expandNode(treeNode, null, null, null, true);
			if(treeNode.isParent){
				return false;
			}
			if(treeNode.accessPath=="" || treeNode.accessPath=="#"){
				return false;
			}
		    rightMain(treeNode.accessPath);
		    if( treeNode.isParent ){
			    $('#here_area').html('当前位置：'+treeNode.getParentNode().resourceName+'&nbsp;>&nbsp;<span style="color:#1A5CC6">'+treeNode.resourceName+'</span>');
		    }else{
			    $('#here_area').html('当前位置：系统&nbsp;>&nbsp;<span style="color:#1A5CC6">'+treeNode.name+'</span>');
		    }
		}

		var zTreeObj;
		var setting = {
			view : {
				showLine : false,
				showTitle : false,
				expandSpeed : "fast"
			},
			callback : {
				onClick : treeClick
			}
		};
		var zNodes = [ {
			name : "用户管理",
			"accessPath":"user/user.jsp",
		}, {
			name : "小百科管理",
			"accessPath":"encyclopedias/encyclopedias.jsp",
		},{
			name : "案例管理",
			"accessPath":"cases/cases.jsp",
		},{
			name : "轮播图管理",
			"accessPath":"benner/benner.jsp",
		},{
			name : "康复视频管理",
			"accessPath":"video/video.jsp",
		}];
		$(document).ready(function() {
			zTreeObj = $.fn.zTree.init($("#dleft_tab1"), setting, zNodes);
		});
	</script>
</body>
</html>


