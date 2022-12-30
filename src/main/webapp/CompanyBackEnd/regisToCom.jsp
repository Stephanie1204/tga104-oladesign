<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.tibame.tga104.g2.oladesign.member.bean.*"%>
<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
System.out.println("RegistmemberVO=" + memberVO);
String city = (String) request.getAttribute("city");
String town = (String) request.getAttribute("town");
String agreement = (String) request.getAttribute("agreement");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>OLA Design | 賣家註冊</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet" />

<!-- Theme style -->
<link rel="stylesheet" href="../css/adminlte.min.css" />
<link rel="stylesheet" href="../css/memReg.css" />
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
							<h1 class="mem-reg">賣家註冊</h1>
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
<!-- 							<form class="reg"> -->
								<div class="card-body">
									<div class="form-group col-md-6">
										<label for="memName">會員姓名</label> <span class="error" id="err_memName"></span>
										<input type="email" class="form-control" id="memName"
											name="memName" value="${memName}" readonly />
									</div>

									<div class="form-group col-md-6">
										<label for="com_taxid">公司統編<span id="star">* </span><span
											class="error" id="err_com_taxid"></span>
										</label> <input type="text" class="form-control" id="com_taxid"
											name="com_taxid" placeholder="請填合法8碼公司統編" />
									</div>

									<div class="form-group col-md-6">
										<label for="com_name">公司名稱<span id="star">* </span><span
											class="error" id="err_com_name"></span></label> <input type="text" class="form-control"
											id="com_name" name="com_name" placeholder="請填真實公司名稱" />
									</div>
									<div class="form-group col-md-6">
										<label for="com_phone">公司電話<span id="star">* </span><span
											class="error" id="err_com_phone"></span></label> <input type="tel" class="form-control"
											id="com_phone" name="com_phone" placeholder="請填市話" />
									</div>

									<div class="form-group col-md-6">
										<label for="com_owner">公司負責人<span id="star">* </span><span
											class="error" id="err_com_owner"></span></label> <input type="text" class="form-control"
											id="com_owner" name="com_owner" placeholder="請填公司負責人真實姓名" />
									</div>

									<div class="form-group col-md-6">
										<label for="owner_phone">負責人手機<span id="star">*
										</span><span class="error" id="err_owner_phone"></span></label><br> <input type="tel"
											class="form-control" id="owner_phone" name="owner_phone"
											placeholder="請填公司負責人手機號碼" />
									</div>

									<div class="form-group col-md-12">
										<label for="com_address">公司地址<span id="star">*
										</span><span class="error" id="err_com_address"></span></label>
										<div class="address">
											<div id="twzipcode" class="col-md-5"></div>
											<input type="text" class="form-control address col-md-7"
												id="com_road" name="com_road" />
										</div>
									</div>

									<%-- 									<jsp:useBean id="memSvc" scope="page" class="com.tibame.tga104.g2.oladesign.member.service.MemberService" /> --%>

									
								</div>
								<!-- /.card-body -->

								<div class="card-footer col-md-12">
									<button type=button class="reg btn btn-primary" id="com_regist">
										註冊賣家</button>
								</div>
<!-- 							</form> -->
						</div>
						<!-- /.card -->
				</div>
			</section>
		
		</div>
		<!-- ./wrapper -->
<%--
     String path = request.getContextPath();
     String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
--%>
		<!-- 連動式地址下拉選單來源  https://github.com/essoduke/jQuery-TWzipcode -->
<script src="../plugins/jQuery-TWzipcode-master/jquery.twzipcode.js"></script>
<%-- <script src="<%=basePath%>/static/js/registToCom.js"></script> --%>

