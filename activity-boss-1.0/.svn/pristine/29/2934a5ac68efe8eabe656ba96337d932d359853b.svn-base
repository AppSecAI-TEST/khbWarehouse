<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>换产品规则查询</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<%-- <script src="${resourceUrl}/js/product.js"></script> --%>
<script type="text/javascript">
  $(function () {
    DatePickerExt.between("createTimeStart", "createTimeEnd", {
//       maxDate : 0,
      showButtonPanel : true
    });
//     DatePickerExt.between("checkedStartTime", "checkedEndTime", {
//       maxDate : 0,
//       showButtonPanel : true
//     });
  });
  
  /**
   *跳转至新增换产品规则页面
   */
  function addInvForProRule() {
	  var action = "invForProRule/toAddInvForProRule";
	  window.location.href = GV.ctxPath + action;
  }
  
  /**
   * 审核通过或退回
   */
  function changeStatus(id, status, version) {
	  var action = "invForProCheck";
	  if(confirm("确认是否审核通过？")) {
		  MessageBoxExt.ajax({
			  url : GV.ctxPath + "invForProRule/" + action,
			  data : {
			    "id" : id,
			    "couponStatus" : status,
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
				<form id="queryInvForProRuleListForm" method="get"
					action="${ctxPath}invForProRule/queryInvForProRuleList">
					<div class="search">
						<div class="search_tit">
							<h2 class="fw fleft f14">换产品规则查询</h2>
						</div>
						<div class="search_con">
							<ul class="fix">
								<li><label class="text_tit">换产品规则名称：</label> <input
									type="text" name="ruleName" class="input_text"
									value="<c:out value="${ruleName}"/>" /></li>
								<li><label class="text_tit">创建起始时间：</label>
									<input type="text" name="createTimeStart" class="input_text" id="createTimeStart" value="${createTimeStart}" readonly="true" />
								</li>
								<li><label class="text_tit">创建截止时间：</label>
                  					<input type="text" name="createTimeEnd" class="input_text" value="${createTimeEnd}" id="createTimeEnd" readonly="true" />
								</li>
							</ul>
							<div class="btn">
								<input type="submit" class="btn_sure fw" value="查询" />
								<input type="button" onclick="clearAll();" class="btn_cancel fw" value="清空" />
								<input type="button" class="btn_sure fw" onclick="addInvForProRule()" value="新增换产品" />
							</div>
							<div class="clearer"></div>
						</div>
					</div>
				</form>
			</div>
			<div class="clearer"></div>
			<div class="result">
				<h2 class="fw">查询结果</h2>
				<q:table queryService="activityQueryService" queryKey="queryInvForProRuleList"
					template="querytableForUnion" formId="queryInvForProRuleListForm"
					class="table_list" pageSize="15">
					<q:nodata>无符合条件的记录</q:nodata>
					<q:column title="序号" value="${id}" width="40px" />
					<q:column title="规则名称" value="${rule_name}" />
					<q:column title="期限" value="${trem}" />
					<q:column title="收益率" value="${rate}" />
					<q:column title="单价" value="${price}" />
					<q:column title="创建时间" value="${_messageFormater.formatDate(create_time)}" />
					<q:column title="创建人" value="${creater}" />
					<q:column title="操作" escapeHtml="false" >
						<a title="详情查看" href="invForProRuleDetail?id=${id }">查看</a>
						<%-- <a title="修改" href="toModifyGoods?id=${id }">修改</a> --%>
					</q:column>
				</q:table>
			</div>
			<div class="clearer"></div>
			<br />
		</div>
	</div>
</body>
</html>
