$(function () {
    $('#reg').click(function(){
	    $('#reg').attr("disabled", true);
		var currentLoginPwdObj = $("#currentLoginPwd");
		var currentLoginPwdCheckFlag = pwdLengthCheck("blur",currentLoginPwdObj,"curpwd"); //验证原始登录密码长度规则
		var loginPwdObj = $("#loginPwd");
		var pwdSetupCheckFlag = pwdSetupCheck("loginPwd","blur","pwd"); //新登录密码规则验证
		var loginRePwdObj = $("#loginRePwd");
		var pwdReCheckFlag = checkEqualByTwoValue(loginPwdObj, loginRePwdObj, "login-re-pwd"); //新登录确认规则验证
		var errorMsg = "";
		
		if (!currentLoginPwdCheckFlag || !pwdSetupCheckFlag || !pwdReCheckFlag) {
			$('#reg').attr("disabled", false);
			return false;
		}
		
      $.post("account/modifyLoginPwd",{loginPwd:currentLoginPwdObj.val(),newLoginPwd:loginPwdObj.val(),newLoginPwdConfirm:loginRePwdObj.val()},function(data){
        if(data == 'SUCCESS'){
          openLayer();
        }else if(data == 'noLogin'){
          location.href=window.location.href;  
        } else if(data == 'PWD_INCONSISTENT'){
            errorMsg = " 新登录密码与新登录密码确认不一致";
            pwdReCheckFlag = false;
        } else if(data == 'VERIFY_FAILED'){
        	errorMsg = " 对不起，登录密码错误";
        	currentLoginPwdCheckFlag = false;
        } else if(data == 'FAILED'){
        	errorMsg = " 对不起，修改登录密码失败";
        } else if(data == 'SYSTEM_EXCEPTION'){
        	errorMsg=" 系统或网络异常，请稍后再试";
        } else {
        	 errorMsg=data;
        }
        if(data != 'SUCCESS'){
        	if(!pwdReCheckFlag) {
				$("#login-re-pwd-li").addClass("input-wrap-error");
		        $("#error-login-re-pwd").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
		        $("#error-login-re-pwd").show();
			} else if(!currentLoginPwdCheckFlag) {
				$("#password-li").addClass("input-wrap-error");
		        $("#error-password").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
		        $("#error-password").show();
			} else {
	            $("#messageBox").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
			}
        	
        	$('#reg').attr("disabled", false);
        }
      },"text");
    });
  });

/**
 * 监听所有文本框输入
 * 提交按钮即时可点击状态变化
 */
function checkFormButton(eventCode,inputNodeId) {
	var currentLoginPwdObj = $("#currentLoginPwd");
	var currentLoginPwdCheckFlag = pwdLengthCheck(eventCode,currentLoginPwdObj,inputNodeId); //验证原始登录密码长度规则
	var loginPwdObj = $("#loginPwd");
	var pwdSetupCheckFlag = pwdSetupCheck("loginPwd",eventCode,inputNodeId); //新登录密码规则验证
	var loginRePwdObj = $("#loginRePwd");
	var pwdReCheckFlag = checkEqualByTwoValue(loginPwdObj, loginRePwdObj, "login-re-pwd"); //新登录确认规则验证
	
	if(currentLoginPwdCheckFlag && pwdSetupCheckFlag && pwdReCheckFlag) {
		$("#reg").removeClass("btn-login-gray").addClass("btn-login");
		$('#reg').attr("disabled", false);
	} else {
		$("#reg").removeClass("btn-login").addClass("btn-login-gray");
		$('#reg').attr("disabled", true);
	}
}

//弹层
function openLayer(){
  $("#mask").show();
  $("#alertLayer-3").show();
}