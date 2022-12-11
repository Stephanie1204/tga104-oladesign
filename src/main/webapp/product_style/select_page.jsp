	<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>IBM Product_style: Home</title>

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
   <tr><td><h3>IBM Product_style: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Product_style: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllProduct_style.jsp'>List</a> all Product_styles.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="product_style.do" >
        <b>輸入地區類別編號 (如S001):</b>
        <input type="text" name="styleCode">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="product_styleSvc" scope="page" class="com.tibame.tga104.g2.oladesign.prodeuct_style.model.Product_styleService" />
   
  <li>
     <FORM METHOD="post" ACTION="product_style.do" >
       <b>選擇地區類別編號:</b>
       <select size="1" name="styleCode">
         <c:forEach var="product_styleVO" items="${product_styleSvc.all}" > 
          <option value="${product_styleVO.styleCode}">${product_styleVO.styleCode}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="product_style.do" >
       <b>選擇地區類別名稱:</b>
       <select size="1" name="styleCode">
         <c:forEach var="product_styleVO" items="${product_styleSvc.all}" > 
          <option value="${product_styleVO.styleCode}">${product_styleVO.styleName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>問題類型管理</h3>

<ul>
  <li><a href='addProduct_style.jsp'>Add</a> a new addProduct_style.</li>
</ul>

</body>
</html>