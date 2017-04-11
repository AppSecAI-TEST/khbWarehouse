<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags"%>
<html>
<head lang="en">
<script type="text/javascript" src="static/js/fixed/queryCoupon.js"></script>
<title>我的卡券</title>
</head>
<body>
	<div id="box">
		<div class="bg-white">
			<div class="tab_nav tabNav voucherNav">
				<ul>
					<li onclick="getUnuseCoupon()">未使用</li>
					<li onclick="getUsedCoupon()">已使用</li>
					<li class="on" onclick="getExpiredCoupon()">已过期</li>
				</ul>
			</div>
		</div>
		<div class="tab_con pb15">
				<div class="voucherList">
				<e:if test="${!empty expiredList}">
					<e:iterator list="@expiredList" var="items">
                    <!-- 红包 -->
                    <e:if test="@items.coupon.isRedPacket == 1">
                        <div class="voucherList voucherRed pr">
                          <img src="static/images/voucherRed.png">
                          <div class="voucherCon font-white pa">
                              <e:if test="@items.coupon.couponType.toString()=='INTEREST_ADD'">
                                  <p class="font-16"><e:property value="@items.coupon.couponName"/>加息<span class="font-25"><e:property value="@items.coupon.increaseInterest" />%</span></p>
                                  <p class="expires pa font-12">有效期至：<span id="time"><e:property value="@_formater.formatDate(items.validityTimeEnd)" /></span></p>
                              </e:if>
                              <e:elseif test="@items.coupon.couponType.toString()=='FULL_ADD'">
                                  <e:if test="@items.coupon.discountType.toString()=='INTEREST'">
                                      <p class="font-16">
                                          <e:property value="@items.coupon.couponName"/>
                                          <span class="font-25">
                                            <e:property value="@_formater.formateMoney(items.coupon.couponAmount)" />
                                          </span>
                                          <span class="font-18"> 元</span>（满
                                          <e:property value="@_formater.formateMoney(items.coupon.minInvestAmount)"/> 元使用）
                                      </p>
                                      <p class="expires pa font-12">有效期至：<span id="time"><e:property value="@_formater.formatDate(items.validityTimeEnd)" /></span></p>
                                  </e:if>
                                  <e:elseif test="@items.coupon.discountType.toString()=='PRINCIPAL'">
                                      <p class="font-16"><e:property value="@items.coupon.couponName"/>满
        								  <e:property value="@_formater.formateMoney(items.coupon.minInvestAmount)" /> 元 加
                                          <span class="font-25">
                                              <e:property value="@_formater.formateMoney(items.coupon.couponAmount)" />
                                          </span> 元
                                      </p>
                                      <p class="expires pa font-12">有效期至：<span id="time"><e:property value="@_formater.formatDate(items.validityTimeEnd)" /></span></p>
                                  </e:elseif>
                              </e:elseif>
                          </div>
                        </div>
                    </e:if>   
                    <!-- 非红包 --> 
                    <e:elseif test="@items.coupon.isRedPacket == 0">   
                        <div class="voucherList pr">
                           <img src="static/images/vouchered.png">
                           <div class="voucherCon font-white pa">
        						<e:if test="@items.coupon.couponType.toString()=='INTEREST_ADD'">
        							
        									<p class="font-16">
        										加息<span class="font-25"><e:property
        												value="@items.coupon.increaseInterest" />%</span>
        									</p>
        									<p class="expires pa font-12">
        										有效期至:
        										<span id="time"><e:property value="@_formater.formatDate(items.validityTimeEnd)" /></span>
        									</p>
        									<span class="voucherType font-16 pa">加息券</span>
        								
        						</e:if>
        						<e:elseif test="@items.coupon.couponType.toString()=='FULL_ADD'">
        							<e:if test="@items.coupon.discountType.toString()=='INTEREST'">
        										<p class="font-16">
        											<span class="font-25"><e:property
        													value="@_formater.formateMoney(items.coupon.couponAmount)" /></span><span class="font-18"> 元</span>（满<e:property value="@_formater.formateMoney(items.coupon.minInvestAmount)"/> 元使用）
        										</p>
        										<p class="expires pa font-12">
        											有效期至：
        											<span id="time"><e:property value="@_formater.formatDate(items.validityTimeEnd)" /></span>
        										</p>
        										<span class="voucherType font-16 pa">理财金</span>
        							</e:if>
        							<e:elseif
        								test="@items.coupon.discountType.toString()=='PRINCIPAL'">
        										<p class="font-16">
        											满
        											<e:property value="@_formater.formateMoney(items.coupon.minInvestAmount)" /> 元
        											加<span class="font-25"><e:property
        													value="@_formater.formateMoney(items.coupon.couponAmount)" /></span> 元
        										</p>
        										<p class="expires pa font-12">
        											有效期至:
        											<span id="time"><e:property value="@_formater.formatDate(items.validityTimeEnd)" /></span>
        										</p>
        										<span class="voucherType font-16 pa">投资券</span>
        							</e:elseif>
        						</e:elseif>
                            </div>
                        </div>
                      </e:elseif>
					</e:iterator>
					</e:if>
					<e:if test="${empty expiredList}">
					<p class="tc">亲，暂无已过期卡券</p>
					</e:if>
				</div>
		</div>
	</div>
	<div class="h1-6"></div>
</body>
</html>