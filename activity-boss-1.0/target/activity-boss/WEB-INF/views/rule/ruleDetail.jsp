<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>规则详情</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<%-- <script src="${resourceUrl}/js/product.js"></script> --%>
<script type="text/javascript">

  $(function () {
  
  });
</script>
</head>
<body>
	<div class="Container">
		<div class="Content fontText">
			<div class="information">
			    <div class="clear"></div>
			    <div class="search_tit">
					<h2 class="fw fleft f14">规则详情</h2>
				</div>
			      <div class="input_cont">
			        <ul>
			          <li>
			            <label class="text_tit">规则编码：</label>
			            <c:out value="${ruleDto.ruleCode }"></c:out>
			          </li>
			          <li><label class="text_tit">规则名称：</label>
						  <c:out value="${ruleDto.ruleName }"></c:out>
					  </li>
			          <li><label class="text_tit">生效时间：</label>
						  <c:out value="${_messageFormater.formatDate(ruleDto.takeEffectTime) }"></c:out>
					  </li>
			          <li><label class="text_tit">失效时间：</label>
						  <c:out value="${_messageFormater.formatDate(ruleDto.invalidTime)}"></c:out>
					  </li>
			          <li>
			            <label class="text_tit">事件列表：</label>
			            <table>
			            	<c:forEach items="${eventDtoList}" var="event" varStatus="num">
		            			<tr>
		            				<td>
					  					<c:out value="${event.eventName }" />
			            			</td>
							 	</tr>
			            	</c:forEach>
			            </table>
			          </li>
			        </ul>
			      </div>
			      <div class="btn">
					<input type="button" onclick="window.history.go(-1)" class="btn_cancel fw" value="取消" />
				  </div>
				  <div class="clearer"></div>
			    </form>
			  </div>
			<br />
		</div>
	</div>
</body>
</html>
