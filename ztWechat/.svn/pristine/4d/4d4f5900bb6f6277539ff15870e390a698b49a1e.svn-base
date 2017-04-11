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
<!DOCTYPE html>
<html>
<head lang="en">
    <meta http-equiv='refresh' content='3;url=<%=basePath%>account/card/toBindCard'>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--优先使用 IE 最新版本和 Chrome-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, maximum-scale=1.0, initial-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <!--设置苹果工具栏颜色-->
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="static/css/LM-common.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/LM-app.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/icon-style.css?v=<%=sysVersion %>">
    <script type="text/javascript" src="static/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="static/js/LM-app.js?v=<%=sysVersion %>"></script>
    <title>绑卡</title>
</head>
<body>
<div id="box" class="bg-white">
    <div class="pr ">
        <img src="static/images/unTieCard.jpg" class="repeatImg">
    </div>
    <div class="layou-04">
        <div id="progressBar" class="mt50">
            <div class="finish"></div>
        </div>
        <p class="font-16 orange mt20 tc">正在进入绑卡页面</p>
    </div>
</div>
</body>
</html>