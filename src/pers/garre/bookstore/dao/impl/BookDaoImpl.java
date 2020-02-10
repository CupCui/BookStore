package pers.garre.bookstore.dao.impl;

import java.util.List;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import pers.garre.bookstore.bean.Book;
import pers.garre.bookstore.bean.Page;
import pers.garre.bookstore.dao.BookDao;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {
	
	/**
	 * Get all books
	 */
	@Override
	public List<Book> findAllBooks() {
		return this.getBeanList("select * from books");
	}

	/**
	 * Add new book
	 */
	@Override
	public boolean addBook(Book book) {
		int rows = this.update("insert into books value(null, ?, ?, ?, ?, ?, 'default.jpg')", book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock());
		if (rows == 0) {
			return false;
		}
		return true;
	}

	@Override
	public Book findBookById(String bookId) {
		return this.getBean("select * from books where id=?", bookId);
	}

	@Override
	public boolean updateBookById(Book book) {
		int rows = this.update("update books set title=?, author=?, price=?, sales=?, stock=? where id = ?", book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getId());
		if (rows == 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteBookById(String bookId) {
		int rows = this.update("delete from books where id=?", bookId);
		if (rows == 0) {
			return false;
		}
		return true;
	}

	@Override
	public List<Book> findPageByPageNum(int pageNum, int pageSize) {
		return this.getBeanList("select * from books limit ?,?", (pageNum-1)*pageSize, pageSize);
	}

	@Override
	public int count() {
		Object value = this.getValue("select count(*) from books");
		return Integer.parseInt(value + "");
	}
	
	@Override
	public List<Book> findPageByPageNum(int pageNum, int pageSize, int minPrice, int maxPrice) {
		return this.getBeanList("select * from books where price>? and price<? limit ?,?", minPrice, maxPrice, (pageNum-1)*pageSize, pageSize);
	}
	
	@Override
	public int count(int minPrice, int maxPrice) {
		Object value = this.getValue("select count(*) from books where price>? and price<?", minPrice, maxPrice);
		return Integer.parseInt(value + "");
	}


}
