var interMailId = new URLSearchParams(location.search).get("interMailId");

$.ajax({
    url: "http://localhost:8080/oladesign/interMail/:id",
    type: "GET",
    data: { interMailId:interMailId },
    dataType: "json", // 預期會接收到回傳資料的格式： json | xml | htm
  
    success: function (data) {
      console.log(data);
  
        let list_html = `
        
            <!-- text input -->
            <div class="form-group">
                <label>日期</label>
                <input
                type="text"
                class="form-control"
                value= "${formatDate(data.sentTime)}"
                disabled="disabled"
                />
            </div>
            <div class="form-group">
                <label>信件編號</label>
                <input
                type="text"
                class="form-control"
                value=${data.interMailId}
                disabled="disabled"
                />
            </div>
            <div class="form-group">
                <label>問題種類</label>
                <input
                type="text"
                class="form-control"
                value= ${code2CodeName(data.numQue, questionType)}
                disabled="disabled"
                />
            </div>

            <!-- textarea -->
            <div class="form-group">
                <label>信件內容</label>
                <textarea
                class="form-control"
                rows="3"
                disabled="disabled"
                >${data.conTent}</textarea>
            </div>

        `;
  
        $("form.list").html(list_html);

    },
    error: function (xhr) {
      console.log("error");
      console.log(xhr);
    },
  });

  //日期格式設定
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

  //信件種類對照
  const questionType = [
    { code: "1", codeName: "檢舉" },
    { code: "2", codeName: "疑難雜症" },
  ];

  //信件種類對照
  const code2CodeName = (code, types) => {
    const obj = types.find((type) => type.code === code);
    if (obj) {
      return obj.codeName;
    }
    return "";
  };