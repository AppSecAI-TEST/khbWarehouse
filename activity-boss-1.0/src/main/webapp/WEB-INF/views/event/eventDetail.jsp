<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>事件详情</title>
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
					<h2 class="fw fleft f14">事件详情</h2>
				</div>
			      <div class="input_cont">
			        <ul>
			          <li>
			            <label class="text_tit">事件编码：</label>
			            <c:out value="${eventDto.eventCode }"></c:out>
			          </li>
			          <li><label class="text_tit">事件名称：</label>
						  <c:out value="${eventDto.eventName }"></c:out>
					  </li>
			          <li>
			            <label class="text_tit">发放优惠券：</label>
			            <table>
			            	<c:forEach items="${couponDtoList}" var="coupon" varStatus="num">
		            			<tr>
		            				<td>
					  					<c:out value="${coupon.couponName }" />
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
