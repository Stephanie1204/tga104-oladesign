<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tibame.tga104.g2.oladesign.admin.model.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.intermail.model.*"%>
<%
IntermailVO intermailVO = (IntermailVO) request.getAttribute("intermailVO");
%>
<%
Intermail_qnVO intermail_qnVO = (Intermail_qnVO) request.getAttribute("intermail_qnVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員問題信件</title>


<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/css/fontawesome-all.min.css">

<!-- BootStrap 5.0.2 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<!-- jQuery 1.12.4 -->
<script src="https://code.jquery.com/jquery-1.12.4.min.js"
	integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
	crossorigin="anonymous"></script>

<style>
#addAdmin {
	width: 40%;
	margin: auto auto;
}

.title {
	text-align: center;
}

.hint {
	color: red;
}

#addAdmin .adminLabel {
	width: 200px;
}

.back-end-li:hover>.back-end-li-child {
	display: block !important;
}

.nav-item {
	list-style-type: none;
}

.back-end-btn {
	color: #7f70f5;
	border-color: #7f70f5;
}

.back-end-btn:hover {
	background-color: #7f70f5;
	color: #ffffff !important;
}

textarea {
	display: block;
	width: 100%;
	padding: 0.375rem 0.75rem;
	font-size: 1rem;
	font-weight: 400;
	line-height: 1.5;
	color: #212529;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid #ced4da;
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
	border-radius: 0.25rem;
	transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
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
							<%--                     		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/product/listAllType.jsp"><i class="fas fa-table"></i><span>商品類型管理</span></a></li> --%>
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


		<div id="addAdmin">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			
			
			<form METHOD="post" ACTION="intermail.do" class="addAdmin"
				name="addAdmin">
				<div class="col title">
<!-- 					<h4>新增站內信問題類別</h4> -->
				</div>
				<div class="mb-3 row">
					<label for="adminid" class="col-sm-2 col-form-label adminLabel">站內信編號:</label>
					<div class="col-sm-10">
						<td><input type="text" class="form-control"
							name="interMailId" id="interMailId"  readonly maxlength="4" size="4"
							value="${intermailVO.interMailId}" /></td>
					</div>
				</div>

				<div class="mb-3 row">
					<label for="adminName" class="col-sm-2 col-form-label adminLabel">會員編號:</label>
					<div class="col-sm-10">
						<td><input type="text" class="form-control" name="memId"
							id="memId" readonly  maxlength="6" size="6" value="${intermailVO.memId}" /></td>
					</div>
				</div>

				<div class="mb-3 row">
					<label for="adminid" class="col-sm-2 col-form-label adminLabel">管理員編號:</label>
					<div class="col-sm-10">
						<td><input type="text" class="form-control" name="adminId"
							id="adminId" readonly maxlength="4" size="4" value="${intermailVO.adminId}" /></td>
					</div>
				</div>

					<div class="mb-3 row">
<!-- 					<label for="adminid" class="col-sm-2 col-form-label adminLabel">問題類別編號:</label> -->
					<label for="adminid" class="col-sm-2 col-form-label adminLabel">問題類別:</label> 
					<div class="col-sm-10">
							<td>
						<td><input type="text" class="form-control" name="numQue" 
							id="numQue" readonly maxlength="4" size="4"						
							value="${intermailVO.type}"/>							
						</td>
							
							
							
							
							
							
							
					</div>
				</div>

<!-- 					<div class="mb-3 row"> -->
<!-- 						<label for="adminid" class="col-sm-2 col-form-label adminLabel">內容</label> -->
<!-- 						<div class="col-sm-10"> -->
<!-- 						<td><textarea  class="form-control" name="conTent" id="conTent" maxlength="1000" -->
<%-- 							value="${intermailVO.conTent}"></textarea></td> --%>
<!-- 						</div> -->
<!-- 					</div> -->

					<div class="mb-3 row">
					<label for="adminid" class="col-sm-2 col-form-label adminLabel">內容:</label>
					<div class="col-sm-10">
							<textarea  class="form-control" name="conTent" 
							id="conTent" readonly  maxlength="1000">${intermailVO.conTent}</textarea>
					</div>
					</div>


<!-- 				<input type="hidden" id="adminStatus" name="adminStatus" value="1"> -->
<!-- <!-- 				<br> -->
<!-- 				<button class="btn back-end-btn" type="submit" id="adminStatus" -->
<!-- 					name="action" value="REPLY">回覆</button> -->
			</form>
		</div>
	</div>
	</footer>
	</div>
	<a class="border rounded d-inline scroll-to-top" href="#page-top"><i
		class="fas fa-angle-up"></i></a>
	</div>
	<script
		src="<%=request.getContextPath()%>/back-end/css/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/js/theme.js"></script>
</body>


</html>