package pers.garre.bookstore.dao.impl;

import pers.garre.bookstore.bean.OrderItem;
import pers.garre.bookstore.dao.OrderItemDao;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {

	@Override
	public void saveOrderItem(OrderItem item) {
		this.update("insert into orderItem values(?,?,?,?,?,?,?,?)", 
				null, item.getCount(), item.getAmount(), item.getTitle(), item.getAuthor(), item.getPrice(), item.getImgPath(), item.getOrderId());
	}

}
