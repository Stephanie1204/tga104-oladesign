<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
Object objname = session.getAttribute("memId");
String userId = "";
if (objname != null) {
	userId = objname.toString();
}

pageContext.setAttribute("userId", userId);
%>
<!DOCTYPE html>
<html lang="zxx">
<head>
<meta charset="UTF-8" />
<meta name="description" content="Ogani Template" />
<meta name="keywords" content="Ogani, unica, creative, html" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>OLA Design | 首頁</title>

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
	href="<%=request.getContextPath()%>/homePage/css/searchResults.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/homePage/css/favorite.css"
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


	<!-- Header Section Begin -->
	<%@ include file="../include/header.jsp"%>
	<!-- Header Section End -->

	<!-- Hero Section Begin -->
	<!-- middle part(all department and the big picture in the middle) -->
	<section class="hero">
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="hero__categories">
						<div class="hero__categories__all">
							<i class="fa fa-bars"></i> <span>條件篩選</span>
						</div>
						<!-- 送到value指定的servlet -->
						<form action="<c:url value="/pages/product.controller" />"
							method="get">
							<ul>
								<div class="conditions">
									<label for="typeCode">商品類別 </label>
									<jsp:useBean id="typeSvc" scope="page"
										class="com.tibame.tga104.g2.oladesign.product.model.type.TypeService" />
									<select id="type" size="1" name="typeCode">
										<option value="">無
											<c:forEach var="typeBean" items="${typeSvc.getAll()}">
												<option value="${typeBean.typeCode}">${typeBean.typeName}
											</c:forEach>
									</select>
								</div>

								<div class="conditions">
									<label for="styleCode">商品風格 </label>
									<jsp:useBean id="styleSvc" scope="page"
										class="com.tibame.tga104.g2.oladesign.product.model.style.StyleService" />
									<select id="style" size="1" name="styleCode">
										<option value="">無
											<c:forEach var="styleBean" items="${styleSvc.getAll()}">
												<option value="${styleBean.styleCode}">${styleBean.styleName}
											</c:forEach>
									</select>
								</div>

								<div class="conditions">
									<label for="price">價格(以下) </label> <input type="text"
										name="price" value="${param.price}">
								</div>
								<span class="error">${errors.price}</span>
								<div class="btn-submit">
									<input type="submit" name="prodaction" value="Select">
									<!-- 傳送select參數給action指向的servlet -->
									<input type="button" id="btn-clear" value="Clear">
								</div>
							</ul>
						</form>
					</div>
				</div>
				<!-- the searh input part -->
				<div class="col-lg-9">
					<!-- big picture with vege and fruits in the middle -->
					<!-- 輪播圖開始 -->
					<div id="carouselExampleControls" class="carousel slide"
						data-bs-ride="carousel">
						<div class="carousel-inner" id="todayAD"></div>
						<button class="carousel-control-prev" type="button"
							data-bs-target="#carouselExampleControls" data-bs-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Previous</span>
						</button>
						<button class="carousel-control-next" type="button"
							data-bs-target="#carouselExampleControls" data-bs-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Next</span>
						</button>
					</div>
					<!-- 輪播圖結束 -->
				</div>
			</div>
		</div>
	</section>
	<!-- Hero Section End -->
	<section class="products">
		<div class="prodcontainer">
			<h3>${fn:length(select)}項搜尋結果</h3>
			<input type="hidden" name="result" id="resultNum"
				value="${fn:length(select)}">

			<c:if test="${not empty select}">
				<div
					class="productDisplay row row-cols-lg-4 row-cols-md-3 row-cols-sm-2 mix">
					<c:forEach var="row" items="${select}">
						<div class="featured__item">
							<div class="featured__item__pic">
								<a
									href="<c:url value="../homePage/productPage.jsp"><c:param name="productId" value="${row.productId}" /></c:url>"
									class="results" target="_blank" class="set-bg"
									data-setbg="img/featured/feature-4.jpg"><img
									class="product__details__pic__item--large"
									src="${ row.productImgBase64 }" alt="No Image"></a>
								<ul class="featured__item__pic__hover">

									<li><a href="##" class="favorcircle"> <i
											class="fa fa-heart favorheart" id="${row.productId}"
											data="${row.productId}"></i>
									</a></li>

									<li>
										<form action="<c:url value="/pages/product.controller" />"
											method="post">
											<input type="hidden" name="prodaction" value="AddCartByPage">
											<input type="hidden" name="memberId" value="${userId }">
											<input type="hidden" name="comTaxId" value="${row.comTaxId }">
											<input type="hidden" name="productId"
												value="${row.productId }"> <input type="hidden"
												name="quantity" value="1"> <input type="hidden"
												name="typeCode" value="${param.typeCode }"> <input
												type="hidden" name="styleCode" value="${param.styleCode }">
											<input type="hidden" name="price" value="${param.price }">
											<button type="submit" class="fa fa-shopping-cart"></button>
										</form>
									</li>
								</ul>
							</div>
							<div class="featured__item__text">
								<h6>
									<a
										href="<c:url value="../homePage/productPage.jsp"><c:param name="productId" value="${row.productId}" /></c:url>"
										class="results" target="_blank">${row.getName()}</a>
								</h6>
								<h5>${row.getPrice()}</h5>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:if>
		</div>
	</section>


	<!-- Footer Section Begin -->
	<footer class="footer spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="footer__about">
						<div class="footer__about__logo">
							<a href="./index.html"><img
								src="<%=request.getContextPath()%>/homePage/img/logo.png" alt="" /></a>
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
							<input type="text" placeholder="Enter your mail" />
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
							<img
								src="<%=request.getContextPath()%>/homePage/img/payment-item.png"
								alt="" />
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

	<script>
	$(window).on("load", function(){
		let memId = "${memId}";
	    console.log("memId=" + memId);
	  //先查詢已經加入收藏的商品
	    if(memId.length != 0){ 
    		$.ajax({
    			url: "<%=request.getContextPath()%>/favorite/FavorServlet",
    			type: "PUT",
    			data: JSON.stringify({
    				"memId": memId
    			}),
        		dataType: "json",
        		contentType: "application/json",
        		processData: false,
        		success: function(data){
	            	console.log("data");
	            	var favorObj = JSON.parse(data.getFavor);
	            	console.log(favorObj);
	            	console.log("favorObj.length:" + favorObj.length);
	            	$("#fav").text(favorObj.length); //在收藏icon顯示收藏數量
	            	
	            	$.each(favorObj, function(index, item){ //取出已收藏的商品ID
		            	console.log(item.prodId);
	            		$("#" + item.prodId + "").addClass("active");
	            		$("#" + item.prodId + "").closest("a").addClass("active");
		            });	            	
	            },
	            error: function(xhr){
	            	console.log(xhr);
	            },
	            complete: function(xhr){
	            	console.log(xhr);
	            }
    		});
    	}
	  
	  	//新增收藏
	    $(".favorcircle").on("click", function(e){
	    	if(memId == null || memId.length == 0){
	    		alert("請先登入會員");
	    	}else{
	    		let prodId = $(e.target).attr("data");
	    		console.log("prodId=" + prodId);
	    		$(e.target).toggleClass("active");
		        $(e.target).closest("a").toggleClass("active");
		        
		        var favordata ={
		        		"memId": memId,
		        		"prodId": prodId
		        };
		        console.log(favordata);
		        let active = $(e.target).hasClass("active");
		        console.log("active=" + active);
		        if(active == true){ //新增收藏
		        	$.ajax({
			            url: "<%=request.getContextPath()%>/favorite/FavorServlet",
			            type: "POST",
			            data: JSON.stringify(favordata),
			            dataType: "json",
			            contentType: "application/json",
			            processData: false,
			            success: function(adddata){
			            	console.log("adddata=" + adddata);
			            	var count = $("#fav").text();
			            	var countFav = parseInt(count); //String 轉為 number
			            	$("#fav").text(countFav + 1);
			            },
			            error: function(xhr){
			            	console.log(xhr);
			            },
			            complete: function(xhr){
			            	console.log(xhr);
			            }
			        });
		        }else{ //移除收藏
		        	$.ajax({
		        		url: "<%=request.getContextPath()%>
		/favorite/FavorServlet",
																	type : "DELETE",
																	data : JSON
																			.stringify(favordata),
																	dataType : "json",
																	contentType : "application/json",
																	processData : false,
																	success : function(
																			deldata) {
																		console
																				.log("deldata");
																		console
																				.log(deldata);
																		var count = $(
																				"#fav")
																				.text();
																		var countFav = parseInt(count); //String 轉為 number
																		$(
																				"#fav")
																				.text(
																						countFav - 1);
																	},
																	error : function(
																			xhr) {
																		console
																				.log(xhr);
																	},
																	complete : function(
																			xhr) {
																		console
																				.log(xhr);
																	}
																});
													}

												}

											});
						});
	</script>
	=======


	<%@ include file="../include/favorite.jsp"%>

	>>>>>>> dev
</body>
</html>
