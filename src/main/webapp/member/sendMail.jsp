<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>OLA Design | 信箱驗證</title>

    <!-- Google Font -->
    <link
      href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
      rel="stylesheet"
    />
    <!-- Font Awesome -->
    <script
      src="https://kit.fontawesome.com/51435664f6.js"
      crossorigin="anonymous"
    ></script>
    <!-- icheck bootstrap -->
    <link rel="stylesheet" href="../css/icheck-bootstrap.min.css" />
    <!-- Theme style -->
    <link rel="stylesheet" href="../css/adminlte.min.css" />
    <link rel="stylesheet" href="../css/sendMail.css" />
  </head>
  <body class="hold-transition login-page">
  	<div class="backtoindex">
		<a href="<%=request.getContextPath()%>/homePage/index.jsp" class="index"><span class="back">回首頁</span></a>
	</div>
    <div class="login-box">
      <div class="card card-outline">
        <div class="card-header text-center">
          <h1 class="reset">驗證您的信箱</h1>
        </div>
        <div class="card-body">
          <p class="login-box-msg">
            我們已發送驗證信至您的信箱，請點選信件中的連結完成驗證。
          </p>
          	<div class="error text-center">
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: #E86F62">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
				<div class="havesend" style="color: #57cc99">${haveSend}</div>
          <form method="post" action="<%=request.getContextPath()%>/member/MemberRegist">
            <div class="input-group mb-3">
              <input type="email" class="form-control" placeholder="Email" name="account" />
              <div class="input-group-append">
                <div class="input-group-text">
                  <span class="fas fa-envelope"></span>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-12">
              	<input type="hidden" name="action" value="resend">
                <button type="submit" class="btn btn-primary btn-block">
                  重新發送驗證信
                </button>
              </div>
              <!-- /.col -->
            </div>
          </form>
        </div>
        <!-- /.login-card-body -->
      </div>
    </div>
    <!-- /.login-box -->
  </body>
</html>
