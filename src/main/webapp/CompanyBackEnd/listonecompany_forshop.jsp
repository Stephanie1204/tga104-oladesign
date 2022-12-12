<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tibame.tga104.g2.oladesign.CompanyMember.vo.*" %>
<%
Company_MemVO company_memVO = (Company_MemVO) request.getAttribute("company_memVO");
%>
<!DOCTYPE html>
<html>

<head>
<!-- 頁面Meta -->
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>OLA Design 賣家中心 | 賣場管理 </title>
<meta name="description" content="AdminLTE2定制版">
<meta name="keywords" content="AdminLTE2定制版">
<meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
<link rel="stylesheet" href="../plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../plugins/font-awesome/css/font-awesome.min.css">
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

	<div class="wrapper">

		<!-- Ola Design Header -->
		<%@ include file="header.jsp"%>
		<!-- Ola Design Menu -->
		<%@ include file="company-menu.jsp"%>

		<!-- 内容區域 -->
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>賣家基本資料表</h1>
				<ol class="breadcrumb">
					<li><a href="company-index.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">基本資料</li>
				</ol>
			</section>
			<c:if test="${not empty errorMsgs}">
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<!-- Main content -->
			<section class="content">
				<div class="row">
					<!-- form start -->
					<form method="post" action="company_memberdo" name="form" enctype="multipart/form-data">
					<!-- left column -->
						<div class="col-md-6">
							<!-- general form elements -->
							<div class="box box-primary">
								<div class="box-header with-border">
									<h3 class="box-title">賣場管理</h3>
								</div>
								<!-- /.box-header -->
								<div class="box-body">
									<div class="form-group">
										<label for="com_taxid">統一編號<font color=red><b>*</b></font></label> 
										<input type="text" class="form-control" name="com_taxid" id="com_taxid" value="23045921" />
									</div>
									
									<div class="form-group">
										<label for="mem_id">會員編號<font color=red><b>*</b></font></label> <input type="text" class="form-control" name="mem_id" id="mem_id" />
									</div>

									<div class="form-group">
										<label for="store_name">賣場名稱<font color=red><b>*</b></font></label> <input type="text" class="form-control" name="store_name"
											id="store_name" value="${(company_memVO == null) ? '' : company_memVO.getStoreName()}" />
									</div>
									<div class="form-group">
										<label for="store_intro">賣場簡介</label> <input type="text" class="form-control" name="store_intro" id="store_intro"
											value="${(company_memVO == null) ? '' : company_memVO.getStoreIntro()}" />
									</div>

									<div class="form-group">
										<label for="store_logo">賣場Logo</label>
										<b><img src="${company_memVO.getStoreLogoString()}" width=30%/></b>
										<input type="file" id="store_logo" name="store_logo" class="upl1" style="display: none;">
										<br />
											<p class="help-block"></p>
									</div>

									<div class="form-group">
										<label for="store_banner">賣場Banner</label> 
										<b><img src="${company_memVO.getStoreBannerString()}" width=30% /></b>
										<input type="file" id="store_banner" name="store_banner" class="upl2" style="display: none;">
										<p class="help-block"></p>
										
									</div>
									<input type="hidden" name="com_taxid" value="${company_memVO.getComTaxId()}" />
									
									<div class="box-footer">
										<input type="hidden" name="action" value="updateshop_save"> 
										 <input type="submit" value="儲存" class="btn btn-primary"id="updateshop_save" disabled/>
										<input type="button"value="修改資料" class="btn btn-primary" id="updateshop" > 
										<input type="button" value="取消修改" class="btn btn-primary" id="cancle_updateshop"
											style="display: none">

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

	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
	<script src="../plugins/jQueryUI/jquery-ui.min.js"></script>
	<script>
		$.widget.bridge('uibutton', $.ui.button);
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
	<script src="../plugins/adminLTE/js/oladesign-address.js"></script>
	<script>
		$(document).ready(function() {
			// 选择框
			$(".select2").select2();

			// WYSIHTML5编辑器
			$(".textarea").wysihtml5({
				locale : 'zh-TW'
			});
			
	  	       //資料先寫死,合在一起之後要改
	           $("#com_taxid").val("23045921").attr('readonly', true);
	           $("#mem_id").val("220020").attr('readonly', true);

               // ajax call api to get CompantMembetInfo
               $.ajax({
                   type : 'POST',
                   url : 'http://localhost:8080/oladesign/CompanyBackEnd/company_memberdo?action=doGetStoreInfo&memId=220020',
                   success : function (data, status, xhr) {
                       var dataJson = JSON.parse(data);

                       if (dataJson.isMemberHasCom) {
                           // 開始set 資料
                           $("#com_taxid").val(dataJson.comTaxId).attr('readonly', true);
                           $("#mem_id").val(dataJson.memId).attr('readonly', true);
                           $("#store_name").val(dataJson.storeName).attr('readonly', true);
                           $("#store_intro").val(dataJson.storeIntro).attr('readonly', true);
                           $("#store_logo").val(dataJson.storeLogo).attr('readonly', true);
                           $("#store_banner").val(dataJson.storeBanner).attr('readonly', true);
                           $("#update_save").attr('disabled', 'disabled');
                       }
                   }
               });
	           
	  	       
		        $("#updateshop").on("click", ()=>{
		            doSetForm(false);
		        });
		
				$("#cancle_updateshop").on("click", ()=>{
		            doSetForm(true);      				
				})
				
		        function doSetForm(trueorfalse){
		            $("#store_name").attr('readonly', trueorfalse);
		            $("#store_intro").attr('readonly', trueorfalse);
		            $("#updateshop_save").attr('disabled', trueorfalse?'disabled' : null);
		            //$("#action").val(trueorfalse?"selectforshop":"updateshop_save"); // 要把action改成update，才不會重複inser
		            
		            if(trueorfalse){
						$("#updateshop").show();
						$("#cancle_updateshop").hide();
			            $("#store_logo").hide();
			            $("#store_banner").hide();
		            }else{
		            	$("#updateshop").hide();
		            	$("#cancle_updateshop").show();
			            $("#store_logo").show();
			            $("#store_banner").show();
		            }
		        }
			
			
			
			
		});

		// 设置激活菜单
		function setSidebarActive(tagUri) {
			var liObj = $("#" + tagUri);
			if (liObj.length > 0) {
				liObj.parent().parent().addClass("active");
				liObj.addClass("active");
			}
		}

		// 激活导航位置
		setSidebarActive("form-general");

		var preview_el = document.getElementById("preview");
		var p_file_el = document.getElementById("store_logo");
		var preview_img = function(file) {
			let reader = new FileReader();
			reader.readAsDataURL(file);
			reader
					.addEventListener(
							"load",
							function() {
								preview_el.innerHTML = `<img src='${reader.result}' class='preview_img'>`;
							});
		};
	</script>
</body>

</html>