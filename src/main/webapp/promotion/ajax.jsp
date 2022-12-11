<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.6.1.min.js"
  integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
  crossorigin="anonymous"></script>
</head>
<body>
	<h1>hello index.jsp</h1>
	<button id="req" type="button">發請求</button>
	<script>
	
	$("#req").on("click", () => {
		$.ajax({
		  method: "POST",
		  url: "http://localhost:8080/tga104-g2-oladesign/AjaxServlet",
		  data: {promoId: "3345678"},
		  dataType: "json",
		  contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		  success: function(res){
		  	console.log(res);
		  },
		  error: function(res){
			console.log(res);
		  }
		});
	});

	</script>
</body>
</html>