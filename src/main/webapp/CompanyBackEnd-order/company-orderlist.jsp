<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tibame.tga104.g2.oladesign.order.model.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.CompanyMember.vo.*"%>
<%
Company_MemVO companyMem = (Company_MemVO) session.getAttribute("comMemVO");
if (companyMem != null) {
	pageContext.setAttribute("comTaxId", companyMem.getComTaxId());
}
%>
<!DOCTYPE html>
<html>
<head>
<!-- 页面meta -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>OLA Design 賣家中心 | 訂單管理</title>
<meta name="description" content="AdminLTE2定制版" />
<meta name="keywords" content="AdminLTE2定制版" />
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
	name="viewport" />
<!-- 页面meta /-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/iCheck/square/blue.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/morris/morris.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/datepicker/datepicker3.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/daterangepicker/daterangepicker.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/datatables/dataTables.bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/treeTable/jquery.treetable.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/treeTable/jquery.treetable.theme.default.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/select2/select2.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/colorpicker/bootstrap-colorpicker.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/adminLTE/css/AdminLTE.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/adminLTE/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/ionslider/ion.rangeSlider.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/ionslider/ion.rangeSlider.skinNice.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bootstrap-slider/slider.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
</head>

<body class="hold-transition skin-purple sidebar-mini">
	<!-- Ola Design Header -->
	<%@ include file="../CompanyBackEnd/header.jsp"%>
	<div class="wrapper">
		<!-- Ola Design Menu -->
		<%@ include file="../CompanyBackEnd/company-menu.jsp"%>
		<!-- 内容区域 -->
		<div class="content-wrapper">
			<!-- 正文区域 -->
			<section class="content">
				<div class="box-body">
					<!--tab页-->
					<div class="nav-tabs-custom">
						<!--tab头-->
						<ul class="nav nav-tabs">
							<li class="active"><a href="#all-orderlist"
								data-toggle="tab">全部訂單</a></li>
							<li><a href="#tobeconfirmed-orderlist" data-toggle="tab">待確認</a></li>
							<li><a href="#complete-orderlist" data-toggle="tab">已成立</a></li>
							<li><a href="#cancel-orderlist" data-toggle="tab">已取消</a></li>
						</ul>
						<!--tab头/-->
						<!--tab内容-->
						<div class="tab-content">
							<!--label显示的内容-->
							<div class="tab-pane active" id="all-orderlist">
								<table id="dataList"
									class="table table-bordered table-striped table-hover dataTable">
									<jsp:useBean id="orderSvc" scope="page"
										class="com.tibame.tga104.g2.oladesign.order.model.OrderService" />
									<thead>
										<tr>
											<th class="text-center">訂單編號</th>
											<th class="text-center">訂單狀態</th>
											<th class="text-center">物流狀態</th>
											<th class="text-center">結帳金額</th>
											<th class="text-center">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="row_order"
											items="${orderSvc.select_Com(comTaxId)}">
											<tr>
												<td>${row_order.orderId }</td>
												<td>${row_order.getOrderStatusString()}</td>
												<td>
													<form action="<c:url value="/pages/order.controller"/>"
														method="post">
														<input type="hidden" name="prodaction"
															value="UpdateShipStatus"> <input type="hidden"
															name="orderId" value="${row_order.orderId }"> <select
															name ="shipStatus" class="form-control" onchange="submit();">
															<option value="1"
																${1 == row_order.shippingStatus ? 'selected' : ''}>待確認</option>
															<option value="2"
																${2 == row_order.shippingStatus ? 'selected' : ''}>待出貨</option>
															<option value="3"
																${3 == row_order.shippingStatus ? 'selected' : ''}>已取消</option>
															<option value="4"
																${4 == row_order.shippingStatus ? 'selected' : ''}>已出貨</option>
															<option value="5"
																${5 == row_order.shippingStatus ? 'selected' : ''}>運送中</option>
															<option value="6"
																${6 == row_order.shippingStatus ? 'selected' : ''}>已送達</option>
														</select>
													</form>
												</td>
												<td>${row_order.amount }</td>
												<td class="text-center">
													<form action="<c:url value="/pages/order.controller"/>"
														method="post">
														<input type="hidden" name="prodaction"
															value="SelectOrderItems_Com"> <input
															type="hidden" name="orderId"
															value="${row_order.orderId }">
														<button type="submit" class="btn btn-default">查看完整內容</button>
													</form>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!--待確認-->
							<div class="tab-pane" id="tobeconfirmed-orderlist">
								<table id="dataList"
									class="table table-bordered table-striped table-hover dataTable">
									<thead>
										<tr>
											<th class="text-center">訂單編號</th>
											<th class="text-center">訂單狀態</th>
											<th class="text-center">物流狀態</th>
											<th class="text-center">結帳金額</th>
											<th class="text-center">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="row_order"
											items="${orderSvc.select_Com(comTaxId, 1)}">
											<tr>
												<td>${row_order.orderId }</td>
												<td>${row_order.getOrderStatusString()}</td>
												<td>
													<form action="<c:url value="/pages/order.controller"/>"
														method="post">
														<input type="hidden" name="prodaction"
															value="UpdateShipStatus"> <input type="hidden"
															name="orderId" value="${row_order.orderId }"> <select
															name ="shipStatus" class="form-control" onchange="submit();">
															<option value="1"
																${1 == row_order.shippingStatus ? 'selected' : ''}>待確認</option>
															<option value="2"
																${2 == row_order.shippingStatus ? 'selected' : ''}>待出貨</option>
															<option value="3"
																${3 == row_order.shippingStatus ? 'selected' : ''}>已取消</option>
															<option value="4"
																${4 == row_order.shippingStatus ? 'selected' : ''}>已出貨</option>
															<option value="5"
																${5 == row_order.shippingStatus ? 'selected' : ''}>運送中</option>
															<option value="6"
																${6 == row_order.shippingStatus ? 'selected' : ''}>已送達</option>
														</select>
													</form>
												</td>
												<td>${row_order.amount }</td>
												<td class="text-center">
													<form action="<c:url value="/pages/order.controller"/>"
														method="post">
														<input type="hidden" name="prodaction"
															value="SelectOrderItems_Com"> <input
															type="hidden" name="orderId"
															value="${row_order.orderId }">
														<button type="submit" class="btn btn-default">查看完整內容</button>
													</form>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!--已成立-->
							<div class="tab-pane" id="complete-orderlist">
								<table id="dataList"
									class="table table-bordered table-striped table-hover dataTable">
									<thead>
										<tr>
											<th class="text-center">訂單編號</th>
											<th class="text-center">訂單狀態</th>
											<th class="text-center">物流狀態</th>
											<th class="text-center">結帳金額</th>
											<th class="text-center">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="row_order"
											items="${orderSvc.select_Com(comTaxId, 2)}">
											<tr>
												<td>${row_order.orderId }</td>
												<td>${row_order.getOrderStatusString()}</td>
												<td>
													<form action="<c:url value="/pages/order.controller"/>"
														method="post">
														<input type="hidden" name="prodaction"
															value="UpdateShipStatus"> <input type="hidden"
															name="orderId" value="${row_order.orderId }"> <select
															name ="shipStatus" class="form-control" onchange="submit();">
															<option value="1"
																${1 == row_order.shippingStatus ? 'selected' : ''}>待確認</option>
															<option value="2"
																${2 == row_order.shippingStatus ? 'selected' : ''}>待出貨</option>
															<option value="3"
																${3 == row_order.shippingStatus ? 'selected' : ''}>已取消</option>
															<option value="4"
																${4 == row_order.shippingStatus ? 'selected' : ''}>已出貨</option>
															<option value="5"
																${5 == row_order.shippingStatus ? 'selected' : ''}>運送中</option>
															<option value="6"
																${6 == row_order.shippingStatus ? 'selected' : ''}>已送達</option>
														</select>
													</form>
												</td>
												<td>${row_order.amount }</td>
												<td class="text-center">
													<form action="<c:url value="/pages/order.controller"/>"
														method="post">
														<input type="hidden" name="prodaction"
															value="SelectOrderItems_Com"> <input
															type="hidden" name="orderId"
															value="${row_order.orderId }">
														<button type="submit" class="btn btn-default">查看完整內容</button>
													</form>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!--已取消-->
							<div class="tab-pane" id="cancel-orderlist">
								<table id="dataList"
									class="table table-bordered table-striped table-hover dataTable">
									<thead>
										<tr>
											<th class="text-center">訂單編號</th>
											<th class="text-center">訂單狀態</th>
											<th class="text-center">物流狀態</th>
											<th class="text-center">結帳金額</th>
											<th class="text-center">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="row_order"
											items="${orderSvc.select_Com(comTaxId, 3)}">
											<tr>
												<td>${row_order.orderId }</td>
												<td>${row_order.getOrderStatusString()}</td>
												<td>
													<form action="<c:url value="/pages/order.controller"/>"
														method="post">
														<input type="hidden" name="prodaction"
															value="UpdateShipStatus"> <input type="hidden"
															name="orderId" value="${row_order.orderId }"> <select
															name ="shipStatus" class="form-control" onchange="submit();">
															<option value="1"
																${1 == row_order.shippingStatus ? 'selected' : ''}>待確認</option>
															<option value="2"
																${2 == row_order.shippingStatus ? 'selected' : ''}>待出貨</option>
															<option value="3"
																${3 == row_order.shippingStatus ? 'selected' : ''}>已取消</option>
															<option value="4"
																${4 == row_order.shippingStatus ? 'selected' : ''}>已出貨</option>
															<option value="5"
																${5 == row_order.shippingStatus ? 'selected' : ''}>運送中</option>
															<option value="6"
																${6 == row_order.shippingStatus ? 'selected' : ''}>已送達</option>
														</select>
													</form>
												</td>
												<td>${row_order.amount }</td>
												<td class="text-center">
													<form action="<c:url value="/pages/order.controller"/>"
														method="post">
														<input type="hidden" name="prodaction"
															value="SelectOrderItems_Com"> <input
															type="hidden" name="orderId"
															value="${row_order.orderId }">
														<button type="submit" class="btn btn-default">查看完整內容</button>
													</form>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<!--tab内容/-->
					</div>
					<!--tab页/-->
				</div>
			</section>
			<!-- 正文区域 /-->
		</div>
		<!-- @@close -->
		<!-- 内容区域 /-->
		<!-- Ola Design Footer -->
		<%@ include file="../CompanyBackEnd/footer.jsp"%>
	</div>
	<script src="<%=request.getContextPath()%>/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/jQueryUI/jquery-ui.min.js"></script>
	<script>$.widget.bridge('uibutton', $.ui.button);</script>
	<script src="<%=request.getContextPath()%>/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/raphael/raphael-min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/morris/morris.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/sparkline/jquery.sparkline.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/knob/jquery.knob.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/daterangepicker/moment.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/daterangepicker/daterangepicker.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/fastclick/fastclick.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/iCheck/icheck.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/adminLTE/js/app.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/treeTable/jquery.treetable.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/select2/select2.full.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bootstrap-markdown/js/markdown.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bootstrap-markdown/js/to-markdown.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/ckeditor/ckeditor.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/input-mask/jquery.inputmask.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/input-mask/jquery.inputmask.extensions.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/chartjs/Chart.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/flot/jquery.flot.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/flot/jquery.flot.resize.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/flot/jquery.flot.pie.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/flot/jquery.flot.categories.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/ionslider/ion.rangeSlider.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bootstrap-slider/bootstrap-slider.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
</body>
</html>
