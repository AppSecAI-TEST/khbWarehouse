<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>修改奖品</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<%-- <script src="${resourceUrl}/js/product.js"></script> --%>
<script type="text/javascript">

  $(function () {
	  
 	  $("#modifyPrize").submit(function(){
           return $(this).validateSubmit({});
       });

	  DatePickerExt.date("validityDate");
	
	$("#selectAll").change(function() {
		selectAll();
	});
	
	//根据奖品的类型和发放方式初始化信息
	$(document).ready(function() {
	  //奖品类型
	  var prizeOfType = $("#prizeOfType").val();
	  $("#prizeType").val(prizeOfType);
	  //发放方式
	  var wayOfType = $("#wayOfType").val();
	  $(".grantWay").val(wayOfType);
	  
	  init(prizeOfType);
	});

  //奖品类型改变展示不同的奖品
  $("#prizeType").change(function() {
      //移除被选中的项
	  $(".radioChecked").removeAttr("checked");
	  var prizeType = $(this).children('option:selected').val();
	  init(prizeType);
  });
  
  function init(prizeType) {
  //优惠券
	  if("COUPON" == prizeType) {
		  $("#coupon").show();
		  $("#raffleTicket").hide();
		  $("#goods").hide();
		  //奖品发放方式，自动
		  /* $(".grantWay").val("AUTOMATIC") */
	  } else if("RAFFLETICKET" == prizeType){
	      //抽奖券
	      $("#raffleTicket").show();
		  $("#coupon").hide();
		  $("#goods").hide();
		  //奖品发放方式，自动
		 /*  $(".grantWay").val("AUTOMATIC") */
	  } else {
	      //商品
	      $("#goods").show();
		  $("#raffleTicket").hide();
		  $("#coupon").hide();
		  //奖品发放方式，物流
		  /* $(".grantWay").val("LOGISTICS") */
	  }
  }
	
  });
  
  //复选框事件  
  //全选、取消全选的事件  
  /* function selectAll(){  
      if ($("#selectAll").attr("checked")) {  
          $(":checkbox").attr("checked", true);  
      } else {  
          $(":checkbox").attr("checked", false);  
      }  
  }  */ 
  
