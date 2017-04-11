$(document).ready(function() {
  //定时显示生财宝
  var scbSwitch = $("#scbSwitch").val();
  if(scbSwitch == "yes"){
    var startDate = new Date($("#startDate").val());
    var endDate = new Date($("#endDate").val());
    var curDate = new Date();
   /* var startDate = new Date("2016/10/18 14:00:00");
    var endDate = new Date("2016/10/19 16:00:00");*/
    if(curDate >= startDate && curDate <= endDate){
      $("#isBuy").attr("class","btnBot");
      //转出按钮去掉
      $("#normalTranOut").remove();
      //转入按钮变灰，不可点，且按钮显示字改变
      $("#normalTranIn").attr("class","btnBuy btnBuy-gray");
      $("#normalTranIn").attr("disabled","true");
      $("#normalTranIn").attr("value","系统升级中暂停服务");
      $("#noBuyTranIn").attr("class","btnBuy btnBuy-gray");
      $("#noBuyTranIn").attr("disabled","true");
      $("#noBuyTranIn").attr("value","系统升级中暂停服务");
      $("#financialMask").hide();
    }
  }
  
	$.post("scb/queryScb", {}, function(data) {
		$("#firstIncomeDay").html(format(data.firstIncomeDay));
		//昨日收益
		if(parseFloat(data.lastestIncome)<0.005) {
		  //收益小于0.005，直接可以四舍五入
		  $("#lastestIncome").html(parseFloat(data.lastestIncome).toFixed(2));
		} else {
		//收益大于0.005，减去0.005实现舍一法
		  $("#lastestIncome").html((parseFloat(data.lastestIncome) - 0.005).toFixed(2));
		}
		//总资产
		if(parseFloat(data.totalBalance)<0.005) {
      //收益小于0.005，直接可以四舍五入
		  $("#totalBalance").html(parseFloat(data.totalBalance).toFixed(2));
    } else {
    //收益大于0.005，减去0.005实现舍一法
      $("#totalBalance").html(parseFloat(data.totalBalance).toFixed(2));
    }
		
		//累计收益
		if(parseFloat(data.accumulativeIncome)<0.005) {
      //收益小于0.005，直接可以四舍五入
      $("#accumulativeIncome").html(parseFloat(data.accumulativeIncome).toFixed(2));
    } else {
    //收益大于0.005，减去0.005实现舍一法
      $("#accumulativeIncome").html(parseFloat(data.accumulativeIncome).toFixed(2));
    }
		
		$("#accountBalanceResult").html(parseFloat(data.accountBalanceResult).toFixed(2));
		$("#incomingDay").html(format(data.incomingDay));
		$("#mostBalance").html(parseFloat(data.totalBalance).toFixed(2));
		if(isNaN(parseFloat(data.totalBalance))||parseFloat(data.totalBalance)==0){
	    	  document.getElementById('isBuy').style.display = "none";
    	      document.getElementById('noBuy').style.display = "";
    	      $("#scbMask").attr("src","static/images/scbMask1.png");
		}else{
	    	  document.getElementById('isBuy').style.display = "block";
	    	  document.getElementById('noBuy').style.display = "none";
    	      $("#scbMask").attr("src","static/images/scbMask.png");
		}
	});
	
	
	$('#transferOutInput').bind('input propertychange', function() {
		var totalBalance = document.getElementById('totalBalance').innerHTML;
		var transferOutInput = document.getElementById('transferOutInput').value;// 获取“内容”
			if (transferOutInput==null||transferOutInput=='') {
			document.getElementById('transferOutDelete').style.display = "none";
	} else {
		document.getElementById('transferOutDelete').style.display="";
	  //小数点为两位的正则
		var filter=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
	  var flag=filter.test(transferOutInput);
	  if(flag == false) {
	    //输入金额格式不正确
	    $("#outText").html("<p class=\"red\"><i class=\"icon icon-error2\"></i> 输入金额必须为数字，小数点不能超过2位</p>");
	  } else {
	  //输入金额格式正确
	    if(!isNaN(totalBalance)&&parseFloat(transferOutInput)>parseFloat(totalBalance)){
	      $("#outText").html("<p class=\"red\"><i class=\"icon icon-error2\"></i> 本次最多转出"+totalBalance+" 元</p>");
	    }else{
	      $("#outText").html("<p class=\"orange\">本次最多转出"+totalBalance+" 元</p>");
	    }
	  }
	}  
	});  
$('#transferInInput').bind('input propertychange', function() {  
	var transferInInput = document.getElementById('transferInInput')
	.value;// 获取“内容”
		if (transferInInput == "") {
		document.getElementById('transferInDelete').style.display = "none";
		$("#inText").html("<p class=\"orange\">请直接输入转入金额，账户余额不足将先进行充值</p>");

} else {
	document.getElementById('transferInDelete').style.display="";
 //小数点为两位的正则
  var filter=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
  var flag=filter.test(transferInInput);
	if(parseFloat(transferInInput)<1){
		$("#inText").html("<p class=\"red\"><i class=\"icon icon-error2\"></i> 请输入1 元以上金额</p>");
	}else{
	  if(flag == false) {
	    //输入金额格式不正确
	    $("#inText").html("<p class=\"red\"><i class=\"icon icon-error2\"></i> 输入金额必须为数字，小数点不能超过2位</p>");
	  }else {
	    //输入金额格式正确
	    $("#inText").html("<p class=\"orange\">请直接输入转入金额，账户余额不足将先进行充值</p>");
	  }
	}
}  
});  


$("[data-duration]").click(function() {
  //获取选中的时间段
  var duTime = $(this).data('duration');
  //选中的是年华收益率
  if($('[data-profit_type="2"]').hasClass("on")) {
    if(duTime == 7) {
      $('[data-profit_type="2"]').text("近七日年化(%)");
    } else if(duTime == 30) {
      $('[data-profit_type="2"]').text("近一月年化(%)");
    } else {
      $('[data-profit_type="2"]').text("近两月年化(%)");
    }
  }
});

  
});  
/**
 * 全部转入按钮
 */
