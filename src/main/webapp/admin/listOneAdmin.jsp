<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.tibame.tga104.g2.oladesign.admin.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  AdminVO adminVO = (AdminVO) request.getAttribute("adminVO"); 
%>

<html>
<head>
<title>�޲z�����</title>

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
	width: 600px;
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
	<tr><td>
		 <h3>�޲z�����</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/back-end-index.jsp">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�޲z���s��</th>
		<th>�޲z���W��</th>
		<th>�b��</th>
		<th>�K�X</th>
	</tr>
	<tr>
		<td><%=adminVO.getAdminId()%></td>
		<td><%=adminVO.getAdminName()%></td>
		<td><%=adminVO.getAccount()%></td>
		<td><%=adminVO.getPassword()%></td>

	</tr>
</table>

</body>
</html>