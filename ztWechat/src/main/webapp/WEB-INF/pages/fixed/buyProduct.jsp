<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="e" uri="/emvc-tags" %>
    
<html>
<head lang="en">
    <script type="text/javascript" src="static/js/fixed/buyProduct.js"></script>
    <title>定期理财确认购买</title>
</head>
<body>
<input type="hidden" id="productId" value="<e:property value="@PRO_NO"/>">
<input type="hidden" id="promoNo" value="<e:property value="@promoNo"/>">
<input type="hidden" id="periodNo" value="<e:property value="@PERIOD_NO"/>">
<input type="hidden" id="contractUrl" value="<e:property value="@contractUrl"/>">
<input type="hidden" id="totalPays" value="<e:property value="@TOTAL_PAYS"/>">
<input type="hidden" id="ArrivalAmount" value="<e:property value="@ArrivalAmount"/>">
<input type="hidden" id="channel" value="<e:property value="@channel"/>"/>
<input type="hidden" id="expectType" value="<e:property value="@expectType"/>"/>
<input type="hidden" id="orderNum" value="<e:property value="@orderNum"/>"/>
<input type="hidden" id="token" name="token" value="<e:property value="@token"/>"/>
<div id="box">
    <div class="buyCard bg-white pr">
        <ul class="buyInfo">
            <li><i class="dot orange font-10">●</i> 购买产品：<span class="font-20 tc block"><e:property value="@PRO_NAME"/></span> <span class="font-12 tc block">第<e:property value="@PERIOD_NO"/>期</span></li>
            <li></li>
            <li><i class="dot orange font-10">●</i> 购买金额：<span class="tc block"><i class="orange font-20"><e:property value="@_formater.formateMoney(TOTAL_PAYS)"/> 元
            <!-- expectType不为空，是投资换产品，不展示其他优惠信息 -->
           <e:if test="${empty expectType }">
            <e:if test="@PROMO_FLAG==true">
    <e:if test="@'lcj'.equals(promoType)"> +<span class="orange font-20"><e:property value="@_formater.formateMoney(promoPrincipal)"/></span> 元理财金</e:if>        
      <e:elseif test="@'mjq'.equals(promoType)"> +<span class="orange font-20"><e:property value="@_formater.formateMoney(promoPrincipal)"/></span> 元本金</e:elseif>
      <e:else></e:else>
            </e:if>
           </e:if>
            </i></span>
              </li>
             <e:if test="${empty expectType }"> 
	            <li>
	                <i class="dot orange font-10">●</i> 预期到账收益：<span class="tc block"><i class="orange font-20"><e:property value="@_formater.formateMoney(expectIncomeNoVoucher)"/> 元
	                <e:if test="@PROMO_FLAG==true">
	         +<span class="orange font-20">
	         <e:if test="@'mjq'.equals(promoType)">
	            <e:property value="@_formater.formateMoney(addtionalIncome+promoPrincipal)"/></span> 元<br>
	         </e:if><e:else>
	           <e:property value="@_formater.formateMoney(addtionalIncome)"/></span> 元<br>
	         </e:else>
	           <span class="givInfo">
	    <e:if test="@'lcj'.equals(promoType)">(赠送<e:property value="@_formater.formateMoney(promoPrincipal)"/> 元理财金对应收益)</e:if>        
	      <e:elseif test="@'mjq'.equals(promoType)">(赠送<e:property value="@_formater.formateMoney(promoPrincipal)"/> 元本金及对应收益)</e:elseif>
	      <e:else><span class=" font-18"> （额外赠送<e:property value="@_formater.formatNumber(addRate)"/>%收益 ）</span></e:else>       
	       </span>
	            </e:if>
	           </i></span>   
	            </li>
            </e:if>
        </ul>
        <img src="static/images/bg-buy.png" class="catImg">
    </div>
    <div class="input-group withdraw-input-group">
        <ul>
            <li>
                <div class="layou-04 input-wrap2 bg-white">
                    
                    <e:if test="@'lcj'.equals(promoType)||'mjq'.equals(promoType)">
                    <div class="input-wrap">
                      <label>交易金额</label>
                        <span><i class="inputAccout orange"><e:property value="@TOTAL_PAYS"/></i></span>
                         </div>
                    </e:if>
                   
                    <div class="input-wrap">
                        <!--<a href="javascript:void(0)" class="icon icon-unlook fr"></a>-->
                          <a href="javascript:void(0)" id="look" onclick="changePasswordType('tradePwd')" class="icon icon-unlook fr"></a>
                        <a href="javascript:void(0)" id="errorPwd" onclick="pwdClick()" class="icon icon-error fr"></a>
                        <label>交易密码</label>
                        <span><input type="password" class="input-text" id="tradePwd" name="" placeholder="请输入交易密码"/></span>
                    </div>
                </div>
                <div class="error-tips red">
                    <a href="account/toResetTradePwd" class="forgetCode orange fr">忘记密码</a>
                 <div id="error" style="display:none"> </div>    
                </div>
            </li>
             <li>
                <div class="checkedBox pt10" style="margin-top:40px">
                    <input type="checkbox" class="agree-box" id="agreeBox"  checked="checked"/>
                    <i  class="icon icon-checkbox orange" id="checked"></i>
                    已阅读并同意遵守<a href="fixed/toLmProtocol" class="orange">《懒猫金服服务协议》</a>和<a href="fixed/toProductContract?productId=<e:property value="@PRO_NO"/>&amount=<e:property value="@TOTAL_PAYS"/>" class="orange">《产品合同》</a>
                </div>
            </li>
            <li class="pt30">
             <!--    <input type="button" class="btn-login-gray" id="sumbit" name="sumbit" value="确认购买"/> -->
                <input type="button" class="btn-login" id="sumbit" name="" value="确认购买"/>
            </li>
        </ul>
    </div>
