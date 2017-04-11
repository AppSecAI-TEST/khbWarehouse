$(function(){
  var timer = null;
  var num = 59;
  
  $('#sendCode').click(function(){

	    //当短信发送框变灰的时候 点击不再向后台发送请求
	if($('#sendCode').hasClass('btn-small-gray fr')){
	     return false;
	}
	  var sendNewPhoneFlag = false;
	  var action = "account/sendOldMobileNoCode";
	  var newMobileNo = "";
	  if($('#sendCode').hasClass('newPhone')) {
	  action = "account/sendNewMobileNoCode";
	  newMobileNo = $("#tel").val();
	  var telRuleCheckFlag = telRuleCheck('blur','tel',true);
		  if(!telRuleCheckFlag) {
			  return false;
		  }
	  } else if($('#sendCode').hasClass('resetTrade')) {
		  action = "account/sendResetTradePwdCode";
	  } else if($('#sendCode').hasClass('resetLogin')){
		  action = "account/sendResetLoginPwdCode";
	  } else if($('#sendCode').hasClass('register')){
		  action = "account/sendRegisterCode";
	  } else if($('#sendCode').hasClass('card')){
		  action = "account/card/sendBindBankCardCode";
	  }
	  
    $.post(action,{newMobileNo:newMobileNo},function(data){
      if(data == 'SUCCESS'){
        clearInterval(timer);
        timer = setInterval(function(){
            $('#sendCode').html(num + 's后重新获取');
            $('#sendCode').removeClass("btn-small fr").addClass("btn-small-gray fr");
            if(num == 0){
                clearInterval(timer);
                $('#sendCode').removeClass('btn-small-gray fr').addClass('btn-small fr');
                $('#sendCode').html("再次获取");
                num = 60;
            }
            num--;
        },1000);
      } else if(data == 'FAILED'){
        errorMsg = " 下发短信验证码失败";
        //错误样式，提示信息
        $("#identify-code-li").addClass("input-wrap-error");
        $("#error-identify-code").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-identify-code").show();
//        $("#messageBox").html("<label class='error'><i class='icon-tips'></i><span class='font-tip'>获取验证码失败，请稍候再试</span></label>");
      }else if(data = "SYSTEM_EXCEPTION"){
        errorMsg = " 系统或网络异常，请稍后再试";
        //TODO
        $("#messageBox").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#messageBox").show();
      }
    },"text");
   });
  
  //取消图标控制
  $('.icon.icon-error.fr').click(function(){
    $(this).hide();
    //错误信息不显示
    $(this).parent().siblings('.error-tips').html("");
    //红框样式去掉
    $(this).parent().parent().removeClass("input-wrap-error");
    //清除内容
    $(this).siblings('.span').children('.input-text').val('');
    $(this).siblings('.span').children('.input-text').focus();
  });
  //失去焦点
  $(".input-text").live('keyup',function(){
    $(this).parent().siblings('.icon.icon-error.fr').show();
  });
  $(".input-text").live('focus',function(){
    if($(this).val()){
      $(this).parent().siblings('.icon.icon-error.fr').show();
    }
  });
  $(".input-text").live('blur',function(){
    $(this).parent().siblings('.icon.icon-error.fr').fadeOut(500);
  });
});

/**
 * 短信验证码规则校验
 * 验证不通过，返回false
 */
