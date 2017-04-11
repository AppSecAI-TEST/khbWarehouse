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
<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--优先使用 IE 最新版本和 Chrome-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, maximum-scale=1.0, initial-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <!--设置苹果工具栏颜色-->
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
   <%--  <link rel="stylesheet" href="static/css/LM-common.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/LM-app.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/LM-funds.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/icon-style.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/LM-invest.css?v=<%=sysVersion %>">
    <script type="text/javascript" src="static/js/jquery-1.8.3.min.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/LM-app.js?v=<%=sysVersion %>"></script> --%>
    <title>赎回成功</title>
</head>
<body>
<div id="box" class="bg-white">
    <div class="pr">
        <img src="static/images/invest/redeemSuccess.jpg" class="repeatImg">
        <span class="red redeemSum pa"><e:property value="@_formater.formatNumber(buyMoney)"/> 元</span>
        <div class="tipArea br-bottom tc">
            <p class="light-gray font-text-note">由于您进行了赎回，为了达成您的<e:property value="@ztSceneDto.sceneName"/>愿望</p>
            <p class="orange font-text-sm">从下月开始，每月需申购<e:property value="@_formater.formatNumber(newPerInvestAmount)"/>元</p>
        </div>
        <div class="mb15">
            <div class="flowList pr">
                <span class="bg-t pa"></span>
                <span class="bg-b pa"></span>
                <ul class="applyFlow">
                    <li class="on">
                        <i class="icon icon-circle"></i> 赎回申请提交，待基金公司确认
                    </li>
                    <li>
                        <i class="icon icon-circle"></i> 基金公司确认赎回份额
                        <p><e:property value="@_formater.formatDate(confirmDate,'dateonly')"/></p>
                        <p>各基金公司略有不同</p>
                    </li>
                    <li><i class="icon icon-circle"></i> 资金全部到账
                        <p>份额确认后1-2个工作日</p>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="btnArea">
        <a class="singleBtn first fl" href="zt/asset/toMyPolicyInvest">我的灵机一投</a>
        <a class="singleBtn fl" href="zt/introduce/sceneList">继续购买</a>
    </div>
</div>
</body>
</html>