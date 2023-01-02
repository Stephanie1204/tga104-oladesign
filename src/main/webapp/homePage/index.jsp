<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<!-- <link rel="stylesheet" -->
<%-- 	href="<%=request.getContextPath()%>/homePage/css/font-awesome.min.css" --%>
<!-- 	type="text/css" /> -->
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

	<!--     header -->

	<%@ include file="../include/header.jsp"%>


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


	<!-- Featured Section Begin -->
	<section class="featured spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-title">
						<h2>商品一覽</h2>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--  -->
	<jsp:useBean id="prodSvc" scope="page"
		class="com.tibame.tga104.g2.oladesign.product.model.product.ProductService" />
	<c:if test="${not empty prodSvc.selectAll()}">
		<div
			class="productDisplay row row-cols-lg-4 row-cols-md-3 row-cols-sm-2 mix">
			<c:forEach var="row" items="${prodSvc.selectAll()}">
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
									<input type="hidden" name="productId" value="${row.productId }">
									<input type="hidden" name="quantity" value="1"> <input
										type="hidden" name="typeCode" value="${param.typeCode }">
									<input type="hidden" name="styleCode"
										value="${param.styleCode }"> <input type="hidden"
										name="price" value="${param.price }">
									<button type="submit" class="fa fa-shopping-bag mycart"></button>
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
						<h5>NT$ ${row.getPrice()}</h5>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:if>
	<%@ include file="../include/footer.jsp"%>

	<!-- Js Plugins -->
	<!-- 		jQuery CDN -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
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
	$.ajax({
	    type : 'POST',
	    url: "http://localhost:8080/oladesign/homePage/adforshop.do?action=doGetTodayAD",
	    success : function (data, status, xhr) {
	        var dataJson = JSON.parse(data);
	        var total_len = dataJson.length;
	        for(i=0;i<total_len;i++){
	        	var active = i === 0? " active": "";
	        	
	            $("#todayAD").append(
	                    " <div class='carousel-item" + active + "'>"+
	                    " <a href='http://localhost:8080/oladesign/shophome/shopinfo.jsp?comTaxId=" + dataJson[i].comTaxId + "'><img id = 'ad_image' src='" + dataJson[i].adImageString +  "' /></a>"+
	                    " </div>"
	            )
	        }
	       
	    }
	});
	</script>
	<%@ include file="../include/favorite.jsp"%>
	<script>sessionStorage.setItem("comTaxId", "${comMemVO.comTaxId}");</script>
	<script>sessionStorage.setItem("memId", "${memId}");</script>
</body>



</html>
