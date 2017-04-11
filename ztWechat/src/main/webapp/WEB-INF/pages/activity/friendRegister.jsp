<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
    <script type="text/javascript" src="static/js/account/activity_common_account.js"></script>
    <!-- <script type="text/javascript" src="static/js/account/activityRegister.js"></script> -->
    <link rel="stylesheet" href="static/css/LM-activity.css">
    <script type="text/javascript" src="static/js/friendRegister.js"></script>
    <!-- <script type="text/javascript" src="static/js/account/register1.js"></script> -->
    <title>加入懒猫抽取大奖</title>
</head>
<body>

<div class="pr">
	  <%-- <e:if test="${!empty activityUserInfo }"> --%>
		    <img src="static/images/activity/invitees-01.jpg" class="repeatImg"/>
		    <div class="inviteesCard pa">
		        <img class="inviteesPic" src='<e:property value="@activityUserInfo.wxHeadUrl"/>' />
		        <p class="font-white">
		            您的朋友<i class="Name"><e:property value="@activityUserInfo.wxNickName"/></i><br/>
		            邀请您一起抽取超级大奖，马上<br/>
		            注册领取！
		        </p>
		    </div>
	   <%-- </e:if> --%>
	   <e:if test="${!empty prizeDtoNewList }">
		    <div id="demo" class="scroll">
		        <div class="lotteryScroll">
		            <div id="demo1">
		                <ul>
		                <e:iterator list="@prizeDtoNewList" var="items">
		                    <li><e:property value="@_formater.formatDate(items.createTime)"/> <e:property value="@_formater.maskCellphone(items.memberTel)"/> 获得奖品<e:property value="@items.prizeName"/></li>
		                </e:iterator>
		                </ul>
		            </div>
		            <div id="demo2"></div>
		        </div>
		    </div>
      </e:if>
</div>
<div class="pr">
    <a class="lotteryclick btnClick" href="javascript:void(0);"></a>
    <img src="static/images/activity/invitees-02.jpg" class="repeatImg"/>
    <img src="static/images/activity/invitees-03.jpg" class="repeatImg"/>
    <img src="static/images/activity/invitees-04.jpg" class="repeatImg"/>
    <img src="static/images/activity/invitees-05.jpg" class="repeatImg"/>
</div>
<div style="background: #7955fd" name="regAERA"  id="regAERA">
    <div class="input-group input-group-activity">
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
	            <div id="error-verify-code" class="error-tips"></div>
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
	        <li>
                <div class="checkedBox font-white pt10">
                    <input type="checkbox" class="agree-box" id="agreeBox" checked="checked"/>
                    <i class="icon icon-checkbox orange"></i>
                    已阅读并同意遵守 <a href="account/toLmProtocol" class="orange">《懒猫金服服务协议》</a>
                </div>
                <div id="error-agree" class="error-tips orange"></div>
            </li>
    </ul>
    </div>
    <!-- <div class="layou-04 input-group-activity">
    <div class="checkedBox font-white pt10">
        <input type="checkbox" class="agree-box" id="agreeBox" checked="checked"/>
        <i class="icon icon-checkbox orange"></i>
        已阅读并同意遵守 <a href="account/toLmProtocol" class="orange">《懒猫金服服务协议》</a>
    </div>
    <div id="error-agree" class="error-tips orange"></div>
</div> -->
</div>
<input style="display:none" value="toRaffleActivity" id="returnFlag"  />

<input style="display:none" value='<e:property value="@activityCode"/>' id="activityCode"  />
<div class="pr">
    <img src="static/images/activity/intiverBtn.jpg" class="repeatImg"/>
</div>
<div class="fixedBtn" id="btn-regist">
    <a href="javascript:void(0)"><img src="static/images/activity/intiverBtn1.jpg" class="repeatImg"/></a>
</div>

<!--大转盘弹出层-->
<div id="mask" style="display: none"></div>
<div id="alertLayer" style="display: none; width: 80%; ">
    <div class="pr activityLayer">
        <p class="justify">距离华为P9、小米平衡车、VR眼镜只差一步，马上注册即可抽取超级大奖！</p>
        <a class="submitBtn2 btnClosed" href="javascript:void(0)" id="canlceRaffle">确 定</a>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function() {
  var demo = document.getElementById("demo");
  var demo1 = document.getElementById("demo1");
  var demo2 = document.getElementById("demo2");
  demo2.innerHTML=document.getElementById("demo1").innerHTML;
  function Marquee(){
      if(demo.scrollLeft-demo2.offsetWidth>=0){
          demo.scrollLeft-=demo1.offsetWidth;
      }
      else{
          demo.scrollLeft++;
      }
  }
  setInterval(Marquee,30);
});
</script>
<!--弹出层-->
<!--未登录弹出层-->
<div id="alertLayer-8" class="unloginMask regMask" style="display: none; width: 100%; height:40%;">
    <div class="pr" id="layerBody">
        <img src="static/images/regFailMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed font-white pa" href="javascript:void(0)"><i class="icon icon-error2"></i></a>
        <p class="errorCon red pa">注册失败</p>
    </div>
</div>
</body>
</html>