<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>IBM Emp: Home</title>

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
   <tr><td><h3>IBM Admin: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Admin: Home</p>

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
  <li><a href='listAllAdmin.jsp'>List</a> all Admins.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="admin.do" >
        <b>輸入管理員編號 (如A001):</b>
        <input type="text" name="adminId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
  <jsp:useBean id="adminSvc" scope="page" class="com.tibame.tga104.g2.oladesign.admin.model.AdminService" />
   
  <li>
     <FORM METHOD="post" ACTION="admin.do" >
       <b>選擇管理員編號:</b>
       <select size="1" name="adminId">
         <c:forEach var="adminVO" items="${adminSvc.all}" > 
          <option value="${adminVO.adminId}">${adminVO.adminId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="admin.do" >
       <b>選擇管理員名稱:</b>
       <select size="1" name="adminId">
         <c:forEach var="adminVO" items="${adminSvc.all}" > 
          <option value="${adminVO.adminId}">${adminVO.adminName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>管理員管理</h3>

<ul>
  <li><a href='addAdmin.jsp'>Add</a> a new Admin.</li>
</ul>

</body>
</html>