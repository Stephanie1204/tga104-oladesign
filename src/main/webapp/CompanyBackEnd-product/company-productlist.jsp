<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<title>OLA Design 賣家中心 | 全部商品列表</title>
<meta name="description" content="AdminLTE2定制版" />
<meta name="keywords" content="AdminLTE2定制版" />

<meta
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
	name="viewport" />

<link rel="stylesheet" href="../plugins/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="../plugins/font-awesome/css/font-awesome.min.css" />
<link rel="stylesheet" href="../plugins/ionicons/css/ionicons.min.css" />
<link rel="stylesheet" href="../plugins/iCheck/square/blue.css" />
<link rel="stylesheet" href="../plugins/morris/morris.css" />
<link rel="stylesheet"
	href="../plugins/jvectormap/jquery-jvectormap-1.2.2.css" />
<link rel="stylesheet" href="../plugins/datepicker/datepicker3.css" />
<link rel="stylesheet"
	href="../plugins/daterangepicker/daterangepicker.css" />
<link rel="stylesheet"
	href="../plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" />
<link rel="stylesheet"
	href="../plugins/datatables/dataTables.bootstrap.css" />
<link rel="stylesheet" href="../plugins/treeTable/jquery.treetable.css" />
<link rel="stylesheet"
	href="../plugins/treeTable/jquery.treetable.theme.default.css" />
<link rel="stylesheet" href="../plugins/select2/select2.css" />
<link rel="stylesheet"
	href="../plugins/colorpicker/bootstrap-colorpicker.min.css" />
<link rel="stylesheet"
	href="../plugins/bootstrap-markdown/css/bootstrap-markdown.min.css" />
<link rel="stylesheet" href="../plugins/adminLTE/css/AdminLTE.css" />
<link rel="stylesheet"
	href="../plugins/adminLTE/css/skins/_all-skins.min.css" />
<link rel="stylesheet" href="../css/style.css" />
<link rel="stylesheet" href="../plugins/ionslider/ion.rangeSlider.css" />
<link rel="stylesheet"
	href="../plugins/ionslider/ion.rangeSlider.skinNice.css" />
