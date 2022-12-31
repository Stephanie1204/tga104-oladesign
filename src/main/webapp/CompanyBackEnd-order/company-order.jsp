<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- 页面meta -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>OLA Design 賣家中心 | 訂單瀏覽</title>
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
				<!--訂單資訊-->
				<div class="panel panel-default">
					<div class="panel-heading list">訂單資訊</div>
					<div class="row data-type">
						<div class="col-md-2 title">訂單編號</div>
						<div class="col-md-4 data">
							<input type="text" class="form-control" value="${order.orderId }"
								readonly />
						</div>

						<div class="col-md-2 title">訂單時間</div>
						<div class="col-md-4 data">
							<input type="text" class="form-control"
								value="${order.orderTime }" readonly />
						</div>

						<div class="col-md-2 title">訂購會員ID</div>
						<div class="col-md-4 data">
							<input type="text" class="form-control" value="${order.memId }"
								readonly />
						</div>

						<div class="col-md-2 title">收件人</div>
						<div class="col-md-4 data">
							<input type="text" class="form-control"
								value="${order.receiver }" readonly />
						</div>

						<div class="col-md-2 title">收件地址</div>
						<div class="col-md-4 data">
							<input type="text" class="form-control" value="${order.address }"
								readonly />
						</div>

					</div>
				</div>
				<!--訂單資訊/-->

				<!--訂購商品資訊-->
				<div class="panel panel-default">
					<div class="panel-heading list">訂購商品資訊</div>
					<table id="dataList"
						class="table table-bordered table-striped table-hover dataTable">
						<thead>
							<tr>
								<th>商品編號</th>
								<th>商品名稱</th>
								<th>購買數量</th>
								<th>單價</th>
								<th>商品總計</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="row_items" items="${orderItems}">
								<tr>
									<td>${row_items.productId}</td>
									<td>${row_items.productName}</td>
									<td>${row_items.quantity}</td>
									<td>${row_items.price}</td>
									<td>${row_items.quantity * row_items.price}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!--訂購商品資訊/-->
				<!--結帳資訊-->
				<div class="panel panel-default checkout">
					<div class="panel-heading list">結帳資訊</div>
					<div class="row data-type">
						<div class="col-md-2 title">折扣碼</div>
						<div class="col-md-4 data text">${order.coupon }</div>

						<div class="col-md-2 title">紅利折抵</div>
						<div class="col-md-4 data text">${order.pointUse }</div>

						<div class="col-md-2 title">結帳金額</div>
						<div class="col-md-4 data text">${order.amount }</div>
					</div>
				</div>
				<!--結帳資訊/-->
				<!--工具栏-->
				<form action="<c:url value="/pages/order.controller"/>"
					method="post">
					<input type="hidden" name="prodaction" value="UpdateOrderStatus">
					<input type="hidden" name="orderId" value="${order.orderId }">
					${order.orderStatus == 1 ? '<div class="box-tools text-center">
						<button type="submit" class="btn btn-default" name ="orderStatus" value="2">接受訂單</button>
						<button type="submit" class="btn btn-default" name ="orderStatus" value="3">不接受訂單</button>
					</div>' : '' }
				</form>
				<!--工具栏/-->
			</section>
			<!-- 正文区域 /-->
		</div>
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
