<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags"%>
<html>
<head lang="en">
<script type="text/javascript" src="static/js/assets/cash_in.js"></script>
<title>提现</title>

</head>
<body>
  <input id="availableCashIn" type="hidden" value="<e:property value="@availableCashIn"/>">
  <input type="hidden" id="token" name="token" value="<e:property value="@token"/>"/>
<div id="box" class="bg-white">
    <h2 class="titleWithdraw orange br-bottom">提现到银行卡</h2>
    <div class="input-group withdraw-input-group">
      <ul>
        <li>
          <div class="input-wrap">
            <div class="bankImg bank<e:property value="@BANK_ID"/>"></div>
            <label class="bankLabel"><e:property
                value="@BANK_LOGO_NAME" />卡( <e:property
                value="@CARD_NO" />)</label>
          </div>
        </li>
        <li id="amountLi">
          <div class="input-wrap">
            <a href="javascript:void(0)" id="errorAmount"
              onclick="amountClick('amount')" class="icon icon-error fr" style="display: none"></a>
            <label>提现金额</label> <span> <input type="text"
              class="input-text" style="width:65%" id="amount" name=""
              onblur="verifyAmount()"
              placeholder="本次最多可提现<e:property value="@_formater.formateMoney(availableCashIn)"/> 元" /></span>
          </div>
          <div class="error-tips red" id="amountMessage"></div>
        </li>
        <li id="pwdLi">
          <div class="input-wrap">
            <a href="javascript:void(0)" id="look"
              onclick="changePasswordType('pwd')"
              class="icon icon-unlook fr"></a> <a
              href="javascript:void(0)" id="errorPwd"
              onclick="pwdClick('pwd')" class="icon icon-error fr" style="display: none"></a>
            <label>交易密码</label> <span><input type="password"
              class="input-text" id="pwd" name="" placeholder="请输入交易密码" /></span>
          </div>
          <div class="error-tips red" id="pwdMessage">
            <a href="account/toResetTradePwd"
              class="forgetCode orange fr">忘记密码</a>
                </div>
            </li>
            <li class="pt50">
                <input type="button" class="btn-login-gray" id="btn-login" name="" value="免费提现"/>
                <p class="pt10"><a id="explain" href="javascript:void(0)"  class="orange  fr">提现说明</a></p>
            </li>
        </ul>
        <!--弹出层-->
        <div id="mask" style="display: none"></div>
        <div id="alertLayer" class="bg-white" style="display: none; width: 90%; min-height: 20%;">
            <div class="withdrawIntro">
                <p class="pr"><i class="icon icon-circle orange pa"></i> 懒猫账号中的余额，可实时进行提现。</p>
                <p class="pr"><i class="icon icon-circle orange pa"></i> 懒猫账户余额提现到银行卡，下一个工作日内到账。</p>
                <div class="tc mt15"><a href="javascript:void(0)" class="btnClosed btn-small">我知道了</a></div>
            </div>
        </div>
        <!--弹出层end-->
        <!--成功失败弹出层-->
        <!------------------充值成功-进度条---------------->
        <div id="alertLayer-3" class="unloginMask regMask" style="display: none; width: 100%; height:60%;">
            <div class="pr">
                <img src="static/images/withdrawSuccessMask.png" class="repeatImg" alt=""/>
                <!-- 去掉×，强制用户点击按钮跳转页面 -->
               <!--  <a class="btnClosed font-white pa" href="javascript:void(0)"><i class="icon icon-error2"></i></a> -->
                <p class="errorCon successCon red pa">提现已受理，提现金额将在下一个<br/>工作日内到账。</p>
                <div class="btnMaskArea tc pa">
                    <a href="asset/toAsset">查看我的资产</a>
                </div>
            </div> 
            <!--系统异常-弹出层-->
            <!-- <div id="alertLayer-8" class="unloginMask regMask rechargeMask"
                style="display: none; width: 100%; height: 40%;">
                <div class="pr">
                    <img src="static/images/errorMask.png" class="repeatImg" alt="" /> <a
                        class="btnClosed font-white pa" href="javascript:void(0)"><i class="icon icon-error2"
                        onclick="cashInAgain()"></i></a>
                    <p class="errorCon red pa">系统异常，请稍后重试哦</p>
                    <div class="btnMaskArea tc pa">
                        <a href="javascript:void(0)" onclick="cashInAgain()">OK</a>
                    </div>
                </div>
            </div> -->
    </div>
    <%@include file = "../inc/failMask.jsp" %>
  </div>
  </div>
<a class="service" href="focus/toContactService"><i class="icon icon-service font-white"></i></a>
</body>
</html>