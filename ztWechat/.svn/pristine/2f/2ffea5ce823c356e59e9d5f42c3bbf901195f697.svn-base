
var typePromo = '';// 全局参数：优惠券类型
var promoNo = '';// 用户优惠券id
var addRate='';//加息券所加利率
var voucherFlag = false;// 是否使用优惠券标示
// var clickFlag=false;//优惠券列表的onclick事件是否有效

//定期理财页面
currentIndex = 0;
$(document).ready(function() {
  var length,
    interval,
    hasStarted = true, //是否已经开始轮播
    t = 3000; //轮播时间间隔
  length = $('.slider-panel').length;
  //将除了第一张图片隐藏
  $('.slider-panel:not(:first)').hide();
  var proId;
  //将第一个slider-item设为激活状态
  /*$('.slider-item:first').addClass('slider-item-selected');*/
  //隐藏向前、向后翻按钮
  $('.slider-page').show();
  $('.slider-item').click(function(e) {
    stop();
    var preIndex = $(".slider-item").filter(".slider-item-selected").index();
    currentIndex = $(this).index();
    play(preIndex, currentIndex);
    proId = $(".slider-item-selected").data("proid");
    getProductById (proId) 
  });
  $('.slider-pre').unbind('click');
  $('.slider-pre').bind('click', function() {
    pre();
  });
  $('.slider-next').unbind('click');
  $('.slider-next').bind('click', function() {
    next();
  });
  
  
  /* 向前翻页*/
  function pre() {
    var preIndex = currentIndex;
    currentIndex = (--currentIndex + length) % length;
    play(preIndex, currentIndex);
    proId = $(".slider-item-selected").data("proid");
    getProductById (proId) 
    
  }
  /* 向后翻页*/
  function next() {
    var preIndex = currentIndex;
    currentIndex = ++currentIndex % length;
    play(preIndex, currentIndex);
    proId = $(".slider-item-selected").data("proid");
    getProductById (proId) 
  }
  /**
   * 从preIndex页翻到currentIndex页
   * preIndex 整数，翻页的起始页
   * currentIndex 整数，翻到的那页
   */
  function play(preIndex, currentIndex) {
    $('.slider-panel').eq(preIndex).hide()
      .parent().children().eq(currentIndex).show();
    $('.slider-item').removeClass('slider-item-selected');
    $('.slider-item').eq(currentIndex).addClass('slider-item-selected');
  }
  /**
   * 开始轮播
   */
  function start() {
    if(!hasStarted) {
      hasStarted = false;
      interval = setInterval(next, t);
    }
  }
  /**
   * 停止轮播
   */
  function stop() {
    clearInterval(interval);
    hasStarted = true;
  }
  //开始轮播
  start();
});


$(function () {
//恢复清除图标
  $('.amount-text').keyup(function(){
    $(this).parent().siblings('.icon.icon-error.fr').show();
  });
  
  var firstColumn = $("#firstColumn").val();
  var productId = $("#productId").val();
  $('[data-proid='+productId+']').addClass("slider-item-selected");
  //显示小球球
  $('.slider-panel').hide();
  currentIndex = $(".slider-item").filter(".slider-item-selected").index();
  $('.slider-panel').parent().children().eq(currentIndex).show();
  // 信托理财布局（月月盈2个产品，季季盈3个，年年盈1个）
  getProductById(productId);

  $.ajax({/*
    url : "fixed/getProductInfo",
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
      if (data == 'SYSTEM_EXCEPTION') {
        $("#mask").show();
        $("#alertLayer-8").show();
      } else {
        //方案一
      str = commonSwitch(data, firstColumn);
      //方案二
//      str = commonSwitch(data);
        $(".pro-tab.font-white.tc.clearfix.pr").html(str);
      }
    }
  */});
  /*var surplusAmount=$("#surplus").val();
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
  }*/
  // 格式化
  /*$(".buyDay").html(format_date_MMDD($(".buyDay").html()));
  $(".incomeDay").html(format_date_MMDD($(".incomeDay").html()));
  $(".expireDay").html(format_date_MMDD($(".expireDay").html()));*/
//  $("#arrivalDay").html(format_date_MMDD($("#arrivalDay").html()));

  //  优惠券
  queryPromotionInfomationsInit(productId);

});

