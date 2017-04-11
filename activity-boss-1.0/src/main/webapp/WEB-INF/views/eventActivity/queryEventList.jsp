<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>活动查询</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<%-- <script src="${resourceUrl}/js/product.js"></script> --%>
<script type="text/javascript">
  
  /**
   *跳转至新增活动事件页面
   */
  function addEvent() {
    var action = "eventActivity/toAddEvent";
    window.location.href = GV.ctxPath + action;
  }
</script>
</head>
<body>
  <div class="Container">
    <div class="Content fontText">
      <!--search star-->
      <div class="information">
        <form id="queryActionForm" method="get"
          action="${ctxPath}eventActivity/queryEventList">
          <div class="search">
            <div class="search_tit">
              <h2 class="fw fleft f14">活动事件查询</h2>
            </div>
            <div class="search_con">
              <ul class="fix">
                <li><label class="text_tit">事件编码：</label> <input
                  type="text" name="actionCode" class="input_text"
                  value="<c:out value="${actionCode}"/>" /></li>
                <li><label class="text_tit">事件名称：</label> <input
                  type="text" name="actionName" class="input_text"
                  value="<c:out value="${actionName}"/>" /></li>
              </ul>
              <div class="btn">
                <input type="submit" class="btn_sure fw" value="查询" />
                <input type="button" onclick="clearAll();" class="btn_cancel fw" value="清空" />
                <input type="button" class="btn_sure fw" onclick="addEvent()" value="新增事件" />
              </div>
              <div class="clearer"></div>
            </div>
          </div>
        </form>
      </div>
      <div class="clearer"></div>
      <div class="result">
        <h2 class="fw">查询结果</h2>
        <q:table queryService="activityQueryActionService" queryKey="queryActionList"
          template="querytableForUnion" formId="queryActionForm"
          class="table_list" pageSize="15">
          <q:nodata>无符合条件的记录</q:nodata>
          <q:column title="序号" value="${id}" />
          <q:column title="事件编码" value="${action_code}" />
          <q:column title="事件名称" value="${action_name}" />
          <q:column title="创建时间" value="${_messageFormater.formatDate(create_time)}" />
          <q:column title="操作" escapeHtml="false">
            <a title="详情查看" href="eventDetail?id=${id }">查看</a>
                        &nbsp;
                        <a title="详情修改" href="toUpdateEvent?id=${id }">修改</a>
          </q:column>
        </q:table>
      </div>
      <div class="clearer"></div>
      <br />
    </div>
  </div>
</body>
</html>
