package pers.garre.bookstore.dao.impl;

import pers.garre.bookstore.bean.User;
import pers.garre.bookstore.dao.UserDao;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public boolean addUser(User user) {
		int rows = update("insert into user(username, password, email) values(?, ?, ?)", user.getUsername(), user.getPassword(), user.getEmail());
		if (rows > 0) {
			System.out.println(rows + " rows affected.");
			return true;
		}
		return false;
	}

	@Override
	public User findUserByUandP(String username, String password) {
		User user = this.getBean("select * from user where username = ? and password = ?", username, password);
		return user;
	}

	@Override
	public User findUserByUsername(String username) {
		return this.getBean("select * from user where username=?", username);
	}

}
