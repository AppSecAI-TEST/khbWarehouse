<%@page import="com.yeepay.g3.app.lmweChat.utils.LmConstants" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<%
  String sysVersion = LmConstants.sysVersion;
%>
<html>
<head lang="en">
    <script type="text/javascript" src="static/js/account/common_account.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/account/add_card.js?v=<%=sysVersion %>"></script>
    <title>绑定银行卡</title>
</head>
<body>
<div class="bg-white">
<form action="" id="forms">
     <a href="account/card/toBankLimit" class="titleInfo quotaInfo block">
        <i class="icon icon-arrow-right font-white fr"></i>
        <h2>支持银行及限额</h2>
        <p>推荐使用民生银行借记卡</p>
    </a>
    <div class="titleInfo">
        <h2>设置交易密码</h2>
        <p>交易密码在进行资金交易时可以有效保障您的资金安全</p>
    </div>
    <div class="input-group" id="" onblur="">
        <ul>
            <li id="setup-pwd-li">
                <div class="input-wrap">
                    <a href="javascript:lookPwd('tradePwd','请输入交易密码');" id="look-tradePwd" class="icon icon-unlook fr"></a>
                    <!-- <a href="javascript:void(0)" id="look-pwd" class="icon icon-look orange fr"></a> -->
                    <a id="trdClear" class="icon icon-error fr" style="display: none"></a>
                    <i class="icon icon-password"></i>
                    <span id="lookBox-tradePwd" class="span">
                      <input type="password" class="input-text" id="tradePwd" name="tradePwd" maxlength="20" placeholder="请输入交易密码" onfocus="pwdLook();" onkeyup="checkFormButton('keyUp',this)" onblur="checkFormButton('onblur',this)"/>
                    </span>
                </div>
                <div id="error-setup-pwd" class="error-tips red"></div>
            </li>
        </ul>
    </div>
    <div class="titleInfo br-top">
        <h2>绑定银行卡</h2>
        <p>为保障您的资金安全，只支持绑定一张银行卡且只能提现至此卡</p>
    </div>
    <div class="input-group">
        <ul>
        <div id="messageBox" class="tc red pb10"></div>
            <li id="nameError">
                <div class="input-wrap">
                    <a  class="icon icon-error fr" style="display: none"></a>
                    <i class="icon icon-name"></i>
                    <span class="span">
                      <input type="text" class="input-text" id="realName" name="realName" placeholder="请输入姓名" onkeyup="checkFormButton('keyUp',this)" onblur="checkFormButton('onblur',this)" value="<e:property value="@memberDto.realName"/>"/>
                    </span>
                </div>
                <div id="error-realname" class="error-tips red"></div>
            </li>
            <li id="idCard-li">
                <div id="idCardBox" class="lookbox"></div>
                <div class="input-wrap">
                    <a class="icon icon-error fr" style="display: none"></a>
                    <i class="icon icon-id"></i>
                    <span class="span">
                      <input type="text" class="input-text" id="idCard" name="idCard" maxlength="18" placeholder="请输入身份证号" onkeyup="checkFormButton('keyUp',this)" onblur="checkFormButton('onblur',this)" value="<e:property value="@memberDto.credentialsNo"/>"/>
                    </span>
                </div>
                <div id="error-idCard" class="error-tips red"></div>
            </li>
            <li id = "cardNoError">
                <div id="cardNoBox" class="lookbox"></div>
                <div class="input-wrap">
                    <a class="icon icon-error fr" style="display: none"></a>
                    <i class="icon icon-bankcard"></i>
                    <span class="span">
                      <input type="tel" class="input-text" id="cardNo" name="cardNo" maxlength="22" placeholder="请输入借记卡卡号" onkeyup="checkFormButton('keyUp',this)" onblur="checkFormButton('onblur',this)"/>
                    </span>
                </div>
                <div id="bankName" class="error-tips orange"></div>
                <div id="error-cardNo" class="error-tips red"></div>
            </li>
            <li id="tel-li">
                <div id="lookTelBox" class="lookbox"></div>
                <div class="input-wrap">
                    <a class="icon icon-error fr" style="display: none"></a>
                    <i class="icon icon-phone"></i>
                    <span class="span">
                    <input type="tel" class="input-text" id="tel" maxlength="11" name="tel" placeholder="请输入银行预留手机号" onkeyup="checkFormButton('keyUp',this)" onblur="checkFormButton('onblur',this)"/>
                    </span>
                </div>
                <div id="error-tel" class="error-tips red"></div>
            </li>
            <li id="identify-code-li">
                <div class="input-wrap input-codeWeb-wrap">
                    <a id="sendBandCardCode" class="btn-small-gray fr card" >点击发送</a>
                    <!-- <a id="sendCode" class="btn-small fr">点击发送</a> -->
                    <a class="icon icon-error fr" style="display: none"></a>
                    <i class="icon icon-codePhone"></i>
                    <span class="span">
                    <input type="tel" class="input-text" id="identifyCode" name="identifyCode" maxlength="6" placeholder="请输入手机验证码" onkeyup="checkFormButton('keyUp',this)" onblur="checkFormButton('onblur',this)"/>
                    </span>
                </div>
                <div id="error-identify-code" class="error-tips red"></div>
            </li>
            <li>
                <input type="button" class="btn-login-gray" id="btn-bindCard" name="btn-bindCard" value="添加银行卡" disabled="true"/>
                <!--<input type="button" class="btn-login" id="btn-bindCard" name="btn-bindCard" value="添加银行卡"/>-->
            </li>
        </ul>
    </div>
</form>
<div id="productId" style="display: none"><e:property value="@productId"/></div>
<div id="amount" style="display: none"><e:property value="@amount"/></div>
<div id="ret" style="display: none"><e:property value="@ret"/></div>
<div id="expectIncome" style="display: none"><e:property value="@expectIncome"/></div>
<div id="promoNo" style="display: none"><e:property value="@promoNo"/></div>
<div id="returnFlag" style="display: none"><e:property value="@returnFlag"/></div>
<!-- 要跳转的活动编码 -->
<div id="activityCode" style="display: none"><e:property value="@activityCode"/></div>
</div>
<!------------------绑卡失败-弹出层------------------>
<!-- <div id="mask" style="display: none"></div>
<div id="alertLayer-3" class="unloginMask regMask" style="display: none; width: 100%; height:40%;">
    <div class="pr" id="layerBody">
        <img src="static/images/tieCardFailMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed font-white pa" href="javascript:void(0)"><i class="icon icon-error2"></i></a>
        <p class="errorCon red pa">银行卡和预留手机号不符</p>
    </div>
</div> -->
<div id="mask" style="display: none"></div>
<%@include file = "../inc/failMask.jsp" %>
<!------------------绑卡失败-弹出层 end------------------>
<a class="service" href="focus/toContactService"><i class="icon icon-service font-white"></i></a>
</body>
</html>