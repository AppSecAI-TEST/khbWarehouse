<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>投资换产品订单查询</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<script type="text/javascript">
$(function () {
  DatePickerExt.time("createTimeStart");
	DatePickerExt.time("createTimeEnd");
});
</script>
</head>
<body>
  <div class="Container">
    <div class="Content fontText">
      <div class="information">
        <form id="queryInvForProOrderForm" method="get"
          action="${ctxPath}invForProOrder/queryInvForProOrderList">
          <div class="search">
            <div class="search_tit">
              <h2 class="fw fleft f14">投资换产品订单查询</h2>
            </div>
            <div class="search_con">
              <ul class="fix">
                <li><label class="text_tit">订单编号：</label> <input
                  type="text" name="orderCode" class="input_text"
                  value="<c:out value="${orderCode}"/>" /></li>
                <li><label class="text_tit">订单状态：</label>
                  <select id="status" name="status" class="input_text">
                              <option ${empty param.status?'selected':'' } value="">全部</option>
                    <c:forEach var="item"
                      items="${_textResource.getTextMap('activity_invForPro_order_status') }">
                      <option ${item.key==param.status?'selected':''}
                        value="${item.key}">${item.value}</option>
                    </c:forEach>
                          </select>
                </li>
                   <li><label class="text_tit">会员编号：</label>
                  <input type="text" name="memberNo" class="input_text" id="memberNo" value="${memberNo}"  />
                </li>
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
              </div>
              <div class="clearer"></div>
            </div>
          </div>
        </form>
      </div>
      <div class="clearer"></div>
      <div class="result">
        <h2 class="fw">查询结果</h2>
        <q:table queryService="invForProOrderQueryService" queryKey="queryInvForProOrderList"
          template="querytableForUnion" formId="queryInvForProOrderForm"
          class="table_list" pageSize="15">
          <q:nodata>无符合条件的记录</q:nodata>
          <q:column title="序号" value="${id}" width="40px" />
          <q:column title="产品明细序号" value="${product_id}" />
          <q:column title="理财产品序号" value="${lc_product_id}" />
          <q:column title="活动编码" value="${activity_code}" />
          <q:column title="订单编码" value="${order_code}" />
          <q:column title="信托交易订单序号" value="${xt_order_id}" />
          <q:column title="会员编号" value="${member_no}" />
          <q:column title="订单状态" >
              <c:forEach var="item" items="${_textResource.getTextMap('activity_invForPro_order_status') }">
                          <c:if test="${item.key == status }">
                          <c:out value="${item.value }"></c:out>
                        </c:if>
                      </c:forEach>
          </q:column>
              <q:column title="下单时间" value="${_messageFormater.formatDate(create_time)}" />
              <q:column title="购买份额" value="${num}" />
              <q:column title="产品单价" value="${price}" />
          <q:column title="投资金额" value="${total}" />
          <q:column title="联系人" value="${user_name}" />
          <q:column title="手机号" value="${mobile_num}" />
          <q:column title="操作员" value="${operator}" />
          <q:column title="操作时间" value="${_messageFormater.formatDate(operator_time)}" />
          <q:column title="物流单号" value="${logistics_number}" />
          <q:column title="操作" escapeHtml="false" >
           <c:if test="${status!='CANCE'&&status!='SUCCESS'}">
          <a title="修改" href="toUpdateInvForProOrder?id=${id }">修改</a>
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
