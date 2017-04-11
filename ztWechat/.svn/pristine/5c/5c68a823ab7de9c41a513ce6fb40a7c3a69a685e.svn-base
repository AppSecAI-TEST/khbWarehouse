var errorCount = 0;
$(function(){
  $("#btn-login").click(function(){
    $('#btn-login').val("登录中...");
    $("#btn-login").attr("disabled",true);
    //校验
    var telObj = $("#tel");
    var telFlag = telRuleCheckCom('onblur');
    var pwdObj = $("#pwd");
    var pwdFlag = pwdLengthCheck('onblur',pwdObj);
    var verifyCodeObj = $("#verifyCode");
    var verifyCodeFlag = true;
    if(errorCount > 0){
      verifyCodeFlag = verifyCodeCheck('onblur',verifyCodeObj);
    }
    if(telFlag && pwdFlag && verifyCodeFlag){
      $("#btn-login").removeClass("btn-login-gray").addClass("btn-login");
      $("#btn-login").attr("disabled",false);
    }else{
      $("#btn-login").removeClass("btn-login").addClass("btn-login-gray");
      $("#btn-login").attr("disabled",true);
      return false;
    }
    $.ajax({
      url:'account/login.action', 
      type:'post',
      data:{loginName:telObj.val(),pwd:pwdObj.val(),errorCount:errorCount,verifyCode:verifyCodeObj.val()},
      error: function(){
        $("#messageBox").html("<i class='icon icon-error2'> 请求失败，请稍候再试</i>");
        $("#messageBox").show();
        $('#btn-login').val("登录并绑定公众账号");
        errorCount++;
        if(errorCount > 0){
          refreshCode();
          $("#verifyCode").val("");
          $("#verifyCodeDiv").attr("style","");
        }
      }, 
      success: function(data){
        if(data.code == 'SUCCESS'){
         /* var interceptUrl = $("#interceptUrl").html();
          * var returnUrl = $("#returnUrl").html();*/
          var interceptUrl = $("#interceptUrl").val();
          var returnUrl = $("#returnUrl").val();
          var projectAction = $("#projectAction").val();
          if(projectAction != '') {
            location.href = projectAction+"?"+"interceptUrl="+interceptUrl+"&memberNo="+data.aesMemberNo;
          } else if(interceptUrl != ''){
            location.href = interceptUrl;
          }else if(returnUrl != ''){
//            location.href = "/lmweChat/"+returnUrl;
            location.href = returnUrl;
          }else if(returnUrl == '' && interceptUrl == '') {
//            location.href = "/lmweChat/popularize/toPopularize";
            location.href = "popularize/toPopularize";
          }
        }else{
          if(data.code == 'VERIFY_FAILED'){
            errorMsg = " 对不起，验证码输入错误";
            $('#verify-code-li').addClass('input-wrap-error');
            $("#error-verify-code").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
            $("#error-verify-code").show();
            $("#btn-login").removeClass("btn-login").addClass("btn-login-gray");
            $("#btn-login").attr("disabled",true);
          }else if(data.code == 'FAILED'){
            errorMsg=" 手机号或密码错误";
            $('#tel-li').addClass('input-wrap-error');
            $("#error-password").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
            $("#error-password").show();
            $("#btn-login").removeClass("btn-login").addClass("btn-login-gray");
            $("#btn-login").attr("disabled",true);
          }else if(data.code == 'SYSTEM_EXCEPTION'){
            errorMsg=" 系统或网络异常，请稍候再试";
            $("#messageBox").html("<i class='icon icon-error2'>请求失败，请稍候再试</i>");
            $("#messageBox").show();
            $("#btn-login").removeClass("btn-login").addClass("btn-login-gray");
            $("#btn-login").attr("disabled",true);
          }else if(data.code == "USER_NOTEXIST"){
            errorMsg=" 该用户不存在";
            $('#tel-li').addClass('input-wrap-error');
            $("#error-tel").html("<i class='icon icon-error2'>"+errorMsg+"</i>");
            $("#error-tel").show();
            $("#btn-login").removeClass("btn-login").addClass("btn-login-gray");
            $("#btn-login").attr("disabled",true);
          }else if(data.code == 'WRONG_PWD'){
            errorMsg = " 密码错误";
            $('#password-li').addClass('input-wrap-error');
            $("#error-password").html("<i class='icon icon-error2'>"+errorMsg+"");
            $("#error-password").show();
            $("#btn-login").removeClass("btn-login").addClass("btn-login-gray");
            $("#btn-login").attr("disabled",true);
          }
          errorCount++;
          $('#btn-login').val("登录并绑定公众账号");
          if(errorCount > 0){
            refreshCode();
            $("#verifyCode").val("");
            $("#verifyCodeDiv").attr("style","");
          }
        }
      } 
    });
  });
});

function checkFormButton(eventName,obj){
  var telFlag = telRuleCheckCom(eventName);
  var pwdObj = $("#pwd");
  var pwdFlag = pwdLengthCheck(eventName,pwdObj);
  var verifyCodeFlag = true;
  if(errorCount > 0){
    verifyCodeFlag = verifyCodeCheck(eventName,obj);
  }
  if(telFlag && pwdFlag && verifyCodeFlag){
    $("#btn-login").removeClass("btn-login-gray").addClass("btn-login");
    $("#btn-login").attr("disabled",false);
  }else{
    $("#btn-login").removeClass("btn-login").addClass("btn-login-gray");
    $("#btn-login").attr("disabled",true);
  }
  
}