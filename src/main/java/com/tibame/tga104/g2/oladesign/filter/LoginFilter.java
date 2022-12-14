package com.tibame.tga104.g2.oladesign.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter(
//		filterName = "LoginFilter",
//		urlPatterns = ""
//		)
public class LoginFilter extends HttpFilter implements Filter {
  
	private static final long serialVersionUID = 1L;
	private FilterConfig config; 
    
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = request.getSession();
		String account = (String) session.getAttribute("account");
		if(account == null) {
			request.getRequestDispatcher("/member/login.jsp").forward(request, response);
		}else {
			chain.doFilter(request, response);
		}		
	}

	@Override
	public void destroy() {		
		config = null;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}


}
