<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags"%>
<html>
<title>优惠券详情-投资券</title>
<head>
</head>
<body>
	<div id="box">
	<div>
	<span hidden="true" id="rule"><e:property value="@items.coupon.ruleDesc" /></span></div>
		<div class="vouchersLastCon bg-white">
			<div class="layou-04">
				<!-- 红包 -->
				<e:if test="@items.coupon.isRedPacket==1">
					<e:if test="@items.coupon.couponType.toString()=='INTEREST_ADD'">
						<h2 class="vouchersTitle orange"><e:property value="@items.coupon.couponName"/>红包</h2>
						<p class="font-18 orange">加息<e:property value="@items.coupon.increaseInterest"/>%</p>
					</e:if>
					<e:elseif test="@items.coupon.couponType.toString()=='FULL_ADD'">
						<e:if test="@items.coupon.discountType.toString()=='INTEREST'">
							<h2 class="vouchersTitle orange"><e:property value="@items.coupon.couponName"/>红包</h2>
							<p class="font-18 orange"><e:property value="@_formater.formateMoney(items.coupon.couponAmount)" /> 元 (满<e:property value="@_formater.formateMoney(items.coupon.minInvestAmount)"/> 元使用)</p>
						</e:if>
						<e:elseif test="@items.coupon.discountType.toString()=='PRINCIPAL'">
							<h2 class="vouchersTitle orange"><e:property value="@items.coupon.couponName"/></h2>
							<p class="font-18 orange"><e:property value="@_formater.formateMoney(items.coupon.couponAmount)"/> 元</p>
						</e:elseif>
					</e:elseif>
				</e:if>
			
				<!-- 非红包 -->
				<e:if test="@items.coupon.isRedPacket==0">
					<e:if test="@items.coupon.couponType.toString()=='INTEREST_ADD'">
						<h2 class="vouchersTitle orange">定期理财加息券</h2>
						<p class="font-18 orange">加息<e:property value="@items.coupon.increaseInterest"/>%</p>
					</e:if>
					<e:elseif test="@items.coupon.couponType.toString()=='FULL_ADD'">
						<e:if test="@items.coupon.discountType.toString()=='INTEREST'">
							<h2 class="vouchersTitle orange">定期理财理财金</h2>
							<p class="font-18 orange"><e:property value="@_formater.formateMoney(items.coupon.couponAmount)" /> 元 (满<e:property value="@_formater.formateMoney(items.coupon.minInvestAmount)"/> 元使用)</p>
						</e:if>
						<e:elseif test="@items.coupon.discountType.toString()=='PRINCIPAL'">
							<h2 class="vouchersTitle orange">定期理财投资券</h2>
							<p class="font-18 orange">满<e:property value="@_formater.formateMoney(items.coupon.minInvestAmount)" /> 元加<e:property value="@_formater.formateMoney(items.coupon.couponAmount)"/> 元</p>
						</e:elseif>
					</e:elseif>
				</e:if>
				<p class="br-bottom">有效期至：<e:property value="@_formater.formatDate(items.validityTimeEnd)"/></p>
				<p class="font-18 orange">使用规则:</p>
                <!-- 红包 -->
                <e:if test="@items.coupon.isRedPacket==1">
                  <ul class="vouchersRule">
                      <li><span class="orange nums">①</span> 红包有效期为90天</li>
                      <li><span class="orange nums">②</span> 此红包仅限于购买顾问专享理财产品使用</li>
                      <li><span class="orange nums">③</span> 顾问专享理财产品起投金额为10元，产品到期后本金、红包及收益全部返还</li>
                      <li><span class="orange nums">④</span> 顾问专享理财产品不参与平台任何优惠活动</li>
                      <li><span class="orange nums">⑤</span> 红包最终解释权归懒猫金服所有</li>
                  </ul>
                </e:if>
				<e:else>
                  <ul class="vouchersRule">
  					<li id="descRule"><e:property value="@items.coupon.ruleDesc" escape="false"/></li>
  				  </ul>
                </e:else>
			</div>
		</div>
		<!-- 红包显示按钮 -->
		<e:if test="@items.coupon.isRedPacket==1">
			<div class="layou-04"><input type="button" class="btn-login" id="redPacketDetail" name="redPacketDetail" value="立即使用" onclick="toRedPacketsDetail()"/></div>
		</e:if>
	</div>
  
  <script type="text/javascript">
  	function toRedPacketsDetail(){
  	  location.href="fixed/toRedPacketsDetail";
  	}
  </script>
</body>
</html>