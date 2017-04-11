$(function(){
  $("#btn-regist").click(function(){
    $("#messageBox").fadeOut(300);
    if(!$('#agreeBox').is(':checked')){
      errorMsg= " 请阅读并同意注册相关协议";
      $("#error-agree").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
      $("#error-idCode").show();
      return false;
    }
    var telObj = $("#tel");
    var telFlag = false;
    if(telRuleCheck()){
      telFlag = verTel();
    }
    var pwdObj = $("#loginPwd");
    var pwdFlag = pwdSetupCheck("loginPwd");
    var identifyCodeObj = $("#identifyCode");
    var identifyCodeFlag = identifyCodeCheck();
    var verifyCodeObj = $("#verifyCode");
    var verifyCodeFlag = true;
    var srcNo = $("#srcNo").val();
    if(srcNo!= null && srcNo !=''){
      if(verifyCodeCheck()){
        verifyCodeFlag = codeVer();
      }
    }
    if(!telFlag || !pwdFlag || !identifyCodeFlag || !verifyCodeFlag){
      $("#btn-regist").attr("disabled",true);
      return false;
    }
    //获取返回的url
    var returnFlag = $("#returnFlag").html();
    var productId = $("#productId").html();
    $.ajax({
      url:'account/register',
      type:'post',
      dataType:'text',
      data:{tel:telObj.val(),loginPwd:pwdObj.val(),verifyCode:verifyCodeObj.val(),identifyCode:identifyCodeObj.val(),returnFlag:returnFlag,productId:productId},
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
          $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
          $("#btn-regist").attr("disabled","true");
        }else if(data == "SYSTEM_EXCEPTION"){
          errorMsg = " 系统或网络异常，请稍后再试";
          //弹层
          openLayer(errorMsg);
          refreshCode();
        }else if(data == "DYNAMIC_FAILED"){
          errorMsg = " 手机验证码输入错误，请核实";
          openLayer(errorMsg);
          $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
          $("#btn-regist").attr("disabled","true");
          refreshCode();
        }else if(data == "SUCCESS"){
          var returnFlag = $("#returnFlag").html();
          location.href = "account/toRegistSuc?returnFlag="+returnFlag;
        }else if(data == "REGISTERED"){
          errorMsg = " 该手机号已注册，请直接登录";
          openLayer(errorMsg);
          $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
          $("#btn-regist").attr("disabled","true");
          refreshCode();
        }
        
      }
    });
  });
  //协议
  $('#agreeBox').click(function(){
    var check = $('#agreeBox').is(':checked');
    var errorMsg;
    if(check){
        $('#error-agree').html("");
    }else{
        errorMsg = " 请阅读并同意相关协议"
        $('#error-agree').html("<i class='icon icon-error2'>"+errorMsg+"</i>");
    }
  });
});
//所有输入框keyup事件
function checkFormButton(){
  var telFlag = false;
  if(telRuleCheck()){
    telFlag = verTel();
  }
  var pwdFlag = pwdSetupCheck("loginPwd");
  var identifyCodeFlag = identifyCodeCheck();
  var verifyCodeFlag = true;
  var srcNo = $("#srcNo").val();
  if(srcNo!= null && srcNo !=''){
    if(verifyCodeCheck()){
      verifyCodeFlag = codeVer();
    }
  }
  if(telFlag && pwdFlag && identifyCodeFlag && verifyCodeFlag){
    $("#btn-regist").removeClass("btn-login-gray").addClass("btn-login");
    $("#btn-regist").attr("disabled",false);
  }else{
    $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
    $("#btn-regist").attr("disabled",true);
  }
};
//刷新验证码
function refreshCode(){
  var srcPath = ($("#securityCode").attr("src")).split("?")[0];
  $("#securityCode").attr("src", srcPath+"?timestamp="+Math.random());
};
//密码框获取焦点触发事件
function pwdLook(){
  errorMsg= " 登录密码必须为8-20位数字和字母组合";
  $("#setup-pwd-li").removeClass("input-wrap-error").addClass("input-wrap-tip");
  $("#error-setup-pwd").removeClass("error-tips red").addClass("error-tips orange");
  $("#error-setup-pwd").html("<i class='icon icon-tip'>"+errorMsg+"</i>");
  $("#error-setup-pwd").show();
}
//图片验证码失去焦点
function codeVer(){
  var verifyCode = $("#verifyCode").val();
//  var verifyCodeFlag = verifyCodeCheck();
//  if(verifyCodeFlag){
    $.ajax({
      url:'verPicCode.action',
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
          verify();
          $("#verify-code-li").removeClass("input-wrap-error");
          $("#error-verify-code").fadeOut(300);
          return true;
        }
      }
    });
//  }
};
/**
 * 验证手机号是否已注册
 */

function verTel(){
  var tel = $("#tel").val();
  var result = false;
 // if(!$("#tel-li").hasClass("input-wrap-error")){
  $.ajaxSetup({  
    async : false  
  });
    $.post ("account/verifyMobile",{mobileNo:tel},function(data){
      if(data == "FAILED"){
        errorMsg= " 请输入正确手机号";
        $("#tel-li").addClass("input-wrap-error");
        $("#error-tel").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-tel").show();
        $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-regist").attr("disabled","true");
        result = false;
        return false;
      }else if(data == "REGISTERED"){
        errorMsg= " 该手机号已注册，请直接登录";
        $("#tel-li").addClass("input-wrap-error");
        $("#error-tel").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-tel").show();
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
        $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-regist").attr("disabled","true");
        result = false;
        return false;
      }
      
    },"text");
    return result;
 // }
  
}
//弹层
function openLayer(errorMsg){
  $(".errorCon.red.pa").remove();
  $("#layerBody").append("<p class='errorCon red pa'>"+errorMsg+"</p>");
  $("#mask").show();
  $("#alertLayer").show();
}