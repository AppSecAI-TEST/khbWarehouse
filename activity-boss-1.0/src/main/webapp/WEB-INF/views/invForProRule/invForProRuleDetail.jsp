<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>产品详情</title>
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
					<h2 class="fw fleft f14">产品详情</h2>
				</div>
			      <div class="input_cont">
			        <ul>
			          <li>
			            <label class="text_tit">规则名称：</label>
			            <c:out value="${InvForProRuleInfoDto.ruleName }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">期限：</label>
			            <c:out value="${InvForProRuleInfoDto.trem }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">收益率：</label>
			            <c:out value="${InvForProRuleInfoDto.rate }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">单价：</label>
						  <c:out value="${InvForProRuleInfoDto.price }"></c:out>
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
