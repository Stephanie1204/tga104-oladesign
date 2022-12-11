	<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>IBM Intermail: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 10px;
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
   <tr><td><h3>IBM Intermail: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Intermail: Home</p>

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
  <li><a href='listAllIntermail.jsp'>List</a> all Intermails.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="intermail.do" >
        <b>輸入信件編號 :</b>
        <input type="text" name="interMailId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="intermailSvc" scope="page" class="com.tibame.tga104.g2.oladesign.intermail.model.IntermailService" />
   

  
</ul>


<h3>站內信管理</h3>

<ul>
  <li><a href='addIntermail.jsp'>Add</a> a new addIntermail.</li>
</ul>

</body>
</html>