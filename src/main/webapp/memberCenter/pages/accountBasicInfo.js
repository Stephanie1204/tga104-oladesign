let memId = new URLSearchParams(location.search).get("memId");
if (memId !== null) {
  sessionStorage.setItem("memId", memId);
  console.log(sessionStorage.getItem("memId"));
}
memId = sessionStorage.getItem("memId");

let memName;
let password;
let memPhone;
let memAddress;
let sex;
let point;
let isBan;
let isCom;
let memPhoto;
let isActive;
let isRegCom;

$.ajax({
  url: "http://localhost:8080/oladesign/member",
  type: "GET",
  data: { memId: memId },
  dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
  success: function (data) {
    console.log(data);
    console.log("success");

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
            <input type="file" id="member_photo" >
            <img id="preview_member_photo" width=50% src="${ data.memPhotoBase64 }">
        </div>
      
        `;

    $("div.box-body").html(list_html);

    password = data.password;
    sex = data.sex;
    point = data.point;
    isBan = data.isBan;
    isCom = data.isCom;
    memPhoto = data.memPhoto;
    memId = data.memId;
    isActive = data.isActive;
    isRegCom = data.isRegCom;
    memPhotoBase64 = data.memPhotoBase64;
  },
  error: function (xhr) {
    console.log("error");
    console.log(xhr);
  },
});

// {
//   "memId": 220021,
//   "memName": "絹雯",
//   "account": "muamua124@gmail.com",
//   "password": "20F645C703944A0027ACF6FAD92EC465247842450605C5406B50676FF0DCD5EA",
//   "memPhone": "0911111111",
//   "memAddress": "950臺東縣臺東市ddd",
//   "memRegdate": "2022-12-30",
//   "sex": "F",
//   "point": 0,
//   "isBan": false,
//   "isCom": true,
//   "isActive": true,
//   "isRegCom": false,
//   "memPhoto": null,
//   "memPhotoBase64": ""
// }

//送出修改
$("#btn_submit").on("click", function () {
  const formData = {
    memName: $("input#name").val().trim(),
    memPhone: $("input#phone").val().trim(),
    memAddress: $("input#address").val().trim(),
    memPhotoBase64: $("#preview_member_photo").attr("src"),

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
      alert("已完成修改");
      location.reload();
      //   window.location.href = `http://localhost:8080/oladesign/promotion/promotion_front/promoHome.html?comTaxId=${data.comTaxId}`;
    },
    error: function (xhr) {
      console.log("error");
      console.log(xhr);
    },
  });
});

//上傳圖片
$(document).on("change", "#member_photo", function () {
  //當檔案改變後，做一些事
  console.log(this);
  readURL(this); // this代表<input id="imgInp">
});

function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function (e) {
      $("#preview_member_photo").attr("src", e.target.result); //result是用 new FileReader() 讀進來的結果
    };
    reader.readAsDataURL(input.files[0]);//readAsDataURL方法會使用base-64進行編碼
  }
}
