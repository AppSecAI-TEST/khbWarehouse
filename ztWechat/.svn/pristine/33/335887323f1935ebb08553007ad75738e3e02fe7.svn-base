$(function(){
	$('#signUp').click(function(){
	var test = document.getElementById("agreeBox").checked;
		if(test){
			location.href="scb/toRegistScb";
		}else{
			return false;
		}
	});
});
$(document).ready(function() {
  $(".icon-checkbox").click(function() {
    //协议是否被选中
    if($("#agreeBox").is(":checked")) {
      $("#signUp").removeClass("btn-login-gray").addClass("btn-login").removeAttr("disabled",false);
    } else {
      $("#signUp").removeClass("btn-login").addClass("btn-login-gray").attr("disabled",true);
    }
});
});
