$(document).ready(function() {
  $(".icon-checkbox").click(function() {
    //协议是否被选中
    if($("#agreeBox").is(":checked")) {
      $("#btn-transferIn").removeClass("btn-login-gray").addClass("btn-login").removeAttr("disabled");
    } else {
      $("#btn-transferIn").removeClass("btn-login").addClass("btn-login-gray").attr("disabled",true);
    }
});
});
$(function() {
	// 密码长度校验
	$('#pwd').bind('input propertychange', function() {
		if ($('#pwd').val().length > 0) {
			changeStyle("delete", "");
			if ($('#pwd').val().length > 5 || $('#pwd').val().length < 21) {
				$('#errorMsg').empty();
			}
		} else {
			changeStyle("delete", "none");
		}
	});
	// 确认转出按钮
	$('#btn-transferOut').click(function(){
		$('#btn-transferOut').attr("disabled", true);
		$('#btn-transferOut').val("处理中...");
		var amount=document.getElementById('totalBalance').innerHTML;
		var tradePwd=$('#pwd').val();
		if(tradePwd.length<6||tradePwd.length>20){
			$('#errorMsg').html("<i class=\"icon icon-error2\"></i> 请输入不小于6位的交易密码");
			$('#btn-transferOut').attr("disabled", false);
			$('#btn-transferOut').val("确认转出");
			return false;
		}
		$.ajax({
			url:'scb/transferOut', 
			type:'post',
			dataType:'text',
			data:{amount:amount,tradePwd:tradePwd},
			error: function(){
			  changeStyle("mask", "block");
//        changeStyle("alertLayer-3", "block");
			  $("#failTitle").remove();//去掉“绑卡失败”字样
	      $("#errorMsgMask").remove();//去掉错误信息
	      $("#btnLayer").addClass("pb50");
	      $("#layerBody").append("<p id='failTitle' class='failTitle'>转出失败</p>");
	      $("#layerBody").append("<p id='errorMsgMask' class='orange'>系统异常，请稍后重试哦</p>");
	      $("#btnLayer").append("<div class='btnMaskArea tc pa'><a href='scb/toScb' class='btnBig'>重新转出</a></div>");
	      $("#failMask").show();
			  
			}, 
			success: function(data){
				if(data=="SUCCESS"){
					//转出成功页面
					location.href="scb/toTransferOutSuccess";
				}else if(data.indexOf("LOCKED_PWD")>=0){
					//你的账号已被锁定
					$('#errorMsg').html("<i class=\"icon icon-error2\"></i> 您的交易密码已被锁定，请在"+data.substr(10)+"分钟后重试");
				}else if(/^[0-9]/.test(data)){
					//交易密码错误！输入错误3次密码将被锁定
					$('#errorMsg').html("<i class=\"icon icon-error2\"></i> 交易密码错误！您还可以再输入"+data+"次");

				}else{
					//交易失败
					changeStyle("mask", "block");
//					changeStyle("alertLayer-3", "block");
					$("#failTitle").remove();//去掉“绑卡失败”字样
	        $("#errorMsgMask").remove();//去掉错误信息
	        $("#btnLayer").addClass("pb50");
	        $("#layerBody").append("<p id='failTitle' class='failTitle'>转出失败</p>");
	        $("#layerBody").append("<p id='errorMsgMask' class='orange'>请稍后重试哦~</p>");
	        $("#btnLayer").append("<div class='btnMaskArea tc pa'><a href='scb/toScb' class='btnBig'>重新转出</a></div>");
	        $("#failMask").show();
				}
				$('#btn-transferOut').val("确认转出");
				$('#btn-transferOut').attr("disabled", false);
			} 
		});
		
	});
		$('#look').click(function() {
		if ($('#pwd')[0].type == "password"){
		    $("#look").removeClass("icon icon-unlook fr").addClass("icon icon-look orange fr");
			changeType("pwd", "text");
		}else{
		    $("#look").removeClass("icon icon-look orange fr").addClass("icon icon-unlook fr");
			changeType("pwd", "password");
		}
	});
	$('#delete').click(function() {
		changeStyle("delete","none");
		$('#pwd').val("");
	});
});
function transferIn(){
	$('#btn-transferIn').attr("disabled", true);
	$('#btn-transferIn').val("处理中...");
	var amount=document.getElementById('totalBalance').innerHTML;
	var tradePwd=$('#pwd').val();
	if(tradePwd.length<6){
		$('#errorMsg').html("<i class=\"icon icon-error2\"></i> 请输入不小于6位的交易密码");
		$('#btn-transferIn').attr("disabled", false);
		$('#btn-transferIn').val("确认购买");
		return false;
	}else if(tradePwd.length>20){
		$('#errorMsg').html("<i class=\"icon icon-error2\"></i> 请输入不大于20位的交易密码");
		$('#btn-transferIn').attr("disabled", false);
		$('#btn-transferIn').val("确认购买");
		return false;
	}
	$.ajax({
		url:'scb/transferIn', 
		type:'post',
		dataType:'text',
		data:{amount:amount,tradePwd:tradePwd},
		error: function(){
		  changeStyle("mask", "block");
//      changeStyle("alertLayer-3", "block");
		  $("#failTitle").remove();//去掉“绑卡失败”字样
      $("#errorMsgMask").remove();//去掉错误信息
      $("#btnLayer").addClass("pb50");
      $("#layerBody").append("<p id='failTitle' class='failTitle'>转入失败</p>");
      $("#layerBody").append("<p id='errorMsgMask' class='orange'>系统异常，请稍后重试哦</p>");
      $("#btnLayer").append("<div class='btnMaskArea tc pa'><a href='scb/toScb' class='btnBig'>重新转入</a></div>");
      $("#failMask").show();
		}, 
		success: function(data){
			if(data=="SUCCESS"){
				//转出成功页面
				location.href="scb/toTransferInSuccess?totalBalance="+amount;
			}else if(data.indexOf("LOCKED_PWD")>=0){
				//你的账号已被锁定
				$('#errorMsg').html("<i class=\"icon icon-error2\"></i> 您的交易密码已被锁定，请在"+data.substr(10)+"后重试");
			}else if(/^[0-9]/.test(data)){
				//交易密码错误！输入错误3次密码将被锁定
				$('#errorMsg').html("<i class=\"icon icon-error2\"></i> 交易密码错误！您还可以再输入"+data+"次");
				//输入密码框变红
				$("#input-wrap-error").addClass("input-wrap-error");

			}else{
				changeStyle("mask", "block");
//				changeStyle("alertLayer-3", "block");
				$("#failTitle").remove();//去掉“绑卡失败”字样
	      $("#errorMsgMask").remove();//去掉错误信息
	      $("#btnLayer").addClass("pb50");
	      $("#layerBody").append("<p id='failTitle' class='failTitle'>转入失败</p>");
	      $("#layerBody").append("<p id='errorMsgMask' class='orange'>请稍后重试哦~</p>");
	      $("#btnLayer").append("<div class='btnMaskArea tc pa'><a href='scb/toScb' class='btnBig'>重新转入</a></div>");
	      $("#failMask").show();
			}
			$('#btn-transferIn').val("确认购买");
			$('#btn-transferIn').attr("disabled", false);
		} 
	});
}	
function changeType(t,action){
	var p = document.getElementById(t);
	p.type=action;
	}
function changeStyle(t,action){
	var p=document.getElementById(t);
	p.style.display=action;
}

