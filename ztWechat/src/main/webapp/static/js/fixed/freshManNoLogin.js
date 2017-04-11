$(function(){
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
  $("#yearRateP").html(format_yearRate($("#yearRateP").html())+"<span class=\"font-30\">%</span>");
  $("#buyDay").html(format_date_MMDD($("#buyDay").html()));
  $("#incomeDay").html(format_date_MMDD($("#incomeDay").html()));
  $("#expireDay").html(format_date_MMDD($("#expireDay").html()));
//  $("#arrivalDay").html(format_date_MMDD($("#arrivalDay").html()));
});
$(function(){
  //已售完
  var surplusAmount=$("#surplus").val();
  if(surplusAmount==0){
    $("#submit").attr("disabled", true);
    $("#submit").addClass('btnBuy-gray');
    $("#submit").val('已售完');
  }else{
    $("#submit").attr("disabled", false);
    $("#submit").removeClass('btnBuy-gray');
    $("#submit").val('立即购买');
  }
});
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
