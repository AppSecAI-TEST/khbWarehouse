 $(function(){
      
      $('#reg').click(function(){
    	  	$('#reg').attr("disabled", true);
			var verifyCodeFlag = identifyCodeCheck('blur','identifyCode'); //验证短信验证码规则
			var telCheckFlag = telRuleCheck('blur','tel',false); //验证手机号规则
			var errorMsg = "";
			
			if (!verifyCodeFlag || !telCheckFlag) {
				$('#reg').attr("disabled", false);
				return false;
			}
		    var verifyCode = $("#identifyCode").val();
	        var newMobileNo = $("#tel").val();

	        $.post("account/modifyMobileNo",{verifyCode:verifyCode,newMobileNo:newMobileNo},function(data){
	          if(data == 'SUCCESS'){
	            openLayer();
	          } else if(data == 'noLogin'){
	            location.href=window.location.href;  
	          }else if(data == 'DYNAMIC_FAILED'){
	              errorMsg = " 对不起，短信验证码不正确";
//	              $("#identifyCode").val("");
	        } else if(data == 'FAILED'){
	            errorMsg = " 对不起，新手机号修改失败";
	        } 
	          if(data != 'SUCCESS'){
	        	  if(!verifyCodeFlag) {
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
 function checkFormButton(eventCode,inputNodeId,telCheckFlag) {
 	var verifyCodeFlag = identifyCodeCheck(eventCode,inputNodeId); //验证短信验证码规则
 	if(verifyCodeFlag) {
 		telCheckFlag = true;
 	}
 	var telRuleCheckFlag = telRuleCheck(eventCode,inputNodeId,telCheckFlag); //验证手机号规则
 	if(verifyCodeFlag && telRuleCheckFlag) {
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
 