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
   <link rel="stylesheet" href="static/css/fixed/bill.css?v=<%=sysVersion %>">
   <script type="text/javascript" src="static/js/common/bill_common.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/iscroll.js?v=<%=sysVersion %>"></script>
   <script type="text/javascript" src="static/js/fixed/billScroll.js?v=<%=sysVersion %>"></script>
   <script type="text/javascript" src="static/js/fixed/bill.js?v=<%=sysVersion %>"></script> 
   <title>定期理财账单</title>
</head>
<body>
<input type="hidden" id="totalPage" value="" />
<div id="box" class="scb">
    <div class="layout-wrap scbCard font-white tc pt30">
        <div class="layou-up assetArea">
            <a  class="font-white">
                <span class="font-45"><e:property value="@_formater.formatNumber(purchasedAmountResult)"/></span>
                <p class="font-14">定期理财总额（元）</p>
            </a>
        </div>
        <div class="layou-down tc clearfix">
            <a class="layou-down-left" >
                <span class="font-20"><e:property value="@_formater.formatNumber(totalIncomeFixed)"/></span>
                <p class="font-10">&nbsp;&nbsp;&nbsp;未到账收益（元）</p>
            </a>
            <a class="layou-down-right">
                <span class="font-20"><e:property value="@_formater.formatNumber(fixedIncomeResult)"/></span>
                <p class="font-10">&nbsp;&nbsp;&nbsp;已到账收益（元）</p>
            </a>
        </div>
    </div>
    <div class="bg-white">
        <div class="tab_nav tabNav">
            <ul>
                <li title="noArrival" id="noArrival" class="on" >未到账</li>
                <li title="arrival" id="arrival">已到账</li>
            </ul>
        </div>
    </div>
  <div class="tab_con pb15">
	<div id="wrapper" class="clearfix" style="bottom:0.1rem;">
  <div id="scroller" >
  </div>
  </div>
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
<a class="service" href="focus/toContactService"><i class="icon icon-service font-white"></i></a>
</body>
</html>

