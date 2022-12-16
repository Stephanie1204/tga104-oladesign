var promoId = new URLSearchParams(location.search).get('promoId');
console.log(promoId);

//專案資訊
// $.ajax({
//     url: "http://localhost:8080/oladesign/promo:promoId",           // 資料請求的網址
//     type: "GET",                  // GET | POST | PUT | DELETE | PATCH
//     data:{promoId:promoId},          // 將物件資料(不用雙引號) 傳送到指定的 url
//     dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
//     success: function(data){      // request 成功取得回應後執行
//       console.log(data);

//       let list_html_head="";

//        list_html_head += '<tr>';
//        list_html_head += '    <td>' +data.promoId+ '</td>';
//        list_html_head += '    <td>' +data.promoName+ '</td>';
//        list_html_head += '    <td>' +data.promoStartDate+ '</td>';
//        list_html_head += '    <td>' +data.promoEndDate+ '</td>';
//        list_html_head += '    <td>' +data.coupon+ '</td>';
//        list_html_head += '    <td class="text-center"></td>';
//        list_html_head += '         <button type="button" class="btn bg-olive btn-xs" onclick=\'location.href="all-order-manage-edit.html"\'> 新增促銷商品 </button>';
//        list_html_head += '    </td>';
//        list_html_head += '</tr>';

//        $("tbody.datalist_promo").html(list_html_head); //.html()會直接覆蓋原有內容
//        console.log("enddd");

//     },
//     error: function(xhr){         // request 發生錯誤的話執行
//       console.log("error");
//       console.log(xhr);
//     }
//   });


  console.log(promoId);


  //明細資訊
  $.ajax({
    url: "http://localhost:8080/oladesign/promoItem",    
    type: "GET",                  
    data:{promoId: '1'},      
    dataType: "json",  
    success: function(data){      
      console.log(data);

      let list_html="";
      $.each(data, function(index, item) {
      
        list_html +=  '<tr>';
        list_html +=  '    <td><input name="ids" type="checkbox" /></td>';
        list_html +=  '    <td>' +item.prodId+ '</td>';
        list_html +=  '    <td>' +item.prodName+ '</td>';
        list_html +=  '    <td>' +item.code+ '</td>';
        list_html +=  '    <td>' +item.codeName+ '</td>';
        list_html +=  '    <td>' +item.discount+ '</td>';
        list_html +=  '    <td>' +item.price+ '</td>';
        list_html +=  '    <td>' +item.stock+ '</td>';
        list_html +=  '    <td>折扣後金額</td>';
        list_html +=  '    <td>' +item.createTime+ '</td>';
        list_html +=  '    <td>' +item.createTime+ '</td>';
        list_html +=  '    <td class="text-center">';
        list_html +=  '    <button type="button" class="btn bg-olive btn-xs" onclick=\'location.href="all-order-manage-edit.html"\' >  編輯  </button>';
        list_html +=  '    <button type="button" class="btn bg-olive btn-xs" onclick=\'location.href="all-order-manage-edit.html"\' >  刪除  </button>';
        list_html +=  '    </td>';
        list_html +=  '</tr>';

      });
      $("tbody.datalist_item").html(list_html);   
    },
    error: function(xhr){         // request 發生錯誤的話執行
      console.log("error");
      console.log(xhr);
    }
  });