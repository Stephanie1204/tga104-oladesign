<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tibame.tga104.g2.oladesign.intermail.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
Intermail_qnVO intermail_qnVO = (Intermail_qnVO) request.getAttribute("intermail_qnVO");
%>

<html>
<head>
<title>站內信問題類型資料 - listOneIntermail_qn.jsp</title>

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
		 <h3>站內信問題類型資料 - listOneIntermail_qn.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>問題類型編號</th>
		<th>問題類型選項</th>
	</tr>
	<tr>
		<td><%=intermail_qnVO.getNumQue()%></td>
		<td><%=intermail_qnVO.getType()%></td>

	</tr>
</table>

</body>
</html>