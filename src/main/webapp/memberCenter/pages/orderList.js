//get order
$.ajax({
  url: "http://localhost:8080/oladesign/order",
  type: "GET",
  data: {},
  dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
  success: function (data) {
    console.log(data);
    let list_html = "";
    $.each(data, function (index, item) {
      list_html += `
      <tr>
          <td>${item.orderTime}</td>
          <td><span class="get_orderId">${item.orderId}</span></td>
          <td>${item.comTaxId}</td>
          <td>${item.amount}</td>
          <td class="orderStatus">${item.orderStatus}</td>
          <td class="shippingStatus">${item.shippingStatus}</td>
          <td class="text-center">
              <button type="button" class="btn bg-olive btn-xs btn_order_detail">訂單詳情</button>
              <button type="button" class="btn bg-olive btn-xs btn_writeReview">填寫評價</button>
          </td>
      </tr>
      `;
    });
    $("tbody#order_list").html(list_html);
  },
  error: function (xhr) {
    console.log("error");
    console.log(xhr);
  },
});

//觸發填寫評價按鈕
$(document).on("click", $("button.btn_writeReview"), function () {
  let $thisTableRow = $(this).closest("tr");
  // console.log($thisTableRow);
  const orderId = $thisTableRow.find(".get_orderId").text();
  console.log(orderId);
  window.location.href="./orderProductReview.html?orderId="+orderId;
});




//觸發專案明細按鈕
$(document).on("click", $("button.btn_order_detail"), function () {
  let $thisTableRow = $(this).closest("tr");
  // console.log($thisTableRow);
  const orderId = $thisTableRow.find(".get_orderId").text();
  console.log(orderId);
  window.location.href="./orderDetail.html?orderId="+orderId;
});




const orderStatusType = [
  { code: 0, codeName: "待確認" },
  { code: 1, codeName: "已成立" },
  { code: 2, codeName: "已取消" },
];

// const shippingStatusType = [
//   { code: 0, codeName: "待確認" },
//   { code: 1, codeName: "待出貨" },
//   { code: 2, codeName: "已取消" },
//   { code: 3, codeName: "已出貨" },
//   { code: 4, codeName: "運送中" },
//   { code: 5, codeName: "已送達" },
// ];


// // let input = 1; //$("td.orderStatus").val();
// // (obj)=>obj.code === input;

// const orderstatus_check=(code)=>{
//   if(code === orderStatusType.code){
// return orderStatusType.codeName;
//   }
// }

// orderstatus_check(0);

