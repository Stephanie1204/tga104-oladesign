<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>        
<link rel="stylesheet" href="/oladesign/plugins/adminLTE/css/skins/oladesign-skin.css">
<!-- <link rel="stylesheet" -->
<!-- 	href="../plugins/adminLTE/css/skins/oladesign-skin.css"> -->
<header class="main-header">
  <!-- Logo -->
  <a href="<%=request.getContextPath()%>/homePage/index.jsp" class="logo">
    <span class="logo-mini"><b>賣家後台首頁</b></span>
    <span class="logo-lg">
      <img src="/oladesign/img/OLA_Logo.svg" width="70%"
    /></span>
  </a>
  <nav class="navbar navbar-static-top">
    <!-- Sidebar toggle button-->
    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
      <span class="sr-only">Toggle navigation</span>
    </a>

    <div class="navbar-custom-menu">
      <ul class="nav navbar-nav">
        <li>
          <form
            method="post"
            class="formcss"
            action="<%=request.getContextPath()%>/member/MemberLogin">
            <button
              type="submit"
              class="btn btn-primary logoutbutton"
              id="logout"
            >
              登出
            </button>
            <input type="hidden" name="action" value="logout" />
          </form>
        </li>
      </ul>
      <ul class="nav navbar-nav">
        <!-- Messages: style can be found in dropdown.less-->

        <li class="dropdown messages-menu">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <i class="fa-solid fa-id-badge fa-xl"></i>
          </a>
          <ul class="dropdown-menu">
            <li>
              <!-- inner menu: contains the actual data -->
              <ul class="menu">
                <li>
                  <a href="<%=request.getContextPath()%>/memberCenter/pages/accountBasicInfo.html?memId=${memId}">會員中心</a>
                </li>
                <li>
                  <a href="http://localhost:8080/oladesign/memberCenter/pages/orderList.html">我的訂單</a>
                </li>
                <li>
                  <a href="http://localhost:8080/oladesign/memberCenter/pages/pointManagement.html">我的紅利</a>
                </li>
                <li><a href="#">聯絡客服</a></li>
              </ul>
            </li>
          </ul>
        </li>
      </ul>
    </div>
  </nav>
</header>

<!-- 		jQuery CDN -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
	$(window).on("load", function() {
		let user = "${memName}";
		console.log("user=" + user);
		if (user.trim().length != 0) { //登入狀態
			console.log("已登入");
			$("li.logout *").addClass("none");
		} else { //未登入
			console.log("未登入");
			$("li.login *").addClass("none");
		}

		let isCom = "${isCom}";
		console.log("isCom=" + isCom);
		if (isCom == true) {
			$("button.mystore").removeClass("none");
			$("button.regist_com").addClass("none");
		} else {
			$("button.regist_com").removeClass("none");
			$("button.mystore").addClass("none");
		}
	});
</script>