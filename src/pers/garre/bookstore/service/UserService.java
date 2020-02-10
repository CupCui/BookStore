package pers.garre.bookstore.service;

import pers.garre.bookstore.bean.User;

public interface UserService {
	public boolean register(User user);
	
	public User login(String username, String password);
}
