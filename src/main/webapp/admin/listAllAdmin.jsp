<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.admin.model.*"%>

<%
    AdminService adminSvc = new AdminService();
    List<AdminVO> list = adminSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有管理員資料 - listAllAdmin.jsp</title>

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
	width: 800px;
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

>
<table id="table-1">
	<tr><td>
		 <h3>所有管理員資料 - listAllAdmin.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/back-end-index.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>管理員編號</th>
		<th>管理員名稱</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="adminVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${adminVO.adminId}</td>
			<td>${adminVO.adminName}</td>
			<td>${adminVO.account}</td>
			<td>${adminVO.password}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/admin/admin.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="adminId"  value="${adminVO.adminId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/admin/admin.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="adminId"  value="${adminVO.adminId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>