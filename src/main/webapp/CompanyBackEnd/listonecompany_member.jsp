<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.tibame.tga104.g2.oladesign.CompanyMember.vo.*" %>
<%Company_MemVO company_memVO = (Company_MemVO) request.getAttribute("company_memVO");%>
<!DOCTYPE html>
<html>
<head>
<!-- 頁面Meta -->
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>OLA Design 賣家中心</title>
<meta name="description" content="AdminLTE2定制版">
<meta name="keywords" content="AdminLTE2定制版">
<meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
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
	<%@ include file="header.jsp"%>
	<div class="wrapper">
		<!-- Ola Design Menu -->
		<%@ include file="company-menu.jsp"%>
		<!-- 内容區域 -->
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
			</section>
			<!-- Main content -->
			<section class="content">
				<div class="row">
					<!-- form start -->
					<form method="post" action="company_member.do" name="form1"
						enctype="multipart/form-data" style=width:100%>
						<!-- left column -->
						<div class="col-md-6">
							<!-- general form elements -->
							<div class="box box-primary">
								<div class="box-header with-border">
									<h3 class="box-title">基本資料</h3>
								</div>
								<!-- /.box-header -->
								<div class="box-body">
									<div class="form-group">
										<label for="com_taxid">公司統編</label>
										<input type="text" readonly="readonly" class="form-control" name="com_taxid"
											id="com_taxid"
											value="${company_memVO.getComTaxId()}" />
									</div>
									<div class="form-group">
										<label for="mem_id">會員編號</label>
											<input type="text" readonly="readonly" class="form-control" name="mem_id"
											id="mem_id"
											value="${company_memVO.getMemId()}" />
									</div>

									<div class="form-group">
										<label for="com_name">公司名稱</label>
											<input type="text" readonly="readonly" class="form-control" name="com_name"
											id="com_name"
											value="${company_memVO.getComName()}"/>
									</div>

									<div class="form-group">
										<label for="com_address">公司地址</label>
										<input type="text" readonly="readonly" class="form-control" name="com_address"
											id="com_address"
											value="${company_memVO.getComAddress()}"/>
									</div>

									<div class="form-group">
										<label for="com_phone">公司電話</label>
										<input type="text" readonly="readonly" class="form-control" name="com_phone"
											id="com_phone"
											value="${company_memVO.getComPhone()}"/>
									</div>
									<div class="form-group">
										<label for="com_owner">負責人</label>
										<input type="text" readonly="readonly" class="form-control" name="com_owner"
											id="com_owner"
											value="${company_memVO.getComOwner()}"/>
									</div>
									<div class="form-group">
										<label for="owner_phone">負責人手機號碼</label>
										<input type="text" readonly="readonly" class="form-control" name="owner_phone"
											id="owner_phone"
											value="${company_memVO.getOwnerPhone()}"/>
									</div>

									<div class="form-group">
										<label for="com_bankaccount">銀行帳戶</label>
										<input type="text" readonly="readonly" class="form-control" name="com_bankaccount"
											id="com_bankaccount"
											value="${company_memVO.getComBankaccount()}"/>
									</div>

								</div>
								<!--/.col (left) -->
							</div>
							<!-- /.row -->
						</div>
					</form>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<!-- 内容区域 /-->
		<!-- Ola Design Footer -->
		<%@ include file="footer.jsp"%>
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