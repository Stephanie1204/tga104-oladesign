<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.intermail.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    Intermail_qnService intermail_qnSvc = new Intermail_qnService();
    List<Intermail_qnVO> list = intermail_qnSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有站內信問題類型資料 - listAllIntermail_qn.jsp</title>

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有站內信問題類型資料 - listAllIntermail_qn.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>問題類型編號</th>
		<th>問題類型選項</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="intermail_qnVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${intermail_qnVO.numQue}</td>
			<td>${intermail_qnVO.type}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/intermail_qn/intermail_qn.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="numQue"  value="${intermail_qnVO.numQue}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/intermail_qn/intermail_qn.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="numQue"  value="${intermail_qnVO.numQue}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>