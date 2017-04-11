$(document).ready(function() {
  
//定时显示生财宝
  var scbSwitch = $("#scbSwitch").val();
  if(scbSwitch == 'yes'){
    var startDate = new Date($("#startDate").val());
    var endDate = new Date($("#endDate").val());
    var curDate = new Date();
    /*var startDate = new Date("2016/10/18 14:00:00");
    var endDate = new Date("2016/10/19 16:00:00");*/
    if(curDate >= startDate && curDate <= endDate){
      $("#noBuyTranIn").attr("class","btnBuy btnBuy-gray");
      $("#noBuyTranIn").attr("disabled","true");
      $("#noBuyTranIn").attr("value","系统升级中暂停服务");
    }
  }
  
  
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
	}  });
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
	 * 转入按钮
	 */
	function toTransferIn(){
		var userStatus = $("#userStatus").val();
		//APP端不弹出蒙层，直接被原生登录拦截
		var u = navigator.userAgent;
		var isApp = /lanmao/i.test(u);
		if ('unLogin' == userStatus) {
		if (isApp) {
			// 获取当前网址，如： http://localhost:8080/ems/Pages/Basic/Person.jsp
			var curWwwPath = window.document.location.href;
			// 获取主机地址之后的目录，如： /ems/Pages/Basic/Person.jsp
			var pathName = window.document.location.pathname;
			var pos = curWwwPath.indexOf(pathName);
			// 获取主机地址，如： http://localhost:8080
			var localhostPath = curWwwPath.substring(0, pos);
			// 获取带"/"的项目名，如：/ems
			var projectName = pathName.substring(0, pathName.substr(1).indexOf(
					'/') + 1);
			if ('unLogin' == userStatus) {
				location.href = "account/toLogin?returnUrl=scb/toScb";
				return;
			}
		}else{
			changeStyle("mask", "block");
			changeStyle("alertLayer", "block");
			return;
		}
	}
		changeStyle("mask", "block");
		changeStyle("alertLayer-2", "block");
		var accountBalanceResult = parseFloat(document.getElementById('accountBalanceResult').innerHTML);
		if(isNaN(accountBalanceResult)||accountBalanceResult==0){
			document.getElementById('totalIn').style.display="none";
			document.getElementById('userBalance').style.display="none";
		}else{
      $("#accountBalanceResult").html(accountBalanceResult.toFixed(2));
			document.getElementById('totalIn').style.display="";
			document.getElementById('userBalance').style.display="";
		}
	}
	/**
	 * 确认转入按钮
	 */
	function transferIn(){
		var userStatus = $("#userStatus").val();
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
		  var rechargeAmountFloat = parseFloat(money)- formatNumberFloor(parseFloat(accountBalanceResult),2);
		  var rechargeAmount=rechargeAmountFloat.toFixed(2);
			//已经绑卡跳转充值页面
			if('unSignUp'==userStatus){
				location.href = "asset/toRecharge?ret=scb&amount="+rechargeAmount;
				transferInOff();
				//已登录未绑卡用户跳转绑卡页面然后再去充值
			}else if('unBindCard'==userStatus){
				var u = navigator.userAgent;
				var isApp = /lanmao/i.test(u);
				if (isApp) {
//					// 获取当前网址，如： http://localhost:8080/ems/Pages/Basic/Person.jsp
//					var curWwwPath = window.document.location.href;
//					// 获取主机地址之后的目录，如： /ems/Pages/Basic/Person.jsp
//					var pathName = window.document.location.pathname;
//					var pos = curWwwPath.indexOf(pathName);
//					// 获取主机地址，如： http://localhost:8080
//					var localhostPath = curWwwPath.substring(0, pos);
//					// 获取带"/"的项目名，如：/ems
//					var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
					location.href = "account/card/toBindCard?returnUrl=asset/toRecharge?ret=scb&amount="+rechargeAmount;
					transferInOff();
				}else{
					location.href = "asset/toRecharge?ret=scb&amount="+rechargeAmount;
					transferInOff();
				}
			}
		  
		}else{
			//已经绑卡的去确认转入页面
			if('unSignUp'==userStatus){
				location.href = "scb/toConfirmTransferIn?totalBalance="+money;
				transferInOff();
				//已登录未绑卡用户跳转绑卡页面然后再去确认转入页面
			}else if('unBindCard'==userStatus){
				var u = navigator.userAgent;
				var isApp = /lanmao/i.test(u);
				if (isApp) {
					location.href = "account/card/toBindCard?returnUrl=scb/toConfirmTransferIn?totalBalance="+money;
					transferInOff();
				}else{
					location.href = "account/card/toGOBindCard?returnFlag=toConfirmTransfaerIn?totalBalance="+money;
					transferInOff();
				}
			}
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
	function format(data){
		//"2016-04-19"
		return data.substr(5,5).replace("-","月")+"日";
		
	}
function changeStyle(t,action){
	var p=document.getElementById(t);
	p.style.display=action;
}
