package pers.garre.bookstore.bean;

import java.math.BigDecimal;

// Book cart items
public class CartItem {
	private Book book; // 图书信息
	private int count; // 图书的数量
	private double amount; // 购物项中图书的金额，通过计算得到

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItem(Book book, int count) {
		super();
		this.book = book;
		this.count = count;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getAmount() {
		BigDecimal price = new BigDecimal(book.getPrice() + "");
		BigDecimal count = new BigDecimal(this.count + "");
		return price.multiply(count).doubleValue();

	}

	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + ", amount=" + amount + "]";
	}

}
