<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Home Page</title>
</head>
<body>
<div class="wrapper">
<%@ include file="../include/header.jsp"%>
</div>
    <h1>This is the Home page for OLA design</h1>
    <h3> <font color=red> ${memName} </font></h3>
    <h3> <font color=red> ${account} </font></h3>
	<h3><a href="<%=request.getContextPath()%>/member/memRegist.jsp">會員註冊</a></h3>
	<h3><a href="<%=request.getContextPath()%>/front-end/regist-login/login/login.jsp">首頁-會員登入jsp</a></h3>
	<button type="button" class="login"><a href="<%=request.getContextPath()%>/member/login.jsp">登入/註冊</a></button>
</body>
</html>