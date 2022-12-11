<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.tibame.tga104.g2.oladesign.promotion.model.promo.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  //EmpVO empVO = (EmpVO) request.getAttribute("empVO"); 
  //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
	<title>促銷資料 - listOnePromo.jsp</title>
	
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

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr><td>
			 <h3>促銷明細 - ListOnePromo.jsp</h3>
			 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
		</td></tr>
	</table>
	
	<table>
		<tr>
			<th>促銷專案編號</th>
			<th>促銷專案名稱</th>
			<th>公司統編</th>
			<th>開始日期</th>
			<th>結束日期</th>
			<th>折扣碼</th>
			
		</tr>
		<tr>
			<td>${promoVO.promoId}</td>
			<td>${promoVO.promoName}</td>
			<td>${promoVO.comTaxId}</td>
			<td>${promoVO.startDate}</td>
			<td>${promoVO.endDate}</td>
			<td>${promoVO.coupon}</td>
		</tr>
	</table>

</body>
</html>