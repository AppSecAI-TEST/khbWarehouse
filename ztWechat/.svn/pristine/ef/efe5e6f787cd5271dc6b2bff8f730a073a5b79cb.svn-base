$(function(){
  $("#appDateBegin").val('');
  $("#appDateEnd").val('');
  var currYear = (new Date()).getFullYear();
  var opt={};
  opt.date = {preset : 'date'};
  opt.defaults = {
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
  $("#appDateBegin").mobiscroll($.extend(opt['date'], opt['defaults']));
  $("#appDateEnd").focusin(function(){
    var vals=$('#appDateBegin').val();
    if(vals==''){
        layer.open({
            btn: ['OK'],
            content:'<p class=\"color-yellow tc\">请选择开始日期</p>'
        });
    }else{
        $("#appDateEnd").unbind('focusin').mobiscroll($.extend(opt['date'], opt['defaults']));
        $(this).unbind('change');
        $(this).change(function(){
          var startTime = $("#appDateBegin").val();
          var endTime = $("#appDateEnd").val();
          queryBillInfo(startTime,endTime,1,0,0);
        });
    }
});
  queryBillInfo('','',1,0,0);
});
function queryBillInfo(startTime,endTime,pageIndex,tradeStatus,tradeType) {
  //TODO
  $.ajax({
    url:'scb/queryScbRecord',
    type:'post',
    dataType:'json',
    data:{startTime:startTime,endTime:endTime,index:pageIndex,tradeType:tradeType,tradeStatus:tradeStatus},
    error:function(){
      
    },
    success:function(data){

//    	if(data != '' && data.lstMaps != '' && data.lstMaps != null){
//    		if(pageTotal > pageIndex){
//    			$("#pullUp").attr("style","");
//    		}
    	  $.each(data.lstMaps,function(i,item){
    		  $("#scbRecord").append("<tr><td><span class=\"f10\">"+item.TIME.replace(" ","<br/>")+"</span> </td><td>"+item.TYPES+"</td><td><span class=\"orange\">"+item.AMOUNT+"</span></td><td>"+item.STATUS+"</td></tr>"); 
    	  });
//    	}else{
//    		$("#scbRecord").html("<tr><td colspan=\"4\">未查询到相关记录</td></tr>");
//    	}
      }
  });
}

    
