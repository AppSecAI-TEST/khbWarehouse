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
<script type="text/javascript" src="static/js/scb/scb.js?v=<%=sysVersion %>"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="static/js/common/wxCommonShare.js?v=<%=sysVersion %>"></script>
    <%
//     String url = "asset/toMyLM";
    String url = "scb/toScb";
    if(request.getQueryString() != null){
      url = "scb/toScb?"+request.getQueryString();
    }
     Map params = WXAPIUtils.getParam(RequestParamBuilderService.APP_ID,RequestParamBuilderService.APP_SECRET, basePath+url); 
//     Map params = WXAPIUtils.getParam("wxe27267ccc8d4808e","7e63b6630406ca967d087e7dc7db4793",basePath+url);
    String shareUrl = basePath+"popularize/toPopularize?recommendMemberNo="+(String)session.getAttribute("recommendMemberNo")+"&bizType="+ShareBizTypeEnum.LINE+"&srcNo="+(String)session.getAttribute("srcNo");
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
<title>生财宝</title>
</head>
<body>
    <input type="hidden" id="scbSwitch" name="scbSwitch" value="<e:property value="@scbSwitchMap.get('scbSwitch')"/>"/>
    <input type="hidden" id="startDate" name="startDate" value="<e:property value="@scbSwitchMap.get('startDate')"/>"/>
    <input type="hidden" id="endDate" name="endDate" value="<e:property value="@scbSwitchMap.get('endDate')"/>"/>
	<div class="scb">
		<!--分享至-->
		<e:if test="@platform==WX">
	    <div id="shareFriend" class="shareTo layout-wrap pa">
	        <div class="pr">
	            <img src="static/images/shareTo.png" class="repeatImg">
	            <a href="javascript:void(0)" class="a-shareTo pa"></a>
	        </div>
	    </div>
	    </e:if>
	    <!--分享至end-->
		<div class="layout-wrap scbCard font-white tc">
			<div class="layou-up">
				<a href="scb/toScbDetail" class="btn-pro">产品介绍</a> <a class="font-white">
					<p>华夏财富宝货币市场基金</p>
					<p>（000343）</p> <span class="font-50" id=lastestIncome></span>
					<p class="font-12">昨日收益（元）</p>
				</a>
			</div>
			<div class="layou-down tc clearfix">
				<a class="layou-down-left"> <span class="font-20"
					id="totalBalance"></span>
					<p class="font-10">&nbsp;&nbsp;&nbsp;持有资产（元）</p>
				</a> <a class="layou-down-right" href="scb/toScbAccumulate"> <span class="font-20"
					id="accumulativeIncome"></span>
					<p class="font-10">&nbsp;&nbsp;&nbsp;累计收益（元）</p>
           <i class="icon-arrow triangle-right pa"></i>
				</a>
			</div>
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
		    <a href="scb/toScbBill" class="A-nav">
        <i class="icon icon-arrow-right fr"></i>
        交易记录
    </a>
</div>
<div class="h1-6"></div>
<div class="btn-group" id="isBuy" style="display: none">
    <input id="normalTranOut" type="button" value="转出" class="btn-inout btnClick-1"/>
    <input id="normalTranIn" type="button" value="转入" class="btn-inout btn-in btnClick-2" onclick="toTransferIn()"/>
</div>
<!-- <div class="btnBot" id="isBuy" style="display: none">
    <input id="normalTranIn" type="button" value="转入" class="btnBuy btnBuy-gray" onclick="toTransferIn()" disabled="true"/>
</div> -->
<div class="btnBot" id="noBuy" style="display: none">
    <input id="noBuyTranIn" type="button" value="转入" class="btnBuy btnClick-2" onclick="toTransferIn()"/>
    <!-- <input id="noBuyTranIn" type="button" value="转入" class="btnBuy btnBuy-gray" disabled="true" onclick="toTransferIn()"/> -->
</div>
	<!--弹出层-->
	<div id="mask"></div>
	<div id="alertLayer-1" class="bg-gray"
		style="display: none; width: 100%; min-height: 290px; left: 0; bottom: 0;">
		<div class="bg-white scbInTitle font-18">转出生财宝到懒猫账户</div>
		<div class="input-group withdraw-input-group">
			<ul>
				<li>
					<div class="input-wrap bg-white">
						<a class="orange mt15 fr" onclick="totalOut()">全部转出</a> <a
							style="display:none" id="transferOutDelete" onclick="transferOutDelete()" class="icon icon-error fr"></a> <label>转出金额</label>
						<input type="text" class="input-text" id="transferOutInput"
							placeholder="请输入转出金额" onblur="transferOutOnBlur()"/>
					</div>
					<div id="outText"><p class="orange">本次最多转出<span id="mostBalance"></span>元</p></div>
				</li>
			</ul>
		</div>
		<div class="bg-white scbInTitle">
			<p class="gray">快速转出: 日限额: 10万, 月限额: 30万</p>
			<p  style="display: none" id="transferOutTime">
				此笔转出为<span class="orange" id="redeemType">快速转出</span>，预计 <span class="orange" id="redeemTime">3月24日</span>
				到账
			</p>
		</div>
		<div class="btnLayer-group font-white">
			<input type="button" value="取消" class="btn-gray btnClosed" onclick="transferOutOff()"/> <input
				type="button" value="确认转出" onclick="transferOut()"/>
		</div>
	</div>
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
							placeholder="1 元起购" />
					</div>
					<div id="inText"><p class="orange">请直接输入转入金额，账户余额不足将先进行充值</p></div>
				</li>
			</ul>
		</div>
		<div class="bg-white scbInTitle">
			<p id="userBalance">
				账户余额: <span class="orange" id="accountBalanceResult"></span>
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
	<e:if test="@FLAG=='ISFIRST'">
		<div id="financialMask">
			<a href="javascript:void(0)" class="a-know"><img id="scbMask" src="static/images/scbMask.png"></a>
		</div>
    </e:if>
    <a class="service" href="focus/toContactService"><i class="icon icon-service font-white"></i></a>
</body>
</html>