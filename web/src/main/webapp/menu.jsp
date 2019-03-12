<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<div id="left_menu_cnt">
	<div id="nav_module">
		<img src="images/common/module_1.png" width="210" height="58" />
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
		<shiro:hasPermission name="cases">
		{
			name : "案例管理",
			"accessPath" : "cases/cases.jsp",
		}, 
		</shiro:hasPermission>
		<shiro:hasPermission name="benner">
		{
			name : "轮播图管理",
			"accessPath" : "benner/benner.jsp",
		},
		</shiro:hasPermission>
		<shiro:hasPermission name="expert">
		{
			name : "专家管理",
			"accessPath" : "expert/expert.jsp",
		},
		</shiro:hasPermission>
		<shiro:hasPermission name="video">
		{
			name : "康复视频管理",
			"accessPath" : "video/video.jsp",
		}
		</shiro:hasPermission>
		],
		click : function(node) {
			 $('#rightMain').attr('src',node.accessPath);
		}
	});
	layui.tree({
		elem : '#tab2',
		nodes : [
		<shiro:hasPermission name="user">
		{
			name : "用户管理",
			"accessPath" : "user/user.jsp",
		},
		</shiro:hasPermission>
		<shiro:hasPermission name="role">
		{
			name : "角色管理",
			"accessPath" : "role/role.jsp",
		}
		</shiro:hasPermission>
		],
		click : function(node) {
			 $('#rightMain').attr('src',node.accessPath);
		}
	});
	layui.tree({
		elem : '#tab3',
		nodes : [
		<shiro:hasPermission name="weixinUser">
		{
			name : "微信用户管理",
			"accessPath" : "weixinUser/weixinUser.jsp",
		},
		</shiro:hasPermission>
		<shiro:hasPermission name="article">
		{
			name : "文章管理",
			"accessPath" : "article/article.jsp",
		}
		</shiro:hasPermission>
		],
		click : function(node) {
			 $('#rightMain').attr('src',node.accessPath);
		}
	});
	$(function(){
		$("#tab2").hide();
		$("#tab3").hide();
	});
	</script>
</body>
</html>


