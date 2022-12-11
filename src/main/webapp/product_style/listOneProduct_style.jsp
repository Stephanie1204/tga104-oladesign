<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tibame.tga104.g2.oladesign.prodeuct_style.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
Product_styleVO product_styleVO = (Product_styleVO) request.getAttribute("product_styleVO");
%>

<html>
<head>
<title>商品類型資料 - listOneProduct_style.jsp</title>

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
		 <h3>商品類型資料 - listOneProduct_style.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>地區類別編號</th>
		<th>地區類別名稱</th>
	</tr>
	<tr>
		<td><%=product_styleVO.getStyleCode()%></td>
		<td><%=product_styleVO.getStyleName()%></td>

	</tr>
</table>

</body>
</html>