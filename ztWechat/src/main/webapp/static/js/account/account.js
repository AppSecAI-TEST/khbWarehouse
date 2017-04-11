$(function(){
//  if ($("#tradePwd").html() != "********") {
//    $("#updateButton").hide();
//    $("#findButton").hide();
//  }
});

function unBind () {
  $.ajax({
    url : 'account/unBindAccount.action',
    type : 'post',
    dataType : 'json',
    data : {},
    error : function () {
      //弹出信息：解绑失败
      layer.open({
        btn : ['OK'],
        content : "<p class=\"color-yellow tc\">请求失败，请稍后重试</p>",
        shadeClose : false,
      });
    },
    success : function (data) {
      if (data == "FAILED") {
        layer.open({
          btn : ['OK'],
          content : "<p class=\"color-yellow tc\">操作失败</p>",
          shadeClose : false,
        });
      } else if (data.status == "SUCCESS") {
        //有openId,是微信用户，微信
        location.href = "popularize/toPopularize";
        /*if(data.openId != null && data.openId != ''){
          wx.closeWindow();
        }else{
          location.href = "popularize/toPopularize";
        }*/
      }

    }
  });
}