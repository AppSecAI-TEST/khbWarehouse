<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="e" uri="/emvc-tags" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"+ request.getServerName(); 
  if(request.getServerPort()!=80){
    basePath = basePath + ":" + request.getServerPort(); 
  }      
  basePath = basePath + path + "/";
%>
<html>
<head lang="en">
    <link rel="stylesheet" href="static/css/islider.css">
    <title>首页</title>
</head>
<body>
<!--banner-->
<div class="banner banner240"><div id="iSlider-wrapper"></div></div>
<script src="static/js/islider.js"></script>
<script>
    var list = [
	 	{content: "<a href='activity/toAppMarketingPromotion?productId=<e:property value='@id'/>'><img src='static/images/banner13.jpg'></a>"}, 
        {content: "<a href='javascript:void(0);'><img src='static/images/banner/1.jpg'></a>"},
        {content: "<a href='javascript:void(0);'><img src='static/images/banner/2.jpg'></a>"},
        {content: "<a href='javascript:void(0);'><img src='static/images/banner/3.jpg'></a>"},
        {content: "<a href='javascript:void(0);'><img src='static/images/banner/4.jpg'></a>"}
        
    ];
    /* var opts = {
        type: 'dom',
        data: list,
        dom: document.getElementById("iSlider-wrapper"),
        isLooping: true,
    };
    var	islider = new iSlider(opts);
    islider.addDot(); */
    var opts = {
        type: 'dom',
        data: list,
        dom: document.getElementById("iSlider-wrapper"),
        isLooping: true,
        isAutoplay: true, //自动播放
        duration: 3000, //间隔时间
        useTime: 500 //每次动画所需时间 ！必须小于duration
    };
    var	islider = new iSlider(opts);
    islider.addDot();
</script>
<script type="text/javascript">
	$(function(){
	  var curDate = new Date();
      var invalidDate = new Date("2016/10/08,00:00:00");
      if(curDate >= invalidDate){
        $("#mask").hide();
        $("#noticeShow").hide();
      }else{
        $("#noticeShow").show();
      }
	});
	function showHolidayNotice(){
	  $("#mask").show();
	  $("#holidayNotice").show();
	}
</script>
<!--banner--end-->
<div class="bg-white infoBar">
    <ul>
        <li><a href="fixed/toPurchaseProduct?productId=<e:property value="@FRESHMANID"/>"><i class="icon icon-beginner"></i>新手专区</a></li>
        <li><a href="invForPro/toInvestForTravelList"><i class="icon icon-change"></i>心愿单</a></li>
        <li><a href="activity/toActivityList"><i class="icon icon-active"></i>活动专区</a></li>    
        <!-- <li><a href="activity/toActivityList"><i class="icon icon-mid-autumn"></i>活动专区</a></li> --> 
    </ul>
    <div id="noticeShow" style="display:block;">
        <div class="clearfix"></div>
        <div class="notice"><i class="icon icon-notice2 orange"></i> <i class="orange">懒猫公告：</i><a href="javascript:void(0)" class="btnClick0" onclick="showHolidayNotice()">2016国庆假期工作安排公告</a></div>
    </div>
</div>
<!-- <div class="safeIndex tc"><i class="icon icon-safe"></i> 资金交易过程由易宝支付提供安全保障</div> -->
<div class="titleArea titleNew bg-white mt15">
    <span class="more fr"><a href="scb/toScb" class="orange font-16">更多</a><i class="icon icon-arrow-r"></i> </span>
    <span class="titleIndex">活期理财</span>
    <span class="profitTip orange tc pr">
        随进随出，1元起购
    </span>
</div>
<div class="proIndexNew bg-white tc"  onClick="window.location.href='scb/toScb'">
    <table cellpadding="0" cellspacing="0" width="100%">
        <tbody>
            <tr>
                <th width="33%"><span class="font-25 orange"><e:property value="@lastestRateForSevenDay"/></span><span class="font-12 orange">%</span></th>
                <th width="33%">安全</th>
                <th>便捷</th>
            </tr>
            <tr>
                <td>近七日年化</td>
                <td>华夏货币基金</td>
                <td>转出实时到账</td>
            </tr>
        </tbody>
    </table>
</div>

