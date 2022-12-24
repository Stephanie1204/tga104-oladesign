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
                <button type="button" class="btn bg-olive btn-xs" id="btn_order_detail">訂單詳情</button>
                <button type="button" class="btn bg-olive btn-xs writeReview">填寫評價</button>
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