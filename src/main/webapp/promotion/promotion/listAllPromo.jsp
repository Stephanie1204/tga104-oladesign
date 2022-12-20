<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.promotion.model.promo.*"%>

<%-- <% --%>
// PromoService promo_svc = new PromoService();
// List<PromoVO> list = promo_svc.getAll();
// pageContext.setAttribute("listtt", list);
<%-- %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>促銷專案-管理員頁面呈現用</title>
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
		<tr>
			<td>
				<h3>所有促銷專案-管理員頁面呈現用 - listAllPromo.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="###" width="100"
						height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
	<table class=allList>
		<tr>
			<th>促銷專案編號</th>
			<th>廠商統編</th>
			<th>專案名稱</th>
			<th>開始日期</th>
			<th>結束日期</th>
			<th>折扣碼</th>
		</tr>

		<c:forEach var="VO" items="${listtt}">
			<tr>
				<td>${VO.promoId}</td>
				<td>${VO.comTaxId}</td>
				<td>${VO.promoName}</td>
				<td>${VO.startDate}</td>
				<td>${VO.endDate}</td>
				<td>${VO.coupon}</td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/promotion/promo.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改">
						<input type="hidden"name="promoId" value="${VO.promoId}">
						<input type="hidden"name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" id="del_form"
						style="margin-bottom: 0px;">
						<input type="submit" id="del_buttom" value="刪除" onclick="check_del()"> 
						<input type="hidden"name="promoId" value="${VO.promoId}"> 
						<input type="hidden"name="action"  >
						
					</FORM>
					<script>
				    function check_del() {
				      var check = confirm("確認要刪除?");
				      if (check) {
				    	  document.getElementById("del_form").setAttribute("ACTION","<%=request.getContextPath()%>/promotion/promo.do")
				    	  document.getElementsByName("action").setAttribute("value", "delete");
				          document.getElementById("del_form").submit();
				          alert("提交成功");
				      }
				    }
				  </script>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/promotion/promoItem.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="促銷明細"> 
						<input type="hidden"name="promoId" value="${VO.promoId}"> 
						<input type="hidden"name="action" value="getOneForDisplay_byPromoID">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	
<!-- TODO 分頁改天啦 -->
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>