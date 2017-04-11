<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
   <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head>
    <script type="text/javascript" src="static/js/assets/recharge.js"></script>
    <title>充值</title>
</head>
<body>
<input id="BUY_AMOUNT" type="hidden" value="<e:property value="@BUY_AMOUNT"/>" />
<input id="EXPECT_INCOME" type="hidden" value="<e:property value="@EXPECT_INCOME"/>" />
<input id="PRO_NAME" type="hidden" value="<e:property value="@PRO_NAME"/>" />
<input id="PRO_NO" type="hidden" value="<e:property value="@PRO_NO"/>" />
<input id="promoType" type="hidden" value="<e:property value="@promoType"/>" />
<input id="addRate" type="hidden" value="<e:property value="@addRate"/>" />
<input id="PROMO_NO" type="hidden" value="<e:property value="@PROMO_NO"/>" />
<input id="YEAR_RATE" type="hidden" value="<e:property value="@YEAR_RATE"/>" />
<input type="hidden" id="restrict" value="<e:property value="@RESULT.singleQuato"/>">
<input type="hidden" id="ret" value="<e:property value="@RET"/>">
<input type="hidden" id="status" value="<e:property value="@RESULT.status"/>">
<input id="expectType" type="hidden" value="<e:property value="@expectType"/>" />
<input id="orderNum" type="hidden" value="<e:property value="@orderNum"/>" />
<input id="uuid" type="hidden" value="<e:property value="@uuid"/>" />
<input type="hidden" id="token" name="token" value="<e:property value="@token"/>"/>

<div id="box" class="bg-white">
<div id="title"><h2 class="titleWithdraw orange br-bottom">充值到懒猫账户</h2></div>
    <div class="input-group withdraw-input-group">
        <ul>
            <li> 
                <div class="input-wrap">
                   <div class="bankImg bank<e:property value="@BANK_ID"/>"></div>
                    <label class="bankLabel"><e:property value="@BANK_LOGO_NAME"/>卡(<e:property value="@CARD_NO"/>)</label>
                </div>
                
                <e:if test="@RESULT.status.toString().equals('OFF')">
                 <div class="error-tips red"><i class='icon icon-tip'><e:property value="@RESULT.memo"/></i></div>
                </e:if>
                <e:else>
                  <p class="gray">
                                                            最低支付限额：<e:property value="@_formater.formatNumber(RESULT.singleLowerQuato) "/>元，
                                                            单笔限额：<e:if test="@RESULT.singleQuato>9999"><e:property value="@Math.round(RESULT.singleQuato/10000) "/>万</e:if>
                  <e:else><e:property value="@Math.round(RESULT.singleQuato/1000)"/>千</e:else>，单日限额：
                  <e:if test="@RESULT.dayQuato>9999"><e:property value="@Math.round(RESULT.dayQuato/10000)"/>万</e:if>
                  <e:else><e:property value="@Math.round(RESULT.dayQuato/1000)"/>千</e:else> ，单月限额：
                  <e:if test="@RESULT.monthQuato>9999"><e:property value="@Math.round(RESULT.monthQuato/10000)"/>万</e:if>
                  <e:else><e:property value="@Math.round(RESULT.monthQuato/1000)"/>千</e:else>
                  </p>
                </e:else>
                
            </li>
            <li id="amountLi">
                <div class="input-wrap">
                    <a href="javascript:void(0)" id="errorAmount" onclick="amountClick()" class="icon icon-error fr" style="display: none"></a>
                    <label>充值金额</label>
                   <span class="span"> <input type="text" class="input-text" id="amount" onblur="verifyAmount()" name="amount" value="<e:property value="@AMOUNT"/>" placeholder="请输入充值金额"/></span>
                </div>
                    <div class="error-tips red" id="amountMessage"> </div>
                <p id="fixedInfo" class="orange"></p>
            </li>
            <li id="pwdLi">
                <div class="input-wrap">
                    <a href="javascript:void(0)" id="look" onclick="changePasswordType('tradePwd')" class="icon icon-unlook fr"></a>
                    <a href="javascript:void(0)" id="errorPwd" onclick="pwdClick()"  class="icon icon-error fr" style="display: none"></a>
                    <label>交易密码</label>
                    <span><input type="password" class="input-text" id="tradePwd" name="" placeholder="请输入交易密码"/></span>
                </div>
                <div class="error-tips red">
                    <a href="account/toResetTradePwd" class="forgetCode orange fr">忘记密码</a>
                    <i id="pwdMessage" ></i> 
                </div>
            </li>
            <li class="pt30">
                <e:if test="${empty RET }">
	                <input type="button" class="btn-login-gray" id="btn-login" name="btn-login" disabled="disabled" value="确认充值"/>
                </e:if>
                <e:if test="${!empty RET }">
	                <input type="button" class="btn-login" id="btn-login" name="btn-login" value="确认充值"/>
                </e:if>
            </li>
        </ul>
    </div>
