<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.prodeuct_style.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
Product_styleService product_styleSvc = new Product_styleService();
    List<Product_styleVO> list = product_styleSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有商品類別資料 - listAllProduct_style.jsp</title>

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
		 <h3>所有地區類型資料 - listAllProduct_style.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>地區類別編號</th>
		<th>地區類別名稱</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="product_styleVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${product_styleVO.styleCode}</td>
			<td>${product_styleVO.styleName}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_style/product_style.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="styleCode"  value="${product_styleVO.styleCode}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_style/product_style.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="styleCode"  value="${product_styleVO.styleCode}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>