<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="../css/favorite.css" type="text/css" />

<script>	
	$(window).on("load", function(){
		let memId = "${memId}";
	    console.log("memId=" + memId);
	    let favorNum = null;
	  //先查詢已經加入收藏的商品
	    if(memId.length != 0){ 
	    	
    		$.ajax({
    			url: "<%=request.getContextPath()%>/favorite/FavorServlet",
    			type: "PUT",
    			data: JSON.stringify({
    				"memId": memId
    			}),
        		dataType: "json",
        		contentType: "application/json",
        		processData: false,
        		success: function(data){
	            	console.log("data");
	            	var favorObj = JSON.parse(data.getFavor);
	            	console.log(favorObj);
	            	console.log("favorObj.length:" + favorObj.length);
	            	favorNum = favorObj.length;
	            	if(favorNum == 0){
	            		$("span#fav").addClass("down"); //沒有收藏時不顯示數量
	            	}else{
	            		$("#fav").text(favorNum); //在收藏icon顯示收藏數量
	            	}
	            	
	            	$.each(favorObj, function(index, item){ //取出已收藏的商品ID
		            	console.log(item.prodId);
	            		$("#" + item.prodId + "").addClass("active");
	            		$("#" + item.prodId + "").closest("ul").addClass("active");
		            });	            	
	            },
	            error: function(xhr){
	            	console.log(xhr);
	            },
	            complete: function(xhr){
	            	console.log(xhr);
	            }
    		});
    	}
	  	    
// 	  	新增、移除收藏
		clickFavor(memId);
	  	
// 	  	點擊會員收藏icon查看所有收藏商品
		$("i.memFav").on("click", function(){
			if(memId == null || memId.length == 0){
	    		alert("請先登入會員");
	    	}else{
	    		$.ajax({
	    			url: "<%=request.getContextPath()%>/favorite/FavorServlet",
	    			type: "PUT",
	    			data: JSON.stringify({
	    				"memId": memId
	    			}),
	        		dataType: "json",
	        		contentType: "application/json",
	        		processData: false,
	        		success: function(data){
		            	console.log("data");
		            	var favorObj = JSON.parse(data.getFavor);
		            	console.log(favorObj);
		            	console.log("favorObj.length:" + favorObj.length);	
		            	$("div.productDisplay *").css("display", "none");
		            	
		            	let container_html= "";
		            	container_html += '<h3>'+ favorObj.length +'項收藏商品</h3>';
		            	container_html += '<div	class="productDisplay row row-cols-lg-4 row-cols-md-3 row-cols-sm-2 mix">';
		            	$("div.prodcontainer").prepend(container_html);
		            	
		            	$.each(favorObj, function(index, item){ //取出已收藏的商品資訊
		            		let prodId = item.prodId;
			            	let prodName = item.name;
			            	let userId = item.memId;
			            	let comTaxId = item.comTaxId;
			            	let typeCode = item.typeCode;
			            	let styleCode = item.styleCode;
			            	let price = item.price;
			            	let img = item.imgBase64;
			            	
			     			let fav_html = "";
			            	fav_html +=	'<div class="featured__item">';
			            	fav_html +=	'<div class="featured__item__pic">';
			            	fav_html +=	'<a	href="../homePage/productPage.jsp?productId='+ prodId + '"';
			            	fav_html +=	'class="results" target="_blank" class="set-bg"';
			            	fav_html +=	'data-setbg="img/featured/feature-4.jpg"><img class="product__details__pic__item--large"';
			            	fav_html +=	'src="'+ img +'" alt="'+ prodName +'"></a>';
							fav_html +=	'<ul class="featured__item__pic__hover active">';
							fav_html +=	'<li><a href="##" class="favorcircle">';
							fav_html +=	'<i class="fa fa-heart favorheart active" id="'+ prodId + '" data="'+ prodId + '"></i></a></li>';
							fav_html +=	'<li>';
							fav_html +=	'<form action="/pages/product.controller" method="post">';
							fav_html +=	'<input type="hidden" name="prodaction" value="AddCartByPage">';
							fav_html +=	'<input type="hidden" name="memberId" value="'+ userId +'">';
							fav_html +=	'<input type="hidden" name="comTaxId" value="'+ comTaxId +'">';
							fav_html +=	'<input type="hidden" name="productId" value="'+ prodId + '">';
	                        fav_html +=	'<input type="hidden" name="quantity" value="1">';
	                        fav_html +=	'<input type="hidden" name="typeCode" value="'+ typeCode + '">';
	                        fav_html +=	'<input	type="hidden" name="styleCode" value="'+ styleCode + '">';
							fav_html +=	'<input type="hidden" name="price" value="'+ price + '">';
							fav_html +=	'<button type="submit" class="fa fa-shopping-bag mycart"></button>';
							fav_html +=	'</form>';
							fav_html +=	'</li></ul></div>';
							fav_html +=	'<div class="featured__item__text">';
							fav_html +=	'<h6><a	href="../homePage/productPage.jsp?productId='+ prodId + '"';
							fav_html +=	'class="results" target="_blank">'+ prodName +'</a>';
							fav_html +=	'</h6>';
							fav_html +=	'<h5>NT$ '+ price + '</h5>';
							fav_html +=	'</div></div>';
							
							$("div.productDisplay").append(fav_html);
							$("#" + item.prodId + "").addClass("active");
		            		$("#" + item.prodId + "").closest("ul").addClass("active");
		            		
			            });	   
		            	clickFavor(memId); //新增、移除收藏
		            },
		            error: function(xhr){
		            	console.log(xhr);
		            },
		            complete: function(xhr){
		            	console.log(xhr);
		            }
	    		});
	    	}
		});
	});
	
	
	function clickFavor(memId){
		//新增、移除收藏
	    $(".favorheart").on("click", function(e){
	    	if(memId == null || memId.length == 0){
	    		alert("請先登入會員");
	    	}else{
	    		let prodId = $(e.target).attr("data");
	    		console.log("prodId=" + prodId);
	    		$(e.target).toggleClass("active");
		        $(e.target).closest("ul").toggleClass("active");
		        
		        var favordata ={
		        		"memId": memId,
		        		"prodId": prodId
		        };
		        console.log(favordata);
		        let active = $(e.target).hasClass("active");
		        console.log("active=" + active);
		        if(active == true){ //新增收藏
		        	$.ajax({
			            url: "<%=request.getContextPath()%>/favorite/FavorServlet",
			            type: "POST",
			            data: JSON.stringify(favordata),
			            dataType: "json",
			            contentType: "application/json",
			            processData: false,
			            success: function(adddata){
			            	console.log("adddata=" + adddata);
			            	var count = $("#fav").text();
			            	console.log("count=" + count);
			            	console.log("countType=" + typeof count);
			            	if(count.length == 0){ //原先是空的的話不能parseInt
			            		count = 0;
			            	}
			            	var countFav = parseInt(count); //String 轉為 number
			            	$("#fav").text(countFav + 1);
			            	count = $("#fav").text(countFav + 1); //收藏+1
			     
			            	if(count.length > 0){ //有收藏時顯示數量
			            		$("span#fav").removeClass("down");
			            	}else{
			            		$("span#fav").addClass("down");
			            	}
			            },
			            error: function(xhr){
			            	console.log(xhr);
			            },
			            complete: function(xhr){
			            	console.log(xhr);
			            }
			        });
		        }else{ //移除收藏
		        	$.ajax({
		        		url: "<%=request.getContextPath()%>/favorite/FavorServlet",
		        		type: "DELETE",
		        		data: JSON.stringify(favordata),
		        		dataType: "json",
		        		contentType: "application/json",
		        		processData: false,
		        		success: function(deldata){
		        			console.log("deldata");
			            	console.log(deldata);
			            	var count = $("#fav").text();
			            	var countFav = parseInt(count); //String 轉為 number
			            	
			            	$("#fav").text(countFav - 1);
			            	
			            	if((countFav - 1) > 0){ //有收藏時顯示數量
			            		$("span#fav").removeClass("down");
			            	}else{
			            		$("span#fav").addClass("down");
			            	}
			            },
			            error: function(xhr){
			            	console.log(xhr);
			            },
			            complete: function(xhr){
			            	console.log(xhr);
			            }
		        	});
		        }
		        
	    	}
	        
	    });
	}
</script>