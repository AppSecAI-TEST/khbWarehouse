<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>活动规则待审核查询</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<%-- <script src="${resourceUrl}/js/product.js"></script> --%>
<script type="text/javascript">
  $(function () {

  });

  /**
   * 审核通过或退回
   */
  function changeStatus(id, status, version) {
	  var action = "ruleCheck";
	  var warnMsg = "";
	  if(status == 'EFFECTIVE') {
		  warnMsg = "确认是否审核通过？";
	  } else {
		  warnMsg = "确认是否退回？";
	  }
	  if(confirm(warnMsg)) {
		  MessageBoxExt.ajax({
			  url : GV.ctxPath + "rule/" + action,
			  data : {
			    "id" : id,
			    "ruleStatus" : status,
			    "version" : version
			  },
			  style : "REDIRECT",
			  success : function (result) {
			     //alert(result);
			  }
		  });
	  }
  }
</script>
</head>
<body>
	<div class="Container">
		<div class="Content fontText">
			<!--search star-->
			<div class="information">
				<form id="queryRuleForm" method="get"
					action="${ctxPath}rule/queryCheckingRuleList">
					<div class="search">
						<div class="search_tit">
							<h2 class="fw fleft f14">活动规则待审核查询</h2>
						</div>
						<div class="search_con">
							<ul class="fix">
								<li><label class="text_tit">规则编码：</label> <input
									type="text" name="ruleCode" class="input_text"
									value="<c:out value="${ruleCode}"/>" /></li>
								<li><label class="text_tit">规则名称：</label> <input
									type="text" name="ruleName" class="input_text"
									value="<c:out value="${ruleName}"/>" /></li>
								<li><label class="text_tit">规则状态：</label>
									<select id="ruleStatus" name="ruleStatus" class="input_text">
										<option ${empty param.ruleStatus?'selected':'' } value="">全部</option>
										<c:forEach var="item"
											items="${_textResource.getTextMap('activity_rule_status') }">
											<option ${item.key==param.ruleStatus?'selected':''}
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
				<q:table queryService="activityQueryService" queryKey="queryRuleList"
					template="querytableForUnion" formId="queryRuleForm"
					class="table_list" pageSize="15">
					<q:nodata>无符合条件的记录</q:nodata>
					<q:column title="序号" value="${id}" />
					<q:column title="规则编码" value="${rule_code}" />
					<q:column title="规则名称" value="${rule_name}" />
					<q:column title="生效时间" value="${_messageFormater.formatDate(take_effect_time)}" />
					<q:column title="失效时间" value="${_messageFormater.formatDate(invalid_time)}" />
					<q:column title="状态" >
						<c:forEach var="item" items="${_textResource.getTextMap('activity_rule_status') }">
	                        <c:if test="${item.key == rule_status }">
	                        <c:out value="${item.value }"></c:out>
                        </c:if>
                      </c:forEach>
					</q:column>
					<q:column title="创建时间" value="${_messageFormater.formatDate(create_time)}" />
					<q:column title="创建人" value="${creator}" />
					<q:column title="审核时间" value="${_messageFormater.formatDate(checked_time)}" />
					<q:column title="审核人" value="${checkor}" />
					<q:column title="操作" escapeHtml="false" >
						<c:if test="${rule_status == 'CHECKING'}">
							<a title="审批优惠券"
								href="javascript:changeStatus('${id}','EFFECTIVE','${version }')">审核通过</a>
							&nbsp;
							<a title="审批优惠券"
								href="javascript:changeStatus('${id}','RETURN_BACK','${version }')">退回</a>
							&nbsp;
			            </c:if>
						<a title="详情查看" href="ruleDetail?id=${id }">查看</a>
					</q:column>
				</q:table>
			</div>
			<div class="clearer"></div>
			<br />
		</div>
	</div>
</body>
</html>
