$(function(){	
    $("#com_regist").on("click", function(){
    //	 		接收參數
                let memId = "${memberVO.memId}";
                console.log("memId=" + memId);
                let memName = $("#memName").val();
                let com_taxid = $("#com_taxid").val();
                let com_name = $("#com_name").val();
                let com_phone = $("#com_phone").val();
                let com_owner = $("#com_owner").val();
                let owner_phone = $("#owner_phone").val();
                let com_zipcode = $("input.zipcode").val();
                let com_city = $("select.city").val();
                let com_town = $("select.town").val();
                let com_road =$("#com_road").val();
                let com_address = com_zipcode + com_city + com_town + com_road;
                
                var comRegistData = {
                        "memId": memId,
                        "memName": memName,
                        "comTaxId": com_taxid,
                        "comName": com_name,
                        "comPhone": com_phone,
                        "comOwner": com_owner,
                        "ownerPhone": owner_phone,
                        "comAddress": com_address,
                        "comZipcode": com_zipcode,
                        "comCity": com_city,
                        "comTown": com_town,
                        "comRoad": com_road
                    };
                console.log(comRegistData);
                
                $.ajax({
                    url: "<%=request.getContextPath()%>/CompanyBackEnd/ComMemRegist",
                    type: "POST",
                    data: JSON.stringify(comRegistData),
                    dataType: "json",
                    contentType: "application/json",
                    processData: false,  // 不用額外處理資料。例：如果是 GET，預設會將 data 物件資料字串化，放到網址
                    success: function(data){  // request 成功取得回應後執行
                        console.log(data); //回傳JSON資料
                        console.log("data.success = " + data.success);
                        if(!data.success){
                            console.log("顯示錯誤訊息")
                            var dataObj = JSON.parse(data.error); //JSON轉變為物件
                            console.log(dataObj.comAddress);
                            console.log(dataObj.comName);
                            
    // 						針對input標籤:如果dataObj沒有此物件 -> 沒有錯誤訊息 -> 顯示原本的值；填寫錯誤就清空value						
                            $("#com_taxid").val((dataObj.comTaxid === undefined ? com_taxid : ""));
                            $("#com_name").val((dataObj.comName === undefined ? com_name : ""));
                            $("#com_phone").val((dataObj.comPhone === undefined ? com_phone : ""));
                            $("#com_owner").val((dataObj.comOwner === undefined ? com_owner : ""));
                            $("#owner_phone").val((dataObj.ownerPhone === undefined ? owner_phone : ""));
                            $("#com_address").val((dataObj.comAddress === undefined ? com_address : ""));
    
    // 						針對錯誤訊息span標籤:如果如果dataObj有此物件 ->顯示錯誤訊息；改正後刪除錯誤訊息
                            if(memName == null){
                                $("#err_memName").text(dataObj.memName);
                            }
                            $("#err_com_taxid").text((dataObj.comTaxid === undefined ? "" : dataObj.comTaxid));
                            $("#err_com_name").text((dataObj.comName === undefined ? "" : dataObj.comName));
                            $("#err_com_phone").text((dataObj.comPhone === undefined ? "" : dataObj.comPhone));
                            $("#err_com_owner").text((dataObj.comOwner === undefined ? "" : dataObj.comOwner));
                            $("#err_owner_phone").text((dataObj.ownerPhone === undefined ? "" : dataObj.ownerPhone));
                            $("#err_com_address").text((dataObj.comAddress === undefined ? "" : dataObj.comAddress));
                            if(com_city == null){
                                $("#err_com_address").text("請選擇縣市與行政區");
                            }
                            return false;
                        }else{
    // 						成功提交，刪除所有錯誤訊息
                            $("#err_memName").text("");
                            $("#err_com_taxid").text("");
                            $("#err_com_name").text("");
                            $("#err_com_phone").text("");
                            $("#err_com_owner").text("");
                            $("#err_owner_phone").text("");
                            $("#err_com_address").text("");
                            
                        }
                        
                        
                        
                    },
                    error: function(xhr){
                        console.log("errxhr=" + xhr);
                    },
                    complete: function(xhr){
                        console.log(xhr);
                    }
                });
    });
});