<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>投资换产品查询</title>
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
   *跳转至新增投资换产品页面
   */
  function addInvForPro() {
	  var action = "invForPro/toAddInvForPro";
	  window.location.href = GV.ctxPath + action;
  }
  
  /**
   * 审核通过或退回
   */
  function changeStatus(id, status, version) {
	  var action = "invForProCheck";
	  if(confirm("确认是否审核通过？")) {
		  MessageBoxExt.ajax({
			  url : GV.ctxPath + "invForPro/" + action,
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
				<form id="queryInvForProListForm" method="get"
					action="${ctxPath}invForPro/queryInvForProList">
					<div class="search">
						<div class="search_tit">
							<h2 class="fw fleft f14">投资换产品查询</h2>
						</div>
						<div class="search_con">
							<ul class="fix">
								<li><label class="text_tit">投资换产品名称：</label> <input
									type="text" name="name" class="input_text"
									value="<c:out value="${name}"/>" /></li>
								<li><label class="text_tit">创建起始时间：</label>
									<input type="text" name="createTimeStart" class="input_text" id="createTimeStart" value="${createTimeStart}" readonly="true" />
								</li>
								<li><label class="text_tit">创建截止时间：</label>
                  					<input type="text" name="createTimeEnd" class="input_text" value="${createTimeEnd}" id="createTimeEnd" readonly="true" />
								</li>
							</ul>
							<ul class="fix">
								<li><label class="text_tit">投资换产品状态：</label>
									<select id="status" name="status" class="input_text">
					                    <option ${empty param.status?'selected':'' } value="">全部</option>
										<c:forEach var="item"
											items="${_textResource.getTextMap('activity_invForPro_status') }">
											<option ${item.key==param.status?'selected':''}
												value="${item.key}">${item.value}</option>
										</c:forEach>
                					</select>
								</li>
<!-- 								<li><label class="text_tit">审核起始时间：</label> -->
<!-- 									<input type="text" name="checkedTimeStart" -->
<%--                   						class="input_text" id="checkedTimeStart" value="${checkedTimeStart}" readonly="true" /> --%>
<!-- 								</li> -->
<!-- 								<li><label class="text_tit">审核截止时间：</label> -->
<%--                   					<input type="text" name="checkedTimeEnd" class="input_text" value="${checkedTimeEnd}" id="checkedTimeEnd" readonly="true" /> --%>
<!-- 								</li> -->
							</ul><br>
							<div class="btn">
								<input type="submit" class="btn_sure fw" value="查询" />
								<input type="button" onclick="clearAll();" class="btn_cancel fw" value="清空" />
								<input type="button" class="btn_sure fw" onclick="addInvForPro()" value="新增投资换产品" />
							</div>
							<div class="clearer"></div>
						</div>
					</div>
				</form>
			</div>
			<div class="clearer"></div>
			<div class="result">
				<h2 class="fw">查询结果</h2>
				<q:table queryService="activityQueryService" queryKey="queryInvForProList"
					template="querytableForUnion" formId="queryInvForProListForm"
					class="table_list" pageSize="15">
					<q:nodata>无符合条件的记录</q:nodata>
					<q:column title="序号" value="${id}" width="40px" />
					<q:column title="投资换产品名称" value="${name}" />
					<q:column title="活动code" value="${activity_code}" />
					<q:column title="价格" value="${price}" />
					<q:column title="URL" value="${url}" />
					<q:column title="收益率" value="${rate}" />
					<q:column title="最低购买理财价格" value="${product_price_low}" />
					<q:column title="总数量" value="${stock_num}" />
					<q:column title="已发数量" value="${used_num}" />
					<q:column title="创建时间" value="${_messageFormater.formatDate(create_time)}" />
					<q:column title="审核时间" value="${_messageFormater.formatDate(operator_time)}" />
					<q:column title="审核人" value="${operatorer}" />
					<q:column title="投资换产品状态" escapeHtml="false" >
						<c:forEach var="item" items="${_textResource.getTextMap('activity_invForPro_status') }">
	                        <c:if test="${item.key == status }">
	                        <c:out value="${item.value }"></c:out>
                        </c:if>
                      </c:forEach>
					</q:column>
					<q:column title="操作" escapeHtml="false" >
						<a title="详情查看" href="invForProDetail?id=${id }">查看</a>
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
