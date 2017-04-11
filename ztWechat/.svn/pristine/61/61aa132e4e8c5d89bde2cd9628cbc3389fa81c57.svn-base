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
    <link rel="stylesheet" href="static/css/LM-tour.css">
    <script type="text/javascript" src="static/js/invForPro/touOrderList.js"></script>
    <title>我的订单</title>
</head>
<body>
<div  class="bg-white">
    <div class="tab_nav tabNav voucherNav orderNav">
        <ul>
            <li id="noFinish" title="noPaid" class="on">未完成</li>
            <li id="finish" title="paid">已完成</li>
        </ul>
    </div>
    <input id="type" type="hidden" value="<e:property value="@type"/>">
    <div class="h1-0"></div>
    <div class="tab_con pb15">
        <blockquote id="orderList" style="display: block">
        </blockquote>
    </div>
</div>
  <!--系统异常-弹出层-->
<div id="mask" style="display: none"></div>
<div id="alertLayer-8" class="unloginMask regMask rechargeMask" style="display: none; width: 100%; height:40%;">
    <div class="pr">
        <img src="static/images/errorMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed font-white pa" href="javascript:void(0)"><i class="icon icon-error2" onclick="clean()"></i></a>
        <p class="errorCon red pa">系统异常，请稍后重试哦</p>
        <div class="btnMaskArea tc pa">
            <a href="javascript:void(0)" onclick="clean()">OK</a>
        </div>
    </div>
</div>
</body>
</html>