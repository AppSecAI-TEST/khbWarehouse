<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head>
     <script type="text/javascript" src="static/js/account/personal_center.js"></script>
     <script type="text/javascript" src="static/js/account/modify_mobile.js"></script>
     <title>手机号修改</title>
</head>
<body>
<div id="box" class="bg-white">
	<form action="" id="register">
	    <div class="input-group">
	        <ul>
	        	<li>
	                <div id="messageBox" class="tc red pb10"></div>
	            </li>
	            <li id="tel-li">
	                <div id="lookTelBox" class="lookbox"></div>
	                <div class="input-wrap">
	                    <a href="javascript:void(0)" class="icon icon-error fr" style="display: none"></a>
	                    <i class="icon icon-phone"></i>
	                    <span class="span">
		                    <input type="tel" class="input-text" id="tel" maxlength="11" name="tel" placeholder="请输入新手机号" onkeyup="checkFormButton('keyup','tel',false)" onblur="checkFormButton('blur','tel',true)"/>
	                    </span>
	                </div>
                	<div id="error-tel" class="error-tips red"></div>
	            </li>
	            <li id="identify-code-li">
	                <div class="input-wrap input-codeWeb-wrap">
	                    <a id="sendCode" href="javascript:void(0)" class="btn-small-gray fr newPhone">点击发送</a>
	                    <!--<a href="#" class="btn-small fr">点击发送</a>-->
	                    <a href="javascript:void(0)" class="icon icon-error fr" style="display: none"></a>
	                    <i class="icon icon-codePhone"></i>
	                    <span class="span">
		                    <input type="tel" class="input-text" id="identifyCode" name="identifyCode" maxlength="6" placeholder="请输入手机验证码" onkeyup="checkFormButton('keyup','identifyCode',false)" onblur="checkFormButton('keyup','identifyCode',false)"/>
	                    </span>
	                </div>
                	<div id="error-identify-code" class="error-tips red"></div>
	            </li>
	            <li>
	                <input type="button" class="btn-login-gray" name="reg" id="reg" value="确认修改" disabled="disabled"/>
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
</body>
</html>