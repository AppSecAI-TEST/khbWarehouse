$(function(){
  $("#btn-regist").click(function(){
    if($("#btn-regist").hasClass('send')){
      return false;
    }
    $("#messageBox").fadeOut(300);
    if(!$('#agreeBox').is(':checked')){
      errorMsg= " 请阅读并同意注册相关协议";
      $("#error-agree").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
      $("#error-idCode").show();
      return false;
    }
    var telObj = $("#tel");
    var telFlag = telRuleCheck('onblur',telObj);
    var loginPwdObj = $("#loginPwd");
    var loginPwdFlag = pwdSetupCheck('loginPwd','onblur');
    var identifyCodeObj = $("#identifyCode");
    var identifyCodeFlag = identifyCodeCheck('onblur');
    var verifyCodeObj = $("#verifyCode");
    var verifyCodeFlag = true;
    var srcNo = $("#srcNo").val(); 
  //微信公众号用户不需要图片验证码
    if(srcNo != null && srcNo !=''){
      verifyCodeFlag = verifyCodeCheck('onblur',verifyCodeObj);
    }
    /*if(telFlag && loginPwdFlag && identifyCodeFlag && verifyCodeFlag){
      $("#btn-regist").removeClass("btn-login-gray").addClass("btn-login");
      $("#btn-regist").attr("disabled",false);
    }else{
      $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-regist").attr("disabled",true);
      return false;
    }*/
  //获取返回的url
    var returnFlag = $("#returnFlag").html();
    var productId = $("#productId").html();
    $.ajax({
      url:'account/register',
      type:'post',
      async:false,
      dataType:'text',
      data:{tel:telObj.val(),loginPwd:loginPwdObj.val(),verifyCode:verifyCodeObj.val(),identifyCode:identifyCodeObj.val(),returnFlag:returnFlag,productId:productId},
      error: function(){
        errorMsg = " 请求失败，请稍后再试";
        openLayer(errorMsg);
      }, 
      success:function(data){
        if(data == "FAILED"){
          errorMsg = " 注册失败";
          //弹层
          openLayer(errorMsg);
          refreshCode();
        }else if(data == "VERIFY_FAILED"){
          errorMsg = " 验证码输入错误";
          $("#verify-code-li").addClass("input-wrap-error");
          $("#error-verify-code").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
          $("#error-verify-code").show();
          /*$("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
          $("#btn-regist").attr("disabled","true");*/
          refreshCode();
        }else if(data == "SYSTEM_EXCEPTION"){
          errorMsg = " 系统或网络异常，请稍后再试";
          //弹层
          openLayer(errorMsg);
          refreshCode();
        }else if(data == "DYNAMIC_FAILED"){
          errorMsg = " 手机验证码输入错误，请核实";
          openLayer(errorMsg);
          /*$("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
          $("#btn-regist").attr("disabled","true");*/
          refreshCode();
        }else if(data == "SUCCESS"){
          $("#btn-regist").addClass('send');
          var returnFlag = $("#returnFlag").html();
          var activityCode = $("#activityCode").html();
          location.href = "account/toSwitchRegistSuc?returnFlag="+returnFlag+"&activityCode="+activityCode;
        }else if(data == "REGISTERED"){
          errorMsg = " 该手机号已注册，请直接登录";
          openLayer(errorMsg);
          /*$("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
          $("#btn-regist").attr("disabled","true");*/
          refreshCode();
        }
        
      }
    });
  });
});
//keyUp和onblur事件调用方法
function checkFormButton(eventName,obj){
  var telFlag = telRuleCheck(eventName,obj);
  var loginPwdFlag = pwdSetupCheck('loginPwd',eventName);
  var identifyCodeFlag = identifyCodeCheck(eventName);
  var verifyCodeFlag = true;
  var srcNo = $("#srcNo").val();
  //微信公众号用户不需要图片验证码
  if(srcNo != null && srcNo !=''){
    verifyCodeFlag = verifyCodeCheck(eventName,obj);
  }
  /*if(telFlag && loginPwdFlag && identifyCodeFlag && verifyCodeFlag){
    $("#btn-regist").removeClass("btn-login-gray").addClass("btn-login");
    $("#btn-regist").attr("disabled",false);
  }else{
    $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
    $("#btn-regist").attr("disabled",true);
  }*/
}
//手机号规则验证
function telRuleCheck(eventName,obj){
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
    /*$("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
    $("#btn-regist").attr("disabled",true);*/
    return false;
  }
  if(tel.length != '' && tel.length < 11){
    //长度不满足
    if(eventName == 'keyUp'){
      $("#sendCode").removeClass("btn-small fr").addClass("btn-small-gray fr");
      /*$("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-regist").attr("disabled",true);*/
      return false;
    }else{
      errorMsg = " 请输入正确手机号";
      telFlag = true;
    }
  }else if(!isMobile("tel")){
    errorMsg = " 只支持手机号注册";
    telFlag = true;
  }
  
  if(telFlag){
  //输入框变成红色
    $("#tel-li").addClass("input-wrap-error");
    //显示错误信息
    $("#error-tel").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
    $("#error-tel").show();
    $("#sendCode").removeClass("btn-small fr").addClass("btn-small-gray fr");
    /*$("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
    $("#btn-regist").attr("disabled",true);*/
    return false;
  }else{
    if(eventName == 'keyUp'){
      /*$("#tel-li").removeClass("input-wrap-error");
      $("#error-tel").html("");
      $("#error-tel").fadeOut(300);*/
      return true;
    }
  //onblur事件，验证手机号是否注册过
    if(obj.id != 'tel'){
      return true;
    }
    telFlag = verTelRegistered();
    if(telFlag){
      if($("#sendCode").hasClass("register")){
        if(!$("#sendCode").hasClass("send")){
          $("#sendCode").removeClass("btn-small-gray fr").addClass("btn-small fr");
        }
      }
      return true;
    }else{
      /*$("#sendCode").removeClass("btn-small fr").addClass("btn-small-gray fr");
      $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-regist").attr("disabled",true);*/
      return false;
    }
  }
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
        /*$("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-regist").attr("disabled","true");*/
        result = false;
        return false;
      }else if(data == "REGISTERED"){
        errorMsg= " 该手机号已注册，请直接登录";
        $("#tel-li").addClass("input-wrap-error");
        $("#error-tel").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-tel").show();
        $("#sendCode").removeClass("btn-small fr").addClass("btn-small-gray fr");
        /*$("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-regist").attr("disabled","true");*/
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
        /*$("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-regist").attr("disabled","true");*/
        result = false;
        return false;
      }
      
    },"text");
    return result;
}
/**
 * 密码框获取焦点触发事件
 */
function pwdLook(){
  errorMsg= " 登录密码必须为8-20位数字和字母组合";
  $("#setup-pwd-li").removeClass("input-wrap-error").addClass("input-wrap-tip");
  $("#error-setup-pwd").removeClass("error-tips red").addClass("error-tips orange");
  $("#error-setup-pwd").html("<i class='icon icon-tip'>"+errorMsg+"</i>");
  $("#error-setup-pwd").show();
}
//弹层
function openLayer(errorMsg){
  $(".errorCon.red.pa").remove();
  $("#layerBody").append("<p class='errorCon red pa'>"+errorMsg+"</p>");
  $("#mask").show();
  $("#alertLayer").show();
}