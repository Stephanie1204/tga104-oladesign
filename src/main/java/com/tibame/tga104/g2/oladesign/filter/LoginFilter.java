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
		urlPatterns = {
				"/CompanyBackEnd/regisToCom.jsp",
//				"/CompanyBackEnd/addadvertisement.jsp",
//				"/CompanyBackEnd/recordadvertisement.jsp",
//				"/CompanyBackEnd/setcompany_member.jsp",
//				"/CompanyBackEnd/listonecompany_forshop.jsp",
				"/memberCenter/pages/accountBasicInfo.html",
				"/memberCenter/pages/accountResetPassword.html",
				"/memberCenter/pages/interMail.html",
				"/memberCenter/pages/interMailAdd.html",
				"/memberCenter/pages/interMailDetail.html",
				"/memberCenter/pages/orderDetail.html",
				"/memberCenter/pages/orderList.html",
				"/memberCenter/pages/orderProductReview.html",
				"/memberCenter/pages/pointManagement.html",
				"/promotion/promotion_front/addPromo.html",
				"/promotion/promotion_front/editPromo.html",
				"/promotion/promotion_front/promoHome.html",
				"/promotion/promotion_front/promoList_add.html"
				
		}
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
