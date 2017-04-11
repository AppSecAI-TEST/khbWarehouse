var typePromo = '';// 全局参数：优惠券类型
var promoNo = '';// 优惠券id
var addRate='';//加息券所加利率
var voucherFlag = false;// 是否使用优惠券标示
//var clickFlag=false;//优惠券列表的onclick事件是否有效
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

  //优惠券
  var productId = $("#productId").val();
  queryPromotionInfomationsInit(productId);
});
//获取优惠券信息
function queryPromotionInfomationsInit (productId) {
  $.ajax({
    url : "fixed/queryPromotionInfomations",
    type : 'post',
    dataType : 'json',
    data : {
      productId : productId
    },
    error : function () {
      $("#mask").show();
      $("#alertLayer-8").show();
    },
    success : function (data) {
       if(data == 'noLogin'){
        location.href=window.location.href;  
      }else  if(data=='SYSTEM_EXCEPTION'){
        $("#mask").show();
        $("#alertLayer-8").show();  
      }else if (data != null && data.length != 0) {
        var str = '';
        var type = '';
        var strType = '';
        $("#voucher").html(
            "<i class=\"icon icon-add\" </i> 添加红包 (共" + data.length + "张)");
        $("#voucher").show();
      }else {// 去除蒙层效果
//        $("#voucher").attr('class', '');
        $("#voucher").html('');
        $("#alertLayer-3").html();
      }
    }
  });
}

//显示优惠券
function getVouchersInfo () {// 过滤可使用优惠券
  var amount = $("#buyAmount").val();
  var productId = $("#productId").val();
  queryPromotionInfomations(productId, amount);
  $("#mask").css('display', 'block');
  $("#alertLayer-3").css('display', 'block');
  $("#mask").show();
  $("#alertLayer-3").show();
}
//查询优惠券并显示
function queryPromotionInfomations (productId, amount) {
  $
      .ajax({
        url : "fixed/queryPromotionInfomations",
        type : 'post',
        dataType : 'json',
        data : {
          productId : productId
        },
        error : function () {
          $("#mask").show();
          $("#alertLayer-8").show();
        },
        success : function (data) {
           if(data == 'noLogin'){
            location.href=window.location.href;  
          }else if(data=='SYSTEM_EXCEPTION'){
            $("#mask").show();
            $("#alertLayer-8").show();  
          }else
          if (data != null && data.length != 0) {
            var str = '';
            var type = '';
            var strType = '';
            data
                .forEach(function (itmes) {
                  if (itmes.type == 'SURPASSED_ADD_PRINCIPAL') {
                    if (itmes.paymentType == 'PRINCIPAL_ENABLE') {// 理财金
                      strCom = "红包" + format_money(itmes.amount) + " 元理财金（满"
                          + format_money(itmes.orderAmountLower) + "可用）";
                      type = "投资时赠送" + format_money(itmes.amount) + " 元理财金";
                      strType = format_money(itmes.amount) + " 元理财金";
                      typeInfo = "lcj";
                    } else {// 满加券
                      strCom = "红包投资券（满" + format_money(itmes.orderAmountLower) + "加"
                          + format_money(itmes.amount) + "）";
                      type = "投资时赠送" + format_money(itmes.amount) + " 元本金";
                      strType = '投资券';
                      typeInfo = "mjq";
                    }
                  } else {// 加息券
                    strCom = "红包加息券（加" + itmes.addtionalRate + "%利息）";
                    type = "投资时赠送" + itmes.addtionalRate + "%利息";
                    strType = '加息券';
                    typeInfo = "jxq";
                  }
                  if (parseFloat(amount) >=itmes.orderAmountLower) {
                    str = str
                        + "<li title=\""
                        + format_money(itmes.orderAmountLower)
                        + "\"><a  class=\"modVouchers pr\" onclick=\"modVouchered('"
                        + type
                        + "','"
                        + strType
                        + "',"
                        + itmes.userPromoId
                        + ",'"
                        + typeInfo
                        + "','"
                        + itmes.addtionalRate 
                        + "')\"><i class=\"icon icon-vouchers3 orange\"></i><i class=\"font-white modVoucherText pa\">"
                        + strCom + "</i></a></li>";
                  } else {
                    str = str
                        + "<li title=\""
                        + format_money(itmes.orderAmountLower)
                        + "\"><a  class=\"modVouchers pr\" ><i class=\"icon icon-vouchers3 gray\"></i><i class=\"font-white modVoucherText pa\">"
                        + strCom + "</i></a></li>";
                  }

                });
            $("#voucher")
                .html(
                    "<i class=\"icon icon-add\" </i> 添加红包 (共" + data.length
                        + "张)");
            $("#voucherList").html(str);
          } else {// 去除蒙层效果
            $("#voucher").html('');
            $("#alertLayer-3").html();
          }
        }
      });
}
//查看产品详情
function getInfo (id) {
  location.href = "fixed/productDetailInfo?productId=" + id;
}
function modVouchered (type, strType, id, typeInfo,rate) {
  typePromo = typeInfo;// 记录优惠券类型
  promoNo = id;
  if(rate=='undefined'){
    rate='';
  }
  addRate=rate;
  voucherFlag = true;
  $("#voucher").css('display', 'none');
  $("#mask").css('display', 'none');
//  $("#alertLayer").css('display', 'none');
  $("#alertLayer-3").css('display', 'none');
  // $("#buyAmount").attr('readonly',true);
  $("#modVouchered")
      .html(
          "  <li class=\"modVouchered\"><p><span class=\"fr modEdit\"><a onclick=\"updateVoucher()\">修改</a> ｜ <a onclick=\"deleteVoucher()\">删除</a></span>已选优惠券:"
              + "<span class=\"redEnvelope\">"
              + "</span></p><p class=\"modTips lcAccont orange\">"
              + type
              + "</p></li>");
  $("#modVouchered").css('display', 'block');
  getExpectIncomeVoucher(id);// 动态修改预计收益
}
//计算预期到账收益
function getExpectIncomeVoucher (promoNo) {
  var amount = $("#buyAmount").val();
  var productId = $("#productId").val();
  $.ajax({
    url : "fixed/calculatePromotion",
    type : 'post',
    dataType : 'json',
    data : {
      productId : productId,
      amount : amount,
      promoNo : promoNo
    },
    error : function () {
      $("#mask").show();
      $("#alertLayer-8").show();
    },
    success : function (data) {
      if(data == 'noLogin'){
        location.href=window.location.href;  
      }else
      if (data == 'SYSTEM_EXCEPTION') {
        $("#mask").show();
        $("#alertLayer-8").show();
      }else{
        var promoPrincipal=0;
        if(typePromo=='mjq'){
          promoPrincipal=data.promoPrincipal; 
        }
      $("#expectIncomeSpan").html(
          "预计到账收益 <span class=\"amountA orange pr\">"
              + format_money(data.actualIncome) + "+"
              + format_money(parseFloat(data.addtionalIncome)+parseFloat(promoPrincipal))
              + "<i class=\"icon icon-give pa\"></i></span> 元");
      $("#expectIncomeSpan").show();
      var expectIncome = parseFloat(data.actualIncome)
          + parseFloat(data.addtionalIncome);
      $("#expectIncome").val(expectIncome);
    }
    }
  });
}

