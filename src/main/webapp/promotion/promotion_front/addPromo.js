// 送出新增
$("button#submit").on("click", function () {
  if (new Date($("input.start_date").val().trim()) < new Date()) {
    alert("開始日期不可小於今日");
    return;
  }
  if ($("input.end_date").val().trim() < $("input.start_date").val().trim()) {
    alert("結束日期不可小於開始日期");
    return;
  }

  // set and trim added info into a form_data variable
  const formData = {
    comTaxId: sessionStorage.getItem("comTaxId"),
    promoName: $("input.promoName").val().trim(),
    startDate: $("input.start_date").val().trim(),
    endDate: $("input.end_date").val().trim(),
    coupon: $("input#coupon_input").val().trim(),
  };

  $.ajax({
    url: "http://localhost:8080/oladesign/promo",
    type: "POST",
    data: JSON.stringify(formData),
    dataType: "json",
    contentType: "application/json; charset=UTF-8",

    success: function (data) {
      console.log("success ");
      window.location.href = `http://localhost:8080/oladesign/promotion/promotion_front/promoHome.html`;
    },
    error: function (xhr) {
      console.log("error");
      console.log(xhr);
      console.log(sessionStorage.getItem("comTaxId"));
    },
  });
});

//檢查coupon是否重複
$("button#checkCoupon").on("click", function () {
  $.ajax({
    url: "http://localhost:8080/oladesign/promo:coupon",
    type: "GET",
    data: { coupon: $("input#coupon_input").val() },
    contentType: "application/json; charset=UTF-8",
    dataType: "json",
    success: function (data) {
      if (data === true) {
        $("span#check_pass").show();
        $("span#check_false").hide();
      }
      if (data === false) {
        $("span#check_pass").hide();
        $("span#check_false").show();
      }
    },
    error: function (xhr) {
      console.log("error");
      console.log(xhr);
      $("span#check_false").show();
    },
  });
});

// function of get chinese byte
String.prototype.len = function () {
  return this.replace(/[^\x00-\xff]/g, "xx").length;
};

//檢查專案名稱字數
$("input.promoName").on("keyup", function () {
  if ($("input.promoName").val().len() > 12) {
    $("span#promoName_alert").show();
  } else {
    $("span#promoName_alert").hide();
  }
});

//檢查coupon字數
$("input#coupon_input").on("keyup", function () {
  if ($("input#coupon_input").val().len() > 8) {
    $("span#coupon_alert").show();
  } else {
    $("span#coupon_alert").hide();
  }
});

//btn_取消新增
$("button#cancel_btn").on("click", function () {
  confirm("確認放棄新增?");
  if (true) {
    window.history.back();
  }
});
