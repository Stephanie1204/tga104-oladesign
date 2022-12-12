<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page import="java.util.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.product.model.product.*"%>
<%
ProductService prodSvc = new ProductService();
String productId = request.getParameter("productId");
if (productId != null) {
	ProductBean product = prodSvc.selectByProdId(Integer.parseInt(productId));
	pageContext.setAttribute("prod", product);
}
System.out.println("test");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
				src="<%=request.getContextPath()%>/homePage/img/OLA_Logo.svg"
				alt="" /></a>
		</div>

		<!-- shopping cart and heart on the right -->

		<div class="humberger__menu__widget">
			<!-- login button on the top-->
			<div class="header__top__right__auth">
				<a href="#"><i class="fa fa-user"></i> Login</a>
			</div>
		</div>

		<nav class="humberger__menu__nav mobile-menu">
		<ul>
			<li class="active"><a
				href="<%=request.getContextPath()%>/homePage/index.jsp">Home</a></li>
			<li><a href="./shop-grid.html">Shop</a></li>
			<li><a href="#">Pages</a>
				<ul class="header__menu__dropdown">
					<li><a href="./shop-details.html">Shop Details</a></li>
					<li><a
						href="<%=request.getContextPath()%>/homePage/shopping_cart.jsp">Shoping
							Cart</a></li>
					<li><a href="./checkout.html">Check Out</a></li>
					<li><a href="./blog-details.html">Blog Details</a></li>
				</ul></li>
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
						href="<%=request.getContextPath()%>/homePage/index.jsp">Home</a></li>
					<li><a href="./shop-grid.html">Shop</a></li>
					<li><a href="#">Pages</a>
						<ul class="header__menu__dropdown">
							<li><a href="./shop-details.html">Shop Details</a></li>
							<li><a
								href="<%=request.getContextPath()%>/homePage/shopping_cart.jsp">Shoping
									Cart</a></li>
							<li><a href="./checkout.html">Check Out</a></li>
							<li><a href="./blog-details.html">Blog Details</a></li>
						</ul></li>
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
					<h2>Vegetable’s Package</h2>
					<div class="breadcrumb__option">
						<a href="./index.html">Home</a> <a href="./index.html">Vegetables</a>
						<span>Vegetable’s Package</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
	<!-- Breadcrumb Section End -->

	<!-- Product Details Section Begin -->
	<section class="product-details spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-md-6">
				<div class="product__details__pic">
					<div class="product__details__pic__item">
						<!-- <img class="product__details__pic__item--large"
							src="img/product/details/product-details-1.jpg" alt=""> -->
						<img class="product__details__pic__item--large"
							src="${ prod.productImgBase64 }" alt="">
					</div>
					<div class="product__details__pic__slider owl-carousel">
						<img data-imgbigurl="img/product/details/product-details-2.jpg"
							src="img/product/details/thumb-1.jpg" alt=""> <img
							data-imgbigurl="img/product/details/product-details-3.jpg"
							src="img/product/details/thumb-2.jpg" alt=""> <img
							data-imgbigurl="img/product/details/product-details-5.jpg"
							src="img/product/details/thumb-3.jpg" alt=""> <img
							data-imgbigurl="img/product/details/product-details-4.jpg"
							src="img/product/details/thumb-4.jpg" alt="">
					</div>
				</div>
			</div>
			<div class="col-lg-6 col-md-6">
				<div class="product__details__text">
					<h3>${ prod.name }</h3>
					<div class="product__details__rating">
						<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star-half-o"></i> <span>(18 reviews)</span>
					</div>
					<div class="product__details__price">${ prod.price }$</div>
					<p>${ prod.typeName }</p>
					<p>${ prod.styleName }</p>
					<div class="product__details__quantity">
						<div class="quantity">
							<div class="pro-qty">
								<input type="text" value="1">
							</div>
						</div>
					</div>
					<!-- add to cart form -->
					<form action="<c:url value="/pages/product.controller" />"
						method="get">
						<input type="hidden" name="productId" value="${prod.productId}">
						<input type="hidden" name="name" value="${prod.name}"> <input
							type="hidden" name="price" value="${prod.price}"> <input
							type="hidden" name="comTaxId" value="${prod.comTaxId}">
						<button type="submit" name="prodaction" value="AddCart"
							class="primary-btn">加入購物車</button>
					</form>
					<a href="#" class="heart-icon"><span class="icon_heart_alt"></span></a>
					<ul>
						<li><b>商品狀態: </b> <span>${ prod.status ? "上架" : "下架" }</span></li>
						<li><b>庫存</b> <span>${ prod.stock }</span></li>
						<li><b>Weight</b> <span>0.5 kg</span></li>
					</ul>
				</div>
			</div>
			<div class="col-lg-12">
				<div class="product__details__tab">
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item"><a class="nav-link active"
							data-toggle="tab" href="#tabs-1" role="tab" aria-selected="true">Description</a>
						</li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab"
							href="#tabs-2" role="tab" aria-selected="false">Information</a></li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab"
							href="#tabs-3" role="tab" aria-selected="false">Reviews <span>(1)</span></a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="tabs-1" role="tabpanel">
							<div class="product__details__tab__desc">
								<h6>商品描述</h6>
								<p>${ prod.intro }</p>
							</div>
						</div>
						<div class="tab-pane" id="tabs-2" role="tabpanel">
							<div class="product__details__tab__desc">
								<h6>Products Infomation</h6>
								<p>Vestibulum ac diam sit amet quam vehicula elementum sed
									sit amet dui. Pellentesque in ipsum id orci porta dapibus.
									Proin eget tortor risus. Vivamus suscipit tortor eget felis
									porttitor volutpat. Vestibulum ac diam sit amet quam vehicula
									elementum sed sit amet dui. Donec rutrum congue leo eget
									malesuada. Vivamus suscipit tortor eget felis porttitor
									volutpat. Curabitur arcu erat, accumsan id imperdiet et,
									porttitor at sem. Praesent sapien massa, convallis a
									pellentesque nec, egestas non nisi. Vestibulum ac diam sit amet
									quam vehicula elementum sed sit amet dui. Vestibulum ante ipsum
									primis in faucibus orci luctus et ultrices posuere cubilia
									Curae; Donec velit neque, auctor sit amet aliquam vel,
									ullamcorper sit amet ligula. Proin eget tortor risus.</p>
								<p>Praesent sapien massa, convallis a pellentesque nec,
									egestas non nisi. Lorem ipsum dolor sit amet, consectetur
									adipiscing elit. Mauris blandit aliquet elit, eget tincidunt
									nibh pulvinar a. Cras ultricies ligula sed magna dictum porta.
									Cras ultricies ligula sed magna dictum porta. Sed porttitor
									lectus nibh. Mauris blandit aliquet elit, eget tincidunt nibh
									pulvinar a.</p>
							</div>
						</div>
						<div class="tab-pane" id="tabs-3" role="tabpanel">
							<div class="product__details__tab__desc">
								<h6>Products Infomation</h6>
								<p>Vestibulum ac diam sit amet quam vehicula elementum sed
									sit amet dui. Pellentesque in ipsum id orci porta dapibus.
									Proin eget tortor risus. Vivamus suscipit tortor eget felis
									porttitor volutpat. Vestibulum ac diam sit amet quam vehicula
									elementum sed sit amet dui. Donec rutrum congue leo eget
									malesuada. Vivamus suscipit tortor eget felis porttitor
									volutpat. Curabitur arcu erat, accumsan id imperdiet et,
									porttitor at sem. Praesent sapien massa, convallis a
									pellentesque nec, egestas non nisi. Vestibulum ac diam sit amet
									quam vehicula elementum sed sit amet dui. Vestibulum ante ipsum
									primis in faucibus orci luctus et ultrices posuere cubilia
									Curae; Donec velit neque, auctor sit amet aliquam vel,
									ullamcorper sit amet ligula. Proin eget tortor risus.</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
	<!-- Product Details Section End -->

	<!-- Related Product Section Begin -->
	<section class="related-product">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="section-title related__product__title">
					<h2>Related Product</h2>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-3 col-md-4 col-sm-6">
				<div class="product__item">
					<div class="product__item__pic set-bg"
						data-setbg="img/product/product-1.jpg">
						<ul class="product__item__pic__hover">
							<li><a href="#"><i class="fa fa-heart"></i></a></li>
							<li><a href="#"><i class="fa fa-retweet"></i></a></li>
							<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
						</ul>
					</div>
					<div class="product__item__text">
						<h6>
							<a href="#">Crab Pool Security</a>
						</h6>
						<h5>$30.00</h5>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-4 col-sm-6">
				<div class="product__item">
					<div class="product__item__pic set-bg"
						data-setbg="img/product/product-2.jpg">
						<ul class="product__item__pic__hover">
							<li><a href="#"><i class="fa fa-heart"></i></a></li>
							<li><a href="#"><i class="fa fa-retweet"></i></a></li>
							<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
						</ul>
					</div>
					<div class="product__item__text">
						<h6>
							<a href="#">Crab Pool Security</a>
						</h6>
						<h5>$30.00</h5>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-4 col-sm-6">
				<div class="product__item">
					<div class="product__item__pic set-bg"
						data-setbg="img/product/product-3.jpg">
						<ul class="product__item__pic__hover">
							<li><a href="#"><i class="fa fa-heart"></i></a></li>
							<li><a href="#"><i class="fa fa-retweet"></i></a></li>
							<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
						</ul>
					</div>
					<div class="product__item__text">
						<h6>
							<a href="#">Crab Pool Security</a>
						</h6>
						<h5>$30.00</h5>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-4 col-sm-6">
				<div class="product__item">
					<div class="product__item__pic set-bg"
						data-setbg="img/product/product-7.jpg">
						<ul class="product__item__pic__hover">
							<li><a href="#"><i class="fa fa-heart"></i></a></li>
							<li><a href="#"><i class="fa fa-retweet"></i></a></li>
							<li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
						</ul>
					</div>
					<div class="product__item__text">
						<h6>
							<a href="#">Crab Pool Security</a>
						</h6>
						<h5>$30.00</h5>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
	<!-- Related Product Section End -->

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
	<script
		src="<%=request.getContextPath()%>/homePage/js/mixitup.min.js"></script>
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