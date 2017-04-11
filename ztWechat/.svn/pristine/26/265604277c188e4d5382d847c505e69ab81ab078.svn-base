<%@page import="com.yeepay.g3.app.lmweChat.service.RequestParamBuilderService"%>
<%@page import="com.yeepay.g3.facade.activity.enums.ShareBizTypeEnum" %> 
<%@page import="com.yeepay.g3.app.lmweChat.utils.WXAPIUtils,java.util.Map" %>
<%@page import="com.yeepay.g3.app.lmweChat.utils.LmConstants" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
      + request.getServerName() + ":" + request.getServerPort()
      + path + "/";
  String sysVersion = LmConstants.sysVersion;
%>
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--优先使用 IE 最新版本和 Chrome-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, maximum-scale=1.0, initial-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <!--设置苹果工具栏颜色-->
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="static/css/LM-common.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/LM-app.css?v=<%=sysVersion %>">
    <script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="static/js/common/wxCommonShare.js?v=<%=sysVersion %>"></script>
    <%
//     String url = "fixed/toNewManActivity?productId="+request.getAttribute("productId");
    String url = "fixed/toNewManActivity";
    if(request.getQueryString() != null){
        url = "fixed/toNewManActivity?"+request.getQueryString();
    }
//     System.out.println(url);
     Map params = WXAPIUtils.getParam(RequestParamBuilderService.APP_ID,RequestParamBuilderService.APP_SECRET, basePath+url); 
//      System.out.println(params);
//     Map params = WXAPIUtils.getParam("wxe27267ccc8d4808e","7e63b6630406ca967d087e7dc7db4793",basePath+url);
    String shareUrl = basePath+"popularize/toPopularize?recommendMemberNo="+(String)session.getAttribute("recommendMemberNo")+"&bizType="+ShareBizTypeEnum.ALL+"&srcNo="+(String)session.getAttribute("srcNo");
    String encodeUrl = java.net.URLEncoder.encode(shareUrl, "utf-8");
    %>
    <script>
  	var appId='<%=com.yeepay.g3.app.lmweChat.service.RequestParamBuilderService.APP_ID%>';
  //var appId='wx196f101900ebc50b';
    var timestamp='<%=params.get("time") %>'; // 生成签名的时间戳
    var nonceStr='<%=params.get("randomStr")%>'; // 生成签名的随机串
    var signature='<%=params.get("signature")%>'; // 签名，见附录1
    var shareUrl='<%=shareUrl%>';
    share(appId,timestamp,nonceStr,signature,shareUrl);
    </script>
    <script>
  	$(document).ready(function() {
  	  $("#shareFriend").fadeOut(4000);
  	});
    </script>
    <title>新手专区活动页</title>
</head>
<body>
<div id="box" class="bg-white">
	<!--分享至-->
    <div id="shareFriend" class="shareTo layout-wrap pa">
        <div class="pr">
            <img src="static/images/shareTo.png" class="repeatImg">
            <a href="javascript:void(0)" class="a-shareTo pa"></a>
        </div>
    </div>
    <!--分享至end-->
    <div class="pr">
        <img src="static/images/beginner-01.jpg" class="repeatImg">
        <img src="static/images/beginner-02.jpg" class="repeatImg">
    </div>
    <div class="pr">
        <img src="static/images/beginner-03.jpg" class="repeatImg">
        <e:if test="@flag == 'unland'">
        	<a href="account/toRegister" class="a-gift pa"></a>
        </e:if>
        <e:if test="@flag == 'unbind'">
        	<a href="account/card/toGOBindCard" class="a-gift pa"></a>
        </e:if>
        <e:if test="@flag == 'bind'">
        	<a href="account/toRegister" class="a-gift pa"></a>
        </e:if>
    </div>
    <div class="pr">
        <img src="static/images/beginner-04.jpg" class="repeatImg">
        <a href="fixed/toPurchaseProduct?productId=<e:property value="@productId"/>" class="a-interests pa"></a>
    </div>
    <div class="activityRule">
        <h2>活动规则</h2>
        <ul>
            <li><span>1、</span>满足以上条件后，将自动获得对应奖励</li>
            <li><span>2、</span>获得后，请在“我的懒猫”——“我的卡券”版块中查看所获卡券并详细了解各种卡券的使用规则</li>
            <li><span>3、</span>此活动最终解释权归懒猫金服所有</li>
        </ul>
    </div>
    <e:if test="${empty platform }">
	    <div class="pr">
	        <img src="static/images/beginner-05.jpg" class="repeatImg">
	        <img src="static/images/beginner-06.jpg" class="repeatImg">
	    </div>
    </e:if>
</div>
</body>
</html>