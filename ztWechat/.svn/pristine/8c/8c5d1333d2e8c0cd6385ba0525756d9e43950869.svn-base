<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head>
	<script type="text/javascript" src="static/js/account/personal_center.js"></script>
	<script type="text/javascript" src="static/js/account/modify_login.js"></script>
	<title>登录密码修改</title>
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
	                    <a href="javascript:lookPwd('currentLoginPwd','请输入原登录密码');" id="look-currentLoginPwd" class="icon icon-unlook orange fr"></a>
	                    <a href="javascript:void(0)" class="icon icon-error orange fr" style="display: none"></a>
	                    <i class="icon icon-password"></i>
	                    <span id="lookBox-currentLoginPwd" class="span">
		                    <input type="password" class="input-text" maxlength="20" id="currentLoginPwd" name="currentLoginPwd" placeholder="请输入原登录密码" onkeyup="checkFormButton('keyup','curpwd')" onblur="checkFormButton('blur','curpwd')"/>
			            </span>
	                </div>
                	<div id="error-password" class="error-tips red"></div>
	            </li>
	            <li id="setup-pwd-li">
	                <div class="input-wrap">
	                    <a href="javascript:lookPwd('loginPwd','请输入新的登录密码');" id="look-loginPwd" class="icon icon-unlook orange fr"></a>
	                    <!--<a href="javascript:void(0)" class="icon icon-look orange fr"></a>-->
	                    <a href="javascript:void(0)" class="icon icon-error orange fr" style="display: none"></a>
	                    <i class="icon icon-password"></i>
	                    <span id="lookBox-loginPwd" class="span">
		                    <input type="password" class="input-text" maxlength="20" id="loginPwd" name="loginPwd" placeholder="请输入新的登录密码" onkeyup="checkFormButton('keyup','pwd')" onblur="checkFormButton('blur','pwd')"/>
	                    </span>
	                </div>
                	<div id="error-setup-pwd" class="error-tips red"></div>
	            </li>
	            <li id="login-re-pwd-li">
	                <div class="input-wrap">
	                    <a href="javascript:lookPwd('loginRePwd','请再次输入新的登录密码');" id="look-loginRePwd" class="icon icon-unlook orange fr"></a>
	                    <!--<a href="javascript:void(0)" class="icon icon-look orange fr"></a>-->
	                    <a href="javascript:void(0)" class="icon icon-error orange fr" style="display: none"></a>
	                    <i class="icon icon-password"></i>
	                    <span id="lookBox-loginRePwd" class="span">
		                    <input type="password" class="input-text" maxlength="20" id="loginRePwd" name="loginRePwd" placeholder="请再次输入新的登录密码" onkeyup="checkFormButton('keyup','repwd')" onblur="checkFormButton('blur','repwd')"/>
	                    </span>
	                </div>
                	<div id="error-login-re-pwd" class="error-tips red"></div>
	            </li>
	            <li>
	                <input type="button" class="btn-login-gray" id="reg" name="reg" value="确认修改"/>
	                <!--<input type="button" class="btn-login" id="" name="" value="确认修改"/>-->
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
        &lt;!&ndash;<p class="errorCon red pa">请重新登录</p>&ndash;&gt;
        <div class="btnMaskArea tc pa">
            <a href="account/toLogin">OK</a>
        </div>
    </div>
</div>
<a class="service" href="focus/toContactService"><i class="icon icon-service font-white"></i></a>
</body>
</html>