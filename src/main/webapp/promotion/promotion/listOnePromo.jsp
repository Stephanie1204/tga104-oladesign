<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.tibame.tga104.g2.oladesign.promotion.model.promo.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  //EmpVO empVO = (EmpVO) request.getAttribute("empVO"); 
  //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
	<title>�P�P��� - listOnePromo.jsp</title>
	
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

	<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
	<table id="table-1">
		<tr><td>
			 <h3>�P�P���� - ListOnePromo.jsp</h3>
			 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
		</td></tr>
	</table>
	
	<table>
		<tr>
			<th>�P�P�M�׽s��</th>
			<th>�P�P�M�צW��</th>
			<th>���q�νs</th>
			<th>�}�l���</th>
			<th>�������</th>
			<th>�馩�X</th>
			
		</tr>
		<tr>
			<td>${promoVO.promoId}</td>
			<td>${promoVO.promoName}</td>
			<td>${promoVO.comTaxId}</td>
			<td>${promoVO.startDate}</td>
			<td>${promoVO.endDate}</td>
			<td>${promoVO.coupon}</td>
		</tr>
	</table>

</body>
</html>