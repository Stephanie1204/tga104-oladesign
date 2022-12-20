<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <body>
    
 	<form METHOD="post" ACTION="<%=request.getContextPath()%>/ToGetComTaxIdService" name="form1">
    會員帳號
    <input type="text" name="memId" value="${param.memId}" placeholder="請輸入會員帳號" />
    <button type="submit" value="登入">登入</button>

    <br />
    賣家統編
    <input type="text" name="comTaxId" value="${sessionScope.comTaxId}" placeholder="自動帶入賣家統編" />
    <c:set var="comTaxId" value="${comTaxId}" scope="session"/>
    </form>
    
    <br>
    
<!--     <form METHOD="get" action="http://localhost:8080/oladesign/promo?comTaxId=2"> -->
<!--       <input type="submit" value="進入賣家促銷首頁"> -->
<%--       <input type="hidden" name="comTaxId" value="${comTaxId}"> --%>
<!--     </form> -->
    
<%--      <button><a href="<%=request.getContextPath()%>/promo?comTaxId=${comTaxId}">進入賣家促銷首頁</a></button> --%>
<%--      <button><a href="<%=request.getContextPath()%>/promotion/promoHome.html">進入賣家促銷首頁</a></button> --%>

		<button><a href="<%=request.getContextPath()%>/promotion/promotion_front/promoHome.html?comTaxId=${comTaxId}">進入賣家促銷首頁</a></button>
    

  </body>
</html>
