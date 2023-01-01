var promoId = new URLSearchParams(location.search).get("promoId");
console.log(promoId);
let promoStatus;


// get original promo
$.ajax({
  method: "GET",
  url: "http://localhost:8080/oladesign/promo:promoId",
  data: { promoId: promoId },
  dataType: "json",
  contentType: "application/json; charset=UTF-8",
  success: function (res) {
    console.log("success");
  promoStatus = res.promoStatus;

    let form_html = `
      <label>專案名稱 ： <input  class="promoName" type="text" value="${res.promoName}"></label><br>
      <label> 折 扣 碼 ：  
        <input id="coupon_input" type="text" value="${res.coupon}"> 
        <button type="button" id="checkCoupon"> 檢查</button>
        <span id="check_false" style="color:red" hidden> 此折扣碼已使用過，請重新輸入</span>
        <span id="check_pass" style="color:blue" hidden> 可使用的折扣碼</span>
      </label><br>
      <label>開始日期 ：</label> <input class="start_date" type="date" value="${res.startDate}"><br>
      <label>結束日期 ：</label> <input class="end_date" type="date" value="${res.endDate}"><br>
      <label>限定可使用會員ID : </label> <input class="limitMemberId" type="text">
      <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
        <thead>
          <tr>
            <th class="sorting">可使用會員明細</th> 
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>xxx</td> <td><input type="submit" value="移除"></input></td>
          </tr>
        </tbody>
      </table> `;

    $("div.edit_form").html(form_html);
  },
  error: function () {
    alert("errror");
  },
});

// // while click submit button
// $("button#submit").on("click", function () {
//   if (new Date($("input.start_date").val().trim()) < new Date()) {
//     alert("開始日期不可小於今日");
//     return;
//   }
//   if ($("input.end_date").val().trim() < $("input.start_date").val().trim()) {
//     alert("結束日期不可小於開始日期");
//     return;
//   }

//   // set and trim added info into a form_data variable
//   const formData = {
//     promoName: $("input.promoName").val().trim(),
//     startDate: $("input.start_date").val().trim(),
//     endDate: $("input.end_date").val().trim(),
//     coupon: $("input#coupon_input").val().trim(),
//     promoStatus: promoStatus,
//   };

//   $.ajax({
//     url: "http://localhost:8080/oladesign/promo",
//     type: "PUT",
//     data: JSON.stringify(formData),
//     dataType: "json",
//     contentType: "application/json; charset=UTF-8",

//     success: function (data) {
//       alert("success ");
//       window.location.href = `http://localhost:8080/oladesign/promotion/promotion_front/promoHome.html`;
//     },
//     error: function (xhr) {
//       console.log("error");
//       console.log(xhr);
//     },
//   });
// });

//檢查折扣碼
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

// 送出修改
$("#submit_btn").on("click", function () {
  if (new Date($("input.start_date").val().trim()) < new Date()) {
    alert("開始日期不可小於今日");
    return;
  }
  if ($("input.end_date").val().trim() < $("input.start_date").val().trim()) {
    alert("結束日期不可小於開始日期");
    return;
  } 

  const formData = {
    promoName: $("input.promoName").val(),
    startDate: $("input.start_date").val(),
    endDate: $("input.end_date").val(),
    coupon: $("input#coupon_input").val(),
    promoStatus: promoStatus,
    promoId:promoId,
  };

  $.ajax({
    method: "PUT",
    url: "http://localhost:8080/oladesign/promo",
    data: JSON.stringify(formData),
    dataType: "json",
    contentType: "application/json; charset=UTF-8",
    success: function (data) {
      console.log(data);
      console.log("success");
      if(data === true){
        window.location.href =
        "http://localhost:8080/oladesign/promotion/promotion_front/promoHome.html";
      }else if(data === false){
        alert("修改失敗");
      }
    },
    error: function (res) {
      console.log("error");
      alert("errror");
    },
  });
});
