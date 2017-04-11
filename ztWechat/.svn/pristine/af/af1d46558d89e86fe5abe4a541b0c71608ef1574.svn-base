<%@page import="com.yeepay.g3.app.lmweChat.utils.LmConstants" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<%
  String sysVersion = LmConstants.sysVersion;
%>
<html>
<head lang="en">
    <link rel="stylesheet" href="static/css/layer.css?v=<%=sysVersion %>"/>
    <link rel="stylesheet" href="static/css/assets/bill_info.css?v=<%=sysVersion %>">
    <link href="static/css/calendar/mobiscroll_002.css?v=<%=sysVersion %>" rel="stylesheet" type="text/css">
    <link href="static/css/calendar/mobiscroll.css?v=<%=sysVersion %>" rel="stylesheet" type="text/css">
    <link href="static/css/calendar/mobiscroll_003.css?v=<%=sysVersion %>" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="static/js/iscroll.js?v=<%=sysVersion %>"></script>
     <script type="text/javascript" src="static/js/assets/billInfoScroll.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/assets/bill_info.js?v=<%=sysVersion %>"></script> 
    <script src="static/js/calendar/mobiscroll_002.js?v=<%=sysVersion %>" type="text/javascript"></script>
    <script src="static/js/calendar/mobiscroll_004.js?v=<%=sysVersion %>" type="text/javascript"></script>
    <script src="static/js/calendar/mobiscroll.js?v=<%=sysVersion %>" type="text/javascript"></script>
    <script src="static/js/calendar/mobiscroll_003.js?v=<%=sysVersion %>" type="text/javascript"></script>
    <script src="static/js/calendar/mobiscroll_005.js?v=<%=sysVersion %>" type="text/javascript"></script>
    <title>账户明细</title>
</head>
<body>
<input type="hidden" id="totalPage" value="" />
<div id="box">
     <div class="date-bar">
        <div class="dateArea">
         <span class="time-label">起止时间</span>
         <a class="input-time-wrap">
             <span class="icon icon-date"></span>
             <input type="date" placeholder="2015-07-01" class="input-time timebegin" id="appDateBegin" readonly="readonly"/>
         </a>
         <span >-</span>
         <a class="input-time-wrap">
             <span class="icon icon-date"></span>
             <input type="date" placeholder="2015-07-01" class="input-time timeend" readonly="readonly" id="appDateEnd"/>
         </a>
         </div>
         <p class="aSee">如需查询2016年3月之前的全部交易记录，<a href="old/toRecord" class="orange">请点此查询</a></p>
     </div>
     <div class="accountList bg-white clearfix">
	<div id="wrapper" >
  <div id="scroller" >
  </div>
  </div>
     </div>
     <!--<p class="loadingArea gray tc">亲～已无更多明细了</p>-->
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