package pers.garre.bookstore.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.soap.AddressingFeature.Responses;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;

import pers.garre.bookstore.bean.Cart;
import pers.garre.bookstore.bean.User;
import pers.garre.bookstore.service.OrderService;
import pers.garre.bookstore.service.impl.OrderServiceImpl;

public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	OrderService orderService = new OrderServiceImpl();
	
	protected void checkOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Get Cart/CartItem
		// 2. To service
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		User user = (User)request.getSession().getAttribute("user");
		
		String orderId = orderService.createOrder(cart, user);
		
		request.setAttribute("orderId", orderId);
		request.getRequestDispatcher("pages/cart/checkout.jsp").forward(request, response);
	}

}
