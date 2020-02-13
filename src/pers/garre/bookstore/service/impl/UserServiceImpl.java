package pers.garre.bookstore.service.impl;

import pers.garre.bookstore.bean.User;
import pers.garre.bookstore.dao.UserDao;
import pers.garre.bookstore.dao.impl.UserDaoImpl;
import pers.garre.bookstore.service.UserService;

public class UserServiceImpl implements UserService {
	
	UserDao userDao = new UserDaoImpl();
	
	@Override
	public boolean register(User user) {
		return userDao.addUser(user);
	}

	@Override
	public User login(String username, String password) {
		User user = userDao.findUserByUandP(username, password);
		return user;
	}

	@Override
	public User findUserByUsername(String username) {
		return userDao.findUserByUsername(username);
	}

}
