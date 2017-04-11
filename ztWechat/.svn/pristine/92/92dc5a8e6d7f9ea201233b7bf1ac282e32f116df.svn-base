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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--优先使用 IE 最新版本和 Chrome-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, maximum-scale=1.0, initial-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <!--设置苹果工具栏颜色-->
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="static/css/LM-common.css">
    <link rel="stylesheet" href="static/css/LM-app.css">
    <link rel="stylesheet" href="static/css/icon-style.css">
    <script type="text/javascript" src="static/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="static/js/LM-app.js"></script>
    <title>提现在途金额</title>
</head>
<body>
<div id="box" class="bg-white">
    <div class="myassetCard font-white tc pr">
        <p><span class="font-tit-wealth"><e:property value="@_formater.formatNumber(freezeAmount)"/></span></p>
        <p class="font-tit-sm pt30">提现在途金额（元）</p>
    </div>
    <div class="ontheWay">
        <img src="static/images/ontheWay.png">
        <p class="tc">提现金额将在发起提现后下一个工作日内到账</p>
    </div>
</div>
</body>
</html>