<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>新增商品</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<%-- <script src="${resourceUrl}/js/product.js"></script> --%>
<script type="text/javascript">

  $(function () {
	// From数据校验
// 	  var valAllList = {
// 		  couponName: {
// 	      req: true,
// 	      label: "商品名称"
// 	    }
// 	  };
// 	  ValidateExt.listen("addGoodsForm",valAllList);
	  
	  $("#addGoodsForm").submit(function(){
          return $(this).validateSubmit({});
      });

	  DatePickerExt.date("validityDate");

// 	商品类型
	  $("#couponType").change(function() {
		
		  var couponType = $(this).children('option:selected').val();
		  if("INTEREST_ADD" == couponType) {
			  $("#increaseInterest_li").show();
			  $("#increaseInterest").attr("req","true");
			  
			  $("#couponAmount").removeAttr("req");
		      $("#couponAmount").val("");
			  $("#couponAmount_li").hide();
			  
			  //优惠类型
			  $("#discountType_li").hide();
			  
			  
		  } else {
			  $("#increaseInterest").removeAttr("req");
		      $("#increaseInterest").val("");
			  $("#increaseInterest_li").hide();
			  
			  $("#couponAmount_li").show();
			  $("#couponAmount").attr("req","true");

			  $("#discountType_li").show();
		  }
	  });
	
// 	有效期类型
	$("#validityType").change(function() {
		
		var validityType = $(this).children('option:selected').val();
		if("FLOAT" == validityType) {
			$("#validityDays_li").show();
			$("#validityDays").attr("req","true");
			
			$("#validityDate_li").hide();
			$("#validityDate").val("");
			$("#validityDate").removeAttr("req");

		} else if("FIXED" == validityType) {
			$("#validityDate_li").show();
			$("#validityDate").attr("req","true");
			
			$("#validityDays").val("");
			$("#validityDays_li").hide();
			$("#validityDays").removeAttr("req");
		} else {
			$("#validityDate").val("");
			$("#validityDays").val("");
			$("#validityDate_li").hide();
			$("#validityDays_li").hide();
			$("#validityDate").removeAttr("req");
			$("#validityDays").removeAttr("req");
		}
	});
	
	$("#selectAll").change(function() {
		selectAll();
	});
  });
  
  //复选框事件  
  //全选、取消全选的事件  
  function selectAll(){  
      if ($("#selectAll").attr("checked")) {  
          $(":checkbox").attr("checked", true);  
      } else {  
          $(":checkbox").attr("checked", false);  
      }  
  }  
  
</script>
</head>
<body>
	<div class="Container">
		<div class="Content fontText">
			<div class="information">
			    <div class="clear"></div>
			    <div class="search_tit">
					<h2 class="fw fleft f14">商品新增</h2>
				</div>
			    <form name="addGoodsForm" id="addGoodsForm" action="addGoods" method="post">
			      <div class="input_cont">
			        <ul>
			        <li>
			            <label class="text_tit">商品编码：</label>
			            <input class="input_text" type="text" name="goodsCode" id="goodsCode" req="true" label="商品编码">&nbsp;<font color="red">*</font>
			          </li>
			          <li>
			            <label class="text_tit">商品名称：</label>
			            <input class="input_text" type="text" name="goodsName" id="goodsName" req="true" label="商品名称">&nbsp;<font color="red">*</font>
			          </li>
			          <li><label class="text_tit">商品类型：</label>
						  <select id="goodsType" name="goodsType" class="input_text" req="true" label="商品类型">
								<c:forEach var="item"
									items="${_textResource.getTextMap('activity_goods_type') }">
									<option ${item.key==param.goodsType?'selected':''}
										value="${item.key}">${item.value}</option>
								</c:forEach>
	                	  </select>&nbsp;<font color="red">*</font>
					  </li>
					  <li>
			            <label class="text_tit">商品简介：</label>
			            <input class="input_text" type="text" name="goodsRemark" id="goodsRemark" req="true" label="商品简介">&nbsp;<font color="red">*</font>
			          </li>
			          <li>
			            <label class="text_tit">商品单价：</label>
			            <input class="input_text" type="text" name="goodsPrice" id="goodsPrice" req="true" label="商品金额">&nbsp;<font color="red">*</font>
			          </li>
			          
			          <li>
			            <label class="text_tit">商品总数：</label>
			            <input class="input_text" type="text" name="totalCount" id="totalCount" req="true" label="商品总数">&nbsp;<font color="red">*</font>
			          </li>
			          
			        </ul>
			      </div>
			      <div class="btn">
					<input type="submit" class="btn_sure fw" value="保存" />
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
