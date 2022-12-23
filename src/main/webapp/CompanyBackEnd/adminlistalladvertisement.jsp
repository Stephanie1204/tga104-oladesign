<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tibame.tga104.g2.oladesign.Advertisement.vo.*"%>
<%
AdvertisementVO advertisementVO = (AdvertisementVO) request.getAttribute("advertisementVO");
%>
<!DOCTYPE html>
<html>

<head>
<!-- 页面meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>OLA Design 賣家中心</title>
<meta name="description" content="AdminLTE2定制版">
<meta name="keywords" content="AdminLTE2定制版">
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
<!-- 页面meta /-->
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
<link rel="stylesheet"  href="../plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
<link href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css" rel="stylesheet">
</head>

<body class="hold-transition skin-purple sidebar-mini">
	<!-- Ola Design Header -->
	<%@ include file="header.jsp"%>
	<div class="wrapper">
		<!-- Ola Design Menu -->
		<%@ include file="company-menu.jsp"%>
		<!-- 内容区域 -->
		<div class="content-wrapper">
			<!-- 正文区域 -->
			<section class="content">
				<!-- .box-body -->
				<div class="box box-primary">
					<div class="box-header with-border"></div>
					<div class="box-body">
						<!-- 数据表格 -->
						<div class="table-box">
							<!--工具栏-->
							<div class="box-tools pull-right">
								<div class="has-feedback">

									<form method="post" action="advertisement.do">
										<input type="hidden" name="adminId" value="A001" /> 
										<input type="hidden" name="adStatus" value="${advertisementVO.getAdStatus()}" /> 
										<input type="hidden" name="adId" value="${advertisementVO.getAdId()}" />
									</form>
								</div>
							</div>
							<!--工具栏/-->

							<!--数据列表-->
							<table id="dataList"
								class="table table-bordered table-striped table-hover dataTable">
								<thead>
									<tr>
										<th class="text-center">廣告編號</th>
										<th class="text-center">公司統編</th>
										<th class="text-center">廣告開始日期</th>
										<th class="text-center">廣告結束日期</th>
										<th class="text-center">廣告狀態</th>
										<th class="text-center">廣告圖片</th>
										<th class="text-center">操作</th>

									</tr>
								</thead>
								<tbody id="row">
								</tbody>
							</table>
							<!--数据列表/-->
						</div>
						<!-- 数据表格 /-->


					</div>
					<!-- /.box-body -->

					<!-- .box-footer-->
					<div class="box-footer">
						<div class="pull-left"></div>

						<div class="box-tools pull-right">
							<ul class="pagination">
								<li><a href="#" aria-label="Previous">首頁</a></li>
								<li><a href="#">上一頁</a></li>
								<li><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">下一頁</a></li>
								<li><a href="#" aria-label="Next">頁尾</a></li>
							</ul>
						</div>

					</div>
					<!-- /.box-footer-->
				</div>

			</section>
			<!-- 正文区域 /-->

		</div>

		<!-- 内容区域 /-->

		<!-- Ola Design Footer -->
		<%@ include file="footer.jsp"%>

	</div>


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
	<script src="../plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
	<script src="../plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
	<script>
        $.ajax({
            type : 'POST',
            url : 'http://localhost:8080/oladesign/CompanyBackEnd/advertisement.do?action=doGetAllADInfo&adminId=A001',
            success : function (data, status, xhr) {
                var dataJson = JSON.parse(data);
				total_len = dataJson.length;
				for(i=0;i<total_len;i++){
					var statusNm = dataJson[i].adStatus? "完成" : "非完成";
					var isDisabled = dataJson[i].adStatus? "disabled":"";
					$("#row").append(
							"<tr>"+
							"<td>"+dataJson[i].adId+"</td>"+
							"<td>"+dataJson[i].comTaxId+"</td>"+
							"<td>"+dataJson[i].startDate+"</td>"+
							"<td>"+dataJson[i].endDate+"</td>"+
							"<td>"+ statusNm + "</td>"+
							"<td><img src = '" +dataJson[i].adImageString +"' style='max-width: 150px; max-height: 150px'></td>"+
							"<td><button " + isDisabled + " type='button' class='btn btn-default reviewad' adId=" + dataJson[i].adId + ">審核</button></td>"+
							"</tr>"
					)
				}
				
		        $(".reviewad").on("click", function (e) {
		            $.ajax({
		              type: "POST",
		              url:
		                "http://localhost:8080/oladesign/CompanyBackEnd/advertisement.do?action=doReviewAD&adminId=A001&adId=" +
		                $(e.currentTarget).attr("adid"),
		              success: function (data, status, xhr) {
		                swal({
		                  title: "審核完成",
		                  type: "success",
		                });
		              },
		            });
		          });
		        },
		      });
		    });
		  </script>
</body>

</html>