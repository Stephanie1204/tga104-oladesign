<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tibame.tga104.g2.oladesign.Advertisement.vo.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.CompanyMember.vo.*"%>
<%AdvertisementVO advertisementVO = (AdvertisementVO) request.getAttribute("advertisementVO");%>
<%Company_MemVO company_memVO = (Company_MemVO) request.getAttribute("company_memVO");%>
<!DOCTYPE html>
<html>
<head>
<!-- 页面meta -->
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>OLA Design 賣家中心 | 申請廣告</title>
<meta name="description" content="AdminLTE2定制版">
<meta name="keywords" content="AdminLTE2定制版">
<meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
<link rel="stylesheet" href="../plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../plugins/ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="../plugins/iCheck/square/blue.css">
<link rel="stylesheet" href="../plugins/morris/morris.css">
<link rel="stylesheet" href="../plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<link rel="stylesheet" href="../plugins/datepicker/datepicker3.css">
<link rel="stylesheet" href="../plugins/daterangepicker/daterangepicker.css">
<link rel="stylesheet" href="../plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<link rel="stylesheet" href="../plugins/datatables/dataTables.bootstrap.css">
<link rel="stylesheet" href="../plugins/treeTable/jquery.treetable.css">
<link rel="stylesheet" href="../plugins/treeTable/jquery.treetable.theme.default.css">
<link rel="stylesheet" href="../plugins/select2/select2.css">
<link rel="stylesheet" href="../plugins/colorpicker/bootstrap-colorpicker.min.css">
<link rel="stylesheet" href="../plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
<link rel="stylesheet" href="../plugins/adminLTE/css/AdminLTE.css">
<link rel="stylesheet" href="../plugins/adminLTE/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../plugins/ionslider/ion.rangeSlider.css">
<link rel="stylesheet" href="../plugins/ionslider/ion.rangeSlider.skinNice.css">
<link rel="stylesheet" href="../plugins/bootstrap-slider/slider.css">
<link rel="stylesheet" href="../plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">

</head>
<body class="hold-transition skin-purple sidebar-mini">
		<!-- Ola Design Header -->
		<%@ include file="header.jsp"%>
	<div class="wrapper">
		<!-- Ola Design Menu -->
		<%@ include file="company-menu.jsp"%>
		<!-- 内容区域 -->
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<form method="post" action="advertisement.do" name="form1"
				enctype="multipart/form-data">
				<jsp:useBean id="AdvertisementSvc" scope="page"
					class="com.tibame.tga104.g2.oladesign.Advertisement.service.AdvertisementService" />
				<input type="hidden" name="action" value="insert" />
				<!-- Main content -->
				<section class="content">
					<div class="row">
						<div class="col-md-12"></div>
						<!-- /.col (left) -->
						<div class="col-md-6">
							<div class="box box-primary">
						        <c:if test="${not empty errorMsgs}">
						          <ul>
						            <c:forEach var="message" items="${errorMsgs}">
						              <li style="color: red">${message}</li>
						            </c:forEach>
						          </ul>
						        </c:if>
								<div class="box-body">
									<div class="form-group"></div>
									<!-- /.form group -->
									<!--統一編號要改成自動帶入  -->
									<div class="form-group">
										<label for="com_taxid">公司統編<font color=red><b>*</b></font></label>
										<input type="text" class="form-control" name="com_taxid"
											id="com_taxid" value="${comMemVO.comTaxId}" readonly="readonly"/>
									</div>

									<div class="form-group">
										<label for="storeLink">廣告導入連結<font color=red><b>*</b></font></label>
										<input type="text" class="form-control" name="storelink"
											id="storelink"
											value="${(advertisementVO == null) ? '' : advertisementVO.getStoreLink()}" />
									</div>
									<!-- Date range -->
									<div class="form-group">
										<label>廣告時段<font color=red><b>*</b></font></label>
										<div class="input-group">
											<div class="input-group-addon">
												<i class="fa fa-calendar"></i>
											</div>
											<input type="text" class="form-control pull-right"
												name="reservation" id="reservation" />
										</div>
									</div>

									<div class="form-group">
										<label for="store_logo">圖片上傳<font color=red><b>*</b></font>
										</label> <input type="file" id="adimages" name="adimages">
										<p class="help-block"></p>
										<img id="preview_adimages" style="max-width: 150px; max-height: 150px;"/>
									</div>

									<div>
										<input type="submit" value="送出新增" class="btn btn-primary">
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
			</form>
		</div>
		<!-- 内容区域 /-->
		<!-- Ola Design Footer -->
		<%@ include file="footer.jsp"%>
	</div>
	<script src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
	<script src="../plugins/jQueryUI/jquery-ui.min.js"></script>
	<script>$.widget.bridge('uibutton', $.ui.button);</script>
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
	<script>
		$(function() {
			//Date range picker
			$('#reservation').daterangepicker({
				locale : {
					applyLabel : '確認',
					cancelLabel : '取消',
					fromLabel : '起始時間',
					toLabel : '結束時間',
					customRangeLabel : '自定義',
					firstDay : 1
				},
				opens : 'left', // 日期选择框的弹出位置
				separator : ' 至 '
			//showWeekNumbers : true,     // 是否显示第几周
			});
		});	
		   $("#adimages").change(function(){
			      //當檔案改變後，做一些事 
			     readURL(this);   // this代表<input id="imgInp">
			   });
		   function readURL(input){
			   if(input.files && input.files[0]){
			     var reader = new FileReader();
			     reader.onload = function (e) {
			        $("#preview_adimages").attr('src', e.target.result);
			     }
			     reader.readAsDataURL(input.files[0]);
			   }
			 }
	</script>
</body>

</html>