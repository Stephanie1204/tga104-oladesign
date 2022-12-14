<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ page import="com.tibame.tga104.g2.oladesign.promotion.controller.*"%> --%>

<html>
<head>
<title> 促銷首頁 </title>
<style>
  table#table-1 {
	width: 100%;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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
</head>
<body bgcolor='white'>
--${comTaxId}--
<table id="table-1">
   <tr><td><h3>促銷首頁</h3><h4>/promotion.select_page.jsp</h4></td></tr>
</table>


<h3>促銷專案查詢:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li>
  	<a href='listAllPromo.jsp'>所有促銷專案</a><br><br>
  </li>
  
   <li>
    <FORM METHOD="post" ACTION="promo.do" >
        <b>輸入廠商搜尋促銷 :</b>
        <input type="text" name="promo" value="${param.comTaxId}" placeholder="還沒做，等知道怎麼鎖定廠商"><font color=red>${errorMsgs.comTaxId}</font>
        <input type="hidden" name="action" value="getOne_For_Display_byCom">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
    <FORM METHOD="post" ACTION="promo.do" >
        <a>目前登入廠商的所有促銷資料 :</a>
        <input type="text" name="promo" value="${session.comTaxId}"><font color=red>${errorMsgs.comTaxId}</font>
        <input type="hidden" name="action" value="getOne_For_Display_byCom">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
    <FORM METHOD="post" ACTION="promo.do" >
        <b>輸入促銷專案編號 :</b>
        <input type="text" name="promoId" value="${param.promoId}" placeholder="請輸入完整促銷專案編號"><font color=red>${errorMsgs.promoId}</font>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

<%--   <jsp:useBean id="promoSvc" scope="page" class="com.tibame.tga104.g2.oladesign.promotion.model.promo.PromoService" /> --%>
 
  
  <li>
     <FORM METHOD="post" ACTION="promo.do" >
       <b>選擇促銷專案編號:</b>
       <select name="promoId">
         <c:forEach var="promoVO" items="${xxx}" > 
          <option value="${promoVO.promoId}">${promoVO.promoId}
         </c:forEach> 
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  

  <li>
     <FORM METHOD="post" ACTION="promo.do" >
       <b>選擇促銷專案名稱:</b>
       <select size="1" name="promoId">
         <c:forEach var="promoVO" items="${promoSvc.all}" > 
          <option value="${promoVO.promoId}">${promoVO.promoName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>新增促銷專案</h3>

<ul>
  <li><a href='addPromo.jsp'>Add a new Promotion</a></li>
</ul>


<h3>商品促銷專案查詢:</h3>
<ul>
	<li>
	    <FORM METHOD="post" ACTION="/oladesign/promoItem.do" >
	        <b>輸入商品編號 :</b>
	        <input type="text" name="prodId" value="${param.prodId}" placeholder="請輸入完整商品編號"><font color=red>${errorMsgs.prodId}</font>
	        <input type="hidden" name="action" value="getOneForDisplay_byProdID">
	        <input type="submit" value="送出">
	    </FORM>
	</li>
</ul>




</body>
</html>