<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>查询会员操作记录列表</title>
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
  
  function userReceiveRaffleTicket(id) {
	  var warnMsg = "确认是否手工发券？";
	  if(confirm(warnMsg)) {
		  MessageBoxExt.ajax({
			  url : GV.ctxPath + "raffle/userReceiveRaffleTicket",
			  data : {
			    "id" : id
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
				<form id="queryMemberRecordListForm" method="get"
					action="${ctxPath}raffle/queryMemberRecordList">
					<div class="search">
						<div class="search_tit">
							<h2 class="fw fleft f14">会员操作记录查询</h2>
						</div>
						<div class="search_con">
							<ul class="fix">
								<li><label class="text_tit">会员编号：</label> <input
									type="text" name="memberNo" class="input_text"
									value="<c:out value="${memberNo}"/>" /></li>
								<li><label class="text_tit">操作类型：</label> <input
									type="text" name="operateType" class="input_text"
									value="<c:out value="${operateType}"/>" /></li>
								<li><label class="text_tit">创建起始时间：</label>
									<input type="text" name="createTimeStart" class="input_text" id="createTimeStart" value="${createTimeStart}" readonly="true" />
								</li>
								<li><label class="text_tit">创建截止时间：</label>
                  					<input type="text" name="createTimeEnd" class="input_text" value="${createTimeEnd}" id="createTimeEnd" readonly="true" />
								</li>
<!-- 								<li><label class="text_tit"></label> -->
<!-- 									<select id="operateType" name="operateType" class="input_text"> -->
<!-- 					                    <option value="">全部</option> -->
<%-- 										<c:forEach var="item" --%>
<%-- 											items="${_textResource.getTextMap('activity_raffleTicket_type') }"> --%>
<%-- 											<option ${item.key==param.raffleTicketType?'selected':''} --%>
<%-- 												value="${item.key}">${item.value}</option> --%>
<%-- 										</c:forEach> --%>
<!--                 					</select> -->
<!-- 								</li> -->
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
				<q:table queryService="activityQueryService" queryKey="queryMemberRecordList"
					template="querytableForUnion" formId="queryMemberRecordListForm"
					class="table_list" pageSize="50">
					<q:nodata>无符合条件的记录</q:nodata>
					<q:column title="序号" value="${id}" width="40px" />
					<q:column title="会员编号" value="${member_no}" />
					<q:column title="会员手机号" value="${member_tel}" />
					<q:column title="操作类型" value="${operate_type}" />
					<q:column title="操作时间" value="${_messageFormater.formatDate(operate_time)}" />
					<q:column title="创建时间" value="${_messageFormater.formatDate(create_time)}" />
					<q:column title="操作金额" value="${operate_amount}" />
					<q:column title="操作" escapeHtml="false" >
						<a title="手工发抽奖券"
							href="javascript:userReceiveRaffleTicket('${id}')">手工发券</a>
					</q:column>
				</q:table>
			</div>
			<div class="clearer"></div>
			<br />
		</div>
	</div>
</body>
</html>
