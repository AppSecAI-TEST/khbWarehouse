<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>查询批量发放记录</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<%-- <script src="${resourceUrl}/js/product.js"></script> --%>

<script type="text/javascript">
  $(function () {
    DatePickerExt.between("createTimeStart", "createTimeEnd", {
      maxDate : 0,
      showButtonPanel : true
    });
    DatePickerExt.between("checkedTimeStart", "checkedTimeEnd", {
      maxDate : 0,
      showButtonPanel : true
    }); 
  });
  showAdds = function(){
  	 var action = "batchGrant/toAddGrantRecord";
  	 window.location.href = GV.ctxPath + action;
  }
  
</script>
</head>
<body>
	<div class="Container">
		<div class="Content fontText">
			<!--search star-->
			<div class="information">
				<form id="queryBatchGrantForm" method="get"
					action="${ctxPath}batchGrant/queryBatchGrantList">
					<div class="search">
						<div class="search_tit">
							<h2 class="fw fleft f14">批量发放优惠券</h2>
						</div>
						<div class="search_con">
							<ul class="fix">
								<li><label class="text_tit">批量发放名称：</label> <input
									type="text" id="batchGrantName" name="batchGrantName" class="input_text"
									value="${batchGrantName}" /></li>
								<li class="between"><label class="text_tit">创建时间：</label>
									<input type="text" name="createTimeStart"
                  						class="input_text" id="createTimeStart" value="${createTimeStart }" readonly="true" />
                                    <span>至</span>
                                    <input type="text" name="createTimeEnd"
                                        class="input_text" id="createTimeEnd" value="${createTimeEnd }" readonly="true" />
								</li>
							</ul>
							<div class="btn">
								<input type="submit" class="btn_sure fw" value="查询" /> 
                                <input type="button" onclick="clearAll();" class="btn_cancel fw" value="清空" />
                                <input type="button" id="showAdd" name="showAdd" class="btn_sure fw" onclick="showAdds()" value="批量发放" />
							</div>
							<div class="clearer"></div>
						</div>
					</div>
				</form>
			</div>
			<div class="clearer"></div>
			<div class="result">
				<h2 class="fw">查询结果</h2>
				<q:table queryService="activityQueryService" queryKey="queryBatchGrantList"
					template="querytableForUnion" formId="queryBatchGrantForm"
					class="table_list" pageSize="15">
					<q:nodata>无符合条件的记录</q:nodata>
					<q:column title="序号" value="${id }" width="40px" />
					<q:column title="批量发放名称" value="${batch_grant_name }" />
					<q:column title="用户数" value="${member_count }" />
					<q:column title="发放数量" value="${grant_count }" />
                    <q:column title="每人发放数量" value="${per_grant_count }" />
                    <q:column title="优惠券名称" value="${coupon_name }" />
                    <q:column title="优惠券种类">
                      <c:forEach var="item" items="${_textResource.getTextMap('activity_coupon_type') }">
                        <c:if test="${item.key == coupon_type }">
                        <c:out value="${item.value }"></c:out>
                        </c:if>
                      </c:forEach>
                    </q:column>
                    <q:column title="优惠券总金额" value="${count_amount }" />
                    <q:column title="创建时间" value="${_messageFormater.formatDate(create_time)}" />
                    <q:column title="状态">
                      <c:forEach var="item" items="${_textResource.getTextMap('activity_grant_status') }">
                        <c:if test="${item.key == grant_status }">
                        <c:out value="${item.value }"></c:out>
                        </c:if>
                      </c:forEach>
                    </q:column>
                    <q:column title="发放明细" escapeHtml="false" >
                      <a title="查看" href="${ctxPath}batchGrant/queryGrantUsercouponDetailList?ruleId=${id }&ruleType=BATCH_GRANT" target="_blank">查看</a>
                      &nbsp;
                    </q:column>
				</q:table>
			</div>
			<div class="clearer"></div>
			<br />
		</div>
	</div>
</body>
</html>
