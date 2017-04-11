<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
    <script type="text/javascript" src="static/js/account/common_account.js"></script>
    <script type="text/javascript" src="static/js/account/reset_login.js"></script>
    <title>重置登录密码</title>
</head>
<body>
<div id="box" class="bg-white">
    <div class="input-group">
        <ul>
            <li>
                <div id="messageBox" class="tc red pb10"></div>
            </li>
            <li id="tel-li">
                <div class="input-wrap">
                    <a href="javascript:void(0)" class="icon icon-error fr" style="display: none"></a>
                    <i class="icon icon-phone"></i>
                    <span class="span">
                      <input type="tel" class="input-text" id="tel" maxlength="11" name="tel" placeholder="请输入登录手机号" onkeyup="checkFormButton('keyUp')" onblur="checkFormButton('onblur')"/>
                    </span>
                </div>
                <div id="error-tel" class="error-tips red"></div>
            </li>
            <li id="setup-pwd-li">
                <div class="input-wrap">
                    <a href="javascript:lookPwd('loginPwd','请输入新的登录密码')" id="look-loginPwd" class="icon icon-unlook orange fr"></a>
                    <a href="javascript:void(0)" class="icon icon-error orange fr" style="display: none"></a>
                    <!--<a href="javascript:void(0)" class="icon icon-look orange fr"></a>-->
                    <i class="icon icon-password"></i>
                    <span id="lookBox-loginPwd" class="span">
                      <input type="password" class="input-text" id="loginPwd" name="loginPwd" maxlength="20" placeholder="请输入新的登录密码" onkeyup="checkFormButton('keyUp')" onblur="checkFormButton('onblur')"/>
                    </span>
                </div>
                <div class="error-tips orange" id="error-setup-pwd"></div>
            </li>
            <li id="login-re-pwd-li">
                <div class="input-wrap">
                    <a href="javascript:lookPwd('loginRePwd','请再次输入新的登录密码')" id="look-loginRePwd" class="icon icon-unlook orange fr"></a>
                    <!--<a href="javascript:void(0)" class="icon icon-look orange fr"></a>-->
                    <a href="javascript:void(0)" class="icon icon-error orange fr" style="display: none"></a>
                    <i class="icon icon-password"></i>
                    <span id="lookBox-loginRePwd" class="span">
                     <input type="password" class="input-text" id="loginRePwd" name="loginRePwd" maxlength="20" placeholder="请再次输入新的登录密码" onkeyup="checkFormButton('keyUp')" onblur="checkFormButton('onblur')"/>
                    </span>
                </div>
                <div class="error-tips red" id="error-login-re-pwd"></div>
            </li>
            <li id="identify-code-li">
                <div class="input-wrap input-codeWeb-wrap">
                    <a id="sendCode" href="javascript:void(0)" class="btn-small-gray fr resetLogin">点击发送</a>
                    <!--<a href="#" class="btn-small fr">点击发送</a>-->
                    <a href="javascript:void(0)" class="icon icon-error fr" style="display: none"></a>
                    <i class="icon icon-codePhone"></i>
                    <span class="span">
                      <input type="tel" class="input-text" id="identifyCode" name="identifyCode" maxlength="6" placeholder="请输入手机验证码" onkeyup="checkFormButton('keyUp')" onblur="checkFormButton('onblur')"/>
                    </span>
                </div>
                <div class="error-tips red" id="error-identify-code"></div>
            </li>
            <li>
                <input type="button" class="btn-login-gray" id="resLogin" name="resLogin" value="确认重置"/>
                <!--<input type="button" class="btn-login" id="" name="" value="确认重置"/>-->
            </li>
        </ul>
    </div>
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
</body>
</html>