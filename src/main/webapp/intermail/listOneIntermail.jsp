<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tibame.tga104.g2.oladesign.intermail.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
IntermailVO intermailVO = (IntermailVO) request.getAttribute("intermailVO");
%>

<html>
<head>
<title>站內信問題類型資料 - listOneIntermail.jsp</title>

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
	width: 600px;
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


<table id="table-1">
	<tr><td>
		 <h3>站內信資料 - listOneIntermail.jsp</h3>
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
	<tr>
			<td>${intermailVO.interMailId}</td>
			<td>${intermailVO.memId}</td>
			<td>${intermailVO.adminId}</td>
			<td>${intermailVO.numQue}</td>
			<td>${intermailVO.conTent}</td>
			<td>${intermailVO.sentTime}</td>

	</tr>
</table>

</body>
</html>