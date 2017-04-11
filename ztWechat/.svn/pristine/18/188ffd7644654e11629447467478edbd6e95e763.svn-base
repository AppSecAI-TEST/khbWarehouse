<%@page import="com.yeepay.g3.app.lmweChat.service.RequestParamBuilderService"%>
<%@page import="com.yeepay.g3.facade.activity.enums.ShareBizTypeEnum" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yeepay.g3.app.lmweChat.utils.WXAPIUtils,java.util.Map" %>
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
    <script type="text/javascript" src="static/js/common/fixed_common.js"></script>
    <script type="text/javascript" src="static/js/financialIndex.js"></script>
    <script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="static/js/common/wxCommonShare.js"></script>
    <script type="text/javascript" src="static/js/wxFriendRaffleShare.js"></script>
    <%
    String url = "popularize/toPopularize";
    if(request.getQueryString() != null){
      url = "popularize/toPopularize?"+request.getQueryString();
    }
     Map params = WXAPIUtils.getParam(RequestParamBuilderService.APP_ID,RequestParamBuilderService.APP_SECRET, basePath+url); 
	  String shareUrl = null;
	  if((String)session.getAttribute("recommendMemberNo") == null) {
	      shareUrl = basePath+"popularize/toPopularize?recommendMemberNo="+(String)session.getAttribute("recommendMemberNo")+"&bizType="+ShareBizTypeEnum.ALL+"&srcNo="+(String)session.getAttribute("srcNo");
		  
	  } else {
		  shareUrl = basePath+"activity/toInvitedRegister?recommendMemberNo="+(String)session.getAttribute("recommendMemberNo")+"&bizType="+ShareBizTypeEnum.ALL+"&srcNo="+(String)session.getAttribute("srcNo")+"&activityCode=LOTTERY_ACTIVITY";
	  }
    String encodeUrl = java.net.URLEncoder.encode(shareUrl, "utf-8");
    %>
    <script>
    $(document).ready(function() {
	  	var appId='<%=com.yeepay.g3.app.lmweChat.service.RequestParamBuilderService.APP_ID%>';
	  //var appId='wx196f101900ebc50b';
	    var timestamp='<%=params.get("time") %>'; // 生成签名的时间戳
	    var nonceStr='<%=params.get("randomStr")%>'; // 生成签名的随机串
	    var signature='<%=params.get("signature")%>'; // 签名，见附录1
	    var shareUrl='<%=shareUrl%>';
	    if($("#loginOrNoLogin").length == 1) {
	        var wxNickName = $("#wxNickName").val();
	        shareFriend(appId,timestamp,nonceStr,signature,shareUrl,wxNickName);
	    } else {
	    	share(appId,timestamp,nonceStr,signature,shareUrl);
	    }
    });
    </script>
    <script>
  	$(document).ready(function() {
  	  $("#shareFriend").fadeOut(4000);
  	});
    </script>
    <title>购买理财</title>
</head>
<body>
<input type="hidden" id="noticeSwitch" name="noticeSwitch" value="<e:property value="@scbSwitchMap.get('noticeSwitch')"/>"/>
<input type="hidden" id="noticeStartDate" name="noticeStartDate" value="<e:property value="@scbSwitchMap.get('noticeStartDate')"/>"/>
<input type="hidden" id="noticeEndDate" name="noticeEndDate" value="<e:property value="@scbSwitchMap.get('noticeEndDate')"/>"/>
<input type="hidden" id="saleDate" name="saleDate" value="<e:property value="@com.yeepay.g3.app.lmweChat.utils.GetParamUtils.readSeckillConfig()['saleDate']"/>"/>
<div id="box">
    <!-- <div class="picArea"><a href="activity/promo?source=00068W002"><img src="static/images/banner11.jpg"></a></div> -->
    <!--banner-->
<div class="banner banner240"><div id="iSlider-wrapper"></div></div>
<script src="static/js/islider.js"></script>
<script>
    
	var list = [
		{content: "<a href='javascript:void(0);'><img src='static/images/banner/8.jpg'></a>"},
    	{content: "<a href='http://a.app.qq.com/o/simple.jsp?pkgname=com.summer.lanmao'><img src='static/images/banner14.jpg'></a>"},
        {content: "<a href='javascript:void(0);'><img src='static/images/banner/1.jpg'></a>"},
        {content: "<a href='javascript:void(0);'><img src='static/images/banner/2.jpg'></a>"},
        {content: "<a href='javascript:void(0);'><img src='static/images/banner/3.jpg'></a>"}
    ];
    
    var opts = {
        type: 'dom',
        data: list,
        dom: document.getElementById("iSlider-wrapper"),
        isLooping: true,
        isAutoplay: true, //自动播放
        duration: 3000, //间隔时间
        useTime: 500, //每次动画所需时间 ！必须小于duration
        fixPage: false
    };
    var islider = new iSlider(opts);
    islider.addDot();
    
