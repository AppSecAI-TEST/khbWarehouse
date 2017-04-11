function changeType(){
  var classType=$("#look").attr('class');
  if(classType!='icon icon-unlook lookIcon font-25 pa'){
    $("#look").attr('class','icon icon-unlook lookIcon font-25 pa');
    $("#totalWealthSpan").html('****');
    $("#yesterdayIncomeSpan").html('****');
    $("#totalIncomeSpan").html('****');
    $("#accountBalanceResultSpan").html('****');
  }else{
    $("#look").attr('class','icon icon-look lookIcon font-25 pa');
    $("#totalWealthSpan").html($("#totalWealth").val());
    $("#yesterdayIncomeSpan").html($("#yesterdayIncome").val());
    $("#totalIncomeSpan").html($("#totalIncome").val());
    $("#accountBalanceResultSpan").html($("#accountBalanceResult").val());
  }
}

$(function() {
	//显示/隐藏弹出层
	$(".mylm-activity-btn").click(function(){
		showDialogBottom();
		$("#alertLayer").show();
//		$("body").css("position","fixed");
	});
	$(".mylm-msg").click(function(){
		showDialogBottom();
		$("#alertLayer-6").show();
//		$("body").css("position","fixed");
	});
	//推荐人点击
	$(".referees").click(function(){
		$.ajax({
			url : 'asset/getConsultantUserUrl',
			type : 'post',
			dataType : 'json',
			data : {
			},
			error : function() {
				//
			},
			success : function(data) {
				location.href = data.returnUrl;
			}
        });
	});
	var showDialogBottom = function(){
		$("#mask").css("height",$(document).height());
		$("#mask").css("width",$(document).width());
		$("#mask").show();
	};
});