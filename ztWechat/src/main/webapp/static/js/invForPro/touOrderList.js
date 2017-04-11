$(function(){
  //初始化页面 根据type 初始化未完成或已完成列表
  var type = $("#type").val();
  if(type=="finish"){
    $('#noFinish').removeClass('on');
    $("#finish").addClass('on');
    queryOrderList();
  }else{
    queryOrderList();  
  }
  $('#noFinish').click(function(){
    $('#finish').removeClass('on');
   $("#noFinish").addClass('on');
   queryOrderList();
  });
  $('#finish').click(function(){
    $('#noFinish').removeClass('on');
   $("#finish").addClass('on');
   queryOrderList();
  });
});
function queryOrderList(){
  var type = $('.on').attr('title');
  $
      .ajax({
        url : 'invForPro/myOrderList',
        type : 'post',
        dataType : 'json',
        data : {
          status : type,
        },
        error : function () {
          $("#mask").show();
          $("#alertLayer-8").show();
        },
        success : function (data) {
          if (data == 'SYSTEM_EXCEPTION') {
            $("#mask").show();
            $("#alertLayer-8").show();
          } else {
            var str="";
         
            //成功处理
            data.forEach(function(items){
              var commonStr="";var commonHref="";
              if(items.activityInvForProOrderDTO.status=="PAIDED"||items.activityInvForProOrderDTO.status=="SUCCESS"){
                commonHref="<a class=\"orderImg fl\"  href=\"javaScript:void(0)\"><img src=\"invForPro/lookProductImg?id="+items.activityInvForProInfoDTO.id+"\" alt=\"\"/></a>";
                commonStr="已完成";
              }else if(items.activityInvForProOrderDTO.status=="NO_PAID"){
                commonHref="<a class=\"orderImg fl\" href=\"invForPro/toFreeProcuctOrderDetail?orderNum="+items.activityInvForProOrderDTO.orderCode+"\"><img src=\"invForPro/lookProductImg?id="+items.activityInvForProInfoDTO.id+"\" alt=\"\"/></a>";
                commonStr="<a href=\"invForPro/toFreeProcuctOrderDetail?orderNum="+items.activityInvForProOrderDTO.orderCode+"\"> 去支付 <i class=\"icon icon-arrow-right\"></i><i class=\"icon icon-arrow-right\"></i>";
              }else if(items.activityInvForProOrderDTO.status=="CANCE"){
                commonHref="<a class=\"orderImg fl\"  href=\"javaScript:void(0)\"><img src=\"invForPro/lookProductImg?id="+items.activityInvForProInfoDTO.id+"\" alt=\"\"/></a>";
                commonStr="已过期";
              }
              str=str + " <div class=\"orderList voucherList voucherRed pr\"> "
              + " <div class=\"orderTop\">"
              + commonHref
              + "  <div class=\"orderText fl\">"
              + "    <h3><a href=\"javascript:void(0);\">"+items.activityInvForProInfoDTO.name+"</a></h3>"
              + "       <p> 出行人数X"+items.activityInvForProOrderDTO.num+"</p>"
              + "       <p>购买金额：￥"+items.activityInvForProOrderDTO.total+"</p>"
              + "   </div>"
              + "   <div class=\"clearfix\"></div>"
              + "</div>"
              + "<div class=\"orderBottom pr\">"
              + "   <img src=\"static/images/tour/orderText-bg.jpg\" alt=\"\"/>"
              + "   <div class=\"orderDetails pa\">"
              + "       <div class=\"orderNum fl\">订单编号："+items.activityInvForProOrderDTO.orderCode+"</div>"
              + "       <div class=\"orderFinish fr\">"+
             commonStr
              +" </a></div>"
              + "   </div>"
              + "</div>"
              + "</div>";
            });
            $("#orderList").html(str);
          }
        }
      });
}