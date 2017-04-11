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
    <script type="text/javascript" src="static/js/inviteFriend.js"></script>
    <%
    String url = "activity/toInviteFriend";
    if(request.getQueryString() != null){
      url = "activity/toInviteFriend?"+request.getQueryString();
    }
    Map params = WXAPIUtils.getParam(RequestParamBuilderService.APP_ID,RequestParamBuilderService.APP_SECRET, basePath+url);
   /* Map params = WXAPIUtils.getParam("wx196f101900ebc50b","d4624c36b6795d1d99dcf0547af5443d",basePath+url); */
    String shareUrl = basePath+"activity/toInvitedRegister?recommendMemberNo="+(String)session.getAttribute("recommendMemberNo")+"&bizType="+ShareBizTypeEnum.ALL+"&srcNo="+(String)session.getAttribute("srcNo")+"&activityCode=LOTTERY_ACTIVITY";
    String encodeUrl = java.net.URLEncoder.encode(shareUrl, "utf-8");
    %>
    <script>
    $(document).ready(function() {
  	var appId='<%=com.yeepay.g3.app.lmweChat.service.RequestParamBuilderService.APP_ID%>';
   /* var appId='wx196f101900ebc50b'; */
    var timestamp='<%=params.get("time") %>'; // 生成签名的时间戳
    var nonceStr='<%=params.get("randomStr")%>'; // 生成签名的随机串
    var signature='<%=params.get("signature")%>'; // 签名，见附录1
    var shareUrl='<%=shareUrl%>';
    var wxNickName = $("#wxNickName").val();
    shareFriend(appId,timestamp,nonceStr,signature,shareUrl,wxNickName);
    });
    </script>
    
    <title>推荐好友抽取大奖</title>
</head>
<body>
<input type="hidden" id="status" value="<e:property value="@status"/>">
<div class="pr">
    <img src="static/images/activity/intiver-01.jpg" class="repeatImg"/>
    <e:if test="${!empty prizeDtoNewList }">
		    <div id="demo" class="scroll">
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
    <img src="static/images/activity/intiver-02.jpg" class="repeatImg"/>
    <img src="static/images/activity/intiver-03.jpg" class="repeatImg"/>
</div>
<div class="pr">
    <img src="static/images/activity/intiver-04.jpg" class="repeatImg"/>
    <e:if test="${empty userInfo }">
    	<p class="lotteryH font-white pa">剩余抽奖机会0次</p>
    </e:if>
    <e:if test="${!empty userInfo }">
    	<p class="lotteryH font-white pa">剩余抽奖机会<e:property value="@canuseTicketCount"/>次</p>
    </e:if>
    <a id="raffle" class="lotteryBtn pa"></a>
</div>
<div class="pr">
    <img src="static/images/activity/intiver-05.jpg" class="repeatImg"/>
    <img src="static/images/activity/intiver-06.jpg" class="repeatImg"/>
    <e:if test="@platform != APP">
      <img src="static/images/activity/intiverBtn.jpg" class="repeatImg"/>
    </e:if>
    
</div>
<e:if test="@platform != 'APP'">
  <div class="fixedBtn" id="inviteFriend">
    <a href="javascript:void(0);" class=""><img src="static/images/activity/intiverBtn.jpg" class="repeatImg"/></a>
  </div>
</e:if>

<e:if test="${!empty commonUser }">
  <input style="display:none" value=<e:property value="@commonUser"/> id="commonUser" /><!-- true普通用户 	 false不是普通用户 -->
</e:if>

<input style="display:none" id="wxNickName" value='<e:property value="@userInfo.wxNickName"/>'/>
<!--弹出层-->
<div id="mask" style="display: none"></div>
<!-- <div id="alertLayer" style="display: none; width: 100%;">
    <div class="pr">
        <img src="static/images/activity/intiverMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed intiverClosed pa" href="javascript:void(0)"></a>
    </div>
</div> -->

<!--弹出层-->
<div id="mask" style="display: none"></div>
<div id="alertLayer" style="display: none; width: 100%;" class="shareTips">
    <div class="pr">
        <img src="static/images/activity/intiverMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed intiverClosed pa" href="javascript:void(0)"></a>
    </div>
</div>

<!-- 邀请好友提示语 -->
<div id="alertLayer-3" style="display: none; width: 80%; ">
    <div class="pr activityLayer">
        <a class="btnClosed icon icon-error2 pa" href="javascript:void(0)"></a>
        <p class="justify" id="inviteTips">对不起，已经成为懒猫金服推荐人、理财顾问或销售的用户不在此活动的参与范围内。</p>
        <a class="submitBtn2 btnClosed" href="javascript:void(0)">确 定</a>
    </div>
</div>
<!-- 登录注册弹出层 -->
<div id="my-alertLayer" class="unloginMask js_register" style="display: none; width: 100%; top:50px; left:0;position:absolute;z-index:9999;">
<!-- <div id="my-alertLayer" class="unloginMask js_register"
	style="display: none; width: 100%; height: 100%;"> -->
	<div class="pr">
		<img src="static/images/unloginMask.png" class="repeatImg" alt="" />
		<a class="btnClosed font-white pa"><i class="icon icon-error2"></i></a>
		<a href="account/toRegister?returnFlag=toInviteFriendRaffle" class="btnReg pa"></a> 
		<a href="account/toLogin?returnFlag=toInviteFriendRaffle" class="btnLogin pa"></a>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
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
});
    
</script>
</body>
</html>