function totalIn() {
	var accountBalanceResult = document.getElementById('accountBalanceResult').innerHTML;
	if (accountBalanceResult != null) {
			document.getElementById('transferInInput').value=accountBalanceResult;
			document.getElementById('transferInDelete').style.display="";
	}
}
/**
 * 转入页面删除图片
 */
function transferInDelete(){
	document.getElementById('transferInInput').value="";
	document.getElementById('transferInDelete').style.display = "none";
}
/**
 * 转出页面删除图片
 */
function transferOutDelete(){
	var totalBalance = document.getElementById('totalBalance').innerHTML;
	$("#outText").html("<p class=\"orange\">本次最多转出"+totalBalance+" 元</p>");
	document.getElementById('transferOutInput').value="";
	document.getElementById('transferOutDelete').style.display = "none";
	  document.getElementById('transferOutTime').style.display = "none";
}
/**
 * 全部转出按钮
 */
function totalOut(){
	var totalBalance = document.getElementById('totalBalance').innerHTML;
	if (accountBalanceResult != null) {
		$("#outText").html("<p class=\"orange\">本次最多转出"+totalBalance+" 元</p>");
			document.getElementById('transferOutInput').value=totalBalance;
			document.getElementById('transferOutDelete').style.display="";
			getReedomType(totalBalance);

	}
}
/**
 * 转入按钮
 */
function toTransferIn(){
	var accountBalanceResult = parseFloat(document.getElementById('accountBalanceResult').innerHTML);
	if(isNaN(accountBalanceResult)||accountBalanceResult==0){
		document.getElementById('totalIn').style.display="none";
		document.getElementById('userBalance').style.display="none";
	}else{
		document.getElementById('totalIn').style.display="";
		document.getElementById('userBalance').style.display="";
	}
}
/**
 * 确认转入按钮
 */
