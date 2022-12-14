<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tibame.tga104.g2.oladesign.member.bean.*"%>

	
<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
System.out.println("login memberVO="+ memberVO);
String message = (String)request.getAttribute("success");
System.out.println("message="+ message);
String pwdMessage = (String)request.getAttribute("pwdrecover");
String vericodeDel = (String)request.getAttribute("vericodeDel");
System.out.println("vericodeDel="+ vericodeDel);
String vericodeDelReset = (String)request.getAttribute("vericodeDelReset");
System.out.println("vericodeDelReset="+ vericodeDelReset);
%>	
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="" />
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors" />
<meta name="generator" content="Hugo 0.104.2" />
<title>OLA Design</title>
<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet" />

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous" />
<!--bootstrap-->

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

.b-example-divider {
	height: 3rem;
	background-color: rgba(0, 0, 0, 0.1);
	border: solid rgba(0, 0, 0, 0.15);
	border-width: 1px 0;
	box-shadow: inset 0 0.5em 1.5em rgba(0, 0, 0, 0.1), inset 0 0.125em
		0.5em rgba(0, 0, 0, 0.15);
}

.b-example-vr {
	flex-shrink: 0;
	width: 1.5rem;
	height: 100vh;
}

.bi {
	vertical-align: -0.125em;
	fill: currentColor;
}

.nav-scroller {
	position: relative;
	z-index: 2;
	height: 2.75rem;
	overflow-y: hidden;
}

.nav-scroller .nav {
	display: flex;
	flex-wrap: nowrap;
	padding-bottom: 1rem;
	margin-top: -1px;
	overflow-x: auto;
	text-align: center;
	white-space: nowrap;
	-webkit-overflow-scrolling: touch;
}
</style>

<!-- Custom styles for this template -->
<link href="../css/login.css" rel="stylesheet" />
</head>
<body class="text-center">
<!-- 	    <button type="button" class="login">??????/??????</button> -->
	<div class="container1">
	<div class="backtoindex">
		<a href="<%=request.getContextPath()%>/homePage/index.jsp" class="index"><span class="back">?????????</span></a>
	</div>
	
		<!-- <div class="filter"></div> -->
		<main class="form-signin w-100 m-auto main">
			<form method="post" action="<%=request.getContextPath()%>/member/MemberLogin">
<!-- 				<button type="button" class="btn-close" aria-label="Close"></button> -->
				<!--????????????????????????-->
				<a href="<%=request.getContextPath()%>/homePage/index.jsp"><img class="mb-4 logo" src="../img/OLA_Logo.svg" alt="logo" /></a>
				<h1 class="h3 mb-3 fw-normal fw-bold">???????????????</h1>

				<div class="form-floating">
					<input type="email" class="form-control" id="floatingInput"
						placeholder="name@example.com" name="account" value="${memberVO.account}"/> <label
						for="floatingInput">Email</label>
				</div>
				<div class="form-floating">
					<input type="password" class="form-control" id="floatingPassword"
						placeholder="Password" name="password"/> <label
						for="floatingPassword">??????</label>
				</div>
				<div class="error">
					<%-- ???????????? --%>
					<c:if test="${not empty errorMsgs}">
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: #E86F62">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
				<div>
					<input type="hidden" name="action" value="login">
					<button id="login" class="btn" type="submit">??????</button>
				</div>
				<a class="forget" href="<%=request.getContextPath()%>/member/forgetpwd.jsp">?????????????</a>
				<!-- <div class="or">
            <hr />
            <span>????????????????????????</span>
          </div>
          <div class="other_account">
            <ul>
              <li class="signin">
                <a class="google_signin" href="#" platformid="google">
                  <img class="google_icon" src="../img/google.png" />
                  <span>Google????????????</span>
                </a>
              </li>
            </ul>
          </div> -->
				<div class="regist">
					??????????????????? <a class=""
						href="<%=request.getContextPath()%>/member/memRegist.jsp">????????????</a>
				</div>
			</form>
		</main>
	</div>

<!-- 	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script> -->
	    <script>
	    	setTimeout(function(){
	    		if(<%=message%> == true){
		    		alert("email???????????????????????????????????????OLA Design?????????!");
		    	}else if(<%=vericodeDel%> == true){
		    		alert("????????????????????????????????????:\r\n"
			    			+ "1. ??????????????????????????????????????????OLA Design?????????!\r\n"
			    			+ "2. ???????????????????????????????????????????????????");
			    }else if(<%=vericodeDelReset%> == true){
			    		alert("????????????????????????????????????:\r\n"
				    			+ "1. ????????????????????????????????????????????????OLA Design?????????!\r\n"
				    			+ "2. ???????????????????????????????????????????????????????????????");
			    }
		    	
		    	if(<%=pwdMessage%> == true){
		    		alert("????????????????????????????????????????????????OLA Design?????????!");
		    	}	
	    	}, 500);
	    	
	    </script>
</body>
</html>
