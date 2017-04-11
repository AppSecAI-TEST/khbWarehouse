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
	  
 	  $("#modifyGoods").submit(function(){
           return $(this).validateSubmit();
       });

	  DatePickerExt.date("validityDate");
	
	$("#selectAll").change(function() {
		selectAll();
	});
	
	//根据奖品的类型，初始化虚拟或者是实物的状态
	$(document).ready(function() {
	  var goodsOfType = $("#goodsOfType").val();
	  $("#goodsType").val(goodsOfType);
	});

	
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
			    <form name="modifyGoods" id="modifyGoods" action="modifyGoods" method="post">
			      <div class="input_cont">
			        <ul>
			          <li style="display:none">
			            <input  value="${goodsDto.id }" name="id" id="id"  label="主键">
			            <input  value="${goodsDto.version }" name="version" id="version"  label="版本号">
			          </li>
			          <li>
			            <label class="text_tit">奖品编码：</label>
			            <input class="input_text" type="text" value="${goodsDto.goodsCode }" name="goodsCode" id="goodsCode" req="true" label="奖品编码">&nbsp;<font color="red">*</font>
			          </li>
			          <li>
			            <label class="text_tit">奖品名称：</label>
			            <input class="input_text" type="text" value="${goodsDto.goodsName }" name="goodsName" id="goodsName" req="true" label="奖品名称">&nbsp;<font color="red">*</font>
			          </li>
			          <li>
			            <label class="text_tit">奖品简介：</label>
			            <input class="input_text" type="text" value="${goodsDto.goodsRemark }" name="goodsRemark" id="goodsRemark" req="true" label="奖品简介">&nbsp;<font color="red">*</font>
			          </li>
			          <%-- <li><label class="text_tit">奖品类型：</label>
			          <input style="display:none" value="${goodsDto.goodsType }" id="goodsOfType">
						  <select id="goodsType" name="goodsType" class="input_text" req="true" label="奖品类型">
								<c:forEach var="item"
									items="${_textResource.getTextMap('activity_goods_type') }">
									<option ${item.key==param.goodsType?'selected':''}
										value="${item.key}">${item.value}</option>
								</c:forEach>
	                	  </select>&nbsp;<font color="red">*</font>
					  </li>
					  <li>
			            <label class="text_tit">奖品单价：</label>
			            <input class="input_text" type="text" value="${goodsDto.goodsPrice }" name="goodsPrice" id="goodsPrice" req="true" label="奖品单价">&nbsp;<font color="red">*</font>
			          </li> --%>
			          <li>
			            <label class="text_tit">奖品数量：</label>
			            <input class="input_text" type="text" name="totalCount" value="${goodsDto.totalCount }" id="totalCount" req="true" label="奖品数量">&nbsp;<font color="red">*</font>
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
