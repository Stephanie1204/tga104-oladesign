var comTaxId = '<%= Session["comTaxId"] %>';
console.log(comTaxId);

// set and trim added info into a form_data variable
let form_data = {
    comTaxId:comTaxId,
    promoName:($("input.promoName").val()).trim(),
    startDate:($("input.start_date").val()).trim(),
    endDate:($("input.end_date").val()).trim(),
    coupon:($("input.coupon").val()).trim()
};

// while click submit button
$("button#submit").on("click",function(){
    $.ajax({
        url: "http://localhost:8080/oladesign/promo",           // 資料請求的網址
        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
        data:form_data,          // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
        success: function(data){      // request 成功取得回應後執行
            alert("success ")
         window.location.href = "http://localhost:8080/oladesign/promo?comTaxId=${data.comTaxId}"; 
        },
        error: function(xhr){         // request 發生錯誤的話執行
          console.log("error");
          console.log(xhr);
        }
      });

})