function updateVoucher () {
  $("#mask").css('display', 'block');
//  $("#alertLayer").css('display', 'block');
  $("#alertLayer-3").css('display', 'block');
}
function deleteVoucher () {
  $("#voucher").css('display', 'block');
  $("#modVouchered").css('display', 'none');
  voucherFlag = false;
  // 除去优惠券预计收益
  var expectIncome = $("#expectIncomeNoVoucher").val();
  $("#expectIncomeSpan").html(
      "预计到账收益 <span class=\"amountA orange pr\">" + format_money(expectIncome)
          + "</span> 元");
  $("#expectIncomeSpan").show();
  $("#expectIncome").val(expectIncome);
  promoNo = "";
}

function getInfo (id) {
  location.href = "fixed/productDetailInfo?productId=" + id;
}
function toBuy () {
  var productId = $("#productId").val();
  var expectIncome = $("#expectIncome").val();
  var amount = $("#buyAmount").val();
  var channel = $("#channel").val();
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
  // var promoNo=$("#promoNo").val();
  var rechargeAmount = $("#rechargeAmount").val();
  var buyAmount = $("#buyAmount").val();
  if (rechargeAmount == 0) {// 去确认购买页面
    location.href = "fixed/toBuyProductInfo?productId=" + productId
        + "&expectIncomeNoVoucher=" + expectIncome + "&promoNo=" + promoNo
        + "&totalPays=" + buyAmount + "&promoType=" + typePromo+ "&addRate=" + addRate + "&channel=" + channel;
  } else {// 去充值页面
    location.href = "asset/toRecharge?amount=" + rechargeAmount
        + "&ret=fixed&productId=" + productId + "&expectIncome=" + expectIncome
        + "&promoNo=" + promoNo+ "&promoType=" + typePromo+ "&addRate=" + addRate;
  }
  // alert(1);
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
  if ($("#buyAmount").val() == null || $("#buyAmount").val() == '') {
    errorMsg = "购买金额为空";
    flag = true;
  } else {
    buyAmount = parseFloat($("#buyAmount").val());
    if (buyAmount < cillAmount) {
      errorMsg = "购买金额必须大于起投金额";
      flag = true;

    } else if (buyAmount > surplusAmount) {
      errorMsg = "购买金额不能大于剩余投资金额";
      flag = true;
    } else if (buyAmount % unitAmount != 0) {
      errorMsg = "购买金额应该是递增金额的整数倍";
      flag = true;
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
    var rechargeAmount = parseFloat(buyAmount) - parseFloat(accountAmount);
    $("#submit").val("去充值" + format_money(rechargeAmount) + " 元");
    $("#rechargeAmount").val(rechargeAmount);
    //由于懒猫账号余额可能有小数点后三位的值，即厘，故用去尾法
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
  if (voucherFlag == false) {
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

}
