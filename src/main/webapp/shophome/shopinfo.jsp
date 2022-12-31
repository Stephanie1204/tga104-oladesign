<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.tibame.tga104.g2.oladesign.CompanyMember.vo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="description" content="Ogani Template" />
<meta name="keywords" content="Ogani, unica, creative, html" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>OLA Design | 賣家首頁</title>
<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet" />
<!-- Css Styles -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/shophome-css/bootstrap.min.css"
	type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/shophome-css/font-awesome.min.css"
	type="text/css" />
<!--<link rel="stylesheet" href="../shophome-css/elegant-icons.css" type="text/css">-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/shophome-css/nice-select.css"
	type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/shophome-css/jquery-ui.min.css"
	type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/shophome-css/owl.carousel.min.css"
	type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/shophome-css/slicknav.min.css"
	type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/shophome-css/style.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/shophome-css/coupon.css" type="text/css" />
<link href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css"
	rel="stylesheet" />
</head>
<body>
	<%@ include file="../include/header.jsp"%>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>
	<!-- Blog Details Hero Begin -->
	<section class="blog-details-hero set-bg" id="preview_store_banner">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="blog__details__hero__text">
						<h2 id="store_name"></h2>
						<div class="coupon leftandright" id="todaycoupon"
							onclick="copycoupon('leftcoupon')" style="display: none">
							<div class="leftcoupon" id="leftcoupon"></div>
							<div class="rightcoupon" id="rightcoupon">領折扣碼</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Blog Details Hero End -->
	<!-- Blog Details Section Begin -->
	<div></div>
	<section class="blog-details spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-5 order-md-1 order-2">
					<div class="blog__details__author__pic" id="store_logo">
						<img id="preview_store_logo" />
					</div>
				</div>
				<div class="col-lg-8 col-md-7 order-md-1 order-1">
					<div class="blog__details__text">
						<div style="text-align: right">
							<button type="button" id="clicktotalk" class="clicktotalk" onclick="startTalk()">
								點我聊聊</button>
						</div>
						<h3>賣家介紹</h3>
						<p id="store_intro"></p>
					</div>
					<div class="blog__details__content">
						<div class="row"></div>
					</div>
				</div>
			</div>
		</div>
		      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <div class="section-title related-blog-title">
              <h2>商品總覽</h2>
            </div>
          </div>
        </div>
        <div class="row" id="companyList">
              <div class="container">
                <div class="row" id="productList">
              </div> 
          </div>
        </div>
      </div>

	</section>
	<!-- Blog Details Section End -->
	<%@ include file="../include/footer.jsp"%>

	<!-- Js Plugins -->
	<script src="<%=request.getContextPath()%>/shophome-js/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/shophome-js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/shophome-js/jquery.nice-select.min.js"></script>
	<script src="<%=request.getContextPath()%>/shophome-js/jquery-ui.min.js"></script>
	<script src="<%=request.getContextPath()%>/shophome-js/jquery.slicknav.js"></script>
	<script src="<%=request.getContextPath()%>/shophome-js/mixitup.min.js"></script>
	<script src="<%=request.getContextPath()%>/shophome-js/owl.carousel.min.js"></script>
	<script src="<%=request.getContextPath()%>/shophome-js/main.js"></script>
	<script
		src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
	<script>
		let urlParams = new URLSearchParams(window.location.search);
		var comTaxId = urlParams.get("comTaxId");
		  var self = "${memId}"; //檢查是自己還是他人
		$.ajax({
					type : "POST",
					url : "http://localhost:8080/oladesign/shophome/shopinfo.do?action=doGetShopInfo&comTaxId="
							+ comTaxId,
					success : function(data, status, xhr) {
						var dataJson = JSON.parse(data);

						$("#store_name").text(dataJson.storeName);
						$("#store_intro").text(dataJson.storeIntro);
						$("#preview_store_logo").attr("src",
								dataJson.StoreLogoString);
						$("#preview_store_banner").css("background-image",
								"url('" + dataJson.StoreBannerString + "')");
					},
				});
		  
		$.ajax({
			type : "POST",
			url : "http://localhost:8080/oladesign/shophome/shopinfo.do?action=doGetProducts&comTaxId="
					+ comTaxId,
			success : function(data, status, xhr) {
				var dataJson = JSON.parse(data);
				total_len = dataJson.length;
				for(i = 0 ; i< total_len; i++){
					$("#productList").append(
	                        "<div class='col-lg-4 col-md-6 col-sm-6'>" +
	                        "<div class='product__item'>"+
	                        "<a href='http://localhost:8080/oladesign/homePage/productPage.jsp?productId=" + dataJson[i].productId + "'>"+
	                        "<div class='product__item__pic set-bg'>"+
	                        "<img id = 'productImage' src='" + dataJson[i].productImage +  "' />"+
	                        "</div>"+
	                        "<div class='product__item__text'>"+
	                        "<h6 id='productName'>" + dataJson[i].productName + "</h6>"+
	                        "      </div>" + 
	                        "</a>" +
	                        "<h5 id='productprice' class='productpriceis'>" + dataJson[i].productPrice + "</h5>"+
	                        "      </div>" + 
	                        "      </div>" 	
	             )
				}
			},
		});

		$.ajax({
			url : "http://localhost:8080/oladesign/promo/:coupon",
			type : "GET",
			data : {
				comTaxId : comTaxId,
			},
			success : function(data) {
				if ((data.coupon) != null) {
					$("#todaycoupon").show();
					$("#leftcoupon").text(data.coupon);
				}
			},
		});

		function startTalk() {
		    if (self === "") {
		        alert("請先登入在使用此功能");
		        return;
		      }
			
			$("#chat-contacts").hide();
			$("#chat").show();
			$("chat-contact-box").show();

			doGetMemIdByComTaxId(comTaxId);
		}

		function copycoupon(id) {
			var TextRange = document.createRange();
			TextRange.selectNode(document.getElementById(id));
			sel = window.getSelection();
			sel.removeAllRanges();
			sel.addRange(TextRange);
			document.execCommand("copy");
			swal({
				title : "折扣碼複製成功",
				type : "success",
			});
		}

		function doGetMemIdByComTaxId(comTaxId) {
			var memId = "";
			$.ajax({
						url : "http://localhost:8080/oladesign/CompanyBackEnd/company_member.do?action=doGetMemIdByComTaxId",
						type : "POST",
						data : {
							comTaxId : comTaxId,
						},
						success : function(data, status, xhr) {
							var dataJson = JSON.parse(data);
							memId = dataJson.memId;
							memName = document.getElementById("store_name").innerHTML;
							doShowChatroom(memId, memName);
						},
					});

			return memId;
		}
	</script>
	<%@ include file="../include/favorite.jsp"%>
</body>
</html>
