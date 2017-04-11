<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>新增app广告页</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<script type="text/javascript">
  $(function () {
    DatePickerExt.time("validStartTime");
    DatePickerExt.time("validEndTime");
     $("#addAppAdForm").submit(function(){
           $(this).validateSubmit({
            code: {
                  req: true,
                  label: "广告编码"
              },
              name: {
                  req: true,
                  label: "广告名称"
              },
              validStartTime: {
                  req: true,
                  label: "广告有效开始时间"
              },
              validEndTime: {
                req: true,
                label: "广告有效结束时间"
             },
              status: {
              req: true,
              label: "状态"
           }
          });
          if(!validate()){
            return false;
          }else{
            return true;
          }
      });
    function validate(){
      var validStartTime=$("#validStartTime").val();
      var validEndTime=$("#validEndTime").val();
      if(validEndTime<validStartTime){
        alert("有效截止时间不能小于开始时间");
        return false;
      }
      return true;
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
          <h2 class="fw fleft f14">新增app广告页</h2>
        </div>
          <form name="addAppAdForm" id="addAppAdForm" action="addAppAd" method="post" enctype="multipart/form-data">
                <div class="input_cont">
              <ul>
                <li>
                  <label class="text_tit">广告编码：</label>
                  <input class="input_text" type="text" name="code" id="code">&nbsp;<font color="red">*</font>
                </li>
                <li>
                  <label class="text_tit">广告名称：</label>
                  <input class="input_text" type="text" name="name">&nbsp;<font color="red">*</font>
                </li>
                  <li>
                  <label class="text_tit">广告url：</label>
                  <input class="input_text" type="text" name="url">
                </li>
                <li>
                        <label class="text_tit">广告图片：</label>
                         <input class="input_text" type="file" name="adImg"  accept="image/*">
                        <font color="red">图片格式仅支持JPEG|jpeg|JPG|jpg|GIF|gif|BMP|bmp|PNG|png</font>
                      </li>
                      <li>
                        <label class="text_tit">有效开始时间：</label>
                        <input id="validStartTime" class="input_text" type="text" readonly="readonly" name="validTimeStart">&nbsp;<font color="red">*</font>
                      </li>
                      <li>
                        <label class="text_tit">有效结束时间：</label>
                        <input id="validEndTime" class="input_text" type="text" readonly="readonly" name="validTimeEnd">&nbsp;<font color="red">*</font>
                      </li>
                       <li>
                  <label class="text_tit">状态：</label>
                 <select id="status" name="status">
                <c:forEach var="item"
                  items="${_textResource.getTextMap('activity_app_ad_status') }">
                  <option 
                    value="${item.key}">${item.value}</option>
                </c:forEach>
                </select>&nbsp;<font color="red">*</font>
                </li>
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
