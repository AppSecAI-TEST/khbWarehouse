var myScroll;
var totalPage = 0;
var pageIndex = 1;
function downAjax () {// 下拉刷新
  var productId=$("#productId").val();
  pageIndex = 1;
  $.ajax({
    url:'fixed/ProductInvRecord',
    type:'post',
    async: false,
    timeount:10000,
    dataType:'json',
    data:{productId:productId,pageIndex:1},
    error:function(){

      $("#mask").show();
      $("#alertLayer-8").show();
    },
    success:function(data){
      
      if(data=='SYSTEM_EXCEPTION'){
        $("#mask").show();
        $("#alertLayer-8").show();
      }else{
        var rowTotal=data.rowTotal;var strButton='';     
        var pageTotal=data.pageTotal;
        if(rowTotal!=0){
        var   strTopPullDown="<div id=\"scroller-pullDown\"><span id=\"down-icon\" class=\"icon-double-angle-down pull-down-icon\"></span><span id=\"pullDown-msg\" class=\"pull-down-msg\">下拉刷新</span></div><div >";
        var   strTop=strTopPullDown+$("#NoAjax").html()+"<div  class=\"prolastCon\">";
        var str=strTop+" <table cellpadding=\"0\" cellspacing=\"0\" class=\"prolastTable mar0\" width=\"100%\"><tbody id=\"result\"><tr><th>时间</th><th>用户名</th> <th>购买金额</th> </tr>";
        data.invRecord.forEach(function(itmes){
          str=str+" <tr><td>"+format_date_forward(itmes.payTime)+"<br/>"+format_date_after(itmes.payTime)+"</td><td>"+
          itmes.phoneNo+"</td><td>"+format_money_upgrade(itmes.amount,2)+"</td></tr>";
        });
        str=str+"</div>";
        if (pageTotal != 1) {
          strButton = " <div id=\"scroller-pullUp\"><span id=\"up-icon\" class=\"icon-double-angle-up pull-up-icon\"></span><span id=\"pullUp-msg\" class=\"pull-up-msg\">加载更多</span></div>";
        }else{
          strButton = " <div id=\"scroller-pullUp\" style=\"display:none\"><span id=\"up-icon\" class=\"icon-double-angle-up pull-up-icon\"></span><span id=\"pullUp-msg\" class=\"pull-up-msg\">加载更多</span></div>";
        }
       
      }else{
        str=$("#NoAjax").html();
      }
      str=str+strButton;
      $("#scroller").html(str);
      }
      myScroll.refresh();
    }
});

}
function upAjax () {// 上滑加载更多

  pageIndex++;
  var pageTotal = $("#totalPage").val();
  var productId=$("#productId").val();
  if (pageIndex > parseInt(pageTotal)) {// 当前页数大于总页数时
    $("#scroller-pullUp").hide();
    return false;
  } else if (pageIndex == parseInt(pageTotal)) {
    $("#scroller-pullUp").hide();
  }
  var elInit = $("#result");
  $.ajax({
    url:'fixed/ProductInvRecord',
    type:'post',
    dataType:'json',
    data:{productId:productId,pageIndex:pageIndex},
    error:function(){

      $("#mask").show();
      $("#alertLayer-8").show();
    },
    success:function(data){
      
      if(data=='SYSTEM_EXCEPTION'){

        $("#mask").show();
        $("#alertLayer-8").show();
      }else{
        var str="";
        data.invRecord.forEach(function(itmes){
          str=str+" <tr><td>"+format_date_forward(itmes.payTime)+"<br/>"+format_date_after(itmes.payTime)+"</td><td>"+
          itmes.phoneNo+"</td><td>"+format_money_upgrade(itmes.amount,2)+"</td></tr>";
        });
        elInit.append(str);
      }
      myScroll.refresh();
    }
});

}
function loaded () {
  var distance = 30; // 滑动距离
  var flagDown = false;
  var flagUp = false;
  myScroll = new iScroll('wrapper', {
    onRefresh: function(){
      $("#pullDown-msg").html('下拉刷新');
      $("#pullUp-msg").html('加载更多');
    },
    onScrollMove : function () {
      if (this.y > distance) {
        $("#pullDown-msg").html('松手开始更新...');
        flagDown = true;
      }else
      if (this.maxScrollY - this.y > distance
          && $("#scroller-pullUp").css('display') != 'none') {
        $("#pullUp-msg").html('松手开始加载...');
        flagUp = true;
      }
    },
    onScrollEnd : function () {
      if (flagDown == true) {
//        setTimeout(downAjax, 1000);
         downAjax();
        flagDown = false;
      }
      if (flagUp == true) {
//        setTimeout(upAjax, 1000);
         upAjax();
        flagUp = false;
      }
    }
  });

}



/*document.addEventListener('DOMContentLoaded', function () {
  setTimeout(loaded, 200);
}, false);*/
