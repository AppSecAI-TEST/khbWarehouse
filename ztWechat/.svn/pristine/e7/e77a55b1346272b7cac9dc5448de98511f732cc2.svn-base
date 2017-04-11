<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags"%>
<html>
<head lang="en">
<title>懒猫帐户余额</title>
</head>
<body>
  <div id="box" class="scb">
    <div class="layout-wrap scbCard font-white tc">
      <div class="layou-up balanceCard">
        <a href="javascript:void(0)" class="font-white"> <span
          class="font-45"><e:property
              value="@_formater.formatNumber(amount)" /></span>
          <p class="font-16">懒猫账户金额（元）</p>
        </a> <span class="profitTip pr" style="margin-top:.1rem"> <i
          class="icon-arrow triangle-top"></i> 不产生收益
        </span> 
      </div>
    </div>
    <!--<div class="balanceArea bg-white mt03 pr">-->
    <!--<i class="icon icon-vip orange pa"></i>-->
    <!--<h2 class="titleH2">享大额充值特权</h2>-->
    <!--<p class="balanceInfo br-bottom">手机上充值有限额？先使用网银在电脑上大额充值，再用余额理财，轻松突破限额</p>-->
    <!--<a href="#">-->
    <!--<i class="icon icon-arrow-right fr"></i>-->
    <!--<i class="orange font-16">立即体验</i>-->
    <!--</a>-->
    <!--</div>-->
    <div class="balanceArea bg-white mt03 pr">
      <i class="icon icon-buy orange pa"></i>
      <h2 class="titleH2">可买入理财产品</h2>
      <p class="balanceInfo br-bottom">余额不产生收益，可用于买入理财产品</p>
      <ul class="lcList">
        <li class="br-bottom"><a href="scb/toScb"> <i
            class="icon icon-arrow-right fr"></i> <i class="gray fr"></i>
            活期理财近七日年化 <e:property value="@lastestRateForSevenDay"/>％
        </a></li>
        <li class="br-bottom"><a href="fixed/toPurchaseProduct"> <i
            class="icon icon-arrow-right fr"></i> <i class="gray fr"></i>
            定期理财年化收益率 5.0%－7.0%
        </a></li>
        <li><a onclick="window.location.href='<e:property value="@fundSalesUrl"/>show/fundList?memberNo=<e:property value="@ascMemberNo"/>&OriginalPro=lmweChat'"> <i
            class="icon icon-arrow-right fr"></i> <i class="gray fr"></i>
            基金理财最高年涨幅 <e:property value="@indexShowRate"/>％
        </a></li>
      </ul>
    </div>
  </div>
  <div class="btn-group">
    <!-- app充值，提现 -->
    <e:if test="@platform=='APP'">
      <e:if test="@isBankCard=='YES'">
        <a href="asset/toCashIn"> <input type="button" value="提现"
          class="btn-inout" /></a>
        <a href="asset/toRecharge"> <input type="button" value="充值"
          class="btn-inout btn-in" /></a>
      </e:if>
      <e:else>
        <a href="account/card/toBindCard?returnFlag=toCashIn"> <input
          type="button" value="提现" class="btn-inout" /></a>
        <a href="account/card/toBindCard?returnFlag=toRecharge"> <input
          type="button" value="充值" class="btn-inout btn-in" /></a>
      </e:else>
    </e:if>
    <e:else>
      <!-- wx充值，提现 -->
      <a href="asset/toCashIn"><input type="button" value="提现"
          class="btn-inout" /></a>
      <a href="asset/toRecharge"> <input type="button" value="充值"
        class="btn-inout btn-in" /></a>
    </e:else>

  </div>
  <a class="service" href="focus/toContactService"><i
    class="icon icon-service font-white"></i></a>
</body>
</html>