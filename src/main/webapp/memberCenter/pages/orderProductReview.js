$.ajax({
    url: "http://localhost:8080/oladesign/order",
    type: "GET",
    data: {},
    dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
    success: function (data) {
      console.log(data);
      let list_html = "";
      $.each(data, function (index, item) {
        list_html += `
        <td>1234567</td>
        <td>XXX美式地毯</td>
        <td class="">
          <textarea rows="3" cols="50">這是內容</textarea>
        </td>
        <td>
          <div class="star_block">
            ;
            <span
              class="star + ${item.star >= 1 ? " -on" : ""}+ ';"
              data-star="1"
              ><i class="fas fa-star"></i></span
            >;
            <span
              class="star + (item.star >= 2 ? ' -on'; : '') + ';"
              data-star="2"
              ><i class="fas fa-star"></i></span
            >;
            <span
              class="star + (item.star >= 3 ? ' -on'; : '') + ';"
              data-star="3"
              ><i class="fas fa-star"></i></span
            >;
            <span
              class="star + (item.star >= 4 ? ; -on'; : '') + ';"
              data-star="4"
              ><i class="fas fa-star"></i></span
            >;
            <span
              class="star + (item.star >= 5 ? ' -on'; : '') + ';"
              data-star="5"
              ><i class="fas fa-star"></i></span
            >;
          </div>
          
        </td>
        `;
      });
      $("tbody#order_list").html(list_html);
    },
    error: function (xhr) {
      console.log("error");
      console.log(xhr);
    },
  });