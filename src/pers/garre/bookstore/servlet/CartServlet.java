package pers.garre.bookstore.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.garre.bookstore.bean.Book;
import pers.garre.bookstore.bean.Cart;
import pers.garre.bookstore.bean.CartItem;
import pers.garre.bookstore.service.impl.BookServiceImpl;

public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * Add book to cart
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get book id from client
		String bookId = request.getParameter("bookId");
		BookServiceImpl bookServiceImpl = new BookServiceImpl();
		
		// Get book by book id
		Book book = bookServiceImpl.findBookById(bookId);
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
		}
		int cartCount = cart.getCountById(bookId);
		Integer bookStock = book.getStock();
		request.getSession().setAttribute("cartStockMsg", "Stock is not avilable.");
		// 判断图书库存
		if (cartCount < bookStock) {	// Book stock is available
			// 将图书添加到购物车
			cart.addCart(book);
			request.getSession().setAttribute("cart", cart);
			request.getSession().setAttribute("lastBook", book);
			request.getSession().removeAttribute("cartStockMsg");
		} else {				// Book stock is not available
			request.getSession().setAttribute("cartStockMsg", "Stock is not avilable.");
			request.getSession().removeAttribute("lastBook");
		}
		
		String url = request.getHeader("Referer");
		response.sendRedirect(url);
	}
	
	/**
	 * Delete cart item
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		cart.deleteCartItem(Integer.parseInt(bookId));
		response.sendRedirect("pages/cart/cart.jsp");
	}
	
	protected void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		cart.clearCart();
		response.sendRedirect("pages/cart/cart.jsp");
	}
	
	protected void updateCartItemCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		String count = request.getParameter("count");
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		cart.updateCartItemCount(Integer.parseInt(bookId), Integer.parseInt(count));
		response.sendRedirect("pages/cart/cart.jsp");
	}
	
}
