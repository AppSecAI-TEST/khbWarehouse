<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>修改场景</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<script type="text/javascript">
  $(function () {
    if($("#pitureFlag").val()=='false'){
      alert("上传文件不是图片，必须为jpg|gif|bmp|png");
    }

	  $("#updateSceneForm").submit(function(){
          return $(this).validateSubmit({
            sceneName: {
                  req: true,
                  label: "场景名称"
              }
          });
      });
  
</script>
</head>
<body>
	<div class="Container">
		<div class="Content fontText">
			<div class="information">
			    <div class="clear"></div>
			    <div class="search_tit">
					<h2 class="fw fleft f14">场景修改</h2>
				</div>
			    <form name="updateSceneForm" id="updateSceneForm" action="updateSceneInfo" method="post" enctype="multipart/form-data">
			      <div class="input_cont">
			        <ul>
                      <li>
                      <input type="hidden" id="id" name="id" value="${ztSceneDto.id }">
                      <input type="hidden" id="pitureFlag" name="pitureFlag" value="${picture}">
                        <input type="hidden" id="version" name="version" value="${ztSceneDto.version }">
                      </li>
			          <li>
			            <label class="text_tit">场景名称：</label>
			            <input class="input_text" type="text" name="sceneName" value="${ztSceneDto.sceneName  }" >&nbsp;<font color="red">*</font>
			          </li>
                      <li>
                        <label class="text_tit">场景图片：</label>
                        <img alt="" src="lookSceneImg?id=${ztSceneDto.id }">
                      </li>
                      <li>
                        <label class="text_tit">修改场景图片：</label>
                        <input class="input_text" type="file" name="sceneImg" accept="image/*">
                      </li>
                      <li>
                        <label class="text_tit">场景描述：</label>
                        <textarea rows="10" cols="100" name="sceneDesc" >${ztSceneDto.sceneDesc }</textarea>
                      </li>
                       <li><label class="text_tit">场景类型：</label>
               <select
                  id="sceneType" name="sceneType"
                  class="input_text" >
                    <c:forEach var="item"
              items="${_textResource.getTextMap('zt_scene_type') }">
              <option
                        ${item.key==ztSceneDto.sceneType?'selected':''}
                        value="${item.key}">${item.value}</option>
            </c:forEach>
                </select>&nbsp;<font
                color="red">*</font></li>
			        </ul>
			      </div>
			      <div class="btn">
					<input type="submit" class="btn_sure fw" value="保存" />
					<input type="button"onclick="window.history.go(-1)" class="btn_cancel fw" value="取消" />
				  </div>
				  <div class="clearer"></div>
			    </form>
			  </div>
			<br />
		</div>
	</div>
</body>
</html>
