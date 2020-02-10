<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<%@include file="/WEB-INF/admin/base.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#pn_btn").click(function() {
			var pageNum = $("#pn_input").val();
			var totalPage = $("#totalPage").val();
			var regExp = /^[0-9]*$/;
			var regResult = regExp.test(pageNum);

			// 【未完成】输入页码超过最大页数
			if (regResult && pageNum<=totalPage) {
				location.href="BookServlet?event=findAllBooks&pageNum=" + pageNum;
			} /* else if (regResult && pageNum<=totalPage) {
				location.href="BookServlet?event=findAllBooks&pageNum=" + totalPage;
			}  */else {
				alert("Page number is not avilable.");
			}
		});
	});
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<div>
				<a href="pages/manager/book_manager.jsp">图书管理</a>
				<a href="pages/manager/order_manager.jsp">订单管理</a>
				<a href="index.jsp">返回商城</a>
			</div>
	</div>
	
	<div id="main" class="box_no">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>		
			
			<c:forEach items="${page.list }" var="book">
				<tr>
					<td>${book.title }</td>
					<td>${book.price }</td>
					<td>${book.author }</td>
					<td>${book.sales }</td>
					<td>${book.stock }</td>
					<td><a href="BookServlet?event=updateBefore&bookId=${book.id }" class="a_green">修改</a></td>
					<td><a href="BookServlet?event=deleteBookById&bookId=${book.id }">删除</a></td>
				</tr>	
			</c:forEach>
			
			<tr>

			<tr>
				<td colspan="6">
					<div id="page_nav">
						<!-- Hidden scope -->
						<input type="hidden" name="totalPage" value="${page.totalPage }" id="totalPage" />

						<a href="BookServlet?event=findAllBooks&pageNum=1">首页</a>
						<a href="BookServlet?event=findAllBooks&pageNum=${page.pageNum-1<1?1:page.pageNum-1 }">上一页</a>

						<a href="BookServlet?event=findAllBooks&pageNum=${page.pageNum-1 }">${page.pageNum-1<1?"      ":page.pageNum-1 }</a>
						【${page.pageNum }】
						<a href="BookServlet?event=findAllBooks&pageNum=${page.pageNum+1 }">${page.pageNum+1>page.totalPage?"      ":page.pageNum+1 }</a>

						<a href="BookServlet?event=findAllBooks&pageNum=${page.pageNum+1>page.totalPage?page.totalPage:page.pageNum+1 }">下一页</a>
						<a href="BookServlet?event=findAllBooks&pageNum=${page.totalPage }">末页</a>
						当前${page.pageNum }页,共${page.totalPage }页，${page.totalSize }条记录 到第<input value="${page.pageNum }" name="pn" id="pn_input"/>页
						<input id="pn_btn" type="button" value="确定">
					</div>
				</td>
				
				<td><a href="pages/manager/book_add.jsp">添加图书</a></td>
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