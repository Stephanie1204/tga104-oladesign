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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Shopping cart</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/homePage/css/cart.css"
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
		data-setbg="<%=request.getContextPath()%>/homePage/img/breadcrumb.jpg">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<div class="breadcrumb__text">
					<h2>Shopping Cart</h2>
					<div class="breadcrumb__option">
						<a href="./index.html">Home</a> <span>Shopping Cart</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
	<!-- Breadcrumb Section End -->

	<!-- Shoping Cart Section Begin -->
	<section class="shoping-cart spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="shoping__cart__table">
					<c:if test="${not empty saler}">
						<c:forEach var="row_saler" items="${saler}">
							<table>
								<thead>
									<tr>
										<th class="shoping__product">Products</th>
										<th>Price</th>
										<th>Quantity</th>
										<th></th>
									</tr>
								</thead>

								<tbody>
									<jsp:useBean id="proSvc" scope="page"
										class="com.tibame.tga104.g2.oladesign.product.model.product.ProductService" />
									<c:forEach var="row_product"
										items="${proSvc.selectCart(userId, row_saler)}">
										<tr>
											<td class="shoping__cart__item"><img
												src="${ row_product.productImgBase64 }" alt="">
												<h5>${ row_product.name }</h5></td>
											<td class="shoping__cart__price">$${ row_product.price }</td>
											<td class="shoping__cart__quantity">
												<form action="<c:url value="/pages/product.controller" />"
													method="post">
													<input type="hidden" name="productId"
														value="${ row_product.productId }"> <input
														type="hidden" name="comTaxId"
														value="${ row_product.comTaxId }"> <input
														type="hidden" name="prodaction" value="UpdateCart">
													<select id="qty" size="1" name="quantity"
														onchange="submit();">
														<option value="1"
															${ row_product.cartQuantity == 1 ? 'selected': ""}>1
														<option value="2"
															${ row_product.cartQuantity == 2 ? 'selected': ""}>2
														<option value="3"
															${ row_product.cartQuantity == 3 ? 'selected': ""}>3
														<option value="4"
															${ row_product.cartQuantity == 4 ? 'selected': ""}>4
														<option value="5"
															${ row_product.cartQuantity == 5 ? 'selected': ""}>5
														<option value="6"
															${ row_product.cartQuantity == 6 ? 'selected': ""}>6
														<option value="7"
															${ row_product.cartQuantity == 7 ? 'selected': ""}>7
														<option value="8"
															${ row_product.cartQuantity == 8 ? 'selected': ""}>8
														<option value="9"
															${ row_product.cartQuantity == 9 ? 'selected': ""}>9
													</select>
												</form>

											</td>
											<td class="shoping__cart__item__close">
												<form action="<c:url value="/pages/product.controller" />"
													method="post">
													<input type="hidden" name="productId"
														value="${row_product.productId }"> <input
														type="hidden" name="comTaxId"
														value="${row_product.comTaxId }">
													<button type="submit" name="prodaction"
														value="DeleteFromCart" class="btn-outline-success">
														<span class="icon_close"></span>
													</button>
												</form>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6">
				<div class="shoping__continue">
					<div class="shoping__discount">
						<h5>Discount Codes</h5>
						<form action="#">
							<input type="text" placeholder="Enter your coupon code">
							<button type="submit" class="site-btn">APPLY COUPON</button>
						</form>
					</div>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="shoping__checkout">
					<h5>Cart Total</h5>
					<ul>
						<li>Subtotal <span>$454.98</span></li>
						<li>Total <span>$454.98</span></li>
					</ul>
					<a href="<%=request.getContextPath()%>/homePage/checkOut.jsp"
						class="primary-btn">結帳</a>
				</div>
			</div>
		</div>
	</div>
	</section>
	<!-- Shoping Cart Section End -->

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
					<p>Get E-mail updates about our latest shop and special offers.</p>
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

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>


</body>
</html>