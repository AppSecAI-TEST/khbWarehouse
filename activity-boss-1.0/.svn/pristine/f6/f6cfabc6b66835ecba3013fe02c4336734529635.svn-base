<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>app广告详情</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
</head>
<body>
	<div class="Container">
		<div class="Content fontText">
			<div class="information">
			    <div class="clear"></div>
			    <div class="search_tit">
					<h2 class="fw fleft f14">app广告详情</h2>
				</div>
			      <div class="input_cont">
			        <ul>
			          <li>
			            <label class="text_tit">广告编码：</label>
			            <c:out value="${activityAppAdDTO.code }"></c:out>
			          </li>
			          <li><label class="text_tit">广告名称：</label>
						  <c:out value="${activityAppAdDTO.name }"></c:out>
					  </li>
                      <li><label class="text_tit">广告图片：</label>
                          <img alt="" src="lookAppAdImg?id=${activityAppAdDTO.id }">
                       </li>
                      <li><label class="text_tit">广告有效开始时间：</label>
                          <c:out value="${_messageFormater.formatDate(activityAppAdDTO.validTimeStart) }"></c:out>
                      </li>
                      <li><label class="text_tit">广告有效截止时间：</label>
                         <c:out value="${_messageFormater.formatDate(activityAppAdDTO.validTimeEnd) }"></c:out>
                      </li>
                      <li><label class="text_tit">广告URL：</label>
                         <c:out value="${activityAppAdDTO.url }"></c:out>
                      </li>
                       <li><label class="text_tit">广告创建时间：</label>
                         <c:out value="${_messageFormater.formatDate(activityAppAdDTO.createTime) }"></c:out>
                      </li>
                       <li><label class="text_tit">广告上次更改时间：</label>
                         <c:out value="${_messageFormater.formatDate(activityAppAdDTO.updateTime) }"></c:out>
                      </li>
                       <li><label class="text_tit">广告创建人：</label>
                         <c:out value="${activityAppAdDTO.cretor}"></c:out>
                      </li>
                       <li><label class="text_tit">广告上次更改人：</label>
                         <c:out value="${activityAppAdDTO.updator}"></c:out>
                      </li>
                      <li><label class="text_tit">广告状态：</label>
                       <c:forEach var="item"
                  items="${_textResource.getTextMap('activity_app_ad_status') }">
                  <c:if test="${item.key==activityAppAdDTO.status }">
                  ${item.value}
                  </c:if>
                </c:forEach>
                      </li>
			        </ul>
			      </div>
			      <div class="btn">
					<input type="button" onclick="window.history.go(-1)" class="btn_cancel fw" value="取消" />
				  </div>
				  <div class="clearer"></div>
			  </div>
			<br />
		</div>
	</div>
</body>
</html>