function identifyCodeCheck(eventCode,inputNodeId) {
    var verifyCode = $("#identifyCode").val();
    var identifyCodeLi = $("#identify-code-li");
    var identifyCodeError = false;
    var errorMsg;
    if(verifyCode == '') {
    	identifyCodeLi.removeClass("input-wrap-error");
        $("#error-identify-code").html("");
        $("#error-identify-code").fadeOut(300);
    	return false;
    }
    if (verifyCode != '' && verifyCode.length != 6) {
    	if(inputNodeId == 'identifyCode' && eventCode == 'keyup'){
    		identifyCodeLi.removeClass("input-wrap-error");
            $("#error-identify-code").html("");
            $("#error-identify-code").fadeOut(300);
            return false;
	    }
    	errorMsg = " 请输入正确的短信验证码";
    	identifyCodeError = true;
    }
    if(identifyCodeError){
    	identifyCodeLi.addClass("input-wrap-error");
        $("#error-identify-code").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-identify-code").show();
        $('#reg').attr("disabled", false);
        return false;
    } else {
    	identifyCodeLi.removeClass("input-wrap-error");
        $("#error-identify-code").html("");
        $("#error-identify-code").fadeOut(300);
    }
    return true;
}

/**
 * 图片验证码规则校验
 * 验证不通过，返回false
 */
function verifyCodeCheck() {
    var verifyCode = $("#verifyCode").val();
    var verifyCodeLi = $("#verify-code-li");
    var verifyCodeError = false;
    var errorMsg;
    if(verifyCode == '') {
      
      return false;
    }
    if (verifyCode != '' && verifyCode.length != 4) {
      errorMsg = " 请输入正确的图片验证码";
      verifyCodeError = true;
    }
    if(verifyCodeError){
        verifyCodeLi.addClass("input-wrap-error");
        $("#error-verify-code").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-verify-code").show();
        $('#reg').attr("disabled", false);
        return false;
    } else {
      
      verifyCodeLi.removeClass("input-wrap-error");
      $("#error-verify-code").html("");
      $("#error-verify-code").fadeOut(300);
    }
    return true;
}

/**
 * 密码长度规则验证
 * 验证不通过，返回false
 */
function pwdLengthCheck(eventCode,obj,inputNodeId) {
    var pwd = obj.val();
    var pwdLi = $("#password-li");
    var pwdError = false;
    var errorMsg;
    if(pwd == '') {
    	pwdLi.removeClass("input-wrap-error");
        $("#error-password").html("");
        $("#error-password").fadeOut(300);
    	return false;
    }
    if (pwd != '' && (pwd.length < 6 || pwd.length > 20)) {
    	if(inputNodeId == 'curpwd' && eventCode == 'keyup'){
//    		pwdLi.addClass("input-wrap-error");
//            $("#error-password").html("");
//            $("#error-password").fadeOut(300);
            return false;
	    }
	    errorMsg = " 请输入6-20位的密码";
	    pwdError = true;
    }
    if(pwdError) {
    	pwdLi.addClass("input-wrap-error");
        $("#error-password").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-password").show();
        return false;
    } else {
    	pwdLi.removeClass("input-wrap-error");
        $("#error-password").html("");
        $("#error-password").fadeOut(300);
    }
    return true;
}

/**
 * 密码设置规则验证
 * 验证不通过，返回false
 */
function pwdSetupCheck(pwdId,eventCode,inputNodeId) {
	
	var pwd = $("#"+pwdId).val();
	if(pwd == '') {
		$("#setup-pwd-li").removeClass("input-wrap-error").removeClass("input-wrap-tip");
	    //显示错误信息
//	    $("#error-setup-pwd").removeClass("error-tips orange").addClass("error-tips red");
	    $("#error-setup-pwd").html("");
	    $("#error-setup-pwd").fadeOut(300);
		return false;
	}
	if(inputNodeId == 'pwd' && eventCode == 'keyup'){
		$("#setup-pwd-li").removeClass("input-wrap-error").addClass("input-wrap-tip");
		$("#error-setup-pwd").removeClass("red").addClass("orange");
        $("#error-setup-pwd").html("<i class='icon icon-tip'>密码必须为8-20位数字和字母组合</i>");
        $("#error-setup-pwd").show();
        return false;
    }
	if(inputNodeId != "pwd" || (inputNodeId == 'pwd' && eventCode == 'blur')) {
    	if(!isPwd(pwdId)) {
    		var errorMsg= " 密码必须为8-20位数字和字母组合";
    		//输入框变红色
    		$("#setup-pwd-li").removeClass("input-wrap-tip").addClass("input-wrap-error");
    		//显示错误信息
    		$("#error-setup-pwd").removeClass("error-tips orange").addClass("error-tips red");
    		$("#error-setup-pwd").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
    		$("#error-setup-pwd").show();
    		return false;
    	}  else{
    		$("#setup-pwd-li").removeClass("input-wrap-error").removeClass("input-wrap-tip");
    		//显示错误信息
    		$("#error-setup-pwd").html("");
    		$("#error-setup-pwd").fadeOut(300);
    		return true;
    	}
    }
	
}

