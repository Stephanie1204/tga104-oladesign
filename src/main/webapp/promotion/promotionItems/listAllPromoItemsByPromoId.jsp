<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.tga104.g2.oladesign.promotion.model.promoItem.*"%>
<%-- 
<%
PromoItemService promoItem_svc = new PromoItemService();
List<PromoItemVO> allItemList = new PromoItemService().getAllByProdId(promoId);
pageContext.setAttribute("allItemList", allItemList);
%>
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>促銷專案明細</title>
<style>
  table#header {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#header h4 {
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
	width: 100%;
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

<script src="/tga104-g2-oladesign/jquery.js"></script>
</head>
<body bgcolor='white'>

	<table id="header">
		<tr>
			<td>
				<h3>促銷專案明細 - listAllPromoItemsByPromoId.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="###" width="100"
						height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table ="listHeadBar">

		  <tr>
			    <th>促銷專案編號</th>
			    <th>促銷專案名稱</th>
			    <th>開始日期</th>
			    <th>結束日期</th>
			    <th>折扣代碼</th>
			   <!--<th>促銷天數</th> --> 
		  </tr>

			<tr>
				<td>${promoVO.promoId}</td>
				<td>${promoVO.promoName}</td>
				<td>${promoVO.startDate}</td>
				<td>${promoVO.endDate}</td>
				<td>${promoVO.coupon}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/promotionItems/AddPromoItem.jsp"
						style="margin-bottom: 0px;">
						<input type="submit" value="新增促銷商品明細"> 
						<input type="hidden"name="promoId" value="${promoVO.promoId}"> 
						<input type="hidden"name="action" value="insert">
					</FORM>
				</td>
			</tr>
	 
	 
	 </table> 

  
	<table class=allList>
		<tr>
			<th>商品編號</th>
		    <th>商品名稱</th>
		    <th>促銷類型代號</th>
		    <th>促銷類型名稱</th>
		    <th>折扣程度</th>
		    <th>原價</th>
		    <!-- <th>促銷後價格</th>-->
		    <th>創建日期</th>
		    <th>最後修改日期</th>
		</tr>

	<c:forEach var="VO" items="${allItemList}">
	 
			<tr>
				<td>${VO.prodId}</td>
			    <td>${VO.prodName}</td>
				<td>${VO.code}</td>
			    <td>
			    	<span>${VO.codeName}</span>
			    	<select onchange="changeCode(this)"  >
						<!-- TODO value應該透過其他方式取得 -->
			    		<option value="P001" ${VO.codeName eq "單品降價" ? 'selected="selected"' : ""}>單品降價</option>
			    		<option value="P002" ${VO.codeName eq "單品打折" ? 'selected="selected"' : ""}>單品打折</option>
			    	</select>
			    </td>
				<td>
					<span>${VO.discount}</span>
					<input type="text" value="${VO.discount}" onchange="$(this).prev().html(this.value); check_discount(this)" >
				</td>
				<td>${VO.price}</td>
				<td>${VO.createTime}</td>
				<td>${VO.modifyTime}</td>
				

				<td>
					<FORM METHOD="post" id="delete_buttom"
						ACTION="<%=request.getContextPath()%>/promotion/promoItem.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除???問" >
						<input type="hidden"name="promoId" value="${promoVO.promoId}"> 
						<input type="hidden"name="prodId" value="${VO.prodId}"> 
						<input type="hidden"name="action" value="delete">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/promotion/promo.do"
						style="margin-bottom: 0px;">
						<input type="button" value="修改還沒做" onclick="awef()" > 
						<input type="button" value="送出修改" onclick="sendUpdate(this)" > 
						<input type="hidden"name="promoId" value="${VO.promoId}"> 
						<input type="hidden"name="action" value="delete###">
					</FORM>
				</td>
			</tr>
		</c:forEach> 
	</table>
	<script>
		function del_check(){
			confirm("確認刪除此筆促銷明細?");
			if(true){
				submit();				
			}	
		}
		
		function awef() {
			alert("edit");
		}
		
		function check_discount(target){
			const $target = $(target);
			const $code = $target.closest("td").prev("td").prev("td");
			if($code.text()==="P002") {
				if($target.val()>99 || $target.val()<1){
					alert("折扣程度請介於1~99")
				}
			}
		}
		
		function changeCode(target){
			const $codeName = $(target).prev('span');
			const $code = $(target).closest("td").prev("td");
			if(target.value === "P001"){
				$code.text("P001");
				$codeName.text("單品降價");
			}
			
			if(target.value === "P002"){
				$code.text("P002");
				$codeName.text("單品打折");
			}	
		}
		
		function sendUpdate(target) {
			// value of input tag element
			
			// value of select tag element
			
			// 組出json物件 { code: ???, discount: ??? }
			
			// alert()提示更新成功或失敗
			
			$.ajax({
				  method: "POST",
				  url: "http://localhost:8080/tga104-g2-oladesign/AjaxServlet",
				  data: {code: "3345678",discount:""},
				  dataType: "json",
				  contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				  success: function(res){
				  		console.log(res);
				  },
				  error: function(res){
						console.log(res);
				  }
			});
		}
		
	</script>
	
	
<!-- TODO 分頁改天啦 -->
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>