<%@ include file="../include/favorite.jsp"%>
<script>
	$(function(){	
		//連動式地址下拉選單
		$("#twzipcode").twzipcode({
			countySel: "${(city == "" ? null : city)}", // 城市預設值, 字串一定要用繁體的 "臺", 否則抓不到資料
			districtSel: "${(town == "" ? null : town)}", // 地區預設值
			zipcodeIntoDistrict: false, // 郵遞區號自動顯示在地區
			css: ["city form-control", "town form-control", "zipcode form-control"], // 自訂 "城市"、"地區" class 名稱
			countyName: "city", // 自訂城市 select 標籤的 name 值
			districtName: "town" // 自訂地區 select 標籤的 name 值
		});	
		
		let isRegCom = null;
		
		$("#com_regist").on("click", function(e){
			
//	 		接收參數
			let memId = "${memId}";
			console.log("memId=" + memId);
			let memName = $("#memName").val();
			let com_taxid = $("#com_taxid").val();
			let com_name = $("#com_name").val();
			let com_phone = $("#com_phone").val();
			let com_owner = $("#com_owner").val();
			let owner_phone = $("#owner_phone").val();
			let com_zipcode = $("input.zipcode").val();
			let com_city = $("select.city").val();
			let com_town = $("select.town").val();
			let com_road =$("#com_road").val();
			let com_address = com_zipcode + com_city + com_town + com_road;
			
			var comRegistData = {
					"memId": memId,
					"memName": memName,
					"comTaxId": com_taxid,
					"comName": com_name,
					"comPhone": com_phone,
					"comOwner": com_owner,
					"ownerPhone": owner_phone,
					"comAddress": com_address,
					"comZipcode": com_zipcode,
					"comCity": com_city,
					"comTown": com_town,
					"comRoad": com_road
				};
			console.log(comRegistData);
			
			$.ajax({
				url: "<%=request.getContextPath()%>/CompanyBackEnd/ComMemRegist",
				type: "POST",
				data: JSON.stringify(comRegistData),
				dataType: "json",
				contentType: "application/json",
				processData: false,  // 不用額外處理資料。例：如果是 GET，預設會將 data 物件資料字串化，放到網址
				success: function(data){  // request 成功取得回應後執行
					console.log(data); //回傳JSON資料
					console.log("data.success = " + data.success);
					if(!data.success){
						console.log("顯示錯誤訊息");
						var dataObj = JSON.parse(data.error); //JSON轉變為物件
						
// 						針對input標籤:如果dataObj沒有此物件 -> 沒有錯誤訊息 -> 顯示原本的值；填寫錯誤就清空value						
						$("#com_taxid").val((dataObj.comTaxid === undefined ? com_taxid : ""));
						$("#com_name").val((dataObj.comName === undefined ? com_name : ""));
						$("#com_phone").val((dataObj.comPhone === undefined ? com_phone : ""));
						$("#com_owner").val((dataObj.comOwner === undefined ? com_owner : ""));
						$("#owner_phone").val((dataObj.ownerPhone === undefined ? owner_phone : ""));
						$("#com_address").val((dataObj.comAddress === undefined ? com_address : ""));

// 						針對錯誤訊息span標籤:如果如果dataObj有此物件 ->顯示錯誤訊息；改正後刪除錯誤訊息
						$("#err_memName").text((dataObj.memName === undefined ? "" : dataObj.memName));
						$("#err_com_taxid").text((dataObj.comTaxid === undefined ? "" : dataObj.comTaxid));
						$("#err_com_name").text((dataObj.comName === undefined ? "" : dataObj.comName));
						$("#err_com_phone").text((dataObj.comPhone === undefined ? "" : dataObj.comPhone));
						$("#err_com_owner").text((dataObj.comOwner === undefined ? "" : dataObj.comOwner));
						$("#err_owner_phone").text((dataObj.ownerPhone === undefined ? "" : dataObj.ownerPhone));
						$("#err_com_address").text((dataObj.comAddress === undefined ? "" : dataObj.comAddress));
						if(com_city == null){
							$("#err_com_address").text("請選擇縣市與行政區");
						}
						return false;
					}else{
						console.log("註冊成功");
						
// 						成功提交，刪除所有錯誤訊息
						$("#err_memName").text("");
						$("#err_com_taxid").text("");
						$("#err_com_name").text("");
						$("#err_com_phone").text("");
						$("#err_com_owner").text("");
						$("#err_owner_phone").text("");
						$("#err_com_address").text("");
						alert("已完成賣家註冊，請耐心等待審核通過");
						console.log("dataregCom=" + data.regCom);
						isRegCom = data.regCom;
						if(isRegCom == true){
							console.log("等待中");
							$(".regist_com").prop("disabled", true);
							$("a.beCom").text("賣家註冊審核中");
							localStorage.setItem("class", "wait");
						}
						window.location.href = "<%=request.getContextPath()%>/homePage/index.jsp";
						
					}				
				},
				error: function(xhr){
					$("#err_memName").text("請先登入或註冊為會員");
					alert("請先登入或註冊為會員");
				},
				complete: function(xhr){
					console.log(xhr);
					
				}
			});
		});
	});
	
	
	$(window).on("load", function(){
		let registCom = "${memberVO.isRegCom}";
		console.log("registCom=" + registCom);
		if(registCom == true){
			$("button.regist_com").attr("disabled", true);
			$("a.beCom").text("賣家註冊審核中");	
		}
	});
	
</script>
</body>
</html>
