$(function(){
  var timer = null;
  var num = 59;
  
  $("#btn-bindCard").click(function(){
    var tradePwdObj = $("#tradePwd");
    var realName = $("#realName").val();
    var idCardObj = $("#idCard");
    var cardNoObj = $("#cardNo");
    var tel = $("#tel").val();
    var identifyCode = $("#identifyCode").val();
    
    var tradePwdFlag = pwdSetUpTrdCheck("tradePwd",'onblur',tradePwdObj);
    var realNameFlag = verRealName('onblur');
    var idCardFlag = idCardNoCheck('onblur',idCardObj);
    var cardNoFlag = bankCardNoCheck('onblur',cardNoObj);
    var telFlag = telRuleCheckCom('onblur');
    var identifyCodeFlag = identifyCodeCheck('onblr');
    
    if(tradePwdFlag && realNameFlag && idCardFlag && cardNoFlag && telFlag && identifyCodeFlag){
      $("#btn-bindCard").removeClass("btn-login-gray").addClass("btn-login");
      $("#btn-bindCard").attr("disabled",false);
    }else{
      $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-bindCard").attr("disabled",true);
      return false;
    }
    //获取返回url的参数
    var productId = $("#productId").html();
    var amount = $("#amount").html();
    var ret = $("#ret").html();
    var expectIncome = $("#expectIncome").html();
    var promoNo = $("#promoNo").html();
    var returnFlag = $("#returnFlag").html();
    var errorMsg;
  //绑定银行卡
    $.ajax({
      url:'account/card/addBankCard',
      type:'post',
      dataType:'text',
      data:{realName:realName,idCard:idCardObj.val(),cardNo:cardNoObj.val(),tel:tel,identifyCode:identifyCode,tradePwd:tradePwdObj.val(),productId:productId,amount:amount,ret:ret,expectIncome:expectIncome,promoNo:promoNo,returnFlag:returnFlag},
      error:function(){
        errorMsg = " 请求失败，请稍后再试";
        //弹层
        openLayer(errorMsg);
      },
      success:function(data){
        //弹层
        if(data == "SYSTEM_EXCEPTION"){
          errorMsg = " 系统或网络异常，请稍后再试";
          //弹层
          openLayer(errorMsg);
          
        }else if(data == "SUCCESS"){
          //跳转页面，绑卡成功
          var activityCode = $("#activityCode").html();
          location.href="account/card/toSwitchBindCardSuc?activityCode="+activityCode;
        }else if(data =="FAILED"){
          errorMsg = " 绑卡失败";
          openLayer(errorMsg);
        }else if(data == "DYNAMIC_FAILED"){
          errorMsg = " 手机验证码输入错误或过期，请核实";
          openLayer(errorMsg);
        }else if(data == "REGISTERED"){
          errorMsg = " 该身份证号已经注册";
          openLayer(errorMsg);
        }else if(data == "IDCARDERROR"){
          errorMsg = " 请输入您首次绑卡的姓名及身份证号";
          openLayer(errorMsg);
        }else if(data == "\"noLogin\""){
          location.href = "account/toLogin";
        } else{
          openLayer(data);
        }
      }
    });
  });
  //绑卡发送短信验证码
  $("#sendBandCardCode").click(function(){
  //当短信发送框变灰的时候 点击不再向后台发送请求
    if($('#sendBandCardCode').hasClass('btn-small-gray')){
         return false;
    }
    var tradePwdObj = $("#tradePwd");
    var realName = $("#realName").val();
    var idCardObj = $("#idCard");
    var cardNoObj = $("#cardNo");
    var tel = $("#tel").val();
    
    var tradePwdFlag = pwdSetUpTrdCheck("tradePwd",'onblur',tradePwdObj);
    var realNameFlag = verRealName('onblur');
    var idCardFlag = idCardNoCheck('onblur',idCardObj);
    var cardNoFlag = bankCardNoCheck('onblur',cardNoObj);
    var telFlag = telRuleCheckCom('onblur');
    //先验证所有参数是否符合要求，不符合提示相应信息，符合则调远程服务发送短信验证码
    if(tradePwdFlag && realNameFlag && idCardFlag && cardNoFlag && telFlag){
      //ajax调用远程服务
      $.ajax({
        url: 'account/card/sendBindBankCardCode',
        type: 'post',
        dataType: 'text',
        data: {realName:realName,idCard:idCardObj.val(),cardNo:cardNoObj.val(),mobileNo:tel,tradePwd:tradePwdObj.val()},
        error:function(){
          errorMsg = " 请求失败，请稍后重试";
          //错误样式，提示信息
          $("#identify-code-li").addClass("input-wrap-error");
          $("#error-identify-code").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
          $("#error-identify-code").show();
        },
        success:function(data){
          if(data == "SUCCESS"){
            clearInterval(timer);
            timer = setInterval(function(){
                $('#sendBandCardCode').html(num + 's后重新获取');
                $('#sendBandCardCode').removeClass("btn-small fr").addClass("btn-small-gray fr");
                //这种状态下，不论什么情况，该链接都是灰色
                $('#sendBandCardCode').addClass("send");
                if(num == 0){
                    clearInterval(timer);
                    $('#sendBandCardCode').removeClass("send");
                    $('#sendBandCardCode').removeClass('btn-small-gray fr').addClass('btn-small fr');
                    $('#sendBandCardCode').html("再次获取");
                    num = 60;
                }
                num--;
            },1000);
          }else if(data == 'SYSTEM_EXCEPTION'){
            errorMsg = " 系统或网络异常，请稍后再试";
            $("#messageBox").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
            $("#messageBox").show();
          }else if(data == "\"noLogin\""){
            location.href = "account/toLogin";
          }else{
            errorMsg = data;
            $("#messageBox").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
            $("#messageBox").show();
          }
        },
        
      });
    }else{
      return false;
    }
  });
});

