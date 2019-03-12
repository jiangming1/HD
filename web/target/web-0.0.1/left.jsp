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
		<li onclick="leftClick1(this)" class="selected"><img src="<%=basePath%>/images/common/1_hover.jpg" width="33" height="31"></li>
		</shiro:hasPermission>
		<shiro:hasPermission name="system"> 
		<li onclick="leftClick2(this)"><img src="<%=basePath%>/images/common/2.jpg" width="33" height="31"></li>
		</shiro:hasPermission>
		<shiro:hasPermission name="other"> 
		<li onclick="leftClick3(this)"><img src="<%=basePath%>/images/common/3.jpg" width="33" height="31"></li>
		</shiro:hasPermission>
	</ul>
</div>
<script type="text/javascript">
	function leftClearn(){
		$($("#TabPage2").children("li").get(0)).removeClass("selected");
		$($("#TabPage2").children("li").get(1)).removeClass("selected");
		$($("#TabPage2").children("li").get(2)).removeClass("selected");
		$($("#TabPage2").children("li").get(0)).find("img").attr("src","<%=basePath%>/images/common/1.jpg");
		$($("#TabPage2").children("li").get(1)).find("img").attr("src","<%=basePath%>/images/common/2.jpg");
		$($("#TabPage2").children("li").get(2)).find("img").attr("src","<%=basePath%>/images/common/3.jpg");
	}
	<shiro:hasPermission name="business"> 
	function leftClick1(obj){
		leftClearn();
		$("#tab2").hide();
		$("#tab3").hide();
		$("#tab1").show();
		$(obj).addClass("selected");
		$(obj).find("img").attr("src","<%=basePath%>/images/common/1_hover.jpg");
		$("#nav_module").find("img").attr("src","<%=basePath%>/images/common/module_1.png");
	}
	</shiro:hasPermission>
	<shiro:hasPermission name="system">
	function leftClick2(obj){
		leftClearn();
		$("#tab1").hide();
		$("#tab3").hide();
		$("#tab2").show();
		$(obj).addClass("selected");
		$(obj).find("img").attr("src","<%=basePath%>/images/common/2_hover.jpg");
		$("#nav_module").find("img").attr("src","<%=basePath%>/images/common/module_2.png");
	}
	</shiro:hasPermission>
	<shiro:hasPermission name="other"> 
	function leftClick3(obj){
		leftClearn();
		$("#tab1").hide();
		$("#tab2").hide();
		$("#tab3").show();
		$(obj).addClass("selected");
		$(obj).find("img").attr("src","<%=basePath%>/images/common/3_hover.jpg");
		$("#nav_module").find("img").attr("src","<%=basePath%>/images/common/module_3.png");
	}
	</shiro:hasPermission>
</script>