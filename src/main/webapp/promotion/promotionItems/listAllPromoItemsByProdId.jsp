<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.promotion.model.promoItem.*"%>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>促銷專案明細</title>
	<style>
	  table#header {
		background-color: #CCCCFF;
	    border: 2px solid black;
	    text-align: center;
	  }
	  table#header h4 {
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
		width: 100%;
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

	<table id="header">
		<tr>
			<td>
				<h3>促銷專案明細 - listAllPromoItemsByProdId.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="###" width="100"
						height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
<%-- 	<table ="listHeadBar">
  
		  <tr>
			    <th>促銷專案編號</th>
			    <th>促銷專案名稱</th>
			    <th>開始日期</th>
			    <th>結束日期</th>
			   <!--<th>促銷天數</th> -->
		  </tr>
		  <c:forEach var="VO" items="${list}">
			<tr>
				<td>${VO.prodId}</td>
				<td>${VO.prodName}</td>
				<td>${VO.code}</td>
				<td>${VO.name}</td>
				<td>${VO.discount}</td>
	 </table> 
  --%>
  
	<table class=allList>
		<tr>
			<th>專案編號</th>
			<th>專案名稱</th>
			<th>開始日期</th>
			<th>結束日期</th>
			<th>商品編號</th>
		    <th>商品名稱</th>
		    <th>原價</th>
		    <th>促銷類型代號</th>
		    <th>促銷類型名稱</th>
		    <th>折扣程度</th>
		    <!-- <th>促銷後價格</th>-->
		    <th>折扣代碼</th>
		    <th>創建日期</th>
		    <th>最後修改日期</th>
		</tr>

		<c:forEach var="VO" items="${allPromoItemList}">
			<tr>
				
				<td>${VO.promoId}</td>
			    <td>${VO.prodName}</td>
			    <td>${VO.promoStartDate}</td>
			    <td>${VO.promoEndDate}</td>
			    <td>${VO.prodId}</td>
			    <td>${VO.prodName}</td>
			    <td>${VO.price}</td>
				<td>${VO.code}</td>
			    <td>${VO.codeName}</td>
				<td>${VO.discount}</td>
				<td>${VO.coupon}</td>
				<td>${VO.createTime}</td>
				<td>${VO.modifyTime}</td>
				</td>
			</tr>
		</c:forEach>
	</table>
	
<!-- TODO 分頁改天啦 -->
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>