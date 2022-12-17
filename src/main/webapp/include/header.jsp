<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!-- Google Font -->
    <link
      href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
      rel="stylesheet"
    />

<!-- header css -->
<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="../css/header.css" type="text/css" />

<!-- Font Awesome -->
<script src="https://kit.fontawesome.com/51435664f6.js"
	crossorigin="anonymous"></script>

<header class="header">
      <!-- <div class="container"> -->
      <div class="row">
        <div class="col-lg-3">
          <div class="header__logo">
            <a href="<%=request.getContextPath()%>/homePage/index.jsp"
              ><img src="../img/OLA_Logo.svg" alt="logo"
            /></a>
          </div>
        </div>

        <!-- search bar -->
        <div class="col-lg-4">
          <div class="hero__search__form">
            <form
              action="<c:url value='/pages/product.controller' />"
              method="get"
            >
              <input
                type="text"
                id="name"
                placeholder="What do yo u need?"
                name="name"
                value="${param.name}"
              />
              <button
                type="submit"
                name="prodaction"
                value="Select"
                class="site-btn"
              >
                SEARCH
              </button>
            </form>
          </div>
        </div>

        <!-- icon -->
        <div class="col-lg-5">
          <div class="header__cart">
            <ul>
              <li>
                <button type="button" class="regist_com" aria-expanded="false">
                  <a href="#">成為賣家</a>
                </button>
              </li>
              <li class="logout">
                <button type="button" class="login"
							aria-expanded="false">
							<a
								href="<%=request.getContextPath()%>/member/login.jsp">註冊/登入</a></i>
              </button> 
              </li>
              <li class="login">
              	<span class="member"
                  ><img
                    id="memPhoto"
                    alt="會員照片"
                    src="${(memberVO.memPhoto == null)? '../img/default_photo.jpg' : memberVO.memPhotoBase64}"
                  /> ${memName}</span
                >
                <ul class="member__menu__dropdown">
                  <li><a href="#">會員中心</a></li>
                  <li><a href="#">我的訂單</a></li>
                  <li><a href="#">我的紅利</a></li>
                  <li><a href="#">聯絡客服</a></li>
                  <li><form method="post" action="<%=request.getContextPath()%>/member/MemberLogin">
                  		<button type="submit" class="logout" id="logout">登出</button>
                  		<input type="hidden" name="action" value="logout"> 
                  </form></li>
                </ul>
              </li>
              <li>
                <a href="#"
                  ><i class="fas fa-comment-dots"></i> <span>1</span></a
                >
              </li>
              <li>
                <a href="#"><i class="fa fa-heart"></i> <span>1</span></a>
              </li>
              <li>
                <a
                  href="<%=request.getContextPath()%>/homePage/shopping_cart.jsp"
                  ><i class="fa fa-shopping-bag"></i> <span>3</span></a
                >
              </li>
            </ul>
          </div>
        </div>

        <!-- </div> -->
      </div>
      <div class="row nav">
        <!-- nav bar -->
        <div class="col-lg-8">
          <nav class="header__menu">
            <ul class="nav">
              <li class="active">
                <a href="<%=request.getContextPath()%>/homePage/index.jsp"
                  >首頁</a
                >
              </li>
              <li>
                <a href="#">全站商品分類</a>
                <ul class="header__menu__dropdown">
                  <li><a href="#">餐具</a></li>
                  <li><a href="#">床包被套</a></li>
                  <li><a href="#">燈具</a></li>
                </ul>
              </li>
              <li><a href="#">促銷商品</a></li>
              <li><a href="./blog.html">設計館</a></li>
              <li><a href="./contact.html">聯絡我們</a></li>
            </ul>
          </nav>
        </div>
      </div>
    </header>
    
<!-- 		jQuery CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
		
		$(window).on("load", function(){
			let user = "${memName}";
			console.log("user=" + user);
			if(user.trim().length != 0){ //登入狀態
				console.log("已登入");
				$("li.logout *").addClass("none");
			}else{ //未登入
				console.log("未登入");
				$("li.login *").addClass("none");
			}
		});
</script>
