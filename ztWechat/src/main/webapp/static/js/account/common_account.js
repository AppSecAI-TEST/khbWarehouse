/**
 * 手机号规则校验
 */
function telRuleCheckCom(eventName) {
  //调用放大显示手机号
  lookTel();
  
//keyUp事件不显示错误信息，只控制按钮显示
  //onblur事件显示错误信息
  var tel = $("#tel").val();
  var errorMsg = null;
  //标志，为true时，
  var telFlag = false;
  //手机号为空时，不显示错误信息
  if(tel == ''){
    $("#tel-li").removeClass("input-wrap-error");
    $("#error-tel").html("");
    $("#error-tel").fadeOut(300);
    $("#sendCode").removeClass("btn-small fr").addClass("btn-small-gray fr");
    $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
    $("#btn-regist").attr("disabled",true);
    return false;
  }
  if(tel.length != '' && tel.length < 11){
    //长度不满足
    if(eventName == 'keyUp'){
      $("#sendCode").removeClass("btn-small fr").addClass("btn-small-gray fr");
      $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-regist").attr("disabled",true);
      return false;
    }else{
      errorMsg = " 请输入正确手机号";
      telFlag = true;
    }
  }else if(!isMobile("tel")){
    errorMsg = " 请输入正确的手机号";
    telFlag = true;
  }
  
  if(telFlag){
  //输入框变成红色
    $("#tel-li").addClass("input-wrap-error");
    //显示错误信息
    $("#error-tel").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
    $("#error-tel").show();
    $("#sendCode").removeClass("btn-small fr").addClass("btn-small-gray fr");
    $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
    $("#btn-regist").attr("disabled",true);
    return false;
  }else{
  //  if($("#sendCode").hasClass("card")){
    if(!$("#sendCode").hasClass("send")){
      $("#sendCode").removeClass("btn-small-gray fr").addClass("btn-small fr");
    }
  //  }
    $("#tel-li").removeClass("input-wrap-error");
    $("#error-tel").html("");
    $("#error-tel").fadeOut(300);
    return true;
  }
}
/**
 * 密码长度校验
 * @param 当前事件触发
 * @param 当前对象
 * @returns {Boolean}
 */