</script>
<!--banner--end-->
<div class="bg-white infoBar infoBar2">
    <ul>
        <li><a href="fundActivity/fundInvset"><i class="icon icon-travel"></i>十元购基</a></li>
        <li><a href="activity/toInviteFriend"><i class="icon icon-lottery"></i>推荐抽奖</a></li>
        <li><a href="activity/toActivityList"><i class="icon icon-activity2"></i>活动专区</a></li>
    </ul>
    <!-- <div id="noticeShow" style="display:none;">
        <div class="clearfix"></div>
        <div class="notice"><i class="icon icon-notice2 orange"></i> <i class="orange">懒猫公告：</i><a href="javascript:void(0)" class="btnClick0" onclick="showHolidayNotice()">系统升级维护公告</a></div>
    </div> -->
    <div id="noticeShow" style="display:none;">
        <div class="clearfix"></div>
        <div class="notice"><i class="icon icon-notice2 orange"></i> <i class="orange">懒猫公告：</i><a href="javascript:void(0)" class="btnClick0" onclick="showHolidayNotice()"><e:property value="@scbSwitchMap.get('noticeName')"/></a></div>
    </div>
</div>
<!-- 新手专区  start -->
<!-- 新手不显示新手标 -->
<e:if test="@isNew == 'YES'">
<div class="titleArea titleNew titleNew2 bg-white mt5" onclick="window.location.href='fixed/toNewManActivity?productId=<e:property value="@FRESHMANDTO.productId"/>'">
    <span class="proinfo fr">新手专享，精彩无限</span>
    <span class="titleIndex"><i class="icon icon-new-hand"></i> 新手专区</span>
</div>
<div class="pr">
    <img src="static/images/new-hand1.jpg" class="repeatImg" onclick="window.location.href='fixed/toPurchaseProduct?productId=<e:property value="@FRESHMANDTO.productId"/>'"/>
    <img src="static/images/new-hand2.jpg" class="repeatImg"/>
    <div class="proIndexNew2 bg-white tc" onclick="window.location.href='fixed/toPurchaseProduct?productId=<e:property value="@FRESHMANDTO.productId"/>'">
        <ul>
            <li>
                <p class="orange"><span class="font-25"><e:property value="@FRESHMANDTO.yearRate" /></span><span class="font-12">%</span></p>
                <p>年化收益率</p>
            </li>
            <li class="br-lr">
                <p class="orange"><span class="font-25"><e:property value="@FRESHMANDTO.termDay"/></span><span class="font-12">天</span></p>
                <p>持有期限</p>
            </li>
            <li>
                <p class="orange"><span class="font-25"><e:property value="@_formater.formateMoney(FRESHMANDTO.cillAmount)"/></span><span class="font-12">元</span></p>
                <p>起购金额</p>
            </li>
        </ul>
    </div>
</div>
</e:if>
<!-- 新手专区 end -->
<!-- 智能投资 start -->
<div class="titleArea titleNew titleNew2 bg-white mt5">
    <span class="proinfo fr">组合投资，动态平衡</span>
    <span class="titleIndex"><i class="icon icon-invest"></i> 智能投资</span>
</div>
<div class="invest-index pr bg-white" onclick="window.location.href='<e:property value="@ztUrl"/>'">
    <div class="rateReturn pr fl">
        <img src="static/images/rateBg.png" class="repeatImg"/>
        <div class="rateCon pa">
            <p class="orange"><e:property value="@ztRateMap.minRateSign"/><span class="num"><e:property value="@ztRateMap.perMinRate"/></span>.<e:property value="@ztRateMap.lateMinRate"/>%～<e:property value="@ztRateMap.maxRateSign"/><span class="num"><e:property value="@ztRateMap.perMaxRate"/></span>.<e:property value="@ztRateMap.lateMaxRate"/>%</p>
            <p class="rateText">过去一年回报率</p>
        </div>
    </div>
    <div class="line pa"></div>
    <div class="wishArea fl">
        <p class="orange font-text-note">多种心愿，我们帮您计划未来!</p>
        <p class="font-text-sm">
            <a href="javascript:void(0);"><i class="icon icon icon-camper"></i> 购房购车</a>
            <a href="javascript:void(0);"><i class="icon icon icon-education"></i> 子女教育</a>
        </p>
        <p class="font-text-sm">
            <a href="javascript:void(0);"><i class="icon icon icon-elder"></i> 养老计划</a>
            <a href="javascript:void(0);"><i class="icon icon icon-custom"></i> 个性定制</a>
        </p>
        <div class="clearfix"></div>
        <p><a href="zt/introduce/brand" class="btn-wish tc radius1 block">去实现愿望</a></p>
    </div>
    <div class="clearfix"></div>
