var myScroll;
var totalPage = 0;
var pageIndex = 1;
function downAjax () {// 下拉刷新
  pageIndex = 1;
  var startTime = $("#appDateBegin").val();
  var endTime = $("#appDateEnd").val();
  $.ajax({
    url:'asset/billInfo',
    type:'post',
    dataType:'json',
    data:{startDate:startTime,endDate:endTime,index:pageIndex},
    error:function(){

      $("#mask").show();
      $("#alertLayer-8").show();
    },
    success:function(data){
      
      if(data=='SYSTEM_EXCEPTION'){
        $("#mask").show();
        $("#alertLayer-8").show();
      }
      else if(data == 'noLogin'){
        location.href=window.location.href;  
      }else{
        var str='';var strButton='';
        var   strTop="<div id=\"scroller-pullDown\"><span id=\"down-icon\" class=\"icon-double-angle-down pull-down-icon\"></span><span id=\"pullDown-msg\" class=\"pull-down-msg\">下拉刷新</span></div><ul><div id=\"result\">";
          var pageTotal=data.pageTotal;
          var   rowTotal=data.rowTotal;
        $("#totalPage").val(pageTotal);
        if(rowTotal!=0){
          str=strTop;
          str=queryCommon(data,str);
          str=str+"</div></ul>";
          if (pageTotal != 1) {
            strButton = " <div id=\"scroller-pullUp\"><span id=\"up-icon\" class=\"icon-double-angle-up pull-up-icon\"></span><span id=\"pullUp-msg\" class=\"pull-up-msg\">加载更多</span></div>";
          }else{
            strButton = " <div id=\"scroller-pullUp\" style=\"display:none\"><span id=\"up-icon\" class=\"icon-double-angle-up pull-up-icon\"></span><span id=\"pullUp-msg\" class=\"pull-up-msg\">加载更多</span></div>";
          }
        }
        str=str+strButton;
$("#scroller").html(str);
      }
      myScroll.refresh();
    }
  });
/*  $("#scroller").css('top','-40px');
  $("#scroller-pullDown").html("下拉刷新");*/
}
function upAjax () {// 上滑加载更多
var startTime = $("#appDateBegin").val();
var endTime = $("#appDateEnd").val();
  pageIndex++;
  var pageTotal = $("#totalPage").val();
  if (pageIndex > parseInt(pageTotal)) {// 当前页数大于总页数时
    $("#scroller-pullUp").hide();
    return false;
  } else if (pageIndex == parseInt(pageTotal)) {
    $("#scroller-pullUp").hide();
  }
  var elInit = $("#result");
  $.ajax({
    url:'asset/billInfo',
    type:'post',
    dataType:'json',
    data:{startDate:startTime,endDate:endTime,index:pageIndex},
    error:function(){

      $("#mask").show();
      $("#alertLayer-8").show();
    },
    success:function(data){
      
      if(data=='SYSTEM_EXCEPTION'){
        $("#mask").show();
        $("#alertLayer-8").show();
      }
      else if(data == 'noLogin'){
        location.href=window.location.href;  
      }else{
        var str = '';
          var  rowTotal=data.rowTotal;
        if(rowTotal!=0){
          str=queryCommon(data,str);
        }
        elInit.append(str);

      }
      myScroll.refresh();
    }
  });
};


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
//公共方法
function queryCommon(data,str){
  data.queryOrderDto.forEach(function(itmes){
    var type=''; var typeInfo=''; var amountSpan='';var sign='';
    switch(itmes.tradeType){
    case 'PACKETS'://分润
      type='分润';
      sign='+';
      typeInfo='懒猫账户';
      break;
    case 'REBATE'://分润
      type='分润';
      sign='+';
      typeInfo='懒猫账户';
      break;
    case 'DEPOSIT'://充值
      type='充值';
      sign='+';
      typeInfo='懒猫账户';
      break;
    case 'WITHDRAW'://提现
      type='提现';
      sign='-';
      typeInfo='懒猫账户';
      break;
    case 'SCB_PUR'://生财宝申购
      type='转入';
      sign='-';
      typeInfo='生财宝';
      break;
    case 'SCB_REDEEM'://生财宝赎回
      type='转出';
      typeInfo='生财宝';
      break;
    case 'TRUST_PUR'://信托购买
      type='购买';
      typeInfo=itmes.trustProductName+"<i class=\"font-12\">"+itmes.trustProductPeriodNo+"期</i>";
      sign='-';
      break;
    case 'TRUST_REDEEM'://信托兑付
      type='兑付';
      typeInfo=itmes.trustProductName+"<i class=\"font-12\">"+itmes.trustProductPeriodNo+"期</i>";
      sign='+';
      break;
    case 'FUNDPAY_PUR'://购买基金
      type='购买';
      sign='-';
      typeInfo='公募基金';
      break;
    }
    if(sign=='-'){
      amountSpan="<span class=\"amounts green fr\">" +sign+
        itmes.amount+"</span>";
    }else{
      amountSpan="<span class=\"amounts orange fr\">+"+itmes.amount+"</span>";
    }
      str=str+" <li><table cellspacing=\"0\" cellpadding=\"0\" class=\"font-18\" width=\"100%\"><tr><td width=\"50\"><span class=\"accountType\">" +
          type+"</span></td><td><span class=\"accountPro\">" +
          typeInfo+"</span></td><td>"+amountSpan+"</td></tr></table><p class=\"gray clearfix\">" +
          itmes.tradeDate+" </p></li> ";
  });
  return str;
}
