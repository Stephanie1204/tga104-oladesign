<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page import="java.util.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.product.model.product.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.order.model.*"%>
<%
ProductService prodSvc = new ProductService();
String userId = "220001";
if (userId != null) {

	List<String> cartProductSaler = prodSvc.selectSaler(userId);

	pageContext.setAttribute("saler", cartProductSaler);
	pageContext.setAttribute("userId", userId);

}
%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Ogani | Template</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet" />

<!-- Css Styles -->
<!-- <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/homePage/css/font-awesome.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/homePage/css/elegant-icons.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/homePage/css/nice-select.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/homePage/css/jquery-ui.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/homePage/css/owl.carousel.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/homePage/css/slicknav.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/homePage/css/style.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/homePage/css/conditionBar.css"
	type="text/css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous" />
</head>

<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Humberger Begin -->
	<!-- Home/shop/pages/blog/contact bar while mobile -->
	<div class="humberger__menu__overlay"></div>
	<div class="humberger__menu__wrapper">
		<!-- organi logo -->
		<div class="humberger__menu__logo">
			<a href="<%=request.getContextPath()%>/homePage/index.jsp"><img
				src="<%=request.getContextPath()%>/homePage/img/OLA_Logo.svg" alt="" /></a>
		</div>

		<!-- shopping cart and heart on the right -->

		<div class="humberger__menu__widget">
			<!-- login button on the top-->
			<div class="header__top__right__auth">
				<a href="#"><i class="fa fa-user"></i>登入</a>
			</div>
		</div>

		<nav class="humberger__menu__nav mobile-menu">
			<ul>
				<li class="active"><a
					href="<%=request.getContextPath()%>/homePage/index.jsp">首頁</a></li>
				<li><a href="./shop-grid.html">Shop</a></li>
				<li><a
					href="<%=request.getContextPath()%>/homePage/checkOut.jsp">結帳</a></li>
				<li><a href="./blog.html">Blog</a></li>
				<li><a href="./contact.html">Contact</a></li>
			</ul>
		</nav>
		<div id="mobile-menu-wrap"></div>

		<!-- info on left-top -->

		<div class="humberger__menu__contact">
			<ul>
				<li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
			</ul>
		</div>
	</div>
	<!-- Humberger End -->

	<!-- Header Section Begin -->
	<!-- header while on PC -->
	<header class="header">
		<div class="container">
			<div class="row">
				<div class="col-lg-2">
					<div class="header__logo">
						<a href="<%=request.getContextPath()%>/homePage/index.jsp"><img
							src="<%=request.getContextPath()%>/homePage/img/OLA_Logo.svg"
							alt="" /></a>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="hero__search__form">
						<form action="<c:url value="/pages/product.controller" />"
							method="get">
							<input type="text" id="name" placeholder="What do yo u need?"
								name="name" value="${param.name}" />
							<button type="submit" name="prodaction" value="Select"
								class="site-btn">SEARCH</button>
						</form>
					</div>
					<nav class="header__menu">
						<ul>
							<li class="active"><a
								href="<%=request.getContextPath()%>/homePage/index.jsp">首頁</a></li>
							<li><a href="./shop-grid.html">Shop</a></li>
							<li><a
								href="<%=request.getContextPath()%>/homePage/checkOut.jsp">結帳</a></li>
							<li><a href="./blog.html">Blog</a></li>
							<li><a href="./contact.html">Contact</a></li>
						</ul>
					</nav>
				</div>

				<div class="col-lg-4">
					<div class="header__cart">
						<ul class="shopping-cart">
							<li class="shopping-cart-li">
								<button type="button" class="btn btn-secondary dropdown-toggle"
									data-bs-toggle="dropdown" aria-expanded="false">會員功能</button>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item" href="#">Action</a></li>
									<li><a class="dropdown-item" href="#">Another action</a></li>
									<li><a class="dropdown-item" href="#">Something else
											here</a></li>
									<li><hr class="dropdown-divider"></li>
									<li><a class="dropdown-item" href="#">Separated link</a></li>
								</ul>
								<button type="button" class="btn btn-secondary"
									aria-expanded="false">
									<i class="fa fa-shopping-bag"> <a
										href="<%=request.getContextPath()%>/homePage/shopping_cart.jsp">購物車</a></i>
								</button>
								<button type="button" class="btn btn-secondary"
									aria-expanded="false">
									<i class="fa fa-user"><a href="#">登入</a></i>
								</button>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="humberger__open">
				<i class="fa fa-bars"></i>
			</div>
		</div>
	</header>
	<!-- Header Section End -->

	<!-- Breadcrumb Section Begin -->
	<section class="breadcrumb-section set-bg"
		data-setbg="img/breadcrumb.jpg">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="breadcrumb__text">
						<h2>Checkout</h2>
						<div class="breadcrumb__option">
							<a href="<%=request.getContextPath()%>/homePage/index.jsp">Home</a>
							<span>Checkout</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Breadcrumb Section End -->

	<!-- Checkout Section Begin -->
	<section class="checkout spad">
		<div class="container">
			<div class="checkout__form">
				<h4>Billing Details</h4>
				<form action="#">
					<div class="row">
						<div class="col-lg-8 col-md-6">
							<div class="row">
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											Fist Name<span>*</span>
										</p>
										<input type="text">
									</div>
								</div>
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											Last Name<span>*</span>
										</p>
										<input type="text">
									</div>
								</div>
							</div>
							<div class="checkout__input">
								<p>
									Country<span>*</span>
								</p>
								<input type="text">
							</div>
							<div class="checkout__input">
								<p>
									Address<span>*</span>
								</p>
								<input type="text" placeholder="Street Address"
									class="checkout__input__add"> <input type="text"
									placeholder="Apartment, suite, unite ect (optinal)">
							</div>
							<div class="checkout__input">
								<p>
									Town/City<span>*</span>
								</p>
								<input type="text">
							</div>
							<div class="checkout__input">
								<p>
									Country/State<span>*</span>
								</p>
								<input type="text">
							</div>
							<div class="checkout__input">
								<p>
									Postcode / ZIP<span>*</span>
								</p>
								<input type="text">
							</div>
							<div class="row">
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											Phone<span>*</span>
										</p>
										<input type="text">
									</div>
								</div>
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											Email<span>*</span>
										</p>
										<input type="text">
									</div>
								</div>
							</div>
							<div class="btn-submit">
								<!-- 傳送select參數給action指向的servlet -->
								<input type="button" id="btn-clear" value="Clear">
							</div>
						</div>
						<div class="col-lg-4 col-md-6">
							<div class="checkout__order">
								<h4>Your Order</h4>
								<div class="checkout__order__products">
									Products <span>Total</span>
								</div>
								<ul>
									<c:if test="${not empty saler}">
										<c:forEach var="row_saler" items="${saler}">
											<jsp:useBean id="proSvc" scope="page"
												class="com.tibame.tga104.g2.oladesign.product.model.product.ProductService" />
											<c:forEach var="row_product"
												items="${proSvc.selectCart(userId, row_saler)}">
												<li id="productName">${ row_product.name }</li>
												<li>數量${row_product.cartQuantity }<span>$${
														row_product.price * row_product.cartQuantity}</span>
												</li>
											</c:forEach>
											<span>--------------------------</span>
										</c:forEach>
									</c:if>
								</ul>
								<div class="checkout__order__subtotal">
									Subtotal <span>$750.99</span>
								</div>
								<div class="checkout__order__total">
									Total <span>$750.99</span>
								</div>
								<div class="checkout__input__checkbox">
									<label for="acc-or"> Create an account? <input
										type="checkbox" id="acc-or"> <span class="checkmark"></span>
									</label>
								</div>
								<p>Lorem ipsum dolor sit amet, consectetur adip elit, sed do
									eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
								<div class="checkout__input__checkbox">
									<label for="payment"> Check Payment <input
										type="checkbox" id="payment"> <span class="checkmark"></span>
									</label>
								</div>
								<div class="checkout__input__checkbox">
									<label for="paypal"> Paypal <input type="checkbox"
										id="paypal"> <span class="checkmark"></span>
									</label>
								</div>
								<button type="submit" class="site-btn">PLACE ORDER</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</section>
	<!-- Checkout Section End -->

	<!-- Footer Section Begin -->
	<footer class="footer spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="footer__about">
						<div class="footer__about__logo">
							<a href="./index.html"><img src="img/logo.png" alt=""></a>
						</div>
						<ul>
							<li>Address: 60-49 Road 11378 New York</li>
							<li>Phone: +65 11.188.888</li>
							<li>Email: hello@colorlib.com</li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-6 offset-lg-1">
					<div class="footer__widget">
						<h6>Useful Links</h6>
						<ul>
							<li><a href="#">About Us</a></li>
							<li><a href="#">About Our Shop</a></li>
							<li><a href="#">Secure Shopping</a></li>
							<li><a href="#">Delivery infomation</a></li>
							<li><a href="#">Privacy Policy</a></li>
							<li><a href="#">Our Sitemap</a></li>
						</ul>
						<ul>
							<li><a href="#">Who We Are</a></li>
							<li><a href="#">Our Services</a></li>
							<li><a href="#">Projects</a></li>
							<li><a href="#">Contact</a></li>
							<li><a href="#">Innovation</a></li>
							<li><a href="#">Testimonials</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4 col-md-12">
					<div class="footer__widget">
						<h6>Join Our Newsletter Now</h6>
						<p>Get E-mail updates about our latest shop and special
							offers.</p>
						<form action="#">
							<input type="text" placeholder="Enter your mail">
							<button type="submit" class="site-btn">Subscribe</button>
						</form>
						<div class="footer__widget__social">
							<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
								class="fa fa-instagram"></i></a> <a href="#"><i
								class="fa fa-twitter"></i></a> <a href="#"><i
								class="fa fa-pinterest"></i></a>
						</div>
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
						<div class="footer__copyright__payment">
							<img src="img/payment-item.png" alt="">
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- Footer Section End -->

	<!-- Js Plugins -->
	<script
		src="<%=request.getContextPath()%>/homePage/js/jquery-3.3.1.min.js"></script>
	<!-- <script src="js/bootstrap.min.js"></script> -->
	<script
		src="<%=request.getContextPath()%>/homePage/js/jquery.nice-select.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/homePage/js/jquery-ui.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/homePage/js/jquery.slicknav.js"></script>
	<script src="<%=request.getContextPath()%>/homePage/js/mixitup.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/homePage/js/owl.carousel.min.js"></script>
	<script src="<%=request.getContextPath()%>/homePage/js/main.js"></script>
	<script src="<%=request.getContextPath()%>/homePage/js/clearInput.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>



</body>

</html>