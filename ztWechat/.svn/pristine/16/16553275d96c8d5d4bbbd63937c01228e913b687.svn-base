<%@page import="com.yeepay.g3.app.lmweChat.service.RequestParamBuilderService"%>
<%@page import="com.yeepay.g3.facade.activity.enums.ShareBizTypeEnum" %> 
<%@page import="com.yeepay.g3.app.lmweChat.utils.WXAPIUtils,java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.yeepay.g3.app.lmweChat.utils.LmConstants" %>
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
<head>
<script type="text/javascript" src="static/js/highstock-min.js?v=<%=sysVersion %>"></script>
<script type="text/javascript" src="static/js/fund_detail_current.js?v=<%=sysVersion %>"></script>
<script type="text/javascript" src="static/js/scb/unSignUpScb.js?v=<%=sysVersion %>"></script>
<title>生财宝</title>
</head>
<body>
<input type="hidden" id="scbSwitch" name="scbSwitch" value="<e:property value="@scbSwitchMap.get('scbSwitch')"/>"/>
<input type="hidden" id="startDate" name="startDate" value="<e:property value="@scbSwitchMap.get('startDate')"/>"/>
<input type="hidden" id="endDate" name="endDate" value="<e:property value="@scbSwitchMap.get('endDate')"/>"/>
<input type="hidden" id="userStatus" value="<e:property value="@userStatus"/>">
<!-- 	<div class="scb"> -->
 <div class="layout-wrap scbCard font-white tc">
        <div class="layou-up br-none">
            <a href="scb/toScbDetail" class="btn-pro">产品介绍</a>
            <a class="font-white">
                <p>华夏财富宝货币市场基金</p>
                <p>（000343）</p>
            </a>
        </div>
        <div class="layou-down tc clearfix pb15">
            <a class="layou-down-left">
                <span class="font-25"><e:property value="@lastestRateForSevenDay"/></span>
                <p class="font-10">7日年化收益率</p>
            </a>
            <a class="layou-down-right">
                <span class="font-25"><e:property value="@lastestIncomeOfDay"/></span>
                <p class="font-10">&nbsp;&nbsp;&nbsp;万份收益（元）</p>
            </a>
        </div>
    </div>
    <div class="scbPic clearfix pr mt10">
        <img src="static/images/scb-ad.jpg">
    </div>
		<!-- 曲线图 -->
		<div class="chart-wrap">
			<div class="chart-top">
				<div class="chart-nav">
					<a href="javascript:;" data-profit_type="2" class="on" >近七日年化(%)</a> <a
						class="" href="javascript:;" data-profit_type="1">万份收益(元)</a>
				</div>
			</div>
			<div class="chart-cnt">
				<div class="chart-info" id="messageBox"></div>
				<div class="chart" id="chart">
					<div class="highcharts-container" id="highcharts-0"></div>
				</div>
				<div class="chart-tab">
					<a href="javascript:;" data-duration="7" class="on">7天</a> <a
						href="javascript:;" data-duration="30" class="">1个月</a> <a
						class="" href="javascript:;" data-duration="60">2个月</a>
				</div>
			</div>
		</div>
		<!-- 曲线图end -->
<!-- </div> -->
<div class="h1-6"></div>
<!-- 按鈕不可点，并且置灰 -->
<div class="btnBot"><input id="noBuyTranIn" type="button" value="转入" class="btnBuy" onclick="toTransferIn()"/></div>
<!-- <div class="btnBot"><input type="button" value="暂停业务" class="btnBuy btnBuy-gray" onclick="toTransferIn()" disabled="true"/></div> -->
	<!--弹出层-->
	<div id="mask"></div>
	<div id="alertLayer-2" class="bg-gray"
		style="display: none; width: 100%; min-height: 300px; left: 0; bottom: 0;">
		<div class="bg-white scbInTitle font-18">买入华夏基金财富宝</div>
		<div class="input-group withdraw-input-group">
			<ul>
				<li>
					<div class="input-wrap bg-white">
						<a class="orange mt15 fr" onclick="totalIn()" id="totalIn">全部转入</a> <a
						 class="icon icon-error fr" style="display:none" id="transferInDelete" onclick="transferInDelete()"></a> <label>转入金额</label>
						<input type="text" class="input-text" id="transferInInput" 
							placeholder="1 元起投" />
					</div>
					<div id="inText"><p class="orange">请直接输入转入金额，账户余额不足将先进行充值</p></div>
				</li>
			</ul>
		</div>
		<div class="bg-white scbInTitle">
			<p id="userBalance">
				账户余额: <span class="orange" id="accountBalanceResult"><e:property value="@accountBalanceResult"/></span>
			</p>
			<p>
				预计<span class="orange" id="firstIncomeDay"></span>产生收益，<span class="orange" id="incomingDay">2月24日</span>收益到账
			</p>
		</div>
		<div class="btnLayer-group font-white">
			<input type="button" value="取消" class="btn-gray btnClosed" onclick="transferInOff()"/> <input
				type="button" value="确认转入" onclick="transferIn()"/>
		</div>
	</div>
	<!--弹出层-->
<!--未登录弹出层-->
<div id="alertLayer" class="unloginMask" style="display: none; width: 100%; height:100%;">
    <div class="pr">
        <img src="static/images/unloginMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed font-white pa" ><i class="icon icon-error2"></i></a>
        <a href="account/toRegister" class="btnReg pa"></a>
        <a href="account/toLogin?returnFlag=scb" class="btnLogin pa"></a>
    </div>
</div>
</body>
</html>