<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.order.model.*"%>
<%
OrderService orderSvc = new OrderService();
List<OrderBean> list = orderSvc.getAll();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單管理</title>


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
			</div>
		</nav>



		<div style="padding: 20px 15px;">
			<div style="display: flex;">
				<h2>訂單管理</h2>
				<div style="display: flex; position: absolute; right: 15px;">
				</div>
			</div>

			<div class="box-tools pull-right">
				<div class="has-feedback">
					<form method="post"
						ACTION="<%=request.getContextPath()%>/order/order.do">
						<input type="text" name="orderId" placeholder="訂單編號"> <input
							type="text" name="comTaxId" placeholder="公司統編"> <input
							type="text" name="memId" placeholder="會員編號"> <input
							type="text" name="receiver" placeholder="收件人名稱">
						<p>
							<!-- 						<input type="text" name="orderStatus" placeholder="訂單狀態">  -->
							<select name="orderStatus">
								<option value="">請選擇訂單狀態</option>
								<option value="1">待確認</option>
								<option value="2">已成立</option>
								<option value="3">已取消</option>
							</select>
							<!-- 						<input type="text" name="shippingStatus" placeholder="物流狀態"> -->
							<select name="shippingStatus">
								<option value="">請選擇物流狀態</option>
								<option value="1">待確認</option>
								<option value="2">待出貨</option>
								<option value="3">已取消</option>
								<option value="4">已出貨</option>
								<option value="5">運送中</option>
								<option value="6">已送達</option>
							</select>
							<!-- 						<input type="date" name="orderTime" value="2022-01-01">						  -->
							<input type="date" name="startTime" value="2022-01-01"> <input
								type="date" name="overTime" value="2022-12-31"> <input
								type="submit" value="查詢" class="btn btn-default"> <input
								type="hidden" name="action" value="Select">
					</form>

					<!-- 					<FORM METHOD="post" -->
					<%-- 						ACTION="<%=request.getContextPath()%>/order/order.do" --%>
					<!-- 						style="margin-bottom: 0px;"> -->
					<!-- 						<input type="submit" class="btn back-end-btn" value="查看"> -->
					<%-- 						<input type="hidden" name="orderId" value="${orderBean.orderId}"> --%>
					<!-- 						<input type="hidden" name="action" value="CheckOne"> -->
					<!-- 					</FORM> -->






				</div>
			</div>

			<div>
				<table class="table table-striped table-sm table-hover">
					<tr>
						<th nowrap="nowrap">訂單編號</th>
						<th nowrap="nowrap">公司統編</th>
						<th nowrap="nowrap">會員編號</th>
						<th nowrap="nowrap">訂單日期</th>
						<th nowrap="nowrap">訂單狀態</th>
						<th nowrap="nowrap">物流狀態</th>
						<th nowrap="nowrap">收件人名稱</th>
						<th nowrap="nowrap">結帳金額</th>
						<th nowrap="nowrap"></th>
						<th nowrap="nowrap">查看</th>
					</tr>

					<%@ include file="page1.file"%>
					<c:forEach var="orderBean" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">

						<%-- 					 					<c:forEach var="OrderBean" items="${list}"> --%>
						<tr>
							<td>${orderBean.orderId}</td>
							<td>${orderBean.comTaxId}</td>
							<td>${orderBean.memId}</td>
							<td>${orderBean.orderTime}</td>

							<td><c:if test="${orderBean.orderStatus==1}">待確認</c:if> <c:if
									test="${orderBean.orderStatus==2}">已成立</c:if> <c:if
									test="${orderBean.orderStatus==3}">已取消</c:if></td>

							<td><c:if test="${orderBean.shippingStatus==1}">待確認</c:if> <c:if
									test="${orderBean.shippingStatus==2}">待出貨</c:if> <c:if
									test="${orderBean.shippingStatus==3}">已取消</c:if> <c:if
									test="${orderBean.shippingStatus==4}">已出貨</c:if> <c:if
									test="${orderBean.shippingStatus==5}">運送中</c:if> <c:if
									test="${orderBean.shippingStatus==6}">已送達</c:if></td>

							<%-- 							<td>${orderBean.orderStatus}</td> --%>
							<%-- 							<td>${orderBean.shippingStatus}</td> --%>
							<td>${orderBean.receiver}</td>
							<td>${orderBean.amount}</td>
							<%-- 							<td><fmt:formatDate value="${intermailVO.sentTime}" --%>
							<%-- 									pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>
							<td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/order/order.do"
									style="margin-bottom: 0px;">
									<input type="submit" class="btn back-end-btn" value="查看">
									<input type="hidden" name="orderId"
										value="${orderBean.orderId}"> <input type="hidden"
										name="action" value="CheckOne">
								</FORM>
							</td>
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
	<!-- 	<a class="border rounded d-inline scroll-to-top" href="#page-top"><i -->
	<!-- 		class="fas fa-angle-up"></i></a> -->
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