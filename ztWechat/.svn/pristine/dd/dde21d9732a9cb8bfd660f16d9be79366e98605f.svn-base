<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
    <script type="text/javascript" src="static/js/account/activity_common_account.js"></script>
    <script type="text/javascript" src="static/js/account/activityRegister.js"></script>
    <link rel="stylesheet" href="static/css/LM-activity.css">
    <!-- <script type="text/javascript" src="static/js/account/register1.js"></script> -->
    <title>注册活动</title>
</head>
<body>
<div class="dlbArea">
    <div class="pr">
   	    <img src="static/images/activity/tianyi-new-1.jpg" class="repeatImg"/>
        <img src="static/images/activity/tianyi-new-2.jpg" class="repeatImg"/>
        <img src="static/images/activity/tianyi-new-3.jpg" class="repeatImg"/>
        <img src="static/images/activity/tianyi-new-4.jpg" class="repeatImg"/>
        <a class="receive-1 tianyi-1 btnClick" href="javascript:void(0)"></a>
        <a class="receive-2 tianyi-2 btnClick-1" href="javascript:void(0)"></a>
    </div>
    <div class="ruleTab">
        <h2 class="tab_nav tc">活动规则</h2>
        <div class="activityRule">
            <h2 class="activityDate">活动时间：即日起，送完为止</h2>
            <ul>
                <li>1、流量有限，先到先得，送完为止，每项限领取一次；</li>
                <li>2、仅限活动期间注册+绑卡并完成首次投资的用户领取流量（仅限新手标、定期理财产品参加）；</li>
                <li>3、电影兑换券领取规则：获奖用户请通过“懒猫金服”微信公众号领取电影兑换券；</li>
                <li>4、投资金额指单笔投资，不含累计；</li>
                <li>5、参与范围：移动、联通、电信用户（欠费、时长用户除外）；</br>流量属性：全国通用流量（当月有效，两个工作日到账）；</li>
                <li>6、微信关注懒猫金服（微信号lanmaojf）即可登录账户查看优惠券、购买记录信息；</li>
                <li>7、活动过程中发现任何作弊行为用户，一律取消活动资格；</li>
                <li>8、本活动最终解释权归北京懒猫金融信息服务有限公司所有；</li>
                <li>9、懒猫金服全国客服电话：4001-500-882</li>
            </ul>
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
                <li>
                    <!--<div class="phoneZoom">13255246655</div>-->
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
		            <div id="error-setup-pwd" class="error-tips orange"></div>
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
                已阅读并同意遵守 <a href="javascript:void(0)" class="orange">《懒猫金服服务协议》</a>
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
<!--弹出层-->
<!-- <div id="mask" style="display: none"></div> -->
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