</div>
<!-- 智能投资 end -->
<!-- 秒客盈  start-->
<e:if test="@com.yeepay.g3.app.lmweChat.utils.GetParamUtils.readSeckillFlag() != 'status0'">
    <div class="seckill-index" onclick="window.location.href='seckillActivity/toSeckillPro'">
        <div class="titleArea titleNew titleNew2 font-white">
            <span class="proinfo fr"><e:property value="@com.yeepay.g3.app.lmweChat.utils.GetParamUtils.readSeckillConfig()['appShowInfo']"/></span>
            <span class="titleIndex"><i class="icon icon-seckill"></i> 秒客盈</span>
        </div>
        <!-- 倒计时 -->
        <e:if test="@com.yeepay.g3.app.lmweChat.utils.GetParamUtils.readSeckillFlag() == 'status1'">
            <div class="seckillArea bg-white radius1">
                <div class="seckill-img fl pr">
                    <img src="static/images/seckill-3.png">
                    <div class="countDown-time pa">
                        <span id="t_h">00</span>
                        <span id="t_m">00</span>
                        <span id="t_s">00</span>
                    </div>
                </div>
                <div class="proIndexNew2 tc">
                    <ul>
                        <li>
                            <p class="orange"><span class="font-25"><e:property value="@com.yeepay.g3.app.lmweChat.utils.GetParamUtils.readSeckillConfig()['rate']"/></span><span class="font-12">%</span></p>
                            <p>年化收益率</p>
                        </li>
                        <li class="br-lr">
                            <p class="orange"><span class="font-25"><e:property value="@com.yeepay.g3.app.lmweChat.utils.GetParamUtils.readSeckillConfig()['holdingPeriod']"/></span><span class="font-12">天</span></p>
                            <p>持有期限</p>
                        </li>
                    </ul>
                </div>
            </div>
        </e:if>
        <!-- status2秒杀中或秒杀后 -->
        <e:else>
            <!-- 秒杀完成 -->
            <e:if test="@productDetailResultDto == null || productDetailResultDto.status.toString() != 'SALING'">
                <!--周三有惊喜-->
                <div class="seckillArea bg-white radius1">
                    <div class="seckill-img fl"><img src="static/images/seckill-2.png"> </div>
                    <div class="proIndexNew2 tc">
                        <ul>
                            <li>
                                <p class="orange"><span class="font-25"><e:property value="@com.yeepay.g3.app.lmweChat.utils.GetParamUtils.readSeckillConfig()['rate']"/></span><span class="font-12">%</span></p>
                                <p>年化收益率</p>
                            </li>
                            <li class="br-lr">
                                <p class="orange"><span class="font-25"><e:property value="@com.yeepay.g3.app.lmweChat.utils.GetParamUtils.readSeckillConfig()['holdingPeriod']"/></span><span class="font-12">天</span></p>
                                <p>持有期限</p>
                            </li>
                        </ul>
                    </div>
                </div>
            </e:if>
            <e:else>
                <!--秒杀中-->
                <div class="seckillArea bg-white radius1">
                    <div class="seckill-img fl"><img src="static/images/seckill-1.png"> </div>
                    <div class="proIndexNew2 tc">
                        <ul>
                            <li>
                                <p class="orange"><span class="font-25"><e:property value="@com.yeepay.g3.app.lmweChat.utils.GetParamUtils.readSeckillConfig()['rate']"/></span><span class="font-12">%</span></p>
                                <p>年化收益率</p>
                            </li>
                            <li class="br-lr">
                                <p class="orange"><span class="font-25"><e:property value="@com.yeepay.g3.app.lmweChat.utils.GetParamUtils.readSeckillConfig()['holdingPeriod']"/></span><span class="font-12">天</span></p>
                                <p>持有期限</p>
                            </li>
                        </ul>
                    </div>
                </div>
            </e:else>
        </e:else>
    </div>