$(function () {/*
//恢复清除图标
  $('.amount-text').keyup(function(){
    $(this).parent().siblings('.icon.icon-error.fr').show();
  });
  
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
  var productId = $("#productId").val();
  // 信托理财布局（月月盈2个产品，季季盈3个，年年盈1个）
  $.ajax({
    url : "fixed/getProductInfo",
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
      if (data == 'SYSTEM_EXCEPTION') {
        $("#mask").show();
        $("#alertLayer-8").show();
      } else {
        //方案一
      str = commonSwitch(data, firstColumn);
      //方案二
//      str = commonSwitch(data);
        $(".pro-tab.font-white.tc.clearfix.pr").html(str);
      }
    }
  });
  var surplusAmount=$("#surplus").val();
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
  $("#buyDay").html(format_date_MMDD($("#buyDay").html()));
  $("#incomeDay").html(format_date_MMDD($("#incomeDay").html()));
  $("#expireDay").html(format_date_MMDD($("#expireDay").html()));
//  $("#arrivalDay").html(format_date_MMDD($("#arrivalDay").html()));

  //  优惠券
  queryPromotionInfomationsInit(productId);

*/});
function toBuy () {
  var productId = $("#productId").val();
  var expectIncome = $("#expectIncome").val();
  var amount = $("#buyAmount").val();
  if (amount == null || amount == '' || amount == 0) {
    $("#submit").attr("disabled", true);
    $("#submit").addClass('btnBuy-gray');
    $("#messageError").html(
        "<i class=\"icon icon-error2\">"
            + " 请输入正确购买金额");
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
//	    + "&ret=fixed&productId=" + productId + "&expectIncome=" + expectIncome;
	  location.href="account/card/toBindCard?returnUrl=/asset/toRecharge?productId="+productId+"&amount="+ rechargeAmount
	  + "&ret=fixed"+"&expectIncome=" + expectIncome;
	  return;
  }
  if (rechargeAmount == 0) {// 去确认购买页面
    location.href = "fixed/toBuyProductInfo?productId=" + productId
        + "&expectIncomeNoVoucher=" + expectIncome + "&promoNo=" + promoNo
        + "&totalPays=" + buyAmount + "&promoType=" + typePromo+ "&addRate=" + addRate;
  } else {// 去充值页面
    location.href = "asset/toRecharge?amount=" + rechargeAmount
        + "&ret=fixed&productId=" + productId + "&expectIncome=" + expectIncome
        + "&promoNo=" + promoNo+ "&promoType=" + typePromo+ "&addRate=" + addRate;
  }
  // alert(1);
}
function getInfo (id) {
  location.href = "fixed/productDetailInfo?productId=" + id;
}
$(function () {/*
  var arry = ["MONY","QUAY","YEAY"];
  // 信托理财布局（月月盈2个产品，季季盈3个，年年盈1个）
  for(var ind =0; ind < arry.length; ind++) {
    $.ajax({
      url : "fixed/getSecondColumn",
      type : 'post',
      dataType : 'json',
      data : {
        firstColumn : arry[ind]
      },
      error : function () {
        $("#mask").show();
        $("#alertLayer-8").show();
      },
      success : function (data) {
        if (data == 'SYSTEM_EXCEPTION') {
          $("#mask").show();
          $("#alertLayer-8").show();
        } else {
          
          switch (data.length) {
            case 2:
              strMONY = '';
              str2 = "<li  title=\""
                + data[0].productId
                + "\" class=\"on\" onclick=\"mony(this,"
                + data[0].productId
                + ")\"><a href=\"javascript:void(0)\"> <p class=\"font-16\">"
                + data[0].termDay
                
                + "天</p><p class=\"font-10\">预计年化收益率</p><p class=\"font-40\"><span class=\"font-30\">"
                + format_yearRate(data[0].yearRate)
                + "%</span></p><p class=\"font-10\">查看详情&gt;</p></a></li>";
              str1 = "<li   title=\""
                + data[1].productId
                + "\" onclick=\"mony(this,"
                + data[1].productId
                + ")\"><a href=\"javascript:void(0)\"> <p class=\"font-16\">"
                + data[1].termDay
                
                + "天</p><p class=\"font-10\">预计年化收益率</p><p class=\"font-40\"><span class=\"font-30\">"
                + format_yearRate(data[1].yearRate)
                + "%</span></p><p class=\"font-10\">查看详情&gt;</p></a></li>";
              strMONY = strMONY
              + str1
              + str2
              + str1
              + "<a href=\"javascript:void(0)\" onclick=\"monyMove(this)\" class=\"icon icon-arrow-l prev pa\"></a><a   onclick=\"monyMove(this)\" href=\"javascript:void(0)\" class=\"icon icon-arrow-r next pa\"></a>";
              ;
              if(data[0].firstColumn == "MONY") {
                getDaysByProductId(data[0].productId,"MONY");
                $("#show_day").html(strMONY);
              } else if(data[0].firstColumn == "QUAY"){
                getDaysByProductId(data[0].productId,"QUAY");
                $("#show_month").html(strMONY);
              } else {
                getDaysByProductId(data[0].productId,"YEAY");
                $("#show_year").html(strMONY);
              }
              break;
            case 3:
              strQUAY="";
              var str1 = 0;
              var str2 = 0;
              var str3 = 0;
              str1 = "<li  title=\""
                + data[0].productId
                + "\"  onclick=\"quay(this,"
                + data[0].productId
                + ")\"><a href=\"javascript:void(0)\"> <p class=\"font-16\">"
                + data[0].termDay
                
                + "天</p><p class=\"font-10\">预计年化收益率</p><p class=\"font-40\"><span class=\"font-30\">"
                + format_yearRate(data[0].yearRate)
                + "%</span></p><p class=\"font-10\">查看详情&gt;</p></a></li>";
              str2 = "<li title=\""
                + data[1].productId
                + "\"  class=\"on\" onclick=\"quay(this,"
                + data[1].productId
                + ")\"><a href=\"javascript:void(0)\"> <p class=\"font-16\">"
                + data[1].termDay
                
                + "天</p><p class=\"font-10\">预计年化收益率</p><p class=\"font-40\"><span class=\"font-30\">"
                + format_yearRate(data[1].yearRate)
                + "%</span></p><p class=\"font-10\">查看详情&gt;</p></a></li>";
              str3 = "<li  title=\""
                + data[2].productId
                + "\"  onclick=\"quay(this,"
                + data[2].productId
                + ")\"><a href=\"javascript:void(0)\"> <p class=\"font-16\">"
                + data[2].termDay
                + "天</p><p class=\"font-10\">预计年化收益率</p><p class=\"font-40\"><span class=\"font-30\">"
                + format_yearRate(data[2].yearRate)
                + "%</span></p><p class=\"font-10\">查看详情&gt;</p></a></li>";
              strQUAY = str1
                + str2
                + str3
                + "   <a href=\"javascript:void(0)\" onclick=\"quayMove(this,2)\" class=\"icon icon-arrow-l prev pa\"></a><a   onclick=\"quayMove(this,1)\" href=\"javascript:void(0)\" class=\"icon icon-arrow-r next pa\"></a>";
              if(data[1].firstColumn == "MONY") {
                getDaysByProductId(data[1].productId,"MONY");
                $("#show_day").html(strQUAY);
              } else if(data[1].firstColumn == "QUAY"){
                getDaysByProductId(data[1].productId,"QUAY");
                $("#show_month").html(strQUAY);
              } else {
                getDaysByProductId(data[1].productId,"YEAY");
                $("#show_year").html(strQUAY);
              }
              break;
            case 1:
              strYEAY = "<li ></li><li class=\"on\"   title=\""
                + data[0].productId+"\""
                +"onclick=\"getInfo("
                + data[0].productId
                + ")\"><a href=\"javascript:void(0)\"> <p class=\"font-16\">"
                + data[0].termDay
                + "天</p><p class=\"font-10\">预计年化收益率</p><p class=\"font-40\"><span class=\"font-30\">"
                + format_yearRate(data[0].yearRate)
                + "%</span></p><p class=\"font-10\">查看详情&gt;</p></a></li><li></li>";
              if(data[0].firstColumn == "MONY") {
                getDaysByProductId(data[0].productId,"MONY");
                $("#show_day").html(strYEAY);
              } else if(data[0].firstColumn == "QUAY"){
                getDaysByProductId(data[0].productId,"QUAY");
                $("#show_month").html(strYEAY);
              } else {
                getDaysByProductId(data[0].productId,"YEAY");
                $("#show_year").html(strYEAY);
              }
              break;
            default:
              break;
          }
          
        }
      }
    });
  }

*/});

