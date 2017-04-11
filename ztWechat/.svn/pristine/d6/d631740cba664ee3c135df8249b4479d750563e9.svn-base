var myScroll;
var totalPage = 0;
var pageIndex = 1;
function downAjax () {// 下拉刷新
  var type = $('.on').attr('title');
  pageIndex = 1;
  $
      .ajax({
        url : 'fixed/bill',
        type : 'post',
        dataType : 'json',
        data : {
          status : type,
          pageIndex : 1
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
            var str = '';
            var strButton = '';
            var strTop = "<div id=\"scroller-pullDown\"><span id=\"down-icon\" class=\"icon-double-angle-down pull-down-icon\"></span><span id=\"pullDown-msg\" class=\"pull-down-msg\">下拉刷新</span></div><div id=\"result\">";

            var pageTotal = data.pageTotal;
            var rowTotal = data.rowTotal;
            $("#totalPage").val(pageTotal);
            if (rowTotal != 0) {
              str = strTop;
              str = queryCommon(data,str);
              str = str + "</div>";
              if (pageTotal != 1) {
                strButton = " <div id=\"scroller-pullUp\"><span id=\"up-icon\" class=\"icon-double-angle-up pull-up-icon\"></span><span id=\"pullUp-msg\" class=\"pull-up-msg\">加载更多</span></div>";
              }else{
                strButton = " <div id=\"scroller-pullUp\" style=\"display:none\"><span id=\"up-icon\" class=\"icon-double-angle-up pull-up-icon\"></span><span id=\"pullUp-msg\" class=\"pull-up-msg\">加载更多</span></div>";
              }
            }else{
              if(type=='noArrival'){
                str="<p class=\"loadingArea gray tc\">亲~ 暂无未到账产品</p>"; 
              }else{
                str="<p class=\"loadingArea gray tc\">亲~ 暂无已到账产品</p>"; 
              }
            }
            str = str + strButton;
            $("#scroller").html(str);
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
  var type = $('.on').attr('title');
  $.ajax({
    url : 'fixed/bill',
    type : 'post',
    dataType : 'json',
    data : {
      status : type,
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
      } else {
        var str = '';

        var pageTotal = data.pageTotal;
        var rowTotal = data.rowTotal;
        if (pageIndex > parseInt(pageTotal)) {// 当前页数大于总页数时
          $("#scroller-pullUp").hide();
          return false;
        }else if (pageTotal == pageIndex) {
          $("#scroller-pullUp").hide();
        }
        if (rowTotal != 0) {
          str = queryCommon(data,str);

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
    },
    checkDOMChanges:true,//是否自动检测内容变化   
    hScroll: false, //是否水平滚动  
    bounce: true, //是否超过实际位置反弹 
    momentum: true //动量效果，拖动惯性  
  });

}

/*document.addEventListener('DOMContentLoaded', function () {
  setTimeout(loaded, 200);
}, false);*/
