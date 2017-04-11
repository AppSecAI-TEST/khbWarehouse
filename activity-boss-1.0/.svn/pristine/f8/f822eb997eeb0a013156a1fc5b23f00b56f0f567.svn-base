<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>新增活动规则</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<script type="text/javascript">
  $(function () {
	  DatePickerExt.date("takeEffectTime");
	  DatePickerExt.date("invalidTime");

	  $("#addRuleForm").submit(function(){
          return $(this).validateSubmit({
        	  ruleCode: {
                  req: true,
                  label: "规则编码"
              },
              ruleName: {
                  req: true,
                  label: "规则名称"
              },
              takeEffectTime: {
                  req: true,
                  label: "生效时间"
              },
              events: {
                  req: {
                      min: 1
                  },
                  label: "发放优惠券"
              }
          });
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
					<h2 class="fw fleft f14">活动规则新增</h2>
				</div>
			    <form name="addRuleForm" id="addRuleForm" action="addRule" method="post">
			      <div class="input_cont">
			        <ul>
			          <li>
			            <label class="text_tit">规则编码：</label>
			            <input class="input_text" type="text" name="ruleCode" id="ruleCode">&nbsp;<font color="red">*</font>
			          </li>
			          <li>
			            <label class="text_tit">规则名称：</label>
			            <input class="input_text" type="text" name="ruleName">&nbsp;<font color="red">*</font>
			          </li>
			          <li>
			            <label class="text_tit">生效时间：</label>
			            <input id="takeEffectTime" class="input_text" type="text" name="takeEffectTime">&nbsp;<font color="red">*</font>
			          </li>
			          <li>
			            <label class="text_tit">失效时间：</label>
			            <input id="invalidTime" class="input_text" type="text" name="invalidTime">
			          </li>
			          <li>
			            <label class="text_tit">选择事件：</label>
			            <table>
			            	<c:forEach items="${eventList}" var="event" varStatus="num">
			            		<c:if test="${num.index%3==0}">
			            			<tr>
			            		</c:if>
			            				<td>
				            				<input name="events" type="checkbox" value="${event.id }"/>${event.eventName }
				            			</td>
		            			<c:if test="${num.index%2==0&&num.index%3==0&&num.index!=0}">  
								 	</tr>
								 </c:if>
			            	</c:forEach>
			            </table>
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