function getDaysByProductId (productId,productType) { // 动态改变产品信息
  $.ajax({
    url : "fixed/getProductById",
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
      if (data == 'SYSTEM_EXCEPTION') {
        $("#mask").show();
        $("#alertLayer-8").show();
      } else {
        $("#buyDay_"+productType).html(format_date_MMDD(data.buyDay));
        $("#incomeDay_"+productType).html(format_date_MMDD(data.incomeDay));
        $("#expireDay_"+productType).html(format_date_MMDD(data.expireDay));
        $("#arrivalDay_"+productType).html(format_date_MMDD(data.arrivalDay));
      }
    }
  });
}

function getProductById (productId) { // 动态改变产品信息
  $.ajax({
    url : "fixed/getProductById",
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
      if(data=='SYSTEM_EXCEPTION'){
        $("#mask").show();
        $("#alertLayer-8").show();  
      }else{
      $(".buyDay").html(format_date_MMDD(data.buyDay));
      $(".incomeDay").html(format_date_MMDD(data.incomeDay));
      $(".expireDay").html(format_date_MMDD(data.expireDay));
//      $("#arrivalDay_"+productType).html(format_date_MMDD(data.arrivalDay));
      $(".periodNo").html("第" + format_money(data.periodNo) + "期");
      $("#surplusAmount").html(
          "产品剩余额度：" + fmoney(data.surplusAmount) + " 元");
      $("#buyAmount").attr(
          'placeholder',
          "起购金额" + format_money(data.cillAmount) + " 元,递增金额"
              + format_money(data.unitAmount) + " 元");
      // 动态改变验证需要值
      $("#yearRate").val(format_yearRate(data.yearRate));
      $("#termDay").val(data.termDay);
      $("#cillAmount").val(data.cillAmount);
      $("#unitAmount").val(data.unitAmount);
      $("#surplus").val(data.surplusAmount);
      $("#status").val(data.status);
      $("#productId").val(data.productId); 
      $("#firstColumn").val(data.firstColumn);
      //修改产品时，清空相关数据
      $("#buyAmount").val('');
      $("#voucher").css('display', 'block');
      $("#modVouchered").css('display', 'none');
      $("#expectIncomeSpan").html('');
      queryPromotionInfomationsInit(productId);
      //已售完
      if(data.surplusAmount==0||data.status=='PAUSE'){
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
      }
    }
  });
}
// 购买金额失去焦点
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
  var filter=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
  var flagFilter=filter.test($("#buyAmount").val());

  if(false==flagFilter){ //输入金额是否满足数字并小数点不超过后俩位
    errorMsg="购买金额必须为数字，小数点不能超过2位"; 
    flag = true;
  }else if ($("#buyAmount").val() == null || $("#buyAmount").val() == '') {
    errorMsg = "请输入购买金额";
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
    }
  }

  if (flag) {
    $("#submit").attr("disabled", true);
    $("#submit").addClass('btnBuy-gray');
    //  错误字
    $("#messageError").html(
        "<i class=\"icon icon-error2 \">"+" "
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
    //由于懒猫账号余额可能有小数点后三位的值，即厘，故用去尾法
    var rechargeAmountFloat = parseFloat(buyAmount)- formatNumberFloor(parseFloat(accountAmount),2);
    var rechargeAmount=rechargeAmountFloat.toFixed(2);
    $("#submit").val("去充值" + rechargeAmount + " 元");
    $("#rechargeAmount").val(rechargeAmount);// 记录需充值金额
  }else{
    $("#submit").val('立即购买');
    $("#rechargeAmount").val(0);
  }
  if (voucherFlag == false) {
    // 没有调用后台，自己根据规则算的收益 待研究
    var expectIncome = (buyAmount * yearRate * termDay) / 365 / 100;
    $("#expectIncomeSpan").html(
        "预计到账收益 <span class=\"amountA orange pr\">"
            + format_money(expectIncome) + "</span>  元");
    $("#expectIncomeSpan").show();
    $("#expectIncome").val(expectIncome);
    $("#expectIncomeNoVoucher").val(expectIncome);
  } else {
    // getVouchersInfo();//过滤可用优惠券
    // updateVoucher();//自动显示优惠券列表
    deleteVoucher();
  }

}
function getText () {
  $("#text").attr('class', 'orange').html('请直接输入购买金额，账户余额不足将先进行充值');
}
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
            "<i class=\"icon icon-add\" </i> 添加优惠券 (共" + data.length + "张)");
        $("#voucher").show();
      }else {// 去除蒙层效果
//        $("#voucher").attr('class', '');
        $("#voucher").html('');
        $("#alertLayer-3").html();
      }
    }
  });
}

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
                      strCom = format_money(itmes.amount) + " 元理财金（满"
                          + format_money(itmes.orderAmountLower) + "可用）";
                      type = "投资时赠送" + format_money(itmes.amount) + " 元理财金";
                      strType = format_money(itmes.amount) + " 元理财金";
                      typeInfo = "lcj";
                    } else {// 满加券
                      strCom = "投资券（满" + format_money(itmes.orderAmountLower) + "加"
                          + format_money(itmes.amount) + "）";
                      type = "投资时赠送" + format_money(itmes.amount) + " 元本金";
                      strType = '投资券';
                      typeInfo = "mjq";
                    }
                  } else {// 加息券
                    strCom = "加息券（加" + itmes.addtionalRate + "%利息）";
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
                    "<i class=\"icon icon-add\" </i> 添加优惠券 (共" + data.length
                        + "张)");
            $("#voucherList").html(str);
          } else {// 去除蒙层效果
            $("#voucher").html('');
            $("#alertLayer-3").html();
          }
        }
      });
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
              + "<span class=\"modVouchers pr orange\"><i class=\"icon icon-vouchers3\"></i><i class=\"font-white modVoucherText pa\">"
              + strType
              + "</i></span></p><p class=\"modTips lcAccont orange\">"
              + type
              + "</p></li>");
  $("#modVouchered").css('display', 'block');
  getExpectIncomeVoucher(id);// 动态修改预计收益
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
function getVouchersInfo () {// 过滤可使用优惠券
  var amount = $("#buyAmount").val();
  var productId = $("#productId").val();
  queryPromotionInfomations(productId, amount);
  $("#mask").css('display', 'block');
  $("#alertLayer-3").css('display', 'block');
}



