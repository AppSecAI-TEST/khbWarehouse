
$(function(){
  var noticeSwitch = $("#noticeSwitch").val();
  //公告展示
  if(noticeSwitch == "yes"){
    var noticeStartDate = new Date($("#noticeStartDate").val());
    var noticeEndStr = $("#noticeEndDate").val();
    var noticeEndDate = "";
    if(noticeEndStr != null && noticeEndStr != ""){
      noticeEndDate = new Date(noticeEndStr);
    }
    
    var curDate = new Date();
    //如果公告有结束时间，则在此时间段内显示，如果公告没有结束时间，一直展示
    if(noticeEndDate != null && noticeEndDate != ""){
      if(curDate >= noticeStartDate && curDate <= noticeEndDate){
        $("#noticeShow").show();
      }else{
        $("#mask").hide();
        $("#noticeShow").hide();
      }
    }else if(curDate >= noticeStartDate){
      $("#noticeShow").show();
    }
  }else{
    $("#mask").hide();
    $("#noticeShow").hide();
  }
  /*var curDate = new Date();
  var invalidDate = new Date("2016/10/24,00:00:00");
  if(curDate >= invalidDate){
    $("#mask").hide();
    $("#noticeShow").hide();
  }else{
    $("#noticeShow").show();
  }*/
  
  var surplusAmountList = $('.surplusAmount');
  for(var i = 0 ;i < surplusAmountList.length ;i++){
    surplusAmountList.eq(i).html(fmoney(surplusAmountList.eq(i).html(), 1));
  }
  
});
function toScb(){
	location.href="scb/toScb";
		}
function getItemId(productId){
	var s=productId;
	  location.href="fixed/toPurchaseProduct?productId="+s;
}

function toShare(){
	changeStyle("share","block");
	changeStyle("alertLayer", "block");	
}
function toShareAlert(){
  changeStyle("alertLayer", "block"); 
}
function changeStyle(t,action){
	var p=document.getElementById(t);
	p.style.display=action;
}
function showHolidayNotice(){
  $("#mask").show();
  $("#holidayNotice").show();
}
//秒客盈倒计时
function getRTime(){
  var saleDate = $("#saleDate").val();
  var EndTime= new Date(saleDate); //截止时间
  var NowTime = new Date();
  var t =EndTime.getTime() - NowTime.getTime();
  var h=Math.floor(t/1000/60/60);
  var m=Math.floor(t/1000/60%60);
  var s=Math.floor(t/1000%60);

  document.getElementById("t_h").innerHTML = num(h);
  document.getElementById("t_m").innerHTML = num(m);
  document.getElementById("t_s").innerHTML = num(s);
}
function num(num){
  return num>9?num:'0'+num;
}
setInterval(getRTime,1000);