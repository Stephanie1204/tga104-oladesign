<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>管理查進入頁面</title>
</head>
<style>


h4 {
	color: blue;
	display: inline;
}
</style>
<body bgcolor='white'>



	<h3>查詢廠商資料</h3>
	<%--錯誤提示 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>

		<%--查看全部廠商資料 --%>
		<li><a href='adminlistallcompany_member.jsp'> 查看全部廠商資料</a> <br> <br></li>

		<%--單一查詢廠商資料 --%>
		<li><form method="post" action="company_member.do">
				<b>請輸入廠商統一編號</b> 
				<input type="text" name="comtaxId"> 
				<input type="hidden" name="action" value="getOne_For_Display"> 
				<input type="submit" value="送出">
			</form></li>
	</ul>
	<h3>查詢全部廣告資料</h3>

	<ul>

		<%--查看全部廠商資料 --%>
		<li><a href='adminlistalladvertisement.jsp'> 查看全部廠商資料</a> <br> <br></li>

		<%--單一查詢廠商資料 --%>
		<li><form method="post" action="advertisement.do">
				<b>請輸入廠商編號查詢廣告</b> 
				<input type="text" name=comtaxId> 
				<input type="hidden" name="action" value="getOneFromADID"> 
				<input type="submit" value="送出">
			</form></li>
	</ul>
</html>