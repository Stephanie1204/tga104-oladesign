$.ajax({
  url: "http://localhost:8080/oladesign/interMail",
  type: "GET",
  data: { memId: sessionStorage.getItem("memId") },
  dataType: "json", // 預期會接收到回傳資料的格式： json | xml | htm

  success: function (data) {
    console.log(data);
    console.log("success");

    let list_html = "";
    $.each(data, function (index, item) {
      list_html += `

          <tr>
              <td>客服回覆 / 信件寄送</td>
              <td>${formatDate(item.sentTime)}</td>
              <td>${item.interMailId}</td>
              <td>${item.type}</td>
              <td style="max-width: 420px; overflow: hidden; text-overflow: ellipsis;">${item.conTent}</td>
              <td class="text-center">
                  <button type="button" class="btn bg-olive btn-xs" onclick='location.href="./interMailDetail.html?interMailId=${item.interMailId}"'>詳情</button>
              </td>
          </tr>
            `;

      $("#list").html(list_html);
    });
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

  const year = date.getFullYear();
  const month = date.getMonth() + 1;
  const day = date.getDate();
  const hour = date.getHours();
  const minute = date.getMinutes();
  const second = date.getSeconds();
  const timeformat = `${year}-${month}-${day} ${hour}:${minute}:${second}`;

  return timeformat;
};