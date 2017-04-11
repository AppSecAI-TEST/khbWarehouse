<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>修改流量通道规则</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<script type="text/javascript">
  $(function () {
	   $("#modifySrcFlowPlatForm").submit(function(){
          return $(this).validateSubmit({
        	  srcNo: {
                  req: true,
                  label: "渠道号"
              },
              mobileSection: {
                  req: true,
                  label: "手机号号段"
              },
              fluxPlatCode: {
                req: true,
                label: "流量平台号"
         	   }
          });
          if(!validate()){
    	      return false;
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
					<h2 class="fw fleft f14">流量通道规则修改</h2>
				</div>
			    <form name="modifySrcFlowPlatForm" id="modifySrcFlowPlatForm" action="modifySrcFlowPlat" method="post">
			      <div class="input_cont">
			        <ul>
                      <li>
                      <input type="hidden" id="id" name="id" value="${srcFlowPlatDTO.id }">
                      <input type="hidden" id="version" name="version" value="${srcFlowPlatDTO.version }">
                      </li>
			          <li>
			            <label class="text_tit">渠道编号：</label>
			            <input class="input_text" type="text" name="srcNo" id="srcNo" value="${srcFlowPlatDTO.srcNo  }">&nbsp;<font color="red">*</font>
			          </li>
                      <li>
                        <label class="text_tit">手机号号段：</label>
                        <input class="input_text" type="text" name="mobileSection" id="mobileSection" value="${srcFlowPlatDTO.mobileSection  }">
                      </li>
                    <%--   <li>
                        <label class="text_tit">流量平台号：</label>
                        <input class="input_text" type="text" name="fluxPlatCode" id="fluxPlatCode" value="${srcFlowPlatDTO.fluxPlatCode  }">&nbsp;<font color="red">*</font>
                      </li> --%>
                       <li><label class="text_tit">流量平台号：</label>
                        <select id="fluxPlatCode" name="fluxPlatCode" class="input_text">
                          <c:forEach var="item"
                            items="${_textResource.getTextMap('LM_FLUX_PLAT') }">
                            <option ${item.key==param.fluxPlatCode?'selected':''}
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
