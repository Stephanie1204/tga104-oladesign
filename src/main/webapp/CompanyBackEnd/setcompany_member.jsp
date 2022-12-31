<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page import="com.tibame.tga104.g2.oladesign.CompanyMember.vo.*" %> 
<%@ page import="com.tibame.tga104.g2.oladesign.member.bean.*" %> 
<%Company_MemVO company_memVO = (Company_MemVO) request.getAttribute("company_memVO");%>
<%MemberVO memberVO = (MemberVO) request.getAttribute("MemberVO");%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>OLA Design 賣家中心 | 賣家基本資料</title>
    <meta name="description" content="AdminLTE2定制版" />
    <meta name="keywords" content="AdminLTE2定制版" />
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"name="viewport"/>
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
      <div class="content-wrapper">
        <section class="content-header"></section>
        <section class="content">
          <div class="row">
            <form  method="post" action="company_member.do" name="form1">
              <div class="col-md-6">
                <div class="box box-primary">
                  <div class="box-header with-border">
                    <h3 class="box-title">基本資料</h3>
                  </div>
                  <div class="box-body">
                    <div class="form-group">                   
                      <label for="com_taxid">公司統編<font color="red"><b>*</b></font>
                      </label>
                      <input type="text" class="form-control" name="com_taxid" id="com_taxid"
                        value="" readonly/>
                    </div>
                    <div class="form-group">
                      <label for="memId">會員編號<font color="red"><b>*</b></font>
                      <span class="errorcolor">${errorMsgs.memId}</span></label>
                      <input type="text" class="form-control" name="memId" id="memId" value="${memId}" readonly/>
                    </div>

                    <div class="form-group">
                      <label for="com_name">公司名稱<font color="red"><b>*</b></font>
                      <span class="errorcolor">${errorMsgs.com_name}</span></label>
                      <input type="text" class="form-control" name="com_name"  id="com_name"
                        value=""/>
                    </div>

                    <div class="form-group">
                      <label for="com_address">公司地址<font color="red"><b>*</b></font>
                      <span class="errorcolor">${errorMsgs.com_address}</span></label>
                      <input type="text" class="form-control" name="com_address" id="com_address"
                        value=""/>
                    </div>

                    <div class="form-group">
                      <label for="com_phone">公司電話<font color="red"><b>*</b></font>
                      <span class="errorcolor">${errorMsgs.com_phone}</span></label>
                      <input type="text" class="form-control" name="com_phone" id="com_phone"/>
                    </div>
                    <div class="form-group">
                      <label for="com_owner">負責人<font color="red"><b>*</b></font>
                      <span class="errorcolor">${errorMsgs.com_owner}</span></label>
                      <input type="text" class="form-control" name="com_owner"id="com_owner"/>
                    </div>
                    <div class="form-group">
                      <label for="owner_phone">負責人手機號碼<font color="red"><b>*</b></font>
                      <span class="errorcolor">${errorMsgs.owner_phone}</span></label>
                      <input type="text" class="form-control" name="owner_phone"id="owner_phone"/>
                    </div>

                    <div class="form-group">
                      <label for="com_bankaccount">銀行帳戶
                      <span class="errorcolor">${errorMsgs.com_bankaccount}</span></label>
                      <input type="text" class="form-control" name="com_bankaccount" id="com_bankaccount"/>
                    </div>

                    <div class="box-footer">
                      <input type="hidden" name="action" value="insert" id="action"/>
                      <input type="submit" value="儲存" class="btn btn-primary" id="update_save"/>
                      <input type="button" value="修改資料"class="btn btn-primary" id="update"/>
                      <input type="button" value="取消修改" class="btn btn-primary" id="cancle"
                      style="display: none"/>
                    </div>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </section>
      </div>
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
        // ajax call api to get CompantMembetInfo
        $.ajax({
          type: "POST",
          url: "http://localhost:8080/oladesign/CompanyBackEnd/company_member.do?action=doGetCompantMembetInfo&memId=${memId}",
          success: function (data, status, xhr) {
            var dataJson = JSON.parse(data);

            if (dataJson.isMemberHasCom) {
              $("#com_taxid").val(dataJson.comTaxId).attr("readonly", true);
              $("#memId").val(dataJson.memId).attr("readonly", true);
              $("#com_name").val(dataJson.comName).attr("readonly", true);
              $("#com_address").val(dataJson.comAddress).attr("readonly", true);
              $("#com_phone").val(dataJson.comPhone).attr("readonly", true);
              $("#com_owner").val(dataJson.comOwner).attr("readonly", true);
              $("#owner_phone").val(dataJson.ownerPhone).attr("readonly", true);
              $("#com_bankaccount")
                .val(dataJson.comBankaccount)
                .attr("readonly", true);
              $("#update_save").attr("disabled", "disabled");
            } else {
              swal({
                title: "賣家功能未啟用",
                text: "來去註冊吧",
                type: "error",
                showConfirmButton: true,
                allowEscapeKey: false,
                allowOutsideClick: false,
              });
              $("button, .confirm").click(function () {
                location.href =
                  "http://localhost:8080/oladesign/CompanyBackEnd/regisToCom.jsp";
              });
            }
          },
        });
      $("#update").on("click", () => {
        doSetForm(false);
      });

      $("#cancle").on("click", () => {
        doSetForm(true);
      });

      function doSetForm(trueorfalse) {
        $("#com_name").attr("readonly", trueorfalse);
        $("#com_address").attr("readonly", trueorfalse);
        $("#com_phone").attr("readonly", trueorfalse);
        $("#com_owner").attr("readonly", trueorfalse);
        $("#owner_phone").attr("readonly", trueorfalse);
        $("#com_bankaccount").attr("readonly", trueorfalse);
        $("#update_save").attr("disabled", trueorfalse ? "disabled" : null);
        $("#action").val(trueorfalse ? "insert" : "update_save");

        if (trueorfalse) {
          $("#update").show();
          $("#cancle").hide();
        } else {
          $("#update").hide();
          $("#cancle").show();
        }
      }

    </script>
  </body>
</html>