</e:if>
<!-- 秒客盈  end-->
<!-- 基金理财 start -->
<div class="titleArea titleNew titleNew2 bg-white mt5" onclick="window.location.href='<e:property value="@fundSalesUrl"/>show/fundList?memberNo=<e:property value="@ascMemberNo"/>&OriginalPro=lmweChat'">
    <span class="proinfo fr">专家理财，智慧投资</span>
    <span class="titleIndex"><i class="icon icon-fund"></i> 基金理财</span>
</div>
<div class="productList bg-white tl">
    <ul>
        <e:iterator list="@fundProductList" var="item">
            <li onclick="window.location.href='<e:property value="@fundSalesUrl"/>show/archivesIndex/<e:property value="@item.fundCode"/>?memberNo=<e:property value="@ascMemberNo"/>'">
                <div class="yield fl">
                    <p><span class="font-12 orange"><e:property value="@item.sign"/></span><span class="font-25"><e:property value="@item.perYearIncrease"/></span><span class="font-12">.<e:property value="@item.lateYearIncrease"/></span><span class="font-12 orange">%</span></p>
                    <p>近一年涨幅</p>
                </div>
                <div class="product fl">
                    <div class="productFirst pr">
                        <p class="productName"><e:property value="@item.fundName"/></p>
                        <e:if test="@item.fundType == '混合型'">
                            <span class="font-10 productStyle style-1 pa"><e:property value="@item.fundType"/></span>
                        </e:if>
                        <e:elseif test="@item.fundType == '股票型'">
                            <span class="font-10 productStyle style-2 pa"><e:property value="@item.fundType"/></span>
                        </e:elseif>
                        <e:elseif test="@item.fundType == '债券型'">
                            <span class="font-10 productStyle style-3 pa"><e:property value="@item.fundType"/></span>
                        </e:elseif>
                        <e:else>
                            <span class="font-10 productStyle style-1 pa"><e:property value="@item.fundType"/></span>
                        </e:else>
                    </div>
                    <p>最新净值：<span class="font-12"><e:property value="@item.unitNv"/></span></p>
                    <p>风险等级：
                        <e:if test="@item.investRisk == 1"><span class="font-12">高</span></e:if>
                        <e:if test="@item.investRisk == 2"><span class="font-12">中</span></e:if>
                        <e:if test="@item.investRisk == 3"><span class="font-12">低</span></e:if>
                        <e:if test="@item.investRisk == 4"><span class="font-12">中高</span></e:if>
                        <e:if test="@item.investRisk == 5"><span class="font-12">中低</span></e:if>
                    </p>
                </div>
            </li>
        </e:iterator>
    </ul>
    <div class="productMore tc"><a href="javascript:void(0);" onclick="window.location.href='<e:property value="@fundSalesUrl"/>show/fundList?memberNo=<e:property value="@ascMemberNo"/>&OriginalPro=lmweChat'">查看更多基金理财产品</a></div>
</div>
<!-- 基金理财  end -->
<!-- 定期理财  start -->
<div class="titleArea titleNew titleNew2 bg-white mt5" onclick="window.location.href='fixed/toPurchaseProduct'">
    <span class="proinfo fr">较低风险，多层保障</span>
    <span class="titleIndex"><i class="icon icon-regular"></i> 定期理财</span>
</div>
<div class="productList bg-white tl">
    <ul>
        <!-- 超短期全部显示 -->
        <e:iterator list="@shortProductList" var="item">
            <li onclick="window.location.href='fixed/toPurchaseProduct?productId=<e:property value="@item.productId"/>'">
                <div class="yield fl">
                    <p><span class="font-25"><e:property value="@item.yearRate"/></span><span class="font-12 orange">%</span></p>
                    <p>年化收益率</p>
                </div>
                <div class="product fl">
                    <p class="productName"><e:property value="@item.productName"/></p>
                    <p>持有期限：<span class="font-12"><e:property value="@item.termDay"/>天</span></p>
                    <p>剩余购买金额：<span class="font-12"><e:property value="@_formater.formateMoney(item.surplusAmount/10000)"/>万</span></p>
                </div>
            </li>
        </e:iterator>
        <!-- 定期理财显示前三个 -->
        <e:iterator list="@fixedPoductList" var="item">
            <li onclick="window.location.href='fixed/toPurchaseProduct?productId=<e:property value="@item.productId"/>'">
                <div class="yield fl">
                    <p><span class="font-25"><e:property value="@item.yearRate"/></span><span class="font-12 orange">%</span></p>
                    <p>年化收益率</p>
                </div>
                <div class="product fl">
                    <p class="productName"><e:property value="@item.productName"/></p>
                    <p>持有期限：<span class="font-12"><e:property value="@item.termDay"/>天</span></p>
                    <p>剩余购买金额：<span class="font-12"><i class="surplusAmount"><e:property value="@_formater.formateMoney(item.surplusAmount/10000)"/></i>万</span></p>
                </div>
            </li>
        </e:iterator>
    </ul>
    <div class="productMore tc"><a href="fixed/toPurchaseProduct">查看更多定期理财产品</a></div>