function transferIn(){
	var userStatus = $("#userStatus").val();
	//var money=parseFloat(document.getElementById('transferInInput').value);
  var money=document.getElementById('transferInInput').value;
	var accountBalanceResult = parseFloat(document.getElementById('accountBalanceResult').innerHTML);
  //小数点为两位的正则
  var filter=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
  var flag=filter.test(money);
  if(flag == false) {
    //输入金额格式不正确
    $("#inText").html("<p class=\"red\"><i class=\"icon icon-error2\"></i> 输入金额必须为数字，小数点不能超过2位</p>");
    return false;
  }
	if(isNaN(money)||money<1){
	}else if(money>accountBalanceResult){
		//跳转充值页面
	  var rechargeAmountFloat = parseFloat(money)- formatNumberFloor(parseFloat(accountBalanceResult),2);
//  var rechargeAmountFloat = parseFloat(accountAmount)- formatNumberFloor(parseFloat(buyAmount),2);
  var rechargeAmount=rechargeAmountFloat.toFixed(2);
		location.href = "asset/toRecharge?ret=scb&amount="+rechargeAmount;
		transferInOff();
	}else{
		//跳转确认转入页面
		location.href = "scb/toConfirmTransferIn?totalBalance="+money;		
		transferInOff();
}
	document.getElementById('transferInInput').value="";
	document.getElementById('transferInDelete').style.display = "none";
	$("#inText").html("<p class=\"orange\">请直接输入转入金额，账户余额不足将先进行充值</p>");
	}
/**
 * 转入页面取消按钮
 */
function transferInOff(){
	document.getElementById('transferInInput').value="";
	document.getElementById('transferInDelete').style.display = "none";
	$("#inText").html("<p class=\"orange\">请直接输入转入金额，账户余额不足将先进行充值</p>");
}
/**
 * 转出页面取消按钮
 */
function transferOutOff(){
	var totalBalance = document.getElementById('totalBalance').innerHTML;
	document.getElementById('transferOutInput').value="";
	document.getElementById('transferOutDelete').style.display = "none";
	$("#outText").html("<p class=\"orange\">本次最多转出"+totalBalance+" 元</p>");
	  document.getElementById('transferOutTime').style.display = "none";
}
/**
 * 确认出按钮
 */
function transferOut(){
	var totalBalance = document.getElementById('totalBalance').innerHTML;
	var outBalance=document.getElementById('transferOutInput').value;
  //小数点为两位的正则
  var filter=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
  var flag=filter.test(outBalance);
  if(flag == false) {
    //输入金额格式不正确
    $("#outText").html("<p class=\"red\"><i class=\"icon icon-error2\"></i> 输入金额必须为数字，小数点不能超过2位</p>");
    return false;
  }
	if(outBalance==""||parseFloat(outBalance)==0||parseFloat(outBalance)>parseFloat(totalBalance)){
		$("#outText").html("<p class=\"red\"><i class=\"icon icon-error2\"></i> 请正确输入转出金额</p>");	
	}else{
	location.href = "scb/toConfirmTransferOut?totalBalance="+parseFloat(outBalance);
	document.getElementById('transferOutInput').value="";
	$("#outText").html("<p class=\"orange\">本次最多转出"+totalBalance+" 元</p>");
}
	}
/**
 * 转出输入框焦点
 */
function transferOutOnBlur(){
	var totalBalance = parseFloat(document.getElementById('totalBalance').innerHTML);
	var redeemAmount=parseFloat(document.getElementById('transferOutInput').value);
	if(!isNaN(redeemAmount)&&redeemAmount<totalBalance){
	getReedomType(redeemAmount);
	}else{
  	  document.getElementById('transferOutTime').style.display = "none";
	}
}
/**
 * 获取赎回类型
 */
function getReedomType(redeemAmount){
	$.ajax({
	      url:'scb/getRedeemType',
	      type:'post',
	      dataType:'text',
	      data:{redeemAmount:redeemAmount},
	      error:function(){
	      },
	      success:function(data){
	    	  if("快速转出"==data){
	    		  var date=formatDate(new Date());
		    	  $("#redeemType").html(data);
		    	  $("#redeemTime").html(date);
		    	  document.getElementById('transferOutTime').style.display = "";
	    	  }else if("普通转出"==data){
	    		  var firstIncomeDay= document.getElementById('firstIncomeDay').innerHTML;
	    		  var date=format(firstIncomeDay);
	    		  $("#redeemType").html(data);
	    		  $("#redeemTime").html(date);
		    	  document.getElementById('transferOutTime').style.display = "";
	    	  }
	      }
	});
}
function formatDate(data){
	var month = data.getMonth()+1; 
	var day = data.getDate(); 
	return month+"月"+day+"日"; 
}
function format(data){
	//"2016-04-19"
	return data.substr(5,5).replace("-","月")+"日";
	
}



