var typePromo = '';// 全局参数：优惠券类型
var promoNo = '';// 优惠券id
//var voucherFlag = false;// 是否使用优惠券标示
// var clickFlag=false;//优惠券列表的onclick事件是否有效
$(function () {
  $("#buyAmount").attr(
      'placeholder',
      "起购金额" + format_money($(cillAmount).val()) + " 元,递增金额"
          + format_money($(unitAmount).val()) + " 元");
  var surplusAmount = $("#surplus").val();
  var status=$("#status").val();
  if(surplusAmount==0||status=='PAUSE'){
  $("#submit").attr("disabled", true);
  $("#submit").addClass('btnBuy-gray');
  $("#buyAmount").attr('readonly',true);
  $("#buyAmount").attr('onblur','');
  $("#submit").val('已售完');
}else{
  $("#submit").attr("disabled", false);
  $("#submit").removeClass('btnBuy-gray');
  $("#buyAmount").attr('readonly',false);
  $("#buyAmount").attr('onblur','getExpectIncome()');
  $("#submit").val('立即购买');
}
  // 格式化
$("#yearRateP").html(format_yearRate($("#yearRateP").html())+"<span class=\"font-30\">%</span>");
  $("#buyDay").html(format_date_MMDD($("#buyDay").html()));
  $("#incomeDay").html(format_date_MMDD($("#incomeDay").html()));
  $("#expireDay").html(format_date_MMDD($("#expireDay").html()));
//  $("#arrivalDay").html(format_date_MMDD($("#arrivalDay").html()));

});
function getInfo (id) {
  location.href = "fixed/productDetailInfo?productId=" + id;
}
function toBuy () {
  var productId = $("#productId").val();
  var expectIncome = $("#expectIncome").val();
  var amount = $("#buyAmount").val();
  if (amount == null || amount == '' || amount == 0) {
    $("#submit").attr("disabled", true);
    $("#submit").addClass('btnBuy-gray');
    $("#messageError").html(
        "<i class=\"icon icon-error2 font-12\">"
            + "请输入正确购买金额");
    $("#messageError").show();
    $("#submit").val('立即购买');
    $("#expectIncomeSpan").hide();
    $("#expectIncome").val('');
    return false;
  } else {
    $("#submit").attr("disabled", false);
    $("#submit").removeClass('btnBuy-gray');
    $("#messageError").html('');
    $("#expectIncomeSpan").show();
    $("#messageError").hide();
  }
  var rechargeAmount = $("#rechargeAmount").val();
  var buyAmount = $("#buyAmount").val();
  //判断APP端用户是否绑卡
  var isBankCard=$("#isBankCard").val();
  if(isBankCard != null && isBankCard != undefined  && 'NO'==isBankCard){
      //获取当前网址，如： http://localhost:8080/ems/Pages/Basic/Person.jsp
      var curWwwPath = window.document.location.href;
      //获取主机地址之后的目录，如： /ems/Pages/Basic/Person.jsp
      var pathName = window.document.location.pathname;
      var pos = curWwwPath.indexOf(pathName);
      //获取主机地址，如： http://localhost:8080
      var localhostPath = curWwwPath.substring(0, pos);
      //获取带"/"的项目名，如：/ems
      var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
//	  location.href="account/card/toBindCard?returnUrl="+localhostPath+projectName+"/asset/toRecharge?productId="+productId+"&amount="+ rechargeAmount
//	    + "&ret=fixed" + "&expectIncome=" + expectIncome;
      location.href="account/card/toBindCard?returnUrl=/asset/toRecharge?productId="+productId+"&amount="+ rechargeAmount
      + "&ret=fixed" + "&expectIncome=" + expectIncome;
	  return;
  }
  if (rechargeAmount == 0) {// 去确认购买页面
    location.href = "fixed/toBuyProductInfo?productId=" + productId
        + "&expectIncomeNoVoucher=" + expectIncome 
        + "&totalPays=" + buyAmount;
  } else {// 去充值页面
    location.href = "asset/toRecharge?amount=" + rechargeAmount
        + "&ret=fixed&productId=" + productId + "&expectIncome=" + expectIncome
        + "&promoNo=" + promoNo;
  }
}
//购买金额失去焦点
function getExpectIncome () {
  var yearRate = parseFloat($("#yearRate").val());
  var termDay = parseFloat($("#termDay").val());
  var cillAmount = parseFloat($("#cillAmount").val());
  var unitAmount = parseFloat($("#unitAmount").val());
  var accountAmount = parseFloat($("#accountAmount").val());
  var surplusAmount = parseFloat($("#surplus").val());
  var errorMsg = null;
  var flag = false;
  var buyAmount = 0;
  var tradeUpper=$("#tradeUpper").val();
  if ($("#buyAmount").val() == null || $("#buyAmount").val() == '') {
    errorMsg = "购买金额为空";
    flag = true;
  } else {
    buyAmount = parseFloat($("#buyAmount").val());
    if (buyAmount < cillAmount) {
      errorMsg = "购买金额必须大于起购金额";
      flag = true;

    } else if (buyAmount > surplusAmount) {
      errorMsg = "购买金额不能大于产品剩余金额";
      flag = true;
    } else if (buyAmount % unitAmount != 0) {
      errorMsg = "购买金额应该是递增金额的整数倍";
      flag = true;
    }else if(tradeUpper!=""){//如果存在单笔购买限额
      if(buyAmount>tradeUpper){
        errorMsg = "购买金额超过单笔最高金额";
        flag = true;
      }
    }
  }

  if (flag) {
    $("#submit").attr("disabled", true);
    $("#submit").addClass('btnBuy-gray');
    $("#messageError").html(
        "<i class=\"icon icon-error2 font-12\">"
            + errorMsg );
    $("#messageError").show();
    $("#submit").val('立即购买');
    $("#expectIncomeSpan").html('');
    $("#expectIncomeSpan").hide();
    $("#expectIncome").val('');
    return false;
  } else {
    $("#submit").attr("disabled", false);
    $("#submit").removeClass('btnBuy-gray');
                $("#messageError").html('');
                $("#messageError").hide();
  }
  if (buyAmount > accountAmount) {
    /*var rechargeAmount = parseFloat(buyAmount) - parseFloat(accountAmount);
    $("#submit").val("去充值" + format_money(rechargeAmount) + " 元");
    $("#rechargeAmount").val(rechargeAmount);
*/    //由于懒猫账号余额可能有小数点后三位的值，即厘，故用去尾法
    var rechargeAmountFloat = parseFloat(buyAmount)- formatNumberFloor(parseFloat(accountAmount),2);
    var rechargeAmount=rechargeAmountFloat.toFixed(2);
    $("#submit").val("去充值" + rechargeAmount + " 元");
    $("#rechargeAmount").val(rechargeAmount);// 记录需充值金额
  }else{
    $("#submit").val('立即购买');
    $("#rechargeAmount").val(0);
  }
  var expectIncome = (buyAmount * yearRate * termDay) / 365 / 100;
  $("#expectIncomeSpan").html(
      "预计到账收益 <span class=\"amountA orange pr\">"
          + format_money(expectIncome) + "</span> 元");
  $("#expectIncomeSpan").show();
  $("#expectIncome").val(expectIncome);
  $("#expectIncomeNoVoucher").val(expectIncome);
/*  if (voucherFlag == false) {
    // 没有调用后台，自己根据规则算的收益 待研究
    var expectIncome = (buyAmount * yearRate * termDay) / 365 / 100;
    $("#expectIncomeSpan").html(
        "预计到账收益 <span class=\"amountA orange pr\">"
            + format_money(expectIncome) + "</span> 元");
    $("#expectIncome").val(expectIncome);
    $("#expectIncomeNoVoucher").val(expectIncome);
  } else {
    // getVouchersInfo();//过滤可用优惠券
    // updateVoucher();//自动显示优惠券列表
    deleteVoucher();
  }
*/
}
