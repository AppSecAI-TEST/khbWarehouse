<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags"%>
<html>
<head lang="en">
<script type="text/javascript" src="static/js/common/fixed_common.js"></script>
<script type="text/javascript" src="static/js/fixed/redPackets.js"></script>
<title>定期理财</title>
</head>
<body>
  <!-- 剩余投资额度 -->
  <input id="surplus" type="hidden"
    value="<e:property value="@productDetailResultDto.saleAbleAmount"/>">
  <!-- 起购金额 -->
  <input id="cillAmount" type="hidden"
    value="<e:property value="@productDetailResultDto.cillAmount"/>">
  <!-- 递增金额-->
  <input id="unitAmount" type="hidden"
    value="<e:property value="@productDetailResultDto.unitAmount"/>">
  <!-- 账户余额 -->
  <input id="accountAmount" type="hidden"
    value="<e:property value="@accountAmount"/>">
  <!-- 产品期限 -->
  <input id="termDay" type="hidden"
    value="<e:property value="@productDetailResultDto.term"/>">
  <!-- 年化收益率 -->
  <input id="yearRate" type="hidden"
    value="<e:property  value="@productDetailResultDto.rate"/>" />
  <!-- 产品id -->
  <input id="productId" type="hidden"
    value="<e:property value="@productDetailResultDto.productId"/>">
  <input id="rechargeAmount" type="hidden" value="0" />
  <input id="expectIncome" type="hidden" />
  <input id="expectIncomeNoVoucher" type="hidden" />
  <input id="channel" type="hidden" value="<e:property value="@productDetailResultDto.channel"/>"/>
         
  <div id="box" class="bg-white">
  <div id="mask" ></div>
    <div class="proArea cardArea">
        <div class="returnCard pr bg-white tc" onclick="getInfo(<e:property value="@productDetailResultDto.productId"/>)">
            <a href="javascript:void(0)" class="orange">
                <p class="font-16">预计年化收益率</p>
                <p id="yearRateP" class="font-50"><e:property value="@productDetailResultDto.rate"/></p>
                <p class="font-12">查看详情&gt;</p>
            </a>
            <div class="returnInfo orange br-top">
                <ul>
                    <li>期限<span class="font-25"><e:property value="@productDetailResultDto.term"/></span>天</li>
                    <li>起投<span class="font-25"><e:property value="@_formater.formateMoney(productDetailResultDto.cillAmount)"/></span>元</li>
                    <li>顾问专享<span class="font-25">&nbsp;</span></li>
                </ul>
            </div>
        </div>
        <div class="dateBar orange dateBarSmall pr bg-white clearfix">
            <table cellpadding="0" cellspacing="0" width="90%">
                <tbody>
                <tr>
                    <th width=25%>购买日</th>
                    <th width=25%>计息日</th>
                    <th width=25%>到期日</th>
                    <th>到账日</th>
                </tr>
                <tr>
                    <td id="buyDay"><e:property value="@_formater.formatDate(productDetailResultDto.tradeDay)"/></td>
                    <td id="incomeDay"><e:property value="@_formater.formatDate(productDetailResultDto.incomeDay)"/></td>
                    <td id="expireDay"><e:property value="@_formater.formatDate(productDetailResultDto.expireDay)"/></td>
                    <td><span class="font-10">两工作日后</span></td>
                </tr>
                </tbody>
            </table>
            <ul class="circleList pa">
                <li><i class="icon icon-circle"></i></li>
                <li><i class="icon icon-circle"></i></li>
                <li><i class="icon icon-circle"></i></li>
                <li><i class="icon icon-circle"></i></li>
            </ul>
        </div>
    </div>
    <h2 class="termTitle">第<e:property value="@productDetailResultDto.periodNo"/>期</h2>
    <div class="bg-white layou-04 mb18">
        <ul class="modList">
            <li>剩余投资额度：<e:property value="@_formater.formateMoney(productDetailResultDto.saleAbleAmount)"/> 元</li>
            <li class="orange">购买金额：<input type="text" class="amount-text" id="buyAmount" name="buyAmount" onblur="getExpectIncome()" placeholder="起购金额1000元,递增金额100元"/> 元</li>
            <p id="messageError" class="modTips moderror font-10" style="display: none;">
              <i class="icon icon-error2 font-12"></i>
            </p>
            <!-- 获取红包优惠券信息 -->
            <li id="voucher" class="orange " onclick="getVouchersInfo()" style="display: none"></li>
            <li id="modVouchered" class="modVouchered" style="display: none"></li>
            <e:if test="@accountAmount==0">
            </e:if>
            <e:else>
              <li>懒猫账户余额：<e:property
                  value="@_formater.formateMoney(accountAmount)" /> 元
              </li>
            </e:else>
            <!-- 预计到账收益 -->
            <li id="expectIncomeSpan" class="bg-white" style="display: none">
            
          
            
            <!-- <li class="modVouchered">
                <p>
                    <span class="fr modEdit"><a href="#">修改</a> ｜ <a href="#">删除</a></span>
                    已选优惠券:
                    <span class="redEnvelope"></span>
                </p>
                <p class="modTips lcAccont orange">投资时赠送100元本金</p>
            </li> -->
        </ul>
    </div>
</div>
  <!--弹出层-->
            <div id="alertLayer-3" class="bg-white"
              style="display: none; width: 90%; min-height: 20%;">
              <h2 class="alertTitle orange br-bottom tc">可使用优惠券
                (每次仅可使用一张)</h2>
              <div class="vouchersList pr" >
                <ul id="voucherList" style="overflow-y:scroll;height: 180px">
      
                </ul>
                <a href="javaScript:void(0)" class="font-white btnClosed"><i
                  class="icon icon-error2"></i></a>
              </div>
            </div>
            <!-- 弹出层 错误信息 -->
            <div id="alertLayer-8" class="unloginMask regMask rechargeMask"
              style="display: none; width: 90%; height: 40%;">
              <div class="pr">
                <img src="static/images/errorMask.png" class="repeatImg"
                  alt="" /> <a class="btnClosed font-white pa"
                  href="javascript:void(0)"><i class="icon icon-error2"
                  onclick="clean()"></i></a>
                <p class="errorCon red pa">系统异常，请稍后重试哦</p>
                <div class="btnMaskArea tc pa">
                  <a href="javascript:void(0)" onclick="clean()">OK</a>
                </div>
              </div>
            </div>
<div class="btnBot"><input id="submit" type="button" value="立即购买" onclick="toBuy()" class="btnBuy"/></div>

<!--最外弹出层-->
 <a class="service" href="focus/toContactService"><i class="icon icon-service font-white"></i></a>
</body>
</html>