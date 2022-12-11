<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- header css -->
<link rel="stylesheet" href="../static/css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="../static/css/header.css" type="text/css" />

<header class="header">
	<div class="row">
		<div class="col-lg-4">
			<div class="header__logo">
				<a href="<%=request.getContextPath()%>/front-end/front-index.jsp"><img src="../img/OLA_Logo.svg" alt="" /></a>
			</div>
		</div>
		<div class="col-lg-6">
			<nav class="header__menu">
				<ul>
					<li class="active"><a href="./index.html">Home</a></li>
					<li><a href="./shop-grid.html">Shop</a></li>
					<li><a href="#">Pages</a>
						<ul class="header__menu__dropdown">
							<li><a href="./shop-details.html">Shop Details</a></li>
							<li><a href="./shoping-cart.html">Shoping Cart</a></li>
							<li><a href="./checkout.html">Check Out</a></li>
							<li><a href="./blog-details.html">Blog Details</a></li>
						</ul></li>
					<li><a href="./blog.html">Blog</a></li>
					<li><a href="./contact.html">Contact</a></li>
				</ul>
			</nav>
		</div>
		<div class="col-lg-2">
			<div class="header__cart">
				<ul>
					<li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a>
					</li>
					<li><a href="#"><i
							class="fa fa-shopping-bag"></i> <span>3</span></a></li>
				</ul>
			</div>
		</div>
	</div>
</header>

