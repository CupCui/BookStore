package pers.garre.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.garre.bookstore.bean.Book;
import pers.garre.bookstore.bean.Page;
import pers.garre.bookstore.service.impl.BookServiceImpl;

public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	BookServiceImpl bookServiceImpl = new BookServiceImpl();
	
	/**
	 * 根据页数查询图书列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNum = 1;
		int pageSize = 4;
		
		String pageNum1 = request.getParameter("pageNum");
		if (pageNum1!=null && !"".equals(pageNum1)) {
			pageNum = Integer.parseInt(pageNum1);
		}
		
		Page<Book> page = bookServiceImpl.findPageByPageNum(pageNum, pageSize);
		
		
		request.setAttribute("page", page);
		request.getRequestDispatcher("pages/manager/book_manager.jsp").forward(request, response);
		
	}
	
	protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("book_name");
		String author = request.getParameter("book_author");
		String price = request.getParameter("book_price");
		String sales = request.getParameter("book_sales");
		String stock = request.getParameter("book_stock");
		
		double price1 = 0;
		if (!"".equals(price)) {
			price1 = Double.parseDouble(price);
		}
		int sales1 = 0;
		if (!"".equals(sales)) {
			sales1 = Integer.parseInt(sales);
		}
		int stock1 = 0;
		if (!"".equals(stock)) {
			stock1 = Integer.parseInt(stock);
		}
		
		
		Book book = new Book(null, title, author, price1, sales1, stock1, "");
		
		bookServiceImpl.addBook(book);
		response.sendRedirect("BookServlet?event=findAllBooks");
	}

	protected void updateBefore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		
		Book book = bookServiceImpl.findBookById(bookId);
		
		request.setAttribute("book", book);
		request.getRequestDispatcher("pages/manager/book_edit.jsp").forward(request, response);
	}

	protected void updateBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		String title = request.getParameter("book_name");
		String author = request.getParameter("book_author");
		String price = request.getParameter("book_price");
		String sales = request.getParameter("book_sales");
		String stock = request.getParameter("book_stock");
		
		double price1 = 0;
		if (!"".equals(price)) {
			price1 = Double.parseDouble(price);
		}
		int sales1 = 0;
		if (!"".equals(sales)) {
			sales1 = Integer.parseInt(sales);
		}
		int stock1 = 0;
		if (!"".equals(stock)) {
			stock1 = Integer.parseInt(stock);
		}
		
		Book book = new Book(Integer.parseInt(bookId), title, author, price1, sales1, stock1, "");

		boolean result = bookServiceImpl.updateBookById(book);
		
		if (!result) {
			request.getRequestDispatcher("pages/manager/book_edit.jsp").forward(request, response);
		}
		response.sendRedirect("BookServlet?event=findAllBooks");
	}
	
	protected void deleteBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		
		boolean result = bookServiceImpl.deleteBookById(bookId);
		
		if (!result) {
			request.getRequestDispatcher("pages/manager/book_edit.jsp").forward(request, response);
		}
		response.sendRedirect("BookServlet?event=findAllBooks");
		
	}
	
}








