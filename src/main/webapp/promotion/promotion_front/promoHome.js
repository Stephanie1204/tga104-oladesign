// var comTaxId = new URLSearchParams(location.search).get("comTaxId");
// console.log(comTaxId);
// sessionStorage.setItem("comTaxId", comTaxId);
// console.log(sessionStorage.getItem("comTaxId"));

//get promo
$.ajax({
  url: "http://localhost:8080/oladesign/promo",
  type: "GET",
  data: { comTaxId: sessionStorage.getItem("comTaxId") },
  dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
  success: function (data) {
    console.log(data);

    let list_html = "";
    $.each(data, function (index, item) {
      list_html += "<tr>";
      list_html += '  <td class="promoId">' + item.promoId + "</td>";
      list_html += '  <td class="promoName">' + item.promoName + "</td>";
      list_html += '  <td class="start_date">' + item.startDate + "</td>";
      list_html += '  <td class="end_date">' + item.endDate + "</td>";
      list_html += '  <td class="coupon">' + item.coupon + "</td>";
      list_html += '  <td class="promo_status">' + code2CodeName(item.promoStatus,promoStatusType) + "</td>";
      list_html += '  <td class="text-center">';
      list_html +=
        '  <button type="button" class="btn bg-olive btn-xs edit_btn" onclick=\'location.href="editPromo.html?&promoId=' +
        item.promoId +
        "\"'> 編輯 </button>";
      list_html +=
        '  <button type="button" class="btn bg-olive btn-xs" onclick=\'location.href="promoList_add.html?promoId=' +
        item.promoId +
        "\"'> 查看明細</button>";
      list_html += "  </td>";
      list_html +=
        '  <td class="create_time">' + formatDate(item.createTime) + "</td>";
      list_html +=
        '  <td class="modify_time">' + formatDate(item.modifyTime) + "</td>";
      list_html += "</tr>";
    });
    $("tbody.promolist_tbody").html(list_html);
  },
  error: function (xhr) {
    console.log("error");
    console.log(xhr);
  },
});

//觸發新增促銷按鈕
$("button#addPromo_btn").on("click", function () {
  window.location.href = "./addPromo.html";
});

//set date format function
const formatDate = (dateStr) => {
  const date = new Date(dateStr);
  //若跳出錯誤訊息，則回傳預設格式
  if ("Invalid Date" === date.toString()) {
    return dateStr;
  }

  const year = date.getFullYear();
  const month = date.getMonth() + 1;
  const day = date.getDate();
  const hour = date.getHours();
  const minute = date.getMinutes();
  const second = date.getSeconds();
  const timeformat = `${year}-${month}-${day} ${hour}:${minute}:${second}`;

  return timeformat;
};


const promoStatusType = [
  { code: "PS001", codeName: "未開始" },
  { code: "PS002", codeName: "進行中" },
  { code: "PS003", codeName: "已失效" },
];

const code2CodeName = (code, types) => {
  const obj = types.find((type) => type.code === code);
  if (obj) {
    return obj.codeName;
  }
  return "";
};