<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css" />    
<link rel="stylesheet" href="../css/footer.css" type="text/css" />    
<!-- Footer Section Begin -->
	<footer class="footer spad">
		<div class="container">
			<div class="footercontent">
				<div class="footer__logo">
					<div class="footer__about">
						<div class="footer__about__logo">
							<a href="#"><img src="../img/OLA_Logo.svg" alt="logo" /></a>
						</div>
						<ul>
							<li><i class="fa-solid fa-envelope"></i> oladesign02@gmail.com</li>
						</ul>
					</div>
				</div>
				<div class="footer__link">
					<div class="footer__widget">
						<ul>
							<li><h6>探索</h6></li>
							<li><a href="../shophome/shoplist.jsp">設計館</a></li>
						</ul>
						<ul>
							<li><h6>會員</h6></li>
							<li><a href="<%=request.getContextPath()%>/memberCenter/pages/accountBasicInfo.html?memId=${memId}">會員中心</a></li>
							<li><a href="<%=request.getContextPath()%>/CompanyBackEnd/company-index.jsp">賣家中心</a></li>
							<li><a href="<%=request.getContextPath()%>/member/membershipTerms.jsp" target="_blank">會員條款</a></li>
							
						</ul>
						<ul>
							<li><h6>關於OLA Design</h6></li>
							<li><a href="<%=request.getContextPath()%>/homePage/about.jsp">關於我們</a></li>
							<li><a href="<%=request.getContextPath()%>/member/privacyTerms.jsp" target="_blank">隱私權政策</a></li>
						</ul>
					</div>
				</div>

			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="footer__copyright">
						<div class="footer__copyright__text">
							<p>
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;
								<script>
									document.write(new Date().getFullYear());
								</script>
								All rights reserved | This template is made with <i
									class="fa fa-heart" aria-hidden="true"></i> by <a
									href="https://colorlib.com" target="_blank">Colorlib</a>
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- Footer Section End -->