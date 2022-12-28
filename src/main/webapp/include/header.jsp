<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet" />

<!-- header css -->
<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="../css/header.css" type="text/css" />

<!-- Font Awesome -->
<script src="https://kit.fontawesome.com/51435664f6.js"
	crossorigin="anonymous"></script>

<header class="header">
	<!-- <div class="container"> -->
	<div class="row">
		<div class="col-lg-3">
			<div class="header__logo">
				<a href="<%=request.getContextPath()%>/homePage/index.jsp"><img
					src="../img/OLA_Logo.svg" alt="logo" /></a>
			</div>
		</div>

		<!-- search bar -->
		<div class="col-lg-4">
			<div class="hero__search__form">
				<form action="<c:url value='/pages/product.controller' />"
					method="get">
					<input type="text" id="name" placeholder="What do yo u need?"
						name="name" value="${param.name}" />
					<button type="submit" name="prodaction" value="Select"
						class="site-btn">SEARCH</button>
				</form>
			</div>
		</div>

		<!-- icon -->
		<div class="col-lg-5">
			<div class="header__cart">
				<ul>
					<li>
						<button type="button" class="regist_com" aria-expanded="false">
							<a
								href="<%=request.getContextPath()%>/CompanyBackEnd/regisToCom.jsp"
								class="beCom">成為賣家</a>
						</button> <input type="hidden" class="waiting" value="">
					</li>
					<li>
						<button type="button" class="mystore none" aria-expanded="false">
							<a
								href="<%=request.getContextPath()%>/CompanyBackEnd/company-index.jsp">我的賣場</a>
						</button>
					</li>
					<li class="logout">
						<button type="button" class="login" aria-expanded="false">
							<a href="<%=request.getContextPath()%>/member/login.jsp">註冊/登入</a></i>
						</button>
					</li>
					<li class="login"><span class="member"><img
							id="memPhoto" alt="會員照片"
							src="${(memberVO.memPhoto == null)? '../img/default_photo.jpg' : memberVO.memPhotoBase64}" />
							${memName}</span>
						<ul class="member__menu__dropdown">
							<li><a
								href="<%=request.getContextPath()%>/memberCenter/pages/accountBasicInfo.html?memId=${memId}">會員中心</a></li>
							<li><a
								href="<%=request.getContextPath()%>/CompanyBackEnd/company-index.jsp">我的賣場</a></li>
							<li><a
								href="http://localhost:8080/oladesign/memberCenter/pages/orderList.html">我的訂單</a></li>
							<li><a
								href="http://localhost:8080/oladesign/memberCenter/pages/pointManagement.html">我的紅利</a></li>
							<li><a href="#">聯絡客服</a></li>
							<li><form method="post"
									action="<%=request.getContextPath()%>/member/MemberLogin">
									<button type="submit" class="logout" id="logout">登出</button>
									<input type="hidden" name="action" value="logout">
								</form></li>
						</ul></li>
					<li><a href="###"><i class="fa fa-heart memFav"></i> <span
							id="fav"></span></a></li>
					<li><a
						href="<%=request.getContextPath()%>/homePage/shopping_cart.jsp"><i
							class="fa fa-shopping-bag"></i> </a></li>
				</ul>
			</div>
		</div>

		<!-- </div> -->
	</div>
	<div class="row nav">
		<!-- nav bar -->
		<div class="col-lg-8">
			<nav class="header__menu">
				<ul class="nav">
					<li class="active"><a
						href="<%=request.getContextPath()%>/homePage/index.jsp">首頁</a></li>
					<li><a href="#">全站商品分類</a>
						<ul class="header__menu__dropdown">
							<jsp:useBean id="typeSvc2" scope="page"
								class="com.tibame.tga104.g2.oladesign.product.model.type.TypeService" />
							<c:forEach var="typeBean" items="${typeSvc2.getAll()}">
								<form action="<c:url value="/pages/product.controller" />"
									method="get">
									<input type="hidden" name="typeCode"
										value="${typeBean.typeCode}"> <input type="hidden"
										name="prodaction" value="Select">
									<li><input type="submit" value="${typeBean.typeName}"
										style="border: 0px;"></li>
								</form>
							</c:forEach>
						</ul></li>
					<li><a href="#">促銷商品</a></li>
					<li><a href="../shophome/shoplist.jsp">設計館</a></li>
					<li><a href="./contact.html">聯絡我們</a></li>
				</ul>
			</nav>
		</div>
	</div>
</header>

<!-- 		jQuery CDN -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
	$(window).on("load", function() {
		let memberVO = "${memberVO}";
		console.log("memberVO=" + memberVO);
		let user = "${memName}";
		console.log("user=" + user);
		if (user.trim().length != 0) { //登入狀態
			console.log("已登入");
			$("li.logout *").addClass("none");
			$("#fav").removeClass("down");
		} else { //未登入
			console.log("未登入");
			$("li.login *").addClass("none");
			$("span#fav").addClass("down");
		}

		let isCom = "${isCom}";
		console.log("isCom=" + isCom);
		if (isCom == true) {
			$("button.mystore").removeClass("none");
			$("button.regist_com").addClass("none");
		} else {
			$("button.regist_com").removeClass("none");
			$("button.mystore").addClass("none");
		}

		let registCom = "${memberVO.isRegCom}";
		console.log("registCom=" + registCom);

		if (registCom == "true") {
			$(".regist_com").prop("disabled", true);
			$("a.beCom").text("賣家註冊審核中");
		} else if ($("input.waiting").hasClass("wait")) {
			console.log("here");
			$(".regist_com").prop("disabled", true);
			$("a.beCom").text("賣家註冊審核中");
		}

	});
</script>

