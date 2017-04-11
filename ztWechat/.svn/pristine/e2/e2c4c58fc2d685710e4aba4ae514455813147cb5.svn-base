<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head>
    <script type="text/javascript" src="static/js/scb/transfer.js"></script>
    <title>生财宝确认转出</title>
</head>
<body>
<div id="box">
    <div class="buyCard bg-white pr">
        <ul class="buyInfo">
            <li><i class="dot orange font-10">●</i> 购买产品：<span class="font-20">生财宝</span></li>
            <li><i class="dot orange font-10">●</i> 转出金额：<span class="orange font-25" id="totalBalance"><e:property value="@totalBalance"/></span> 元</li>
        </ul>
        <p class="scbText font-white pa">此笔转出为<span class="font-12"><e:property value="@reedemType"/></span>，预计<span class="font-12"><e:property value="@redeemTime"/></span>到账</p>
        <img src="static/images/bg-buy.png">
    </div>
    <div class="input-group withdraw-input-group">
        <ul>
            <li class="input-wrap-error">
                <div class="input-wrap bg-white">
                    <a id="look" class="icon icon-unlook fr"></a>
                    <a id="delete" style="display: none" class="icon icon-error fr"></a>
                    <label>交易密码</label>
                    <span><input type="password" class="input-text" id="pwd" name="" placeholder="请输入交易密码"/></span>
                </div>
                <div class="error-tips red">
                    <a href="account/toResetTradePwd" class="forgetCode orange fr">忘记密码</a>
                    <div id="errorMsg"></div>
                </div>
            </li>
            <li class="pt30">
                <input type="button" class="btn-login" id="btn-transferOut" name="" value="确认转出"/>
                <!--<input type="button" class="btn-login" id="" name="" value="确认转出"/>-->
            </li>
        </ul>
    </div>
</div>
	<!--转出失败-弹出层-->
<div id="mask" style="display: none"></div>
<!-- <div id="alertLayer-3" class="unloginMask regMask withdrawMask" style="display: none; width: 100%; height:40%;">
    <div class="pr">
        <img src="static/images/roll-outFailMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed font-white pa" href="javascript:void(0)"><i class="icon icon-error2"></i></a>
        <p class="errorCon red pa">请稍后重试哦~</p>
        <div class="btnMaskArea tc pa">
            <a href="scb/toScb">重新转出</a>
        </div>
    </div>
</div> -->
<%@include file = "../inc/failMask.jsp" %>
</body>
</html>