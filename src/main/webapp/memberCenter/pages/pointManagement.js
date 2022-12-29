//get order
$.ajax({
  url: "http://localhost:8080/oladesign/order",
  type: "GET",
  data: {memId:sessionStorage.getItem("memId")},
  dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
  success: function (data) {
    console.log(data);
    let list_html = "";
    $.each(data, function (index, item) {
      list_html += `
        <tr>
            <td>${formatDate(item.orderTime)}</td>
            <td>${item.orderId}</td>
            <td>＋　${item.pointGet}</td>
            <td>－　${item.pointUse}</td>
            
        </tr>
      `;
    });
    $("#tbody_listBody").html(list_html);
  },
  error: function (xhr) {
    console.log("error");
    console.log(xhr);
  },
});

//get totalPoint
$.ajax({
    url: "http://localhost:8080/oladesign/member",
    type: "GET",
    data: {memId:sessionStorage.getItem("memId")},
    dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
    success: function (data) {
      console.log(data);

      let list_html = `
        <tr>
            <th style="width: 15%;font-size: 20px; height:30px; ">現有紅利</th>
            <th style="font-size: 20px;">${data.point}</th>
        </tr>
        `;

      $("#thead_totalPoint").html(list_html);
    },
    error: function (xhr) {
      console.log("error");
      console.log(xhr);
    },
  });

  //set date format function
const formatDate = (dateStr) => {
    const date = new Date(dateStr);
    //若跳出錯誤訊息，則回傳預設格式
    if ("Invalid Date" === date.toString()) {
      return dateStr;
    }
  
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    const timeformat = `${year}-${month}-${day}`;
  
    return timeformat;
  };
