$(document).ready(function() {
  $("#inviteFriend").click(function() {
    var $commonUser = $("#commonUser");
    //不存在commonUser，则用户没有登陆，弹出登录注册中间页
    if($commonUser.length == 0) {
      $("body").css("position","fixed");
      $("#mask").show();
      $(".js_register").show();
    } else {
      //用户登录判断用户类型 true普通用户    false不是普通用户
      /*if($commonUser.val() == "true") {*/
        //可以分享的弹层
        $("#mask").show();
        $(".shareTips").show();
      /*} else {
        //不可以分享的弹层
        $("#inviteTips").text("对不起，已经成为懒猫金服推荐人、理财顾问或销售的用户不在此活动的参与范围内。");
        $("#alertLayer-3").show();
      }*/
    }
    
  });
  //点击立即去抽奖
  $("#raffle").click(function(){
	  var status = $("#status").val();
      var u = navigator.userAgent;
      var isApp = /lanmao/i.test(u);
      if(isApp&""==status) {
          location.href ="account/toLogin?returnUrl=activity/toRaffleActivity";
          return;
      }
      
      location.href="activity/toRaffleActivity";
  });
});
