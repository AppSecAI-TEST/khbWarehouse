<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>修改渠道发放流量规则</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<script type="text/javascript">
  $(function () {
	  DatePickerExt.time("startDate");
	  DatePickerExt.time("endDate");

	  $("#updateActivityForm").submit(function(){
          return $(this).validateSubmit({
            srcNo: {
                  req: true,
                  label: "渠道编号"
              },
              startDate: {
                req: true,
                label: "规则生效时间"
           	 },
           		endDate: {
              req: true,
              label: "规则截止时间"
         	 },
          });
          if(!validate()){
    	      return false;
    	    }
      });
	  function validate(){
	    var startTime=$("#startDate").val();
	    var endTime=$("#endDate").val();
        if(startDate>endDate){
  	      alert("开始时间不能超过截止时间");
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
					<h2 class="fw fleft f14">渠道发放流量规则修改</h2>
				</div>
			    <form name="updateSrcFlowRuleForm" id="updateSrcFlowRuleForm" action="updateSrcFlowRule" method="post">
			      <div class="input_cont">
			        <ul>
                      <li>
                      <input type="hidden" id="id" name="id" value="${activitySrcFlowRuleDTO.id }">
                      <input type="hidden" id="version" name="version" value="${activitySrcFlowRuleDTO.version }">
                      </li>
			          <li>
			            <label class="text_tit">渠道编号：</label>
			            <input class="input_text" type="text" name="srcNo" id="srcNo" value="${activitySrcFlowRuleDTO.srcNo  }">&nbsp;<font color="red">*</font>
			          </li>
			          <li>
                        <label class="text_tit">操作类型：</label>
                        <select  name="opeType" id="opeType" title="select">
                          <c:forEach var="item" items="${_textResource.getTextMap('srcFlow_ope_type') }">
                            <option ${item.key==activitySrcFlowRuleDTO.opeType?'selected':''} value="${item.key}">${item.value}</option>
                          </c:forEach>
                        </select>
                        &nbsp;<font color="red">*</font>
                      </li>
                      <li>
                        <label class="text_tit">最小投资金额：</label>
                        <input class="input_text" type="text" name="minOpeAmount" id="minOpeAmount" value="${activitySrcFlowRuleDTO.minOpeAmount  }">
                      </li>
                      <li>
                        <label class="text_tit">最大投资金额：</label>
                        <input class="input_text" type="text" name="maxOpeAmount" id="maxOpeAmount" value="${activitySrcFlowRuleDTO.maxOpeAmount  }">&nbsp;<font color="red">*</font>
                      </li>
                      <li>
                        <label class="text_tit">移动编码：</label>
                        <input class="input_text" type="text" name="cmccCode" id="cmccCode" value="${activitySrcFlowRuleDTO.cmccCode  }">
                      </li>
                      <li>
                        <label class="text_tit">联通编码：</label>
                        <input class="input_text" type="text" name="cuccCode" id="cuccCode" value="${activitySrcFlowRuleDTO.cuccCode  }">
                      </li>
                      <li>
                        <label class="text_tit">电信编码：</label>
                        <input class="input_text" type="text" name="ctccCode" id="ctccCode" value="${activitySrcFlowRuleDTO.ctccCode  }">
                      </li>
                      <li>
                        <label class="text_tit">规则生效时间：</label>
                        <input id="startDate" class="input_text" type="text" name="startDate" value="${_messageFormater.formatDate(activitySrcFlowRuleDTO.startDate) }" >&nbsp;<font color="red">*</font>
                      </li>
                      <li>
                        <label class="text_tit">规则截止时间：</label>
                        <input id="endDate" class="input_text" type="text" name="endDate" value="${_messageFormater.formatDate(activitySrcFlowRuleDTO.endDate) }" >&nbsp;<font color="red">*</font>
                      </li>         
                      <li>
                        <label class="text_tit">是否发送流量：</label>
                        <select name="sendFlag" id="sendFlag" >
                          <option value=1 ${activitySrcFlowRuleDTO.sendFlag==1?'selected':''}>是</option>
                          <option value=0 ${activitySrcFlowRuleDTO.sendFlag==0?'selected':''}>否</option>
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
