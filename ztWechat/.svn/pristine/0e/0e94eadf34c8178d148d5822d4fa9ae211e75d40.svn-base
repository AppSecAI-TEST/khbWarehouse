  $(function(){
      
      $('#reg').click(function(){
        $('#reg').attr("disabled", true);

    	var idNumberFlag = idCardNoCheck("idNumber","id-number","idcard","blur"); //验证身份证号
    	var verifyCodeFlag = identifyCodeCheck('blur','identifyCode'); //验证短信验证码规则
      	var tradePwdObj = $("#tradePwd");
      	var pwdSetupCheckFlag = pwdSetupCheck("tradePwd",'blur','pwd'); //新交易密码规则验证
      	var tradeRePwdObj = $("#tradeRePwd");
      	var pwdReCheckFlag = checkEqualByTwoValue(tradePwdObj, tradeRePwdObj, "trade-re-pwd"); //新交易确认规则验证
      	var errorMsg = "";
		
		if (!idNumberFlag || !verifyCodeFlag || !pwdSetupCheckFlag || !pwdReCheckFlag) {
			$('#reg').attr("disabled", true);
			return false;
		}
	    var verifyCode = $("#identifyCode").val();
	    var idNumber = $("#idNumber").val();

        $.post("account/resetTradePwd",{credentialsNo:idNumber,newTradePwd:tradePwdObj.val(),newTradePwdConfirm:tradeRePwdObj.val(),verifyCode:verifyCode},function(data){
          if(data == 'SUCCESS'){
            openLayer();
          }else if(data == 'noLogin'){
            location.href=window.location.href;  
          } else if(data == 'PWD_INCONSISTENT') {
              errorMsg = " 新交易密码与新交易密码确认不一致";
              pwdReCheckFlag = false;
          } else if(data == 'ID_VERIFY_FAILED') {
        	  errorMsg = " 对不起，身份证号错误";
        	  idNumberFlag = false;
          } else if(data == 'VERIFY_FAILED') {
        	  errorMsg = " 对不起，交易密码错误";
          } else if(data == 'DYNAMIC_FAILED') {
        	  errorMsg = " 对不起，短信码错误";
        	  verifyCodeFlag = false;
          } else if(data == 'FAILED'){
        	  errorMsg = " 对不起，修改交易密码失败";
          } else if(data=='SYSTEM_EXCETPTION') {
        	  errorMsg=" 系统或网络异常，请稍后再试";
          } else {
        	  errorMsg=data;
          }
          if(data != 'SUCCESS'){
        	  if(!pwdReCheckFlag) {
    				$("#trade-re-pwd-li").addClass("input-wrap-error");
    		        $("#error-trade-re-pwd").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
    		        $("#error-trade-re-pwd").show();
        	  } else if(!idNumberFlag) {
    				$("#id-number-li").addClass("input-wrap-error");
    		        $("#error-id-number").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
    		        $("#error-id-number").show();
        	  } else if(!verifyCodeFlag) {
					$("#identify-code-li").addClass("input-wrap-error");
			        $("#error-identify-code").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
			        $("#error-identify-code").show();
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
	
	var idNumberFlag = idCardNoCheck("idNumber","id-number",inputNodeId,eventCode); //验证身份证号
	var verifyCodeFlag = identifyCodeCheck(eventCode,inputNodeId); //验证短信验证码规则
  	var tradePwdObj = $("#tradePwd");
  	var pwdSetupCheckFlag = pwdSetupCheck("tradePwd",eventCode,inputNodeId); //新交易密码规则验证
  	var tradeRePwdObj = $("#tradeRePwd");
  	var pwdReCheckFlag = checkEqualByTwoValue(tradePwdObj, tradeRePwdObj, "trade-re-pwd"); //新交易确认规则验证
  	
  	if(idNumberFlag && verifyCodeFlag && pwdSetupCheckFlag && pwdReCheckFlag) {
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