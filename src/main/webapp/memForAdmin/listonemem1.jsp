<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tibame.tga104.g2.oladesign.member.service.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.member.bean.*"%>
<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>管理員後台頁面</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/fontawesome-all.min.css">
    <link href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css" rel="stylesheet">
           
    <style>
    	.back-end-li:hover>.back-end-li-child{	
			display: block !important;
		}
		
		.nav-item{
			list-style-type: none;
		}
		
		a, a:link, a:visited, a:hover{
			color:#ffffff;
			text-decoration:none;
		}
    </style>
</head>

<body id="page-top">
    <div id="wrapper">
        <nav class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0">
            <div class="container-fluid d-flex p-0">
                <a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="#">
                    <div class="sidebar-brand-icon rotate-n-15"><i class="fas fa-laugh-wink"></i></div>
                    <div class="sidebar-brand-text mx-3"><span>oladesign</span></div>
                </a>
                <hr class="sidebar-divider my-0">
                <ul class="nav navbar-nav text-light" id="accordionSidebar">
                    <li class="nav-item"><a class="nav-link active" href="<%=request.getContextPath()%>/back-end/back-end-index.jsp"><i class="fas fa-tachometer-alt"></i><span>首頁</span></a></li>
                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/index-admin.jsp"><i class="fas fa-table"></i>管理員管理</a></li>
                    
                     <li class="nav-item back-end-li"><a class="nav-link" href="#"><i class="fas fa-table"></i><span>前台會員管理</span></a>
                    	<ul class="back-end-li-child" style="display:none;">
		                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/mem/allMem.jsp"><i class="fas fa-table"></i>一般會員管理</a></li>
		                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/listallcompanymember.jsp"><i class="fas fa-table"></i>廠商會員管理</a></li>
                    	</ul>
                    </li>
                    
                    <li class="nav-item back-end-li"><a class="nav-link" href="#"><i class="fas fa-table"></i>訂單管理</a>
                    	<ul class="back-end-li-child" style="display:none;">
                    		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/order/listAllOrder.jsp"><i class="fas fa-table"></i><span>訂單管理</span></a></li>
<%--                     		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/product/prodInfoQuery.jsp"><i class="fas fa-table"></i><span>商品管理審核</span></a></li> --%>
                    	</ul>
                    </li>
                    <li class="nav-item back-end-li"><a class="nav-link" href="#"><i class="fas fa-table"></i><span>商品分類</span></a>
                    	<ul class="back-end-li-child" style="display:none;">
                    		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/index-product_style.jsp"><i class="fas fa-table"></i><span>商品地區類別</span></a></li>
                    		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/index-product_type.jsp"><i class="fas fa-table"></i><span>商品類別</span></a></li>
                    	</ul>
                    </li>

                    <li class="nav-item back-end-li"><a class="nav-link" href="#"><i class="fas fa-table"></i><span>站內信管理</span></a>
                    	<ul class="back-end-li-child" style="display:none;">
                    		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/index-intermail.jsp"><i class="fas fa-table"></i><span>站內信</span></a></li>
                    		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/index-intermail_qn.jsp"><i class="fas fa-table"></i><span>站內信問題類別</span></a></li>
                    	</ul>
                    </li>
					<li class="nav-item back-end-li"><a class="nav-link" href="#"><i class="fas fa-table"></i><span>廣告管理</span></a>
                    	<ul class="back-end-li-child" style="display:none;">
                    		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/listalladvertisement.jsp"><i class="fas fa-table"></i><span>廣告審核</span></a></li>
<%--                     		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/news/select_page.jsp"><i class="fas fa-table"></i><span>查看最新消息</span></a></li> --%>
<%--                     		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/shopEvent/select_page.jsp"><i class="fas fa-table"></i><span>查看商城活動</span></a></li> --%>
                    	</ul>
                    </li>
                </ul>
<!-- 				<div class="text-center d-none d-md-inline" style="margin: 0 auto;"> -->
<!-- 					<button class="btn rounded-circle border-0" id="sidebarToggle" -->
<!-- 						type="button"></button> -->
<!-- 				</div> -->
			</div>
		</nav>
		<div class="d-flex flex-column" id="content-wrapper">
			<div id="content">
				<nav
					class="navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top">
					<div class="container-fluid">
						<button class="btn btn-link d-md-none rounded-circle mr-3"
							id="sidebarToggleTop" type="button">
							<i class="fas fa-bars"></i>
						</button>
						<form
							class="form-inline d-none d-sm-inline-block mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
							<div class="input-group">
								<div class="input-group-append"></div>
							</div>
						</form>
						<ul class="nav navbar-nav flex-nowrap ml-auto">
							<li class="nav-item dropdown d-sm-none no-arrow"><a
								class="dropdown-toggle nav-link" data-toggle="dropdown"
								aria-expanded="false" href="#"><i class="fas fa-search"></i></a>
								<div
									class="dropdown-menu dropdown-menu-right p-3 animated--grow-in"
									aria-labelledby="searchDropdown">
									<form class="form-inline mr-auto navbar-search w-100">
										<div class="input-group">
											<input class="bg-light form-control border-0 small"
												type="text" placeholder="Search for ...">
											<div class="input-group-append">
												<button class="btn btn-primary py-0" type="button">
													<i class="fas fa-search"></i>
												</button>
											</div>
										</div>
									</form>
								</div></li>
							<li class="nav-item dropdown no-arrow mx-1">
