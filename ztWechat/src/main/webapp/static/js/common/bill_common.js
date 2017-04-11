function queryCommon (data,str) {
  data.trustOrderResult.forEach(function(itmes){
    var strType='';var strAmount='';var giftAmount=0;var strRate='';
   var promoList= itmes.promotionResult;
   if(promoList!=null&&promoList.length!=0){
    var type= promoList[0].promoType;
    if(type=='SURPASSED_ADD_PRINCIPAL'){//满加类型券
      if(promoList[0].promoPaymentType=='PRINCIPAL_ABLE'){//满加券、投资券
        //判断是否是红包
        if(promoList[0].isRedPacket==1){
          strType=" <div class=\"vouchers2 pa\"><i class=\"icon icon-vouchers2\"></i><span class=\"font-white tc pa\">已用红包<br/>赠送"+format_money(parseFloat(promoList[0].promoPrincipal)+parseFloat(promoList[0].promoIncomeAmount))+" 元</span></div>";
          strAmount=" <br> <a class=\"btn-small\">赠"+format_money(promoList[0].promoPrincipal)+"元</a>";
        }else{
          strType=" <div class=\"vouchers2 pa\"><i class=\"icon icon-vouchers2\"></i><span class=\"font-white tc pa\">已用投资券<br/>赠送"+format_money(parseFloat(promoList[0].promoPrincipal)+parseFloat(promoList[0].promoIncomeAmount))+" 元</span></div>";
          strAmount=" <br> <a class=\"btn-small\">赠"+format_money(promoList[0].promoPrincipal)+"元</a>";
        }
        giftAmount=promoList[0].promoPrincipal;
      }else if(promoList[0].promoPaymentType=='PRINCIPAL_ENABLE'){//理财券
        strType=" <div class=\"vouchers2 pa\"><i class=\"icon icon-vouchers2\"></i><span class=\"font-white tc pa\">已用理财券<br/>赠送"+format_money(promoList[0].promoIncomeAmount)+" 元</span></div>";
        strAmount=" <br> <a  class=\"btn-small\">赠理财金:"+format_money(promoList[0].promoPrincipal)+"元</a>";
        giftAmount=promoList[0].promoPrincipal;
      }
      
    }else if(type=='SURPASSED_ADD_RATE'){//加息券
      strType=" <div class=\"vouchers2 pa\"><i class=\"icon icon-vouchers2\"></i><span class=\"font-white tc pa\">已用加息券<br/>赠送"+format_money(promoList[0].promoIncomeAmount)+" 元</span></div>";
      strRate="<span class=\"font-18\">+"+format_money(promoList[0].addIncome)+"</span>%";
    }
   }
   switch (itmes.businessStatus)
   {
   case 'NOT_EARNINGS':
     str=str+"<div class=\"proList bg-white pr\"><span class=\"no-interest pa\"></span>"+strType+"<h2 class=\"titlePro br-bottom pr\">" +
     itmes.productName+" <a  href=\"fixed/toFinishedContact?periodNo="+itmes.periodNo+"&orderNo="+itmes.orderNo+"\" class=\"proPact orange font-10 radius1\">产品合同</a>  <span class=\"stage f14\">第" +
     itmes.periodNo+ "期</span></h2><div class=\"proCon br-bottom\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">" +
               "<tbody><tr><td class=\"orange\"><span class=\"font-18\">";
     if(itmes.incomeReleaseType=='GOODS'){//收益发放类型为商品
      str=str+
      format_money(parseFloat(itmes.payableAmount)) +"</span> 元</td><td class=\"orange\"><span class=\"font-18\">" +
                   format_money(itmes.termDay)+"</span>天</td><td class=\"orange font-16\">" 
                     +format_date(itmes.arrivalDate)+"</td> </tr><tr> <td>本金</td><td>持有期限</td><td>到账日</td></tr></tbody></table> </div> <p><a href=\"invForPro/toOrderList?type=finish\" style=\"color:#666\" class=\"fr\">查看订单<i class=\"icon icon-arrow-right\">" +
                     "</i><i class=\"icon icon-arrow-right\"></i></a>" +
                       "购买金额 <i class=\"orange\">" +
                       format_money(parseFloat(itmes.payableAmount))+"元</i>"+strAmount+"</p></div> ";
     }else{//收益发放类型为利息
       str=str +
              format_money(parseFloat(itmes.accumulationIncome)+parseFloat(itmes.payableAmount)) +"</span> 元</td><td class=\"orange\"><span class=\"font-18\">" +
                   format_money(itmes.termDay)+"</span>天</td><td class=\"orange font-16\">" 
                     +format_date(itmes.incomeDate)+"</td> </tr><tr> <td>累计未到账本息</td><td>持有期限</td><td>起息日</td></tr></tbody></table> </div> <p><span class=\"fr\">年化收益率 <i class=\"orange\">" +
                     format_yearRate(itmes.yearRate)+"%"+strRate+"</i></span>" +
                       "购买金额 <i class=\"orange\">" +
                       format_money(parseFloat(itmes.payableAmount))+"元</i>"+strAmount+"</p></div> ";
     }
    
     break;
   case 'EARNINGS':
     str=str+"<div class=\"proList bg-white pr\"><span class=\"investing pa\"></span>"+strType+"<h2 class=\"titlePro br-bottom pr\">" +
     itmes.productName+" <a  href=\"fixed/toFinishedContact?periodNo="+itmes.periodNo+"&orderNo="+itmes.orderNo+"\" class=\"proPact orange font-10 radius1\">产品合同</a>  <span class=\"stage f14\">第" +
     itmes.periodNo+ "期</span></h2><div class=\"proCon br-bottom\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">" +
             "<tbody><tr><td class=\"orange\"><span class=\"font-18\">";
     if(itmes.incomeReleaseType=='GOODS'){//收益发放类型为商品
       str =str +
       format_money(parseFloat(itmes.payableAmount))+"</span> 元</td><td class=\"orange font-16\">" +
            format_date(itmes.arrivalDate) +"</td> </tr><tr> <td>本金</td><td>到账日</td></tr></tbody></table> </div> <p><a href=\"invForPro/toOrderList?type=finish\" style=\"color:#666\" class=\"fr\">查看订单<i class=\"icon icon-arrow-right\">" +
                     "</i><i class=\"icon icon-arrow-right\"></i></a>" +
                       "购买金额 <i class=\"orange\">" +
                       format_money(parseFloat(itmes.payableAmount))+"元</i>"+strAmount+"</p></div> ";
     }else{
       str =str +
       format_money(parseFloat(itmes.accumulationIncome)+parseFloat(itmes.payableAmount))+"</span> 元</td><td class=\"orange\"><span class=\"font-18\">" +
       format_money(itmes.yesterdayIncome)+"</span> 元</td><td class=\"orange font-16\">" +
            format_date(itmes.expireDate) +"</td> </tr><tr> <td>累计未到账本息</td><td>昨日收益</td><td>到期日</td></tr></tbody></table> </div> <p><span class=\"fr\">年化收益率 <i class=\"orange\">" +
            format_yearRate(itmes.yearRate)+"%"+strRate+"</i></span>" +
               "购买金额 <i class=\"orange\">" +
               format_money(parseFloat(itmes.payableAmount))+" 元</i>"+strAmount+"</p></div> ";
     }
    
     break;
   case 'SETTLEMENT':
     str=str + "<div class=\"proList bg-white pr\"><span class=\"expire pa\"></span>"+strType+"<h2 class=\"titlePro br-bottom pr\">"+
     itmes.productName+" <a  href=\"fixed/toFinishedContact?periodNo="+itmes.periodNo+"&orderNo="+itmes.orderNo+"\" class=\"proPact " +
     		"orange font-10 radius1\">产品合同</a>  <span class=\"stage f14\">"+itmes.periodNo+"</span></h2> <div class=\"proCon br-bottom\">" +
     				" <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tbody><tr> <td class=\"orange\"><span class=\"font-18\">";
     if(itmes.incomeReleaseType=='GOODS'){//收益发放类型为商品
       str =str +
       format_money(parseFloat(itmes.payableAmount))+"</span> 元</td><td class=\"orange font-16\">" +
            format_date(itmes.arrivalDate) +"</td> </tr><tr> <td>本金</td><td>到账日</td></tr></tbody></table> </div> <p><a href=\"invForPro/toOrderList?type=finish\" style=\"color:#666\" class=\"fr\">查看订单<i class=\"icon icon-arrow-right\">" +
                     "</i><i class=\"icon icon-arrow-right\"></i></a>" +
                       "购买金额 <i class=\"orange\">" +
                       format_money(parseFloat(itmes.payableAmount))+"元</i>"+strAmount+"</p></div> "; 
     }else{
       str=str+
       format_money(parseFloat(itmes.accumulationIncome)+parseFloat(itmes.payableAmount))+"</span> 元</td> <td class=\"orange\"><span class=\"font-18\">" +
       format_money(itmes.yesterdayIncome)+"</span> 元</td>  <td class=\"orange\">" +
         format_date(itmes.expireDate)+"<br><i class=\"font-10\">后两个工作日</i></td></tr><tr><td>累计未到账本息</td><td>昨日收益</td><td>到账日</td></tr></tbody></table> </div> <p><span class=\"fr\">年化收益率 <i class=\"orange\">" +
                    format_yearRate(itmes.yearRate)+"%"+strRate+"</i></span>" +
                       "购买金额 <i class=\"orange\">" +
                       format_money(parseFloat(itmes.payableAmount))+" 元</i>"+strAmount+"</p></div> ";
     }
    
     break;
   case 'PAYMENT':
     str=str+" <div class=\"proList proListed bg-white pr\">"+strType+"<h2 class=\"titlePro br-bottom pr\">"+
     itmes.productName+" <a  href=\"fixed/toFinishedContact?periodNo="+itmes.periodNo+"&orderNo="+itmes.orderNo+"\" class=\"proPact orange font-10 radius1\">产品合同</a>  <span class=\"stage f14\">"+itmes.periodNo+"</span></h2> <div class=\"proCon br-bottom\"> <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tbody><tr> <td class=\"orange\"><span class=\"font-18\">";
     if(itmes.incomeReleaseType=='GOODS'){//收益发放类型为商品
       str=str+
       format_money(parseFloat(itmes.payableAmount))+"</span> 元"+strAmount+"</td><td class=\"orange font-16\">" +
       format_date(itmes.arrivalDate) +"</td></tr><tr><td>本金</td><td>到账日</td></tr></tbody></table> </div><p><a href=\"invForPro/toOrderList?type=finish\" style=\"color:#666\" class=\"fr\">查看订单<i class=\"icon icon-arrow-right\">" +
                     "</i><i class=\"icon icon-arrow-right\"></i></a>" +
                       "购买金额 <i class=\"orange\">" +
                       format_money(parseFloat(itmes.payableAmount))+"元</i>"+strAmount+"</p></div> ";
     }else{
       str=str+
       format_money(parseFloat(itmes.payableAmount))+"</span> 元"+strAmount+"</td> <td class=\"orange\"><span class=\"font-18\">" +
       format_money(parseFloat(itmes.accumulationIncome)) +"</span> 元</td>  <td class=\"orange font-18\">" +
       format_yearRate(itmes.yearRate) +"%"+strRate+"</td><td class=\"orange font-16\">" +
       format_date(itmes.expireDate) +"</td></tr><tr><td>购买金额</td><td>已到账收益</td><td>年化收益率</td><td>到期日</td></tr></tbody></table> </div></div> ";
     }
    
     break;
   }
 });
  return str;
}
