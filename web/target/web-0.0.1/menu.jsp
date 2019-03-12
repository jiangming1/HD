<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div id="left_menu_cnt">
	<div id="nav_module">
		<img src="<%=basePath%>/images/common/module_1.png" width="210"
			height="58" />
	</div>
	<div id="nav_resource">
		<ul id="tab1" class="ztree"></ul>
		<ul id="tab2" class="ztree"></ul>
		<ul id="tab3" class="ztree"></ul>
	</div>
</div>
<script type="text/javascript">
	layui.tree({
		elem : '#tab1',
		nodes : [
		<shiro:hasPermission name="encyclopedias">
		{
			name : "小百科管理",
			"accessPath" : "encyclopedias/encyclopedias.jsp",
		},
		</shiro:hasPermission>
		{
			name : "案例管理",
			"accessPath" : "cases/cases.jsp",
		}, {
			name : "轮播图管理",
			"accessPath" : "benner/benner.jsp",
		}, {
			name : "专家管理",
			"accessPath" : "expert/expert.jsp",
		},{
			name : "康复视频管理",
			"accessPath" : "video/video.jsp",
		} ],
		click : function(node) {
			 $('#rightMain').attr('src',node.accessPath);
		}
	});
	layui.tree({
		elem : '#tab2',
		nodes : [{
			name : "用户管理",
			"accessPath" : "user/user.jsp",
		},{
			name : "角色管理",
			"accessPath" : "role/role.jsp",
		}],
		click : function(node) {
			 $('#rightMain').attr('src',node.accessPath);
		}
	});
	layui.tree({
		elem : '#tab3',
		nodes : [{
			name : "微信用户管理",
			"accessPath" : "weixinUser/weixinUser.jsp",
		},{
			name : "文章管理",
			"accessPath" : "article/article.jsp",
		}],
		click : function(node) {
			 $('#rightMain').attr('src',node.accessPath);
		}
	});
	$(function(){
		$("#tab2").hide();
		$("#tab3").hide();
	});
</script>