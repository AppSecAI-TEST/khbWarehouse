$(document).ready(function(){
    //µ¯´°
    var x;
    var y;
    function xy(){
        $("#mask").height($(window).height());
        $("#alertLayer").animate({"left":"6%","top":"2%"},0);
    }
    xy();
    $(window).resize(function(){
        xy();
    });

    var showDialogBottom = function(){
        $("#mask").css("height",$(document).height());
        $("#mask").css("width",$(document).width());
        $("#mask").show();
    };
    //ÏÔÊ¾/Òþ²Øµ¯³ö²ã
    $(".btnClick").click(function(){
        showDialogBottom();
        $("#alertLayer").show();
        $("body").css("position","fixed");
    });
    $(".btnClosed").click(function(){
        $("#alertLayer").hide();
        $("body").css("position","initial");
        $("#mask").hide();
    });
});