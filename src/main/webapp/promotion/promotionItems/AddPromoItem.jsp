<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ page import="com.promo.model.*"%> --%>

<html>
<head>
	<title>新增促銷明細 - addPromoItem.jsp</title>
	
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
		width: 100%;
		background-color: white;
		margin-top: 1px;
		margin-bottom: 1px;
	  }
	  table, th, td {
	    border: 0px solid #CCCCFF;
	  }
	  th, td {
	    padding: 1px;
	  }
	</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
			 <h3>新增促銷明細 - addPromoItem.jsp</h3>
			 </td>
			 <td>
			 <h4><a href="<%=request.getContextPath()%>/promotion/select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
			</td>
		</tr>
	</table>

	<h3>促銷明細新增</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/promotion/promoItem.do" name="form1">
	<table>
		<tr>
			<td>促銷專案編號:
			<input type="TEXT" name="promoId" size="20" value="${param.promoId}" readonly/></td>
		</tr>

		<tr>
			<td><font color=red>*</font>商品編號:
			<input type="TEXT" name="prodId" size="45" value="${param.prodId}" /></td>
			<td>${errorMsgs.prodId}</td>
		</tr>

		<jsp:useBean id="promoTypeSvc" scope="page" class="com.tibame.tga104.g2.oladesign.promotion.model.promo.PromoService" />
		<tr>
			<td><font color=red>*</font>折扣種類:
			<select name="code">
				<c:forEach var="VO" items="${promoTypeSvc.all}">
					<option value="${VO.code}" >${VO.name}
				</c:forEach>
			</select></td>
		</tr>
<!-- 		<tr> -->
<!-- 			<td>折扣種類:</td> -->
<!-- 			<td><input name="code" id="f_date1" type="text"></td> -->
<!-- 		</tr> -->

		<tr>
			<td><font color=red>*</font>折扣程度:
			<input type="TEXT" name="discount" size="6"
				 value="${param.discoun}" /></td>
		</tr>

	</table>
	<br>
	<input type="hidden" name="action" value="insert">
	<input type="submit" value="送出新增"></FORM>
</body>


</html>