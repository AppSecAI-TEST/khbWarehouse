var typePromo = '';// 全局参数：优惠券类型
var promoNo = '';// 优惠券id
var voucherFlag = false;// 是否使用优惠券标示
// var clickFlag=false;//优惠券列表的onclick事件是否有效
$(function () {

  var firstColumn = $("#firstColumn").val();
  switch (firstColumn) {
  case 'MONY':
    $("#month").addClass("on");
    break;
  case 'QUAY':
    $("#quarter").addClass("on");
    break;
  case 'YEAY':
    $("#year").addClass("on");
    break;
  default:
    break;
  }
    //已售完
  var surplusAmount = $("#surplus").val();
  var status=$("#status").val();
  if(surplusAmount==0||status=='PAUSE'){
      $("#submit").attr("disabled", true);
      $("#submit").addClass('btnBuy-gray');
      $("#submit").val('已售完');
    }else{
      $("#submit").attr("disabled", false);
      $("#submit").removeClass('btnBuy-gray');
      $("#submit").val('立即购买');
    }
  // 格式化
    $("#yearRateP").html(format_yearRate($("#yearRateP").html())+"<span class=\"font-30\">%</span>");
  $("#buyDay").html(format_date_MMDD($("#buyDay").html()));
  $("#incomeDay").html(format_date_MMDD($("#incomeDay").html()));
  $("#expireDay").html(format_date_MMDD($("#expireDay").html()));
  $("#arrivalDay").html(format_date_MMDD($("#arrivalDay").html()));

});
function getInfo (id) {
  location.href = "fixed/productDetailInfo?productId=" + id;
}
function toBuyNoLogin () {// 点击立即购买
  var productId = $("#productId").val();
	//APP端不弹出蒙层，直接被原生登录拦截
	var u = navigator.userAgent;
	var isApp = /lanmao/i.test(u);
	if(isApp) {
		location.href ="account/toLogin?returnUrl=fixed/toPurchaseProduct?productId=" + productId;
		return;
	}
  $("#alertLayer-5 .pr a").eq(2).attr('href',"account/toLogin?returnFlag=toBuy&productId="
            + productId);
  $("#alertLayer-5 .pr a").eq(1).attr('href',"account/toRegister?returnFlag=toBuy&productId="
            + productId);
  $("#mask").show();
  $("#alertLayer-5").show();
}
function clean(){
  $("#mask").show();
  $("#alertLayer-5").show();
}
