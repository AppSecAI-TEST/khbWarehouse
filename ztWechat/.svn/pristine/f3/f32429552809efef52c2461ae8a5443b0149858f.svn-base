 $(function(){
      $('#reg').click(function(){
        $('#reg').attr("disabled", true);
        
        var currentTradePwdObj = $("#currentTradePwd");
		var currentTradePwdCheckFlag = ("blur",currentTradePwdObj,"curpwd"); //验证原始交易密码长度规则
		var tradePwdObj = $("#tradePwd");
		var pwdSetupCheckFlag = pwdSetupCheck("tradePwd","blur","pwd"); //新交易密码规则验证
		var tradeRePwdObj = $("#tradeRePwd");
		var pwdReCheckFlag = checkEqualByTwoValue(tradePwdObj, tradeRePwdObj, "trade-re-pwd"); //新交易确认规则验证
		var errorMsg = "";
		
		if (!currentTradePwdCheckFlag || !pwdSetupCheckFlag || !pwdReCheckFlag) {
			$('#reg').attr("disabled", false);
			return false;
		}
		
        $.post("account/modifyTradePwd",{tradePwd:currentTradePwdObj.val(),newTradePwd:tradePwdObj.val(),newTradePwdConfirm:tradeRePwdObj.val()},function(data){
          if(data == 'SUCCESS'){
            openLayer();
          }else if(data == 'noLogin'){
            location.href=window.location.href;  
          } else if(data == 'PWD_INCONSISTENT') {
              errorMsg = " 新交易密码与新交易密码确认不一致";
              pwdReCheckFlag = false;
          } else if(data == 'VERIFY_FAILED') {
        	  errorMsg = " 对不起，交易密码错误";
          	  currentTradePwdCheckFlag = false;
          } else if(data == 'DYNAMIC_FAILED') {
        	  errorMsg = " 对不起，短信码错误";
          } else if(data == 'FAILED') {
        	  errorMsg = " 对不起，修改交易密码失败";
          } else if(data == 'SYSTEM_EXCEPTION') {
        	  errorMsg=" 系统或网络异常，请稍后再试";
          } else {
        	  errorMsg = data;
          }
          if(data != 'SUCCESS'){
          	if(!pwdReCheckFlag) {
  				$("#trade-re-pwd-li").addClass("input-wrap-error");
  		        $("#error-trade-re-pwd").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
  		        $("#error-trade-re-pwd").show();
  			} else if(!currentTradePwdCheckFlag) {
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
 	var currentTradePwdObj = $("#currentTradePwd");
 	var currentTradePwdCheckFlag = pwdLengthCheck(eventCode,currentTradePwdObj,inputNodeId); //验证原始交易密码长度规则
 	var tradePwdObj = $("#tradePwd");
 	var pwdSetupCheckFlag = pwdSetupCheck("tradePwd",eventCode,inputNodeId); //新交易密码规则验证
 	var tradeRePwdObj = $("#tradeRePwd");
 	var pwdReCheckFlag = checkEqualByTwoValue(tradePwdObj, tradeRePwdObj, "trade-re-pwd"); //新交易确认规则验证
 	
 	if(currentTradePwdCheckFlag && pwdSetupCheckFlag && pwdReCheckFlag) {
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