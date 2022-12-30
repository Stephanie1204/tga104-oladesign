<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tibame.tga104.g2.oladesign.product.model.product.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.CompanyMember.vo.*"%>
<%
Company_MemVO companyMem = (Company_MemVO) session.getAttribute("comMemVO");
if (companyMem != null) {
	pageContext.setAttribute("comTaxId", companyMem.getComTaxId());
}
%>
<%
ProductService prodSvc = new ProductService();
String productId = request.getParameter("productId");
if (productId != null) {
	ProductBean product = prodSvc.selectByProdId(Integer.parseInt(productId));
	pageContext.setAttribute("prod", product);

	int imgAmount = prodSvc.getImgAmount(Integer.parseInt(productId));
	pageContext.setAttribute("amount", imgAmount);
}

System.out.println("test");
%>
<!DOCTYPE html>
<html>
<head>
<!-- 页面meta -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>OLA Design 賣家中心 | 修改商品</title>
<meta name="description" content="AdminLTE2定制版" />
<meta name="keywords" content="AdminLTE2定制版" />
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
	name="viewport" />

<link rel="stylesheet" href="../plugins/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="../plugins/font-awesome/css/font-awesome.min.css" />
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
<link rel="stylesheet"
	href="../plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css" />
<link rel="stylesheet" href="../plugins/updateProduct/updateProduct.css" />

<script type="text/javascript">
	function clearForm() {
		var inputs = document.getElementsByTagName("input");
		for (var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "text") {
				inputs[i].value = "";
			}
		}
	}
