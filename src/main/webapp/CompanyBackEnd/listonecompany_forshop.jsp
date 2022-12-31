<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tibame.tga104.g2.oladesign.CompanyMember.vo.*" %>
<%Company_MemVO company_memVO = (Company_MemVO) request.getAttribute("company_memVO");%>
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
	<link href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css" rel="stylesheet"/>
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
			<!-- Main content -->
			<section class="content">
				<div class="row">
					<!-- form start -->
					<form method="post" action="company_member.do" name="form" enctype="multipart/form-data" style=width:100%>
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
										<input type="text" class="form-control" name="com_taxid" id="com_taxid" value="" readonly/>
									</div>
									
									<div class="form-group">
										<label for="memId">會員編號<font color=red><b>*</b></font></label> 
										<input type="text" class="form-control" name="memId" id="memId"  value="${memId}" readonly/>
									</div>

									<div class="form-group">
										<label for="store_name">賣場名稱<font color=red><b>*</b></font>
										 <span class="errorcolor">${errorMsgs.store_name}</span></label> 
										 <input type="text" class="form-control" name="store_name"
											id="store_name" value="${(company_memVO == null) ? '' : company_memVO.getStoreName()}" />
									</div>
									<div class="form-group">
										<label for="store_intro">賣場簡介</label> 
										<textarea class="form-control" name="store_intro" id="store_intro"/></textarea>
									</div>

									<div class="form-group">
										<label for="store_logo">賣場Logo</label>										
										<input type="file" id="store_logo" name="store_logo" >
										<img id="preview_store_logo" style="max-width: 150px; max-height: 150px;"/>
										<br />
											<p class="help-block"></p>
									</div>

									<div class="form-group">
										<label for="store_banner">賣場Banner</label> 
										<input type="file" id="store_banner" name="store_banner" >
										<img id="preview_store_banner" style="max-width: 150px; max-height: 150px;"/>
										<br />
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
	<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
	<script>
	var ori_store_logo = "";
	var ori_store_banner = "";
               // ajax call api to get CompantMembetInfo
               $.ajax({
                   type : 'POST',
                   url : 'http://localhost:8080/oladesign/CompanyBackEnd/company_member.do?action=doGetStoreInfo&memId=${memId}',
                   success : function (data, status, xhr) {
                       var dataJson = JSON.parse(data);

                       if (dataJson.isMemberHasCom) {
                           // 開始set 資料
                           $("#com_taxid").val(dataJson.comTaxId).attr('readonly', true);
                           $("#memId").val(dataJson.memId).attr('readonly', true);
                           $("#store_name").val(dataJson.storeName).attr('readonly', true);
                           $("#store_intro").val(dataJson.storeIntro).attr('readonly', true);
                           $("#preview_store_logo").attr("src", dataJson.StoreLogoString);
                           $("#preview_store_banner").attr("src", dataJson.StoreBannerString);
                           $("#update_save").attr('disabled', 'disabled');
                           
                           ori_store_logo = dataJson.storeLogoString;
                        	ori_store_banner = dataJson.storeBannerString;
                       }else {
                           swal({
                               title: "賣家功能未啟用",
                               text: "來去註冊吧",
                               type: "error",
                               showConfirmButton: true,
                               allowEscapeKey: false,
                               allowOutsideClick: false,
                             });
                             $(".confirm").click(function () {
                               location.href =
                                 "http://localhost:8080/oladesign/CompanyBackEnd/regisToCom.jsp";
                             });
                           }
                   }
               });
	           
		        $("#updateshop").on("click", ()=>{
		            doSetForm(false);
		        });
		
				$("#cancle_updateshop").on("click", ()=>{
		            doSetForm(true);      	
                    $("#preview_store_logo").attr("src", ori_store_logo);
                    $("#preview_store_banner").attr("src", ori_store_banner);
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
		   $("#store_logo").change(function(){

			      //當檔案改變後，做一些事 
			     readLogo(this);   // this代表<input id="imgInp">
			   });

		   function readLogo(input){
			   if(input.files && input.files[0]){
			     var reader = new FileReader();
			     reader.onload = function (e) {
			        $("#preview_store_logo").attr('src', e.target.result);
			     }
			     reader.readAsDataURL(input.files[0]);
			   }
			 }
		   
		   $("#store_banner").change(function(){
			      //當檔案改變後，做一些事 
			     readBanner(this);   // this代表<input id="imgInp">
			   });
		   function readBanner(input){
			   if(input.files && input.files[0]){
			     var reader = new FileReader();
			     reader.onload = function (e) {
			        $("#preview_store_banner").attr('src', e.target.result);
			     }
			     reader.readAsDataURL(input.files[0]);
			   }
			 }
	</script>
</body>

</html>