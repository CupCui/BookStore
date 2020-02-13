package pers.garre.bookstore.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.garre.bookstore.bean.User;

public class LoginFilter extends HttpFilter {
	
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		User user = (User)request.getSession().getAttribute("user");
		
		if (user != null) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect("pages/user/login.jsp");
		}
	}

}
