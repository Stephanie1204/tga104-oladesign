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
            <img src="data:image/jpeg;base64,${data.memPhotoBase64}">
            <label for="exampleInputFile">個人圖像上傳</label>
            <input type="file" id="exampleInputFile">
            <p class="help-block">Example block-level help text here.</p>
        </div>
        <div class="col-md-2 title rowHeight2x">圖片上傳</div>
        <div class="col-md-10 data" style="height: 320px">
          <input type="file" name="img_file" accept="image/*" class="upl">
          <img class="preview" style="max-width: 150px; max-height: 150px;">
        </div>
        `;

    $("div.box-body").html(list_html);

    memName = data.memName;
    password = data.password;
    memPhone = data.memPhone;
    memAddress = data.memAddress;
    sex = data.sex;
    point = data.point;
    isBan = data.isBan;
    isCom = data.isCom;
    memPhoto = data.memPhotoBase64;
    memId = data.memId;
  },
  error: function (xhr) {
    console.log("error");
    console.log(xhr);
  },
});

$("#btn_submit").on("click", function () {
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
