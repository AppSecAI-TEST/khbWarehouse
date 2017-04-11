
function commonParamForTable(redeemMoney,totalEnableMoney,type){
  //动态改变每只基金的赎回份额
  var leastRedeemList=$("#leastRedeemList").find('input');//最低赎回份额集合
  var netValueList=$("#netValueList").find('input');//组合净值集合
//  var totalFundDetailList=$("#totalFundDetailList").find('input');//组合基金对应总基金详情集合
  var trs = $('.redemptionTable.redemptionTable-page').find('tr');
  var needMoney=0;var fundStr='';
  for(var i=1;i<trs.length;i++){
    //每个基金可赎回总份额
    var totalRedeem =$('.redemptionTable.redemptionTable-page').find('tr').eq(i).find('td').eq(1).html();
//    var totalFundRedeem=totalFundDetailList.eq(i-1).val();//用户此基金共有份额 组合+单只
    //赎回份额=每只基金的持有份额*赎回金额/总市值
    var redeem=parseFloat(totalRedeem)*parseFloat(redeemMoney)/parseFloat(totalEnableMoney);//每个基金赎回份额
//    var redeem=Math.floor((parseFloat(totalRedeem)*parseFloat(redeemMoney)/parseFloat(totalEnableMoney))*100)/100;//每个基金赎回份额
    var resultShares=calcShares(parseFloat(totalRedeem), redeem, parseFloat(leastRedeemList.eq(i-1).val()));
    if(type=='redeem'){
      $('.redemptionTable.redemptionTable-page').find('tr').eq(i).find('td').eq(2).html(formatNumFloor(resultShares));
    }else{
      $('.redemptionTable.redemptionTable-page').find('tr').eq(i).find('td').eq(1).html(formatNumFloor(resultShares));
      $('.redemptionTable.redemptionTable-page').find('tr').eq(i).find('td').eq(2).html((resultShares*parseFloat(netValueList.eq(i-1).val())).toFixed(2));
    }
    needMoney+=resultShares*parseFloat(netValueList.eq(i-1).val());
    //判断是否份额有改变
    if(resultShares!=redeem){
      fundStr+= $('.redemptionTable.redemptionTable-page').find('tr').eq(i).find('td').eq(0).html()+',';
    }
}
  //显示提示,赎回页面
  var redeemVal=parseFloat($("#redeemMoney").val());
  var needval=parseFloat(needMoney.toFixed(2));
  if(redeemVal!=needval){//判断输入金额和计算金额精确度之后是否一致
    if(type=='redeem'){
      $("#chanceInfo").html('努力计算后，由于'+fundStr.substring(0, fundStr.length-1)+'基金不满足赎回限制，需增加其赎回份额，并增加总赎回金额至 <span class="orange">'+needMoney.toFixed(2)+'</span>元');
      $("#mask").show();
      $("#alertLayer").show();
      $("#redeemMoney").val(needMoney.toFixed(2));
    //错误提现信息
//      $("#errorInfo").html('<i class="icon icon-error2"></i> 由于'+fundStr.substring(0, fundStr.length-1)+'基金小于起赎份额，需增加总赎回金额至'+needMoney.toFixed(2)+'元');
//      toGray();
  }
  }else{
    $(".identifier-page").hide();
  }
  //@_formater.formatNumber((items.ztPolicyFundShareDto.enableShare*redeemMoney/totalEnableMoney)*items.ztPolicyFundShareDto.netValue)
}
/**
 * 公用赎回份额算法
 * @param totalRedeem
 * @param redeem
 * @param leastRedeem
 * @returns
 */
function calcShares(totalRedeem,redeem,leastRedeem){
  var resultShares=0;
     resultShares=redeem;//最终赎回份额
    //当用户赎回份额大于或等于最低赎回份额时，比较剩余份额与最低赎回份额
    if(redeem >= leastRedeem){
      if(totalRedeem-redeem<leastRedeem){// 若剩余份额小于最低赎回份额时,全部赎回
        resultShares=totalRedeem;
      }
    }else{//当用户赎回份额小于最低赎回份额时，比较可赎份额与最低赎回份额
      if(totalRedeem >= leastRedeem){// 若可赎份额大于或等于最低赎回份额时，比较可赎回份额与最低赎回份额；默认赎回份额为最低赎回在进行判断
        if(totalRedeem<2*leastRedeem){// 若可用份额减去最低赎回小于最低赎回
          resultShares=totalRedeem;
        }else{
          resultShares=leastRedeem;
        }
      }else{// 若可赎份额小于最低赎回份额时，全部赎回
        resultShares=totalRedeem;
      }
  }
//  return parseFloat(resultShares.toFixed(2));
    return resultShares;
}

function formatNumFloor(number){
  return Math.floor(number*100)/100;
}