</script>
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
				<!--订单信息-->
				<div class="panel panel-default">
					<div class="row data-type">
						<div class="col-md-2 title rowHeight2x">商品圖片上傳</div>
						<div class="col-md-10 data" style="height: 320px">
							<div>
								<div class="img_display">
									<div>
										<form action='<c:url value="/pages/saler.controller"/>'
											method="post" enctype="multipart/form-data">
											<div>
												<input type="file" id="p_file1" name="img_file1"
													style="display: none;" accept="image/*" multiple /> <input
													type="button" value="Browse..."
													onclick="document.getElementById('p_file1').click();" />
											</div>
											<div>
												<input type="hidden" name="comTaxId" value="${comTaxId }">
												<input type="hidden" name="imgAmount" value="${amount }">
												<input type="hidden" name="productId"
													value="${prod.productId }"> <input type="hidden"
													name="prodaction" value="addImg">
												<button type="submit">上傳</button>
											</div>
										</form>
									</div>
									<div id="preview1">
										<span class="text" id="text-preview">預覽圖</span>
									</div>
									<div>
										<ul class="picture_1"></ul>
									</div>
									<div>
										<h4>圖片一覽</h4>
									</div>
									<div>
										<ul class="pictures"></ul>
										<jsp:useBean id="productSvc" scope="page"
											class="com.tibame.tga104.g2.oladesign.product.model.product.ProductService" />
										<c:if test="${not empty productSvc.getImages(prod.productId)}">
											<c:forEach var="row_image"
												items="${productSvc.getImages(prod.productId)}">
												<li style="display: inline-block">
													<form action='<c:url value="/pages/saler.controller"/>'
														method="post" enctype="multipart/form-data">
														<div>
															<img style="width: 80px; height: 80px;"
																src="${row_image.productImgBase64}">
														</div>
														<div>
															<input type="hidden" name="comTaxId" value="${comTaxId }">
															<input type="hidden" name="productId"
																value="${prod.productId }"> <input type="hidden"
																name="imageId" value="${row_image.imageId }"> <input
																type="hidden" name="prodaction" value="deleteImg">
															<button type="submit">刪除</button>
														</div>
													</form>
												</li>
											</c:forEach>
										</c:if>
									</div>
								</div>
							</div>
						</div>
						<form action='<c:url value="/pages/saler.controller"/>'
							method="post" enctype="multipart/form-data">
							<div class="col-md-2 title">商品編號</div>
							<div class="col-md-4 data">
								<input type="hidden" name="productId" value="${prod.productId}">${prod.productId}
							</div>

							<div class="col-md-2 title">商品名稱</div>
							<div class="col-md-4 data">
								<input type="text" class="form-control" placeholder="商品名稱"
									name="name" value="${prod.name}" />
							</div>

							<div class="col-md-2 title">商品類別</div>
							<div class="col-md-4 data">
								<jsp:useBean id="typeSvc" scope="page"
									class="com.tibame.tga104.g2.oladesign.product.model.type.TypeService" />
								<select class="form-control" name="typeCode" style="width: 100%"
									size="1">
									<option value="">無
										<c:forEach var="typeBean" items="${typeSvc.getAll()}">
											<option value="${typeBean.typeCode}"
												${prod.typeCode == typeBean.typeCode ? 'selected' : ''}>${typeBean.typeName}</option>
										</c:forEach>
								</select>
							</div>

							<div class="col-md-2 title">商品風格</div>
							<div class="col-md-4 data">
								<jsp:useBean id="styleSvc" scope="page"
									class="com.tibame.tga104.g2.oladesign.product.model.style.StyleService" />
								<select class="form-control" name="styleCode"
									style="width: 100%" size="1">
									<option value="">無
										<c:forEach var="styleBean" items="${styleSvc.getAll()}">
											<option value="${styleBean.styleCode}"
												${prod.styleCode == styleBean.styleCode ? 'selected' : ''}>${styleBean.styleName}</option>
										</c:forEach>
								</select>
							</div>

							<div class="col-md-2 title">商品價格</div>
							<div class="col-md-4 data">
								<input type="text" class="form-control" placeholder="商品價格"
									name="price" value="${prod.price}" />
							</div>

							<div class="col-md-2 title">商品庫存</div>
							<div class="col-md-4 data">
								<input type="text" class="form-control" placeholder="商品庫存"
									name=stock value="${prod.stock}" />
							</div>

							<div class="col-md-2 title">安全存量</div>
							<div class="col-md-4 data">
								<input type="text" class="form-control" placeholder="安全存量"
									name="safeStock" value="${prod.safeStock}" />
							</div>

							<div class="col-md-2 title">商品狀態</div>
							<div class="col-md-4 data">
								<select size="1" name="status" class="form-control"
									style="width: 100%">
									<option value="true" ${prod.status == true ? 'selected' : ''}>上架</option>
									<option value="false" ${prod.status == false ? 'selected' : ''}>下架</option>
								</select>
							</div>
							<div class="col-md-2 title rowHeight2x">主圖上傳</div>
							<div class="col-md-10 data" style="height: 320px">
								<input type="file" name="img_file" accept="image/*"
									id="uploadimage" class="upl" /> <img class="preview"
									src="${prod.productImgBase64}"
									style="max-width: 150px; max-height: 150px;"> <input
									type="hidden" name="tempImg" value="${prod.productImgBase64}">
								<div class="img-box"></div>
							</div>

							<div class="col-md-2 title rowHeight2x">商品介紹</div>

							<!--编辑器-->
							<div class="tab-pane" id="tab-editer">
								<div class="col-md-10 data editer">
									<textarea class="textarea" name="intro" placeholder="請輸入商品介紹"
										style="width: 100%; height: 265px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;">${prod.intro}</textarea>
								</div>
							</div>
							<!--编辑器/-->
							<!--工具栏-->
							<div class="box-tools text-center">
								<hr />
								<input type="hidden" name="comTaxId" value="${comTaxId }">
								<input type="submit" name="prodaction" value="Update"
									class="btn bg-maroon updatebutton"><input type="submit"
									name="prodaction" value="Delete" class="btn bg-maroon">
								<input type="button" value="Clear" class="btn bg-maroon" onclick="clearForm()">
								<hr />
							</div>
							<!--工具栏/-->
						</form>
						<h3>
							<div>
								<span class="error">${errors.action}</span>
							</div>
							<div>
								<span class="error">${errors.typeCode}</span>
							</div>
							<div>
								<span class="error">${errors.styleCode}</span>
							</div>
							<div>
								<span class="error">${errors.name}</span>
							</div>
							<div>
								<span class="error">${errors.price}</span>
							</div>
							<div>
								<span class="error">${errors.intro}</span>
							</div>
							<div>
								<span class="error">${errors.stock}</span>
							</div>
							<div>
								<span class="error">${errors.safeStock}</span>
							</div>
						</h3>
						<c:if test="${not empty update}">
							<h3>更新成功</h3>
						</c:if>
					</div>
				</div>
				<!--订单信息/-->
			</section>
			<!-- 正文区域 /-->
		</div>
		<!-- 内容区域 /-->

		<!-- 底部导航 -->
		<footer class="main-footer">
			<div class="pull-right hidden-xs">
				<b>Version</b> 1.0.8
			</div>
			<strong>Copyright &copy; 2014-2017 <a
				href="http://www.itcast.cn">研究院研发部</a>.
			</strong> All rights reserved.
		</footer>
		<!-- 底部导航 /-->
	</div>

	<script src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
	<script src="../plugins/jQueryUI/jquery-ui.min.js"></script>
	<script>
		$.widget.bridge("uibutton", $.ui.button);
	</script>
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
	<script
		src="../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
	<script
		src="../plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
	<script src="../plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<script src="../plugins/fastclick/fastclick.js"></script>
	<script src="../plugins/iCheck/icheck.min.js"></script>
	<script src="../plugins/adminLTE/js/app.min.js"></script>
	<script src="../plugins/treeTable/jquery.treetable.js"></script>
	<script src="../plugins/select2/select2.full.min.js"></script>
	<script src="../plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
	<script
		src="../plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
	<script src="../plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
	<script
		src="../plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
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
	<script
		src="../plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
	<script
		src="../plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="../plugins/updateProduct/updateproduct.js"></script>
	<script>
		$(function() {

			function format_float(num, pos) {
				var size = Math.pow(10, pos);
				return Math.round(num * size) / size;
			}

			function preview(input) {

				if (input.files && input.files[0]) {
					var reader = new FileReader();

					reader.onload = function(e) {
						$('.preview').attr('src', e.target.result);
						var KB = format_float(e.total / 1024, 2);
						$('.size').text("檔案大小：" + KB + " KB");
					}

					reader.readAsDataURL(input.files[0]);
				}
			}

			$("body").on("change", ".upl", function() {
				preview(this);
			})

		})
		$(document).ready(function() {
			// 选择框
			$(".select2").select2();

			// WYSIHTML5编辑器
			$(".textarea").wysihtml5({
				locale : "zh-CN",
			});

		});

		// 设置激活菜单
		function setSidebarActive(tagUri) {
			var liObj = $("#" + tagUri);
			if (liObj.length > 0) {
				liObj.parent().parent().addClass("active");
				liObj.addClass("active");
			}
		}

		$(document).ready(function() {
			$("#datepicker-a3").datepicker({
				autoclose : true,
				language : "zh-CN",
			});
		});

		$(document).ready(function() {
			$("#datepicker-a6").datepicker({
				autoclose : true,
				language : "zh-CN",
			});
		});

		$(document).ready(function() {
			// 激活导航位置
			setSidebarActive("order-manage");
		});
	</script>
</body>
</html>
