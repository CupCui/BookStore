BookStore08:
	2020.02.04
	
	1. Change base.jsp base tag to dynamic get.
	2. Add book list. (Book Manager)
	
	3. Page divide
		page:
			private int pageNum;// 当前的页码
			private int pageSize;// 一页多少条
			private int totalPage;// 总页数 
			private int totalSize;// 总条数

		manager.jsp  -->  BookServlet?event=findAllBooks

		findAllBooks: 
			bookServiceImpl.findPageByPageNum
		bookServiceImpl：
			findPageByPageNum();
		bookDao:
			public Page<Book> findPageByPageNum(int pageNum, int pageSize);
			public int count();
		baseDao:
			getValue();	
			
		
			
		book_manager.jsp --> 	

BookStore08:
	2020.02.07
	1. Client book display page
		1.1 Pagination
		1.2 Search
	2. BookServlet
		--> findAllBooks
		

BookStore10:		
		2020.02.08
		1. Image code
		2. Cart
		3. Cart Item
		
		2020.02.10
		1. Cart
		
		2020.02.11
		1. User name duplicate check
		
		
BookStore10:		
		2020.02.12
		1. Order and order item.
			1.1
				OrderServlet?method=checkOut -->
				OrderServlet	-->
				OrderService	-->
				OrderDao
		2. Update Ajax to cart item change.
		
		
		
		
		
		
		
		
		
		
		
		
		