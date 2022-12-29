<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>OLA Design 賣家中心 | 銷售報表</title>
    <meta name="description" content="AdminLTE2定制版" />
    <meta name="keywords" content="AdminLTE2定制版" />
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"name="viewport"/>
    <!-- 页面meta /-->
    <link rel="stylesheet" href="../plugins/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="../plugins/font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="../plugins/ionicons/css/ionicons.min.css" />
    <link rel="stylesheet" href="../plugins/iCheck/square/blue.css" />
    <link rel="stylesheet" href="../plugins/morris/morris.css" />
    <link rel="stylesheet" href="../plugins/jvectormap/jquery-jvectormap-1.2.2.css"/>
    <link rel="stylesheet" href="../plugins/datepicker/datepicker3.css" />
    <link rel="stylesheet" href="../plugins/daterangepicker/daterangepicker.css"/>
    <link rel="stylesheet" href="../plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css"/>
    <link rel="stylesheet" href="../plugins/datatables/dataTables.bootstrap.css"/>
    <link rel="stylesheet" href="../plugins/treeTable/jquery.treetable.css" />
    <link rel="stylesheet" href="../plugins/treeTable/jquery.treetable.theme.default.css"/>
    <link rel="stylesheet" href="../plugins/select2/select2.css" />
    <link rel="stylesheet" href="../plugins/colorpicker/bootstrap-colorpicker.min.css"/>
    <link rel="stylesheet" href="../plugins/bootstrap-markdown/css/bootstrap-markdown.min.css"/>
    <link rel="stylesheet" href="../plugins/adminLTE/css/AdminLTE.css" />
    <link rel="stylesheet" href="../plugins/adminLTE/css/skins/_all-skins.min.css"/>
    <link rel="stylesheet" href="../css/style.css" />
    <link rel="stylesheet" href="../plugins/ionslider/ion.rangeSlider.css" />
    <link rel="stylesheet" href="../plugins/ionslider/ion.rangeSlider.skinNice.css"/>
    <link rel="stylesheet" href="../plugins/bootstrap-slider/slider.css" />
    <link rel="stylesheet" href="../plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css"/>
  </head>

  <body class="hold-transition skin-purple sidebar-mini">
    <!-- Ola Design Header -->
    <%@ include file="header.jsp"%>
    <div class="wrapper">
      <!-- Ola Design Menu -->
      <%@ include file="company-menu.jsp"%>
      <div class="content-wrapper">
        <section class="content">
          <div class="box box-primary">
            <!-- Date -->
            <div class="form-group">
              <label>請選擇年份</label>
              <select name="reportYears" id="reportYears">
                <option value="2020">2020</option>
                <option value="2021">2021</option>
                <option value="2022">2022</option>
              </select>
              <button id="searchreport" class="btn btn-primary searchreport">搜尋</button>
              <div class="canvas_parent" style="height: 30%; width: 30%">
                <canvas id="myChart"></canvas>
              </div>
            </div>
          </div>
        </section>
      </div>
      <!-- Ola Design Footer -->
      <%@ include file="footer.jsp"%>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"></script>
    <script>
    $("#searchreport").on("click", function (e) {
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/oladesign/CompanyBackEnd/report.do?action=doGetCOMReport&comTaxId=${comMemVO.comTaxId}&reportYears=" + $("#reportYears").val(),
        success: function (data, status, xhr) {
          var dataJson = JSON.parse(data);

          const ctx = document.getElementById("myChart").getContext("2d");

          var chartLabels = ["01","02","03","04","05","06","07","08","09","10","11","12"];
          var chartData = [];

          chartLabels.forEach((labels)=>{
        	  var monthlyData = dataJson.filter((data)=>{
        		  return data.month === labels
        	  })

        	  return chartData.push(monthlyData[0]?.dayPrice);
          })

          const myChart = new Chart(ctx, {
            type: "bar",
            data: {
              labels: chartLabels,
              datasets: [
                {
                  label: "銷售報表",
                  data: chartData,
                  backgroundColor: [
                      'rgba(255, 99, 132, 0.2)',
                      'rgba(255, 159, 64, 0.2)',
                      'rgba(255, 205, 86, 0.2)',
                      'rgba(75, 192, 192, 0.2)',
                      'rgba(54, 162, 235, 0.2)',
                      'rgba(153, 102, 255, 0.2)',
                      'rgba(201, 203, 207, 0.2)',
                      'rgba(255, 99, 132, 0.2)',
                      'rgba(255, 159, 64, 0.2)',
                      'rgba(255, 205, 86, 0.2)',
                      'rgba(75, 192, 192, 0.2)',
                      'rgba(54, 162, 235, 0.2)',
                      'rgba(153, 102, 255, 0.2)'
                    ],
                    borderColor: [
                      'rgb(255, 99, 132)',
                      'rgb(255, 159, 64)',
                      'rgb(255, 205, 86)',
                      'rgb(75, 192, 192)',
                      'rgb(54, 162, 235)',
                      'rgb(153, 102, 255)',
                      'rgb(201, 203, 207)',
                      'rgb(255, 99, 132)',
                      'rgb(255, 159, 64)',
                      'rgb(255, 205, 86)',
                      'rgb(75, 192, 192)',
                      'rgb(54, 162, 235)',
                      'rgb(153, 102, 255)'
                    ],
                  borderWidth: 1,
                },
              ],
            },
          });
        },
      });
    });
    </script>
  </body>
</html>
