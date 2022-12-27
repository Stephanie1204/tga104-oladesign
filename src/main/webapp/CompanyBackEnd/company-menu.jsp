<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="/oladesign/plugins/adminLTE/css/skins/oladesign-skin.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"/>	
<!-- <link rel="stylesheet" -->
<!-- 	href="../plugins/adminLTE/css/skins/oladesign-skin.css"> -->
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="/oladesign/img/kapibarakun.jpg" class="img-circle"
					alt="User Image">
			</div>
			<div class="pull-left info">
				<p>水豚君</p>

			</div>
		</div>

		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<!-- 菜单 -->


			<li class="treeview"><a href="#"> <i class="fa-regular fa-face-smile"></i>
					<span>賣家專區</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">

					<li id="form-general"><a href="<%=request.getContextPath()%>/CompanyBackEnd/setcompany_member.jsp"> <i
							class="fa fa-circle-o"></i> 賣家基本資料
					</a></li>

					<li id="form-general"><a href="<%=request.getContextPath()%>/CompanyBackEnd/listonecompany_forshop.jsp"> <i
							class="fa fa-circle-o"></i> 賣場管理
					</a></li>


				</ul></li>

			<li class="treeview"><a href="#"> <i class="fa-solid fa-folder-plus"></i>
					<span>訂單管理</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">

					<li id="form-general"><a href="<%=request.getContextPath()%>/CompanyBackEnd-order/company-orderlist.jsp"> <i
							class="fa fa-circle-o"></i> 訂單管理
					</a></li>


				</ul></li>

			<li class="treeview"><a href="#"> <i class="fa-solid fa-briefcase"></i>
					<span>商品管理</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">

					<li id="form-general"><a href="<%=request.getContextPath()%>/CompanyBackEnd-product/company-addproduct.jsp"> <i
							class="fa fa-circle-o"></i> 新增商品
					</a></li>
					<li id="form-general"><a href="<%=request.getContextPath()%>/CompanyBackEnd-product/company-productlist.jsp"> <i
							class="fa fa-circle-o"></i> 商品列表
					</a></li>


				</ul></li>

			<li class="treeview"><a href="#"> <i class="fa-solid fa-tags"></i>
					<span>促銷活動管理</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">

					<li id="form-general"><a href="<%=request.getContextPath()%>/promotion/promotion_front/promoHome.html"> <i
							class="fa fa-circle-o"></i> 促銷明細
					</a></li>
					<li id="form-general"><a href="<%=request.getContextPath()%>/promotion/promotion_front/addPromo.html"> <i
							class="fa fa-circle-o"></i> 新增促銷活動
					</a></li>


				</ul></li>

			<li class="treeview"><a href="#"> <i class="fa-solid fa-rectangle-ad"></i>
					<span>廣告投放</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">

					<li id="form-general"><a href="<%=request.getContextPath()%>/CompanyBackEnd/addadvertisement.jsp"> <i
							class="fa fa-circle-o"></i> 申請廣告投放
					</a></li>

					<li id="form-general"><a href="<%=request.getContextPath()%>/CompanyBackEnd/recordadvertisement.jsp"> <i
							class="fa fa-circle-o"></i> 查詢廣告投放紀錄
					</a></li>


				</ul></li>



			<li class="treeview"><a href="#"> <i class="fa-solid fa-chart-pie"></i>
					<span>營運分析報表</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">


					<li id="form-general"><a href="<%=request.getContextPath()%>/CompanyBackEnd/report.jsp"> <i
							class="fa fa-circle-o"></i> 銷量明細
					</a></li>


				</ul></li>



			<!-- 菜单 /-->

		</ul>
	</section>
	<!-- /.sidebar -->
</aside>
<!-- 导航侧栏 /-->
