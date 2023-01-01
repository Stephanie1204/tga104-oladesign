// $("#profile").on("click",function(){

//     window.location.href="./accountBasicInfo.html?memId="+sessionStorage.getItem("memId");
// })

$(window).on("load", () => {
  loadMemberInfo();
});

const loadMemberInfo = () => {
  $.ajax({
    url: "http://localhost:8080/oladesign/member",
    type: "GET",
    data: { memId: sessionStorage.getItem("memId") },
    dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
    success: function (data) {
      console.log(data.memName);
      console.log("success to get memName");

      let list_html = `
      <p>${data.memName}</p>
      <a href="#"><i class="fa fa-circle text-success"></i> 線上</a>
          `;
      $("#memberName").html(list_html);

      let list_html2 = `<img src="${data.memPhotoBase64}" class="img-circle" alt="User Image"> `;
      $("div.image").html(list_html2);
    },
    error: function (xhr) {
      console.log("error");
      console.log(xhr);
    },
  });
};
