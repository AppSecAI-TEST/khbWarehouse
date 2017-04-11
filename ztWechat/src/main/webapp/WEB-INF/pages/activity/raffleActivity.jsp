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
    <link rel="stylesheet" href="static/css/LM-activity.css">
    <script type="text/javascript" src="static/js/financialIndex.js"></script>
    
    <script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="static/js/wxFriendRaffleShare.js"></script>
    <%
    String url = "activity/toRaffleActivity";
    if(request.getQueryString() != null){
      url = "activity/toRaffleActivity?"+request.getQueryString();
    }
     Map params = WXAPIUtils.getParam(RequestParamBuilderService.APP_ID,RequestParamBuilderService.APP_SECRET, basePath+url); 
  /*  Map params = WXAPIUtils.getParam("wx196f101900ebc50b","d4624c36b6795d1d99dcf0547af5443d",basePath+url); */
    String shareUrl = basePath+"activity/toInvitedRegister?recommendMemberNo="+(String)session.getAttribute("recommendMemberNo")+"&bizType="+ShareBizTypeEnum.ALL+"&srcNo="+(String)session.getAttribute("srcNo")+"&activityCode=LOTTERY_ACTIVITY";
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
    var wxNickName = $("#wxNickName").val();
    shareFriend(appId,timestamp,nonceStr,signature,shareUrl,wxNickName);
    });
    </script>
    
    <title>疯狂抽奖赚起来</title>
</head>
<body>
<div class="pr">
    <img src="static/images/activity/rotate-01.jpg" class="repeatImg"/>
    <e:if test="${!empty prizeDtoNewList }">
		    <div id="demo" class="scroll" style="bottom: 20%">
		        <div class="lotteryScroll">
		            <div id="demo1">
		                <ul>
		                <e:iterator list="@prizeDtoNewList" var="items">
		                    <li><e:property value="@_formater.formatDate(items.createTime)"/> <e:property value="@_formater.maskCellphone(items.memberTel)"/> 获得奖品<e:property value="@items.prizeName"/></li>
		                </e:iterator>
		                </ul>
		            </div>
		            <div id="demo2"></div>
		        </div>
		    </div>
      </e:if>
</div>
<div class="pr">
    <img src="static/images/activity/rotate-02.jpg" class="repeatImg"/>
    <div class="progress">
        <div id="score" class="progressBar" style="width:<e:property value='@luckyScore/0.88'/>%;">
            <div class="value" id="showScore"><e:property value="@luckyScore"/></div>
        </div>
        <span class="min">0</span>
        <span class="max">88</span>
    </div>
    <div class="turntable-bg">
        <img src="static/images/activity/turntable-bg.png" alt=""/>
        <div class="pointer"><img src="static/images/activity/pointer.png" class="repeatImg" alt="pointer"/></div>
        <div class="rotate" ><img id="rotate" src="static/images/activity/turntable.png" class="repeatImg"  alt="turntable"/></div>
    </div>
    <div class="cloud pr">
        <img src="static/images/activity/cloud.png" class="repeatImg" alt=""/>
        <p class="lotteryText">已抽奖 <span id="usedCount"><e:property value="@usedTicketCount"/></span> 次，剩余抽奖机会 <span id="canUse"><e:property value="@canuseTicketCount"/></span> 次</p>
        <a href="activity/toRafflePrizeList" class="lotteryBtn pa"></a>
    </div>
</div>
<input style="display:none" id="actionCode" value='LOTTERY_ACTION'/>
<input style="display:none" id="wxNickName" value='<e:property value="@activityUserInfo.wxNickName"/>'/>
<input style="display:none" value='<e:property value="@isFlag"/>' id="isFlag" />
<!-- <input style="display:none" id="activityCode" value='007'/> -->
<div class="pr">
    <img src="static/images/activity/rotate-03.jpg" class="repeatImg"/>
</div>
<e:if test="${commonUser == true }">
	<div class="fixedBtn" id="inviteFriend">
	    <a href="javascript:void(0);" class=""><img src="static/images/activity/intiverBtn.jpg" class="repeatImg"/></a>
	</div>
</e:if>
<!--弹出层-->
<div id="mask" style="display: none"></div>
<div id="alertLayer" style="display: none; width: 100%; ">
    <div class="pr">
        <img src="static/images/activity/intiverMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed intiverClosed pa" href="javascript:void(0)"></a>
    </div>
</div>

<!-- 中奖的弹层 -->
<div id="alertLayer-3" style="display: none; width: 80%; ">
    <div class="pr activityLayer">
        <a class="btnClosed icon icon-error2 pa" href="javascript:void(0)"></a>
        <p class="justify" id="prizeInfo">恭喜您获得XXX，我们的工作人员会在两个工作日内与您联系确认奖品发放事宜，请保持手机畅通。</p>
        <a class="submitBtn2 btnClosed" href="javascript:void(0)">确 定</a>
    </div>
</div> 
<script type="text/javascript">
    var demo = document.getElementById("demo");
    var demo1 = document.getElementById("demo1");
    var demo2 = document.getElementById("demo2");
    demo2.innerHTML=document.getElementById("demo1").innerHTML;
    function Marquee(){
        if(demo.scrollLeft-demo2.offsetWidth>=0){
            demo.scrollLeft-=demo1.offsetWidth;
        }
        else{
            demo.scrollLeft++;
        }
    }
    setInterval(Marquee,30);

</script>
<script type="text/javascript" src="static/js/awardRotate.js"></script>
<script type="text/javascript" src="static/js/raffleActivity.js"></script>
</body>
</html>