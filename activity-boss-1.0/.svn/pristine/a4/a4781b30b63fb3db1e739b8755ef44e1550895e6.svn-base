<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>修改app广告</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<script type="text/javascript">
  $(function () {
    DatePickerExt.time("validTimeStart");
    DatePickerExt.time("validTimeEnd");
     $("#updateAppAdForm").submit(function(){
          var flag = $(this).validateSubmit({
            code: {
                  req: true,
                  label: "广告编码"
              },
              name: {
                  req: true,
                  label: "广告名称"
              },
              url:{
                req: true,
                label: "url"
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
          if(!flag||!validate()){
            return false;
          }else{
            return true;
          }
      });
    function validate(){
      var validTimeStart=$("#validTimeStart").val();
      var validTimeEnd=$("#validTimeEnd").val();
      if(validTimeEnd<validTimeStart){
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
          <h2 class="fw fleft f14">修改app广告</h2>
        </div>
          <form name="updateAppAdForm" id="updateAppAdForm" action="updateAppAd" method="post" >
            <div class="input_cont">
              <ul>
                      <li>
                      <input type="hidden" id="id" name="id" value="${activityAppAdDTO.id }">
                        <input type="hidden" id="version" name="version" value="${activityAppAdDTO.version }">
                      </li>
                <li>
                  <label class="text_tit">广告编码：</label>
                  <input class="input_text" type="text" readonly="readonly"  value="${activityAppAdDTO.code  }">&nbsp;<font color="red">*</font>
                </li>
                <li>
                  <label class="text_tit">广告名称：</label>
                  <input class="input_text" type="text" name="name" id="name" value="${activityAppAdDTO.name  }" >&nbsp;<font color="red">*</font>
                </li>
                <li>
                  <label class="text_tit">广告url：</label>
                  <input class="input_text" type="text" name="url" id="url" value="${activityAppAdDTO.url  }" >&nbsp;<font color="red">*</font>
                </li>
                      <li>
                        <label class="text_tit">广告图片：</label>
                        <img alt="" src="lookAppAdImg?id=${activityAppAdDTO.id }">
                      </li>
                      <li>
                        <label class="text_tit">广告有效开始时间：</label>
                        <input  class="input_text" type="text" name="validTimeStart" id="validTimeStart" value="${_messageFormater.formatDate(activityAppAdDTO.validTimeStart) }" >&nbsp;<font color="red">*</font>
                      </li>
                      <li>
                        <label class="text_tit">广告有效截止时间：</label>
                        <input  class="input_text" type="text" name="validTimeEnd" id="validTimeEnd"  value="${_messageFormater.formatDate(activityAppAdDTO.validTimeEnd) }" >&nbsp;<font color="red">*</font>
                      </li>    
                      <li>
                        <label class="text_tit">创建时间：</label>
                        <input  class="input_text" type="text" readonly="readonly"  value="${_messageFormater.formatDate(activityAppAdDTO.createTime) }" >&nbsp;<font color="red">*</font>
                      </li> 
                      <li>
                        <label class="text_tit">上次修改时间：</label>
                        <input  class="input_text" type="text" readonly="readonly"  value="${_messageFormater.formatDate(activityAppAdDTO.updateTime) }" >&nbsp;<font color="red">*</font>
                      </li>
                <li>
                  <label class="text_tit">创建人：</label>
                  <input class="input_text" type="text" readonly="readonly" value="${activityAppAdDTO.cretor  }" >&nbsp;<font color="red">*</font>
                </li>  
                 <li>
                  <label class="text_tit">上次更改人员：</label>
                  <input class="input_text" type="text" readonly="readonly" value="${activityAppAdDTO.updator  }" >&nbsp;<font color="red">*</font>
                </li>  
                <li>
                <label class="text_tit">状态：</label>
                <select id="status" name="status">
                <c:forEach var="item"
                  items="${_textResource.getTextMap('activity_app_ad_status') }">
                  <option ${item.key==param.status?'selected':''}
                    value="${item.key}">${item.value}</option>
                </c:forEach>
                </select>
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
