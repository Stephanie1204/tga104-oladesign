// $.ajax({
//     url: "http://localhost:8080/oladesign/promo", // 資料請求的網址
//     type: "GET", // GET | POST | PUT | DELETE | PATCH
//     data: { comTaxId: comTaxId }, // 將物件資料(不用雙引號) 傳送到指定的 url
//     dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
//     success: function (data) {
//       // request 成功取得回應後執行
//       console.log(data);
  
//       let list_html = "";
//       $.each(data, function (index, item) {
//         list_html += "<tr>";
//         list_html += '  <td class="promoId">' + item.promoId + "</td>";
//         list_html += '  <td class="promoName">' + item.promoName + "</td>";
//         list_html += '  <td class="start_date">' + item.startDate + "</td>";
//         list_html += '  <td class="end_date">' + item.endDate + "</td>";
//         list_html += '  <td class="coupon">' + item.coupon + "</td>";
//         list_html += '  <td class="promo_status">' + item.promoStatus + "</td>";
//         list_html += '  <td class="limit">是/否</td>';
//         list_html += '  <td class="text-center">';
//         list_html +=
//           '  <button type="button" class="btn bg-olive btn-xs edit_btn" onclick=\'location.href="editPromo.html?&promoId=' +
//           item.promoId +
//           "\"'> 編輯 </button>";
//         list_html +=
//           '  <button type="button" class="btn bg-olive btn-xs" onclick=\'location.href="promoList_add.html?&promoId=' +
//           item.promoId +
//           "\"'> 查看明細</button>";
//         list_html += "  </td>";
//         list_html += '  <td class="create_time">' + item.createTime + "</td>";
//         list_html += '  <td class="modify_time">' + item.modifyTime + "</td>";
//         list_html += "</tr>";
//       });
//       $("tbody.promolist_tbody").html(list_html);
//     },
//     error: function (xhr) {
//       console.log("error");
//       console.log(xhr);
//     },
//   });