<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>待审核发放记录</title>
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
  //审核通过或退回
  function changeGrantStatus(id,grantStatus,version){
    var action = "checkGrantRecord";
    if(grantStatus == 'RETURN_BACK'){
      if(!confirm("确认是否退回？")){
        return;
      }
    }else if(grantStatus == 'EFFECTIVE'){
      if(!confirm("确认是否审核通过？")){
        return;
      }
    }
    MessageBoxExt.ajax({
      url:GV.ctxPath + "batchGrant/" + action,
      type:"post",
      data:{"id":id,"grantStatus":grantStatus,"version":version},
      style:"REDIRECT",
      success:function(data){
        //刷新页面
        if(data == "DISABLED") {
          alert("优惠券审核不通过或无效");
        } else if(data == "EXPIRE") {
          alert("优惠券已到期");
        } else if(data == "NOT_ENOUGH") {
          alert("优惠券数量不足,请重新操作批量发放！");
        } else if(data == "SUCCESS"){
//           alert("操作成功");
        }
      }
    });
  }
</script>
</head>
<body>
	<div class="Container">
		<div class="Content fontText">
			<!--search star-->
			<div class="information">
				<form id="queryBatchGrantForm" method="get"
					action="${ctxPath}batchGrant/queryCheckingGrantRecord">
					<div class="search">
						<div class="search_tit">
							<h2 class="fw fleft f14">审核批量发放记录</h2>
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
					<q:column title="操作" escapeHtml="false" >
                      <c:if test="${grant_status == 'CHECKING'}">
                        <a title="审批优惠券"
                          href="javascript:changeGrantStatus('${id}','EFFECTIVE','${version }')">审核通过</a>
                        &nbsp;
                        <a title="审批优惠券"
                          href="javascript:changeGrantStatus('${id}','RETURN_BACK','${version }')">退回</a>
                        &nbsp;
                      </c:if>
                      <c:if test="${grant_status == 'EFFECTIVE'}">
                        <span>已审核</span>
                      </c:if>
                      <c:if test="${grant_status == 'RETURN_BACK'}">
                        <span>已退回</span>
                      </c:if>
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
