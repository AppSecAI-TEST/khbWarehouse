<%@page import="com.yeepay.g3.app.lmweChat.utils.LmConstants" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<%
  String sysVersion = LmConstants.sysVersion;
%>
<html>
<head lang="en">
<link rel="stylesheet" href="static/css/LM-tour.css?v=<%=sysVersion %>">
 <script type="text/javascript" src="static/js/fixed/buySuccess.js?v=<%=sysVersion %>"></script>
    <title>购买成功</title>
</head>
<body>
<input type="hidden" id="buyDay" value="<e:property value="@_formater.formatDate(pdfwxrDto.buyDay)"/>"> 
<input type="hidden" id="incomeDay" value="<e:property value="@_formater.formatDate(pdfwxrDto.incomeDay)"/>"> 
<input type="hidden" id="expireDay" value="<e:property value="@_formater.formatDate(pdfwxrDto.expireDay)"/>"> 
<input type="hidden" id="arrivalDay" value="<e:property value="@_formater.formatDate(pdfwxrDto.arrivalDay)"/>"> 
<input type="hidden" id="amount" value="<e:property value="@amount"/>"> 
<input type="hidden" id="productName" value="<e:property value="@pdfwxrDto.productName"/>"> 
<div class="bg-white">
    <div class="pr"><img src="static/images/tour/buysuccess-tour.jpg" class="repeatImg"/></div>
    <div class="buysuccessText">
        <ul>
            <li>购买产品：<e:property value="@pdfwxrDto.productName"/> 第<e:property value="@pdfwxrDto.periodNo"/>期</li>
            <li>所获线路：<e:property value="@invForProDto.activityInvForProInfoDTO.name"/> X<e:property value="@invForProDto.activityInvForProOrderDTO.num"/></li>
            <li>有效期限：产品购买日起1个月内有效</li>
        </ul>
        <p>注意事项：具体的出行日期及出行安排我们的工作人员会在2个工作日内与您联系确定，请保持您当前注册手机号码畅通。</p>
    </div>
    <div class="layou-04 mb0">
        <div class="br-bottom mt25 pb25 tc">
            <p class="font-18 orange">到期后预计到账金额<e:property value="@amount"/>元</p>
        </div>
    </div>
    <div class="flowList pr">
        <span class="bg-t pa"></span>
        <span class="bg-b pa"></span>
        <ul class="">
            <li id="buyDaySpan" class="on">  </li>
            <li id="expireDaySpan"></li>
            <li id="arrivalDaySpan"><i class="icon icon-circle"></i> 到期后两个工作日本金到账</li>
        </ul>
    </div>
    <div class="btnMaskArea tc mt5">
        <a href="asset/myTotalWealth">查看我的总资产</a>
        <a href="invForPro/toInvestForTravelList">继续购买</a>
    </div>
    <div class="h0-5"></div>
</div>
<!--购买成功-弹出层-->
<!--<div id="mask" style="display: block"></div>
 <div id="alertLayer-3" class="unloginMask regMask" style="display: block; width: 100%; height:40%;">
    <div class="pr">
        <img src="static/images/buySuccessMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed font-white pa" href="javascript:void(0)"><i class="icon icon-error2"></i></a>
        <div class="btnMaskArea tc pa">
            <a href="asset/myTotalWealth">查看我的财富</a>
            <a href="popularize/toPopularize">继续购买</a>
        </div>
    </div>
</div> -->
</body>
</html>