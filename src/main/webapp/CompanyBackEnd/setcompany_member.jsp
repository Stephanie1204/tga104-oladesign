<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tibame.tga104.g2.oladesign.CompanyMember.vo.*" %>
<%
Company_MemVO company_memVO = (Company_MemVO) request.getAttribute("company_memVO");
%>
<!DOCTYPE html>
<html>
<head>
<!-- 頁面Meta -->
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>OLA Design 賣家中心</title>
<meta name="description" content="AdminLTE2定制版">
<meta name="keywords" content="AdminLTE2定制版">

<meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
<link rel="stylesheet" href="../plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="../plugins/ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="../plugins/iCheck/square/blue.css">
<link rel="stylesheet" href="../plugins/morris/morris.css">
<link rel="stylesheet" href="../plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<link rel="stylesheet" href="../plugins/datepicker/datepicker3.css">
<link rel="stylesheet" href="../plugins/daterangepicker/daterangepicker.css">
<link rel="stylesheet" href="../plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<link rel="stylesheet" href="../plugins/datatables/dataTables.bootstrap.css">
<link rel="stylesheet" href="../plugins/treeTable/jquery.treetable.css">
<link rel="stylesheet" href="../plugins/treeTable/jquery.treetable.theme.default.css">
<link rel="stylesheet" href="../plugins/select2/select2.css">
<link rel="stylesheet" href="../plugins/colorpicker/bootstrap-colorpicker.min.css">
<link rel="stylesheet" href="../plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
<link rel="stylesheet" href="../plugins/adminLTE/css/AdminLTE.css">
<link rel="stylesheet" href="../plugins/adminLTE/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../plugins/ionslider/ion.rangeSlider.css">
<link rel="stylesheet" href="../plugins/ionslider/ion.rangeSlider.skinNice.css">
<link rel="stylesheet" href="../plugins/bootstrap-slider/slider.css">
<link rel="stylesheet" href="../plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">

</head>

