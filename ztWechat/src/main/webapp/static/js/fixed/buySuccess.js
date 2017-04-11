$(function(){
//格式化
  $("#buyDaySpan").html("<i class=\"icon icon-circle\"></i>"+format_date_MMDD($("#buyDay").val())+" 成功买入"+$("#productName").val()+format_money($("#amount").val())+" 元");
  $("#incomeDaySpan").html("<i class=\"icon icon-circle\"></i>"+format_date_MMDD($("#incomeDay").val())+" 开始起息");
  $("#expireDaySpan").html("<i class=\"icon icon-circle\"></i>"+format_date_MMDD($("#expireDay").val())+" 产品到期");
//  $("#arrivalDaySpan").html("<i class=\"icon icon-circle\"></i>"+format_date_MMDD($("#arrivalDay").val())+"收益本金到账");
});
