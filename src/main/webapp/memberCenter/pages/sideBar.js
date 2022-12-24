$("#profile").on("click",function(){

    window.location.href="./accountBasicInfo.html?memId="+sessionStorage.getItem("memId");
})