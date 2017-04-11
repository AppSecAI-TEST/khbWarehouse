$(function(){
  //初始化数据
  var totalEnableMoney=$("#totalEnableMoney").val();
  var redeemMoney=$("#buyMoney").val();
  commonParamForTable(redeemMoney,totalEnableMoney,'confirm');
  
//取消图标控制
  $('.icon.icon-error.fr').click(function(){
    $(this).hide();
    //错误信息不显示
    $("#errorInfo").hide();
    //红框样式去掉
    $(this).parent().parent().removeClass("input-wrap-error");
    //清除内容
    $(this).siblings('.input-text').val('');
    $(this).siblings('.input-text').focus();
  });
  
  //调用赎回接口
  $('.btnBuy').click(function(){
    changeSumbitButtonGray();//置灰
    var policyOrderId=$("#policyOrderId").val();
    var redeemMoney=$("#buyMoney").val();
    var tradePwd=$("#tradePwd").val();
    //校验
    if (tradePwd == '' || tradePwd.length < 6 || tradePwd.length > 20){
      $("#errorInfo").html('<i class="icon icon-error2"></i> 请输入6-20位的交易密码');
      $("#errorInfo").show();
      $(".icon.icon-error.fr").parent().parent().addClass("input-wrap-error");
      //置灰
      $('#sumbit').attr("disabled", true);
      $('#sumbit').attr('class','btnBuy btnBuy-gray' );
      $('#sumbit').val("确认赎回");
      return ;
    }
    $.ajax({
      url:'zt/redeem/redeem',
      type:'post',
      dataType:'json',
      data:{policyOrderId:policyOrderId,redeemMoney:redeemMoney,tradePwd:tradePwd},
      success:function(data){
        if(data.status == "SYSTEM_EXCEPTION"){
          errorMsg = " 系统或网络异常，请稍后再试";
          //蒙层
          $("#mask").show();
          $("#alertLayer-8").show();
          changeSumbitButtonLight();//置亮
        }else if(data.status == "SUCCESS"){
          location.href="zt/redeem/toRedeemSuccess?policyOrderId="+policyOrderId+"&policyOrderDetailId="+data.orderDetailId+"&redeemMoney="+redeemMoney;
        }else if(data.status == "WRONG_PWD"){
          //交易密码错误弹层不改
          if(data.description.overPlusCount==0){
            $("#alertLayer-6Message").html("<i class=\"icon icon-tips\"></i> 账号已冻结,解冻日期 "+ data.description.lockedEndTime);
            $("#mask").show();
            $("#alertLayer-6").show();
            changeSumbitButtonLight();//置亮
          }else{
            $("#alertLayer-5Message").html("<i class=\"icon icon-tips\"></i> 交易密码输入错误，您还可再输入"+data.description.overPlusCount+"次");
            $("#mask").show();
            $("#alertLayer").show();
            changeSumbitButtonLight();//置亮
          }
        }
      }
  });

});
});
/**
 * 确认赎回按钮可用
 */
function changeSumbitButtonLight(){
  $('#sumbit').removeClass('btnBuy-gray');
  $('#sumbit').attr("disabled", false);
  $('#sumbit').val("确认赎回");
}
/**
 * 确认赎回按钮置置不可用
 */
function changeSumbitButtonGray(){
  $('#sumbit').attr("disabled", true);
  $('#sumbit').addClass('btnBuy-gray');
  $('#sumbit').val("处理中...");
}
/**
*显示 隐藏密码
*/
function changePasswordType(){
  var classType=$("#look").attr('class');
  if(classType!='icon icon-unlook fl'){
    document.getElementById('tradePwd').type="password";
    $("#look").attr('class','icon icon-unlook fl');
  }else{
    document.getElementById('tradePwd').type="text";
    $("#look").attr('class','icon icon-look orange fl');
  }
}



$(".input-text").live('keyup',function(){
  $(this).siblings('.icon.icon-error.fr').show();
});
$(".input-text").live('focus',function(){
  if($(this).val()){
    $(this).siblings('.icon.icon-error.fr').show();
  }
});
$(".input-text").live('blur',function(){
  $(this).siblings('.icon.icon-error.fr').fadeOut(500);
});

/**
*除去蒙层
*/
function clean(){
  $("#mask").hide();
  $("#alertLayer").hide();
  $("#alertLayer-6").hide();
  $("#alertLayer-8").hide();
//  $("#tradePwd").val('');
//  $("#tradePwd").focus(); 
}

/**
*交易密码框失去焦点
*/
function onTradePwdBlur(){
  var tradePwd=$("#tradePwd").val(); 
  if (tradePwd == '' || tradePwd.length < 6 || tradePwd.length > 20){
    $("#errorInfo").html('<i class="icon icon-error2"></i> 请输入6-20位的交易密码');
    $("#errorInfo").show();
    $(".icon.icon-error.fr").parent().parent().addClass("input-wrap-error");
    //置灰
    $('#sumbit').attr("disabled", true);
    $('#sumbit').attr('class','btnBuy btnBuy-gray' );
  }else{
    changeSumbitButtonLight();
    $("#errorInfo").hide();
    $(".icon.icon-error.fr").parent().parent().removeClass("input-wrap-error");
  //置亮
    $('#sumbit').attr("disabled", false);
    $('#sumbit').attr('class','btnBuy' );
  }
}

