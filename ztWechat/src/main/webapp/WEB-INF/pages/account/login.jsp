<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
    <script type="text/javascript" src="static/js/account/common_account.js"></script>
    <script type="text/javascript" src="static/js/account/login.js"></script>
    <title>登录</title>
    <script type="text/javascript">
    function refreshCode(){
      var srcPath = ($("#securityCode").attr("src")).split("?")[0];
      $("#securityCode").attr("src", srcPath+"?timestamp="+Math.random());
    }
    </script>
</head>
<body>
<div id="box" class="bg-white">
    <div class="input-group">
    <form name="forms" id="forms">
        <ul>
            <div id="messageBox" class="tc red pb10"></div>
            <li id="tel-li">
                <div class="input-wrap">
                    <i class="icon icon-phone"></i>
                    <a class="icon icon-error fr" style="display: none"></a>
                    <span class="span">
                    <input type="tel" class="input-text" id="tel" name="tel" maxlength="11" placeholder="请输入手机号" onkeyup="checkFormButton('keyUp',this)" onblur="checkFormButton('onblur',this)"/>
                    </span>
                </div>
                <div id="error-tel" class="error-tips red"></div>
            </li>
            <li id="password-li">
                <div class="input-wrap">
                    <!-- 隐藏密码 -->
                    <a href="javascript:lookPwd('pwd','请输入登录密码');" id="look-pwd" class="icon icon-unlook fr"></a>
                    <!-- 显示密码 -->
                    <!-- <a href="javascript:lookPwd();" class="icon icon-look orange fr"></a> -->
                    <!-- 清除密码 -->
                    <a class="icon icon-error fr" style="display: none"></a>
                    <i class="icon icon-password"></i>
                    <span id="lookBox-pwd" name="lookBox-pwd" class="span">
                    <input type="password" class="input-text" id="pwd" name="pwd" placeholder="请输入登录密码" maxlength="20" onkeyup="checkFormButton('keyUp',this)" onblur="checkFormButton('onblur',this)"/>
                    </span>
                </div>
                <div id="error-password" class="error-tips red"></div>
            </li>
            <li id="verify-code-li">
                <div id="verifyCodeDiv" class="input-wrap input-codeWeb-wrap" style="display:none">
                    <a href="javascript:void(0);" onclick="refreshCode();"><img id="securityCode" src="<%=request.getContextPath()%>/Kaptcha.jpg"  class="fr"/></a>
                    <a class="icon icon-error fr"></a>
                    <i class="icon icon-codeWeb"></i>
                    <span class="span">
                    <input type="text" class="input-text" id="verifyCode" name="verifyCode" maxlength="4" placeholder="请输入右侧验证码" onkeyup="checkFormButton('keyUp',this)" onblur="checkFormButton('onblur',this)"/>
                    </span>
                </div>
                <div id="error-verify-code" class="error-tips red"></div>
            </li>
            <li>
               <e:if test="@source == null">
                <input type="button" class="btn-login-gray" id="btn-login" name="btn-login" value="登录并绑定公众号" disabled="true"/>
               </e:if>
               <e:if test="@source != null">
                 <input type="button" class="btn-login-gray" id="btn-login" name="btn-login" value="登录" disabled="true"/>
               </e:if>
            </li>
            <li>
                <div class="pt10">
                    <a href="account/toResetLoginPwd" class="orange fr">忘记密码</a>
                </div>
            </li>
        </ul>
    </form>
    </div>
    <!-- 前一个页面的url -->
    <input id="interceptUrl" type="hidden" value="<e:property value="@interceptUrl"/>">
     <input id="returnUrl" type="hidden" value="<e:property value="@returnUrl"/>">
     <input id="projectAction" type="hidden" value="<e:property value="@projectAction"/>">
  <%--   <div id="interceptUrl" style="display: none"><e:property value="@interceptUrl"/></div> 
    <div id="returnUrl" style="display: none"><e:property value="@returnUrl"/></div>--%>
</div>
</body>
</html>