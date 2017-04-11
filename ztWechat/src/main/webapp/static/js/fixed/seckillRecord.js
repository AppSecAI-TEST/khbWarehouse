var pageIndex = 1;
var pageTotal = 1;
$(function(){
    loaded(); //先加载框架
    ajaxExcu(); //执行ajax查询数据并显示到页面
});
//上拉加载执行的方法
function pullUpAction(){
    queryRecordNextPage(++pageIndex);
}
function queryRecordNextPage(pageIndex){
    if(pageTotal >= pageIndex){
        ajaxExcu(); //执行ajax查询数据并显示到页面
    }else{
        $('.pullUpLabel').html('亲，没有更多记录了!');
        $('.pullUpIcon').hide();
    }
}

function ajaxExcu(){
    $.ajax({
        url : "seckillActivity/querySeckillRecord",
        type : "post",
        dataType : "json",
        data : {pageIndex : pageIndex},
        error : function(){
            $("#seckillRecord").append("<div class=\"tc\"><p class=\"pullUpLabel\">网络异常，请稍后重试...</p></div>");
        },
        success : function(data){
            if(data != '' && data != null){
                if(data == "\"noLogin\""){
                    location.href = "account/toLogin";
                }else{
                    pageTotal = data.pageTotal; //总页数
                    $("#totalNum").remove();
                    $("#seckillNum").append("<div id=\"totalNum\"><p class=\"font-text-sm\">总人数："+data.totalNum+"人 <span>|</span> 总金额："+parseFloat(data.totalAmount).toFixed(2)+"元</p></div>");
                    if(data.seckillHistoryListDto != '' && data.seckillHistoryListDto != 'undefined' && data.seckillHistoryListDto.length != 0){
                        if(pageTotal > pageIndex){   //当前页不是最后一页，显示字样“上拉加载。。”
                            $("#pullUp").attr("style","");
                        }
                        showRecord(data);
                        //刷新坐标
                        myScroll.refresh();
                    }else{
                        $("#seckillRecord").append("<div class=\"tc\"><p class=\"pullUpLabel\">未查询到相关记录</p></div>");
                    }
                }
            }else{
                $("#seckillRecord").append("<div class=\"tc\"><p class=\"pullUpLabel\">未查询到相关记录</p></div>");
            }
        }
    })
}

function showRecord(data){
    $.each(data.seckillHistoryListDto,function(n,value){
        $("#seckillRecord").append("" +
        		"<div class=\"seckill-list bg-white\">" +
            		"<div class=\"secName tc fl\">"+
                        "<span class=\"red font-tit-sm block\">"+value.productName+"</span>" +
                        "第"+format_date(value.saleStart)+"期" +
                "</div>" +
                "<div class=\"timeList fl pr\">"+
                    "<span class=\"time-line red pa\">|</span>"+
                    "<ul>"+
                        "<li><i class=\"icon icon-time red\"></i> "+value.saleStart+"<span class=\"font-text-note\"> (发售)</span></li>"+
                        "<li><i class=\"icon icon-time red\"></i> "+value.saleEnd+"<span class=\"font-text-note\"> (售完)</span></li>"+
                    "</ul>"+
                "</div>"+
                "<div class=\"clearfix\"></div>"+
                "<p class=\"secInfo\"><span class=\"fr\">秒杀人数："+value.seckillNum+"人</span> 秒杀总额："+parseFloat(value.seckillAmount).toFixed(2)+"元</p>"+
            "</div>");
    });
}