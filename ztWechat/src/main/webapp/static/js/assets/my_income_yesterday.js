$(function () {
  loaded();
  var type = $("#type").val();
  switch (type) {
  case 'scb':
    $("ul").children("li").attr('class', '');
    $("ul").children("li").eq(1).attr('class', 'on');
    $("#scbIncome").css('display', 'block');
    $("#fixedIncome").css('display', 'none');
    $("#fundIncome").css('display', 'none');
    $("#totalIncome").css('display', 'none');
    break;
  case 'fixed':
    $("ul").children("li").attr('class', '');
    $("ul").children("li").eq(2).attr('class', 'on');
    $("#fixedIncome").css('display', 'block');
    $("#scbIncome").css('display', 'none');
    $("#fundIncome").css('display', 'none');
    $("#totalIncome").css('display', 'none');
    break;
  case 'fund':
    $("ul").children("li").attr('class', '');
    $("ul").children("li").eq(3).attr('class', 'on');
    $("#fundIncome").css('display', 'block');
    $("#fixedIncome").css('display', 'none');
    $("#scbIncome").css('display', 'none');
    $("#totalIncome").css('display', 'none');
    break;
  case 'total':
    $("ul").children("li").attr('class', '');
    $("ul").children("li").eq(0).attr('class', 'on');
    $("#totalIncome").css('display', 'block');
    $("#scbIncome").css('display', 'none');
    $("#fundIncome").css('display', 'none');
    $("#fixedIncome").css('display', 'none');
    break;
  default:
    break;
  }
  downAjax();
  $("li").click(function () {
    $(this).attr('class', 'on');
    $(this).siblings('li').attr('class', '');
    //清除
    $("#scroller").css('text-align','center');
    $("#scroller").html('<span >数据加载中...</span>');
    $("#fixedIncome").css('display', 'none');
    $("#fundIncome").css('display', 'none');
    $("#scbIncome").css('display', 'none');
    $("#totalIncome").css('display', 'none');
    downAjax();
//    var title = $(this).attr('title');
  /*  switch (title) {
    case 'scb':
      $("#scbIncome").css('display', 'block');
      $("#fixedIncome").css('display', 'none');
      $("#totalIncome").css('display', 'none');
      break;
    case 'fixed':
      $("#fixedIncome").css('display', 'block');
      $("#scbIncome").css('display', 'none');
      $("#totalIncome").css('display', 'none');
      break;
    case 'total':
      $("#totalIncome").css('display', 'block');
      $("#scbIncome").css('display', 'none');
      $("#fixedIncome").css('display', 'none');
      break;
    default:
      break;
    }*/
//    myScroll.refresh();
  });

});
function clean(){
  $("#mask").hide();
  $("#alertLayer-8").hide();
}
    
