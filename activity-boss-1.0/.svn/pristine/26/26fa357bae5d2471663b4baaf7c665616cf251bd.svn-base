<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>查询批量发放用户优惠券记录</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<%-- <script src="${resourceUrl}/js/product.js"></script> --%>

<script type="text/javascript">
  $(function () {
  });
</script>
</head>
<body>
	<div class="Container">
		<div class="Content fontText">
			<!--search star-->
			<div class="information">
				<form id="queryGrantUsercouponDetailForm" method="get"
					action="${ctxPath}batchGrant/queryGrantUsercouponDetailList">
<%-- 					<input type="hidden" id="ruleId" name="ruleId" value="${ruleId }" /> --%>
<%-- 					<input type="hidden" id="ruleType" name="ruleType" value="${ruleType }" /> --%>
					<div class="search">
						<div class="search_tit">
							<h2 class="fw fleft f14">用户优惠券列表查询</h2>
						</div>
						<div class="search_con">
							<ul class="fix">
								<li><label class="text_tit">会员编号：</label> <input
									type="text" id="memberNo" name="memberNo" class="input_text"
									value="${memberNo}" /></li>
								<li><label class="text_tit">规则ID：</label> <input
									type="text" id="ruleId" name="ruleId" class="input_text"
									value="${ruleId}"/></li>
								<li><label class="text_tit">规则类型：</label> 
									<select name="ruleType" id="ruleType" type="text" class="input_text">
										<option ${empty ruleType?'selected':'' } value="">全部</option>
										<c:forEach var="item"
											items="${_textResource.getTextMap('activity_rule_type') }">
											<option ${item.key==param.ruleType?'selected':''}
												value="${item.key}">${item.value}</option>
										</c:forEach>
									</select>
								</li>
							</ul>
							<div class="btn">
								<input type="submit" class="btn_sure fw" value="查询" /> 
                                <input type="button" onclick="clearAll();" class="btn_cancel fw" value="清空" />
							</div>
							<div class="clearer"></div>
						</div>
					</div>
				</form>
			</div>
			<div class="clearer"></div>
			<div class="result">
				<h2 class="fw">查询结果</h2>
				<q:table queryService="activityQueryService" queryKey="queryGrantUsercouponDetailList"
					template="querytableForUnion" formId="queryGrantUsercouponDetailForm"
					class="table_list" pageSize="15">
					<q:nodata>无符合条件的记录</q:nodata>
					<q:column title="序号" value="${id }" width="40px" />
					<q:column title="优惠券名称" value="${coupon_name }" />
					<q:column title="优惠券数量" value="${coupon_count }" />
					<q:column title="已使用数量" value="${coupon_used_count }" />
                    <q:column title="会员编号" value="${member_no }" />
                    <q:column title="有效期起始日期" value="${_messageFormater.formatDate(validity_time_start)}" />
                    <q:column title="有效期截止日期" value="${_messageFormater.formatDate(validity_time_end)}" />
                    <q:column title="规则名称" value="${rule_name }" />
                    <q:column title="规则类型" >
                    	<c:forEach var="item" items="${_textResource.getTextMap('activity_rule_type') }">
	                        <c:if test="${item.key == rule_type }">
		                        <c:out value="${item.value }"></c:out>
	                        </c:if>
	                    </c:forEach>
                    </q:column>
                    <q:column title="优惠券状态">
                      <c:forEach var="item" items="${_textResource.getTextMap('activity_coupon_status') }">
                        <c:if test="${item.key == coupon_status }">
                        <c:out value="${item.value }"></c:out>
                        </c:if>
                      </c:forEach>
                    </q:column>
				</q:table>
			</div>
			<div class="clearer"></div>
			<br />
		</div>
	</div>
</body>
</html>
