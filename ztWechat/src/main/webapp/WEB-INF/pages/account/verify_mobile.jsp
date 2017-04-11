<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head>
    <script type="text/javascript" src="static/js/account/personal_center.js"></script>
    <script type="text/javascript" src="static/js/account/verify_mobile.js"></script>
    <title>手机号验证</title>
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
	                    <input type="text" class="input-text" id="tel" maxlength="11" name="tel"
	                    	 value="<e:property value="@mobileNo"/>" readonly="readonly"/>
	                </div>
	            </li>
	            <li id="identify-code-li">
	                <div class="input-wrap input-codeWeb-wrap">
	                    <a id="sendCode" href="javascript:void(0)" class="btn-small fr">点击发送</a>
	                    <a href="javascript:void(0)" class="icon icon-error fr" style="display: none"></a>
	                    <i class="icon icon-codePhone"></i>
	                    <span class="span">
		                    <input type="tel" class="input-text" maxlength="6" id="identifyCode" name="identifyCode" placeholder="请输入手机验证码" onkeyup="checkFormButton('keyup','identifyCode')" onblur="checkFormButton('blur','identifyCode')"/>
	                    </span>
	                </div>
	                <div id="error-identify-code" class="error-tips red"></div>
	            </li>
	            <li id="password-li">
	                <div class="input-wrap">
	                    <!--<a href="javascript:void(0)" class="icon icon-unlook fr"></a>-->
	                    <a href="javascript:lookPwd('tradePwd','请输入交易密码');" id="look-tradePwd" class="icon icon-unlook orange fr"></a>
	                    <a href="javascript:void(0)" class="icon icon-error orange fr" style="display: none"></a>
	                    <i class="icon icon-password"></i>
	                    <span id="lookBox-tradePwd" class="span">
		                    <input type="password" class="input-text" maxlength="20" id="tradePwd" name="tradePwd" placeholder="请输入交易密码" onkeyup="checkFormButton('keyup','curpwd')" onblur="checkFormButton('focus','curpwd')"/>
	                    </span>
	                </div>
	                <div id="error-password" class="error-tips red"></div>
	            </li>
	            <li>
	                <input type="button" class="btn-login-gray" id="reg" name="reg" value="确认验证" disabled="disabled"/>
	                <!--<input type="button" class="btn-login" id="" name="" value="确认验证"/>-->
	            </li>
	        </ul>
	    </div>
	</form>
</div>
<a class="service" href="focus/toContactService"><i class="icon icon-service font-white"></i></a>
</body>
</html>