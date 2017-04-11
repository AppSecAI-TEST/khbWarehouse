<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>修改投资换产品订单信息</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<%-- <script src="${resourceUrl}/js/product.js"></script> --%>
<script type="text/javascript">
$(function(){
  $("#updateInvForProOrder").submit(function(){
    return $(this).validateSubmit({
      status: {
            req: true,
            label: "订单状态"
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
					<h2 class="fw fleft f14">修改投资换产品订单信息</h2>
				</div>
			    <form name="updateInvForProOrder" id="updateInvForProOrder" action="updateInvForProOrder" method="post">
			      <div class="input_cont">
			        <ul>
			          <li style="display:none">
			            <input  value="${result.id }" name="id" id="id"  >
			            <input  value="${result.version }" name="version" id="version"  >
			          </li>
			          <li>
			            <label class="text_tit">订单编号：</label>
			            <input class="input_text" type="text" value="${result.orderCode }" name="goodsCode" id="goodsCode" readonly="readonly">
			          </li>
			          <li>
			            <label class="text_tit">会员编号：</label>
			            <input class="input_text" type="text" value="${result.memberNo }" name="memberNo" id="memberNo" readonly="readonly">
			          </li>
			           <li><label class="text_tit">订单状态：</label>
			          <input style="display:none" value="${result.status }" id="goodsOfType">
						  <select id="status" name="status" class="input_text" >
								<c:forEach var="item"
									items="${_textResource.getTextMap('activity_invForPro_order_status') }">
									
                                <c:if test="${item.key=='CANCE'||item.key=='SUCCESS'}">
                                <option ${item.key==result.status?'selected':''}
                                value="${item.key}">${item.value}</option>
                                </c:if>
										
								</c:forEach>
	                	  </select>&nbsp;<font color="red">*</font>
					  </li>
			          <li>
			            <label class="text_tit">物流单号：</label>
			            <input class="input_text" type="text" name="logisticsNumber" value="${result.logisticsNumber }" id="logisticsNumber"/>
			          </li>
			         
			        </ul>
			      </div>
			      <div class="btn">
					<input type="submit" class="btn_sure fw" value="修改" />
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