//keyup事件，onblur事件
function checkFormButton(eventName,obj) {
  var tradePwdFlag = pwdSetUpTrdCheck("tradePwd",eventName,obj);
  var realNameFlag = verRealName(eventName);
  var idCardFlag = idCardNoCheck(eventName,obj);
  var cardNoFlag = bankCardNoCheck(eventName,obj);
  var telFlag = telRuleCheckCom(eventName);
  var identifyCodeFlag = identifyCodeCheck(eventName);
  
  //交易密码和四要素齐全，发送短信验证码显示 TODO
  if(tradePwdFlag && realNameFlag && idCardFlag && cardNoFlag && telFlag){
    //由于keyup事件不会请求后台验证，所以当身份证和银行卡后台验证失败时，不显示发送短信按钮
      if($("#idCard-li").hasClass("input-wrap-error") /*|| $("#cardNoError").hasClass("input-wrap-error")*/){
          $("#sendBandCardCode").removeClass("btn-small fr").addClass("btn-small-gray fr");
      }else{
          if(!$('#sendBandCardCode').hasClass('send')){
            $("#sendBandCardCode").removeClass("btn-small-gray fr").addClass("btn-small fr");
          }
      }
      //优化
      if(identifyCodeFlag){
        $("#btn-bindCard").removeClass("btn-login-gray").addClass("btn-login");
        $("#btn-bindCard").attr("disabled",false);
      }else{
        $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-bindCard").attr("disabled",true);
      }
  }else{
      $("#sendBandCardCode").removeClass("btn-small fr").addClass("btn-small-gray fr");
      //优化
      $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-bindCard").attr("disabled",true);
  }
  //可以优化
  /*if(tradePwdFlag && realNameFlag && idCardFlag && cardNoFlag && telFlag && identifyCodeFlag){
      $("#btn-bindCard").removeClass("btn-login-gray").addClass("btn-login");
      $("#btn-bindCard").attr("disabled",false);
  }else{
      $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-bindCard").attr("disabled",true);
  }*/
}

/**
 *交易密码框规则验证
 */
