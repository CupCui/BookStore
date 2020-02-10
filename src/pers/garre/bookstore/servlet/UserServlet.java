package pers.garre.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.garre.bookstore.bean.User;
import pers.garre.bookstore.service.UserService;
import pers.garre.bookstore.service.impl.UserServiceImpl;

public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verifyCode = request.getParameter("verifyCode");

		User user = userService.login(username, password);
		String sessionKeyCode = (String)request.getSession().getAttribute("KAPTCHA_SESSION_KEY");
		
		if (sessionKeyCode.equals(verifyCode)) {
			if (user != null) {
				request.getSession().setAttribute("user", user);
				response.sendRedirect("pages/user/login_success.jsp");
			} else {
				request.setAttribute("username", username);
				request.setAttribute("password", password);
				request.setAttribute("user", user);
				request.getRequestDispatcher("pages/user/login.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("pages/user/login.jsp").forward(request, response);
		}
	}

	protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		boolean b = userService.register(new User(0, username, password, email));
		if (b) {
			request.getRequestDispatcher("pages/user/regist_success.jsp").forward(request, response);
		} else {
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			request.setAttribute("email", email);
			request.getRequestDispatcher("pages/user/regist.jsp").forward(request, response);
		}
	}

	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		
		request.getRequestDispatcher("pages/user/login.jsp").forward(request, response);
	}
}
