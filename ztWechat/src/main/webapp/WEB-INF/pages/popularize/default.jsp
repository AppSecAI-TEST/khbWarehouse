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
    <script type="text/javascript" src="static/js/financialIndex.js"></script>
    <script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="static/js/common/wxCommonShare.js"></script>
    <script type="text/javascript" src="static/js/wxFriendRaffleShare.js"></script>
    <%
    String url = "popularize/toPopularize";
    if(request.getQueryString() != null){
      url = "popularize/toPopularize?"+request.getQueryString();
    }
//     System.out.println(url);
     Map params = WXAPIUtils.getParam(RequestParamBuilderService.APP_ID,RequestParamBuilderService.APP_SECRET, basePath+url); 
//     System.out.println(params);
  /*  Map params = WXAPIUtils.getParam("wx196f101900ebc50b","d4624c36b6795d1d99dcf0547af5443d",basePath+url); */
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
    
    <title>首页</title>
</head>
<body>
<div id="box">
    <!-- <div class="picArea"><a href="activity/promo?source=00068W002"><img src="static/images/banner11.jpg"></a></div> -->
    <!--banner-->
<div class="banner banner240"><div id="iSlider-wrapper"></div></div>
<script src="static/js/islider.js"></script>
<script>
    
	var list = [
    	{content: "<a href='http://a.app.qq.com/o/simple.jsp?pkgname=com.summer.lanmao'><img src='static/images/banner14.jpg'></a>"},
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
    var islider = new iSlider(opts);
    islider.addDot(); */
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
    
    /* function bannerClick(){
      location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.summer.lanmao";
    } */
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
            <div id="" class="notice"><i class="icon icon-notice2 orange"></i> <i class="orange">懒猫公告：</i><a href="javascript:void(0)" class="btnClick0" onclick="showHolidayNotice()">2016国庆假期工作安排公告</a></div>
        </div>
    </div>
    <div class="titleArea br-bottom bg-white mt15">
        <span class="more more1 fr"><a href="scb/toScb" class="gray">更多</a><i class="icon icon-arrow-r"></i> </span>
        <span class="titleIndex">活期理财</span>
        <span class="profitTip orange tc pr">
            <i class="icon-arrow triangle-left"></i>
            随进随出，1元起购
        </span>
    </div>
    <div class="proIndex pr">
        <div class="titleIndexPro orange pa" >
            <i class="icon icon-item"></i>
            <span class="pa">生财宝</span>
        </div>
        <div class="proIndexTable fr orange tc" onClick="window.location.href='scb/toScb'">
            <table cellpadding="0" cellspacing="0" width="100%">
                <tbody>
                    <tr>
                        <th width="33%"><span class="font-25"><e:property value="@lastestRateForSevenDay"/></span><span class="font-16">%</span></th>
                        <th width="33%"><span>安全</span></th>
                        <th><span>便捷</span></th>
                    </tr>
                    <tr>
                        <td width="33%"><span class="font-12">近七日年化</span></td>
                        <td width="33%"><span class="font-12 gray">华夏货币基金</span></td>
                        <td><span class="font-12 gray">转出实时到账</span></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="titleArea br-bottom bg-white mt15">
        <span class="more more1 fr"><a href="fixed/toPurchaseProduct?productId=<e:property value="@id"/>" class="gray">更多</a><i class="icon icon-arrow-r"></i> </span>
        <span class="titleIndex">定期理财</span>
        <span class="profitTip orange tc pr">
            <i class="icon-arrow triangle-left"></i>
            安全稳赢，较高回报
        </span>
    </div>
    <div class="proIndex pr">
        <div class="titleIndexPro orange pa">
            <i class="icon icon-item"></i>
            <span class="pa">月月盈</span>
        </div>
        <div class="proIndexTable fr orange tc" onClick="window.location.href='fixed/toPurchaseProduct?productId=<e:property value="@id"/>'">
            <table cellpadding="0" cellspacing="0" width="100%">
                <tbody>
                <tr>
                <th width="33%"><span class="font-25"><e:property value="@yearRate"/></span><span class="font-16">%</span></th>
                <th width="33%"><span class="font-20"><e:property value="@termDay"/></span><span class="font-16">天</span></th>
                <th><span class="font-20"><e:property value="@_formater.formateMoney(surplusAmount/10000)"/></span><span class="font-16">万</span></th>
            </tr>
                <tr>
                    <td width="33%"><span class="font-12">年化收益率</span></td>
                    <td width="33%"><span class="font-12">持有期限</span></td>
                    <td><span class="font-12 gray">剩余购买金额</span></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="titleArea br-bottom bg-white mt15">
        <span class="more more1 fr"><a href='<e:property value="@fundSalesUrl"/>show/fundList?memberNo=<e:property value="@ascMemberNo"/>&OriginalPro=lmweChat' class="gray">更多</a><i class="icon icon-arrow-r"></i> </span>
        <span class="titleIndex">基金理财</span>
        <span class="profitTip orange tc pr">
            <i class="icon-arrow triangle-left"></i>
            专家理财，智慧投资
        </span>
    </div>
    <div class="proIndex pr">
        <div class="titleIndexPro orange pa">
            <i class="icon icon-item"></i>
            <span class="pa jzTitle">基金</span>
        </div>
        <div class="proIndexTable fr orange tc" onClick="window.location.href='<e:property value="@fundSalesUrl"/>show/fundList?memberNo=<e:property value="@ascMemberNo"/>&OriginalPro=lmweChat'">
            <table cellpadding="0" cellspacing="0" width="100%">
                <tbody>
                <tr>
                    <th width="33%"><span class="font-25"><e:property value="@indexShowRate"/></span><span class="font-16">%</span></th>
                    <th width="33%"><span class="font-16">五星优选</span></th>
                    <th><span class="font-16">长期投资</span></th>
                </tr>
                <tr>
                    <td width="33%"><span class="font-12">最高年涨幅</span></td>
                    <td width="33%"><span class="font-12">专家团队甄选</span></td>
                    <td><span class="font-12 gray">追求更高收益</span></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
<div class="safeIndex safeIndex2 pr tc">
        <img src="static/images/safeIndex.jpg" class="repeatImg">
        <a href="account/toInsurance" class="pa"></a>
    </div>
    <div class="h1-2"></div>
</div>

<!--弹出层-->
<!-- <div id="mask" style="display: block"></div> -->
<input style="display:none"  value='<e:property value="@activityUserInfo.wxNickName"/>' id="wxNickName"/>
<!--弹出层-->
	<e:if test="@flag=='unLogin'">
		<!--未登录弹出层-->
		<e:if test="@firstIn == 'firstIn'">
				<div id="mask" style="display: block"></div>
		<div id="alertLayer" class="unloginMask"
			style="display: block; width: 100%; height: 100%;">
			<div class="pr">
				<img src="static/images/unloginMask.png" class="repeatImg" alt="" />
				<a class="btnClosed font-white pa"><i class="icon icon-error2"></i></a>
				<a href="account/toRegister" class="btnReg pa"></a> <a href="account/toLogin" class="btnLogin pa"></a>
			</div>
		</div>
		</e:if>
	</e:if>
	<!--登陆未绑卡蒙版弹出层-->
	<e:elseif test="@flag=='unBankCard'">
	<e:if test="@first == 'firstAction'">
	<div id="mask" style="display: block"></div>
		<div id="alertLayer" class="unloginMask"
			style="display: block; width: 100%; height: 100%;">
			<div class="pr">
				<img src="static/images/untieCard.png" class="repeatImg" alt="" />
				<a class="btnClosed font-white pa"><i
					class="icon icon-error2"></i></a> <a href="account/card/toBindCard" class="btntieCard pa"></a>
			</div>
		</div>
		</e:if>
		<!--登陆绑卡未投资弹出层-->
	</e:elseif>
	<e:elseif test="@flag=='unBuy'">
	<e:if test="@first == 'firstAction'">
	<div id="mask" style="display: block"></div>
		<div id="alertLayer" class="unloginMask"
			style="display: block; width: 100%; height: 100%;">
			<div class="pr">
				<img src="static/images/unBuy.png" class="repeatImg" alt="" /> 
				<a class="btnClosed font-white pa">
					<i class="icon icon-error2"></i>
				</a> 
				<a href="javascript:void(0)" onclick="getItemId(<e:property value="@FRESHMANID"/>)" class="btntieCard btnunBuy pa"></a>
			</div>
		</div>
		</e:if>
	</e:elseif>
	<!--弹出层end-->
	<!--分享弹出层-->
<div id="share" style="display: none" ></div>
<div id="alertLayer" class="unloginMask" style="display: none; width: 100%; height:100%;">
    <div class="pr">
        <a class="btnClosed" href="javascript:void(0)" id="shareAlert"><img src="static/images/share.png" class="repeatImg" alt=""/></a>
    </div>
</div>
<!--弹出层end-->
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