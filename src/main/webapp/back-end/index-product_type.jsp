<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<!-- jQuery 1.12.4 -->
<script src="https://code.jquery.com/jquery-1.12.4.min.js"
	integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/css/fontawesome-all.min.css">

<title>商品地區類別管理</title>
<style>
.back-end-btn {
	color: #7f70f5;
	border-color: #7f70f5
}

.back-end-btn:hover {
	background-color: #7f70f5;
	color: #ffffff !important;
}

.back-end-li:hover>.back-end-li-child {
	display: block !important;
}

.nav-item {
	list-style-type: none;
}
</style>
</head>

<body id="page-top">
	<div id="wrapper">
		<nav
			class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0">
			<div class="container-fluid d-flex p-0">
				<a
					class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0"
					href="#">
					<div class="sidebar-brand-icon rotate-n-15">
						<i class="fas fa-laugh-wink"></i>
					</div>
					<div class="sidebar-brand-text mx-3">
						<span>oladesign</span>
					</div>
				</a>
				<hr class="sidebar-divider my-0">
				<ul class="nav navbar-nav text-light" id="accordionSidebar">
					<li class="nav-item"><a class="nav-link active"
						href="<%=request.getContextPath()%>/back-end/back-end-index.jsp"><i
							class="fas fa-tachometer-alt"></i><span>首頁</span></a></li>
					<!-- 					<li class="nav-item"><a class="nav-link" -->
					<%-- 						href="<%=request.getContextPath()%>/back-end/index-admin.jsp"><i --%>
					<!-- 							class="fas fa-table"></i>管理員管理</a></li> -->
					<li class="nav-item back-end-li"><a class="nav-link" href="#"><i
							class="fas fa-table"></i><span>管理員管理</span></a>
						<ul class="back-end-li-child" style="display: none;">
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/admin/listAllAdmin.jsp"><i
									class="fas fa-table"></i>所有管理員</a></li>
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/admin/addAdmin.jsp"><i
									class="fas fa-table"></i>新增管理員</a></li>
						</ul></li>

					<li class="nav-item back-end-li"><a class="nav-link" href="#"><i
							class="fas fa-table"></i><span>前台會員管理</span></a>
						<ul class="back-end-li-child" style="display: none;">
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/memForAdmin/listallmember.jsp"><i
									class="fas fa-table"></i>一般會員管理</a></li>
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/back-end/listallcompanymember.jsp"><i
									class="fas fa-table"></i>廠商會員管理</a></li>
						</ul></li>

					<li class="nav-item back-end-li"><a class="nav-link" href="#"><i
							class="fas fa-table"></i>訂單管理</a>
						<ul class="back-end-li-child" style="display: none;">
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/order/listAllOrder.jsp"><i
									class="fas fa-table"></i><span>訂單管理</span></a></li>
							<%--                     		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/product/prodInfoQuery.jsp"><i class="fas fa-table"></i><span>商品管理審核</span></a></li> --%>
						</ul></li>
					<li class="nav-item back-end-li"><a class="nav-link" href="#"><i
							class="fas fa-table"></i><span>商品分類</span></a>
						<ul class="back-end-li-child" style="display: none;">
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/back-end/index-product_style.jsp"><i
									class="fas fa-table"></i><span>商品地區類別</span></a></li>
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/back-end/index-product_type.jsp"><i
									class="fas fa-table"></i><span>商品類別</span></a></li>
						</ul></li>

					<li class="nav-item back-end-li"><a class="nav-link" href="#"><i
							class="fas fa-table"></i><span>站內信管理</span></a>
						<ul class="back-end-li-child" style="display: none;">
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/intermail/listAllIntermail.jsp"><i
									class="fas fa-table"></i><span>所有站內信</span></a></li>
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/intermail/addIntermail.jsp"><i
									class="fas fa-table"></i><span>新增站內信</span></a></li>
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/intermail/unreadIntermail.jsp"><i
									class="fas fa-table"></i><span>尚未回覆站內信</span></a></li>
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/back-end/index-intermail_qn.jsp"><i
									class="fas fa-table"></i><span>站內信問題類別</span></a></li>
						</ul></li>
					<li class="nav-item back-end-li"><a class="nav-link" href="#"><i
							class="fas fa-table"></i><span>廣告管理</span></a>
						<ul class="back-end-li-child" style="display: none;">
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/back-end/listalladvertisement.jsp"><i
									class="fas fa-table"></i><span>廣告審核</span></a></li>
							<%--                     		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/news/select_page.jsp"><i class="fas fa-table"></i><span>查看最新消息</span></a></li> --%>
							<%--                     		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/shopEvent/select_page.jsp"><i class="fas fa-table"></i><span>查看商城活動</span></a></li> --%>
						</ul></li>
				</ul>
				<!-- 				<div class="text-center d-none d-md-inline" style="margin: 0 auto;"> -->
				<!-- 					<button class="btn rounded-circle border-0" id="sidebarToggle" -->
				<!-- 						type="button"></button> -->
				<!-- 				</div> -->
			</div>
		</nav>
		<div class="d-flex flex-column" id="content-wrapper">
			<div id="content">
				<nav
					class="navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top">
					<div class="container-fluid">
						<button class="btn btn-link d-md-none rounded-circle mr-3"
							id="sidebarToggleTop" type="button">
							<i class="fas fa-bars"></i>
						</button>
						<form
							class="form-inline d-none d-sm-inline-block mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
							<div class="input-group">
								<div class="input-group-append"></div>
							</div>
						</form>
						<ul class="nav navbar-nav flex-nowrap ml-auto">
							<li class="nav-item dropdown d-sm-none no-arrow"><a
								class="dropdown-toggle nav-link" data-toggle="dropdown"
								aria-expanded="false" href="#"><i class="fas fa-search"></i></a>
								<div
									class="dropdown-menu dropdown-menu-right p-3 animated--grow-in"
									aria-labelledby="searchDropdown">
									<form class="form-inline mr-auto navbar-search w-100">
										<div class="input-group">
											<input class="bg-light form-control border-0 small"
												type="text" placeholder="Search for ...">
											<div class="input-group-append">
												<button class="btn btn-primary py-0" type="button">
													<i class="fas fa-search"></i>
												</button>
											</div>
										</div>
									</form>
								</div></li>
							<li class="nav-item dropdown no-arrow mx-1">
								<!-- 								<div class="nav-item dropdown no-arrow"> --> <!-- 									<a class="dropdown-toggle nav-link" data-toggle="dropdown" -->
								<!-- 										aria-expanded="false" --> <%-- 										href="<%=request.getContextPath() %>/customerservice/NameServlet?backaction=admin&userID=${adminVO.adminId}" --%>
								<%-- 										class="d-none d-lg-inline mr-2 text-gray-600 small">${adminVO.adminId}</span><img --%>
								<!-- 										onclick="window.open(this.href, '', 'width=800,height=800'); return false;"><span -->
								<!-- 										class="badge badge-danger badge-counter"></span><i -->
								<!-- 										class="fa fa-comment"></i></a> --> <!-- 								</div> -->
								<!-- 							</li> -->
								<div class="d-none d-sm-block topbar-divider"></div>
							<li class="nav-item dropdown no-arrow">
								<div class="nav-item dropdown no-arrow">
									<a class="dropdown-toggle nav-link" data-toggle="dropdown"
										aria-expanded="false" href="#"><span
										class="d-none d-lg-inline mr-2 text-gray-600 small">${adminVO.adminName}</span><img
										class="border rounded-circle img-profile"
										src="<%=request.getContextPath()%>/image/logo1.png"></a>
									<div
										class="dropdown-menu shadow dropdown-menu-right animated--grow-in">
										<div class="dropdown-divider"></div>
										<a class="dropdown-item"
											href="<%=request.getContextPath()%>/login/logOutServlet?action=admin"><i
											class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Logout</a>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</nav>

				<div style="padding: 20px 15px;">
					<h2>商品類別管理</h2>

					<c:if test="${not empty errorMsgs}">
						<b>請修正以下錯誤：</b>
						<ul>
							<c:forEach var="msg" items="${errorMsgs}">
								<li>${msg}</li>
							</c:forEach>
						</ul>
					</c:if>

					<ul>
						<li><a
							href="<%=request.getContextPath()%>/product_type/listAllProduct_type.jsp">檢視所有商品類別</a>
						</li>
						<li><a
							href="<%=request.getContextPath()%>/product_type/addProduct_type.jsp">新增商品類別</a>
						</li>

						<%-- <li>
						<form method="post"
							action="<%=request.getContextPath()%>/admin/GetOneAdminServlet">
							<h5>請輸入管理員編號：</h5>
							<input type="text" name="adminid">
							<button type="submit" name="action" value="getOneAdmin" class="btn back-end-btn">送出</button>
						</form>
					</li> --%>
						<%-- 					<jsp:useBean id="adminSvc" scope="page" --%>
						<%-- 						class="com.admin.model.AdministratorService" /> --%>



						<%-- 					<jsp:useBean id="funcSvc" scope="page" --%>
						<%-- 						class="com.function.model.FunctionService" /> --%>
						<jsp:useBean id="product_typeSvc" scope="page"
							class="com.tibame.tga104.g2.oladesign.product_type.model.Product_typeService" />
						<li>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/product_type/product_type.do">
								<b>選擇商品類別編號:</b> <select size="1" name="typeCode">
									<c:forEach var="product_typeVO" items="${product_typeSvc.all}">
										<option value="${product_typeVO.typeCode}">${product_typeVO.typeCode}
									</c:forEach>
								</select>
								<button type="submit" name="action" value="getOne_For_Display"
									class="btn back-end-btn">送出</button>
								<!-- 								<input type="hidden" name="action" value="getOne_For_Display"> -->
								<!-- 								<input type="submit" value="送出"> -->
							</FORM>
						</li>

						<li>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/product_type/product_type.do">
								<b>選擇商品類別名稱:</b> <select size="1" name="typeCode">
									<c:forEach var="product_typeVO" items="${product_typeSvc.all}">
										<option value="${product_typeVO.typeCode}">${product_typeVO.typeName}
									</c:forEach>
								</select>
								<button type="submit" name="action" value="getOne_For_Display"
									class="btn back-end-btn">送出</button>
							</FORM>
						</li>


					</ul>
					<div>
						<%-- 					<%if (request.getAttribute("adminVO") != null) {%> --%>
						<%-- 					<jsp:include page="/back-end/admin/listOneAdmin.jsp" /> --%>
						<%-- 					<% --%>
						<%-- 					} else if (request.getAttribute("listAdminsByFuncid") != null) {
<%-- 					%> --%>
						<%-- 					<jsp:include page="/back-end/function/listAdminsByFuncid.jsp" /> --%>
						<%-- 					<% --%>
						<%-- 						}
<%-- 					%> --%>
					</div>
				</div>

			</div>
			<footer class="bg-white sticky-footer">
				<div class="container my-auto">
					<div class="text-center my-auto copyright">
						<span>oladesign</span>
					</div>
				</div>
			</footer>
		</div>
		<!-- 		<a class="border rounded d-inline scroll-to-top" href="#page-top"><i -->
		<!-- 			class="fas fa-angle-up"></i></a> -->
	</div>
	<script src="<%=request.getContextPath()%>/back-end/js/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/js/theme.js"></script>
</body>

</html>