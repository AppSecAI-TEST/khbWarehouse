<%@page import="com.yeepay.g3.app.lmweChat.utils.LmConstants" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<%
  String sysVersion = LmConstants.sysVersion;
%>
<html>
<head lang="en">
    <script type="text/javascript" src="static/js/common/format_common.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/fixed/seckillActivity.js?v=<%=sysVersion %>"></script>
    <title>秒客盈</title>
</head>
<body>
<input type="hidden" id="saleDate" name="saleDate" value="<e:property value="@com.yeepay.g3.app.lmweChat.utils.GetParamUtils.readSeckillConfig()['saleDate']"/>"/>
<input type="hidden" id="productId" name="productId" value="<e:property value="@pdr.productId"/>"/>
<input type="hidden" id="status" name="status" value="<e:property value="@pdr.status"/>"/>
<input type="hidden" id="cillAmount" name="cillAmount" value="<e:property value="@pdr.cillAmount"/>"/>
<input type="hidden" id="unitAmount" name="unitAmount" value="<e:property value="@pdr.unitAmount"/>"/>
<input type="hidden" id="tradeUpper" name="tradeUpper" value="<e:property value="@pdr.tradeUpper"/>"/>
<input type="hidden" id="yearRate" name="yearRate" value="<e:property value="@pdr.rate"/>"/>
<input type="hidden" id="termDay" name="termDay" value="<e:property value="@pdr.term"/>"/>
<input type="hidden" id="accountAmount" name="accountAmount" value="<e:property value="@accountAmount"/>"/>
<input type="hidden" id="surplusAmount" name="surplusAmount" value="<e:property value="@surplusAmount"/>"/>
<input type="hidden" id="expectIncome"/>
<input id="rechargeAmount" type="hidden" value="0" />
<input id=isBankCard type="hidden" value="<e:property  value="@isBankCard"/>" />
<div class="bg-white proArea">
    <div class="financial-ad">
        <img src="static/images/financial-ad.jpg">
        <a href="javascript:void(0)" class="btnClick pa"></a>
        <a href="javascript:void(0)" class="financialClosed pa"></a>
    </div>
    <!--弹出层-->
    <div id="mask"></div>
    <div id="alertLayer" class="bg-white" style="display: none; width: 90%; min-height: 33%;">
        <div class="withdrawIntro">
            <div class="pb15">定期理财挂钩金融资产收益权。由银行、信托、保险等大型国有金融机构进行风险审查及兑付管理，具备较低风险、较低门槛、多层保障等特点，是您稳健投资的优质选择。</div>
            <div class="tc mt15"><a href="javascript:void(0)" class="btnClosed btn-small">我知道了</a></div>
        </div>
    </div>
    <!--弹出层end-->
    <div class="proSlider proSlider-1">
        <div class="slider">
            <!-- 倒计时 -->
            <e:if test="@com.yeepay.g3.app.lmweChat.utils.GetParamUtils.readSeckillFlag() == 'status1'">
                <div class="countDown pr">
                    <img src="static/images/countDown.jpg" alt=""/>
                    <p class="countDown-text pa">本期秒杀倒计时</p>
                    <div class="countDown-time pa">
                        <span id="t_h">00</span>
                        <span id="t_m">00</span>
                        <span id="t_s">00</span>
                    </div>
                </div>
            </e:if>
            <div class="sliderArea">
                <ul class="slider-main">
                    <li class="slider-panel">
                        <div class="product-content product-content-new">
                            <h3>
                                <e:property value="@pdr.productName"/> - 第<e:property value="@pdr.periodNo"/>期
                                <a class="past fr" href="seckillActivity/toSeckillRecord">往期 <i class="icon icon-arrow-right"></i> </a>
                            </h3>
                            <div class="energy-ball energy-ball-new pr">
                                <img src="static/images/energy-ball-new.png" alt=""/>
                                
                                <div class="slider-page slider-page-new pa">
                                     <div class="slider-page-left fl">
                                         <div class="slider-page-1">
                                             <p><e:property value="@pdr.term"/><span>天</span></p>
                                             <p>持有期限</p>
                                         </div>
                                         <div class="slider-page-2">
                                             <p><e:property value="@_formater.formateMoney(pdr.tradeUpper)"/><span>元</span></p>
                                             <p>每期最高购买</p>
                                         </div>
                                     </div>
                                     <div class="slider-page-right fr">
                                         <div class="slider-page-1">
                                             <p><e:property value="@_formater.formateMoney(pdr.cillAmount)"/><span>元</span></p>
                                             <p>起购金额</p>
                                         </div>
                                         <div class="slider-page-2">
                                             <p><e:property value="@pdr.userMaxNum"/><span>笔</span></p>
                                             <p>每期限购笔数</p>
                                         </div>
                                     </div>
                                     <div class="energy-text">
                                        <p class="orange p-1" id="yearRateP"><e:property value="@pdr.rate"/><span class="orange">%</span></p>
                                        <p class="p-2 orange">预计年化收益率</p>
                                        <p class="p-3 font-white"><a href="fixed/productDetailInfo?productId=<e:property value="@pdr.productId"/>">查看详情</a></p>
                                    </div>
                                </div>
                            </div> 
                            <div class="scale scale-new pr">
                                <img src="static/images/scale.png" alt=""/>
                                <table class="pa">
                                    <tr class="date-text">
                                        <td width="25%">购买日</td>
                                        <td width="25%">计息日</td>
                                        <td width="25%">到期日</td>
                                        <td>到账日</td>
                                    </tr>
                                    <tr class="date-num">
                                        <td width="25%" id="buyDay"><e:property value="@_formater.formatDate(pdr.tradeDay)"/></td>
                                        <td width="25%" id="incomeDay"><e:property value="@pdr.incomeDay"/></td>
                                        <td width="25%" id="expireDay"><e:property value="@pdr.expireDay"/></td>
                                        <td>两个工作日后</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <!-- 倒计时 -->
                        <e:if test="@com.yeepay.g3.app.lmweChat.utils.GetParamUtils.readSeckillFlag() == 'status1'">
                            <div class="investment-cap mt10">本期秒杀产品总额：<e:property value="@_formater.formateMoney(pdr.saleAbleAmount)"/>元</div>
                            <div class="bg-white layou-04 mt20">
                                <p class="modTips font-10 tl"><i class="icon icon-tips font-12"></i>提前充值到懒猫账户，秒杀更方便哦！</p>
                                <p class="modTips font-10 tl"><i class="icon icon-tips font-12"></i>活动开始时，刷新下页面，抢购速度更快哦!</p>
                            </div>
                        </e:if>
                        <!-- 秒杀中或秒杀完 -->
                        <e:if test="@com.yeepay.g3.app.lmweChat.utils.GetParamUtils.readSeckillFlag() == 'status2'">
                            <!-- 秒杀中 -->
                            <e:if test="@pdr != null || pdr.status.toString() == 'SALING'">
                                <div class="investment-cap mt10">秒杀产品总额：<e:property value="@_formater.formateMoney(pdr.saleAbleAmount)"/>元</div>
                                <div class="investment-cap investment-cap-new">剩余投资额度: <e:property value="@_formater.formateMoney(surplusAmount)"/> 元</div>
                            </e:if>
                            <!-- 秒杀完成 -->
                            <e:else>
                                <div class="investment-cap mt10">秒杀产品总额：<e:property value="@_formater.formateMoney(pdr.saleAbleAmount)"/>元</div>
                                <div class="investment-cap investment-cap-new">秒杀产品购买人次: <e:property value="@pdr.participants"/> 人</div>
                            </e:else>
                        </e:if>
                    </li>
                </ul>
                <!-- 倒计时 -->
                <e:if test="@com.yeepay.g3.app.lmweChat.utils.GetParamUtils.readSeckillFlag() == 'status1'">
                    <div class="sliderBtnArea">
                    <!-- 已登录 -->
                        <e:if test="@noLogin == 'NO'">
                            <a href="asset/toRecharge?ret=seckillPer" class="sliderBtn sliderBtn-1">充值</a>
                        </e:if>
                        <e:else>
                            <a href="javascript:void(0);" class="sliderBtn sliderBtn-1" id="toRecharge" onclick="toNoLoginRecharge()">充值</a>
                        </e:else>
                    </div>
                </e:if>
                <!-- 秒杀完成 -->
                <e:if test="@com.yeepay.g3.app.lmweChat.utils.GetParamUtils.readSeckillFlag() == 'status2'">
                    <!-- 秒杀完 -->
                    <e:if test="@pdr == null || pdr.status.toString() != 'SALING'">
                        <div class="sliderBtnArea">
                            <a href="javascript:void(0);" class="sliderBtn">本期已售罄</a>
                            <p>下期抢购时间<e:property escape="false" value="@com.yeepay.g3.app.lmweChat.utils.GetParamUtils.readSeckillConfig()['nextDate']"/></p>
                        </div>
                    </e:if>
                </e:if>
            </div>
        </div>
    </div>
    <e:if test="@com.yeepay.g3.app.lmweChat.utils.GetParamUtils.readSeckillFlag() == 'status2'">
        <!-- 秒杀中 -->
        <e:if test="@pdr != null && pdr.status.toString() == 'SALING'">
            <div class="bg-white layou-04 layou-new">
                <ul class="modList modList1 modList-new">
                    <li class="pb5">
                        <p class="orange">购买金额：<input type="text" class="amount-text" id="buyAmount" name="buyAmount" onblur="getExpectIncome()" placeholder="起购金额<e:property value="@_formater.formateMoney(pdr.cillAmount)"/>元,递增金额<e:property value="@_formater.formateMoney(pdr.unitAmount)"/>元"/> 元</p>
                        <!-- 错误提示 -->
                        <p id="messageError" class="modTips font-10 red"><!-- <i class="icon icon-error2 font-12"></i> --></p>
                        <p class="modTips font-10"><i class="icon icon-tips font-12"></i> 请直接输入转入金额，账户余额不足将先进行充值</p>
                        <e:if test="@pdr.userMaxNum != null && pdr.tradeUpper != 0">
                            <p class="modTips font-10"><i class="icon icon-tips font-12"></i> 本期限购<e:property value="@pdr.userMaxNum"/>笔，单笔最高<e:property value="@_formater.formateMoney(pdr.tradeUpper)"/>元，购买时不可使用优惠券</p>
                        </e:if>
                    </li>
                    <e:if test="@accountAmount != 0">
                        <li>账户余额：<e:property value="@_formater.formateMoney(accountAmount)"/> 元</li>
                    </e:if>
                    <li id="expectIncomeSpan" style="display: none"></li>
                </ul>
            </div>
        </e:if>
    </e:if>
</div>
<div class="h1-2"></div>
<e:if test="@com.yeepay.g3.app.lmweChat.utils.GetParamUtils.readSeckillFlag() == 'status2'">
    <!-- 秒杀中 -->
    <e:if test="@pdr != null && pdr.status.toString() == 'SALING'">
        <e:if test="@noLogin == 'NO'">
            <div class="btnBot"><input id="submit" onclick="toBuy()" type="button" value="立即秒杀" class="btnBuy"/></div>
        </e:if>
        <e:else>
            <div class="btnBot"><input id="submit" onclick="toBuyNoLogin()" type="button" value="立即秒杀" class="btnBuy"/></div>
        </e:else>
    </e:if>
</e:if>
<!--未登录弹出层-->
<div id="alertLayer-5" class="unloginMask  radius1" style="display: none; width: 100%; height: 100%;">
    <div class="pr">
      <img src="static/images/unloginMask.png" class="repeatImg" alt="" />
      <a class="btnClosed font-white pa" href="javascript:void(0)"><i
        class="icon icon-error2" onclick="clean()"></i></a> <a
        href="javascript:void(0)" class="btnReg pa"></a> <a
        href="javascript:void(0)" class="btnLogin pa"></a>
    </div>
</div>
</body>
</html>