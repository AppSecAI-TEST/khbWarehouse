<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>奖品详情</title>
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
					<h2 class="fw fleft f14">奖品详情</h2>
				</div>
			      <div class="input_cont">
			        <ul>
			          <li>
			            <label class="text_tit">奖品编码：</label>
			            <c:out value="${prizeDto.prizeCode }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">奖品名称：</label>
			            <c:out value="${prizeDto.prizeName }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">奖品等级：</label>
			            <c:if test="${prizeDto.prizeType == 'COUPON' }">优惠券</c:if>
			            <c:if test="${prizeDto.prizeType == 'RAFFLETICKET' }">抽奖券</c:if>
			            <c:if test="${prizeDto.prizeType == 'GOODS' }">商品</c:if>
			          </li>
			          <%-- <li>
			            <label class="text_tit">奖品类型：</label>
			            <c:if test="${prizeDto.prizeLevel == 'FIRST' }">特等奖</c:if>
			            <c:if test="${prizeDto.prizeLevel == 'SECOND' }">一等奖</c:if>
			            <c:if test="${prizeDto.prizeLevel == 'THIRD' }">二等奖</c:if>
			            <c:if test="${prizeDto.prizeLevel == 'FOURTH' }">三等奖</c:if>
			            <c:if test="${prizeDto.prizeLevel == 'FIFTH' }">四等奖</c:if>
			            <c:if test="${prizeDto.prizeLevel == 'SIXTH' }">五等奖</c:if>
			            <c:if test="${prizeDto.prizeLevel == 'seventh' }">六等奖</c:if>
			            <c:if test="${prizeDto.prizeLevel == 'eighth' }">七等奖</c:if>
			          </li> --%>
			          <li>
			            <label class="text_tit">奖品发放方式：</label>
			            <c:if test="${prizeDto.grantWay == 'LOGISTICS' }">物流</c:if>
			            <c:if test="${prizeDto.grantWay == 'AUTOMATIC' }">自动</c:if>
			          </li>
			          <li>
			            <label class="text_tit">奖品简介：</label>
			            <c:out value="${prizeDto.prizeRemark }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">对应奖品：</label>
			            <c:if test="${prizeDto.prizeType == 'COUPON' }">
			              <a href="${ctxPath}coupon/couponDetail?id=${prizeDto.relaGoodsId }">点击此处查看详情</a>
			            </c:if>
			            <c:if test="${prizeDto.prizeType == 'RAFFLETICKET' }">
			              <a href="${ctxPath}raffle/raffleTicketDetail?id=${prizeDto.relaGoodsId }">点击此处查看详情</a>
			            </c:if>
			            <c:if test="${prizeDto.prizeType == 'GOODS' }">
			              <a href="${ctxPath}goods/goodsDetail?id=${prizeDto.relaGoodsId}">点击此处查看详情</a>
			            </c:if>
			          </li>
			          <li>
			            <label class="text_tit">奖品总数：</label>
						  <c:out value="${prizeDto.prizeTotalCount }"></c:out>
			          </li>
			          <li>
			            <label class="text_tit">奖品已发数：</label>
						  <c:out value="${prizeDto.prizeGrantCount }"></c:out>
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
