<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<html>
<head>
<title>app广告页查询</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<script type="text/javascript">
$(function(){
  DatePickerExt.time("createTimeStart");
  DatePickerExt.time("createTimeEnd");
});
  /**
   *跳转至新增app页面
   */
  function addAppAd () {
    var action = "app/toAddAppAd";
    window.location.href = GV.ctxPath + action;
  }
</script>
</head>
<body>
  <div class="Container">
    <div class="Content fontText">
      <!--search star-->
      <div class="information">
        <form id="queryAppAdForm" method="get" 
          action="${ctxPath}app/queryAppAd">
          <div class="search">
            <div class="search_tit">
              <h2 class="fw fleft f14">app广告页查询</h2>
            </div>
            <div class="search_con">
              <ul class="fix">
                 <li><label class="text_tit">广告编码：</label> <input
                  type="text" id="code" name="code" class="input_text"
                  value="<c:out value="${code}"/>" /></li>
                   <li><label class="text_tit">开始时间：</label> <input
                  type="text" id="createTimeStart" name="createTimeStart"  readonly="readonly" class="input_text"
                  value="<c:out value="${createTimeStart}"/>" /></li>
                  <li><label class="text_tit">截止时间：</label> <input
                  type="text" id="createTimeEnd" name="createTimeEnd" readonly="readonly" class="input_text"
                  value="<c:out value="${createTimeEnd}"/>" /></li>
                <li><label class="text_tit">状态： </label> <select
                  id="status" name="status"
                  class="input_text">
                    <option ${empty param.status?'selected':'' }
                      value="">全部</option>
                    <c:forEach var="item"
                      items="${_textResource.getTextMap('activity_app_ad_status') }">
                      <option
                        ${item.key==param.status?'selected':''}
                        value="${item.key}">${item.value}</option>
                    </c:forEach>
                </select></li>
              </ul>
              <div class="btn">
                <input type="submit" class="btn_sure fw" value="查询" />
                <input type="button" onclick="clearAll();"
                  class="btn_cancel fw" value="清空" /> <input
                  type="button" class="btn_sure fw "
                  onclick="addAppAd()" value="新增" />
              </div>
              <div class="clearer"></div>
            </div>
          </div>
        </form>
      </div>
      <div class="clearer"></div>
      <div class="result">
        <h2 class="fw">查询结果</h2>
        <q:table queryService="appAdQueryService"
          queryKey="queryAppAdList" template="querytableForUnion"
          formId="queryAppAdForm" class="table_list" pageSize="15">
          <q:nodata>无符合条件的记录</q:nodata>
          <q:column title="序号" value="${id}" />
          <q:column title="广告编码" value="${code}" />
          <q:column title="有效开始时间"
            value="${_messageFormater.formatDate(valid_time_start)}" />
          <q:column title="有效结束时间"
            value="${_messageFormater.formatDate(valid_time_end)}" />
          <q:column title="状态">
            <c:forEach var="item"
              items="${_textResource.getTextMap('activity_app_ad_status') }">
              <c:if test="${item.key == status }">
                <c:out value="${item.value }"></c:out>
              </c:if>
            </c:forEach>
          </q:column>
          <q:column title="创建时间"
            value="${_messageFormater.formatDate(create_time)}" />
          <q:column title="创建人" value="${cretor}" />
          <q:column title="更改时间"
            value="${_messageFormater.formatDate(update_time)}" />
          <q:column title="更改人" value="${updator}" />
          <q:column title="操作" escapeHtml="false">
            <a title="详情查看" href="queryAppAdDetail?id=${id }">查看</a>
            <a title="修改" href="toUpdateAppAd?id=${id }">修改</a>
          </q:column>
        </q:table>
      </div>
      <div class="clearer"></div>
      <br />
    </div>
  </div>
</body>
</html>
