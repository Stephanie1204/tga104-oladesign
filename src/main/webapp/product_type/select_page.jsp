	<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>IBM Product_type: Home</title>

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
   <tr><td><h3>IBM Product_type: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Product_type: Home</p>

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
  <li><a href='listAllProduct_type.jsp'>List</a> all Product_types.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="product_type.do" >
        <b>輸入問題類型編號 (如A001):</b>
        <input type="text" name="typeCode">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="product_typeSvc" scope="page" class="com.tibame.tga104.g2.oladesign.product_type.model.Product_typeService" />
   
  <li>
     <FORM METHOD="post" ACTION="product_type.do" >
       <b>選擇類型編號:</b>
       <select size="1" name="typeCode">
         <c:forEach var="product_typeVO" items="${product_typeSvc.all}" > 
          <option value="${product_typeVO.typeCode}">${product_typeVO.typeCode}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="product_type.do" >
       <b>選擇類型名稱:</b>
       <select size="1" name="typeCode">
         <c:forEach var="product_typeVO" items="${product_typeSvc.all}" > 
          <option value="${product_typeVO.typeCode}">${product_typeVO.typeName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>問題類型管理</h3>

<ul>
  <li><a href='addProduct_type.jsp'>Add</a> a new addProduct_type.</li>
</ul>

</body>
</html>