/**
 * 比较两个文本框的输入值是否相同
 * @param obj1
 * @param obj2
 */
function checkEqualByTwoValue(obj1, obj2, obj2ErrorId) {
	
	if(obj2.val() == '') {
		$("#"+obj2ErrorId+"-li").removeClass("input-wrap-error");
	    //显示错误信息
//	    $("#error-setup-pwd").removeClass("error-tips orange").addClass("error-tips red");
	    $("#error-"+obj2ErrorId).html("");
	    $("#error-"+obj2ErrorId).fadeOut(300);
		return false;
	}
	
	if(obj1.val() ==  obj2.val()) {
		$("#"+obj2ErrorId+"-li").removeClass("input-wrap-error");
	    //显示错误信息
//	    $("#error-setup-pwd").removeClass("error-tips orange").addClass("error-tips red");
	    $("#error-"+obj2ErrorId).html("");
	    $("#error-"+obj2ErrorId).fadeOut(300);
		return true;
	} else {
		var errorMsg= " 两次密码输入不一致";
		$("#"+obj2ErrorId+"-li").removeClass("input-wrap-tip").addClass("input-wrap-error");
	    //显示错误信息
	    $("#error-"+obj2ErrorId).removeClass("error-tips orange").addClass("error-tips red");
	    $("#error-"+obj2ErrorId).html("<i class='icon icon-error2'>"+errorMsg+"</i>");
	    $("#error-"+obj2ErrorId).show();
	    return false;
	}
}

/**
 * 身份证验证
 */
function idCardNoCheck(idCardId, nodeId,inputNodeId,eventCode) {
	//身份证放大
	lookIdCard(idCardId);
	
	var idCard = $("#"+idCardId).val();
	if(idCard == "") {
		$("#"+nodeId+"-li").removeClass("input-wrap-error");
        $("#error-"+nodeId).html("");
        $("#error-"+nodeId).fadeOut(300);
		return false;
	}
	if(inputNodeId == 'idcard' && eventCode == 'keyup'){
		$("#"+nodeId+"-li").removeClass("input-wrap-error");
        $("#error-"+nodeId).html("");
        $("#error-"+nodeId).fadeOut(300);
        return false;
    } 
	if(inputNodeId != 'idcard' || (inputNodeId == 'idcard' && eventCode == 'blur')) {
    	if(!isIdCardNo(idCardId)) {
    		errorMsg = " 身份证号有误，请核实";
    		//输入框变成红色
    		$("#"+nodeId+"-li").addClass("input-wrap-error");
    		$("#error-"+nodeId).html("<i class='icon icon-error2'>"+errorMsg+"</i>");
    		$("#error-"+nodeId).show();
    	    return false;
    	} else {
    		$("#"+nodeId+"-li").removeClass("input-wrap-error");
    		$("#error-"+nodeId).html("");
    		$("#error-"+nodeId).fadeOut(300);
    		return true;
    	}
    }
	
}

/**
 * 显示密码
 * pwdId 密码输入框ID
 * pwdType 密码框文本提示信息
 */
