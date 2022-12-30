$("#btn_submit").on("click", function () {
  const formData = {
    originPwd: $("#originPwd").val(),
    newPwd1: $("#newPwd1").val(),
    newPwd2: $("#newPwd2").val(),
  };
  $.ajax({
    url: "http://localhost:8080/oladesign/member/password",
    type: "PUT",
    data: formData,
    dataType: "json",
    success: function (data) {
      if (data === true) {
        alert("密碼更新成功");
        window.location.href="../../member/login.jsp"
        sessionStorage.removeItem("memId");
      } else {
        alert("密碼重置失敗");
      }
    },
    error: function (xhr) {
      console.log("error");
      console.log(xhr);
      alert("請重新輸入正確密碼");
    },
  });
});
