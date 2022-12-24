<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>        
<link rel="stylesheet"
	href="/oladesign/plugins/adminLTE/css/skins/oladesign-skin.css">
<!-- <link rel="stylesheet" -->
<!-- 	href="../plugins/adminLTE/css/skins/oladesign-skin.css"> -->
    <!-- 页面头部 -->
		<header class="main-header">
			<!-- Logo -->
			<a href="<%=request.getContextPath()%>/homePage/index.jsp" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini"><b>賣家後台首頁</b></span> <!-- logo for regular state and mobile devices -->
				<span class="logo-lg"><img src="/oladesign/img/OLA_Logo.svg" width=70%></span>
			</a>
			<!-- Header Navbar: style can be found in header.less -->
			<nav class="navbar navbar-static-top">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
					role="button"> <span class="sr-only">Toggle navigation</span>
				</a>

				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<!-- Messages: style can be found in dropdown.less-->
						<li class="dropdown messages-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> <i
								class="fa fa-envelope-o"></i> <span class="label label-success">4</span>
						</a>
							<ul class="dropdown-menu">
	
							</ul></li>
						<!-- Notifications: style can be found in dropdown.less -->
						<li class="dropdown notifications-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> <i
								class="fa fa-bell-o"></i> <span class="label label-warning">10</span>
						</a>

						<!-- Tasks: style can be found in dropdown.less -->
						<li class="dropdown tasks-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> <i
								class="fa fa-flag-o"></i> <span class="label label-danger">9</span>
						</a>

					</ul>
				</div>
			</nav>
		</header>
		<!-- 页面头部 /-->