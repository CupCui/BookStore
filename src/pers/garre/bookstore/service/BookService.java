package pers.garre.bookstore.service;

import java.util.List;

import pers.garre.bookstore.bean.Book;
import pers.garre.bookstore.bean.Page;

public interface BookService {
	public List<Book> findAllBooks();

	public boolean addBook(Book book);

	public Book findBookById(String bookId);

	public boolean updateBookById(Book book);

	public boolean deleteBookById(String bookId);

	public Page<Book> findPageByPageNum(int pageNum, int pageSize);
	
	public Page<Book> findPageByPageNum(int pageNum, int pageSize, int minPrice, int maxPrice);
}
