<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags"%>
<html>
<head lang="en">
<link rel="stylesheet" href="static/css/swiper.min.css">
<script type="text/javascript" src="static/js/fixed/queryCoupon.js"></script>
<script type="text/javascript" src="static/js/swiper.min.js"></script>
<script type="text/javascript" src="static/js/Swiper.js"></script>
<style>
        .swiper-slide{
            height: 200px;
        }
        .swiper-slide-active{
            height: auto;
        }
    </style>
<title>我的卡券</title>
</head>
<body>
<div id="box">
    <div class="bg-white">
        <div class="tab_nav tabNav voucherNav tabs">
            <ul>
                <li class="on">未使用</li>
                <li>已使用</li>
                <li>已过期</li>
            </ul>
        </div>
    </div>
    <div id="tabs-container" class="swiper-container pb15">
        <div class="swiper-wrapper">
            <div class="swiper-slide">
                <div class="content-slide">
	                 <e:if test="${!empty unuseList }">			
					  <e:iterator list="@unuseList" var="items">
		              <!-- 红包 -->
		              <e:if test="@items.coupon.isRedPacket == 1">
	                  <div class="voucherList voucherRed pr" id="coupon" onclick="queryCouponDetail(<e:property value="@items.id"/>)">
	                      <img src="static/images/voucherRed.png">
	                      <div class="voucherCon font-white pa">
	                          <e:if test="@items.coupon.couponType.toString()=='INTEREST_ADD'">
	                              <p class="font-16"><e:property value="@items.coupon.couponName"/> 加息<span class="font-25"><e:property value="@items.coupon.increaseInterest"/>%</span></p>
	                              <p class="expires pa font-12">有效期至：<span id="time"><e:property value="@_formater.formatDate(items.validityTimeEnd)"/></span></p>
	                          </e:if> 
	                          <e:elseif test="@items.coupon.couponType.toString()=='FULL_ADD'"> 
	                            <e:if test="@items.coupon.discountType.toString()=='INTEREST'">
	                                <p class="font-16"><e:property value="@items.coupon.couponName"/><span class="font-25"><e:property value="@_formater.formateMoney(items.coupon.couponAmount)"/></span><span class="font-18"> 元</span>（满<e:property value="@_formater.formateMoney(items.coupon.minInvestAmount)"/> 元使用）</p>
	                                <p class="expires pa font-12">有效期至：<span id="time"><e:property value="@_formater.formatDate(items.validityTimeEnd)"/></span></p>
	                            </e:if> 
	                            <e:elseif test="@items.coupon.discountType.toString()=='PRINCIPAL'">
	                                <p class="font-16"><e:property value="@items.coupon.couponName"/><span class="font-25"><e:property value="@_formater.formateMoney(items.coupon.couponAmount)"/></span> 元</p>
	                                <p class="expires pa font-12">有效期至：<span id="time"><e:property value="@_formater.formatDate(items.validityTimeEnd)"/></span></p>
	                            </e:elseif> 
	                          </e:elseif> 
	                      </div>
	                  </div>
	              </e:if>
	              <!-- 非红包 -->
	              <e:elseif test="@items.coupon.isRedPacket == 0">
	                <div class="voucherList pr" id="coupon" onclick="queryCouponDetail(<e:property value="@items.id"/>)"> 
	                  <img src="static/images/vouchernone.png">
	                     <div class="voucherCon font-white pa">
		                     <e:if test="@items.coupon.couponType.toString()=='INTEREST_ADD'">
		                        <p class="font-16">加息<span class="font-25"><e:property value="@items.coupon.increaseInterest"/>%</span></p>
		                        <p class="expires pa font-12">有效期至：<span id="time"><e:property value="@_formater.formatDate(items.validityTimeEnd)"/></span></p> 
		                        <span class="voucherType font-16 pa">加息券</span> 
		                     </e:if>
		                     <e:elseif test="@items.coupon.couponType.toString()=='FULL_ADD'">
		                        <e:if test="@items.coupon.discountType.toString()=='INTEREST'">
		                            <p class="font-16"><span class="font-25"><e:property value="@_formater.formateMoney(items.coupon.couponAmount)"/></span><span class="font-18"> 元</span>（满<e:property value="@_formater.formateMoney(items.coupon.minInvestAmount)"/> 元使用）</p> 
		                            <p class="expires pa font-12">有效期至：<span id="time"><e:property value="@_formater.formatDate(items.validityTimeEnd)"/></span></p> 
		                            <span class="voucherType font-16 pa">理财金</span>
		                        </e:if>
		                        <e:elseif test="@items.coupon.discountType.toString()=='PRINCIPAL'">
		                            <p class="font-16">满<e:property value="@_formater.formateMoney(items.coupon.minInvestAmount)"/> 元加<span class="font-25"><e:property value="@_formater.formateMoney(items.coupon.couponAmount)"/></span> 元</p>
		                            <p class="expires pa font-12">有效期至：<span id="time"><e:property value="@_formater.formatDate(items.validityTimeEnd)"/></span></p>
		                            <span class="voucherType font-16 pa">投资券</span>
		                        </e:elseif>
		                     </e:elseif>
	                     </div> 
	                </div>
	               </e:elseif> 
	              </e:iterator>
	             </e:if>
	             <e:if test="${empty unuseList }">
		              <div class="voucherList">
		              	<p class="tc p15">亲，暂无可使用卡券</p>
		              </div>
	             </e:if>
                </div>
            </div>
            <div class="swiper-slide">
                <div class="content-slide">
                	<e:if test="${!empty usedResultList }">
					<e:iterator list="@usedResultList" var="items">
	                <!-- 红包 -->
	                <e:if test="@items.coupon.isRedPacket == 1">
	                    <div class="voucherList voucherRed pr">
	                        <img src="static/images/voucherReded.png">
	                        <div class="voucherCon font-white pa">
	                        
	                            <e:if test="@items.coupon.couponType.toString()=='INTEREST_ADD'">
	                                <p class="font-16"><e:property value="@items.coupon.couponName"/> 加息<span class="font-25"><e:property value="@items.coupon.increaseInterest" />%</span></p>
	                                <p class="used pa font-12">使用日期：<span id="time"><e:property value="@_formater.formatDate(items.useTime)" /></span></p>
	                                <p class="expires pa font-12">使用产品：<e:property value="@items.productName" /></p>
	                            </e:if>
	                            <e:elseif test="@items.coupon.couponType.toString()=='FULL_ADD'">
	                                <e:if test="@items.coupon.discountType.toString()=='INTEREST'">
	                                    <p class="font-16"><e:property value="@items.coupon.couponName"/><span class="font-25"><e:property value="@_formater.formateMoney(items.coupon.couponAmount)" /></span><span class="font-18">元</span>（满<e:property value="@_formater.formateMoney(items.coupon.minInvestAmount)" />元使用）</p>
	                                    <p class="used pa font-12">使用日期：<span id="time"><e:property value="@_formater.formatDate(items.useTime)" /></span></p>
	                                    <p class="expires pa font-12">使用产品：<e:property value="@items.productName" /></p>
	                                </e:if>
	                                <e:elseif test="@items.coupon.discountType.toString()=='PRINCIPAL'">
	                                    <p class="font-16"><e:property value="@items.coupon.couponName"/><span class="font-25">
	                                        <e:property value="@_formater.formateMoney(items.coupon.couponAmount)" /></span> 元</p>
	                                    <p class="used pa font-12">使用日期：<span id="time"><e:property value="@_formater.formatDate(items.useTime)" /></span></p>
	                                    <p class="expires pa font-12">使用产品：<e:property value="@items.productName" /></p>
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
	                          <p class="font-16">加息<span class="font-25"><e:property value="@items.coupon.increaseInterest" />%</span></p>
	                          <p class="used pa font-12">使用日期：<span id="time"><e:property value="@_formater.formatDate(items.useTime)" /></span></p>
	                          <p class="expires pa font-12">使用产品：<e:property value="@items.productName" /></p>
	                          <span class="voucherType font-16 pa">加息券</span>
	                        </e:if>
	                        <e:elseif test="@items.coupon.couponType.toString()=='FULL_ADD'">
	                          <e:if test="@items.coupon.discountType.toString()=='INTEREST'">
	                            <p class="font-16"><span class="font-25"><e:property value="@_formater.formateMoney(items.coupon.couponAmount)" /></span><span class="font-18"> 元</span>（满<e:property value="@_formater.formateMoney(items.coupon.minInvestAmount)" />元使用）</p>
	                            <p class="used pa font-12">使用日期：<span id="time"><e:property value="@_formater.formatDate(items.useTime)" /></span></p>
	                            <p class="expires pa font-12">使用产品：
	                              <e:property value="@items.productName" />
	                            </p>
	                            <span class="voucherType font-16 pa">理财金</span>         
	                          </e:if>
	                          <e:elseif test="@items.coupon.discountType.toString()=='PRINCIPAL'">
	                              <p class="font-16">满
	                                <e:property value="@_formater.formateMoney(items.coupon.minInvestAmount)" /> 元 加<span class="font-25">
	                                <e:property value="@_formater.formateMoney(items.coupon.couponAmount)" /></span> 元
	                              </p>
	                              <p class="used pa font-12">使用日期：
	                                <span id="time"><e:property value="@_formater.formatDate(items.useTime)" /></span>
	                              </p>
	                              <p class="expires pa font-12">使用产品:<e:property value="@items.productName" /></p>
	                              <span class="voucherType font-16 pa">投资券</span>
	                          </e:elseif>
	                        </e:elseif>
	                      </div>
	                    </div>
	        
	                  </e:elseif>
						
					</e:iterator>
				</e:if>
				<e:if test="${empty usedResultList }">
					<p class="tc p15">亲，暂无已使用卡券</p>
				</e:if>
                </div>
            </div>
            <div class="swiper-slide">
                <div class="content-slide">
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
					<p class="tc p15">亲，暂无已过期卡券</p>
					</e:if>
                </div>
            </div>
        </div>
    </div>
    <div class="h1-6"></div>
    </div>
</body>
</html>