// 节点移动，月月盈
function mony (obj, id) {
  var $index = $(obj).index(); // 点击的坐标
  var $parent = $(obj).parent(".showQiuQiu").find("li");
  var $first_li = $parent.eq(0); // 获取<ul>节点中第一个<li>元素节点
  var $second_li = $parent.eq(1); // 获取<ul>节点中第二个<li>元素节点
  var $third_li = $parent.eq(2); // 获取<ul>节点中第三个<li>元素节点

  if ($(obj).attr('class') == 'on') {
    getInfo(id);
    return false;
  }
  if ($index == 0 || $index == 2) {
    $parent.eq(1).removeClass('on');
    $second_li.insertBefore($first_li);
    $third_li.find("p:eq(0)").text($second_li.find("p:eq(0)").text());
    $third_li.attr('title',$second_li.attr('title'));
    $third_li.attr('onclick',$second_li.attr('onclick'));
    $first_li.addClass('on');
  }
  getProductById(id);
}
function quay (obj, id) {
  var $index = $(obj).index(); // 点击的坐标
  var $parent = $(obj).parent(".showQiuQiu").find("li");
  var $first_li = $parent.eq(0); // 获取<ul>节点中第一个<li>元素节点
  var $last_li = $parent.eq($("#show_month li").length - 1); // 获取<ul>节点中最后个<li>元素节点
  if ($(obj).attr('class') == 'on') {
    getInfo(id);
    return false;
  }
  if ($index == 0) {
    $parent.eq(1).removeClass('on');
    $last_li.insertBefore($first_li);
    $(obj).addClass('on');
  }
  if ($index == ($("#show_month li").length - 1)) {
    $parent.eq(1).removeClass('on');
    $first_li.insertAfter($last_li);
    $(obj).addClass('on');
  }
  getProductById(id);
}
/**
 * 旋转代替
 */
