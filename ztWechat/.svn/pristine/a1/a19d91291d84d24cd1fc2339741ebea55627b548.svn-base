var myScroll;
var totalPage = 0;
var pageSize = 0;
var pageIndex = 1;
//代码优化
var scbData=null;
var fixedData=null;
var fundData=null;
var totalData=null;
function downAjax () {// 下拉刷新
  var title = $(".on").attr('title');
  var url = '';
  switch (title) {
  case 'scb':
    url = 'asset/queryAccumulativeIncome';
    break;
  case 'fixed':
    url = 'asset/queryTrustIncome';
    break;
  case 'total':
    url = 'asset/queryTotalIncome';
    break;
  case 'fund':
    url = 'asset/queryFundIncome';
    break;
  default:
    break;
  }
  pageIndex = 1;
  $
      .ajax({
        url : url,
        type : 'post',
//        async: false,
        dataType : 'json',
        data : {
          pageIndex : 1
        },
        error : function () {
          //解决请求未完成，返回异常问题
/*          $("#mask").show();
          $("#alertLayer-8").show();*/
        },
        success : function (data) {

          if (data == 'SYSTEM_EXCEPTION') {
            $("#mask").show();
            $("#alertLayer-8").show();
          } else if (data == 'noLogin') {
            location.href = window.location.href;
          } else if (data == 'NULL_RESULT') {
            $("#scroller").html('');
          } else {
            //刷新记录data
            switch (title) {
            case 'scb':
              scbData=data;
              break;
            case 'fixed':
              fixedData=data;
              break;
            case 'fund':
              fundData=data;
              break;
            case 'total':
              totalData=data;
              break;
            }
            var str = '';
            var strButton = '';
            var strTop = "<div id=\"scroller-pullDown\"><span id=\"down-icon\" class=\"icon-double-angle-down pull-down-icon\"></span><span id=\"pullDown-msg\" class=\"pull-down-msg\">下拉刷新</span></div><div id=\"result\">";
            pageSize = data.pageSize;
            $("#pageSize").val(pageSize);
            $("#totalPage").val(pageTotal);
            var pageTotal = data.pageTotal;
            var rowTotal = data.rowTotal;
            var dataResult=[];
            var maxvalue = data.maxValue;
            if (rowTotal != 0) {
              for ( var int = 0; int < pageSize; int++) {
                if((pageIndex-1)*pageSize+int>rowTotal-1){
                  continue;
                }
                dataResult[int]=data.list[(pageIndex-1)*pageSize+int];
              }
              str = strTop;
              str=queryCommon(dataResult,str);
              str = str + "</div>";
              if (pageTotal != 1) {
                strButton = " <div id=\"scroller-pullUp\"><span id=\"up-icon\" class=\"icon-double-angle-up pull-up-icon\"></span><span id=\"pullUp-msg\" class=\"pull-up-msg\">加载更多</span></div>";
              }else{
                strButton = " <div id=\"scroller-pullUp\" style=\"display:none\"><span id=\"up-icon\" class=\"icon-double-angle-up pull-up-icon\"></span><span id=\"pullUp-msg\" class=\"pull-up-msg\">加载更多</span></div>";
              }
              str = str + strButton;
            }
            $("#scroller").css('text-align','');
            $("#scroller").html(str);
            drawing(dataResult,this.data.split('=')[1],maxvalue);
          }
          switch (title) {
          case 'scb':
            $("#scbIncome").css('display', 'block');
            $("#fixedIncome").css('display', 'none');
            $("#fundIncome").css('display', 'none');
            $("#totalIncome").css('display', 'none');
            break;
          case 'fixed':
            $("#fixedIncome").css('display', 'block');
            $("#scbIncome").css('display', 'none');
            $("#fundIncome").css('display', 'none');
            $("#totalIncome").css('display', 'none');
            break;
          case 'fund':
            $("#fundIncome").css('display', 'block');
            $("#fixedIncome").css('display', 'nond');
            $("#scbIncome").css('display', 'none');
            $("#totalIncome").css('display', 'none');
            break;
          case 'total':
            $("#totalIncome").css('display', 'block');
            $("#scbIncome").css('display', 'none');
            $("#fundIncome").css('display', 'none');
            $("#fixedIncome").css('display', 'none');
            break;
          default:
            break;
          }
          myScroll.refresh();
        }
      });

}
function upAjax () {// 上滑加载更多

  pageIndex++;
  var pageTotal = $("#totalPage").val();
  if (pageIndex > parseInt(pageTotal)) {// 当前页数大于总页数时
    $("#scroller-pullUp").hide();
    return false;
  } else if (pageIndex == parseInt(pageTotal)) {
    $("#scroller-pullUp").hide();
  }
  var elInit = $("#result");
  var url = '';
  var title = $(".on").attr('title');
/*  switch (title) {
  case 'scb':
    url = 'asset/queryAccumulativeIncome';
    break;
  case 'fixed':
    url = 'asset/queryTrustIncome';
    break;
  case 'total':
    url = 'asset/queryTotalIncome';
    break;
  default:
    break;
  }*/
  switch (title) {
  case 'scb':
    data=scbData;
    break;
  case 'fixed':
    data=fixedData;
    break;
  case 'fund':
    data=fundData;
    break;
  case 'total':
    data=totalData;
    break;
  default:
    break;
  }
  
  queryPage(pageIndex,data,elInit);
   
}    
    
 /* $
      .ajax({
        url : url,
        type : 'post',
        dataType : 'json',
        data : {
          pageIndex : pageIndex
        },
        error : function () {

          $("#mask").show();
          $("#alertLayer-8").show();
        },
        success : function (data) {

          if (data == 'SYSTEM_EXCEPTION') {
            $("#mask").show();
            $("#alertLayer-8").show();
          } else if (data == 'noLogin') {
            location.href = window.location.href;
          } else if (data == 'NULL_RESULT') {
            // 不会出现这种情况
          } else {
            var str = '';
            var pageTotal = data.pageTotal;
            var rowTotal = data.rowTotal;
//            var maxvalue = data.maxValue;
            $("#totalPage").val(pageTotal);
            if (rowTotal != 0) {
         str=queryCommon(data,str);
            }
            elInit.append(str);
            drawing(data,this.data.split('=')[1]);
          }
          myScroll.refresh();
        }
      });
};*/

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
      }else if(this.maxScrollY>0&&-this.y>distance){
        $("#pullUp-msg").html('松手开始加载...');
        flagUp = true;
      }
      else
      if (this.maxScrollY<0&&this.maxScrollY - this.y > distance
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
function queryPage(pageIndex,data,elInit){
  var str = '';
  var pageTotal = data.pageTotal;
  var rowTotal = data.rowTotal;
  var maxvalue = data.maxValue;
  $("#totalPage").val(pageTotal);
  var dataResult=[];
  if (rowTotal != 0) {
    for ( var int = 0; int < pageSize; int++) {
      if((pageIndex-1)*pageSize+int>rowTotal-1){
        continue;
      }
      dataResult[int]=data.list[(pageIndex-1)*pageSize+int];
    }
str=queryCommon(dataResult,str);
  }
  elInit.append(str);
  drawing(dataResult,pageIndex,maxvalue);
myScroll.refresh();
}

function queryCommon(data,str){
  $
  .each(
      data,
      function (i, item) {
        str = str
            + "<div class=\"meter-wrap\"><meter low=\"0\" high=\"0\" max=\"0\"" 
            + " optimum=\"0\" value=\"0\"></meter><p class=\"value-wrap\"><span class=\"cur-date\">0000-00-00"
            + "</span><span class=\"cur-money\">0"
            + "</span></p></div>";
      });
  return str;
}
function drawing(data,pageIndex,maxvalues){
  // 动态修改参数值
  var maxvalue = parseFloat(maxvalues);
  $.each(data, function (i, item) {
    var per = item.money / maxvalue;
    var meterwidth = $('meter').width();
    var bgper = 140 / meterwidth;
//    pageSize = $("#pageSize").val();
    i = i + (parseInt(pageIndex) - 1) * pageSize;
    $('meter').eq(i).prop('max', maxvalue);
    $('meter').eq(i).next('p').children('span.cur-date').html(
        item.date == null ? '0000-00-00' : item.date);
    $('meter').eq(i).next('p').children('span.cur-money').html(
        item.money == null ? '0' : format_money(item.money));
    if (per < bgper) {
      $('meter').eq(i).val(maxvalue * 0.42);
      $('meter').eq(i).next('p').width('42%');
    } else {
      $('meter').eq(i).val(format_money(item.money));
      $('meter').eq(i).next('p').width(per * 100 + '%');
    }
  });
  $('meter').eq(0).prop('optimum', maxvalue);
  /*
   * for( var i=0; i<$('meter').length; i++ ) { var val =
   * $('meter').eq(i).val();
   * $('meter').eq(i).next('p').width(val+'%'); }
   */
  /* 计算区域高度 */
  var bodyH = $(document.body).height();
  var meterH = $('.deal-meter').height();
  meterH = bodyH - 40 > meterH ? bodyH - 40 : meterH;
  $('.deal-meter').height(meterH);
}
