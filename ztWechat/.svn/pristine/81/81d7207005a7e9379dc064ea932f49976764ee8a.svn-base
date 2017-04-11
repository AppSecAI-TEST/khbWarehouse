$(function(){
  loaded();
  $("#appDateBegin").val('');
  $("#appDateEnd").val('');
  var currYear = (new Date()).getFullYear();
  var opt={};
  opt.date = {preset : 'date'};
  opt.defaulter = {
      theme: 'android-ics light', //皮肤样式
      display: 'modal', //显示方式
      mode: 'scroller', //日期选择模式
      dateFormat: 'yyyy-mm-dd',
      lang: 'zh',
      showNow: true,
      nowText: "今天",
      startYear: currYear - 10, //开始年份
      endYear: currYear + 10 //结束年份
  };
  $("#appDateBegin").mobiscroll($.extend(opt['date'], opt['defaulter']));
  $("#appDateEnd").focusin(function(){
    var vals=$('#appDateBegin').val();
    if(vals==''){
        layer.open({
            btn: ['OK'],
            content:'<p class=\"\">请选择开始日期</p>'
        });
    }else{
        $("#appDateEnd").unbind('focusin').mobiscroll($.extend(opt['date'], opt['defaulter']));
        $(this).unbind('change');
        $(this).change(function(){
          downAjax();
        });
    }
});
  downAjax();
});

function clean(){
  $("#mask").hide();
  $("#alertLayer-8").hide();
}
    