function pwdSetUpTrdCheck(pwdId,eventName,obj){
  var pwd = $("#"+pwdId).val();
  var pwdFlag = false;
  var errorMsg = null;
  if(pwd == '') {
    if(eventName == 'keyUp' && obj.id == 'tradePwd'){
      /*$("#setup-pwd-li").removeClass("input-wrap-error").removeClass("input-wrap-tip");
      $("#error-setup-pwd").html("");
      $("#error-setup-pwd").fadeOut(300);*/
    }else{
      errorMsg = "请先设置交易密码";
      //输入框变红色
      $("#setup-pwd-li").removeClass("input-wrap-tip").addClass("input-wrap-error");
      //显示错误信息
      $("#error-setup-pwd").removeClass("error-tips orange").addClass("error-tips red");
      $("#error-setup-pwd").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
      $("#error-setup-pwd").show();
    }
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
      errorMsg = " 交易密码必须为8-20位数字和字母组合";
      pwdFlag = true;
    }
  }else if(!isPwd(pwdId)) {
    if(eventName == 'keyUp'){
      $("#btn-regist").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-regist").attr("disabled",true);
      return false;
    }
    errorMsg= " 交易密码必须为8-20位数字和字母组合";
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
    if(eventName == 'keyUp'){
      return true;
    }
    if(obj.id != 'tradePwd'){
      return true;
    }
  //验证交易密码和登录密码是否一致
    pwdFlag = verTradePwd();
    if(pwdFlag){
      return true;
    }else{
      return false;
    }
    
  }
}

/**
 *验证交易密码和登录密码是否一致
 */
function verTradePwd(){
  var tradePwd = $("#tradePwd").val();
  var trdFlag = false;
  var errorMsg = null;
  $.ajaxSetup({  
    async : false  
  });
  $.ajax({
    url:'account/card/verTradePwd',
    type:'post',
    dataType:'text',
    async:false,
    data:{tradePwd:tradePwd},
    error:function(){
      errorMsg = " 验证失败,请稍后再试";
      $("#setup-pwd-li").removeClass("input-wrap-tip").addClass("input-wrap-error");
      $("#error-setup-pwd").removeClass("error-tips orange").addClass("error-tips red");
      $("#error-setup-pwd").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
      $("#error-setup-pwd").show();
      //交易密码标志为false
      trdFlag = false;
      $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-bindCard").attr("disabled","true");
    },
    success:function(data){
      if(data == "FAILED"){
        errorMsg = " 验证交易密码失败";
      //框变红，提示错误信息
        $("#setup-pwd-li").removeClass("input-wrap-tip").addClass("input-wrap-error");
        $("#error-setup-pwd").removeClass("error-tips orange").addClass("error-tips red");
        $("#error-setup-pwd").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-setup-pwd").show();
        //交易密码标志位
        trdFlag = false;
        $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-bindCard").attr("disabled","true");
        //框变红，提示错误信息
      }else if(data == "SYSTEM_EXCEPTION"){
        errorMsg = " 系统或网络异常，请稍候再试";
        $("#setup-pwd-li").removeClass("input-wrap-tip").addClass("input-wrap-error");
        $("#error-setup-pwd").removeClass("error-tips orange").addClass("error-tips red");
        $("#error-setup-pwd").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-setup-pwd").show();
        trdFlag = false;
        $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-bindCard").attr("disabled","true");
      }else if(data == "SUCCESS"){
        //交易密码标志位
        trdFlag = true;
        //显示“交易密码用于您在平台交易时的操作验证，请牢记！”
        errorMsg = "交易密码用于您在平台交易时的操作验证，请牢记！";
//        $("#setup-pwd-li").removeClass("input-wrap-error").addClass("input-wrap-tip");
        $("#error-setup-pwd").removeClass("error-tips red").addClass("error-tips orange");
        $("#error-setup-pwd").html("<i class='icon icon-tip'>"+errorMsg+"</i>");
        $("#error-setup-pwd").show();
//        $("#error-setup-pwd").html("");
//        $("#error-setup-pwd").fadeOut(300);
//        $("#setup-pwd-li").removeClass("input-wrap-error").removeClass("input-wrap-tip");
      }else if(data == "\"noLogin\""){
        location.href = "account/toLogin";
      }else{
        errorMsg = data;
        //交易密码标志位
        $("#setup-pwd-li").removeClass("input-wrap-tip").addClass("input-wrap-error");
        $("#error-setup-pwd").removeClass("error-tips orange").addClass("error-tips red");
        $("#error-setup-pwd").html("<i class='icon icon-error2'>"+data+"</i>");
        $("#error-setup-pwd").show();
        //交易密码标志位
        trdFlag = false;
        $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-bindCard").attr("disabled","true");
      }
    }
  });
  return trdFlag;
}
/*
 * 验证姓名
 */
