<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="com.tibame.tga104.g2.oladesign.CompanyMember.vo.*" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="description" content="Ogani Template" />
    <meta name="keywords" content="Ogani, unica, creative, html" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>OLA Design | 設計館總覽</title>
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet"/>
    <!-- Css Styles -->
    <link rel="stylesheet" href="../shophome-css/bootstrap.min.css" type="text/css" />

    <!--<link rel="stylesheet" href="../shophome-css/elegant-icons.css" type="text/css">-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/shophome-css/nice-select.css" type="text/css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/shophome-css/jquery-ui.min.css" type="text/css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/shophome-css/owl.carousel.min.css" type="text/css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/shophome-css/slicknav.min.css" type="text/css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/shophome-css/style.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/shophome-css/coupon.css" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/shophome-css/shoplogolist.css" type="text/css" />
  </head>
  <body>
    <%@ include file="../include/header.jsp"%>
    <!-- Page Preloder -->
    <div id="preloder">
      <div class="loader"></div>
    </div>
    <!-- Related Blog Section Begin -->
    <section class="related-blog spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <div class="section-title related-blog-title">
              <h2>設計館總覽</h2>
            </div>
          </div>
        </div>
        <div class="row" id="companyList">

        </div>
      </div>
    </section>
    <!-- Related Blog Section End -->
	<%@ include file="../include/footer.jsp"%>
    <!-- Js Plugins -->
    <script src="<%=request.getContextPath()%>/shophome-js/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/shophome-js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/shophome-js/jquery.nice-select.min.js"></script>
    <script src="<%=request.getContextPath()%>/shophome-js/jquery-ui.min.js"></script>
    <script src="<%=request.getContextPath()%>/shophome-js/jquery.slicknav.js"></script>
    <script src="<%=request.getContextPath()%>/shophome-js/mixitup.min.js"></script>
    <script src="<%=request.getContextPath()%>/shophome-js/owl.carousel.min.js"></script>
    <script src="<%=request.getContextPath()%>/shophome-js/main.js"></script>
    <script>
    $.ajax({
        type : 'POST',
        url: "http://localhost:8080/oladesign/shophome/shopinfo.do?action=doGetShopList",
        success : function (data, status, xhr) {
            var dataJson = JSON.parse(data);
            total_len = dataJson.length;
            for(i = 0 ; i < total_len; i++){
              
            	$("#companyList").append(
            "<div class='col-lg-4 col-md-4 col-sm-6'>" + 
            "    <div class='blog__item'>" + 
            "<a href='http://localhost:8080/oladesign/shophome/shopinfo.jsp?comTaxId=" + dataJson[i].comTaxId + "'>" + 
            "      <div class='blog__item__pic' id ='store_logo'>" + 
            "        <img id = 'store_logo' src='" + dataJson[i].StoreLogoString +  "' class ='circlestorelogo' />" + 
            "      </div>" + 
            "      <div class='blog__item__text'>" + 
            "        <h5 id='store_name'>" + dataJson[i].storeName + "</h5>" + 
            "      </div>" + 
            "</a>" +
            "    </div>" + 
            "   </div>" 
            )
            	
            }
        }
    });
    </script>
    <%@ include file="../include/favorite.jsp"%>
  </body>
</html>