<link rel="stylesheet" href="../plugins/bootstrap-slider/slider.css" />
<link rel="stylesheet" href="../plugins/table.css" />
<link rel="stylesheet"
	href="../plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css" />
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
				<!-- .box-body -->
				<div class="box box-primary">
					<div class="box-header with-border"></div>
					<!--工具栏-->
							<div class="pull-left">
								<div class="form-group form-inline">
									<div class="btn-group">
										<button type="button" class="btn btn-default addbutton" title="新增"
											onclick='location.href="<%=request.getContextPath()%>/CompanyBackEnd-product/company-addproduct.jsp"'>
											<i class="fa fa-file-o"></i>新增
										</button>
									</div>
								</div>
							</div>
							<!--工具栏/-->
					<div class="box-body">
						<!-- 数据表格 -->
						<div class="table-box">
							<jsp:useBean id="prodSvc" scope="page"
								class="com.tibame.tga104.g2.oladesign.product.model.product.ProductService" />
							<c:if test="${not empty prodSvc.selectByComTaxId(comTaxId)}">
								<table id="dataList"
									class="table table-bordered table-striped table-hover dataTable"
									style="table-layout: fixed;">
									<thead>
										<tr>
											<th style="width: 4%">商品編號</th>
											<th style="width: 32%">商品圖片</th>
											<th style="width: 7%">商品類別</th>
											<th style="width: 7%">商品風格</th>
											<th style="width: 20%">商品名稱</th>
											<th style="width: 5%">價格</th>
											<th style="width: 5%">庫存</th>
											<th style="width: 5%">安全存量</th>
											<th style="width: 5%">收藏人數</th>
											<th style="width: 5%">商品狀態</th>
											<th style="width: 5%" class="text-center">操作</th>
										</tr>
										<c:forEach var="row"
											items="${prodSvc.selectByComTaxId(comTaxId)}">
											<form
												action="<c:url value="/CompanyBackEnd-product/company-updateproduct.jsp">
													<c:param name="productId" value="${row.productId}" />
												</c:url>"
												method="post" enctype="multipart/form-data">
												<input type="hidden" name="productId"
													value="${row.productId}">
												<tr>
													<td style="width: 4%">${row.productId}</td>
													<td style="width: 32%"><img
														src="${row.productImgBase64}"></td>

													<td style="width: 7%">${row.typeName}<input
														type="hidden" name="productId" value="${row.typeCode}"></td>

													<td style="width: 7%">${row.styleName}<input
														type="hidden" name="productId" value="${row.styleCode}"></td>

													<td style="width: 200px" style="word-wrap:break-word;"><p>${row.name}</p>
														<input type="hidden" name="productId" value="${row.name}"></td>

													<td style="width: 5%">${row.price}<input type="hidden"
														name="productId" value="${row.price}"></td>

													<td style="width: 5%">${row.stock}<input type="hidden"
														name="productId" value="${row.stock}"></td>

													<td style="width: 5%">${row.safeStock}<input
														type="hidden" name="productId" value="${row.safeStock}"></td>
													<td style="width: 5%">${row.amountFavor}</td>

													<td style="width: 5%">${row.status  ? "上架" : "下架"}<input
														type="hidden" name="productId" value="${row.status}"></td>

													<td style="width: 5%"><input type="submit"
														name="prodaction" value="編輯"></td>
												</tr>
											</form>
										</c:forEach>
									</thead>
								</table>
							</c:if>

						</div>
						<!-- 数据表格 /-->
					</div>
					<!-- /.box-body -->
				</div>
			</section>
			<!-- 正文区域 /-->
		</div>
		<!-- 内容区域 /-->
		<!-- Ola Design Footer -->
		<%@ include file="../CompanyBackEnd/footer.jsp"%>
	</div>
	<script src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
	<script src="../plugins/jQueryUI/jquery-ui.min.js"></script>
	<script> $.widget.bridge("uibutton", $.ui.button);</script>
	<script src="../plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="../plugins/raphael/raphael-min.js"></script>
	<script src="../plugins/morris/morris.min.js"></script>
	<script src="../plugins/sparkline/jquery.sparkline.min.js"></script>
	<script src="../plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="../plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script src="../plugins/knob/jquery.knob.js"></script>
	<script src="../plugins/daterangepicker/moment.min.js"></script>
	<script src="../plugins/daterangepicker/daterangepicker.js"></script>
	<script src="../plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
	<script src="../plugins/datepicker/bootstrap-datepicker.js"></script>
	<script src="../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
	<script src="../plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
	<script src="../plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<script src="../plugins/fastclick/fastclick.js"></script>
	<script src="../plugins/iCheck/icheck.min.js"></script>
	<script src="../plugins/adminLTE/js/app.min.js"></script>
	<script src="../plugins/treeTable/jquery.treetable.js"></script>
	<script src="../plugins/select2/select2.full.min.js"></script>
	<script src="../plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
	<script src="../plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
	<script src="../plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
	<script src="../plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
	<script src="../plugins/bootstrap-markdown/js/markdown.js"></script>
	<script src="../plugins/bootstrap-markdown/js/to-markdown.js"></script>
	<script src="../plugins/ckeditor/ckeditor.js"></script>
	<script src="../plugins/input-mask/jquery.inputmask.js"></script>
	<script src="../plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
	<script src="../plugins/input-mask/jquery.inputmask.extensions.js"></script>
	<script src="../plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="../plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script src="../plugins/chartjs/Chart.min.js"></script>
	<script src="../plugins/flot/jquery.flot.min.js"></script>
	<script src="../plugins/flot/jquery.flot.resize.min.js"></script>
	<script src="../plugins/flot/jquery.flot.pie.min.js"></script>
	<script src="../plugins/flot/jquery.flot.categories.min.js"></script>
	<script src="../plugins/ionslider/ion.rangeSlider.min.js"></script>
	<script src="../plugins/bootstrap-slider/bootstrap-slider.js"></script>
	<script src="../plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
	<script src="../plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
</body>
</html>