function verRealName(eventName){
  var errorMsg;
  var realName = $("#realName").val();
  var realNameFlag = false;
  if(realName == ''){
    //姓名标志位
    $("#nameError").removeClass("input-wrap-error");
    $("#error-realname").html("");
    $("#error-realname").fadeOut(300);
    $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
    $("#btn-bindCard").attr("disabled","true");
    realNameFlag = false;
    return false;
  }
  if(isChinese("realName") == false){
    errorMsg = " 请输入中文姓名";
    realNameFlag = true;
  }
  
  if(realNameFlag){
    if(eventName != 'keyUp'){
      $("#nameError").attr("class","input-wrap-error");
      $("#error-realname").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
      $("#error-realname").show();
      $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-bindCard").attr("disabled","true");
      return false;
    }else{
      $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-bindCard").attr("disabled","true");
      return false;
    }
  }else{
    $("#nameError").removeClass("input-wrap-error");
    $("#error-realname").html("");
    $("#error-realname").fadeOut(300);
    return true;
  }
};
/**
 * 验证身份证规则
 */
function idCardNoCheck(eventName,obj){
//身份证放大
  lookIdCard("idCard");
  
  var idCard = $("#idCard").val();
  var idCardLi = $("#idCard-li");
  var errorMsg = null;
  var idCardFlag = false;
  if(idCard == ''){
    idCardLi.removeClass("input-wrap-error");
    $("#error-idCard").html("");
    $("#error-idCard").fadeOut(300);
    $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
    $("#btn-bindCard").attr("disabled",true);
    return false;
  }
  if(idCard != '' && idCard.length != 18){
    if(eventName == 'keyUp'){
      $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-bindCard").attr("disabled",true);
      return false;
    }else{
      errorMsg = " 身份证号有误，请核实";
      idCardFlag = true;
    }
  }/*else if(!isIdCardNo('idCard')){
    errorMsg = " 身份证号有误，请核实";
    idCardFlag = true;
  }*/else if(!callIdCodeValid('idCard')){
    errorMsg = " 身份证号有误，请核实";
    idCardFlag = true;
  }
  
  if(idCardFlag){
    $("#idCard-li").addClass("input-wrap-error");
    $("#error-idCard").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
    $("#error-idCard").show();
    $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
    $("#btn-bindCard").attr("disabled",true);
    return false;
  }else{
    //当不校验身份证是否注册时，以下代码解除注释
//    idCardLi.removeClass("input-wrap-error");
//    $("#error-idCard").html("");
//    $("#error-idCard").fadeOut(300);
//    return true;
    //当不校验身份证是否注册时，以下代码加上注释
    if(eventName == 'keyUp'){
      return true;
    }
    if(obj.id != 'idCard'){
      return true;
    }
    //验证身份证是否注册过
    idCardFlag = verIdCard();
    if(idCardFlag){
      return true;
    }else{
      return false;
    }
  }
}
/*
 * 验证身份证号是否注册
 */
function verIdCard(){
  var idCard = $("#idCard").val();
  var idCardFlag = false;
  var errorMsg = null;
  $.ajaxSetup({  
    async : false  
  });
  $.ajax({
    url:'account/card/verIdCard',
    type:'post',
    dataType:'text',
    data:{idCard:idCard},
    error:function(){
      errorMsg = " 请求验证失败";
      $("#idCard-li").addClass("input-wrap-error");
      $("#error-idCard").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
      $("#error-idCard").show();
      //身份证标志位
      idCardFlag = false;
      $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-bindCard").attr("disabled","true");
    },
    success:function(data){
      if(data == "FAILED"){
        errorMsg = " 验证身份证信息失败";
        $("#idCard-li").addClass("input-wrap-error");
        $("#error-idCard").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-idCard").show();
        //身份证标志位
        idCardFlag = false;
        $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-bindCard").attr("disabled","true");
      }else if(data == "SYSTEM_EXCEPTION"){
        errorMsg = " 系统或网络异常，请稍候再试";
        $("#idCard-li").addClass("input-wrap-error");
        $("#error-idCard").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-idCard").show();
        idCardFlag = false;
        $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-bindCard").attr("disabled","true");
      }else if(data == "SUCCESS"){
     //   $("#error-idCard").html("");
        //身份证标志位
        idCardFlag = true;
        $("#idCard-li").removeClass("input-wrap-error");
        $("#error-idCard").html();
        $("#error-idCard").fadeOut(300);
        
      }else if(data == "REGISTERED"){
        errorMsg = " 该身份证号已经注册";
        $("#idCard-li").addClass("input-wrap-error");
        $("#error-idCard").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-idCard").show();
        //身份证标志位
        idCardFlag = false;
        $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-bindCard").attr("disabled","true");
      }else if(data == "IDCARDERROR"){
        errorMsg = " 请输入您首次绑卡的姓名及身份证号";
        $("#idCard-li").addClass("input-wrap-error");
        $("#error-idCard").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-idCard").show();
        //身份证标志位
        idCardFlag = false;
        $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-bindCard").attr("disabled","true");
      }else if(data == "\"noLogin\""){
        location.href = "account/toLogin";
      }
    }
    
  });
  return idCardFlag;
}
/**
 * 验证银行卡号
 */
