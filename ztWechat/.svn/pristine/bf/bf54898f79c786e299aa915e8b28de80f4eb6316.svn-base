$(document).ready(function() {

  var rotateTimeOut = function (){
      $('#rotate').rotate({
          angle:0,
          animateTo:2160,
          duration:8000,
          callback:function (){
              alert('网络超时，请检查您的网络设置！');
          }
      });
  };
//  var bRotate = false;

  function rotateFn(/*awards, angles, txt,prizeLevel,grantWay,data*/){
      bRotate = !bRotate;
      $('#rotate').stopRotate();
      $('#rotate').rotate({
          angle:0,
          animateTo:/*angles+*/1800,
          duration:6000,
          callback:function (){/*
            if(data) {
              //分数百分比
              $("#score").attr("style","width:"+data.luckyScore/0.88+"%");
              $("#showScore").text(data.luckyScore);
              $("#usedCount").text(data.usedTicketCount);//已使用次数
              $("#canUse").text(data.canuseTicketCount);//未使用次数
            }
            if(prizeLevel == 7) {
              $("#prizeInfo").text("真遗憾你没有中奖");
              $("#alertLayer-3").show();
            } else {
              if(grantWay == 'AUTOMATIC') {
                //自动发放
                $("#prizeInfo").text("恭喜您获得"+txt+"，您可以在我的懒猫-我的卡券中进行查看及使用。");
                $("#alertLayer-3").show();
              }else {
                //物流发放
                $("#prizeInfo").text("恭喜您获得"+txt+"，我们的工作人员会在两个工作日内与您联系确认奖品发放事宜，请保持手机畅通。");
                $("#alertLayer-3").show();
              }
            }
            bRotate = !bRotate;
          */}
      })
  };

  $('.pointer').click(function (){

      if(bRotate)return;
      if($("#canUse").text()==0) {
      //没有抽奖机会
        var isFlag = $("#isFlag").val();
        if(isFlag == "true") {//绑卡送抽奖券
          $("#prizeInfo").text("抽奖机会已用完，您可以投资或者邀请好友注册、投资，抽取超级大奖。");
        } else {
          $("#prizeInfo").text("抽奖机会已用完，您可以再次邀请好友注册或投资，抽取超级大奖。");
        }
        $("#alertLayer-3").show();
        return;
      }
      rotateFn(/*awards, angles, txt,prizeLevel,grantWay,data*/)
      /*$.ajax({
        url:"activity/toRafflePrize",
        data:{"actionCode":$("#actionCode").val(),"activityCode":$("#activityCode").val()},
        success:function(data) {
          var prizeDTO = data.prizeDTO;
          if(prizeDTO.code == 0) {
            switch (prizeDTO.prizeLevel) {
              case 0:
                //var angle = [22, 67, 112, 157, 202, 247, 292，337];
                rotateFn(0, 22, prizeDTO.prizeName,prizeDTO.prizeLevel,prizeDTO.grantWay,data);
                break;
              case 1:
                //var angle = [88, 137, 185, 235, 287];
                rotateFn(1, 337, prizeDTO.prizeName,prizeDTO.prizeLevel,prizeDTO.grantWay,data);
                break;
              case 2:
                //var angle = [137, 185, 235, 287];
                rotateFn(2, 67, prizeDTO.prizeName,prizeDTO.prizeLevel,prizeDTO.grantWay,data);
                break;
              case 3:
                //var angle = [137, 185, 235, 287];
                rotateFn(3, 292, prizeDTO.prizeName,prizeDTO.prizeLevel,prizeDTO.grantWay,data);
                break;
              case 4:
                //var angle = [185, 235, 287];
                rotateFn(4, 247, prizeDTO.prizeName,prizeDTO.prizeLevel,prizeDTO.grantWay,data);
                break;
              case 5:
                //var angle = [185, 235, 287];
                rotateFn(5, 112, prizeDTO.prizeName,prizeDTO.prizeLevel,prizeDTO.grantWay,data);
                break;
              case 6:
                //var angle = [235, 287];
                rotateFn(6, 157, prizeDTO.prizeName,prizeDTO.prizeLevel,prizeDTO.grantWay,data);
                break;
              case 7:
                //var angle = [287];
                rotateFn(7, 202, prizeDTO.prizeName,prizeDTO.prizeLevel,prizeDTO.grantWay,data);
                break;
            }
          } else if(prizeDTO.code == 1002){
            //没有抽奖机会
            $("#prizeInfo").text("抽奖机会已用完，您可以再次邀请好友注册或投资，抽取超级大奖。");
            $("#alertLayer-3").show();
          } else {
            $("#prizeInfo").text(prizeDTO.message);
            $("#alertLayer-3").show();
          }
        }
      });*/
     
  });
  function rnd(n, m){
    return Math.floor(Math.random()*(m-n+1)+n)
}
  
  //邀请好友
  $("#inviteFriend").click(function() {
    //可以分享的弹层
    /*$("#prizeInfo").text("点击屏幕右上角发送此页面给好友，超级大奖，触手可得！");*/
    
    $("#mask").show();
    $("#alertLayer").show();
  });
});
