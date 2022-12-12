$(window).on("load", function(){
    $("button.login").on("click", function(){
        $("div.container1").fadeIn(500); //點擊註冊時淡入
    });

    $("button.btn-close").on("click", function(){
        $("div.container1").fadeOut(500);
    });

});