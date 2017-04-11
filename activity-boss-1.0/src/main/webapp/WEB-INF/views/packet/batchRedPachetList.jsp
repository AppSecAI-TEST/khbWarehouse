<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>查询批量发放红包列表</title>
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
				<form id="batchRedPachetListForm" method="get"
					action="${ctxPath}packet/batchRedPachetList">
<%-- 					<input type="hidden" id="ruleId" name="ruleId" value="${ruleId }" /> --%>
<%-- 					<input type="hidden" id="ruleType" name="ruleType" value="${ruleType }" /> --%>
					<div class="search">
						<div class="search_tit">
							<h2 class="fw fleft f14">查询批量发放红包列表</h2>
						</div>
						<div class="search_con">
							<ul class="fix">
								<li><label class="text_tit">批次号：</label> <input
									type="text" id="batchId" name="batchId" class="input_text"
									value="${batchId}" /></li>
								<li><label class="text_tit">会员编号：</label> <input
									type="text" id="memberNo" name="memberNo" class="input_text"
									value="${memberNo}" /></li>
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
				<q:table queryService="activityQueryService" queryKey="batchRedPachetList"
					template="querytableForUnion" formId="batchRedPachetListForm"
					class="table_list" pageSize="100">
					<q:nodata>无符合条件的记录</q:nodata>
					<q:column title="序号" value="${id }" width="40px" />
					<q:column title="批次号" value="${batch_id }" />
                    <q:column title="会员编号" value="${member_no }" />
					<q:column title="红包名称" value="${coupon_name }" />
					<q:column title="红包金额" value="${coupon_amount }" />
					<q:column title="红包数量" value="${coupon_count }" />
					<q:column title="已使用数量" value="${coupon_used_count }" />
                    <q:column title="有效期起始日期" value="${_messageFormater.formatDate(validity_time_start)}" />
                    <q:column title="有效期截止日期" value="${_messageFormater.formatDate(validity_time_end)}" />
                    <q:column title="红包状态">
                      <c:forEach var="item" items="${_textResource.getTextMap('activity_coupon_status') }">
                        <c:if test="${item.key == status }">
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
