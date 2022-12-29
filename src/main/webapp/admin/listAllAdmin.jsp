<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.admin.model.*"%>
<%
AdminService adminSvc = new AdminService();
List<AdminVO> list = adminSvc.getAll();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有管理員</title>


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
                    		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/index-product_style.jsp"><i class="fas fa-table"></i><span>商品地區類別</span></a></li>
                    		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/index-product_type.jsp"><i class="fas fa-table"></i><span>商品類別</span></a></li>
						</ul></li>

					<li class="nav-item back-end-li"><a class="nav-link" href="#"><i
							class="fas fa-table"></i><span>站內信管理</span></a>
						<ul class="back-end-li-child" style="display: none;">
                    		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/index-intermail.jsp"><i class="fas fa-table"></i><span>站內信</span></a></li>
                    		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/index-intermail_qn.jsp"><i class="fas fa-table"></i><span>站內信問題類別</span></a></li>
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
			<div style="display: flex;">
				<h2>所有管理員清單</h2>
				<div style="display: flex; position: absolute; right: 15px;">
					<!-- 							<h4> -->
					<%-- 								<a href="<%=request.getContextPath()%>/back-end/index-admin.jsp">回管理員管理</a> --%>
					<%-- 								<a href="<%=request.getContextPath()%>/admin/addAdmin.jsp">新增管理員</a> --%>
					<!-- 							</h4> -->
				</div>
			</div>

			<div>
				<table class="table table-striped table-sm table-hover">
					<tr>
						<th scope="col">編號</th>
						<th scope="col">名稱</th>
						<th scope="col">帳號</th>
						<th scope="col">密碼</th>
						<th scope="col">修改</th>
						<th scope="col">刪除</th>
						
					</tr>

<%-- 	<%@ include file="page1.file" %>  --%>
<%-- 	<c:forEach var="adminVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>

					<c:forEach var="adminVO" items="${list}">
						<tr>
							<td>${adminVO.adminId}</td>
							<td>${adminVO.adminName}</td>
							<td>${adminVO.account}</td>
							<td>${adminVO.password}</td>
							<!-- 								<td> -->
							<%-- 									<c:if test="${administratorVO.adminStatus==1}">有效</c:if> --%>
							<%-- 									<c:if test="${administratorVO.adminStatus==0}">失效</c:if> --%>
							<!-- 								</td> -->
							<td>
<!-- 								<FORM METHOD="post" -->
<%-- 									ACTION="<%=request.getContextPath()%>/admin/admin.do"> --%>
<%-- 									<input type="hidden" name="adminId" value="${adminVO.adminId}"> --%>
<!-- 									<button type="submit" name="action" value="getOne_For_Update" -->
<!-- 										class="btn back-end-btn">修改</button> -->
<!-- 								</FORM> -->
			  <FORM METHOD="post" 
			  ACTION="<%=request.getContextPath()%>/admin/admin.do" style="margin-bottom: 0px;">
			     <input type="submit" class="btn back-end-btn" value="修改">
			     <input type="hidden" name="adminId"  value="${adminVO.adminId}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
							</td>
							<td>
<!-- 								  <FORM METHOD="post"  -->
<%-- 									ACTION="<%=request.getContextPath()%>/admin/admin.do"> --%>
<%-- 									<input type="hidden" name="adminid" value="${adminVO.adminId}"> --%>
<!-- 									<button type="submit" name="action" value="delete" -->
<!-- 										class="btn back-end-btn">刪除</button> -->
<!-- 								</FORM> -->
			  <FORM METHOD="post" 
			  ACTION="<%=request.getContextPath()%>/admin/admin.do" style="margin-bottom: 0px;">
			     <input type="submit" class="btn back-end-btn" value="刪除">
			     <input type="hidden" name="adminId"  value="${adminVO.adminId}">
			     <input type="hidden" name="action" value="delete"></FORM>
							</td>
							<!-- 								<td> -->
							<!-- 									<form method="post" -->
							<%-- 										action="<%=request.getContextPath()%>/admin/UpdateAdminFuncServlet"> --%>
							<!-- 										<input type="hidden" name="adminid" -->
							<%-- 											value="${administratorVO.adminid}"> --%>
							<!-- 										<button type="submit" name="action" value="UpdateAdminFunc" class="btn back-end-btn">調整權限</button> -->
							<!-- 									</form> -->
							<!-- 								</td> -->
						</tr>
					</c:forEach>
				</table>
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