function bankCardNoCheck(eventName,obj){
//放大显示银行卡号
  lookCardNo("cardNo");
  var cardNo = $("#cardNo").val();
  var cardNoFlag = false;
  var errorMsg;
  //银行名称
  var bankName;
  if(cardNo == ''){
    //去掉错误样式
    $("#cardNoError").removeClass("input-wrap-error");
    $("#error-cardNo").fadeOut(300);
    $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
    $("#btn-bindCard").attr("disabled","true");
    return false;
  }else if(isNum("cardNo") == false){
    if(eventName == 'keyUp'){
      $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-bindCard").attr("disabled","true");
      return false;
    }
    errorMsg = " 输入的银行卡号不正确，请核实";
  //银行卡号标志位
    cardNoFlag = true;
  }
  if(cardNoFlag){
    $("#cardNoError").addClass("input-wrap-error");
    $("#error-cardNo").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
    $("#error-cardNo").show();
    $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
    $("#btn-bindCard").attr("disabled","true");
    return false;
  }else{
    if(eventName == 'keyUp'){
      return true;
    }
    if(obj.id != 'cardNo'){
      return true;
    }
    cardNoFlag = verCardNo();
    var cardFlag=verCard();
    if(cardNoFlag&&cardFlag){
      return true;
    }else{
      return false;
    }
  //去掉错误样式
   /* $("#cardNoError").removeClass("input-wrap-error");
    $("#error-cardNo").fadeOut(300);
    return true;*/
  }
}
/**
 * 验证银行卡是否支持鉴全
 */
//TODO
function verCard(){
  var cardNo = $("#cardNo").val();
  var cardNoFlag = false;
  var bankName;
  $.ajaxSetup({  
    async : false  
  });
  $.ajax({
    url:'account/card/verifyCard',
    type:'post',
    dataType:'json',
    data:{cardNo:cardNo},
    error:function(){
      errorMsg = " 请求验证失败";
      $("#cardNoError").addClass("input-wrap-error");
      $("#error-cardNo").attr("class","error-tips red");
      $("#error-cardNo").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
      $("#error-cardNo").show();
      //卡号标志位
      cardNoFlag = false;
      $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-bindCard").attr("disabled","true");
      return false;
    },
    success:function(data){
      if(data == "SYSTEM_EXCEPTION"){
        errorMsg = " 系统或网络异常，请稍候重试";
        $("#cardNoError").addClass("input-wrap-error");
        $("#cardNoError").addClass("input-wrap-error");
        $("#error-cardNo").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-cardNo").show();
        //卡号标志位
        cardNoFlag = false;
        $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-bindCard").attr("disabled","true");
        return false;
      }else if(data == "FAILED"){
        errorMsg = " 验证时出现异常，请检查卡号是否输入正确";
        $("#cardNoError").addClass("input-wrap-error");
        $("#cardNoError").addClass("input-wrap-error");
        $("#error-cardNo").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-cardNo").show();
        //卡号标志位
        cardNoFlag = false;
        $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-bindCard").attr("disabled","true");
        return false;
        //显示银行
      }else if(data == "bankCardOFF"){
        errorMsg = " 暂不支持绑定该行银行卡";
        $("#cardNoError").addClass("input-wrap-error");
        $("#error-cardNo").attr("class","error-tips red");
        $("#error-cardNo").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-cardNo").show();
        //卡号标志位
        cardNoFlag = false;
        $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-bindCard").attr("disabled","true");
        return false;
      }else if(data == "noCardOFF"){
        errorMsg = " 可绑卡，暂不支持快捷支付";
        $("#cardNoError").addClass("input-wrap-error");
        $("#error-cardNo").attr("class","error-tips orange");
        $("#error-cardNo").html("<i class='icon icon-tip'>"+errorMsg+"</i>");
        $("#error-cardNo").show();
        //卡号标志位
        cardNoFlag = true;
        return true;
      }else if(data == "\"noLogin\""){
        location.href = "account/toLogin";
      }else{
        cardNoFlag = true;
        return true;
      //  $("#error-cardNo").fadeOut(300);
      }
    }
  });
  return cardNoFlag;
}
/*
 * 验证银行卡类型
 */
