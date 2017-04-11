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
    <title>洗爱车注册活动</title>
</head>
<body>
<div style="background: #ffd302">
    <div class="pr">
        <img src="static/images/activity/car-header-1.jpg" class="repeatImg"/>
        <img src="static/images/activity/car-header-2.jpg" class="repeatImg"/>
    </div>
<div class="input-group input-group-invest">
    <ul>
    	<li>
            <div id="messageBox" class="tc red pb10"></div>
        </li>
        <li>
            <div id="lookTelBox"  class="lookbox"></div>
            <div class="input-wrap">
                <a class="icon icon-error fr" style="display: none"></a>
                <i class="icon icon-phone"></i>
                <span class="span">
                      <input type="tel" class="input-text" id="tel" maxlength="11" name="tel" placeholder="请输入注册手机号" onkeyup="checkFormButton('keyUp',this)" onblur="checkFormButton('onblur',this)"/>
                    </span>
            </div>
            <div id="error-tel" class="error-tips orange"></div>
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

<div class="input-group-invest mb5">
    <div class="checkedBox pt10">
        <input type="checkbox" class="agree-box" id="agreeBox" checked="checked"/>
        <i class="icon icon-checkbox orange"></i>
        已阅读并同意遵守 <a href="account/toLmProtocol" class="orange">《懒猫金服服务协议》</a>
    </div>
    <div id="error-agree" class="error-tips orange"></div>
</div>
<div class="pr" id="btn-regist">
    <a href="javascript:void(0)"><img src="static/images/regBtn.png" class="repeatImg"></a>
</div>
    <div class="layou-04 input-group-invest tr">
        <div class="checkedBox pt10" style="color:#e85d25;">已有懒猫账户
            <a href="account/toLogin">
                <img class="my-replace" src="static/images/activity/walletLogin.png" alt=""/>
            </a>
        </div>
    </div>
   <div class="pr">
        <img src="static/images/activity/car-main-1.jpg" class="repeatImg"/>
        <img src="static/images/activity/car-main-2.jpg" class="repeatImg"/>
        <img src="static/images/activity/car-main-3.jpg" class="repeatImg"/>
        <img src="static/images/activity/car-main-4.jpg" class="repeatImg"/>
        <img src="static/images/activity/car-main-5.jpg" class="repeatImg"/>
    </div>
    <div class="car-explain">
        <img class="car-explain-caption" src="static/images/activity/car-explain.png" alt=""/>
        <ul>
            <li>1、即日起，懒猫金服新用户成功注册+绑卡，即送价值10元洗车卡，还有价值2390元的投资大礼包；完成首次信托理财产品投资的用户，即可获赠60-300元不等的洗车卡！ </li>
            <li>2、满足获赠洗车卡的用户，客服人员会在1～3个工作日与您电话联系，给予洗车卡或兑换码。</li>
            <li>3、每日投资额排名前3位的用户，将分别获赠：200元京东卡、150元京东卡、精美懒猫公仔。客服人员会在1～3个工作日与获奖用户电话联系，给予京东卡或懒猫公仔。</li>
            <li>4、满足获奖条件的用户也可搜索关注“懒猫金服”微信公众号，或拨打客服电话主动联系我们。</li>
            <li>5、活动最终权解释在法律允许范围内归懒猫金服所有。</li>
            <li>6、懒猫金服客服电话：4001-500-882。</li>
        </ul>
        <img src="static/images/activity/shadow.jpg" class="car-shadow"/>
    </div>
    <div class="pr" >
        <img src="static/images/activity/car-footer.jpg" class="repeatImg"/>
        <a href="javascript:void(0)" onclick="gotop();" class="btnAreas btnProfit pa">马上赚钱</a>
    </div>
</div>
<e:if test="${!empty returnFlag }">
 <div id="returnFlag" style="display: none"><e:property value="@returnFlag"/></div>
</e:if>
<e:if test="${!empty source }">
<div id="activityCode" style="display: none"><e:property value="@source"/></div>
</e:if>
<!--弹出层-->
<div id="mask" style="display: none"></div>
<!--未登录弹出层-->
<div id="alertLayer" class="unloginMask regMask" style="display: none; width: 100%; height:40%;">
    <div class="pr" id="layerBody">
        <img src="static/images/regFailMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed font-white pa" href="javascript:void(0)"><i class="icon icon-error2"></i></a>
        <p class="errorCon red pa">注册失败</p>
    </div>
</div>
</body>
</html>