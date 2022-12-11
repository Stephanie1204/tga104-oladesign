<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Promo Home </title>
<style>
  table#table-1 {
	width: 450px;
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
<table id="table-1">
   <tr><td><h3>Promo: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Promotion: Home</p>

<h3>資料查詢:</h3>

<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message.value}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<ul>
  <li><a href='PromoItemHomePage.jsp'>List all Promotions</a><br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="promo.do" >
        <b>輸入促銷專案編號 :</b>
        <input type="text" name="promo" value="${param.empno}"><font color=red>${errorMsgs.empno}</font>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="promoSvc" scope="page" class="com.tibame.tga104.g2.oladesign.promotion.model.promo.PromoService" />
  
   
  <li>
     <FORM METHOD="post" ACTION="promo.do" >
       <b>選擇促銷專案編號:</b>
       <select size="1" name="promoId">
         <c:forEach var="promoVO" items="${promoSvc.all}" > 
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




</body>
</html>