$(function(){/*
   tabsSwiper = new Swiper('#tabs-container', {
    speed: 500,
    onSlideChangeStart: function () {
      $(".tabs .on").removeClass('on')
      $(".tabs li,.tabs a").eq(tabsSwiper.activeIndex).addClass('on')
      setTimeout(function(){
        var type = $(this).attr("id");
        var id = $(".swiper-slide-active").find("li").eq(1).attr("title");
        getProductById(id);
        
      },100);
    }
  })
  $(".tabs li,.tabs a").on('touchstart mousedown', function (e) {
    e.preventDefault()
    $(".tabs .on").removeClass('on')
    $(this).addClass('on')
    tabsSwiper.slideTo($(this).index())
    var type = $(this).attr("id");
    var id = $(".swiper-slide-active").find("li").eq(1).attr("title");
    getProductById(id);

  })
  $(".tabs li,.tabs a").click(function (e) {
    e.preventDefault()
  })
*/})


var typePromo = '';// 全局参数：优惠券类型
var promoNo = '';// 优惠券id
var voucherFlag = false;// 是否使用优惠券标示
// var clickFlag=false;//优惠券列表的onclick事件是否有效
function getInfo (id) {
  location.href = "fixed/productDetailInfo?productId=" + id;
}

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
  
  
  /*
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
              + "</ul>"
              + "   <a href=\"javascript:void(0)\" onclick=\"monyMove(this)\" class=\"icon icon-arrow-l prev pa\"></a><a   onclick=\"monyMove(this)\" href=\"javascript:void(0)\" class=\"icon icon-arrow-r next pa\"></a>";
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
                + "</ul>"
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

$(document).ready(function(){/*
  
  $(function () {
    
    var firstColumn = $("#firstColumn").val();
    $(".tabs .on").removeClass('on');
    switch (firstColumn) {
      case 'MONY':
        $("#month").addClass("on");
        tabsSwiper.slideTo(0);
        break;
      case 'QUAY':
        $("#quarter").addClass("on");
        tabsSwiper.slideTo(1);
        break;
      case 'YEAY':
        $("#year").addClass("on");
        tabsSwiper.slideTo(2);
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
          str = commonSwitch(data, firstColumn,productId);
          //方案二
//        str = commonSwitch(data);
          switch (firstColumn) {
            case 'MONY':
              $("#show_day").html(str);
              break;
            case 'QUAY':
              $("#show_month").html(str);
              break;
            case 'YEAY':
              $("#show_year").html(str);
              break;
            default:
              break;
          }
        }
      }
    });
    // 已售完
    var surplusAmount = $("#surplus").val();
    var status=$("#status").val();
    if(surplusAmount==0||status=='PAUSE'){
      $("#submit").attr("disabled", true);
      $("#submit").addClass('btnBuy-gray');
      $("#submit").val('已售完');
    } else {
      $("#submit").attr("disabled", false);
      $("#submit").removeClass('btnBuy-gray');
      $("#submit").val('立即购买');
    }
    // 格式化
    $("#buyDay").html(format_date_MMDD($("#buyDay").html()));
    $("#incomeDay").html(format_date_MMDD($("#incomeDay").html()));
    $("#expireDay").html(format_date_MMDD($("#expireDay").html()));
    $("#arrivalDay").html(format_date_MMDD($("#arrivalDay").html()));
    
  });
*/})

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
      if (data == 'SYSTEM_EXCEPTION') {
        $("#mask").show();
        $("#alertLayer-8").show();
      } else {
        $(".buyDay").html(format_date_MMDD(data.buyDay));
        $(".incomeDay").html(format_date_MMDD(data.incomeDay));
        $(".expireDay").html(format_date_MMDD(data.expireDay));
//        $("#arrivalDay_"+productType).html(format_date_MMDD(data.arrivalDay));
        $(".periodNo").html("第" + format_money(data.periodNo) + "期");
        $("#surplusAmount").html(
            "产品剩余额度：" + fmoney(data.surplusAmount) + " 元");
        $("#surplus").val(data.surplusAmount);
        $("#status").val(data.status);
        $("#productId").val(data.productId); 
        $("#firstColumn").val(data.firstColumn);
        //已售完
        if(data.surplusAmount==0||data.status=='PAUSE'){
          $("#submit").attr("disabled", true);
          $("#submit").addClass('btnBuy-gray');
          $("#submit").val('已售完');
        } else {
          $("#submit").attr("disabled", false);
          $("#submit").removeClass('btnBuy-gray');
          $("#submit").val('立即购买');
        }
      }
    }
  });
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
  $("#alertLayer-5 .pr a").eq(2).attr('href',
      "account/toLogin?returnFlag=toBuy&productId=" + productId);
  $("#alertLayer-5 .pr a").eq(1).attr('href',
      "account/toRegister?returnFlag=toBuy&productId=" + productId);
  $("#mask").show();
  $("#alertLayer-5").show();
}
// 节点移动，月月盈，二个移动  "MONY","QUAY","YEAY"
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
//    $third_li=$second_li;
    $first_li.addClass('on');
  }
  getProductById(id,"MONY");
}
//三个移动
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
  getProductById(id,"QUAY");
}
/**
 * 旋转代替，俩个移动
 */
function monyMove (obj) {
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
//三个节点移动
function quayMove (obj,type) {
  var $parent = $(obj).parent(".showQiuQiu").find("li");
  var $first_li = $parent.eq(0); // 获取<ul>节点中第一个<li>元素节点
  var $last_li = $parent.eq($parent.length - 1); // 获取<ul>节点中最后个<li>元素节点
  if (type == 1) {
    $parent.eq(1).removeClass('on');
    $last_li.insertBefore($first_li);
    $first_li.addClass('on');
    getProductById($first_li.attr('title'));
  } else {
    $parent.eq(1).removeClass('on');
    $first_li.insertAfter($last_li);
    $last_li.addClass('on');
    getProductById($last_li.attr('title'));
  }
}

function clean () {
  $("#mask").hide();
  $("#alertLayer-5").hide();
  $("#alertLayer-8").hide();
}