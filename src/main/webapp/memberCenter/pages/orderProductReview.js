let orderId = new URLSearchParams(location.search).get("orderId");
let alreadyReview;
$.ajax({
  url: "http://localhost:8080/oladesign/orderItem",
  type: "GET",
  data: { orderId: orderId },
  dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
  success: function (data) {
    console.log(data);
    let list_html = "";
    $.each(data, function (index, item) {
      
      if(!(item.commentStar===0 && item.comment===null)){
        alreadyReview=true;
      }

      list_html += `
        <tr>
          <td class="productId">${item.productId}</td>
          <td class="productName">${item.productName}</td>
          <td >
            <textarea rows="3" cols="50" class="comment"${alreadyReview?"readonly":""}>${item.comment===null?"":item.comment}</textarea>
          </td>
          <td>
            <div class="star_block" ${alreadyReview?"style='pointer-events:none'":""}>
              <span class="star ${item.commentStar >= 1 ? "-on" : ""}" data-star="1" ><i class="fas fa-star"></i></span>
              <span class="star ${item.commentStar >= 2 ? "-on" : ""}" data-star="2" ><i class="fas fa-star"></i></span>
              <span class="star ${item.commentStar >= 3 ? "-on" : ""}" data-star="3" ><i class="fas fa-star"></i></span>
              <span class="star ${item.commentStar >= 4 ? "-on" : ""}" data-star="4" ><i class="fas fa-star"></i></span>
              <span class="star ${item.commentStar >= 5 ? "-on" : ""}" data-star="5" ><i class="fas fa-star"></i></span>
            </div>
            
          </td>
          <td class=""><button class="submit" class="btn btn-default" ${alreadyReview?"hidden":""}>送出評論</button></td>
        </tr>
        `;
    });
    $("tbody#list").html(list_html);
  },
  error: function (xhr) {
    console.log("error");
    console.log(xhr);
  },
});

// ==== 星號 ===== //
$("tbody#list").on("click", "span.star", function (e) {
  let current_star = parseInt($(this).attr("data-star"));

  $(this)
    .closest("div.star_block")
    .find("span.star")
    .each(function (i, item) {
      if (parseInt($(this).attr("data-star")) <= current_star) {
        $(this).addClass("-on");
      } else {
        $(this).removeClass("-on");
      }
    });
});

//觸發送出新增按鍵
$("tbody#list").on("click", "button.submit", function () {
  const $thisRow = $(this).closest("tr");
  console.log($thisRow.find("div.star_block").find("span.-on").length);
  if($thisRow.find("div.star_block").find("span.-on").length===0){
    alert("請評分星等");
    return;
  }
  $.ajax({
    url: "http://localhost:8080/oladesign/productReview",
    type: "PUT",
    data: JSON.stringify({
      orderId: orderId,
      productId: $thisRow.find("td.productId").text(),
      comment: $thisRow.find("textarea.comment").val().trim(),
      commentStar: $thisRow.find("div.star_block").find("span.-on").length,
    }),
    dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
    contentType: "application/json; charset=UTF-8",
    success: function (data) {
      alert("已送出一筆評論")
      console.log(data);
      $thisRow.find("button.submit").hide();
      $thisRow.find("textarea.comment ").attr("readonly", true);
      $thisRow.find("div.star_block").css("pointer-events", "none");
    },
    error: function (xhr) {
      console.log("error");
      console.log(xhr);
    },
  });
});
