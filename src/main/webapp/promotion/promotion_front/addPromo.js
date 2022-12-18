// while click submit button
$("button#submit").on("click", function () {
  // set and trim added info into a form_data variable
  const formData = {
    promoName: $("input.promoName").val().trim(),
    startDate: $("input.start_date").val().trim(),
    endDate: $("input.end_date").val().trim(),
    coupon: $("input.coupon").val().trim(),
  };

  $.ajax({
    url: "http://localhost:8080/oladesign/promo", // 資料請求的網址
    type: "POST", // GET | POST | PUT | DELETE | PATCH
    data: JSON.stringify(formData),
    dataType: "json",
    contentType: "application/json; charset=UTF-8",

    success: function (data) {
      // request 成功取得回應後執行
      alert("success ");
      window.location.href = `http://localhost:8080/oladesign/promotion/promotion_front/promoHome.html?comTaxId=${data.comTaxId}`;
    },
    error: function (xhr) {
      // request 發生錯誤的話執行
      console.log("error");
      console.log(xhr);
    },
  });
});
