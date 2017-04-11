<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<html>
<head>
<title>新增场景</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<script type="text/javascript">
  $(function () {
	  if($("#pitureFlag").val()=='false'){
      alert("上传文件不是图片，必须为jpg|gif|bmp|png");
    }
	   $("#addSceneForm").submit(function(){
           $(this).validateSubmit({
              sceneName: {
                  req: true,
                  label: "场景名称"
              },
              sceneDesc: {
                req: true,
                label: "场景描述"
            },
            sceneType: {
              req: true,
              label: "场景类型"
          }
          });
          if(!validate()){
    	      return false;
    	    }else{
    	      return true;
    	    }
      });
	  function validate(){
	   var desc = $("#sceneDesc").html();
	   if(desc.length>100){//超过100汉子
	     alert("场景描述不能超过100汉子");
	     return false;
	     }eles{
	       return true;
	     }
	   }
  }); 
  
</script>
</head>
<body>
  <div class="Container">
    <div class="Content fontText">
      <div class="information">
        <div class="clear"></div>
        <div class="search_tit">
          <h2 class="fw fleft f14">场景新增</h2>
        </div>
        <form name="addSceneForm" id="addSceneForm"
          action="addSceneInfo" method="post"
          enctype="multipart/form-data">
          <input type="hidden" id="pitureFlag" name="pitureFlag"
            value="${picture}">
          <div class="input_cont">
            <ul>
              <li><label class="text_tit">场景名称：</label> <input
                class="input_text" type="text" name="sceneName">&nbsp;<font
                color="red">*</font></li>
              <li><label class="text_tit">场景描述：</label> <textarea
                  rows="10" cols="100" name="sceneDesc" id="sceneDesc"></textarea>&nbsp;<font
                color="red">*</font></li>
              <li><label class="text_tit">场景类型：</label>
               <select
                  id="sceneType" name="sceneType"
                  class="input_text">
                    <c:forEach var="item"
                      items="${_textResource.getTextMap('zt_scene_type') }">
                      <option
                        value="${item.key}">${item.value}</option>
                    </c:forEach>
                </select>&nbsp;<font
                color="red">*</font></li>
              <li><label class="text_tit">场景图片：</label> <input
                class="input_text" type="file" name="sceneImg"
                accept="image/*">&nbsp;<font
                color="red">*</font></li>
            </ul>
          </div>
          <div class="btn">
            <input type="submit" class="btn_sure fw" value="保存" /> <input
              type="button" onclick="window.history.go(-1)"
              class="btn_cancel fw" value="取消" />
          </div>
          <div class="clearer"></div>
        </form>
      </div>
      <br />
    </div>
  </div>
</body>
</html>
