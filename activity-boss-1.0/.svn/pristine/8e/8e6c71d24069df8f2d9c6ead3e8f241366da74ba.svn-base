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
			            <label class="text_tit">产品名称：</label>
			            <c:out value="${invForProInfoDto.name }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">活动编码：</label>
			            <c:out value="${invForProInfoDto.activityCode }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">产品金额：</label>
						  <c:out value="${invForProInfoDto.price }"></c:out>
			          </li>
			          
			          <li>
			            <label class="text_tit">产品数量：</label>
						  <c:out value="${invForProInfoDto.stockNum }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">产品已发数量：</label>
						  <c:out value="${invForProInfoDto.usedNum }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">URL：</label>
						  <c:out value="${invForProInfoDto.url }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">最低购买理财价格：</label>
						  <c:out value="${invForProInfoDto.productPriceLow }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">收益率：</label>
						  <c:out value="${invForProInfoDto.rate }"></c:out>
			          </li>
			          <li>
				            <label class="text_tit">选择规则：</label>
				            <table>
				            	<c:forEach items="${ruleDtoList}" var="ruleDto" varStatus="num">
				            		<c:if test="${num.index%3==0}">
				            			<tr>
				            		</c:if>
				            				<td>
					            				
	                                                                                                                        规则序号：${ruleDto.id }; 规则名称： ${ruleDto.ruleName } <span style="width:30px"></span>
					            			</td>
			            			<c:if test="${num.index==2||num.index%3==2}">  
									 	</tr>
									 </c:if>
				            	</c:forEach>
				            </table>
				          </li>
			          <li><label class="text_tit">微信活动图片：</label>
                          <img alt="" src="lookInvForProImg?id=${invForProInfoDto.id }&type=WX">
                       </li>
                       <li><label class="text_tit">PC活动图片：</label>
                          <img alt="" src="lookInvForProImg?id=${invForProInfoDto.id }&type=PC">
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
