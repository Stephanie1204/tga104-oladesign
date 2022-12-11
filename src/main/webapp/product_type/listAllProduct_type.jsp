<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.product_type.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
Product_typeService product_typeSvc = new Product_typeService();
    List<Product_typeVO> list = product_typeSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有商品類別資料 - listAllProduct_type.jsp</title>

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

<table id="table-1">
	<tr><td>
		 <h3>所有商品類別資料 - listAllProduct_type.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>類型編號</th>
		<th>類型名稱</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="product_typeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${product_typeVO.typeCode}</td>
			<td>${product_typeVO.typeName}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_type/product_type.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="typeCode"  value="${product_typeVO.typeCode}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_type/product_type.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="typeCode"  value="${product_typeVO.typeCode}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>