</div>
<!--购买失败-弹出层-->
<div id="mask" style="display: none"></div>
<!--购买失败弹窗抢光了-弹出层-->
<!-- <div id="alertLayer-3" class="unloginMask regMask withdrawMask" style="display: none; width: 100%; height:40%;">
    <div class="pr" id="layerBody">
        <img src="static/images/buyFailMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed font-white pa" href="javascript:void(0)"></a>
        <p class="errorCon red pa"></p>
        <div class="btnMaskArea tc pa">
            <a href="fixed/toPurchaseProduct">去看看其他产品</a>
        </div>
    </div>
</div> -->

<!--购买失败弹窗重新购买-弹出层-->
<%-- <div id="alertLayer-4" class="unloginMask regMask rechargeMask" style="width: 100%; height:40%; display: none">
    <div class="pr" id="layerBody1">
        <img src="static/images/buyFailMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed font-white pa" href="javascript:void(0)"></a>
        <p class="errorCon red pa"></p>
        <div class="btnMaskArea tc pa">
            <e:if test="@channel != 'ADVISOR'">
                <e:if test="@orderNum!=null&&orderNum!=''">
                 <a href="invForPro/toInvestForTravelList">重新购买</a>
                </e:if>
                <e:else>
                 <a href="fixed/toPurchaseProduct?productId=<e:property value="@PRO_NO"/>">重新购买</a>
                </e:else>
            </e:if>
            <e:if test="@channel == 'ADVISOR'">
              <a href="fixed/toRedPacketsDetail">重新购买</a>
            </e:if>
        </div>
    </div>
</div> --%>
<!--交易密码输入错误 弹出层-->
<div id="alertLayer" class="bg-white radius1" style="display: none; width: 90%; min-height: 25%;">
    <div class="errorTexts br-bottom orange tc" id="alertLayer-5Message"></div>
    <div class="btnMaskArea btnMaskArea1 tc">
        <a href="javascript:void(0)" onclick="clean()">再次输入</a>
        <a href="account/toResetTradePwd">重置密码</a>
    </div>
</div>
<!--交易密码输入错误 弹出层-->
<div id="alertLayer-6" class="bg-white radius1" style="display: none; width: 90%; min-height: 25%;">
    <div class="errorTexts br-bottom orange tc" id="alertLayer-6Message"><i class="icon icon-tips" href="javascript:void(0)" onclick="clean()"></i> </div>
    <div class="btnMaskArea btnMaskArea1 tc">
        <a href="account/toResetTradePwd">重置密码</a>
        <a href="javascript:void(0)" onclick="clean()">取消</a>
    </div>
</div>
<!--确认购买更换期号未换资产 弹出层-->
<div id="alertLayer-7" class="bg-white radius1" style="display: none; width: 90%; min-height: 28%;">
    <div class="withdrawIntro">
        <div class="proTimeout orange">
        </div>
        <div class="btnMaskArea tc mt30">
            <a href="javascript:void(0)" onclick="buyAgain()">确认购买</a>
        </div>
    </div>
</div>
<!--系统异常-弹出层-->
<!-- <div id="alertLayer-8" class="unloginMask regMask rechargeMask" style="display: none; width: 100%; height:40%;">
    <div class="pr">
        <img src="static/images/errorMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed font-white pa" href="javascript:void(0)"><i class="icon icon-error2" onclick="clean()"></i></a>
        <p class="errorCon red pa">系统异常，请稍后重试哦</p>
        <div class="btnMaskArea tc pa">
            <a href="javascript:void(0)" onclick="clean()">OK</a>
        </div>
    </div>
</div> -->
<%@include file = "../inc/failMask.jsp" %>
<a class="service" href="focus/toContactService"><i class="icon icon-service font-white"></i></a>
</body>
</html>