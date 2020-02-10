package pers.garre.bookstore.dao;

import java.util.List;

import pers.garre.bookstore.bean.Book;
import pers.garre.bookstore.bean.Page;

public interface BookDao {
	public List<Book> findAllBooks();

	public boolean addBook(Book book);

	public Book findBookById(String bookId);

	public boolean updateBookById(Book book);

	public boolean deleteBookById(String bookId);

	public List<Book> findPageByPageNum(int pageNum, int pageSize);
	
	public int count();

	public List<Book> findPageByPageNum(int pageNum, int pageSize, int minPrice, int maxPrice);
	public int count(int minPrice, int maxPrice);
}
