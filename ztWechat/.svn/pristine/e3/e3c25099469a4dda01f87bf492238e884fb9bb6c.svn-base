$(document).ready(function() {
  $("#toBuy").click(function() {
    var goods = "tour";
    var productId = $("#lcProductId").val();
    var orderNum = $("#orderNum").val();
    if ($("#rechargeAmount").length > 0) {// 去充值页面
      var rechargeAmount = $("#rechargeAmount").val();
      var isBankCard = $("#isBankCard").val();
      if(isBankCard=='NO'){
        location.href = "account/card/toBindCard?returnUrl=/asset/toRecharge?amount=" + rechargeAmount
        + "&ret=fixed&productId=" + productId + "&expectType=" + goods+ "&orderNum=" + orderNum+ "&expectIncome=0";
      }else{
        location.href = "asset/toRecharge?amount=" + rechargeAmount
        + "&ret=fixed&productId=" + productId + "&expectType=" + goods+ "&orderNum=" + orderNum+ "&expectIncome=0";
      }
      
     } else {// 去确认购买页面
       var buyAmount = $("#total").val();
      location.href = "fixed/toBuyProductInfo?productId=" + productId
      + "&expectType=" + goods + "&totalPays=" + buyAmount + "&orderNum=" + orderNum + "&expectIncomeNoVoucher=0";
      }
  });
});

  
  



