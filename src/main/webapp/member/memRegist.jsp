<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tibame.tga104.g2.oladesign.member.bean.*"%>	
<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>	
	
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>OLA Design | 會員註冊</title>

<!-- Font Awesome -->
<script src="https://kit.fontawesome.com/51435664f6.js" crossorigin="anonymous"></script>

<!-- header css -->
<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="../css/header.css" type="text/css" />
  
<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback" />

<!-- Theme style -->
<link rel="stylesheet" href="./css/adminlte.min.css" />
<link rel="stylesheet" href="./css/memReg.css" />
</head>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<!--     header -->
		
		<%@ include file="../include/header.jsp"%>
		

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="mem-reg">會員註冊</h1>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<!-- general form elements -->
						<div class="card card-primary">

							<!-- form start -->
							<form class="reg" method="post" action="<%=request.getContextPath()%>/front-end/regist-login/registration/MemberRegist">
								<div class="card-body">
									<div class="form-group col-md-6">
										<label for="account">帳號(Email)<span id="star">* </span><span class="error">${errorMsgs.account}</span>
											</label> 
											<input type="email" class="form-control"
											id="account" name="account" value="${(memberVO == null)? '' : memberVO.account}" placeholder="請填寫Email" />
									</div>

									<div class="form-group col-md-6">
										<label for="memName">姓名<span id="star">* </span><span class="error">${errorMsgs.memName}</span>
										</label> <input	type="text" class="form-control" id="memName" name="memName"
											placeholder="請填真實中文姓名" value="${(memberVO == null)? '' : memberVO.memName}"/>
									</div>

									<div class="form-group col-md-6">
										<label for="password">密碼<span id="star">* </span><span class="error">${errorMsgs.password}</span></label>
										<input type="password" class="form-control"
											id="password" name="password" placeholder="請設定6~15位英文數字混合密碼" value="${(memberVO == null)? '' : memberVO.password}"/>
									</div>
									<div class="form-group col-md-6">
										<label for="cpassword">確認密碼<span id="star">* </span><span class="error">${errorMsgs.cpassword}</span></label>
										<input type="password" class="form-control"
											id="cpassword" name="cpassword" placeholder="請再輸入一次密碼" />											
									</div>

									<div class="form-group col-md-6">
										<label for="memPhone">手機<span id="star">* </span><span class="error">${errorMsgs.memPhone}</span></label> 
										<input type="tel" class="form-control" id="memPhone" name="memPhone"
											placeholder="請填入手機號碼" value="${(memberVO == null)? '' : memberVO.memPhone}"/>											
									</div>

									<div class="form-group col-md-6">
										<label for="gender">性別<span id="star">* </span><span class="error">${errorMsgs.sex}</span></label><br>
										<div class="gender form-check col-md-3">
											<input type="radio" class="form-check-input gender" id="M"
												name="sex" value="M" ${(memberVO.sex == "M")?'checked' : '' }/> 
												<label for="M" class="form-check-label">男</label>
										</div>
										<div class="gender form-check col-md-5">
											<input type="radio" class="form-check-input gender" id="F"
												name="sex" value="F" ${(memberVO.sex == "F")?'checked' : '' }/> <label for="F"
												class="form-check-label">女</label>
										</div>										
									</div>

									<div class="form-group col-md-12">
										<label for="memAddress">地址<span id="star">* </span><span class="error">${errorMsgs.memAddress}</span></label> 
										<input type="text" class="form-control" id="memAddress" name="memAddress" value="${(memberVO == null)? '' : memberVO.memAddress}"/>
									</div>

									<jsp:useBean id="memSvc" scope="page" class="com.tibame.tga104.g2.oladesign.member.service.MemberService" />

									<div class="final-check form-check ">
										<input class="final-check" type="checkbox" value="agreement" name="agreement"
											id="flexCheckDefault"> <label
											class="form-check-label" for="flexCheckDefault">
											已確認以上資料無誤，並同意接受 <a href="#">會員條款</a>及<a href="#">隱私條款</a>
										</label>
										<div><span class="error">${errorMsgs.agreement}</span></div>
										
									</div>
								</div>
								<!-- /.card-body -->

								<div class="card-footer col-md-12">
									<input type="hidden" name="action" value="regist"> 
									<button type="submit" class="reg btn btn-primary">
										立即註冊</button>
								</div>
							</form>
						</div>
						<!-- /.card -->
				</div>
			</section>


			<footer class="main-footer col-md-12">
				<div class="float-right d-none d-sm-block">
					<b>Version</b> 3.2.0
				</div>
				<strong>Copyright &copy; 2014-2021 <a
					href="https://adminlte.io">AdminLTE.io</a>.
				</strong> All rights reserved.
			</footer>


		</div>
		<!-- ./wrapper -->
		
</body>
</html>