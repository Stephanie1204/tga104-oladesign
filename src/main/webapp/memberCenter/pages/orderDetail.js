var orderId = new URLSearchParams(location.search).get("orderId");
let originalPrice=0;

//get orderlist
$.ajax({
    url: "http://localhost:8080/oladesign/orderItem",
    type: "GET",
    data: {orderId:orderId},
    contentType: "application/json; charset=UTF-8",
    dataType: "json",
    success: function (data) {
      console.log(data);

        let list_html = "";
        $.each(data, function (index, item) {
          originalPrice += item.price*item.quantity;

            list_html += `
            <div class="one_list">
            <div class="col-md-2 title">商品編號</div>
            <div class="col-md-4 data text">${item.productId}</div>
            <div class="col-md-6 data text"></div>

            <div class="col-md-2 title">商品名稱</div>
            <div class="col-md-4 data text">${item.productName}</div>
            
            <div class="col-md-2 title">原價</div>
            <div class="col-md-2 data text">$${item.price}</div>

            <div class="col-md-1 title">購買數量</div>
            <div class="col-md-1 data text">${item.quantity}</div>
            </div>
            `;
        });

        $("div.all_list").html(list_html);


    },
    error: function (xhr) {
      // request 發生錯誤的話執行
      console.log("error");
      console.log(xhr);
    },
});


//get order
$.ajax({
    url: "http://localhost:8080/oladesign/order/:id",
    type: "GET",
    data: {orderId:orderId},
    contentType: "application/json; charset=UTF-8",
    dataType: "json",
    success: function (data) {
      console.log(data);
        
        let  list_html = `
            <div class="panel-heading">收件資訊</div>
            <div class="row data-type">
                <div class="col-md-2 title">收件人</div>
                <div class="col-md-4 data text">${data.receiver}</div>

                <div class="col-md-2 title">收件地址</div>
                <div class="col-md-4 data text">${data.address}</div>
            </div>
        `;
        $("div.receive_info").html(list_html);
        

        let  order_sellerInfo = `
            <div class="col-md-2 title">賣家統編</div>
            <div class="col-md-4 data text">${data.comTaxId}</div>

            <div class="col-md-2 title">會員編號</div>
            <div class="col-md-4 data text">${data.memId}</div>

            <div class="col-md-2 title">訂單編號</div>
            <div class="col-md-4 data text time">${orderId}</div>

            <div class="col-md-2 title">訂單日期</div>
            <div class="col-md-4 data text time">${formatDate(data.orderTime)}</div>
        `;
        $("div.order_sellerInfo").html(order_sellerInfo);

        let  order_sellerInfo2 = `
            <div class="col-md-2 title">訂單狀態</div>
            <div class="col-md-4 data text">${code2CodeName(data.orderStatus, orderStatusType)}</div>

            <div class="col-md-2 title">商品小計</div>
            <div class="col-md-4 data text">${originalPrice}</div>

            <div class="col-md-2 title">物流狀態</div>
            <div class="col-md-4 data text">${code2CodeName(data.shippingStatus, shippingStatusType)}</div>

            <div class="col-md-2 title">使用折扣碼</div>
            <div class="col-md-4 data text">${data.coupon===null?"":data.coupon}</div>

            <div class="col-md-6 data text"></div>
            <div class="col-md-2 title">使用紅利點數</div>
            <div class="col-md-4 data text">${data.pointUse}</div>

            <div class="col-md-6 data text"></div>
            <div class="col-md-2 title">新增紅利點數</div>
            <div class="col-md-4 data text">${data.pointGet}</div>

            <div class="col-md-6 data text"></div>
            <div class="col-md-2 title">折扣後總額</div>
            <div class="col-md-4 data text">${data.amount}</div>

            <div class="col-md-6 data text"></div>
            <div class="col-md-2 title">付款方式</div>
            <div class="col-md-4 data text">信用卡</div>
        `;
        $("div.order_sellerInfo2").html(order_sellerInfo2);

        

    },
    error: function (xhr) {
      // request 發生錯誤的話執行
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



const orderStatusType = [
  { code: 1, codeName: "待確認" },
  { code: 2, codeName: "已成立" },
  { code: 3, codeName: "已取消" },
];

const shippingStatusType = [
  { code: 1, codeName: "待確認" },
  { code: 2, codeName: "待出貨" },
  { code: 3, codeName: "已取消" },
  { code: 4, codeName: "已出貨" },
  { code: 5, codeName: "運送中" },
  { code: 6, codeName: "已送達" },
];

const code2CodeName = (code, types) => {
  const obj = types.find((type) => type.code === code);
  if (obj) {
    return obj.codeName;
  }
  return "";
};