<body class="hold-transition skin-purple sidebar-mini">
	<!-- Ola Design Header -->
	<%@ include file="header.jsp"%>
	<div class="wrapper">
		<!-- Ola Design Menu -->
		<%@ include file="company-menu.jsp"%>
		<!-- 内容區域 -->
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">

			</section>
			<c:if test="${not empty errorMsgs}">
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<!-- Main content -->
			<section class="content">
				<div class="row">
					<!-- form start -->
					<form method="post" action="company_member.do" name="form1" enctype="multipart/form-data">
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
										<label for="com_taxid">公司統編<font color=red><b>*</b></font></label> <input type="text" class="form-control" name="com_taxid" id="com_taxid"
											value="${(company_memVO == null) ? '' : company_memVO.getComTaxId()}" />
									</div>
									<div class="form-group">
										<label for="mem_id">會員編號<font color=red><b>*</b></font></label> <input type="text" class="form-control" name="mem_id" id="mem_id" />
									</div>

									<div class="form-group">
										<label for="com_name">公司名稱<font color=red><b>*</b></font></label> <input type="text" class="form-control" name="com_name" id="com_name"
											value="${(company_memVO == null) ? '' : company_memVO.getComName()}" />
									</div>

									<div class="form-group">
										<label for="com_address">公司地址<font color=red><b>*</b></font></label> <select id="County" name="County"
											onchange="SelZero(this.options[this.options.selectedIndex].value);">
											<option value="0">請選擇縣市...</option>
											<option value="1">臺北市</option>
											<option value="2">基隆市</option>
											<option value="3">臺北縣</option>
											<option value="4">宜蘭縣</option>
											<option value="5">新竹縣市</option>
											<option value="6">桃園縣</option>
											<option value="7">苗栗縣</option>
											<option value="8">臺中市</option>
											<option value="9">臺中縣</option>
											<option value="10">彰化縣</option>
											<option value="11">南投縣</option>
											<option value="12">嘉義縣市</option>
											<option value="13">雲林縣</option>
											<option value="14">臺南市</option>
											<option value="15">臺南縣</option>
											<option value="16">高雄市</option>
											<option value="17">高雄縣</option>
											<option value="18">澎湖縣</option>
											<option value="19">屏東縣</option>
											<option value="20">臺東縣</option>
											<option value="21">花蓮縣</option>
											<option value="22">金門縣</option>
											<option value="23">連江縣</option>
											<option value="24">南海諸島</option>
											<option value="25">釣魚台列嶼</option>
										</select> <select id="Zero" name="Zero" onChange="document.form1.Zip.value=this.options[this.options.selectedIndex].value;">
											<option value="">請選擇縣市..</option>
										</select> 郵遞區號<input name="Zip" type="text" value="" /> <input type="text" class="form-control" name="com_address" id="com_address"
											value="${(company_memVO == null) ? '' : company_memVO.getComAddress()}" />
									</div>

									<div class="form-group">
										<label for="com_phone">公司電話<font color=red><b>*</b></font></label> <input type="text" class="form-control" name="com_phone" id="com_phone"
											value="${(company_memVO == null) ? '' : company_memVO.getComPhone()}" />
									</div>
									<div class="form-group">
										<label for="com_owner">負責人<font color=red><b>*</b></font></label> <input type="text" class="form-control" name="com_owner" id="com_owner"
											value="${(company_memVO == null) ? '' : company_memVO.getComOwner()}" />
									</div>
									<div class="form-group">
										<label for="owner_phone">負責人手機號碼<font color=red><b>*</b></font></label> <input type="text" class="form-control" name="owner_phone"
											id="owner_phone" value="${(company_memVO == null) ? '' : company_memVO.getOwnerPhone()}" />
									</div>

									<div class="form-group">
										<label for="com_bankaccount">銀行帳戶</label> <input type="text" class="form-control" name="com_bankaccount" id="com_bankaccount"
											value="${(company_memVO == null) ? '' : company_memVO.getComBankaccount()}" />
									</div>

									<div class="box-footer">
										<input type="hidden" name="action" value="insert" id="action"> <input type="submit" value="儲存" class="btn btn-primary"
											id="update_save" /> <input type="hidden" name="com_regdate" value="${company_memVO.getComRegdate().toString()}" /> <input type="button"
											value="修改資料" class="btn btn-primary" id="update"> <input type="button" value="取消修改" class="btn btn-primary" id="cancle"
											style="display: none">

									</div>

								</div>
							</div>
						</div>
					</form>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<!-- 内容区域 /-->
		<!-- Ola Design Footer -->
		<%@ include file="footer.jsp"%>
	</div>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
	<script src="../plugins/jQueryUI/jquery-ui.min.js"></script>
	<script>
        $.widget.bridge('uibutton', $.ui.button);
    </script>
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
	<script src="../plugins/adminLTE/js/oladesign-address.js"></script>
	<script>
        $(document).ready(
                function () {
                    function SelZero(strCode) {
                        document.forms["FormCode"].Zero.options.length = 0; // 動態控制表單物件方法一
                        document.FormCode.Zero.options.length = 0; // 動態控制表單物件方法二
                        document.getElementById("Zero").options.length = 0; // 動態控制表單物件方法三

                        if (strCode == 0) // 第一個條件如果 縣市 選擇第一個的話則新增一個 Option
                        {
                            document.getElementById("Zero").options[0] = new Option("請選擇縣市..", "");
                        } else {
                            document.getElementById("Zero").options[0] = new Option("請選擇區域..", "0"); // 設定第一個選項

                            var Zero = new Array( // 1維陣列
                            // 臺北市
                            new Array( // 2維陣列
                            new Array("中正區", "大同區", "中山區", "松山區", "大安區", "萬華區", "信義區", // 3維陣列
                            "士林區", "北投區", "內湖區", "南港區", "文山區"), new Array("100", "103", "104", "105", "106", "108", "110", "111", "112", "114", "115", "116")),
                            // 基隆市
                            new Array(new Array("仁愛區", "信義區", "中正區", "中山區", "安樂區", "暖暖區", "七堵區"), new Array("200", "201", "202", "203", "204", "205", "206")),
                            // 臺北縣
                            new Array(new Array("萬里鄉", "金山鄉", "板橋市", "汐止市", "深坑鄉", "石碇鄉", "瑞芳鎮", "平溪鄉", "雙溪鄉", "貢寮鄉", "新店市", "坪林鄉", "烏來鄉", "永和市", "中和市", "土城市", "三峽鎮", "樹林市",
                                    "鶯歌鎮", "三重市", "新莊市", "泰山鄉", "林口鄉", "蘆洲市", "五股鄉", "八里鄉", "淡水鎮", "三芝鄉", "石門鄉"), new Array("207", "208", "220", "221", "222", "223", "224", "226",
                                    "227", "228", "231", "232", "233", "234", "235", "236", "237", "238", "239", "241", "242", "243", "244", "247", "248", "249", "251", "252",
                                    "253")),
                            // 宜蘭縣
                            new Array(new Array("宜蘭市", "頭城鎮", "礁溪鄉", "壯圍鄉", "員山鄉", "羅東鎮", "三星鄉", "大同鄉", "五結鄉", "冬山鄉", "蘇澳鎮", "南澳鄉"), new Array("260", "261", "262", "263", "264",
                                    "265", "266", "267", "268", "269", "270", "272")),
                            // 新竹縣市
                            new Array(new Array("新竹市", "竹北市", "湖口鄉", "新豐鄉", "新埔鎮", "關西鎮", "芎林鄉", "寶山鄉", "竹東鎮", "五峰鄉", "橫山鄉", "尖石鄉", "北埔鄉", "峨眉鄉"), new Array("300", "302", "303",
                                    "304", "305", "306", "307", "308", "310", "311", "312", "313", "314", "315")),
                            // 桃園縣
                            new Array(new Array("中壢市", "平鎮市", "龍潭鄉", "楊梅鎮", "新屋鄉", "觀音鄉", "桃園市", "龜山鄉", "龜山鄉", "八德市", "大溪鎮", "復興鄉", "大園鄉", "蘆竹鄉"), new Array("320", "324", "325",
                                    "326", "327", "328", "330", "330", "333", "334", "335", "336", "337", "338")),
                            // 苗栗縣
                            new Array(new Array("竹南鎮", "頭份鎮", "三灣鄉", "南庄鄉", "獅潭鄉", "後龍鎮", "通霄鎮", "苑裡鎮", "苗栗市", "造橋鄉", "頭屋鄉", "公館鄉", "大湖鄉", "泰安鄉", "銅鑼鄉", "三義鄉", "西湖鄉", "卓蘭鎮"),
                                    new Array("350", "351", "352", "353", "354", "356", "357", "358", "360", "361", "362", "363", "364", "365", "366", "367", "368", "369")),
                            // 臺中市
                            new Array(new Array("中　區", "東　區", "南　區", "西　區", "北　區", "北屯區", "西屯區", "南屯區"), new Array("400", "401", "402", "403", "404", "406", "407", "408")),
                            // 臺中縣
                            new Array(new Array("太平市", "大里市", "霧峰鄉", "烏日鄉", "豐原市", "后里鄉", "石岡鄉", "東勢鎮", "和平鄉", "新社鄉", "潭子鄉", "大雅鄉", "神岡鄉", "大肚鄉", "沙鹿鎮", "龍井鄉", "梧棲鎮", "清水鎮",
                                    "大甲鎮", "外埔鄉", "大安鄉"), new Array("411", "412", "413", "414", "420", "421", "422", "423", "424", "426", "427", "428", "429", "432", "433", "434",
                                    "435", "436", "437", "438", "439")),
                            // 彰化縣
                            new Array(new Array("彰化市", "芬園鄉", "花壇鄉", "秀水鄉", "鹿港鎮", "福興鄉", "線西鄉", "和美鎮", "伸港鄉", "員林鎮", "社頭鄉", "永靖鄉", "埔心鄉", "溪湖鎮", "大村鄉", "埔鹽鄉", "田中鎮", "北斗鎮",
                                    "田尾鄉", "埤頭鄉", "溪州鄉", "竹塘鄉", "二林鎮", "大城鄉", "芳苑鄉", "二水鄉"), new Array("500", "502", "503", "504", "505", "506", "507", "508", "509", "510", "511",
                                    "512", "513", "514", "515", "516", "520", "521", "522", "523", "524", "525", "526", "527", "528", "530")),
                            // 南投縣
                            new Array(new Array("南投市", "中寮鄉", "草屯鎮", "國姓鄉", "埔里鎮", "仁愛鄉", "名間鄉", "集集鎮", "水里鄉", "魚池鄉", "信義鄉", "竹山鎮", "鹿谷鄉"), new Array("540", "541", "542", "544",
                                    "545", "546", "551", "552", "553", "555", "556", "557", "558")),
                                    // 嘉義縣市
                                    new Array(new Array("嘉義市", "番路鄉", "梅山鄉", "竹崎鄉", "阿里山", "中埔鄉", "大埔鄉", "水上鄉", "鹿草鄉", "太保市", "朴子市", "東石鄉", "六腳鄉", "新港鄉", "民雄鄉", "大林鎮", "溪口鄉",
                                            "義竹鄉", "布袋鎮"), new Array("600", "602", "603", "604", "605", "606", "607", "608", "611", "612", "613", "614", "615", "616", "621",
                                            "622", "623", "624", "625")),
                                    // 雲林縣
                                    new Array(new Array("斗南鎮", "大埤鄉", "虎尾鎮", "土庫鎮", "褒忠鄉", "東勢鄉", "台西鄉", "崙背鄉", "麥寮鄉", "斗六市", "林內鄉", "古坑鄉", "莿桐鄉", "西螺鎮", "二崙鄉", "北港鎮", "水林鄉",
                                            "口湖鄉", "四湖鄉", "元長鄉"), new Array("630", "631", "632", "633", "634", "635", "636", "637", "638", "640", "643", "646", "647", "648",
                                            "649", "651", "652", "653", "654", "655")),
                                    // 臺南市
                                    new Array(new Array("中西區", "東　區", "南　區", "北　區", "安平區", "安南區"), new Array("700", "701", "702", "704", "708", "709")),
                                    // 臺南縣
                                    new Array(new Array("永康市", "歸仁鄉", "新化鎮", "左鎮鄉", "玉井鄉", "楠西鄉", "南化鄉", "仁德鄉", "關廟鄉", "龍崎鄉", "官田鄉", "麻豆鎮", "佳里鎮", "西港鄉", "七股鄉", "將軍鄉", "學甲鎮",
                                            "北門鄉", "新營市", "後壁鄉", "白河鎮", "東山鄉", "六甲鄉", "下營鄉", "柳營鄉", "鹽水鎮", "善化鎮", "新市鄉", "大內鄉", "山上鄉", "新市鄉", "安定鄉"), new Array("710", "711",
                                            "712", "713", "714", "715", "716", "717", "718", "719", "720", "721", "722", "723", "724", "725", "726", "727", "730", "731", "732",
                                            "733", "734", "735", "736", "737", "741", "741", "742", "743", "744", "745")),
                                    // 高雄市
                                    new Array(new Array("新興區", "前金區", "苓雅區", "鹽埕區", "鼓山區", "旗津區", "前鎮區", "三民區", "楠梓區", "小港區", "左營區"), new Array("800", "801", "802", "803", "804",
                                            "805", "806", "807", "811", "812", "813")),
                                    // 高雄縣
                                    new Array(new Array("仁武鄉", "大社鄉", "岡山鎮", "路竹鄉", "阿蓮鄉", "田寮鄉", "燕巢鄉", "橋頭鄉", "梓官鄉", "彌陀鄉", "永安鄉", "湖內鄉", "鳳山市", "大寮鄉", "林園鄉", "鳥松鄉", "大樹鄉",
                                            "旗山鎮", "美濃鎮", "六龜鄉", "內門鄉", "杉林鄉", "甲仙鄉", "桃源鄉", "三民鄉", "茂林鄉", "茄萣鄉"), new Array("814", "815", "820", "821", "822", "823", "824",
                                            "825", "826", "827", "828", "829", "830", "831", "832", "833", "840", "842", "843", "844", "845", "846", "847", "848", "849", "851",
                                            "852")),
                                    // 澎湖縣
                                    new Array(new Array("馬公市", "西嶼鄉", "望安鄉", "七美鄉", "白沙鄉", "湖西鄉"), new Array("880", "881", "882", "883", "884", "885")),
                                    // 屏東縣
                                    new Array(new Array("屏東市", "三地門", "霧台鄉", "瑪家鄉", "九如鄉", "里港鄉", "高樹鄉", "鹽埔鄉", "長治鄉", "麟洛鄉", "竹田鄉", "內埔鄉", "萬丹鄉", "潮州鎮", "泰武鄉", "來義鄉", "萬巒鄉",
                                            "崁頂鄉", "新埤鄉", "南州鄉", "林邊鄉", "東港鎮", "琉球鄉", "佳冬鄉", "新園鄉", "枋寮鄉", "枋山鄉", "春日鄉", "獅子鄉", "車城鄉", "牡丹鄉", "恆春鎮", "滿州鄉"), new Array("900",
                                            "901", "902", "903", "904", "905", "906", "907", "908", "909", "911", "912", "913", "920", "921", "922", "923", "924", "925", "926",
                                            "927", "928", "929", "931", "932", "940", "941", "942", "943", "944", "945", "946", "947")),
                                    // 臺東縣
                                    new Array(new Array("台東市", "綠島鄉", "蘭嶼鄉", "延平鄉", "卑南鄉", "鹿野鄉", "關山鎮", "海端鄉", "池上鄉", "東河鄉", "成功鎮", "長濱鄉", "太麻里", "金峰鄉", "大武鄉", "達仁鄉"), new Array(
                                            "950", "951", "952", "953", "954", "955", "956", "957", "958", "959", "961", "962", "963", "964", "965", "966")),
                                    // 花蓮縣
                                    new Array(new Array("花蓮市", "新城鄉", "秀林鄉", "吉安鄉", "壽豐鄉", "鳳林鎮", "光復鄉", "豐濱鄉", "瑞穗鄉", "萬榮鄉", "玉里鎮", "卓溪鄉", "富里鄉"), new Array("970", "971", "972",
                                            "973", "974", "975", "976", "977", "978", "979", "981", "982", "983")),
                                    // 金門縣
                                    new Array(new Array("金沙鎮", "金湖鎮", "金寧鄉", "金城鎮", "烈嶼鄉", "烏坵鄉"), new Array("890", "891", "892", "893", "894", "896")),
                                    // 連江縣
                                    new Array(new Array("南竿鄉", "北竿鄉", "莒光鄉", "東引鄉"), new Array("209", "210", "211", "212")),
                                    // 南海諸島
                                    new Array(new Array("東　沙", "南　沙"), new Array("817", "819")),
                                    // 釣魚台列嶼
                                    new Array(new Array("釣魚台列嶼"), new Array("290")));

                            // 迴圈開始新增出Option
                            // Zero[strCode - 1][0].length 取得第3維資料中的長度
                            for (i = 0; i <= Zero[strCode - 1][0].length - 1; i++) {
                                // 產生選項內容
                                document.getElementById("Zero").options[i + 1] = new Option(Zero[strCode - 1][1][i] + " | " + Zero[strCode - 1][0][i], Zero[strCode - 1][1][i]);
                            }

                        }
                        document.getElementById("Zero").options[0].selected = true;

                    }

                    // 选择框
                    $(".select2").select2();

                    // WYSIHTML5编辑器
                    $(".textarea").wysihtml5({
                        locale : 'zh-TW'
                    });
         	       //會員資料先寫死,合在一起之後要改
                    $("#mem_id").val("220017").attr('readonly', true);

                    // ajax call api to get CompantMembetInfo
                    $.ajax({
                        type : 'POST',
                        url : 'http://localhost:8080/oladesign/CompanyBackEnd/company_memberdo?action=doGetCompantMembetInfo&memId=220017',
                        success : function (data, status, xhr) {
                            var dataJson = JSON.parse(data);

                            if (dataJson.isMemberHasCom) {
                                // 開始set 資料
                                $("#com_taxid").val(dataJson.comTaxId).attr('readonly', true);
                                $("#com_name").val(dataJson.comName).attr('readonly', true);
                                $("#com_address").val(dataJson.comAddress).attr('readonly', true);
                                $("#com_phone").val(dataJson.comPhone).attr('readonly', true);
                                $("#com_owner").val(dataJson.comOwner).attr('readonly', true);
                                $("#owner_phone").val(dataJson.ownerPhone).attr('readonly', true);
                                $("#com_bankaccount").val(dataJson.comBankaccount).attr('readonly', true);
                                $("#update_save").attr('disabled', 'disabled');
                            }
                        }
                    });
                });

			        $("#update").on("click", ()=>{
			            doSetForm(false);
			        });
			
					$("#cancle").on("click", ()=>{
			            doSetForm(true);      				
					})
					
			        function doSetForm(trueorfalse){
			            $("#com_taxid").attr('readonly', trueorfalse);
			            $("#com_name").attr('readonly', trueorfalse);
			            $("#com_address").attr('readonly', trueorfalse);
			            $("#com_phone").attr('readonly', trueorfalse);
			            $("#com_owner").attr('readonly', trueorfalse);
			            $("#owner_phone").attr('readonly', trueorfalse);
			            $("#com_bankaccount").attr('readonly', trueorfalse);
			            $("#update_save").attr('disabled', trueorfalse?'disabled' : null);
			            $("#action").val(trueorfalse?"insert":"update_save"); // 要把action改成update，才不會重複inser
			            
			            if(trueorfalse){
							$("#update").show();
							$("#cancle").hide();
			            }else{
			            	$("#update").hide();
			            	$("#cancle").show();
			            }
			        }
        
        
        // 设置激活菜单
        function setSidebarActive(tagUri) {
            var liObj = $("#" + tagUri);
            if (liObj.length > 0) {
                liObj.parent().parent().addClass("active");
                liObj.addClass("active");
            }
        }

        // 激活导航位置
        setSidebarActive("form-general");

        var preview_el = document.getElementById("preview");
        var p_file_el = document.getElementById("store_logo");
        var preview_img = function (file) {
            let reader = new FileReader();
            reader.readAsDataURL(file);
            reader.addEventListener("load", function () {
                preview_el.innerHTML = `<img src='${reader.result}' class='preview_img'>`;
            });
        };
    </script>
</body>

</html>