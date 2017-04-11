<%@page import="com.yeepay.g3.app.lmweChat.service.RequestParamBuilderService"%>
<%@page import="com.yeepay.g3.facade.activity.enums.ShareBizTypeEnum" %> 
<%@page import="com.yeepay.g3.app.lmweChat.utils.WXAPIUtils,java.util.Map" %>
<%@page import="com.yeepay.g3.app.lmweChat.utils.LmConstants" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
 <%@ taglib prefix="e" uri="/emvc-tags" %>
 <%
 String path = request.getContextPath();
 String basePath = request.getScheme() + "://"+ request.getServerName(); 
 if(request.getServerPort()!=80){
   basePath = basePath + ":" + request.getServerPort(); 
 }      
 basePath = basePath + path + "/";
 String sysVersion = LmConstants.sysVersion;
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head lang="en">
<base href=" <%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--优先使用 IE 最新版本和 Chrome-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, maximum-scale=1.0, initial-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <!--设置苹果工具栏颜色-->
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="static/css/LM-common.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/LM-app.css?v=<%=sysVersion %>">
    <script type="text/javascript" src="static/js/common/format_common.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="static/js/common/wxCommonShare.js?v=<%=sysVersion %>"></script>
    <%
    String url = "asset/toMyLM";
    if(request.getQueryString() != null){
      url = "asset/toMyLM?"+request.getQueryString();
    }
     Map params = WXAPIUtils.getParam(RequestParamBuilderService.APP_ID,RequestParamBuilderService.APP_SECRET, basePath+url); 
  /*  Map params = WXAPIUtils.getParam("wx196f101900ebc50b","d4624c36b6795d1d99dcf0547af5443d",basePath+url); */
    String shareUrl = basePath+"asset/toMyLM?recommendMemberNo="+(String)session.getAttribute("recommendMemberNo")+"&bizType="+ShareBizTypeEnum.ALL+"&srcNo="+(String)session.getAttribute("srcNo");
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
    <title>加入懒猫</title>
</head>
<body style="background: #fff7d3">
<div id="box" >
    <div class="unloginMask joinLm pr">
        <img src="static/images/join-01.jpg" class="repeatImg">
        <img src="static/images/join-02.jpg" class="repeatImg">
        <a href="account/toRegister?returnFlag=${ returnFlag}" class="btnReg pa"></a>
        <a href="account/toLogin?returnFlag=${ returnFlag}" class="btnLogin pa"></a>
    </div>
</div>
</body>
</html>