<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
<%@include file="/WEB-INF/admin/base.jsp" %>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
			<div>
				<a href="pages/manager/book_manager.jsp">图书管理</a>
				<a href="pages/manager/order_manager.jsp">订单管理</a>
				<a href="index.jsp">返回商城</a>
			</div>
	</div>
	
	<div id="main" class="box_order">
		<table>
			<tr>
				<td>订单号</td>
				<td>订单日期</td>
				<td>订单金额</td>
				<td>订单数量</td>
				<td>订单详情</td>
				<td>发货状态</td>
			</tr>		
			<tr>
				<td>12354456895</td>
				<td>2015.04.23</td>
				<td>90.00</td>
				<td>88</td>
				<td><a href="#">查看详情</a></td>
				<td><a href="#" class="a_green">点击发货</a></td>
			</tr>	
			<tr>
				<td>12354456895</td>
				<td>2015.04.20</td>
				<td>20.00</td>
				<td>88</td>
				<td><a href="#">查看详情</a></td>
				<td>已发货</td>
			</tr>	
			<tr>
				<td>12354456895</td>
				<td>2014.01.23</td>
				<td>190.00</td>
				<td>88</td>
				<td><a href="#">查看详情</a></td>
				<td>等待收货</td>
			</tr>	
			<tr>
				<td>12354456895</td>
				<td>2014.01.23</td>
				<td>190.00</td>
				<td>88</td>
				<td><a href="#">查看详情</a></td>
				<td>等待收货</td>
			</tr>
			<tr>
				<td>12354456895</td>
				<td>2014.01.23</td>
				<td>190.00</td>
				<td>88</td>
				<td><a href="#">查看详情</a></td>
				<td>等待收货</td>
			</tr>	
		</table>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>