<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
    <script type="text/javascript" src="static/js/scb/transfer.js"></script>
    <title>生财宝确认购买</title>
</head>
<body>
<div id="box">
    <div class="buyCard bg-white pr">
        <ul class="buyInfo">
            <li><i class="dot orange font-10">●</i> 购买产品：<span class="font-20">生财宝</span></li>
            <li><i class="dot orange font-10">●</i> 购买金额：<span class="orange font-25" id="totalBalance"><e:property value="@totalBalance"/></span> 元</li>
        </ul>
        <p class="scbText font-white pa">预计<span class="font-12"><e:property value="@firstIncomeDay"/></span>产生收益，<span class="font-12"><e:property value="@incomingDay"/></span>收益到账</p>
        <img src="static/images/bg-buy.png">
    </div>
    <div class="input-group withdraw-input-group">
        <ul>
            <li class="" id="input-wrap-error">
                <div class="input-wrap bg-white">
                    <a id="look" class="icon icon-unlook fr"></a>
                    <a id="delete"  style="display: none" class="icon icon-error fr"></a>
                    <label>交易密码</label>
                    <span><input type="password" class="input-text" id="pwd" name="" placeholder="请输入交易密码"/></span>
                </div>
                <div class="error-tips red">
                    <a href="account/toResetTradePwd" class="forgetCode orange fr">忘记密码</a>
                    <div id="errorMsg"></div>
                </div>
            </li>
            <li class="pt30">
                <input type="button" class="btn-login" id="btn-transferIn" onclick="transferIn()" name="" value="确认购买"/>
            </li>
            <li>
            <div class="checkedBox pt10">
                <input type="checkbox" class="agree-box" id="agreeBox" checked="checked"/>
                <i class="icon icon-checkbox orange"></i>
                我已阅读并同意遵守<a href="scb/toScbProtocol" class="orange">《生财宝协议》</a>和<a href="scb/tohxProtocol" class="orange">《华夏基金管理有限公司电子交易直销前置式服务协议》</a>
            </div>
            </li>
        </ul>
    </div>
</div>
<!--转入失败-弹出层-->
<div id="mask" style="display: none"></div>
 <!------------------充值失败-进度条------------------>
<!-- <div id="alertLayer-3" class="unloginMask regMask withdrawMask" style="display: none; width: 100%; height:40%;">
    <div class="pr">
        <img src="static/images/roll-inFailMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed font-white pa" href="javascript:void(0)"><i class="icon icon-error2"></i></a>
        <p class="errorCon red pa">请稍后重试哦~</p>
        <div class="btnMaskArea tc pa">
            <a href="scb/toScb">重新转入</a>
        </div>
    </div>
</div> -->
<%@include file = "../inc/failMask.jsp" %>
<a class="service" href="focus/toContactService"><i class="icon icon-service font-white"></i></a>
</body>
</html>