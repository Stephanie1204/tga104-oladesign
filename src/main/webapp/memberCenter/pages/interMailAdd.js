$("#btn_submit").on("click", function () {
  $.ajax({
    url: "http://localhost:8080/oladesign/interMail",
    type: "POST",
    data: {
      memId: sessionStorage.getItem("memId"),
      numQue: $("#type").val(),
      conTent: $("#content").val(),
    },
    dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
    success: function (data) {
      alert("信件發送成功");
      window.location.href = "./interMail.html";
    },
    error: function (request, error) {
      console.log("error");
      //   console.log(xhr);
      alert(" Can't do because: " + error);
    },
  });
});
