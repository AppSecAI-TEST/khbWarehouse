<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>策略信息查询</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<%-- <script src="${resourceUrl}/js/product.js"></script> --%>
<script type="text/javascript">
/* $(function(){
	  DatePickerExt.time("createTimeStart");
	  DatePickerExt.time("createTimeEnd");
	}); */
function deleteSrcFlowPlat(id){
    if(!confirm("确认删除？？？")){
      return;
    }
    var action = "fluxPlat/deleteSrcFlowPlat?id="+id;
    window.location.href = GV.ctxPath + action;
  }
        /**
         *跳转至新增活动页面
         */
        function toAddZtPolicy () {
         	var action = "ztPolicy/toAddZtPolicy";
          window.location.href = GV.ctxPath + action;
        }
        /**
         * 审核通过或退回
         */
        function changeStatus(id, status, version) {
      	  var action = "checkPolicyInfo";
      	  var warnMsg = "";
      	  if(status == 'EFFECTIVE') {
      		  warnMsg = "确认是否审核通过？";
      	  } else if(status == 'RETURN_BACK') {
      		  warnMsg = "确认是否退回？";
      	  }else{
      	    warnMsg = "确认是否置失效？";
      	  }
      	  if(confirm(warnMsg)) {
      		  MessageBoxExt.ajax({
      			  url : GV.ctxPath + "ztPolicy/" + action,
      			  data : {
      			    "id" : id,
      			    "status" : status,
      			    "version" : version,
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
				<form id="queryZtPolicyListForm" method="get"
					action="${ctxPath}ztPolicy/queryCheckPolicyInfo">
					<div class="search">
						<div class="search_tit">
							<h2 class="fw fleft f14">策略信息查询</h2>
						</div>
						<div class="search_con">
							<ul class="fix">
								<li><label class="text_tit">策略名称：</label> <input
									type="text" name="policyName" class="input_text"
									value="<c:out value="${policyName}"/>" /></li>
								<%-- <li><label class="text_tit">创建起始时间：</label>
									<input type="text" name="createTimeStart" class="input_text" id="createTimeStart" value="${createTimeStart}" readonly="true" />
								</li>
								<li><label class="text_tit">创建截止时间：</label>
                  					<input type="text" name="createTimeEnd" class="input_text" value="${createTimeEnd}" id="createTimeEnd" readonly="true" />
								</li> --%>
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
				<q:table queryService="ztPolicyQueryService" queryKey="queryZtPolicyList"
					template="querytableForUnion" formId="queryZtPolicyListForm"
					class="table_list" pageSize="15">
					<q:nodata>无符合条件的记录</q:nodata>
					<q:column title="序号" value="${id}" width="40px" />
					<q:column title="策略名称" value="${policy_name}" 	/>
					<q:column title="波动性类型" value="${fluctuate}" />
					<q:column title="策略类型" value="${policy_type}" />
					<q:column title="起购金额" value="${min_purchase_amount}" />
					<q:column title="建议连续投资年数" value="${sug_inv_year}" />
					<q:column title="最近成立的基金年月" value="${last_establish_fund_year}" />
					<q:column title="审核状态" escapeHtml="false" >
						<c:forEach var="item" items="${_textResource.getTextMap('activity_goods_status') }">
	                        <c:if test="${item.key == check_status }">
	                        <c:out value="${item.value }"></c:out>
                        </c:if>
                      </c:forEach>
					</q:column>
					<q:column title="创建人" value="${creator}" />
					<q:column title="创建时间" value="${_messageFormater.formatDate(create_time)}" />
					<q:column title="审核人" value="${checker}" />
					<q:column title="最后修改时间" value="${_messageFormater.formatDate(last_update_time)}" />
					<q:column title="操作" escapeHtml="false" >
						  <a title="详情查看" href="queryPolicyDetailInfo?id=${id }">查看</a>
					  <c:if test="${check_status == 'CHECKING'}">
			              <a title="审批智投场景"
			                href="javascript:changeStatus('${id}','EFFECTIVE','${version }')">审核通过</a>
			              &nbsp;
			              <a title="审批智投场景"
			                href="javascript:changeStatus('${id}','RETURN_BACK','${version }')">退回</a>
			              &nbsp;
                      </c:if>
                      <c:if test="${check_status == 'EFFECTIVE'}">
						  <a title="修改" href="toModifyPolicy?id=${id }">修改</a>
	                  </c:if>
					</q:column>
				</q:table>
			</div>
			<div class="clearer"></div>
			<br />
		</div>
	</div>
</body>
</html>
