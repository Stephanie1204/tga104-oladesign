<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.member.service.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.member.bean.*"%>
<%
MemberService memberSvc = new MemberService();
List<MemberVO> list = memberSvc.getAll();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>


<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/css/fontawesome-all.min.css">
<title>管理員資料</title>
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
<body>
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
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/back-end/index-admin.jsp"><i
							class="fas fa-table"></i>管理員管理</a></li>

					<li class="nav-item back-end-li"><a class="nav-link" href="#"><i
							class="fas fa-table"></i><span>前台會員管理</span></a>
						<ul class="back-end-li-child" style="display: none;">
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/back-end/mem/allMem.jsp"><i
									class="fas fa-table"></i>一般會員管理</a></li>
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/back-end/listallcompanymember.jsp"><i
									class="fas fa-table"></i>廠商會員管理</a></li>
						</ul></li>

					<li class="nav-item back-end-li"><a class="nav-link" href="#"><i
							class="fas fa-table"></i>訂單管理</a>
						<ul class="back-end-li-child" style="display: none;">
							                    		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/order/listAllOrder.jsp"><i class="fas fa-table"></i><span>訂單管理</span></a></li>
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
								href="<%=request.getContextPath()%>/back-end/index-intermail.jsp"><i
									class="fas fa-table"></i><span>站內信</span></a></li>
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/back-end/index-intermail_qn.jsp"><i
									class="fas fa-table"></i><span>站內信問題類別</span></a></li>
						</ul></li>
					<li class="nav-item back-end-li"><a class="nav-link" href="#"><i
							class="fas fa-table"></i><span>廣告管理</span></a>
						<ul class="back-end-li-child" style="display: none;">
							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/listalladvertisement.jsp"><i class="fas fa-table"></i><span>廣告審核</span></a></li>
							<%--                     		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/news/select_page.jsp"><i class="fas fa-table"></i><span>查看最新消息</span></a></li> --%>
							<%--                     		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/shopEvent/select_page.jsp"><i class="fas fa-table"></i><span>查看商城活動</span></a></li> --%>
						</ul></li>
				</ul>
			</div>
		</nav>



		<div style="padding: 20px 15px;">
							<div class="box-tools pull-right">
								<div class="has-feedback">
								<form method="post" ACTION="<%=request.getContextPath()%>/MemberForAdmin">
								<input type="text" name="memId" placeholder="請輸入會員編號"> 
								<input type="hidden" name="action" value="getOne_For_Display"> 
<!-- 								<input type="hidden" name="adminId" value="A001" /> -->
								<input type="submit" value="搜尋" class="btn btn-default">	
								</form>
								</div>
							</div>

			<div>
				<table class="table table-striped table-sm table-hover">
			<tr>
				<th nowrap="nowrap">會員編號</th>
				<th nowrap="nowrap">會員名稱</th>
				<th nowrap="nowrap">會員帳號</th>
				<th nowrap="nowrap">會員手機號</th>
<!-- 				<th nowrap="nowrap">帳號是否停權</th> -->
<!-- 				<th nowrap="nowrap">帳號是否啟用</th> -->

<!-- 				<th nowrap="nowrap"></th> -->
<!-- 				<th nowrap="nowrap"></th> -->
				<th nowrap="nowrap">查看</th>
				<th nowrap="nowrap">停權</th>
			</tr>

					<%@ include file="page1.file"%>
					<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">

						<%-- 					<c:forEach var="intermail_qnVO" items="${list}"> --%>
						<tr>
							
					<td>${memberVO.memId}</td>
					<td>${memberVO.memName}</td>
					<td>${memberVO.account}</td>
					<td>${memberVO.memPhone}</td>
<%-- 					<td>${memberVO.isBan}</td> --%>
<%-- 					<td>${memberVO.isActive}</td> --%>



							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/MemberForAdmin"
									style="margin-bottom: 0px;">
									<input type="submit" class="btn back-end-btn" value="查看">
									<input type="hidden" name="memId"
										value="${memberVO.memId}"> <input
										type="hidden" name="action" value="CheckOne">
								</FORM>
								</td>

<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/MemberForAdmin"
									style="margin-bottom: 0px;">
									<input type="submit" class="btn back-end-btn" value="停權">
									<input type="hidden" name="memId"
										value="${memberVO.memId}"> <input
										type="hidden" name="action" value="Ban">
								</FORM>
							</td>
							<!-- 								</td>  -->
						</tr>
					</c:forEach>
				</table>
				<%@ include file="page2.file"%>
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
	<a class="border rounded d-inline scroll-to-top" href="#page-top"><i
		class="fas fa-angle-up"></i></a>
	</div>
	<script
		src="<%=request.getContextPath()%>/back-end/assets/js/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/assets/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/assets/js/theme.js"></script>
</body>
</html>