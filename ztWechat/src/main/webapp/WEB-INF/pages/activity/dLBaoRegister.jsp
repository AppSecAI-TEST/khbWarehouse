<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
    <script type="text/javascript" src="static/js/account/activity_common_account.js"></script>
    <script type="text/javascript" src="static/js/account/activityRegister.js"></script>
    <script type="text/javascript" >
    
    function gotop(){
    	document.getElementsByTagName('body')[0].scrollTop = 0;
    }
    
    </script>
    <link rel="stylesheet" href="static/css/LM-activity.css">
    <!-- <script type="text/javascript" src="static/js/account/register1.js"></script> -->
    <title>懒猫金服送流量</title>
</head>
<body>
<div class="dlbArea">
    <div class="pr">
        <img src="static/images/activity/duoLa-01.jpg" class="repeatImg"/>
        <img src="static/images/activity/duoLa-02.jpg" class="repeatImg"/>
        <img src="static/images/activity/duoLa-03.jpg" class="repeatImg"/>
        <img src="static/images/activity/duoLa-04.jpg" class="repeatImg"/>
        <a class="receive-1 btnClick" href="javascript:void(0)"></a>
        <a class="receive-2 btnClick-1" href="javascript:void(0)"></a>
        <a class="dlbDetail" href="javascript:;"></a>
        <div class="dlb-popup pr">
            <img src="static/images/activity/dlb-popup.png" class="repeatImg" alt=""/>
            <a class="btnClosed dlbClosed pa" href="javascript:void(0)"></a>
        </div>
    </div>

</div>
<!--弹出层-->
<div id="mask" style="display: none"></div>
<div class="alertLayer" style="display: none; width: 100%; top: .5rem">
	<div class="dlb-content pr">
	        <a class="btnClosed intiverClosed pa" href="javascript:void(0)"><img src="static/images/activity/intiverClosed.png" alt=""/></a>
	        <img class="dlb-inputImg" src="static/images/activity/dlb-input.png" alt=""/>
	        <div class="input-group input-group-invest dlb-input">
	            <ul>
		              <!-- <li>
	                   <div id="messageBox" class="tc red pb10"></div>
	                  </li> -->
	                <li>
	                	<!-- <div id="lookTelBox"  class="lookbox"></div> -->
		                <div class="input-wrap">
			                <a class="icon icon-error fr" style="display: none"></a>
			                <i class="icon icon-phone"></i>
			                <span class="span">
			                      <input type="tel" class="input-text" id="tel" maxlength="11" name="tel" placeholder="请输入注册手机号" onkeyup="checkFormButton('keyUp',this)" onblur="checkFormButton('onblur',this)"/>
			                    </span>
			            </div>
	            		<div id="error-tel" class="error-tips orange"></div>
	                    <!--<div class="phoneZoom">13255246655</div>-->
	                    
	                </li>
	                <li class="input-wrap-tip">
	                    <div class="input-wrap">
			                <!--<a href="javascript:void(0)" class="icon icon-unlook purple fr"></a>-->
			                <a href="javascript:lookPwd('loginPwd','请输入登录密码');" id="look-loginPwd" class="icon icon-unlook orange fr"></a>
			                <a class="icon icon-error fr" style="display: none"></a>
			                <i class="icon icon-password"></i>
			                <span id="lookBox-loginPwd" class="span">
			                    <input type="password" class="input-text" id="loginPwd" name="loginPwd" maxlength="20" placeholder="请输入登录密码" onfocus="pwdLook()" onkeyup="checkFormButton('keyUp',this)" onblur="checkFormButton('onblur',this)"/>
			                </span>
			            </div>
			            <div id="error-setup-pwd" class="error-tips"></div>
			            <!-- 浏览器访问显示图片验证码 -->
			            <input id="srcNo" name="srcNo" type="hidden" value="<e:property value="@srcNo"/>"/>
	                </li>
	                <e:if test="${srcNo!=null }">
				        <li class="input-wrap-error">
				            <div class="input-wrap input-codeWeb-wrap">
				            <a href="javascript:void(0);" onclick="refreshCode();"><img id="securityCode" src="<%=request.getContextPath()%>/Kaptcha.jpg"  class="fr"/></a>
				                <!-- <img src="static/images/codeWeb.jpg"  class="fr"/> -->
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
	                   <div class="input-wrap input-codeWeb-wrap">
			             <a id="sendCode" class="btn-small-gray fr register">点击发送</a>
			                <!--<a href="#" class="btn-small fr">点击发送</a>-->
			                <a class="icon icon-error fr" style="display: none"></a>
			                <i class="icon icon-codePhone"></i>
			                <span class="span">
			                   <input type="tel" class="input-text" maxlength="6" id="identifyCode" name="identifyCode" placeholder="请输入手机验证码" onkeyup="checkFormButton('keyUp',this)" onblur="checkFormButton('onblur',this)"/>
			                </span>
			            </div>
	                </li>
	            </ul>
	        </div>
	        <div class="pr" id="btn-regist">
	            <a href="javascript:void(0)"><img src="static/images/activity/dlb-input-btn.jpg" class="repeatImg"></a>
	        </div>
	        <div class="layou-04 input-group-invest">
	            <div class="checkedBox">
	                <input type="checkbox" class="agree-box" id="agreeBox" checked="checked"/>
	                <i class="icon icon-checkbox orange"></i>
	                已阅读并同意遵守 <a href="account/toLmProtocol" class="orange">《懒猫金服服务协议》</a>
	            </div>
	            <div id="error-agree" class="error-tips orange"></div>
	        </div>
	        <div class="onload">
	            <img src="static/images/activity/dlb-input-1.png" alt="">
	            <a href="account/toLogin"></a>
	        </div>
	  </div>
</div>



<e:if test="${!empty returnFlag }">
 <div id="returnFlag" style="display: none"><e:property value="@returnFlag"/></div>
</e:if>
<e:if test="${!empty source }">
<div id="activityCode" style="display: none"><e:property value="@source"/></div>
</e:if>
<!--未登录弹出层-->
<!-- <div id="alertLayer" class="unloginMask regMask" style="display: none; width: 100%; height:40%;">
    <div class="pr" id="layerBody">
        <img src="static/images/regFailMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed font-white pa" href="javascript:void(0)"><i class="icon icon-error2"></i></a>
        <p class="errorCon red pa">注册失败</p>
    </div>
</div> -->
</body>
</html>