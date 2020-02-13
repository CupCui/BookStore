package pers.garre.bookstore.service;

import pers.garre.bookstore.bean.Cart;
import pers.garre.bookstore.bean.User;

public interface OrderService {
	public String createOrder(Cart cart, User user);
}
