<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<html>
<head>
<title>活动查询</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<script type="text/javascript">
$(function(){
  DatePickerExt.time("startTime");
  DatePickerExt.time("endTime");
});
  /**
   *跳转至新增活动页面
   */
  function addActivity () {
    var action = "activity/toAddActivity";
    window.location.href = GV.ctxPath + action;
  }
</script>
</head>
<body>
  <div class="Container">
    <div class="Content fontText">
      <!--search star-->
      <div class="information">
        <form id="queryActivityForm" method="get" enctype="multipart/form-data"
          action="${ctxPath}activity/queryActivityList">
          <div class="search">
            <div class="search_tit">
              <h2 class="fw fleft f14">活动查询</h2>
            </div>
            <div class="search_con">
              <ul class="fix">
                <li><label class="text_tit">活动编码：</label> <input
                  type="text" name="activityCode" class="input_text"
                  value="<c:out value="${activityCode}"/>" /></li>
                <li><label class="text_tit">活动名称：</label> <input
                  type="text" name="activityName" class="input_text"
                  value="<c:out value="${activityName}"/>" /></li>
                   <li><label class="text_tit">开始时间：</label> <input
                  type="text" id="startTime" name="startTime"  readonly="readonly" class="input_text"
                  value="<c:out value="${startTime}"/>" /></li>
                  <li><label class="text_tit">截止时间：</label> <input
                  type="text" id="endTime" name="endTime" readonly="readonly" class="input_text"
                  value="<c:out value="${endTime}"/>" /></li>
                <li><label class="text_tit">活动状态： </label> <select
                  id="activityStatus" name="activityStatus"
                  class="input_text">
                    <option ${empty param.activityStatus?'selected':'' }
                      value="">全部</option>
                    <c:forEach var="item"
                      items="${_textResource.getTextMap('activity_activity_status') }">
                      <option
                        ${item.key==param.activityStatus?'selected':''}
                        value="${item.key}">${item.value}</option>
                    </c:forEach>
                </select></li>
              </ul>
              <div class="btn">
                <input type="submit" class="btn_sure fw" value="查询" />
                <input type="button" onclick="clearAll();"
                  class="btn_cancel fw" value="清空" /> <input
                  type="button" class="btn_sure fw"
                  onclick="addActivity()" value="新增活动" />
              </div>
              <div class="clearer"></div>
            </div>
          </div>
        </form>
      </div>
      <div class="clearer"></div>
      <div class="result">
        <h2 class="fw">查询结果</h2>
        <q:table queryService="activityQueryActionService"
          queryKey="queryActivityList" template="querytableForUnion"
          formId="queryActivityForm" class="table_list" pageSize="15">
          <q:nodata>无符合条件的记录</q:nodata>
          <q:column title="序号" value="${id}" />
          <q:column title="活动编码" value="${activity_code}" />
          <q:column title="活动名称" value="${activity_name}" />
          <q:column title="活动上线时间"
            value="${_messageFormater.formatDate(upline_time)}" />
          <q:column title="活动开始时间"
            value="${_messageFormater.formatDate(start_time)}" />
          <q:column title="活动结束时间"
            value="${_messageFormater.formatDate(end_time)}" />
          <q:column title="状态">
            <c:forEach var="item"
              items="${_textResource.getTextMap('activity_activity_status') }">
              <c:if test="${item.key == activity_status }">
                <c:out value="${item.value }"></c:out>
              </c:if>
            </c:forEach>
          </q:column>
          <q:column title="创建时间"
            value="${_messageFormater.formatDate(create_time)}" />
          <q:column title="创建人" value="${creator}" />
          <q:column title="审核时间"
            value="${_messageFormater.formatDate(checked_time)}" />
          <q:column title="审核人" value="${checkor}" />
          <q:column title="操作" escapeHtml="false">
            <a title="详情查看" href="activityDetail?id=${id }">查看</a>
            <a title="修改" href="toUpdateActivity?id=${id }">修改</a>
          </q:column>
        </q:table>
      </div>
      <div class="clearer"></div>
      <br />
    </div>
  </div>
</body>
</html>
