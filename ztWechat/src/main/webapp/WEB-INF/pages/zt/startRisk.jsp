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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>风险测评</title>
<link rel="stylesheet" href="static/css/LM-common.css?v=<%=sysVersion %>">
<link rel="stylesheet" href="static/css/LM-app.css?v=<%=sysVersion %>">
<link rel="stylesheet" href="static/css/LM-funds.css?v=<%=sysVersion %>">
<script type="text/javascript" src="static/js/common/format_common.js?v=<%=sysVersion %>"></script>
<script type="text/javascript">
function unBinkCard(){
	$("#alertLayer-5Message").html("对不起，您尚未绑定银行卡，需绑定银行卡后再进行基金的购买操作。");
	$("#mask").show();
		$("#alert").show();
}
//关闭弹出框
function clean(){
	  $("#mask").hide();  
	  $("#alert").hide();
	  $("#alert7").hide();
	  $("#alert6").hide();
	}

function goBinkCard(){
	var hiddenLanmao = document.getElementById("addr").value;
	location.href= hiddenLanmao;
}
function checkInstructionStatus(){
	if($('#agreeBox').is(':checked')){
	  var hiddenSceneId = document.getElementById("hiddenSceneId").value;
	  var uri = "zt/risk/toRiskTest";
	  var parmas = "sceneId=" + hiddenSceneId;
	  location.href= uri + "?" + parmas ;
	}else{
		$('#tips').html('<i class="icon icon-error2"></i> 请阅读并同意相关协议');
	}
}

function jumpRiskTest(){
        var riskLevel = $("#riskLevel").val();
        if(riskLevel == '') {
          riskLevel="保守型";
        }
        
		$("#alertLayer-6Message").html("跳过风险测评平台将默认您为"+riskLevel+"投资者，是否确认跳过测评？");
		$("#mask").show();
		$("#alert6").show();
}

//跳过风险测评
function toJumpRiskTest(){
	
	var hiddenSceneId = document.getElementById("hiddenSceneId").value;
	var uri = "zt/risk/goJumpRisk";
	var parmas = "sceneId=" + hiddenSceneId;
	location.href= uri + "?" + parmas ;
	
}
</script>
</head>
<!-- 头部结束 -->
<body>
<div class="coverArea bg-white">
<input type="hidden" id="hiddenLanmao" name="hiddenLanmao" value="${lanmao}"/>
<input type="hidden" id="hiddenSceneId" name="hiddenSceneId" value="${sceneId}"/>
<input type="hidden" id="riskLevel" name="riskLevel" value="${riskLevel}"/>
<input type="hidden" id="addr" name="addr" value="${addr}"/>
  <div class="coverContent">
    <div class="evaluation">投资者风险承受能力评测</div>
    <div class="evaluationHeader">测一下你是哪种类型投资者？</div>
    <div class="evaluationImg">
        <img src="static/images/fund/evaluationImg.png" alt=""/>
    </div>
    <e:if test="${isBinkCard == 1}">
	    <div class="skip">
	        <a onClick="jumpRiskTest()">
		        <e:if test="${empty riskLevel }">
		       	 我是保守型投资者，跳过测试直接交易
		       	 </e:if>
	       	 	<e:if test="${!empty riskLevel }">
		       	 我是${riskLevel }投资者，跳过测试直接交易
		       	 </e:if>
	        <img src="static/images/fund/evaluationArrow.png" alt=""/></a>
	    </div>
	    <div class="evaluationBtn">
	        <a id='start' onClick="checkInstructionStatus()" href="javascript:void(0)">开始测试</a>
	    </div>
    </e:if>
    <e:if test="${isBinkCard == 0}">
	    <div class="skip">
	        <a href="javascript:void(0)" onClick="unBinkCard()">
	        	<e:if test="${empty riskLevel }">
		       	 我是保守型投资者，跳过测试直接交易
		       	 </e:if>
	       	 	<e:if test="${!empty riskLevel }">
		       	 我是${riskLevel }投资者，跳过测试直接交易
		       	 </e:if>
	        <img src="static/images/fund/evaluationArrow.png" alt=""/></a>
	    </div>
	    <div class="evaluationBtn">
	        <a id='start' href="javascript:void(0)" onClick="unBinkCard()">开始测试</a>
	    </div>
    </e:if>
    <div class="checkedBox evaluationBox">
        <input type="checkbox" class="agree-box" id="agreeBox" checked="checked"/>
        <i class="icon icon-checkbox orange"></i>
                         已阅读并同意遵守 <a href="zt/risk/riskInstruction" class="orange">《投资者风险承受能力评测说明》</a>
        <a href="zt/risk/riskProtocol" class="orange">《懒猫金服基金网上交易协议》</a>
        <div id='tips' class="error-tips red"></div>
    </div>
    </div>
</div>
<div class="prompt">
    <img src="static/images/fund/prompt.png" alt=""/>
</div>
<div id="mask" style="display: none"></div>
<div id="alert" class="alertLayer"
		style="display: none; width: 100%; top:20%;">
		<div class="bg-white radius1" style=" width: 90%; margin:0 auto">
		<div class="pt30 pb30 br-bottom orange tc font-14"
			id="alertLayer-5Message" style="margin:10px 20px 10px 20px;text-align:left;"></div>
		<div class="btnMaskArea tc mt30">
			<a href="javascript:void(0)" onclick="goBinkCard()">去绑卡</a> <a
				href="javascript:void(0)" onClick="clean()">取消</a>
		</div>
		</div>
	</div>
	
	<div id="alert6" class="alertLayer" 
		style="display: none; width: 100%; top:20%; ">
		<div class="bg-white radius1" style=" width: 90%; margin:0 auto">
			<div class="pt30 pb30 br-bottom orange tc font-16"
				id="alertLayer-6Message" style="margin:10px 20px 10px 20px;text-align:left;">
			</div>
			<div class="btnMaskArea tc mt30">
				<a href="javascript:toJumpRiskTest();">跳过</a> <a
					href="javascript:void(0)" onclick="clean()">取消</a>
			</div>
		</div>
	</div>
	
	<div id="alert7" class="alertLayer"
		style="display: none; width: 100%; top:20%;">
		<div class="bg-white radius1" style=" width: 90%; margin:0 auto">
		<div class="pt30 pb30 br-bottom orange tc font-16"
			id="alertLayer-7Message" style="margin:10px 20px 10px 20px;text-align:left;"></div>
		<div class="btnMaskArea tc mt30">
			<a href="javascript:toJumpRiskTest();">跳过</a>
			<a href="javascript:void(0)" onclick="clean()">取消</a>
		</div>
		</div>
	</div>
</body>
</html>