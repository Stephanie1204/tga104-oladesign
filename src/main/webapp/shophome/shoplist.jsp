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
    <link rel="stylesheet" href="../shophome-css/nice-select.css" type="text/css"/>
    <link rel="stylesheet" href="../shophome-css/jquery-ui.min.css" type="text/css"/>
    <link rel="stylesheet" href="../shophome-css/owl.carousel.min.css" type="text/css"/>
    <link rel="stylesheet" href="../shophome-css/slicknav.min.css" type="text/css"/>
    <link rel="stylesheet" href="../shophome-css/style.css" type="text/css" />
    <link rel="stylesheet" href="../shophome-css/coupon.css" type="text/css" />
    <link rel="stylesheet" href="../shophome-css/shoplogolist.css" type="text/css" />
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
    <!-- Footer Section Begin -->
    <footer class="footer spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-3 col-md-6 col-sm-6">
            <div class="footer__about">
              <div class="footer__about__logo">
                <a href="./index.html" ><img src="../shophome-img/logo.png" alt=""
                /></a>
              </div>
              <ul>
                <li>Address: 60-49 Road 11378 New York</li>
                <li>Phone: +65 11.188.888</li>
                <li>Email: hello@colorlib.com</li>
              </ul>
            </div>
          </div>
          <div class="col-lg-4 col-md-6 col-sm-6 offset-lg-1">
            <div class="footer__widget">
              <h6>Useful Links</h6>
              <ul>
                <li><a href="#">About Us</a></li>
                <li><a href="#">About Our Shop</a></li>
                <li><a href="#">Secure Shopping</a></li>
                <li><a href="#">Delivery infomation</a></li>
                <li><a href="#">Privacy Policy</a></li>
                <li><a href="#">Our Sitemap</a></li>
              </ul>
              <ul>
                <li><a href="#">Who We Are</a></li>
                <li><a href="#">Our Services</a></li>
                <li><a href="#">Projects</a></li>
                <li><a href="#">Contact</a></li>
                <li><a href="#">Innovation</a></li>
                <li><a href="#">Testimonials</a></li>
              </ul>
            </div>
          </div>
          <div class="col-lg-4 col-md-12">
            <div class="footer__widget">
              <h6>Join Our Newsletter Now</h6>
              <p>
                Get E-mail updates about our latest shop and special offers.
              </p>
              <form action="#">
                <input type="text" placeholder="Enter your mail" />
                <button type="submit" class="site-btn">Subscribe</button>
              </form>
              <div class="footer__widget__social">
                <a href="#"><i class="fa fa-facebook"></i></a>
                <a href="#"><i class="fa fa-instagram"></i></a>
                <a href="#"><i class="fa fa-twitter"></i></a>
                <a href="#"><i class="fa fa-pinterest"></i></a>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-12">
            <div class="footer__copyright">
              <div class="footer__copyright__text">
                <p>
                  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                  Copyright &copy;
                  <script>
                    document.write(new Date().getFullYear());
                  </script>
                  All rights reserved | This template is made with
                  <i class="fa fa-heart" aria-hidden="true"></i> by
                  <a href="https://colorlib.com" target="_blank">Colorlib</a>
                  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                </p>
              </div>
              <div class="footer__copyright__payment">
                <img src="../shophome-img/payment-item.png" alt="" />
              </div>
            </div>
          </div>
        </div>
      </div>
    </footer>
    <!-- Footer Section End -->
    <!-- Js Plugins -->
    <script src="../shophome-js/jquery-3.3.1.min.js"></script>
    <script src="../shophome-js/bootstrap.min.js"></script>
    <script src="../shophome-js/jquery.nice-select.min.js"></script>
    <script src="../shophome-js/jquery-ui.min.js"></script>
    <script src="../shophome-js/jquery.slicknav.js"></script>
    <script src="../shophome-js/mixitup.min.js"></script>
    <script src="../shophome-js/owl.carousel.min.js"></script>
    <script src="../shophome-js/main.js"></script>
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
  </body>
</html>
