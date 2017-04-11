$(document).ready(function() {
 
  if($("#flag").val() == "0") {
    yesrs ()
    money()
  }
  
  $("#years,#monthMoney").focus(function() {
    $(".input-wrap-error").removeClass("input-wrap-error");
    $(this).parents(".input-wrap").addClass("input-wrap-error");
  });
  
  var errorMsg,moneyFlag,ageFlag;
  $("#years").keyup(function() {
    yesrs ()
  });
  $("#years").blur(function() {
    yesrs ()
  });
  
  function yesrs() {
    var ageFil=/^[0-9]*[1-9][0-9]*$/;
    var ageFilter=ageFil.test($("#years").val());
    if(ageFilter == false) {
      errorMsg=" 年龄应为整数"; 
      var yearsString =  $("#years").val();
      $("#years").val(yearsString.substring(0,yearsString.length-1));
      $("#yearsError").html('<i class="icon icon-error2"></i>'+errorMsg);
      $("#submitBtn").addClass("submitBtn-gray");
      ageFlag = false;
    } else if($("#years").val() > 200){
      errorMsg=" 预计年数不能大于200年"; 
      $("#yearsError").html('<i class="icon icon-error2"></i>'+errorMsg);
      $("#submitBtn").addClass("submitBtn-gray");
      ageFlag = false;
    }else if($("#years").val() < 1){
      errorMsg=" 预计年数不能小于1年"; 
      $("#yearsError").html('<i class="icon icon-error2"></i>'+errorMsg);
      $("#submitBtn").addClass("submitBtn-gray");
      ageFlag = false;
    } else {
      $("#yearsError").html('');
      ageFlag = true;
    }
    if(ageFlag && moneyFlag) {
      $("#flag").val("0");
      $("#submitBtn").removeClass("submitBtn-gray");
    }
  }
  
  
  $("#monthMoney").keyup(function() {
    money()
  });
  $("#monthMoney").blur(function() {
    money()
  });
  function money() {
    var moneyString =  $("#monthMoney").val();
    if(moneyString.indexOf(".")>0 && moneyString.length > moneyString.indexOf(".")+3) {
      $("#monthMoney").val(moneyString.substring(0,moneyString.length-1));
    } 
    
    var filterMoney=/^[0-9]+\.{0,1}[0-9]{0,2}$/;
    var flagFilter=filterMoney.test($("#monthMoney").val());
    if(false==flagFilter){ //输入金额是否满足数字并小数点不超过后俩位
      errorMsg=" 购买金额必须为数字，小数点不能超过2位"; 
      $("#moneyError").html('<i class="icon icon-error2"></i>'+errorMsg);
      $("#submitBtn").addClass("submitBtn-gray");
      moneyFlag = false;
    }else if ($("#monthMoney").val() == null || $("#monthMoney").val() == '') {
      errorMsg = " 请输入购买金额";
      $("#moneyError").html('<i class="icon icon-error2"></i>'+errorMsg);
      $("#submitBtn").addClass("submitBtn-gray");
      moneyFlag = false;
    }else if($("#monthMoney").val() < 500){
      errorMsg = " 每月可投入金额需大于500元";
      $("#moneyError").html('<i class="icon icon-error2"></i>'+errorMsg);
      $("#submitBtn").addClass("submitBtn-gray");
      moneyFlag = false;
    } else {
      $("#moneyError").html('');
      moneyFlag = true;
    }
    if(ageFlag && moneyFlag) {
      $("#flag").val("0");
      $("#submitBtn").removeClass("submitBtn-gray");
    }
  }
  
 $("#submitBtn").click(function() {
   if(ageFlag && moneyFlag) {
     $("#form").submit();
   }
  });
});