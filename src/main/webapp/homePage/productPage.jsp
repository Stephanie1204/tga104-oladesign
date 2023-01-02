<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page import="java.util.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.product.model.product.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.order.model.*"%>
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/homePage/css/productPage.css"
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
							src="${ prod.productImgBase64 }" alt="No Image">
					</div>
					<div class="product__details__pic__slider owl-carousel">
						<img data-imgbigurl="${ prod.productImgBase64 }"
							src="${ prod.productImgBase64 }" alt="">
						<jsp:useBean id="proSvc" scope="page"
							class="com.tibame.tga104.g2.oladesign.product.model.product.ProductService" />
						<c:if test="${not empty proSvc.getImages(prod.productId)}">
							<c:forEach var="row_image"
								items="${proSvc.getImages(prod.productId)}">
								<img data-imgbigurl="${row_image.productImgBase64}"
									src="${row_image.productImgBase64}" alt="">
							</c:forEach>
						</c:if>
					</div>
				</div>
			</div>
			<div class="col-lg-6 col-md-6">
				<div class="product__details__text">
					<h3>${ prod.name }</h3>
					<div class="product__details__price">${ prod.price }$</div>
					<p>${ prod.typeName }</p>
					<p>${ prod.styleName }</p>
					<!-- add to cart form -->
					<form action="<c:url value="/pages/product.controller"/>"
						method="get">
						<div class="product__details__quantity">
							<div class="quantity">
								<div class="pro-qty">
									<input type="text" name="quantity" value="1">
								</div>
							</div>
						</div>
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
						<li><a class="shop-connect"
							href="<c:url value="../shophome/shopinfo.jsp"><c:param name="comTaxId" value="${prod.comTaxId}" /></c:url>"
							target="_blank">前往賣場</a></li>
					</ul>
				</div>
			</div>
			<div class="col-lg-12">
				<div class="product__details__tab">
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item"><a class="nav-link active"
							data-toggle="tab" href="#tabs-1" aria-selected="true">Description</a>
						</li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab"
							href="#tabs-2" aria-selected="false">Reviews</a></li>
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
								<c:if test="${not empty proSvc.getComment(prod.productId)}">
									<c:forEach var="row"
										items="${proSvc.getComment(prod.productId)}">
										<div class="star_block">
											<span
												class="star ${ row.commentStar >= 1 ? '
											-on' : '' }"><i
												class="fas fa-star"></i></span> <span
												class="star ${ row.commentStar >= 2 ? '
											-on' : '' }"><i
												class="fas fa-star"></i></span> <span
												class="star ${ row.commentStar >= 3 ? '
											-on' : '' }"><i
												class="fas fa-star"></i></span> <span
												class="star ${ row.commentStar >= 4 ? '
											-on' : '' }"><i
												class="fas fa-star"></i></span> <span
												class="star ${ row.commentStar >= 5 ? '
											-on' : '' }"><i
												class="fas fa-star"></i></span>
											<p>${row.comment }</p>
										</div>
									</c:forEach>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
	<!-- Product Details Section End -->

	<!-- Footer Section Begin -->
	<%@ include file="../include/footer.jsp"%>
	<!-- Footer Section End -->

	<!-- Js Plugins -->
	<script
		src="<%=request.getContextPath()%>/homePage/js/jquery-3.3.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/homePage/js/bootstrap.min.js"></script>
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

	<%@ include file="../include/favorite.jsp"%>
</body>

</html>