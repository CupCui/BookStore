package pers.garre.bookstore.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.garre.bookstore.bean.Book;
import pers.garre.bookstore.bean.Page;
import pers.garre.bookstore.service.impl.BookServiceImpl;

public class BookClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	BookServiceImpl bookServiceImpl = new BookServiceImpl();
       
	/**
	 * 根据页数查询图书列表 display in client page
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pageNum = 1;
		int pageSize = 4;
		int minPrice = 0;
		int maxPrice = Integer.MAX_VALUE;
		
		String pageNum1 = request.getParameter("pageNum");
		String minPrice1 = request.getParameter("minPrice");
		String maxPrice1 = request.getParameter("maxPrice");
		
		if (pageNum1!=null && !"".equals(pageNum1)) {
			pageNum = Integer.parseInt(pageNum1);
		}
		if (minPrice1!=null && !"".equals(minPrice1)) {
			minPrice = Integer.parseInt(minPrice1);
			request.setAttribute("minPrice", minPrice);
		}
		if (maxPrice1!=null && !"".equals(maxPrice1)) {
			maxPrice = Integer.parseInt(maxPrice1);
			request.setAttribute("maxPrice", maxPrice);
		}
		
		Page<Book> page = bookServiceImpl.findPageByPageNum(pageNum, pageSize, minPrice, maxPrice);
		
		request.setAttribute("page", page);
		request.getRequestDispatcher("pages/client/book_client.jsp").forward(request, response);
	}

}
