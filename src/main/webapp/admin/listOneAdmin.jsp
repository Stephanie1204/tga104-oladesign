<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.tibame.tga104.g2.oladesign.admin.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  AdminVO adminVO = (AdminVO) request.getAttribute("adminVO"); 
%>

<html>
<head>
<title>管理員資料</title>

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
		 <h3>管理員資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/back-end-index.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>管理員編號</th>
		<th>管理員名稱</th>
		<th>帳號</th>
		<th>密碼</th>
	</tr>
	<tr>
		<td><%=adminVO.getAdminId()%></td>
		<td><%=adminVO.getAdminName()%></td>
		<td><%=adminVO.getAccount()%></td>
		<td><%=adminVO.getPassword()%></td>

	</tr>
</table>

</body>
</html>