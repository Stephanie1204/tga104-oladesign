<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.intermail.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
IntermailService intermailSvc = new IntermailService();
List<IntermailVO> list = intermailSvc.getAll();
pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有站內信問題類型資料 - listAllIntermail.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有站內信問題類型資料 - listAllIntermail.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>站內信編號</th>
		<th>會員編號</th>
		<th>管理員編號</th>
		<th>問題類型選項</th>
		<th>內容</th>
		<th>發送時間</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="intermailVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${intermailVO.interMailId}</td>
			<td>${intermailVO.memId}</td>
			<td>${intermailVO.adminId}</td>
			<td>${intermailVO.numQue}</td>
			<td>${intermailVO.conTent}</td>
			<td><fmt:formatDate value="${intermailVO.sentTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${intermailVO.isSend}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/intermail/intermail.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="interMailId"  value="${intermailVO.interMailId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/intermail/intermail.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="interMailId"  value="${intermailVO.interMailId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>