<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>HD患者健康管理后台系统</title>
<link href="style/authority/login_css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="scripts/jquery/jquery-1.7.1.js"></script>
</head>
<body>
	<div id="login_center">
		<div id="login_area">
			<div id="login_box">
				<div id="login_form">
					<form>
						<div id="login_tip">
							<span id="login_err" class="sty_txt2"></span>
						</div>
						<div>
							 用户名：<input type="text" class="username" id="userName">
						</div>
						<div>
							密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" class="pwd" id="password">
						</div>
						<div>
							验证码：<input type="text" class="verification" id="verification" onkeypress="EnterPress(event)" onkeydown="EnterPress()">
						</div>
						<img id="verificationimg" class="verificationimg" onclick="getVerification()" src="">
						<div id="btn_area">
							<input type="button" onclick="javascript:login();" class="login_btn" value="登  录">
							<input type="reset" class="login_btn" value="重 置">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
	function login(){
		var userName = $("#userName").val();
		var password = $("#password").val();
		var verification = $("#verification").val();
		if(userName==""){
			alert("请输入用户名！");
		}else if(password==""){
			alert("请输入密码");
		}else if(verification==""){
			alert("请输入验证码");
		}else{
			$.post("<%=basePath %>/user/login.do",{userName:userName,password:password,verification:verification},function(data){
				if(data=="0"){
					alert("验证码错误");
				}else if(data=="1"){
					alert("用户名密码错误");
				}else if(data=="2"){
					alert("登陆次数超出");
				}else if(data=="3"){
					window.location.href = "index.jsp";
				}
			});
		}
	}

	$(function(){
		getVerification();
	});
	/*回车事件*/
 /* 	function EnterPress(e){
		var e = e || window.event; 
		if(e.keyCode == 13){
			$("#submitForm").attr("action", "index.html").submit();
		} 
	} */
	function getVerification(){
		$("#verificationimg").attr("src","<%=basePath %>/user/getVerification.do?"+(new Date()).getTime());
	}
</script>
</body>
</html>