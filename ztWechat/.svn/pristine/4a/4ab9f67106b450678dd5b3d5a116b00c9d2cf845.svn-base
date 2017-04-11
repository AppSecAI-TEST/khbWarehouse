  $(function(){
      $('#resLogin').click(function(){
        $('#resLogin').attr("disabled", true);

        var telObj = $("#tel");
        var loginPwdObj = $("#loginPwd");
        var loginRePwdObj = $("#loginRePwd");
        var identifyCode = $("#identifyCode").val();
        var telFlag = telRuleCheckCom("onblur");
        var loginPwdFlag = pwdSetupCheck("loginPwd","onblur");
        var loginRePwdFlag = checkEqualByTwoValue(loginPwdObj,loginRePwdObj,"login-re-pwd");
        var identifyCodeFlag = identifyCodeCheck("onblur");
        if(telFlag && loginPwdFlag && loginRePwdFlag && identifyCodeFlag){
          $("#resLogin").removeClass("btn-login-gray").addClass("btn-login");
          $("#resLogin").attr("disabled", false);
        }else{
          $("#resLogin").removeClass("btn-login").addClass("btn-login-gray");
          $('#resLogin').attr("disabled", true);
          return false;
        }
        
        var errorMsg = "";
        $.post("account/resetLoginPwd",{tel:telObj.val(),loginPwd:loginPwdObj.val(),loginRePwd:loginRePwdObj.val(),verifyCode:identifyCode},function(data){
          if(data == 'SUCCESS'){
            //弹层
            openLayer();
          } else if(data == 'USER_NOTEXIST'){
            errorMsg = " 该用户不存在";
            telFlag = false;
          } else if(data == 'DYNAMIC_FAILED') {
        	  errorMsg = " 对不起，短信验证码错误";
        	  verifyCodeFlag = false;
          } else if(data == 'FAILED'){
        	  errorMsg = " 对不起，修改登录密码失败";
          } else if(data=='SYSTEM_EXCETPTION') {
        	  errorMsg=" 系统或网络异常，请稍后再试";
          } else {
        	  errorMsg=data;
          }
          if(data != 'SUCCESS'){
        	  if(!telFlag) {
        	      $("#tel-li").addClass("input-wrap-error");
    		        $("#error-tel").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
    		        $("#error-tel").show();
        	  } else if(!loginPwdFlag) {
        	      $("#setup-pwd-li").removeClass("input-wrap-tip").addClass("input-wrap-error");
    		        $("#error-setup-pwd").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
    		        $("#error-setup-pwd").show();
        	  } else if(!identifyCodeFlag) {
          	    $("#identify-code-li").addClass("input-wrap-error");
  			        $("#error-identify-code").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
  			        $("#error-identify-code").show();
        	  } else {
    	            $("#messageBox").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        	  }
        	  $('#sendCode').removeClass("btn-small fr").addClass("btn-small-gray fr");
        	  $('#resLogin').removeClass('btn-login').addClass('btn-login-gray');
        	  $('#resLogin').attr("disabled", true);
          }
        },"text");
      });
    });
  
  /**
   * 监听所有文本框输入
   * 提交按钮即时可点击状态变化
   */
  function checkFormButton(eventName) {
	
    var telFlag = telRuleCheckCom(eventName);
    var loginPwdObj = $("#loginPwd");
    var loginPwdFlag = pwdSetupCheck('loginPwd',eventName);
    var loginRePwdObj = $("#loginRePwd");
    var loginRePwdFlag = checkEqualByTwoValue(loginPwdObj,loginRePwdObj,"login-re-pwd");
    var identifyCode = $("#identifyCode").val();
    var identifyCodeFlag = identifyCodeCheck(eventName);
    if(telFlag && loginPwdFlag && loginRePwdFlag && identifyCodeFlag){
      $("#resLogin").removeClass("btn-login-gray").addClass("btn-login");
      $("#resLogin").attr("disabled", false);
    }else{
      $("#resLogin").removeClass("btn-login").addClass("btn-login-gray");
      $('#resLogin').attr("disabled", true);
    }
  }
//弹层
  function openLayer(){
    $("#mask").show();
    $("#alertLayer-3").show();
  }