</div>
<!-- 定期理财  end -->
<!-- 活期理财  start -->
<div class="titleArea titleNew titleNew2 bg-white mt5" onclick="window.location.href='scb/toScb'">
    <span class="proinfo fr">随进随出，1元起购</span>
    <span class="titleIndex"><i class="icon icon-current"></i> 活期理财</span>
</div>
<div class="productList bg-white tl">
    <ul>
        <li onclick="window.location.href='scb/toScb'">
            <div class="yield fl">
                <p><span class="font-25"><e:property value="@perRateForSevenDay"/></span><span class="font-12">.<e:property value="@lateRateForSevenday"/></span><span class="font-12 orange">%</span></p>
                <p>近七日年化</p>
            </div>
            <div class="product proCurrent fl">
                <p><i class="icon icon-safe2"></i> 华夏货币基金，更安全</p>
                <p><i class="icon icon-convenient"></i> 转出实时到账，更便捷</p>
            </div>
        </li>
    </ul>
</div>
<!-- 活期理财  end -->
<!-- 安全保障 -->
<div class="pr">
    <a href="account/toInsurance"><img src="static/images/safeIndex2.jpg" class="repeatImg"></a>
</div>
<div class="h1-2"></div>
<!--懒猫公告弹出层-->
<div id="mask" style="display: none"></div>
<!-- <div id="holidayNotice" class="alertLayer" style="display: none; width: 100%; top: 15%">
    <div class="noticeCon pr">
        <a class="btnClosed bg-white pa" href="javascript:void(0)"><i class="icon icon-error orange"></i></a>
        <div class="noticeText">
            <p class="orange">亲爱的懒猫用户：</p>
            <p class="indent2 justify">懒猫金服近期将进行系统升级维护，涉及到的业务在维护期间将暂停服务，其中：</p>
            <p class="indent2 justify">1、生财宝的升级维护时间为2016年10月18日14:00-10月19日16:00；</p>
            <p class="indent2 justify">2、基金理财的升级维护时间为2016年10月18日14:00-24:00；</p>
            <p class="indent2 justify">3、平台整体系统升级维护时间为2016年10月23日0:00-24:00。</p>
            <p class="indent2 justify">给您带来的不便，深表歉意，敬请谅解！</p>
            <p class="indent2 justify"></p>
            <p class="indent2 justify"></p>
            <p class="orange tr">懒猫金服<br/>2016年10月17日</p>
        </div>
    </div>
</div> -->
<div id="holidayNotice" class="alertLayer" style="display: none; width: 100%; top: 15%">
    <div class="noticeCon pr">
        <a class="btnClosed bg-white pa" href="javascript:void(0)"><i class="icon icon-error orange"></i></a>
        <div class="noticeText">
            <p class="orange">亲爱的懒猫用户：</p>
            <p class="indent2 justify"><e:property value="@scbSwitchMap.get('noticeStart')"/></p>
            <p class="indent2 justify"><e:property value="@scbSwitchMap.get('notice1')"/></p>
            <p class="indent2 justify"><e:property value="@scbSwitchMap.get('notice2')"/></p>
            <e:if test="@scbSwitchMap.get('notice3') != null">
                <p class="indent2 justify"><e:property value="@scbSwitchMap.get('notice3')"/></p>
            </e:if>
            <e:if test="@scbSwitchMap.get('notice4') != null">
                <p class="indent2 justify"><e:property value="@scbSwitchMap.get('notice4')"/></p>
            </e:if>
            <e:if test="@scbSwitchMap.get('noticeEnd') != null">
                <p class="indent2 justify"><e:property value="@scbSwitchMap.get('noticeEnd')"/></p>
            </e:if>
            <p class="orange tr"><e:property value="@scbSwitchMap.get('noticeCom')"/><br/><e:property value="@scbSwitchMap.get('noticeDate')"/></p>
        </div>
    </div>
</div>
</body>
</html>