var memId = new URLSearchParams(location.search).get("memId");
sessionStorage.setItem("memId",memId);
console.log(sessionStorage.getItem("memId"));

let memName;
let password;
let memPhone;
let memAddress;
let sex;
let point;
let isBan;
let isCom;
let memPhoto;

$.ajax({
  url: "http://localhost:8080/oladesign/member", // 資料請求的網址
  type: "GET", // GET | POST | PUT | DELETE | PATCH
  data: {memId:memId}, // 將物件資料(不用雙引號) 傳送到指定的 url
  dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
  success: function (data) {
    // request 成功取得回應後執行
    console.log(data);
    alert("success");

    let list_html = `
        <div class="form-group">
            <label for="exampleInputEmail1">帳號</label>
            <input type="email" class="form-control" id="exampleInputEmail1" value="${
              data.account
            }" readonly> 
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">姓名</label>
            <input type="text" class="form-control" id="name" value="${
              data.memName
            }">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">手機</label>
            <input type="text" class="form-control" id="phone" value="${
              data.memPhone
            }">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">地址</label>
            <input type="text" class="form-control" id="address" value="${
              data.memAddress
            }">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">性別 </label> 
            <input type="radio" name="gender" value="女"  ${
              data.sex === "F" ? 'checked="checked"' : "disabled"
            }> 女
            <input type="radio" name="gender" value="男"  ${
              data.sex === "M" ? 'checked="checked"' : "disabled"
            }> 男
        </div>
        <div class="form-group">
            <label for="exampleInputFile">個人圖像上傳</label>
            <input type="file" id="exampleInputFile">
            <p class="help-block">Example block-level help text here.</p>
        </div>`;

    $("div.box-body").html(list_html);

    memName = data.memName;
    password = data.password;
    memPhone = data.memPhone;
    memAddress = data.memAddress;
    sex = data.sex;
    point = data.point;
    isBan = data.isBan;
    isCom = data.isCom;
    memPhoto = data.memPhoto;
    memId = data.memId;
  },
  error: function (xhr) {
    console.log("error");
    console.log(xhr);
  },
});

$("button#btn_submit").on("click", function () {
  const formData = {
    memName: $("input#name").val().trim(),
    memPhone: $("input#phone").val().trim(),
    memAddress: $("input#address").val().trim(),
    memPhoto: $("input#exampleInputFile").val().trim(),

    // can not be change by user
    password: password,
    sex: sex,
    point: point,
    isBan: isBan,
    isCom: isCom,
    memId: memId,
  };
  $.ajax({
    url: "http://localhost:8080/oladesign/member",
    type: "PUT",
    data: JSON.stringify(formData),
    dataType: "json",
    contentType: "application/json; charset=UTF-8",

    success: function (data) {
      alert("success ");
      location.reload();
      //   window.location.href = `http://localhost:8080/oladesign/promotion/promotion_front/promoHome.html?comTaxId=${data.comTaxId}`;
    },
    error: function (xhr) {
      console.log("error");
      console.log(xhr);
    },
  });
});