<div class="titleArea titleNew bg-white mt15">
    <span class="more fr"><a href="fixed/toPurchaseProduct?productId=<e:property value="@id"/>" class="orange font-16">更多</a><i class="icon icon-arrow-r"></i> </span>
    <span class="titleIndex">定期理财</span>
    <span class="profitTip orange tc pr">
        安全稳赢，较高收益
    </span>
</div>
<div class="proIndexNew bg-white tc" onClick="window.location.href='fixed/toPurchaseProduct?productId=<e:property value="@id"/>'">
    <table cellpadding="0" cellspacing="0" width="100%">
        <tbody>
        <tr>
            <th width="33%"><span class="font-25 orange"><e:property value="@yearRate"/></span><span class="font-12 orange">%</span></th>
            <th width="33%"><span class="num"><e:property value="@termDay"/></span><span class="font-12">天</span></th>
            <th><span class="num"><e:property value="@_formater.formateMoney(surplusAmount/10000)"/></span><span class="font-12">万</span></th>
        </tr>
        <tr>
            <td>年化收益率</td>
            <td>持有期限</td>
            <td>剩余购买金额</td>
        </tr>
        </tbody>
    </table>
</div>

<div class="titleArea titleNew bg-white mt15">
    <span class="more fr"><a href='<e:property value="@fundSalesUrl"/>show/fundList?memberNo=<e:property value="@ascMemberNo"/>&platform=<e:property value="@platform"/>&OriginalPro=lmweChat' class="orange font-16">更多</a><i class="icon icon-arrow-r"></i> </span>
    <span class="titleIndex">基金理财</span>
    <span class="profitTip orange tc pr">
        专家理财，智慧投资
    </span>
</div>
<div class="proIndexNew bg-white tc" onClick="window.location.href='<e:property value="@fundSalesUrl"/>show/fundList?memberNo=<e:property value="@ascMemberNo"/>&platform=<e:property value="@platform"/>&OriginalPro=lmweChat'" >
    <table cellpadding="0" cellspacing="0" width="100%">
        <tbody>
        <tr>
            <th width="33%"><span class="font-12 orange">最高</span><span class="font-20 orange"><e:property value="@indexShowRate"/></span><span class="font-12 orange">%</span></th>
            <th width="33%">五星优选</th>
            <th>长期投资</th>
        </tr>
        <tr>
            <td>近一年涨幅</td>
            <td>由专家团队甄选</td>
            <td>追求更高收益</td>
        </tr>
        </tbody>
    </table>
</div>
<div class="safeIndex pr tc">
    <img src="static/images/safeIndex.jpg" class="repeatImg">
    <a href="account/toInsurance" class="pa"></a>
</div>
<!--懒猫公告弹出层-->
<div id="mask" style="display: none"></div>
<div id="holidayNotice" class="alertLayer" style="display: none; width: 100%; top: 8%">
    <div class="noticeCon pr">
        <a class="btnClosed bg-white pa" href="javascript:void(0)"><i class="icon icon-error orange"></i></a>
        <div class="noticeText">
            <p class="orange">亲爱的懒猫用户：</p>
            <p class="indent2 justify">根据国家放假规定，10月1日至10月7日为国庆节假期，节假日期间的各项安排如下：</p>
            <p class="indent2 justify">关于提现：</p>
            <p class="indent2 justify">假期期间，可以正常提现。(由于节假日期间各别银行可能会出现临时维护，具体到账时间以银行处理时间为准)</p>
            <p class="indent2 justify">关于充值：</p>
            <p class="indent2 justify">假期期间，可以正常充值。目前暂未收到银行方面的通知。</p>
            <p class="indent2 justify">关于产品：</p>
            <p class="indent2 justify">假期期间，线上产品均可正常购买。</p>
            <p class="indent2 justify">关于到期产品：</p>
            <p class="indent2 justify">假期期间到期的产品将顺延至节后工作日兑付到账，通常是两个工作日内到账。</p>
            <p class="indent2 justify">关于客服：</p>
            <p class="indent2 justify">假期期间，您可正常在懒猫平台微信客服处留言，客服人员将定期集中处理。10月8日上午9点正常上班，为您带来的不便敬请谅解。</p>
            <p class="indent2 justify">祝大家国庆节快乐！</p>
        </div>
    </div>
</div>
<!--弹出层end-->
</body>
</html>