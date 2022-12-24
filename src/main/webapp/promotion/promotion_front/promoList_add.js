var promoId = new URLSearchParams(location.search).get("promoId");
console.log(promoId);
const discountTypeTable = [
  { code: "P001", codeName: "單品降價" },
  { code: "P002", codeName: "單品打折" },
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
          <td>
            <span class="prodId_text">${item.prodId}</span> 
          </td>
          <td>
            <span class="prodName_text">${item.prodName}</span> 
          </td>
          <td>
            <span class="code_text">${item.code}</span>
          </td>
          <td>
            <span class="codeName_text">${item.codeName}</span>
            <select class="codeName_input modify" value='${item.code}' >   
              <option value="P001"  ${
                item.code === "P001" ? 'selected="selected"' : ""
              }>單品降價</option>
              <option value="P002"  ${
                item.code === "P002" ? 'selected="selected"' : ""
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
          <td id="td_discount_price">${calcDiscountPrice(item.code, item.discount, item.price)}</td>
          <td>${formatDate(item.createTime)}</td>;
          <td>${formatDate(item.modifyTime)}</td>;
          <td class="text-center">
            <button type="button" class="btn bg-olive btn-xs" onclick='showBtn(this)' > 編輯 </button>
            <button type="button" class="btn bg-olive btn-xs" onclick='deleteData(this)' > 刪除 </button>

            <button type="button" class="btn bg-olive btn-xs modify" onclick="save(this)" > 保存 </button>
            <button type="button" class="btn bg-olive btn-xs modify" onclick="cancel(this)"> 取消 </button>
          </td>
        </tr>
      `;
    });
    $("tbody.datalist_item").html(list_html);
    $(".modify").hide();
  },
  error: function (xhr) {
    console.log("error");
    console.log(xhr);
  },
});

const calcDiscountPrice = (code, discount, price) => {
  if(code==="P001"){
    return price-discount;
  }else if(code==="P002"){
    return Math.ceil(price*discount/100);
  } else {
    return price;
  }
}

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

  const code = $thisTableRow.find(".codeName_input").val();
  const discountType = discountTypeTable.find((type) => type.code === code);
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

//新增促銷明細
$("button#addItem").on("click", function () {
  let list_html = `
    <tr>
      <td><input class="create_item" id="create_prodId" style="width: 100%" ></input></td> <!--商品ID-->
      <td>
        <input class="create_item" id="create_prodName"></input>
        <span id="check_product" hidden style="color:red">查無此商品</span>
      </td> <!--商品名稱-->
      <td><input class="create_item" id="create_code" style="width: 100px" readonly ></input></td> <!--促銷種類代碼-->
      <td> 
        <select class="create_item" id="create_codeName" style="width: 100px" >
          <option value="P001" >單品降價</option>
          <option value="P002" >單品打折</option>
        </select>
      </td> <!--促銷種類名稱-->
      <td>
        <input class="create_item" id="create_discount" style="width: 80px"></input>
        <span id="check_discount" hidden style="color:red">請重新確認折扣金額</span> 
      </td> <!--折扣程度-->
      <td><input class="create_item" id="create_price" style="width: 100px" readonly></input></td> <!--原價-->
      <td><input class="create_item" id="create_stock" style="width: 80px" readonly></input></td> <!--庫存-->
      <td><input class="create_item" id="create_discountPrice" style="width: 100px" readonly></input></td></td> <!--折扣後金額-->
      <td></td> <!--建立日期-->
      <td></td>;<!--修改日期-->
      <td class="text-center">
        <button type="button" class="btn bg-olive btn-xs modify" id="btn_save">保存</button>
        <button type="button" class="btn bg-olive btn-xs modify" id="btn_cancel">取消</button>
      </td>
    </tr>
  `;
  $("tbody.datalist_item").append(list_html);
});

//計算折扣價格
const show_promo_price = () => {
  if ($("#item.codeName").val() === "P001") {
    var disprice = $("#item.price").val() - $("#item.discount").val();
    $("#td_discount_price").html(disprice);
  } else if ($("#item.codeName").val() === "P002") {
    var disprice = Math.ceil(
      ($("#item.price").val() * $("#item.discount").val()) / 100
    );
    $("#td_discount_price").html(disprice);
  }
};

//自動帶出促銷種類編號
$(document).on("click", "#create_codeName", function () {
  console.log(this);
  if ($(create_codeName).val() === "P001") {
    $("input#create_code").attr("value", "P001");
  } else if ($(create_codeName).val() === "P002") {
    $("input#create_code").attr("value", "P002");
  }
});

//檢查折扣
$(document).on("keyup", "#create_discount", function () {
  if ($("#create_codeName").val() === "P001") {
    if (
      $("#create_discount").val() <= 0 ||
      Number.parseInt($("#create_discount").val()) >=
        Number.parseInt($("#create_price").val())
    ) {
      $("#check_discount").show();
    } else {
      $("#check_discount").hide();
      var disprice = $("#create_price").val() - $("#create_discount").val();
      $("#create_discountPrice").attr("value", disprice);
    }
  } else if ($("#create_codeName").val() === "P002") {
    if (
      $("#create_discount").val() <= 0 ||
      $("#create_discount").val() >= 100
    ) {
      $("#check_discount").show();
    } else {
      $("#check_discount").hide();
      var disprice = Math.ceil(
        ($("#create_price").val() * $("#create_discount").val()) / 100
      );
      $("#create_discountPrice").attr("value", disprice);
    }
  }
});

//自動帶出商品名稱、價格、庫存
$(document).on("keyup", "#create_prodId", function () {
  $.ajax({
    url: "http://localhost:8080/oladesign/product",
    type: "GET",
    data: { productId: $("input#create_prodId").val().trim() },
    contentType: "application/json; charset=UTF-8",
    dataType: "json",
    success: function (data) {
      console.log(data);
      if (data.comTaxId === sessionStorage.getItem("comTaxId")) {
        $("#create_prodName").attr("value", data.name);
        $("#create_price").attr("value", data.price);
        $("#create_stock").attr("value", data.stock);
        $("#check_product").hide();
      } else {
        $("#check_product").show();
      }
    },
    error: function (xhr) {
      console.log("error");
      console.log(xhr);
    },
  });
});

$(document).on("click", "#btn_save", function () {
  const formData = {
    promoId: promoId,
    prodId: $("input#create_prodId").val().trim(),
    code: $("input#create_code").val().trim(),
    discount: $("#create_discount").val().trim(),
  };
  $.ajax({
    url: "http://localhost:8080/oladesign/promoItem",
    type: "POST",
    data: JSON.stringify(formData),
    dataType: "json",
    contentType: "application/json; charset=UTF-8",

    success: function (data) {
      alert("success");
      window.location.href =
        "http://localhost:8080/oladesign/promotion/promotion_front/promoList_add.html?promoId=" +
        promoId;
    },
    error: function (xhr) {
      console.log("error");
      console.log(xhr);
    },
  });
});

$(document).on("click", "#btn_cancel", function () {
  window.location.reload();
})

