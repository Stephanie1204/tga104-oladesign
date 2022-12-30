$("#profile").on("click",function(){

    window.location.href="./accountBasicInfo.html?memId="+sessionStorage.getItem("memId");
})

$.ajax({
    url: "http://localhost:8080/oladesign/member",
    type: "GET",
    data: { memId: sessionStorage.getItem("memId") },
    dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
    success: function (data) {
      console.log(data.memName);
      console.log("success");
  
      let list_html = `
      <p>${data.memName}</p>
      <a href="#"><i class="fa fa-circle text-success"></i> 線上</a>
          `;
      $("#memberName").html(list_html);
  
    
    },
    error: function (xhr) {
      console.log("error");
      console.log(xhr);
    },
});
