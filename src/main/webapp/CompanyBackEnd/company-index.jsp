<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>OLA Design 賣家中心首頁</title>
<meta name="description" content="AdminLTE2定制版">
<meta name="keywords" content="AdminLTE2定制版">
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
	<!-- <link rel="stylesheet" href="../plugins/adminLTE/css/skins/oladesign-skin.css"> -->
	<link rel="stylesheet" href="/oladesign/plugins/adminLTE/css/skins/oladesign-skin.css">
  
</head>
<body class="hold-transition skin-purple sidebar-mini">
	<!-- Ola Design Header -->
	<%@ include file="header.jsp"%>
	<div class="wrapper">
		<!-- Ola Design Menu -->
		<%@ include file="company-menu.jsp"%>
		<div class="content-wrapper">
			<section class="content">
					<div class="col-lg-3 col-xs-6">
					<div class="designimage"></div>
					<h1 id="comname"></h1>
				</div>
			</section>
		</div>
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
<script>
$.ajax({
    type: "POST",
    url:
      "http://localhost:8080/oladesign/CompanyBackEnd/company_member.do?action=doGetComName&comTaxId=${comMemVO.comTaxId}",
    success: function (data, status, xhr) {
      var dataJson = JSON.parse(data);
      $("#comname").text(dataJson.comName + "   你好,歡迎來到賣家後台!");
    },
  });
</script>
</html>