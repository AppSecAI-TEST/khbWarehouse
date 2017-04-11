$(function() {

    $('.questionList li').each(function(index){
        $(this).click(function(){
            if($('.quesCon').eq(index).css("display")=='none'){
                $('.quesCon').eq(index).show().parent().siblings().find('.quesCon').hide();
                $('.questionList li').eq(index).addClass('active').siblings().removeClass('active');
                //$('.questionList li .trigger').eq(index).addClass('trigger-top').parent().siblings().find('.trigger').removeClass('trigger-top');
                $('.questionList li .trigger').removeClass('trigger-top').eq(index).addClass('trigger-top');
            }else{
                $('.questionList li').removeClass('active');
                $('.quesCon').eq(index).hide();
                //$('.questionList li .trigger').eq(index).removeClass('trigger-top').parent().siblings().find('.trigger').addClass('trigger-top');
                $('.questionList li .trigger').eq(index).removeClass('trigger-top');
            }
        });
    });

    /*交易明细下拉传值*/
    $('.detail-list li').each(function(index){
        $(this).click(function(){
            if($('.detail-list-main .label').eq(index).css("display")=='none'){
                $(".label").eq(index).show().siblings().hide();
                $('.detail-list li').eq(index).addClass('active').siblings().removeClass('active');
                $('.detail-list li i').removeClass('trigger-top').eq(index).addClass('trigger-top');
            }else{
                $(".label").eq(index).hide();
                $('.detail-list li').removeClass('active');
                $('.detail-list li i').eq(index).removeClass('trigger-top');
            }
        });
    });
    $(".style-list li").click(function(){
        var selectedValue = $(this).html();
        $("#aSelectedText").html(selectedValue);

        $(".style-list").hide();
        $('.detail-list li i').removeClass('trigger-top');
    });
    $(".confirm-list li").click(function(){
        var selectedValue = $(this).html();
        $("#bSelectedText").html(selectedValue);

        $(".confirm-list").hide();
        $('.detail-list li i').removeClass('trigger-top');
    });

    /*组合购买运算弹层*/
    $(function(){
        $('.btnTimeOut').click(function(){
            if($('#alertLayer').css('display')=='block'){
                setTimeout(function(){
                    $('#alertLayer').hide();
                },3000);
                setTimeout(function(){
                    $('#mask').hide();
                },3000);
                $('body').css('position','initial');
            }
        })
    })
});


