<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@include file="/WEB-INF/admin/base.jsp" %>
<script type="text/javascript">
	$(function() {
		$(".cartItemCount").change(function() {
			var bookId = Number(this.name);
			var count = Number(this.value);
			var stock = Number(this.min);
			
			if (count > stock || count <= 0) {
				if (count == 0) {
					location.href = "CartServlet?event=deleteCartItem&bookId=" + bookId;
				} else {
					alert("Stock is not available.");
					location.href = "pages/cart/cart.jsp";
				}
			} else {
				location.href = "CartServlet?event=updateCartItemCount&bookId=" + bookId + "&count=" + count;
			}
		});
		
		$(".cartItemCountPlus").click(function() {
			var bookId = Number(this.name);
			var count = Number(this.alt) + 1;
			var stock = Number(this.min);
			
			if (count > Number(stock) || count <= 0) {
				if (count == 0) {
					location.href = "CartServlet?event=deleteCartItem&bookId=" + bookId;
				} else {
					alert("Stock is not available.");
					location.href = "pages/cart/cart.jsp";
				}
			} else {
				location.href = "CartServlet?event=updateCartItemCount&bookId=" + bookId + "&count=" + count;
			}
		});
		
		$(".cartItemCountMinus").click(function() {
			var bookId = Number(this.name);
			var count = Number(this.alt) - 1;
			var stock = Number(this.min);
			
			if (count > Number(stock) || count <= 0) {
				if (count == 0) {
					location.href = "CartServlet?event=deleteCartItem&bookId=" + bookId;
				} else {
					location.href = "pages/cart/cart.jsp";
				}
			} else {
				location.href = "CartServlet?event=updateCartItemCount&bookId=" + bookId + "&count=" + count;
			}
		});
	});
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@include file="/WEB-INF/admin/header.jsp" %>
	</div>
	
	<div id="main" class="box_order">
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>		
			<c:forEach items="${sessionScope.cart.cartItems }" var="cartItem">
			<tr>
				<td>${cartItem.book.title }</td>
				
				<td>
					<input type="button" min="${cartItem.book.stock }" name="${cartItem.book.id }" alt="${cartItem.count }" class="cartItemCountMinus" value="-" />
					<input type="text" min="${cartItem.book.stock }" name="${cartItem.book.id }" class="cartItemCount" value="${cartItem.count }" size="3" />
					<input type="button" min="${cartItem.book.stock }" name="${cartItem.book.id }" alt="${cartItem.count }" class="cartItemCountPlus" value="+" />
				</td>
				
				<td>${cartItem.book.price }</td>
				<td>${cartItem.amount }</td>
				<td><a href="CartServlet?event=deleteCartItem&bookId=${cartItem.book.id }">删除</a></td>
			</tr>
			</c:forEach>
		</table>
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount }</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalAmount }</span>元</span>
			<span class="cart_span"><a href="CartServlet?event=clearCart">清空购物车</a></span>
			<span class="cart_span"><a href="BookClientServlet?event=findAllBooks">继续购物</a></span>
			<span class="cart_span"><a href="pages/cart/checkout.jsp">去结账</a></span>
		</div>
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>