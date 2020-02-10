<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<%@include file="/WEB-INF/admin/base.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#pn_btn").click(function() {
			var pageNum = $("#pn_input").val();
			var totalPage = $("#totalPage").val();
			var regExp = /^[0-9]*$/;
			var result = regExp.test(pageNum);

			// 【未完成】输入页码超过最大页数
			if (result && pageNum<totalPage) {
				location.href="BookClientServlet?event=findAllBooks&pageNum=" + pageNum;
			} else if (pageNum<totalPage) {
				location.href="BookClientServlet?event=findAllBooks&pageNum=" + totalPage;
			} else {
				alert("Page number is not avilable.");
			}
		});
		
		$("#search").click(function() {
			var maxPrice = $("#maxPrice").val();
			var minPrice = $("#minPrice").val();
			var regExp = /^[0-9]*$/;
			var result = regExp.test(minPrice) && regExp.test(maxPrice);

			if (minPrice > maxPrice) {
				alert("Price is not avilable.");
				location.href="BookClientServlet?event=findAllBooks";
			} else if (result) {
				location.href="BookClientServlet?event=findAllBooks&minPrice=" + minPrice + "&maxPrice=" + maxPrice;
			} else {
				alert("Price is not avilable.");
			}
		});
		
		$(".addCart").click(function() {
			var bookId = this.name;
			location.href="CartServlet?event=addCart&bookId=" + bookId;
		});
		
	});
</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<%@include file="/WEB-INF/admin/header.jsp" %>
	</div>
	
	<div id="main">
		<div id="book">
			<div class="book_cond">
				价格：<input type="text" name="minPrice" id="minPrice" value="${minPrice }"> 
					<span class="sp1">元</span> - 
					<input type="text" name="maxPrice" id="maxPrice" value="${maxPrice }"> 
					<span class="sp1">元</span> 
					<button id="search">查询</button>
			</div>
			<div class="cont">
					<span>您的购物车中有${empty sessionScope.cart?0:sessionScope.cart.totalCount }件商品</span>
					<c:if test="${not empty sessionScope.lastBook }">
						您刚刚将<span style="color: red">${sessionScope.lastBook.title }</span>加入到了购物车中
					</c:if>
				<c:if test="${not empty sessionScope.cartStockMsg }"><span>库存不足</span></c:if>
			</div>
			<c:forEach items="${page.list }" var="book">
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${book.img_path }" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">书名:</span>
							<span class="sp2">${book.title }</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span>
							<span class="sp2">${book.author }</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span>
							<span class="sp2">${book.price }</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span>
							<span class="sp2">${book.sales }</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span>
							<span class="sp2">${book.stock }</span>
						</div>
						<div class="book_add">
							<button class="addCart" name="${book.id }">加入购物车</button>
						</div>
						<input type="hidden" name="referer" value="${sessionScope.referer }" id="referer" />
					</div>
				</div>
			</c:forEach>
		</div>
		
		<div id="page_nav">
			<!-- Hidden scope -->
			<input type="hidden" name="totalPage" value="${page.totalPage }" id="totalPage" />

			<a href="BookClientServlet?event=findAllBooks&pageNum=1&minPrice=${minPrice }&maxPrice=${maxPrice }">首页</a>
			<a href="BookClientServlet?event=findAllBooks&pageNum=${page.pageNum-1<1?1:page.pageNum-1 }&minPrice=${minPrice }&maxPrice=${maxPrice }">上一页</a>

			<a href="BookClientServlet?event=findAllBooks&pageNum=${page.pageNum-1 }&minPrice=${minPrice }&maxPrice=${maxPrice }">${page.pageNum-1<1?"      ":page.pageNum-1 }</a>
			【${page.pageNum }】
			<a href="BookClientServlet?event=findAllBooks&pageNum=${page.pageNum+1 }&minPrice=${minPrice }&maxPrice=${maxPrice }">${page.pageNum+1>page.totalPage?"      ":page.pageNum+1 }</a>

			<a href="BookClientServlet?event=findAllBooks&pageNum=${page.pageNum+1>page.totalPage?page.totalPage:page.pageNum+1 }&minPrice=${minPrice }&maxPrice=${maxPrice }">下一页</a>
			<a href="BookClientServlet?event=findAllBooks&pageNum=${page.totalPage }&minPrice=${minPrice }&maxPrice=${maxPrice }">末页</a>
			当前${page.pageNum }页,共${page.totalPage }页，${page.totalSize }条记录 到第<input value="${page.pageNum }" name="pn" id="pn_input"/>页
			<input id="pn_btn" type="button" value="确定">
		</div>
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>