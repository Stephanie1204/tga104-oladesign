const oredrStatusType=[
    {  code:0 , codeName:"待確認"},
    {  code:1 , codeName:"已成立"},
    {  code:2 , codeName:"已取消"},
]

const shippingStatusType=[
    {  code:0 , codeName:"待確認"},
    {  code:1 , codeName:"待出貨"},
    {  code:2 , codeName:"已取消"},
    {  code:3 , codeName:"已出貨"},
    {  code:4 , codeName:"運送中"},
    {  code:5 , codeName:"已送達"},
]

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
          <td>${item.orderId}</td>
          <td>${item.comTaxId}</td>
          <td>${item.amount}</td>
          <td>${item.orderStatus}</td>
          <td>${item.shippingStatus}</td>
          <td class="text-center">
              <button type="button" class="btn bg-olive btn-xs" onclick='location.href="all-order-manage-edit.html"' id="btn_order_detail">訂單詳情</button>
              <button type="button" class="btn bg-olive btn-xs" onclick='location.href="all-order-manage-edit.html"'>填寫評價</button>
          </td>
      </tr>
      `
    });
    $("tbody#order_list").html(list_html);
  },
  error: function (xhr) {
    console.log("error");
    console.log(xhr);
  },
});

// $(document).on("click","#btn_order_detail",function(target){

//   let $thisTableRow = $(target).closest("tr");

//   $.ajax({
//     url: "http://localhost:8080/oladesign/promoItem",
//     type: "PUT",
//     data: JSON.stringify({
//       promoId: $("span.promoId_text").text(),
//       prodId: $("span.prodId_text").text(),
//       code: discountType.code,
//       discount: discount,
//     }),
//     contentType: "application/json; charset=UTF-8",
//     dataType: "json",
//     success: function (data) {
//       console.log(data);
//     },
//     error: function (xhr) {
//       // request 發生錯誤的話執行
//       console.log("error");
//       console.log(xhr);
//     },
//   });


// })