function lookPwd(pwdId,pwdPlaceHolder){
	var pwd = $("#"+pwdId);
	var pwdVal = pwd.val();
	if(pwd.attr("type") == "password"){
		$("#lookBox-"+pwdId).html("<input type=\"text\" class=\"input-text\" id=\""+pwdId+"\" name=\""+pwdId+"\" size=\"20\" placeholder=\""+pwdPlaceHolder+"\"  onkeyup=\"checkFormButton()\" onfocus=\"checkFormButton()\" value="+pwdVal+">");
		$("#look-"+pwdId).removeClass("icon icon-unlook orange fr").addClass("icon icon-look orange fr");
	}else{
		$("#lookBox-"+pwdId).html("<input type=\"password\" class=\"input-text\" id=\""+pwdId+"\" name=\""+pwdId+"\" size=\"20\" placeholder=\""+pwdPlaceHolder+"\"  onkeyup=\"checkFormButton()\" onfocus=\"checkFormButton()\" value="+pwdVal+">");
	    $("#look-"+pwdId).removeClass("icon icon-look orange fr").addClass("icon icon-unlook orange fr");
	}
}

/**
 * 手机号规则校验
 */
function telRuleCheck(eventCode,inputNodeId,telCheckFlag) {
	//调用放大显示手机号
	lookTel();
	
	var tel = $("#tel").val();
	if(tel == '') {
		errorMsg= " 请输入正确手机号";
		//输入框红色去掉
		$("#tel-li").removeClass("input-wrap-error");
		//显示错误信息
		$("#error-tel").html("");
		$("error-tel").fadeOut(300);
		return false;
	}
	if(tel.length != 11) {
		if(eventCode == 'keyup' && inputNodeId == 'tel') {
			$("#tel-li").removeClass("input-wrap-error");
			$("#error-tel").html("");
			$("#error-tel").fadeOut(300);
			return false;
		}
		errorMsg= " 请输入正确手机号";
		//输入框变成红色
		$("#tel-li").addClass("input-wrap-error");
		//显示错误信息
		$("#error-tel").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
		$("#error-tel").show();
		//注册按钮为灰色
		return false;
	} else if(isMobile("tel") == false) {
		errorMsg = " 只支持手机号注册";
		//输入框变成红色
		$("#tel-li").addClass("input-wrap-error");
		//显示错误信息
		$("#error-tel").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
		$("#error-tel").show();
		return false;
	} else {
		//后台验证手机号是否唯一
		if(telCheckFlag) {
			var telFlag = verTelRegistered();
			if(telFlag) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
}
/**
 * 银行卡号验证
 */
function bankCardNoCheck(){
  //放大显示银行卡号
  lookCardNo("cardNo");
  var cardNo = $("#cardNo").val();
  var errorMsg;
  //银行名称
  var bankName;
  if(cardNo == ''){
    //银行卡号标志位
    //去掉错误样式
    $("#cardNoError").removeClass("input-wrap-error");
    $("#error-cardNo").fadeOut(300);
    $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
    $("#btn-bindCard").attr("disabled","true");
    return false;
  }else if(isNum("cardNo") == false){
    errorMsg = " 输入的银行卡号不正确，请核实";
  //银行卡号标志位
    $("#cardNoError").addClass("input-wrap-error");
    $("#error-cardNo").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
    $("#error-cardNo").show();
    $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
    $("#btn-bindCard").attr("disabled","true");
    return false;
  }else{
  //去掉错误样式
    /*$("#cardNoError").removeClass("input-wrap-error");
    $("#error-cardNo").html();
    $("#error-cardNo").fadeOut(300);*/
    return true;
    
  }
};
/**
 * 放大显示手机号
 */
function lookTel(){
  var tel = $("#tel").val();
  if(tel == ""){
    $("#lookTelBox").fadeOut(300);
    return false;
  }
  var lookTel = null;
  if(tel.length <= 3){
    lookTel = tel;
  }
  if(tel.length > 3 && tel.length <=7){
    lookTel = tel.substr(0,3)+" "+tel.substr(3);
  }
  if(tel.length > 7){
    lookTel = tel.substr(0,3)+" " + tel.substr(3,4)+" "+tel.substr(7);
  }
  $("#lookTelBox").html("<div id='lookTelBox' class='phoneZoom'>"+lookTel+"</div>");
  $("#lookTelBox").show();
}

/**
 * 放大身份证
 * @returns {Boolean}
 */
function lookIdCard(idCardId){
	  var idCard = $("#"+idCardId).val();
	  if(idCard == ""){
	    $("#idCardBox").html("");
	    return false;
	  }
	  var card = null;
	  if(idCard.length <= 6){
	    card = idCard;
	  }
	  if(idCard.length > 6 && idCard.length <= 14){
	    card = idCard.substr(0,6)+" "+idCard.substr(6);
	  }
	  if(idCard.length > 14){
	    card = idCard.substr(0,6)+" "+idCard.substr(6,8)+" "+idCard.substr(14); 
	  }
	  $("#idCardBox").html("<div class='phoneZoom' id='idCardBox'>"+card+"</div>");
	  $("#idCardBox").show();
}
/**
 * 放大银行卡号
 * @returns {Boolean}
 */
function lookCardNo(cardNoId){
    var cardNo = $("#"+cardNoId).val();
    if(cardNo == ""){
      $("#idCardBox").html("");
      return false;
    }
    var cno = null;
    if(cardNo.length <= 4){
      cno = cardNo;
    }
    if(cardNo.length > 4 && cardNo.length <= 8){
      cno = cardNo.substr(0,4)+" "+cardNo.substr(4);
    }
    if(cardNo.length > 8 && cardNo.length <= 12){
      cno = cardNo.substr(0,4)+" "+cardNo.substr(4,4)+" "+cardNo.substr(8);
    }
    if(cardNo.length > 12 && cardNo.length <= 16){
      cno = cardNo.substr(0,4)+" "+cardNo.substr(4,4)+" "+cardNo.substr(8,4)+" "+cardNo.substr(12);
    }
    if(cardNo.length > 16){
      cno = cardNo.substr(0,4)+" "+cardNo.substr(4,4)+" "+cardNo.substr(8,4)+" "+cardNo.substr(12,4)+" "+cardNo.substr(16);
    }
    $("#cardNoBox").html("<div class='phoneZoom' id='cardNoBox'>"+cno+"</div>");
    $("#cardNoBox").show();
}

//验证手机号是否注册
function verTelRegistered(){
  var tel = $("#tel").val();
  var result = false;
  var errorMsg = null;
  $.ajaxSetup({  
    async : false  
  });
    $.post ("account/verifyMobile",{mobileNo:tel},function(data){
      if(data == "FAILED"){
        errorMsg= " 请输入正确手机号";
        $("#tel-li").addClass("input-wrap-error");
        $("#error-tel").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-tel").show();
        $("#sendCode").removeClass("btn-small fr").addClass("btn-small-gray fr");
        $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-regist").attr("disabled","true");
        result = false;
        return false;
      }else if(data == "REGISTERED"){
        errorMsg= " 该手机号已注册，请直接登录";
        $("#tel-li").addClass("input-wrap-error");
        $("#error-tel").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-tel").show();
        $("#sendCode").removeClass("btn-small fr").addClass("btn-small-gray fr");
        $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-regist").attr("disabled","true");
        result = false;
        return false;
      }else if(data == "SUCCESS"){
        $("#tel-li").removeClass("input-wrap-error");
        $("#error-tel").html("");
        $("#error-tel").fadeOut(300);
        $("#sendCode").removeClass("btn-small-gray fr").addClass("btn-small fr");
        result = true;
        return true;
      }else if(data == "SYSTEM_EXCEPTION"){
        errorMsg= " 系统或网络异常，请稍后再试";
        $("#messageBox").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#messageBox").show();
        $("#sendCode").removeClass("btn-small fr").addClass("btn-small-gray fr");
        $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-regist").attr("disabled","true");
        result = false;
        return false;
      }
      
    },"text");
    return result;
}