</div>
<!--弹出层-->
<div id="mask" style="display: none"></div>
<!------------------充值成功-进度条---------------->
<div id="alertLayer-3" class="unloginMask regMask" style="display: none; width: 100%; height:40%;">
    <div class="pr">
        <img src="static/images/rechargeSuccessMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed font-white pa" href="javascript:void(0)"></a>
        <!--充值成功-进度条-->
        <div id="buyType" style="display: none;" class="progressArea pa">
            <div id="progressBar-2" class="mt50">
                <div class="finish-2"></div>
            </div>
            <p class="font-16 orange mt20 tc">正在进入确认购买页面</p>
        </div>
        <!--充值成功-进度条 end-->
    <!--     充值成功-按钮 -->
        <div id="common" style="display: none;" class="btnMaskArea tc pa">
            <a href="asset/toAsset">查看我的财富</a>
            <a href="popularize/toPopularize">购买理财</a>
        </div>
  <!--       充值成功-按钮 end -->
    </div>
</div>
<!-- 充值处理中  -->
<div class="alertLayer-10 alertLayer" style="display: none; width: 100%; top:20%">
    <div class="loading">
        <img src="static/images/loading-recharge.gif" class="loadingImg" width="130" height="126"/>
        <p class="font-18 tc font-white">充值中，请耐心等待哦<img src="static/images/ellipsis.gif" class="ellipsisImg"/></p>
    </div>
</div>
<!-- 充值处理中 end -->

<!------------------充值失败-start------------------>
<!-- <div id="alertLayer-4" class="unloginMask regMask rechargeMask" style="display: none; width: 100%; height:40%;">
    <div class="pr">
        <img src="static/images/rechargeFailMask.png" class="repeatImg" alt=""/>
          <a class="btnClosed font-white pa" href="javascript:void(0)">
          	<i onclick="cashInAgain()" class="icon icon-error2"></i>
          </a>
        充值失败弹窗超额
        <div id="rechargeLimit" style="display: none;">
            <p class="errorCon red pa">充值失败:银行限额超限</p>
            <div class="btnMaskArea tc pa">
                <a href="javascript:void(0)" onclick="buyAgain()">重新购买</a>
                <a href="#">享大额充值特权</a>
            </div>
        </div>
        充值失败弹窗超额-按钮 end
        充值失败弹窗重新购买
        <div id="buyTypeFailed" style="display: none;">
            <p id="buyTypeFailedError" class="errorCon red pa"></p>
            <div class="btnMaskArea tc pa">
                <a href="javascript:void(0)" onclick="buyAgain()">重新购买</a>
            </div> 
        </div>
        充值失败弹窗重新购买-按钮 end
        充值失败弹窗重新充值
        <div id="commonFailed" style="display: none;">
            <p id="commonFailedError" class="errorCon red pa"></p>
            <div class="btnMaskArea tc pa">
                <a href="javascript:void(0)" onclick="cashInAgain()">重新充值</a>
            </div>
        </div>
        充值失败弹窗重新充值-按钮 end
    </div>
</div>
系统异常-弹出层
<div id="alertLayer-8" class="unloginMask regMask rechargeMask" style="display: none; width: 100%; height:40%;">
    <div class="pr">
        <img src="static/images/errorMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed font-white pa" href="javascript:void(0)"><i class="icon icon-error2" onclick="cashInAgain()"></i></a>
        <p class="errorCon red pa">系统异常，请稍后重试哦</p>
        <div class="btnMaskArea tc pa">
            <a href="javascript:void(0)" onclick="cashInAgain()">OK</a>
        </div>
    </div>
</div> -->
<div class="alertLayer-11 alertLayer unloginMask regMask" style="display: none; width: 100%; height:40%; top:20%">
    <div class="pr">
        <img src="static/images/rechargeAcceptMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed font-white pa" href="javascript:void(0)"></a>
        <div class="btnMaskArea tc pa">
        	<a href="asset/toAsset">查看我的财富</a>
            <a href="popularize/toPopularize">购买理财</a>
        </div>
    </div>
</div>
<%@include file = "../inc/failMask.jsp" %>
<!------------------充值失败-end------------------>
<a class="service" href="focus/toContactService"><i class="icon icon-service font-white"></i></a>
</body>
</html>
