<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%@include file="/WEB-INF/admin/base.jsp" %>
<script type="text/javascript">
// <!-- 2.	使用jQuery技术和正则表达式对注册中的用户名、密码、确认密码、邮箱进行格式验证，对验证码进行非空验证 -->
// <!-- a)	用户名、密码：只能是字母（大小写）、数字、_。6-18位。 -->
// <!-- b)	邮箱：API中的标准验证。 -->
	$(function() {
		var regUser = /^[a-zA-Z_0-9]{6,18}$/;
		var regEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		var flag = false;
		$(".itxt").blur(function() {
			var $username = $("#username").val();
			var $password = $("#password").val();
			var $repwd = $("#repwd").val();
			var $email = $("#email").val();
			var $code = $("#code").val();
			
			if (!regUser.test($username) || !regUser.test($password) || $password == null) {
				$("#errorMsg").text("User name or password is not available.");
			} else if ($password != $repwd) {
				$("#errorMsg").text("Confirm password is not equal password.");
			} else if (!regEmail.test($email)) {
				$("#errorMsg").text("Email is not available.");
			} else {
				flag = true;
				$("#errorMsg").text("All right.");
			}
		});
		
		// Check user name duplicate
		$("#username").change(function() {
			var username = $("#username").val();
			$.post(
				"UserServlet?event=checkUsername",
				{"username":username},
				function(result) {
					if (result == "ok") {
						$("#errorMsg").text("User name is not available.");
					} else {
						$("#errorMsg").text("User name is available.");
					}
				},
				"text"
			);
		});
		
		$("#sub_btn").click(function() {
			return flag;
		});
	});
</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
							</div>
							<div class="msg_cont">
								<b></b>
								<span class="errorMsg" id="errorMsg">请输入用户名和密码</span>
							</div>
							<div class="form">
								<form action="UserServlet?event=regist" method="post">
									<label>用户名称：</label>
									<!-- 用户注册信息错误动态回显用户注册信息 -->
									<%String username = (String)request.getAttribute("username"); %>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" value="<%=username==null ? "" : username%>"/>
									<br />
									<br />
									<label>用户密码：</label>
									<%String password = (String)request.getAttribute("password"); %>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" value="<%=password==null?"":password %>" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<%String email = (String)request.getAttribute("email"); %>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email" value="<%=email==null?"":email %>" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt inp" type="text" style="width: 155px;" id="code"/>
									<img alt="" src="static/img/code.bmp" style="float: right; margin-right: 22px">									
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>