function pwdLengthCheck(eventName,obj) {
  var pwd = obj.val();
  var pwdLi = $("#password-li");
  var pwdError = false;
  var errorMsg = null;
  if(pwd == '') {
    pwdLi.removeClass("input-wrap-error");
    $("#error-password").html("");
    $("#error-password").fadeOut(300);
    $("#btn-login").removeClass("btn-login").addClass("btn-login-gray");
    $("#btn-login").attr("disabled",true);
    return false;
  }
  if (pwd != '' && (pwd.length < 6 || pwd.length > 20)) {
    if(eventName == 'keyUp'){
      $("#btn-login").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-login").attr("disabled",true);
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
    return true;
  }
  
}
/**
 *密码框规则验证
 */
function pwdSetupCheck(pwdId,eventName){
  var pwd = $("#"+pwdId).val();
  var pwdFlag = false;
  var errorMsg = null;
  if(pwd == '') {
    $("#setup-pwd-li").removeClass("input-wrap-error").removeClass("input-wrap-tip");
    //显示错误信息
    $("#error-setup-pwd").html("");
    $("#error-setup-pwd").fadeOut(300);
    $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
    $("#btn-regist").attr("disabled",true);
    return false;
  }
  if(pwd.length < 8 || pwd.length > 20){
    if(eventName == 'keyUp'){
      $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-regist").attr("disabled",true);
      return false;
    }else{
      errorMsg = " 登录密码必须为8-20位数字和字母组合";
      pwdFlag = true;
    }
  }else if(!isPwd(pwdId)) {
    if(eventName == 'keyUp'){
      $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-regist").attr("disabled",true);
      return false;
    }
    errorMsg= " 登录密码必须为8-20位数字和字母组合";
    pwdFlag = true;
  }
  
  if(pwdFlag){
    //输入框变红色
    $("#setup-pwd-li").removeClass("input-wrap-tip").addClass("input-wrap-error");
    //显示错误信息
    $("#error-setup-pwd").removeClass("error-tips orange").addClass("error-tips red");
    $("#error-setup-pwd").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
    $("#error-setup-pwd").show();
    $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
    $("#btn-regist").attr("disabled",true);
    return false;
  }else{
    $("#setup-pwd-li").removeClass("input-wrap-error").removeClass("input-wrap-tip");
    //显示错误信息
    $("#error-setup-pwd").html("");
    $("#error-setup-pwd").fadeOut(300);
    return true;
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
//      $("#error-setup-pwd").removeClass("error-tips orange").addClass("error-tips red");
      $("#error-"+obj2ErrorId).html("");
      $("#error-"+obj2ErrorId).fadeOut(300);
    return false;
  }
  
  if(obj1.val() ==  obj2.val()) {
    $("#"+obj2ErrorId+"-li").removeClass("input-wrap-error");
      //显示错误信息
//      $("#error-setup-pwd").removeClass("error-tips orange").addClass("error-tips red");
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
 * 显示密码
 * pwdId 密码输入框ID
 * pwdType 密码框文本提示信息
 */
function lookPwd(pwdId,pwdPlaceHolder){
  var pwd = $("#"+pwdId);
  var pwdVal = pwd.val();
  if(pwd.attr("type") == "password"){
    $("#lookBox-"+pwdId).html("<input type=\"text\" class=\"input-text\" id=\""+pwdId+"\" name=\""+pwdId+"\" size=\"20\" placeholder=\""+pwdPlaceHolder+"\" onfocus=\"pwdLook()\" onkeyup=\"checkFormButton('keyUp',this)\" onblur=\"checkFormButton('onblur',this)\" value="+pwdVal+">");
    $("#look-"+pwdId).removeClass("icon icon-unlook orange fr").addClass("icon icon-look orange fr");
  }else{
    $("#lookBox-"+pwdId).html("<input type=\"password\" class=\"input-text\" id=\""+pwdId+"\" name=\""+pwdId+"\" size=\"20\" placeholder=\""+pwdPlaceHolder+"\" onfocus=\"pwdLook()\" onkeyup=\"checkFormButton('keyUp',this)\" onblur=\"checkFormButton('onblur',this)\" value="+pwdVal+">");
      $("#look-"+pwdId).removeClass("icon icon-look orange fr").addClass("icon icon-unlook orange fr");
  }
}
/**
 *短信验证码验证规则
 */
function identifyCodeCheck(eventName){
  var identifyCode = $("#identifyCode").val();
  var identifyCodeLi = $("#identify-code-li");
  var identifyCodeFlag = false;
  var errorMsg = null;
  if(identifyCode == '') {
    identifyCodeLi.removeClass("input-wrap-error");
    $("#error-identify-code").html("");
    $("#error-identify-code").fadeOut(300);
    $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
    $("#btn-regist").attr("disabled",true);
    return false;
  }
  if (identifyCode != '' && identifyCode.length != 6) {
    if(eventName == 'keyUp'){
      $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-regist").attr("disabled",true);
      return false;
    }
    errorMsg = " 请输入正确的短信验证码";
    identifyCodeFlag = true;
  }
  
  if(identifyCodeFlag){
    //显示错误信息
    identifyCodeLi.addClass("input-wrap-error");
    $("#error-identify-code").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
    $("#error-identify-code").show();
    $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
    $("#btn-regist").attr("disabled",true);
    return false;
  }else{
    identifyCodeLi.removeClass("input-wrap-error");
    $("#error-identify-code").html("");
    $("#error-identify-code").fadeOut(300);
    return true;
  }
}
/**
 * 刷新验证码
 */
function refreshCode(){
  var srcPath = ($("#securityCode").attr("src")).split("?")[0];
  $("#securityCode").attr("src", srcPath+"?timestamp="+Math.random());
};
/**
 * 图片验证码验证规则
 */
function verifyCodeCheck(eventName,obj){
  var verifyCode = $("#verifyCode").val();
  var verifyCodeLi = $("#verify-code-li");
  var verifyCodeFlag = false;
  if(verifyCode == ''){
    //去掉错误样式,注册置为灰色
    verifyCodeLi.removeClass("input-wrap-error");
    $("#error-verify-code").html("");
    $("#error-verify-code").fadeOut(300);
    $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
    $("#btn-regist").attr("disabled",true);
    return false;
  }
  if(verifyCode != '' && verifyCode.length != 4){
    if(eventName == 'keyUp'){
      $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-regist").attr("disabled",true);
      return false;
    }else{
      errorMsg = " 验证码输入错误";
      verifyCodeFlag = true;
    }
  }
  //没通过验证
  if(verifyCodeFlag){
    verifyCodeLi.removeClass("input-wrap-tip").addClass("input-wrap-error");
    $("#error-verify-code").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
    $("#error-verify-code").show();
    $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
    $("#btn-regist").attr("disabled",true);
    refreshCode();
    return false;
  }else{
    if(eventName == 'keyUp'){
      return true;
    }else{
      if(obj.id != 'verifyCode'){
        return true;
      }
      //验证验证码是否正确
      verifyCodeFlag = codeVer();
      if(verifyCodeFlag){
        return true;
      }else{
        return false;
      }
    }
  }
}
//验证图片验证码是否正确
function codeVer(){
  var verifyCode = $("#verifyCode").val();
  var codeFlag = false;
  $.ajaxSetup({  
    async : false  
  });
  
    $.ajax({
      url:'account/verPicCode.action',
      type:'post',
      dataType:'text',
      data:{errorCount:1,verifyCode:verifyCode},
      error:function(){
        errorMsg=" 请求验证失败，请稍后再试";
        //输入框颜色和提示信息
        $("#verify-code-li").addClass("input-wrap-error");
        $("#error-verify-code").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-verify-code").show();
        codeFlag = false;
        $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-regist").attr("disabled","true");
        refreshCode();
        return false;
      },
      success:function(data){
        if(data == "VERIFY_FAILED"){
          errorMsg=" 验证码输入错误";
          //输入框颜色和提示信息
          $("#verify-code-li").addClass("input-wrap-error");
          $("#error-verify-code").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
          $("#error-verify-code").show();
          codeFlag = false;
          $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
          $("#btn-regist").attr("disabled","true");
          refreshCode();
          return false;
        }else if(data == "SUCCESS"){
          codeFlag = true;
        //输入框颜色和提示信息
          $("#verify-code-li").removeClass("input-wrap-error");
          $("#error-verify-code").html("");
          $("#error-verify-code").fadeOut(300);
          return true;
        }
      }
    });
    return codeFlag;
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
      $("#cardNoBox").html("");
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
$(function(){
  var timer = null;
  var num = 59;
  $('#sendCode').click(function(){
    //当短信发送框变灰的时候 点击不再向后台发送请求
    if($('#sendCode').hasClass('btn-small-gray')){
         return false;
    }
      var sendNewPhoneFlag = false;
      var action = "account/sendOldMobileNoCode";
      var newMobileNo = "";
      newMobileNo = $("#tel").val();
      if($('#sendCode').hasClass('newPhone')) {
      action = "account/sendNewMobileNoCode";
     // newMobileNo = $("#tel").val();
      var telRuleCheckFlag = telRuleCheck('blur','tel',true);
        if(!telRuleCheckFlag) {
          return false;
        }
      } else if($('#sendCode').hasClass('register')){
        action = "account/sendRegisterCode";
      } else if($('#sendCode').hasClass('card')){
        action = "account/card/sendBindBankCardCode";
      } else if($('#sendCode').hasClass('resetLogin')){
        action = "account/sendResetLoginPwdCode";
      } 
      
      $.post(action,{mobileNo:newMobileNo},function(data){
        if(data == 'SUCCESS'){
          clearInterval(timer);
          timer = setInterval(function(){
              $('#sendCode').html(num + 's后重新获取');
              $('#sendCode').removeClass("btn-small fr").addClass("btn-small-gray fr");
              //这种状态下，不论什么情况，该链接都是灰色
              $('#sendCode').addClass("send");
              if(num == 0){
                  clearInterval(timer);
                  $('#sendCode').removeClass("send");
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
    //      $("#messageBox").html("<label class='error'><i class='icon-tips'></i><span class='font-tip'>获取验证码失败，请稍候再试</span></label>");
        }else if(data = "SYSTEM_EXCEPTION"){
          errorMsg = " 系统或网络异常，请稍后再试";
          //TODO
          /*clearInterval(timer);
          timer = setInterval(function(){
              $('#sendCode').html(num + 's后重新获取');
              $('#sendCode').removeClass("btn-small fr").addClass("btn-small-gray fr");
              //这种状态下，不论什么情况，该链接都是灰色
              $('#sendCode').addClass("send");
              if(num == 0){
                  clearInterval(timer);
                  $('#sendCode').removeClass("send");
                  $('#sendCode').removeClass('btn-small-gray fr').addClass('btn-small fr');
                  $('#sendCode').html("再次获取");
                  num = 60;
              }
              num--;
          },1000);*/

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