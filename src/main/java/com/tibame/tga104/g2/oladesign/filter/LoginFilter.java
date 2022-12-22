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

@WebFilter(
		filterName = "LoginFilter",
		urlPatterns = {"/CompanyBackEnd/regisToCom.jsp",
					  "/CompanyBackEnd/company_member.do"}
		)
public class LoginFilter extends HttpFilter implements Filter {
  
	private static final long serialVersionUID = 1L;
	private FilterConfig config; 
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}
    
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = request.getSession();
		Integer memId = (Integer) session.getAttribute("memId");
		if(memId == null) {
			session.setAttribute("location", request.getRequestURI());
			response.setContentType("text/html; charset=UTF-8");
//			response.getWriter().write("<script> setTimeout(function(){ alert('請先登入或註冊為會員');" + 
//										"window.location.href='" + request.getContextPath() +"/member/login.jsp';}, 500);</script>");
			response.sendRedirect(request.getContextPath() + "/member/login.jsp");
			return;
		}else {
			chain.doFilter(request, response);
		}		
	}

	@Override
	public void destroy() {		
		config = null;
	}
}