<!-- 								<div class="nav-item dropdown no-arrow"> -->
<!-- 									<a class="dropdown-toggle nav-link" data-toggle="dropdown" -->
<!-- 										aria-expanded="false" -->
<%-- 										href="<%=request.getContextPath() %>/customerservice/NameServlet?backaction=admin&userID=${adminVO.adminId}" --%>
<%-- 										class="d-none d-lg-inline mr-2 text-gray-600 small">${adminVO.adminId}</span><img --%>
<!-- 										onclick="window.open(this.href, '', 'width=800,height=800'); return false;"><span -->
<!-- 										class="badge badge-danger badge-counter"></span><i -->
<!-- 										class="fa fa-comment"></i></a> -->

<!-- 								</div> -->
<!-- 							</li> -->
							<div class="d-none d-sm-block topbar-divider"></div>
							<li class="nav-item dropdown no-arrow">
								<div class="nav-item dropdown no-arrow">
									<a class="dropdown-toggle nav-link" data-toggle="dropdown"
										aria-expanded="false" href="#"><span
										class="d-none d-lg-inline mr-2 text-gray-600 small">${adminVO.adminName}</span><img
										class="border rounded-circle img-profile"
										src="<%=request.getContextPath()%>/image/logo1.png"></a>
									<div
										class="dropdown-menu shadow dropdown-menu-right animated--grow-in">
										<div class="dropdown-divider"></div>
										<a class="dropdown-item"
											href="<%=request.getContextPath()%>/login/logOutServlet?action=admin"><i
											class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Logout</a>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</nav>
 		<!-- 内容區域 -->
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
			</section>
			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-8">
						<!-- form start -->
						<form method="post" action="comforadmin.do" name="form1">
							<!-- left column -->
							<div class="col-md-6">
								<!-- general form elements -->
								<div class="box box-primary">
									<div class="box-header with-border">
										<h3 class="box-title">基本資料</h3>
									</div>
									<!-- /.box-header -->
									<div class="box-body">
										<div class="form-group">
											<label for="com_taxid">會員編號</label>
											<input type="text" readonly="readonly" class="form-control" name="mem_id"
												id="com_taxid"
												value="${memberVO.getMemId()}" />
										</div>
										<div class="form-group">
											<label for="mem_id">會員名稱</label>
												<input type="text" readonly="readonly" class="form-control" name="mem_name"
												id="mem_id"
												value="${memberVO.getMemName()}" />
										</div>
	
										<div class="form-group">
											<label for="com_name">會員帳號</label>
												<input type="text" readonly="readonly" class="form-control" name="mem_account"
												id="com_name"
												value="${memberVO.getAccount()}"/>
										</div>
	
										<div class="form-group">
											<label for="com_address">會員手機號碼</label>
											<input type="text" readonly="readonly" class="form-control" name="mem_phone"
												id="com_address"
												value="${memberVO.getMemPhone()}"/>
										</div>
	
										<div class="form-group">
											<label for="com_phone">會員地址</label>
											<input type="text" readonly="readonly" class="form-control" name="mem_address"
												id="com_phone"
												value="${memberVO.getMemAddress()}"/>
										</div>
										<div class="form-group">
											<label for="com_owner">會員註冊日期</label>
											<input type="text" readonly="readonly" class="form-control" name="mem_regdate"
												id="com_owner"
												value="${memberVO.getMemRegdate()}"/>
										</div>
										<div class="form-group">
											<label for="owner_phone">性別</label>
											<input type="text" readonly="readonly" class="form-control" name="mem_sex"
												id="owner_phone"
												value="${memberVO.getSex()}"/>
										</div>
	
										<div class="form-group">
											<label for="com_bankaccount">紅利點數</label>
											<input type="text" readonly="readonly" class="form-control" name="mem_point"
												id="com_bankaccount"
												value="${memberVO.getPoint()}"/>
										</div>
										
										<div class="form-group">
											<label for="com_bankaccount">會員照片</label>
											<input type="text" readonly="readonly" class="form-control" name="mem_photo"
												id="com_bankaccount"
												value="${memberVO.getMemPhoto()}"/>
										</div>
	
									</div>
									<!--/.col (left) -->
								</div>
								<!-- /.row -->
							</div>
						</form>
					</div>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<!-- 内容区域 /-->
        </div>
        <footer class="bg-white sticky-footer">
            <div class="container my-auto">
                <div class="text-center my-auto copyright"><span>oladesign</span></div>
            </div>
        </footer>
<!--     </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a></div> -->
	<script src="<%=request.getContextPath()%>/back-end/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/js/theme.js"></script>
</body>

</html>