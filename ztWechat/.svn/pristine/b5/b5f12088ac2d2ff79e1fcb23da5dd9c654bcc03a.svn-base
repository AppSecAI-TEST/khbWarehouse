<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head>
	<script type="text/javascript" src="static/js/account/personal_center.js"></script>
    <script type="text/javascript" src="static/js/account/reset_trade.js"></script>
    <title>交易密码重置</title>
</head>
<body>
<input id="ret" type=hidden value="<e:property value="@RET"/>"/>
<form action="" id="register">
	<div id="box" class="bg-white">
	    <div class="input-group">
	        <ul>
	        	<li>
	                <div id="messageBox" class="tc red pb10"></div>
	            </li>
	            <li>
	                <div class="input-wrap">
	                    <a href="javascript:void(0)" class="icon icon-error fr" style="display: none"></a>
	                    <i class="icon icon-phone"></i>
	                    <input type="text" class="input-text" id="tel" maxlength="11" name="tel" value="${mobileNo}" readonly="readonly"/>
	                </div>
	            </li>
	            <li id="id-number-li">
                	<div id="idCardBox" class="lookbox"></div>
	                <div class="input-wrap">
	                    <a href="javascript:void(0)" class="icon icon-error fr" style="display: none"></a>
	                    <i class="icon icon-id"></i>
	                    <span class="span">
		                    <input type="text" class="input-text" maxlength="18" id="idNumber" name="idNumber" placeholder="请输入身份证号" onkeyup="checkFormButton('keyup','idcard')" onblur="checkFormButton('blur','idcard')"/>
	                    </span>
	                </div>
                	<div id="error-id-number" class="error-tips red"></div>
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
	            <li id="identify-code-li">
	                <div class="input-wrap input-codeWeb-wrap">
	                    <a id="sendCode" href="javascript:void(0)" class="btn-small fr resetTrade">点击发送</a>
	                    <a href="javascript:void(0)" class="icon icon-error fr" style="display: none"></a>
	                    <i class="icon icon-codePhone"></i>
	                    <span class="span">
		                    <input type="tel" class="input-text" maxlength="6" id="identifyCode" name="identifyCode" placeholder="请输入手机验证码" onkeyup="checkFormButton('keyup','identifyCode')" onblur="checkFormButton('blur','identifyCode')"/>
	                    </span>
	                </div>
	                <div id="error-identify-code" class="error-tips red"></div>
	            </li>
	            <li>
	                <input type="button" class="btn-login-gray" id="reg" name="reg" value="确认重置"/>
	                <!--<input type="button" class="btn-login" id="" name="" value="确认重置"/>-->
	            </li>
	        </ul>
	    </div>
	</div>
</form>
<div id="mask" style="display: none"></div>
<div id="alertLayer-3" class="unloginMask regMask editMask" style="display: none; width: 100%; height:40%;">
  <div class="pr">
      <img src="static/images/editSuccessMask.png" class="repeatImg" alt=""/>
      <a class="btnClosed font-white pa" href="javascript:void(0)"><i class="icon icon-error2"></i></a>
      <div class="btnMaskArea tc pa">
        <e:if test="${RET=='toRecharge' }">
          <a href="account/toAccount">OK</a>
        </e:if>
        <e:else>
          <a href="account/toAccount">OK</a>
        </e:else>
      </div>
  </div>
</div>
<a class="service" href="focus/toContactService"><i class="icon icon-service font-white"></i></a>
</body>
</html>