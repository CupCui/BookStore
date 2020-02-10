package pers.garre.bookstore.service.impl;

import java.util.List;

import com.sun.javafx.sg.prism.web.NGWebView;

import pers.garre.bookstore.bean.Book;
import pers.garre.bookstore.bean.Page;
import pers.garre.bookstore.dao.impl.BookDaoImpl;
import pers.garre.bookstore.service.BookService;

public class BookServiceImpl implements BookService {
	BookDaoImpl bookDaoImpl = new BookDaoImpl();

	@Override
	public List<Book> findAllBooks() {
		return bookDaoImpl.findAllBooks();
	}

	@Override
	public boolean addBook(Book book) {
		return bookDaoImpl.addBook(book);
	}

	@Override
	public Book findBookById(String bookId) {
		return bookDaoImpl.findBookById(bookId);
	}

	@Override
	public boolean updateBookById(Book book) {
		return bookDaoImpl.updateBookById(book);
	}

	@Override
	public boolean deleteBookById(String bookId) {
		return bookDaoImpl.deleteBookById(bookId);
	}

	@Override
	public Page<Book> findPageByPageNum(int pageNum, int pageSize) {
		int totalSize = bookDaoImpl.count();
		List<Book> list = bookDaoImpl.findPageByPageNum(pageNum, pageSize);
		
		Page<Book> page = new Page<Book>(pageNum, pageSize, totalSize);
		page.setList(list);
		
		return page;
	}
	
	@Override
	public Page<Book> findPageByPageNum(int pageNum, int pageSize, int minPrice, int maxPrice) {
		int totalSize = bookDaoImpl.count(minPrice, maxPrice);
		List<Book> list = bookDaoImpl.findPageByPageNum(pageNum, pageSize, minPrice, maxPrice);
		
		Page<Book> page = new Page<Book>(pageNum, pageSize, totalSize);
		page.setList(list);
		
		return page;
	}
}
