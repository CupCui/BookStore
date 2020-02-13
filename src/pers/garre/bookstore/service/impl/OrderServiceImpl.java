package pers.garre.bookstore.service.impl;

import java.util.Date;
import java.util.List;

import pers.garre.bookstore.bean.Book;
import pers.garre.bookstore.bean.Cart;
import pers.garre.bookstore.bean.CartItem;
import pers.garre.bookstore.bean.Order;
import pers.garre.bookstore.bean.OrderItem;
import pers.garre.bookstore.bean.User;
import pers.garre.bookstore.dao.BookDao;
import pers.garre.bookstore.dao.OrderDao;
import pers.garre.bookstore.dao.OrderItemDao;
import pers.garre.bookstore.dao.impl.BookDaoImpl;
import pers.garre.bookstore.dao.impl.OrderDaoImpl;
import pers.garre.bookstore.dao.impl.OrderItemDaoImpl;
import pers.garre.bookstore.service.OrderService;

public class OrderServiceImpl implements OrderService {

	@Override
	public String createOrder(Cart cart, User user) {
		String orderId = System.currentTimeMillis() + user.getId() + "";
		
		Order order = new Order(orderId, new Date(), cart.getTotalCount(), cart.getTotalAmount(), 0, user.getId());
		
		OrderDao orderDao = new OrderDaoImpl();
		OrderItemDao orderItemDao = new OrderItemDaoImpl();
		BookDao bookDao = new BookDaoImpl();
		
		orderDao.saveOrder(order);
		
		List<CartItem> cartItems = cart.getCartItems();
		for (CartItem cartItem : cartItems) {
			Book book = cartItem.getBook();
			OrderItem orderItem = new OrderItem(null, cartItem.getCount(), cartItem.getAmount(), book.getTitle(), book.getAuthor(), book.getPrice(), book.getimg_path(), orderId);
			orderItemDao.saveOrderItem(orderItem);
			
			// Update book stock and sales
			book.setStock(book.getStock() - cartItem.getCount());
			book.setSales(book.getSales() + cartItem.getCount());
			bookDao.updateBookById(book);
		}
		
		cart.clearCart();
		
		return orderId;
	}

}
