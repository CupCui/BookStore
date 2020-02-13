package pers.garre.bookstore.dao.impl;

import pers.garre.bookstore.bean.Order;
import pers.garre.bookstore.dao.OrderDao;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {

	@Override
	public void saveOrder(Order order) {
		this.update("insert into orders value(?,?,?,?,?,?)", 
				order.getId(), order.getOrderTime(), order.getTotalCount(), order.getTotalAmount(), order.getState(), order.getUserId());
	}

}
