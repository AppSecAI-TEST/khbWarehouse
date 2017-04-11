<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head>
	<script type="text/javascript" src="static/js/account/personal_center.js"></script>
    <script type="text/javascript" src="static/js/account/modify_trade.js"></script>
    <title>交易密码修改</title>
</head>
<body>
<div id="box" class="bg-white">
	<form action="" id="register">
	    <div class="input-group">
	        <ul>
	        	<li>
	                <div id="messageBox" class="tc red pb10"></div>
	            </li>
	            <li>
	                <div class="input-wrap">
	                    <a href="javascript:void(0)" class="icon icon-error fr" style="display: none"></a>
	                    <i class="icon icon-phone"></i>
	                    <input type="text" class="input-text" id="tel" maxlength="11" name="tel" value="<e:property value="@mobileNo"/>" readonly="readonly"/>
	                </div>
	            </li>
	            <li id="password-li">
	                <div class="input-wrap">
	                    <!--<a href="javascript:void(0)" class="icon icon-unlook orange fr"></a>-->
	                    <a href="javascript:lookPwd('currentTradePwd','请输入原交易密码');" id="look-currentTradePwd" class="icon icon-unlook orange fr"></a>
	                    <a href="javascript:void(0)" class="icon icon-error orange fr" style="display: none"></a>
	                    <i class="icon icon-password"></i>
	                    <span id="lookBox-currentTradePwd" class="span">
		                    <input type="password" class="input-text" id="currentTradePwd" name="currentTradePwd" maxlength="20" placeholder="请输入原交易密码" onkeyup="checkFormButton('keyup','curpwd')" onblur="checkFormButton('blur','curpwd')"/>
	                    </span>
	                </div>
                	<div id="error-password" class="error-tips red"></div>
	            </li>
	            <li id="setup-pwd-li">
	                <div class="input-wrap">
	                    <a href="javascript:lookPwd('tradePwd','请输入新的交易密码');" id="look-tradePwd" class="icon icon-unlook orange fr"></a>
	                    <!--<a href="javascript:void(0)" class="icon icon-look orange fr"></a>-->
	                    <a href="javascript:void(0)" class="icon icon-error orange fr" style="display: none"></a>
	                    <i class="icon icon-password"></i>
	                    <span id="lookBox-tradePwd" class="span">
		                    <input type="password" class="input-text" maxlength="20" id="tradePwd" name="tradePwd" placeholder="请输入新的交易密码" onkeyup="checkFormButton('keyup','pwd')" onblur="checkFormButton('blur','pwd')"/>
	                    </span>
	                </div>
                	<div id="error-setup-pwd" class="error-tips red"></div>
	            </li>
	            <li id="trade-re-pwd-li">
	                <div class="input-wrap">
	                    <a href="javascript:lookPwd('tradeRePwd','请再次输入新的交易密码');" id="look-tradeRePwd" class="icon icon-unlook orange fr"></a>
	                    <!--<a href="javascript:void(0)" class="icon icon-look orange fr"></a>-->
	                    <a href="javascript:void(0)" class="icon icon-error orange fr" style="display: none"></a>
	                    <i class="icon icon-password"></i>
	                    <span id="lookBox-tradeRePwd" class="span">
		                    <input type="password" class="input-text" maxlength="20" id="tradeRePwd" name="tradeRePwd" placeholder="请再次输入新的交易密码" onkeyup="checkFormButton('keyup','repwd')" onblur="checkFormButton('blur','repwd')"/>
	                    </span>
	                </div>
                	<div id="error-trade-re-pwd" class="error-tips red"></div>
	            </li>
	            <li>
	                <input type="button" class="btn-login-gray" id="reg" name="reg" value="确认修改"/>
	                <!--<input type="button" class="btn-trade" id="" name="" value="确认修改"/>-->
	            </li>
	        </ul>
	    </div>
	</form>
</div>
<div id="mask" style="display: none"></div>
<div id="alertLayer-3" class="unloginMask regMask editMask" style="display: none; width: 100%; height:40%;">
    <div class="pr">
        <img src="static/images/editSuccessMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed font-white pa" href="javascript:void(0)"><i class="icon icon-error2"></i></a>
        <div class="btnMaskArea tc pa">
            <a href="account/toAccount">OK</a>
        </div>
    </div>
</div>
<a class="service" href="focus/toContactService"><i class="icon icon-service font-white"></i></a>
</body>
</html>