function monyMove(obj){
  var $parent = $(obj).parent(".showQiuQiu").find("li");
  var $first_li = $parent.eq(0); // 获取<ul>节点中第一个<li>元素节点
  var $second_li = $parent.eq(1); // 获取<ul>节点中第二个<li>元素节点
  var $third_li = $parent.eq(2); // 获取<ul>节点中第三个<li>元素节点
  
  $parent.eq(1).removeClass('on');
  $second_li.insertBefore($first_li);
  $($third_li).find("p:eq(0)").text($second_li.find("p:eq(0)").text());
  $($first_li).addClass('on');
  
  getProductById($first_li.attr('title'));
}
function quayMove(obj,type){
  var $parent = $(obj).parent(".showQiuQiu").find("li");
  var $first_li = $parent.eq(0); // 获取<ul>节点中第一个<li>元素节点
  var $last_li = $parent.eq($parent.length - 1); // 获取<ul>节点中最后个<li>元素节点
  if(type==1){
    $parent.eq(1).removeClass('on');
    $last_li.insertBefore($first_li);
    $first_li.addClass('on');
    getProductById($first_li.attr('title'));
  }else{
    $parent.eq(1).removeClass('on');
    $first_li.insertAfter($last_li);
    $last_li.addClass('on');
    getProductById($last_li.attr('title'));
  }
}
function clean(){
  $("#mask").hide();
  $("#alertLayer-8").hide();
}
    