<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员登录页面</title>
<%@include file="/WEB-INF/admin/base.jsp" %>
<script type="text/javascript">
	// 使用jQuery对登陆中的用户名、密码进行非空验证
	$(function() {
		$("#sub_btn").click(function() {
			var $username = $("#username").val();
			var $password = $("#password").val();
			if ($username == "") {
				$("#errorMsg").text("User name is null.");
				return false;
			} else if ($password == "") {
				$("#errorMsg").text("Password is null.");
				return false;
			}
		});
		
		$("#imgCode").click(function() {
			this.src = "KaptchaServlet?time=" + new Date();
		});
	});
</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>尚硅谷会员</h1>
							</div>
							<div class="msg_cont">
								<b></b>
								<span class="errorMsg" id="errorMsg">
									${empty requestScope.user.name ? "请输入用户名和密码" : "User name or password is not available." }
								</span>
							</div>
							<div class="form">
								<form action="UserServlet?event=login" method="post">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" value="${empty requestScope.user.username?'':requestScope.user.username}" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" value="${empty requestScope.user.password?'':requestScope.user.password }" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt inp" type="text" style="width: 155px;" id="code" name="verifyCode" />
									<img alt="" src="KaptchaServlet" style="float: right; margin-right: 22px; width: 89px; height:40px;" id="imgCode">									
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
								<div class="tit">
									<a href="pages/user/regist.jsp">立即注册</a>
								</div>
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