function verCardNo(){
  var cardNo = $("#cardNo").val();
  var cardNoFlag = false;
  var bankName;
  $.ajaxSetup({  
    async : false  
  });
  $.ajax({
    url:'account/card/verifyCardInfo',
    type:'post',
    dataType:'json',
    data:{cardNo:cardNo},
    error:function(){
      errorMsg = " 请求验证失败";
      $("#cardNoError").addClass("input-wrap-error");
      $("#error-cardNo").attr("class","error-tips red");
      $("#error-cardNo").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
      $("#error-cardNo").show();
      //卡号标志位
      cardNoFlag = false;
      $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-bindCard").attr("disabled","true");
      return false;
    },
    success:function(data){
      if(data == "SYSTEM_EXCEPTION"){
        errorMsg = " 系统或网络异常，请稍候重试";
        $("#cardNoError").addClass("input-wrap-error");
        $("#error-cardNo").attr("class","error-tips red");
        $("#error-cardNo").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-cardNo").show();
        //卡号标志位
        cardNoFlag = false;
        $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-bindCard").attr("disabled","true");
        return false;
      }else if(data == "FAILED"){
        errorMsg = " 验证时出现异常，请检查卡号是否输入正确";
        $("#cardNoError").addClass("input-wrap-error");
        $("#error-cardNo").attr("class","error-tips red");
        $("#error-cardNo").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-cardNo").show();
        //卡号标志位
        cardNoFlag = false;
        $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-bindCard").attr("disabled","true");
        return false;
        //显示银行
      }else if(data.status == "CREDIT"){
        errorMsg = " 只支持绑定借记卡，请重新输入";
        $("#cardNoError").addClass("input-wrap-error");
        $("#error-cardNo").attr("class","error-tips red");
        $("#error-cardNo").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
        $("#error-cardNo").show();
        //卡号标志位
        cardNoFlag = false;
        $("#btn-bindCard").removeClass("btn-login").addClass("btn-login-gray");
        $("#btn-bindCard").attr("disabled","true");
        return false;
      }else if(data == "\"noLogin\""){
        location.href = "account/toLogin";
      }else{
        bankName = data.bankName;
       // $("#bankName").html(bankName);
        $("#error-cardNo").attr("class","error-tips orange");
        $("#error-cardNo").html("<i class='icon icon-tip'>"+bankName+"</i>");
        $("#error-cardNo").show();
        cardNoFlag = true;
        $("#cardNoError").removeClass("input-wrap-error");
        return true;
      //  $("#error-cardNo").fadeOut(300);
      }
    }
  });
  return cardNoFlag;
}
/**
 * 密码框获取焦点触发事件
 */
function pwdLook(){
  errorMsg= " 交易密码必须为8-20位数字和字母组合";
  $("#setup-pwd-li").removeClass("input-wrap-error").addClass("input-wrap-tip");
  $("#error-setup-pwd").removeClass("error-tips red").addClass("error-tips orange");
  $("#error-setup-pwd").html("<i class='icon icon-tip'>"+errorMsg+"</i>");
  $("#error-setup-pwd").show();
}

//弹层
function openLayer(errorMsg){
//  $(".errorCon.red.pa").remove();
  $("#failTitle").remove();//去掉“绑卡失败”字样
  $("#errorMsgMask").remove();//去掉错误信息
  $("#failMask").css("top","20%");
  $("#layerBody").append("<p id='failTitle' class='failTitle'>绑卡失败</p>");
  $("#layerBody").append("<p id='errorMsgMask' class='orange'>"+errorMsg+"</p>");
  $("#mask").show();
  $("#failMask").show();
}