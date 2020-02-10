package pers.garre.bookstore.dao;

import pers.garre.bookstore.bean.User;

public interface UserDao {
	public boolean addUser(User user);
	
	public User findUserByUandP(String username, String password);
}
