<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
    <script type="text/javascript" src="static/js/account/common_account.js"></script>
    <script type="text/javascript" src="static/js/account/register1.js"></script>
    <title>注册</title>
</head>
<body>
<div id="box" class="bg-white">
    <div class="input-group">
    <form action="" id="forms">
        <ul>
            <li>
                <div id="messageBox" class="tc red pb10"></div>
            </li>
            <li id="tel-li">
                <div id="lookTelBox"  class="lookbox"></div>
                <div class="input-wrap">
                    <a class="icon icon-error fr" style="display: none"></a>
                    <i class="icon icon-phone"></i>
                    <span class="span">
                      <input type="tel" class="input-text" id="tel" maxlength="11" name="tel" placeholder="请输入注册手机号" onkeyup="checkFormButton('keyUp',this)" onblur="checkFormButton('onblur',this)"/>
                    </span>
                </div>
                <div id="error-tel" class="error-tips red"></div>
            </li>
            <li id="setup-pwd-li">
                <div class="input-wrap">
                    <a href="javascript:lookPwd('loginPwd','请输入登录密码');" id="look-loginPwd" class="icon icon-unlook orange fr"></a>
                    <!-- <a href="javascript:lookPwd();" id="look-pwd" class="icon icon-look orange fr"></a> -->
                    <a class="icon icon-error fr" style="display: none"></a>
                    <i class="icon icon-password"></i>
                    <span id="lookBox-loginPwd" class="span">
                    <input type="password" class="input-text" id="loginPwd" name="loginPwd" maxlength="20" placeholder="请输入登录密码" onfocus="pwdLook()" onkeyup="checkFormButton('keyUp',this)" onblur="checkFormButton('onblur',this)"/>
                    </span>
                </div>
                <div id="error-setup-pwd" class="error-tips red"></div>
                <!-- 浏览器访问显示图片验证码 -->
                <input id="srcNo" name="srcNo" type="hidden" value="<e:property value="@srcNo"/>"/>
            </li>
            <li id="identify-code-li">
                <div class="input-wrap input-codeWeb-wrap">
                    <a id="sendCode" class="btn-small-gray fr register">点击发送</a>
                    <!-- <a href="#" id="sendCode" class="btn-small fr">点击发送</a> -->
                    <a class="icon icon-error fr" style="display: none"></a>
                    <i class="icon icon-codePhone"></i>
                    <span class="span">
                    <input type="tel" class="input-text" maxlength="6" id="identifyCode" name="identifyCode" placeholder="请输入手机验证码" onkeyup="checkFormButton('keyUp',this)" onblur="checkFormButton('onblur',this)"/>
                    </span>
                </div>
                <div id="error-identify-code" class="error-tips red"></div>
            </li>
            <e:if test="${srcNo!=null }">
              <li id="verify-code-li">
                  <div class="input-wrap input-codeWeb-wrap">
                      <a href="javascript:void(0);" onclick="refreshCode();"><img id="securityCode" src="<%=request.getContextPath()%>/Kaptcha.jpg"  class="fr"/></a>
                      <a class="icon icon-error fr" style="display: none"></a>
                      <i class="icon icon-codeWeb"></i>
                      <span class="span">
                      <input type="text" class="input-text" maxlength="4" id="verifyCode" name="verifyCode" placeholder="请输入右侧验证码" onkeyup="checkFormButton('keyUp',this)" onblur="checkFormButton('onblur',this)"/>
                      </span>
                  </div>
                  <div id="error-verify-code" class="error-tips red"></div>
              </li>
            </e:if>
            <li>
                <input type="button" class="btn-login-gray" id="btn-regist" name="btn-regist" value="注册并登录" disabled="true"/>
                <!-- <input type="button" class="btn-login" id="btn-regist" name="btn-regist" value="注册并登录"/> -->
            </li>
            <li>
                <div class="checkedBox pt10">
                    <input type="checkbox" class="agree-box" id="agreeBox" checked="checked"/>
                    <i class="icon icon-checkbox orange"></i>
                    已阅读并同意遵守 <a href="account/toLmProtocol" class="orange">《懒猫金融服务协议》</a>
                </div>
                <div id="error-agree" class="error-tips red"></div>
            </li>
        </ul>
    </form>
    <!-- 前一个页面的url -->
    <%-- <div id="returnUrl" style="display: none">${returnUrl}</div> --%>
    <div id="returnFlag" style="display: none"><e:property value="@returnFlag"/></div>
    <div id="productId" style="display: none"><e:property value="@productId"/></div>
    <div id="fundCode" style="display: none"><e:property value="@fundCode"/></div>
    </div>
</div>
<!--弹出层-->
<div id="mask" style="display: none"></div>
<!--注册失败弹出层-->
<!-- <div id="alertLayer" class="unloginMask regMask" style="display: none; width: 100%; height:40%;">
    <div class="pr" id="layerBody">
        <img src="static/images/regFailMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed font-white pa" href="javascript:void(0)"><i class="icon icon-error2"></i></a>
        <p class="errorCon red pa">注册失败</p>
    </div>
</div> -->
<%@include file = "../inc/failMask.jsp" %>
</body>
</html>