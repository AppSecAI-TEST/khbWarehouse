$(function() {

	$('#reg').click(
		function() {
			$('#reg').attr("disabled", true);
			var pwdObj = $("#tradePwd");
			var verifyCodeFlag = identifyCodeCheck('blur','identifyCode'); //验证短信验证码规则
			var pwdLengthCheckFlag = pwdLengthCheck('blur',pwdObj,'pwd'); //验证交易密码长度规则
			var errorMsg = "";
			
			if (!verifyCodeFlag || !pwdLengthCheckFlag) {
				$('#reg').attr("disabled", false);
				return false;
			}
		    var verifyCode = $("#identifyCode").val();

			$.post("account/verifyOldMobileNoCode", {
				verifyCode : verifyCode,
				tradePwd : pwdObj.val()
			}, function(data) {
				if (data == 'SUCCESS') {
					location.href = "account/toModifyMobile";
				}else if(data == 'noLogin'){
					location.href=window.location.href;  
				} else if (data == 'WRONG_PWD') {
					errorMsg = " 对不起，交易密码错误";
					pwdLengthCheckFlag = false;
				} else if (data == 'DYNAMIC_FAILED') {
					errorMsg = " 对不起，短信验证码不正确";
					verifyCodeFlag = false;
//					$("#identifyCode").val("");
				} else if (data == 'TRADE_PWD_VERIFY_LIMIT') {
					errorMsg = " 对不起，交易密码验证超限";
					pwdLengthCheckFlag = false;
				} else if (data == 'FAILED') {
					errorMsg = " 对不起，验证失败";
				}
				if (data != 'SUCCESS') {
					if(!verifyCodeFlag) {
						$("#identify-code-li").addClass("input-wrap-error");
				        $("#error-identify-code").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
				        $("#error-identify-code").show();
					} else if(!pwdLengthCheckFlag) {
						$("#password-li").addClass("input-wrap-error");
				        $("#error-password").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
				        $("#error-password").show();
					} else {
			            $("#messageBox").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
					}
					$('#reg').attr("disabled", false);
				}
			}, "text");
		});
});

/**
 * 监听所有文本框输入
 * 提交按钮即时可点击状态变化
 */
function checkFormButton(eventCode,inputNodeId) {
	var verifyCodeFlag = identifyCodeCheck(eventCode,inputNodeId); //验证短信验证码规则
	var pwdObj = $("#tradePwd");
	var pwdLengthCheckFlag = pwdLengthCheck(eventCode,pwdObj,inputNodeId); //验证交易密码长度规则
	if(verifyCodeFlag && pwdLengthCheckFlag) {
		$("#reg").removeClass("btn-login-gray").addClass("btn-login");
		$('#reg').attr("disabled", false);
	} else {
		$("#reg").removeClass("btn-login").addClass("btn-login-gray");
		$('#reg').attr("disabled", true);
	}
}