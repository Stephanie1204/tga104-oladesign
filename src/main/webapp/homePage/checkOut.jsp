<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page import="java.util.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.product.model.product.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.order.model.*"%>
<%
Object objname = session.getAttribute("memId");
String userId = "";
if (objname != null) {
	userId = objname.toString();
}
ProductService prodSvc = new ProductService();

if (userId != null) {

	List<String> cartProductSaler = prodSvc.selectSaler(userId);

	pageContext.setAttribute("saler", cartProductSaler);
	pageContext.setAttribute("userId", userId);

}
//
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/homePage/css/checkOut.css"
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
	<%@ include file="../include/header.jsp"%>

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
				<form action="<c:url value="/pages/order.controller"/>"
					method="post">
					<div class="row">
						<div class="col-lg-8 col-md-6">
							<div class="col-lg-6">
								<div class="checkout__input">
									<p>
										收件人<span>*</span>
									</p>
									<input type="text" name="receiver" value="${param.receiver }">
									<span class="error">${errors.receiver}</span>
								</div>
								<div class="checkout__input">
									<p>
										收件地址<span>*</span>
									</p>
									<input type="text" class="checkout__input__add" name="address"
										value="${param.address }"> <span class="error">${errors.address}</span>
								</div>
								<div class="checkout__input">
									<p>
										郵遞區號<span>*</span>
									</p>
									<input type="text" name="address_zone"> <span
										class="error">${errors.address_zone}</span>
								</div>
							</div>


							<input type="hidden" name="coupon" value="${param.coupon }">
							<div class="shoping__discount">
								<jsp:useBean id="orderSvc" scope="page"
									class="com.tibame.tga104.g2.oladesign.order.model.OrderService" />
								<div>
									<span><h5>所持紅利: ${orderSvc.getPoint(userId)}</h5></span>
								</div>
								<input type="text" placeholder="use your points"
									name="point_use" value="${param.point_use }"> <span
									class="error">${errors.point_use}</span> <span class="error">${errors.pointError}</span>
							</div>

						</div>
						<div class="col-lg-4 col-md-6">
							<div class="checkout__order">
								<h4>Your Order</h4>
								<div class="checkout__order__products">
									Products <span>Total</span>
								</div>
								<ul>
									<jsp:useBean id="proSvc" scope="page"
										class="com.tibame.tga104.g2.oladesign.product.model.product.ProductService" />
									<c:forEach var="row_product"
										items="${proSvc.selectCart(userId, param.comTaxId)}">
										<li id="productName">${ row_product.name }</li>
										<li>數量${row_product.cartQuantity }<span>$${
												row_product.price * row_product.cartQuantity}</span>
										</li>
									</c:forEach>
								</ul>

								<div class="checkout__order__subtotal">
									合計 <span>${orderSvc.getTotalPrice(userId, param.comTaxId)}</span>
								</div>
								<div class="checkout__order__total">
									折扣後 <span>${AfterDiscount }</span>
								</div>
								<input type="hidden" name="comTaxId" value="${param.comTaxId }">
								<input type="hidden" name="memberId" value="${userId }">
								<button type="submit" name="prodaction" value="PlaceOrder"
									class="site-btn">結帳</button>
							</div>
						</div>
					</div>
				</form>
				<div class="shoping__discount">
					<h5>折扣碼</h5>
					<form action="<c:url value="/pages/order.controller"/>"
						method="post">
						<input type="text" placeholder="Enter your coupon code"
							name="coupon" value="${param.coupon }"> <input
							type="hidden" name="comTaxId" value="${param.comTaxId }">
						<input type="hidden" name="memberId" value="${userId }">
						<button type="submit" class="site-btn" name="prodaction"
							value="Coupon">APPLY</button>
					</form>
					<span class="error">${errors.couponError}</span>
				</div>
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

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>



</body>

</html>