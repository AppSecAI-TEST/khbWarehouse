<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>策略详情</title>
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
					<h2 class="fw fleft f14">策略详情</h2>
				</div>
			      <div class="input_cont">
			        <ul>
			          <li>
			            <label class="text_tit">策略名称：</label>
			            <c:out value="${ztPolicyDTO.policyName }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">波动性类型 ：</label>
			            <c:out value="${ztPolicyDTO.fluctuate }"></c:out>
			          </li>
			          <li id="couponAmount_li">
			            <label class="text_tit">策略类型：</label>
						  <c:out value="${ztPolicyDTO.policyType }"></c:out>
			          </li>
			          <li id="couponAmount_li">
			            <label class="text_tit">计划愿望：</label>
						  <c:out value="${ztPolicyDTO.sugWish }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">策略简介：</label>
						  <c:out value="${ztPolicyDTO.policyDesc }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">相关费用：</label>
						  <c:out value="${ztPolicyDTO.costDesc }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">注意事项：</label>
						  <c:out value="${ztPolicyDTO.attentionDesc }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">起购金额：</label>
						  <c:out value="${ztPolicyDTO.minPurchaseAmount }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">创建人：</label>
						  <c:out value="${ztPolicyDTO.creator }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">建议投资年限：</label>
						  <c:out value="${ztPolicyDTO.sugInvYear }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">最近投资的基金年月：</label>
						  <c:out value="${ztPolicyDTO.lastEstablishFundYear }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">是否重新计算策略收益率：</label>
						  <c:out value="${ztPolicyDTO.whetherReCalculate }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">收益率计算状态：</label>
						  <c:out value="${ztPolicyDTO.calculateStatus }"></c:out>
			          </li>
			        </ul>
			      </div>
			      <div class="rateTable light-gray tc radius1">
		                <table id="table" cellpadding="0" cellspacing="0" width="50%" style="text-align:center">
		                    <tbody>
		                    	<tr>
			                    	<th>占比</th>
			                    	<th>名称</th>
			                    	<th>code</th>
			                    	<th>类型</th>
			                    	<th>起购金额</th>
		                    	</tr>
		                    	<tr>
		                    	<c:forEach var="product" items="${fundList}">
			                    	<tr>
								      <td>${product.productProportion }</td>
								      <td>${product.productName }</td>
								      <td>${product.productCode }</td>
								      <td>${product.productType }</td>
								      <td>${product.productMinAmount }</td>
								    </tr>
							    </c:forEach>
    						</tbody>
		                </table>
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
