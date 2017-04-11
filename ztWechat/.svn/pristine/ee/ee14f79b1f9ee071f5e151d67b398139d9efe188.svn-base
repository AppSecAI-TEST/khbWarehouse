$(document).ready(function() {
  //初始化年化率
  $("#productRate").html(Math.floor($("#productRate").html()*10)/10);
  var singlePrice = $("#singlePrice").val();
  var ruleId = $("#ruleId").val();
  
  $(".js_numChange").click(function() {
    //剩余数量
    var leftAmout = $("#leftAmout").val();
    //想要购买的数量
    var buyNum = $("#buyNum").html();
    if((buyNum-leftAmout) > 0) {
      $("#buyNum").html(leftAmout);
    }
    buyNum = $("#buyNum").html();
    //判断打折剩余数
    var surplusNum = $("#surplusNum").val();
    if(surplusNum!=null&&surplusNum!=''&&buyNum>1){
      if(parseInt(buyNum/2)>surplusNum){//超出限额
        buyNum=buyNum-surplusNum*2+surplusNum*1.5;
      }else{
        buyNum=parseInt(buyNum/2)*1.5+buyNum%2;
      }
    }
    
    $("#buyPrice").text((singlePrice*buyNum).toFixed(2));
  });
  
  //期限和展示的价格
  $(".trem").click(function() {
    var buyNum = $("#buyNum").html();
    //判断打折剩余数
    var surplusNum = $("#surplusNum").val();
    if(surplusNum!=null&&surplusNum!=''&&buyNum>1){
      if(parseInt(buyNum/2)>surplusNum){//超出限额
        buyNum=buyNum-surplusNum*2+surplusNum*1.5;
      }else{
        buyNum=parseInt(buyNum/2)*1.5+buyNum%2;
      }
    }
    var $this = $(this);
    var value =$this.data("value");
    //设置产品的单价
    singlePrice = value;
   //收益率
    var rate = Math.floor($this.data("rate")*10)/10;
    //规则编号
    ruleId = $($this).data("id");
    //需要购买的总价
    $("#buyPrice").text((value*buyNum).toFixed(1));
    $("#productRate").text(rate);
  });
  
  $(".btnBuy").click(function() {
    if($("#login").length > 0) {
      $.ajax({
        url:"invForPro/toCreateOrder",
        data:{
          productId:$("#productId").val(),
          number:$("#buyNum").html(),
          ruleId:ruleId
        },
        success:function(data) {
          if(data.code == "SUCCESS"){
            location.href="invForPro/toFreeProcuctOrderDetail?orderNum="+data.orderNum;
          } else {
            $("#mask").show();
            $("#alertLayer-8").show();
            $(".errorCon").html(data.message);
          }
        }
      })
    } else{
      var u = navigator.userAgent;
      var isApp = /lanmao/i.test(u);
      if(isApp) {
    	  var unLoginReturnUrlParam=$("#unLoginReturnUrlParam").val();
          location.href ="account/toLogin?returnUrl=invForPro/toFreeTraveInfo?"+unLoginReturnUrlParam;
          return;
      }
      $("#mask").show();
      $("#alertLayer-5").show();
      
    }
    
  });
  
  
});

  
  



