<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
						<div class="col-md-2 title">公司統編</div>
						<div class="col-md-4 data">
							<input type="text" class="form-control" placeholder="公司統編"
								value="" />
						</div>

						<div class="col-md-2 title">商品名稱</div>
						<div class="col-md-4 data">
							<input type="text" class="form-control" placeholder="商品名稱"
								value="" />
						</div>

						<div class="col-md-2 title">商品類別</div>
						<div class="col-md-4 data">
							<select class="form-control" style="width: 100%">
								<option value="" selected="selected">無</option>
								<option value="">餐具</option>
								<option value="">床包被套</option>
								<option value="">燈具</option>
							</select>
						</div>

						<div class="col-md-2 title">商品風格</div>
						<div class="col-md-4 data">
							<select class="form-control" style="width: 100%">
								<option value="" selected="selected">無</option>
								<option value="">日式風格</option>
								<option value="">美式風格</option>
								<option value="">歐式風格</option>
								<option value="">韓式風格</option>
							</select>
						</div>

						<div class="col-md-2 title">商品價格</div>
						<div class="col-md-4 data">
							<input type="text" class="form-control" placeholder="商品價格"
								value="" />
						</div>

						<div class="col-md-2 title">商品庫存</div>
						<div class="col-md-4 data">
							<input type="text" class="form-control" placeholder="商品庫存"
								value="" />
						</div>

						<div class="col-md-2 title">安全存量</div>
						<div class="col-md-4 data">
							<input type="text" class="form-control" placeholder="安全存量"
								value="" />
						</div>

						<div class="col-md-2 title">商品狀態</div>
						<div class="col-md-4 data">
							<select class="form-control" style="width: 100%">
								<option value="">上架</option>
								<option value="">下架</option>
							</select>
						</div>
						<div class="col-md-2 title rowHeight2x">圖片上傳</div>
						<div class="col-md-10 data" style="height: 320px">
							<input type="file" multiple name="" id="uploadimage" class="upl" />
							<div class="img-box"></div>
						</div>
						
						<div class="col-md-2 title rowHeight2x">商品介紹</div>

						<!--编辑器-->
						<div class="tab-pane" id="tab-editer">
							<div class="col-md-10 data editer">
								<textarea class="textarea" placeholder="請輸入商品介紹"
									style="width: 100%; height: 265px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
							</div>
						</div>
						<!--编辑器/-->
						<!--工具栏-->
						<div class="box-tools text-center">
							<hr />
							<button type="button" class="btn bg-maroon">保存</button>
							<button type="button" class="btn bg-default"
								onclick="history.back(-1);">返回</button>
							<hr />
						</div>
						<!--工具栏/-->
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
	<script>
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

		$('#uploadimage')
				.on(
						'change',
						function(e) {
							
							var files = e.target.files;
							if (files.length > 0) {
								for (var i = 0; i < files.length; i++) {
									var reader = new FileReader();
									reader.onload = function() {
										var text = "<div id='upload'><img src='" + this.result + "' style='max-height: 100px' alt=''>"
										$('.img-box').append(text);
									};
									reader.readAsDataURL(files[i]);
								}
								;
							}
							;
						})
	</script>
</body>
</html>
