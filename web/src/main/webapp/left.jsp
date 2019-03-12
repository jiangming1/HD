<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<div id="left_menu">
	<ul id="TabPage2" style="height:200px; margin-top:50px;">
		<shiro:hasPermission name="business">
			<li id="TabPage_1" onclick="leftClick1()">
				<img src="images/common/1.jpg" width="33" height="31">
			</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="system">
			<li id="TabPage_2" onclick="leftClick2()">
				<img src="images/common/2.jpg" width="33" height="31">
			</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="other">
			<li id="TabPage_3" onclick="leftClick3()">
				<img src="images/common/3.jpg" width="33" height="31">
			</li>
		</shiro:hasPermission>
	</ul>
</div>
<script type="text/javascript">
	<shiro:hasPermission name="business">
	function leftClick1() {
		$("#TabPage_2").removeClass("selected");
		$("#TabPage_3").removeClass("selected");
		$("#TabPage_1").addClass("selected");
		$("#TabPage_2").find("img").attr("src", "images/common/2.jpg");
		$("#TabPage_3").find("img").attr("src", "images/common/3.jpg");
		$("#TabPage_1").find("img").attr("src", "images/common/1_hover.jpg");
		$("#tab2").hide();
		$("#tab3").hide();
		$("#tab1").show();
		$("#nav_module").find("img").attr("src", "images/common/module_1.png");
	}
	</shiro:hasPermission>
	<shiro:hasPermission name="system">
	function leftClick2() {
		$("#TabPage_1").removeClass("selected");
		$("#TabPage_3").removeClass("selected");
		$("#TabPage_2").addClass("selected");
		$("#TabPage_2").find("img").attr("src", "images/common/2_hover.jpg");
		$("#TabPage_3").find("img").attr("src", "images/common/3.jpg");
		$("#TabPage_1").find("img").attr("src", "images/common/1.jpg");
		$("#tab1").hide();
		$("#tab3").hide();
		$("#tab2").show();
		$("#nav_module").find("img").attr("src", "images/common/module_2.png");
	}
	</shiro:hasPermission>
	<shiro:hasPermission name="other">
	function leftClick3() {
		$("#TabPage_1").removeClass("selected");
		$("#TabPage_2").removeClass("selected");
		$("#TabPage_3").addClass("selected");
		$("#TabPage_2").find("img").attr("src", "images/common/2.jpg");
		$("#TabPage_3").find("img").attr("src", "images/common/3_hover.jpg");
		$("#TabPage_1").find("img").attr("src", "images/common/1.jpg");
		$("#tab1").hide();
		$("#tab2").hide();
		$("#tab3").show();
		$("#nav_module").find("img").attr("src", "images/common/module_3.png");
	}
	</shiro:hasPermission>
</script>