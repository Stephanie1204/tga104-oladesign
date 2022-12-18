var promoId = new URLSearchParams(location.search).get("promoId");
console.log(promoId);
const discountTypeTable = [
  {
    code: "P001",
    codeName: "單品降價",
  },
  {
    code: "P002",
    codeName: "單品打折",
  },
];

//專案資訊
$.ajax({
  url: "http://localhost:8080/oladesign/promo:promoId", // 資料請求的網址
  type: "GET", // GET | POST | PUT | DELETE | PATCH
  data: { promoId: promoId }, // 將物件資料(不用雙引號) 傳送到指定的 url
  dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
  success: function (data) {
    // request 成功取得回應後執行
    console.log(data);

    let list_html_head = "";

    list_html_head += "<tr>";
    list_html_head +=
      "    <td><span class='promoId_text'>" + data.promoId + "</span></td>";
    list_html_head += "    <td>" + data.promoName + "</td>";
    list_html_head += "    <td>" + data.startDate + "</td>";
    list_html_head += "    <td>" + data.endDate + "</td>";
    list_html_head += "    <td>" + data.coupon + "</td>";
    list_html_head += '    <td class="text-center"></td>';
    list_html_head +=
      '         <button type="button" class="btn bg-olive btn-xs" onclick=\'location.href="all-order-manage-edit.html"\'> 新增促銷商品 </button>';
    list_html_head += "    </td>";
    list_html_head += "</tr>";

    $("tbody.datalist_promo").html(list_html_head); //.html()會直接覆蓋原有內容
    console.log("enddd");
  },
  error: function (xhr) {
    // request 發生錯誤的話執行
    console.log("error");
    console.log(xhr);
  },
});

console.log(promoId);

//明細資訊
$.ajax({
  url: "http://localhost:8080/oladesign/promoItem",
  type: "GET",
  data: { promoId: promoId },
  dataType: "json",
  success: function (data) {
    console.log(data);

    let list_html = "";
    $.each(data, function (index, item) {
      list_html += `
        <tr>
        <td><span class="prodId_text">${item.prodId}</span> </td>
        <td><span class="prodName_text">${item.prodName}</span> </td>
        <td><span class="code_text">${item.code}</span></td>
        <td>
          <span class="codeName_text">${item.codeName}</span>
          <select class="codeName_input modify" value='${item.codeName}' />   
            <option value="P001"  ${
              item.codeName === "單品降價" ? 'selected="selected"' : ""
            }>單品降價</option>
            <option value="P002"  ${
              item.codeName === "單品打折" ? 'selected="selected"' : ""
            }>單品打折</option>
          </select>
        </td>
        <td>
          <span class="discount_text">${item.discount}</span>
          <input type='text' class="discount_input modify" value='${
            item.discount
          }' />
        </td>
        <td>${item.price}</td>
        <td>${item.stock}</td>
        <td>折扣後金額</td>
        <td>${formatDate(item.createTime)}</td>;
        <td>${formatDate(item.modifyTime)}</td>;
        <td class="text-center">
        <button type="button" class="btn bg-olive btn-xs" onclick='showBtn(this)' >編輯</button>
        <button type="button" class="btn bg-olive btn-xs" onclick='deleteData(this)' >刪除</button>

        <button type="button" class="btn bg-olive btn-xs modify" onclick="save(this)" >保存</button>
        <button type="button" class="btn bg-olive btn-xs modify" onclick="cancel(this)">取消</button>
        </td>
        </tr>
      `;
    });
    $("tbody.datalist_item").html(list_html);
    $(".modify").hide();
  },
  error: function (xhr) {
    // request 發生錯誤的話執行
    console.log("error");
    console.log(xhr);
  },
});

const cancel = (target) => {
  const $thisTableRow = $(target).closest("tr");
  $thisTableRow.find(".modify").hide();
};

const deleteData = (target) => {
  const $thisTableRow = $(target).closest("tr");

  $.ajax({
    url: "http://localhost:8080/oladesign/promoItem",
    type: "DELETE",
    data: {
      promoId: $("span.promoId_text").text(),
      prodId: $thisTableRow.find($("span.prodId_text")).text(),
    },
    dataType: "json",
    success: function (data) {
      console.log(data);
      alert("刪除成功");

      $thisTableRow.remove();
    },
    error: function (xhr) {
      // request 發生錯誤的話執行
      console.log("error");
      console.log(xhr);
    },
  });
};

const showBtn = (target) => {
  const $thisTableRow = $(target).closest("tr");
  $($thisTableRow).find(".modify").show();
};

const formatDate = (dateStr) => {
  const date = new Date(dateStr);
  if ("Invalid Date" === date.toString()) {
    return dateStr;
  }

  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();
  var hour = date.getHours();
  var minute = date.getMinutes();
  const timeformat = `${year}-${month}-${day} ${hour}:${minute}`;

  return timeformat;
};

const save = (target) => {
  const $thisTableRow = $(target).closest("tr");

  const codeName = $thisTableRow.find(".codeName_input").val();
  const discountType = discountTypeTable.find((type) => type.code === codeName);
  $thisTableRow.find(".code_text").html(discountType.code);
  $thisTableRow.find(".codeName_text").html(discountType.codeName);

  const discount = $thisTableRow.find(".discount_input").val();
  $thisTableRow.find(".discount_text").html(discount);

  $($thisTableRow).find(".modify").hide();

  $.ajax({
    url: "http://localhost:8080/oladesign/promoItem",
    type: "PUT",
    data: JSON.stringify({
      promoId: $("span.promoId_text").text(),
      prodId: $("span.prodId_text").text(),
      code: discountType.code,
      discount: discount,
    }),
    contentType: "application/json; charset=UTF-8",
    dataType: "json",
    success: function (data) {
      console.log(data);
    },
    error: function (xhr) {
      // request 發生錯誤的話執行
      console.log("error");
      console.log(xhr);
    },
  });
};

const setInvalid = () => {
  const check = confirm("確認將整筆促銷專案設為無效?");

  if (!check) {
    return;
  }

  $.ajax({
    url: "http://localhost:8080/oladesign/promo",
    type: "DELETE",
    data: { promoId: promoId },
    dataType: "json",
    success: function (data) {
      console.log(data);
      alert("已將此筆專案設為無效");
    },
    error: function (xhr) {
      // request 發生錯誤的話執行
      console.log("error");
      console.log(xhr);
    },
  });
};
