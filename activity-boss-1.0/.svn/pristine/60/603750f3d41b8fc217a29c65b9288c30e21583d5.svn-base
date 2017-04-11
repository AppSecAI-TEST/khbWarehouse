<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>奖品查询</title>
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
   *跳转至新增奖品页面
   */
  function addPrize() {
	  var action = "prize/toAddPrize";
	  window.location.href = GV.ctxPath + action;
  }
  
  /**
   * 审核通过或退回
   */
  function changeStatus(id, status, version) {
	  var action = "prizeCheck";
	  if(confirm("确认是否审核通过？")) {
		  MessageBoxExt.ajax({
			  url : GV.ctxPath + "prize/" + action,
			  data : {
			    "id" : id,
			    "prizeStatus" : status,
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
				<form id="queryPrizeListForm" method="get"
					action="${ctxPath}prize/queryPrizeList">
					<div class="search">
						<div class="search_tit">
							<h2 class="fw fleft f14">奖品查询</h2>
						</div>
						<div class="search_con">
							<ul class="fix">
								<li><label class="text_tit">奖品名称：</label> <input
									type="text" name="prizeName" class="input_text"
									value="<c:out value="${prizeName}"/>" /></li>
								<li><label class="text_tit">奖品类型：</label>
									<select id="prizeType" name="prizeType" class="input_text">
					                    <option ${empty param.prizeType?'selected':'' } value="">全部</option>
										<c:forEach var="item"
											items="${_textResource.getTextMap('activity_prize_type') }">
											<option ${item.key==param.prizeType?'selected':''}
												value="${item.key}">${item.value}</option>
										</c:forEach>
                					</select>
								</li>
								<li><label class="text_tit">创建起始时间：</label>
									<input type="text" name="createTimeStart" class="input_text" id="createTimeStart" value="${createTimeStart}" readonly="true" />
								</li>
								<li><label class="text_tit">创建截止时间：</label>
                  					<input type="text" name="createTimeEnd" class="input_text" value="${createTimeEnd}" id="createTimeEnd" readonly="true" />
								</li>
							</ul>
							<ul class="fix">
								<li><label class="text_tit">奖品状态：</label>
									<select id="prizeStatus" name="prizeStatus" class="input_text">
					                    <option ${empty param.prizeStatus?'selected':'' } value="">全部</option>
										<c:forEach var="item"
											items="${_textResource.getTextMap('activity_prize_status') }">
											<option ${item.key==param.prizeStatus?'selected':''}
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
								<input type="button" class="btn_sure fw" onclick="addPrize()" value="新增奖品" />
							</div>
							<div class="clearer"></div>
						</div>
					</div>
				</form>
			</div>
			<div class="clearer"></div>
			<div class="result">
				<h2 class="fw">查询结果</h2>
				<q:table queryService="activityQueryService" queryKey="queryPrizeList"
					template="querytableForUnion" formId="queryPrizeListForm"
					class="table_list" pageSize="15">
					<q:nodata>无符合条件的记录</q:nodata>
					<q:column title="序号" value="${id}" width="40px" />
					<q:column title="奖品名称" value="${prize_name}" />
					<q:column title="奖品类型" >
						<c:forEach var="item" items="${_textResource.getTextMap('activity_prize_type') }">
	                        <c:if test="${item.key == prize_type }">
	                        <c:out value="${item.value }"></c:out>
                        </c:if>
                      </c:forEach>
					</q:column>
					<q:column title="奖品发放方式" escapeHtml="false" >
						<c:forEach var="item" items="${_textResource.getTextMap('activity_grant_way') }">
	                        <c:if test="${item.key == grant_way }">
	                        <c:out value="${item.value }"></c:out>
                        </c:if>
                      </c:forEach>
					</q:column>
					<q:column title="总数量" value="${prize_total_count}" />
					<q:column title="已中奖数量" value="${prize_grant_count}" />
					<q:column title="创建时间" value="${_messageFormater.formatDate(create_time)}" />
					<q:column title="审核时间" value="${_messageFormater.formatDate(checked_time)}" />
					<q:column title="审核人" value="${checkor}" />
					<q:column title="奖品状态" escapeHtml="false" >
						<c:forEach var="item" items="${_textResource.getTextMap('activity_prize_status') }">
	                        <c:if test="${item.key == prize_status }">
	                        <c:out value="${item.value }"></c:out>
                        </c:if>
                      </c:forEach>
					</q:column>
					<q:column title="操作" escapeHtml="false" >
						<a title="详情查看" href="prizeDetail?id=${id }">查看</a>
						<%-- <a title="修改" href="toModifyPrize?id=${id }">修改</a> --%>
					</q:column>
				</q:table>
			</div>
			<div class="clearer"></div>
			<br />
		</div>
	</div>
</body>
</html>
