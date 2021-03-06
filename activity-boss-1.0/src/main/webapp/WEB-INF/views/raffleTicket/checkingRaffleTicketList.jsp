<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>查询待审核抽奖券</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<%-- <script src="${resourceUrl}/js/product.js"></script> --%>
<script type="text/javascript">
  $(function () {
//     DatePickerExt.between("createTimeStart", "createTimeEnd", {
//       maxDate : 0,
//       showButtonPanel : true
//     });
	DatePickerExt.date("createTimeStart");
	DatePickerExt.date("createTimeEnd");
//     DatePickerExt.between("checkedTimeStart", "checkedTimeEnd", {
//       maxDate : 0,
//       showButtonPanel : true
//     });
  });
  
  /**
   * 审核通过或退回
   */
  function changeStatus(id, status, version) {
	  var action = "raffleTicketCheck";
	  var warnMsg = "";
	  if(status == 'EFFECTIVE') {
		  warnMsg = "确认是否审核通过？";
	  } else {
		  warnMsg = "确认是否退回？";
	  }
	  if(confirm(warnMsg)) {
		  MessageBoxExt.ajax({
			  url : GV.ctxPath + "raffle/" + action,
			  data : {
			    "id" : id,
			    "couponStatus" : status,
			    "version" : version,
			    "operateType" : '',
			    "ruleDesc" : ''
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
				<form id="queryCheckingRaffleTicketListForm" method="get"
					action="${ctxPath}raffle/queryCheckingRaffleTicketList">
					<div class="search">
						<div class="search_tit">
							<h2 class="fw fleft f14">待审核抽奖券查询</h2>
						</div>
						<div class="search_con">
							<ul class="fix">
								<li><label class="text_tit">抽奖券名称：</label> <input
									type="text" name="raffleTicketName" class="input_text"
									value="<c:out value="${raffleTicketName}"/>" /></li>
								<li><label class="text_tit">创建起始时间：</label>
									<input type="text" name="createTimeStart" class="input_text" id="createTimeStart" value="${createTimeStart}" readonly="true" />
								</li>
								<li><label class="text_tit">创建截止时间：</label>
                  					<input type="text" name="createTimeEnd" class="input_text" value="${createTimeEnd}" id="createTimeEnd" readonly="true" />
								</li>
								<li><label class="text_tit"></label>
									<%-- <select id="raffleTicketType" name="raffleTicketType" class="input_text">
					                    <option ${empty param.raffleTicketType?'selected':'' } value="">全部</option>
										<c:forEach var="item"
											items="${_textResource.getTextMap('activity_raffleTicket_type') }">
											<option ${item.key==param.raffleTicketType?'selected':''}
												value="${item.key}">${item.value}</option>
										</c:forEach>
                					</select> --%>
								</li>
							</ul>
							<ul class="fix">
								<li><label class="text_tit">抽奖券状态：</label>
									<select id="couponStatus" name="couponStatus" class="input_text">
					                    <option ${empty param.couponStatus?'selected':'' } value="">全部</option>
										<c:forEach var="item"
											items="${_textResource.getTextMap('activity_coupon_status') }">
											<option ${item.key==param.couponStatus?'selected':''}
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
							</ul>
							<div class="btn">
								<input type="submit" class="btn_sure fw" value="查询" />
								<input type="button" onclick="clearAll();" class="btn_cancel fw" value="清空" />
								<!-- <input type="button" class="btn_sure fw" onclick="addRaffleTicket()" value="新增抽奖券" /> -->
							</div>
							<div class="clearer"></div>
						</div>
					</div>
				</form>
			</div>
			<div class="clearer"></div>
			<div class="result">
				<h2 class="fw">查询结果</h2>
				<q:table queryService="activityQueryService" queryKey="queryRaffleTicketList"
					template="querytableForUnion" formId="queryCheckingRaffleTicketListForm"
					class="table_list" pageSize="15">
					<q:nodata>无符合条件的记录</q:nodata>
					<q:column title="序号" value="${id}" width="40px" />
					<q:column title="抽奖券编码" value="${raffle_ticket_code}" />
					<q:column title="抽奖券名称" value="${raffle_ticket_name}" />
					<%-- <q:column title="抽奖券类型" >
						<c:forEach var="item" items="${_textResource.getTextMap('activity_coupon_type') }">
	                        <c:if test="${item.key == coupon_type }">
	                        <c:out value="${item.value }"></c:out>
                        </c:if>
                      </c:forEach>
					</q:column> --%>
					<q:column title="总数量" value="${total_count}" />
					<q:column title="已发数量" value="${grant_count}" />
					<q:column title="创建时间" value="${_messageFormater.formatDate(create_time)}" />
					<q:column title="审核时间" value="${_messageFormater.formatDate(checked_time)}" />
					<q:column title="审核人" value="${checkor}" />
					<q:column title="抽奖券状态" escapeHtml="false" >
						<c:forEach var="item" items="${_textResource.getTextMap('activity_coupon_status') }">
	                        <c:if test="${item.key == coupon_status }">
	                        <c:out value="${item.value }"></c:out>
                        </c:if>
                      </c:forEach>
					</q:column>
					<q:column title="操作" escapeHtml="false" >
						<c:if test="${coupon_status == 'CHECKING'}">
							<a title="审批优惠券"
								href="javascript:changeStatus('${id}','EFFECTIVE','${version }')">审核通过</a>
							&nbsp;
							<a title="审批优惠券"
								href="javascript:changeStatus('${id}','RETURN_BACK','${version }')">退回</a>
							&nbsp;
			            </c:if>
						<a title="详情查看" href="raffleTicketDetail?id=${id }">查看</a>
						<a title="修改" href="toModifyRaffleTicket?id=${id }">修改</a>
					</q:column>
				</q:table>
			</div>
			<div class="clearer"></div>
			<br />
		</div>
	</div>
</body>
</html>
