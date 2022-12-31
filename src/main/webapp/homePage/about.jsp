<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">
  <head>
    <meta charset="UTF-8" />
    <meta name="description" content="Ogani Template" />
    <meta name="keywords" content="Ogani, unica, creative, html" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>OLA Design | 關於我們</title>

    <!-- Google Font -->
    <link
      href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
      rel="stylesheet"
    />

    <!-- Css Styles -->
    <!-- <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"> -->
    <link rel="stylesheet"
	href="<%=request.getContextPath()%>/homePage/css/elegant-icons.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/homePage/css/nice-select.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/homePage/css/jquery-ui.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/homePage/css/owl.carousel.min.css"
	type="text/css" />
<!-- <link rel="stylesheet" -->
<%-- 	href="<%=request.getContextPath()%>/homePage/css/style.css" --%>
<!-- 	type="text/css" /> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/homePage/css/slicknav.min.css"
	type="text/css" />
    <link rel="stylesheet" href="css/about.css" type="text/css" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
  </head>

  <body>
    <!-- Page Preloder -->
    <div id="preloder">
      <div class="loader"></div>
    </div>

    <!-- Header -->
	<%@ include file="../include/header.jsp"%>
    <!-- Breadcrumb Section Begin -->
    <section
      class="breadcrumb-section set-bg"
      data-setbg="./img/about/pexels-miesha-renae-maiden-390573.jpg"
    >
      <div class="container">
        <div class="row">
          <div class="col-lg-12 text-center">
            <div class="filter"></div>
            <div class="breadcrumb__text">
              <div>
                <h2>Java雲端服務開發技術養成班</h2>
                <h2>TibaMe TGA104 第二組</h2>
              </div>
              <div class="breadcrumb__option">
                <span>
                  指導老師: 吳永志老師、吳冠宏老師、郭惠民老師、陳怡榕老師、李偉銘老師
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- about nav Begin -->
    <aside class="about">
      <ul class="about">
        <a class="begin"><li><span>OLA Design緣起</span></li></a>
        <a class="logostory"><li><span>LOGO由來</span></li></a>
        <a class="team"><li><span>開發團隊</span></li></a>
      </ul>
    </aside>
    <!-- about nav End -->

    <!-- Map Begin -->
    <section class="content">
      <div>
        <h1 id="begin">OLA Design緣起</h1>
        <p>
          SARS-CoV-2於2020年起迅速擴散至全球後，長期與疫情的抗戰影響到實體銷售通路，加上網路、行動裝置的普及與便利，使宅經濟文化興起。
          隨著消費者購物習慣的轉變，許多業者也紛紛開始轉型、經營自有品牌，其中不乏許多生活風格品牌，藉由創意帶給現代人全新的體驗與感受。
        </p>
        <p>
          而我們想建立一個便利的設計師與消費者之間的購物平台，一方面提供設計師販售、打響知名度的通路，
          另一方面讓追求質感、設計風格的消費者，在這裡找到屬於他們「生活品味」的商品。
        </p>
      </div>
      <div>
        <h1 id="logostory">LOGO由來</h1>
        <div class="ola">
          <img src="./img/about/Word Art.png" class="ola" />
          <!-- https://wordart.com/create -->
          <p>
            Ola是葡萄牙人的「你好」，希望這個平台給大家像葡萄牙人熱情友善的第一印象。而網站設計也如同葡萄牙的民風一般，雖然不華麗，但真誠與純樸，
            想要讓不論是賣作品的設計師還是買設計的消費者都能在這個平台找到屬於他們的風景。
          </p>
        </div>
      </div>

      <div>
        <h1 id="team">開發團隊</h1>
      </div>
      <section class="card-list">
      <!-- card1 -->
        <div class="card-deck">
          <div class="a_card">
            <div class="card-head1">
              <div class="card-author">
                <a class="author-avatar" href="https://github.com/Stephanie1204" target="_blank">
                  <img src="./img/about/steph.jpg" />
    
                <div class="author-name">
                  <span class="author-name-prefix">組長__黃絹雯 <i class="fa-brands fa-github"></i></span>
                </div>
              </a>
              </div>
            </div>
            <div class="card-body">
              <h5 class="card-text">負責功能:</h5>
              <ul>
                <li>賣家-促銷活動</li>
                <li>會員-基本資料</li>
                <li>會員-修改密碼</li>
                <li>會員-紅利</li>
                <li>會員-站內信</li>
                <li>會員-訂單管理</li>
              </ul>
              
            </div>
            <!-- <div class="card-footer">
              <h5 class="product">XXX</h5>
            </div> -->
          </div>
        </div>
        <!-- Add more cards here -->
        <!-- card2 -->
        <div class="card-deck">
          <div class="a_card">
            <div class="card-head2">
              <div class="card-author">
                <a class="author-avatar" href="https://github.com/Jesse7073" target="_blank">
                  <img src="./img/about/jesse.jpg" />
    
                <div class="author-name">
                  <span class="author-name-prefix">組員__孟子翔 <i class="fa-brands fa-github"></i></span>
                </div>
              </a>
              </div>
            </div>
            <div class="card-body">
              <h5 class="card-text">負責功能:</h5>
              <ul>
                <li>會員註冊/登入</li>
                <li>賣家註冊</li>
                <li>忘記密碼</li>
                <li>首頁UI設計</li>
                <li>商品收藏</li>
                <li>關於我們</li>
              </ul>
              
            </div>
            <!-- <div class="card-footer">
              <h5 class="product">XXX</h5>
            </div> -->
          </div>
        </div>
        <!-- card3 -->
        <div class="card-deck">
          <div class="a_card">
            <div class="card-head3">
              <div class="card-author">
                <a class="author-avatar" href="https://github.com/Frances75" target="_blank">
                  <img src="./img/about/france.png" />
    
                <div class="author-name">
                  <span class="author-name-prefix">組員__吳佳璇 <i class="fa-brands fa-github"></i></span>
                </div>
              </a>
              </div>
            </div>
            <div class="card-body">
              <h5 class="card-text">負責功能:</h5>
              <ul>
                <li>賣家基本資料</li>
                <li>賣場後台</li>
                <li>賣場報表生成</li>
                <li>賣家廣告申請</li>
                <li>聊天室</li>
              </ul>
              
            </div>
            <!-- <div class="card-footer">
              <h5 class="product">XXX</h5>
            </div> -->
          </div>
        </div>
        <!-- card4 -->
        <div class="card-deck">
          <div class="a_card">
            <div class="card-head4">
              <div class="card-author">
                <a class="author-avatar" href="https://github.com/shu2320" target="_blank">
                  <img src="./img/about/hsu.png" />
    
                <div class="author-name">
                  <span class="author-name-prefix">組員__徐翊庭 <i class="fa-brands fa-github"></i></span>
                </div>
              </a>
              </div>
            </div>
            <div class="card-body">
              <h5 class="card-text">負責功能:</h5>
              <ul>
                <li>購物車</li>
                <li>訂單</li>
                <li>商城頁面</li>
                <li>商品結帳</li>
                <li>綠界金流API串接</li>
              </ul>
              
            </div>
            <!-- <div class="card-footer">
              <h5 class="product">XXX</h5>
            </div> -->
          </div>
        </div>
        <!-- card5 -->
        <div class="card-deck">
          <div class="a_card">
            <div class="card-head5">
              <div class="card-author">
                <a class="author-avatar" href="###" target="_blank">
                  <img src="./img/about/leo2.jpg" />
    
                <div class="author-name">
                  <span class="author-name-prefix">組員__陳彥竹 <i class="fa-brands fa-github"></i></span>
                </div>
              </a>
              </div>
            </div>
            <div class="card-body">
              <h5 class="card-text">負責功能:</h5>
              <ul>
                <li>管理員-登入</li>
                <li>管理員-站內信</li>
                <li>管理員-會員管理</li>
                <li>管理員-廠商管理</li>
                <li>管理員-訂單管理</li>
                <li>管理員-商品類別管理</li>
              </ul>
              
            </div>
            <!-- <div class="card-footer">
              <h5 class="product">XXX</h5>
            </div> -->
          </div>
        </div>
     </section>   
    </section>
    <!-- Map End -->

	<!-- 	footer -->
	<%@ include file="../include/footer.jsp"%>
    <!-- Js Plugins -->
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.nice-select.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/jquery.slicknav.js"></script>
    <script src="js/mixitup.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/main.js"></script>
	<!--     商品收藏 -->
    <%@ include file="../include/favorite.jsp"%>
    <script type="text/javascript">
    	$(window).scroll(function (){
    		var scrollVal = $(this).scrollTop();
    		if(scrollVal > 300 && scrollVal < 1050){
    			$("aside.about").fadeIn(500);
    		}else{
    			$("aside.about").fadeOut(500);
    		}
    		
    		$("a.begin").on("click", function(){
    			$(window).scrollTop(330);
    		});
    		
    		$("a.logostory").on("click", function(){
    			$(window).scrollTop(608);
    		});
    		
    		$("a.team").on("click", function(){
    			$(window).scrollTop(892);
    		});
    	});
    </script>
  </body>
</html>
