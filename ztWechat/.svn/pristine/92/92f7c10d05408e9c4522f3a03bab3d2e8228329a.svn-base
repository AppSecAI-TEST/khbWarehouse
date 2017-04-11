var amountFlag = true;

$(function(){
	 $('#sumbit').attr("disabled", true);
	 $('#sumbit').val("处理中...");
	 var pwd =$("#tradepwd").val();
	 
});

function doMoneyCheck(obj) {
	var amount = obj.value;
	if (amount == "") {
		alert("不能为空");
		return false;
	} 
	amountValid(obj.id, "amountSccess();", "amountError();");
	if (!amountFlag) {
		return false;
	}
}

function amountSccess() {
	amountFlag = true;
};

function amountError(id) {
	showError(id,"必须为数字且小数点后不能超过2位");
	amountFlag = false;
};
	
/**
 * 验证数字
 * 必须是整数，或者小数点后为两位的数字
 */
function amountValid(id, succFun, failFun) {
	if (callAmountValid(id)) {
		eval(succFun);
		return true;
	} else {
		amountError(id);
		return false;
	}
}
	
function callAmountValid(id) {
	var amount = document.getElementById(id).value;
	var reg = /^[1-9]\d*\.?\d{0,2}$|^0\.\d{0,2}$/;
	if ($.trim(amount).length <= 0 || amount == 0) {
		changeInput(id,"nullVal");
		return false;
	} else if (!reg.test(amount)) {
		changeInput(id,"购买金额必须为数字，小数点不能超过2位");
		return false;
	} else {
		var v = amount.toString().split(".");
		if (v.length == 1) {
			amount = amount + ".00";
			document.getElementById(id).value = amount;
		} else {
			var len = v[1].length;
			if (len == 0) {
				amount = amount + "00";
				document.getElementById(id).value = amount;
			} else if (len == 1) {
				amount = amount + "0";
				document.getElementById(id).value = amount;
			}
		}
	}
	return true;
};
	
function checkForm(id,leastVal,totalShares) { //提交时再判断
	if (!amountFlag) {
		return false;
	}
	var amount = $("#"+id).val();
	if ($.trim(amount).length <= 0 || amount == 0) {
		changeInput(id,"nullVal");
		return false;
	} else if (parseFloat(amount) < leastVal){
		changeInput(id,"littleVal");
		return false;
	} else if (parseFloat(amount) > parseFloat(totalShares)){
		changeInput(id,"more");
		return false;
	} else {
		return true;
	}
}
function showError(id,msg){
	document.getElementById(id).focus();
	alert(msg);
}

function checkApplyForm(id,leastVal) { //提交时校验 仅申购
	if (!amountFlag) {
		return false;
	}
	var balance = $("#"+id).val();
	if(checkApplyMoneyOptions(id,balance,leastVal)) {
		$("#"+id+"Form").submit();
	}
}

function checkApplyMoney(obj,leastVal,balance) { //校验输入框 
	var amount = document.getElementById(obj).value;
	if(callAmountValid(obj)){ //校验出入框数字是否符合规范
		if(checkApplyMoneyOptions(obj,amount,leastVal,balance)){
			amountFlag = true;
			document.getElementById("goPurchaseSpan").style.display="";
			document.getElementById("expectIncomeSpan").style.display="none";
			document.getElementById("confirmPurchase").disabled=false;
			document.getElementById("confirmPurchase").style.background="#fe7422";
			return true;
		}
	}
	amountFlag = false;
	document.getElementById("confirmPurchase").disabled=true;
	document.getElementById("confirmPurchase").style.background="#bbb";
	if(parseFloat(amount) > parseFloat(balance)){
		document.getElementById("goPurchaseSpan").style.display="none";
		document.getElementById("expectIncomeSpan").style.display="";	
	}else{
		document.getElementById("goPurchaseSpan").style.display="";
		document.getElementById("expectIncomeSpan").style.display="none";
	}
	return false;
}

function checkApplyMoneyOptions(id,balance,leastVal,amount) { //校验申请金额相关选项
	if ($.trim(balance).length <= 0) {
		changeInput(id,"nullVal");
		return false;
	}
	if (parseFloat(balance) < leastVal){
		changeInput(id,"littleVal");
		return false;
	} 
	if (parseFloat(balance) > 99999999.99){
		changeInput(id,"biggerVal");
		return false;
	} 
	if (parseFloat(balance) > parseFloat(amount)){
		changeInput(id,"more");
		return false;
	} 
	changeInput(id,"rightVal");
	return true;
}

function changeInput(id,msg) { 
	if (msg == "nullVal") {
		$("#" + id + "Msg").html("<i class=\"icon icon-error2 font-12\"></i> 购买金额必须为数字，小数点不能超过2位");
		document.getElementById(id+"Msg").style.display="";
		document.getElementById(id).focus();
	} else if (msg == "littleVal") {
		$("#" + id + "Msg").html("<i class=\"icon icon-error2 font-12\"></i> 购买金额不能低于最低申购额");
		document.getElementById(id+"Msg").style.display="";
		document.getElementById(id).focus();
	} else if (msg == "biggerVal") {
		$("#" + id + "Msg").html("<i class=\"icon icon-error2 font-12\"></i> 购买金额不能超过一亿");
		document.getElementById(id+"Msg").style.display="";
		document.getElementById(id).focus();
	} else if (msg == "more"){
		$("#" + id + "Msg").html("<i class=\"icon icon-error2 font-12\"></i> 账户余额不足");
		document.getElementById(id+"Msg").style.display="";
		document.getElementById(id).focus();
	} else if(msg == "rightVal") {
		document.getElementById(id+"Msg").style.display="none";		
		var fundC = document.getElementById("fundCode").value;
		var fundB = document.getElementById("buyAmount").value;
	} else {
		$("#" + id + "Msg").html("<i class=\"icon icon-error2 font-12\"></i> "+msg);
		document.getElementById(id+"Msg").style.display="";
		document.getElementById(id).focus();
	}
}

/**
 * 错误信息清空
*/
function pwdClick(){
   //清除内容
  $("#tradepwd").val('');
  $("#tradepwd").focus();
}

/**
 *显示 隐藏密码
 */
 function changePasswordType(id){
   var classType=$("#look").attr('class');
   if(classType!='icon icon-unlook fr'){
     document.getElementById(id).type="password";
     $("#look").attr('class','icon icon-unlook fr');
   }else{
     document.getElementById(id).type="text";
     $("#look").attr('class','icon icon-look orange fr');
   }
 }

function getRootPath() {
	var pathName = window.location.pathname.substring(1);
	var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
	return window.location.protocol + '//' + window.location.host + '/'+ webName;
}