</script>
</head>
<body>
	<div class="Container">
		<div class="Content fontText">
			<div class="information">
			    <div class="clear"></div>
			    <div class="search_tit">
					<h2 class="fw fleft f14">修改奖品</h2>
				</div>
			    <form name="modifyPrize" id="modifyPrize" action="modifyPrize" method="post">
			      <div class="input_cont">
			        <ul>
			          <li style="display:none">
			            <input  value="${prizeDto.id }" name="id" id="id"  label="主键">
			            <input  value="${prizeDto.version }" name="version" id="version"  label="版本号">
			          </li>
			          <li>
			            <label class="text_tit">奖品编码：</label>
			            <input class="input_text" type="text" value="${prizeDto.prizeCode }" name="prizeCode" id="prizeCode" req="true" label="奖品编码">&nbsp;<font color="red">*</font>
			          </li>
			          <li>
			            <label class="text_tit">奖品名称：</label>
			            <input class="input_text" type="text" value="${prizeDto.prizeName }" name="prizeName" id="prizeName" req="true" label="奖品名称">&nbsp;<font color="red">*</font>
			          </li>
			          <li><label class="text_tit">奖品类型：</label>
			          <input style="display:none" value="${prizeDto.prizeType }" id="prizeOfType">
						  <select id="prizeType" name="prizeType" class="input_text" req="true" label="奖品类型">
								<c:forEach var="item"
									items="${_textResource.getTextMap('activity_prize_type') }">
									<option ${item.key==param.prizeType?'selected':''}
										value="${item.key}">${item.value}</option>
								</c:forEach>
	                	  </select>&nbsp;<font color="red">*</font>
					  </li>
					  
					  <li><label class="text_tit">奖品发放方式：</label>
					  <%-- <input name="grantWay"  value="${prizeDto.grantWay }" class="grantWay" style="display:none"/> --%>
						  <select class="grantWay"  name="grantWay" class="input_text" req="true" label="奖品发放方式" >
								<c:forEach var="item"
									items="${_textResource.getTextMap('activity_grant_way') }">
									<option ${item.key==prizeDto.grantWay?'selected':''}
										value="${item.key}">${item.value}</option>
								</c:forEach>
	                	  </select>&nbsp;<font color="red">*</font>
					  </li>
			          <li>
			            <label class="text_tit">奖品总数：</label>
			            <input class="input_text" type="text" name="prizeTotalCount" value="${prizeDto.prizeTotalCount }" id="prizeTotalCount" req="true" label="奖品数量">&nbsp;<font color="red">*</font>
			          </li>
			          <li>
			            <label class="text_tit">奖品：</label>
			            
			            <!-- 优惠券 -->
			            <c:if test="${empty couponDtoList }">
				            <table border="1" id="coupon">
				            	<tr><td>无相应奖品</td></tr>
				            </table>
			            </c:if>
			            <c:if test="${!empty couponDtoList }">
				            <table border="1" id="coupon">
				            	<tr><td></td><td>名称</td><td>剩余数量</td><td>小栏目</td></tr>
				            	<c:forEach var="coupon" items="${couponDtoList }" >
				            		<tr>
					            		<td><input name="relaGoodsId" <c:if test="${prizeDto.prizeType =='COUPON' && prizeDto.relaGoodsId == coupon.id }">checked</c:if> type="radio" value="${coupon.id }"
					            		   class="radioChecked"/></td><td>${coupon.couponName }</td>
					            		<td>${coupon.totalCount- coupon.grantCount}</td>
					            		<td>无</td>
				            		</tr>
				                </c:forEach>
				            </table>
			            </c:if>
			            
			            <!-- 抽奖券 -->
			            <c:if test="${empty raffleTicketDtoList }">
				            <table border="1" id="raffleTicket" style="display:none">
				            	<tr><td>无相应奖品</td></tr>
				            </table>
			            </c:if>
			            <c:if test="${!empty raffleTicketDtoList }">
				            <table border="1" id="raffleTicket" style="display:none">
				            	<tr><td></td><td>名称</td><td>剩余数量</td><td>小栏目</td></tr>
				            	<c:forEach var="raffleTicket" items="${raffleTicketDtoList }" >
				            		<tr>
					            		<td><input name="relaGoodsId" type="radio" <c:if test="${prizeDto.prizeType =='RAFFLETICKET' && prizeDto.relaGoodsId == raffleTicket.id }">checked</c:if> value="${raffleTicket.id }" 
					            		class="radioChecked"/></td><td>${raffleTicket.raffleTicketName }</td>
					            		<td>${raffleTicket.totalCount- raffleTicket.grantCount}</td>
					            		<td>无</td>
				            		</tr>
				                </c:forEach>
				            </table>
			            </c:if>
			            
			            <!-- 商品 -->
			            <c:if test="${empty goodsDtoList }">
				            <table border="1" id="goods" style="display:none">
				            	<tr><td>无相应奖品</td></tr>
				            </table>
			            </c:if>
			            <c:if test="${!empty goodsDtoList }">
				            <table border="1" id="goods" style="display:none">
				            	<tr><td></td><td>名称</td><td>剩余数量</td><td>小栏目</td></tr>
				            	<c:forEach var="goods" items="${goodsDtoList }" >
				            		<tr>
					            		<td><input name="relaGoodsId" type="radio" <c:if test="${prizeDto.prizeType =='GOODS' && prizeDto.relaGoodsId == goods.id }">checked</c:if> value="${goods.id }" 
					            		class="radioChecked"/></td><td>${goods.goodsName }</td>
					            		<td>${goods.totalCount- goods.grantCount}</td>
					            		<td>无</td>
				            		</tr>
				                </c:forEach>
				            </table>
			            </c:if>
			          </li>
			          <li>
			            <label class="text_tit">奖品说明：</label>
			            <p>
			              <textarea name="prizeRemark" id="prizeRemark" class="textfield">${prizeDto.prizeRemark}</textarea>
			              <span style="color: gray; font-size:12px;">说明：最多允许输入200个字</span>
			            </p>
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
