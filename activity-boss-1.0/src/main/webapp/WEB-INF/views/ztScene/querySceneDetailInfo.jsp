<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>场景详情</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
</head>
<body>
	<div class="Container">
		<div class="Content fontText">
			<div class="information">
			    <div class="clear"></div>
			    <div class="search_tit">
					<h2 class="fw fleft f14">场景详情</h2>
				</div>
			      <div class="input_cont">
			        <ul>
			          <li><label class="text_tit">场景名称：</label>
						  <c:out value="${ztSceneDto.sceneName }"></c:out>
					  </li>
             <li><label class="text_tit">场景名称：</label>
              <c:out value="${ztSceneDto.sceneDesc }"></c:out>
            </li>
            <li><label class="text_tit">场景类型：</label>
             <c:forEach var="item"
              items="${_textResource.getTextMap('zt_scene_type') }">
              <c:if test="${item.key == ztSceneDto.sceneType }">
                <c:out value="${item.value }"></c:out>
              </c:if></c:forEach>
            </li>
                      <li><label class="text_tit">场景图片：</label>
                          <img alt="" src="lookSceneImg?id=${ztSceneDto.id }">
                       </li>
<li><label class="text_tit">审核状态：</label>
            <c:forEach var="item"
              items="${_textResource.getTextMap('zt_scene_status') }">
              <c:if test="${item.key == ztSceneDto.checkStatus }">
                <c:out value="${item.value }"></c:out>
              </c:if>
            </c:forEach>
           </li>
            <li><label class="text_tit">创建时间：</label>
              <c:out value="${_messageFormater.formatDate(ztSceneDto.createTime) }"></c:out>
            </li>
            <li><label class="text_tit">创建人：</label>
              <c:out value="${ztSceneDto.creator }"></c:out>
            </li>
            <li><label class="text_tit">审核时间：</label>
              <c:out value="${_messageFormater.formatDate(ztSceneDto.checkTime) }"></c:out>
            </li>
            <li><label class="text_tit">审核人：</label>
              <c:out value="${ztSceneDto.checker }"></c:out>
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
