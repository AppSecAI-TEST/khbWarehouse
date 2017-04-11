<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>新增投资换产品</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<%-- <script src="${resourceUrl}/js/product.js"></script> --%>
<script type="text/javascript">

  $(function () {
	// From数据校验
// 	  var valAllList = {
// 		  couponName: {
// 	      req: true,
// 	      label: "投资换产品名称"
// 	    }
// 	  };
// 	  ValidateExt.listen("addInvForProForm",valAllList);
	  
	  $("#addInvForProForm").submit(function(){
          return $(this).validateSubmit({});
      });

	  DatePickerExt.date("validityDate");

$(".ruleId").click(function(){
	  var $check = $(".ruleId");
	  var str="";
	  for(i=0;i<$check.length;i++) {
	    if($($check[i]).is(":checked")){
	      str = str+$($check[i]).val() + "#";
	    }
	  }
	  str = str.substring(0, str.length-1);
	  if(str.length>0) {
	    $("#ruleIdStr").val(str);
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
					<h2 class="fw fleft f14">投资换产品新增</h2>
				</div>
			    <form name="addInvForProForm" id="addInvForProForm" action="addInvForPro" method="post" enctype="multipart/form-data">
			      <div class="input_cont">
			        <ul>
				        <li>
				            <label class="text_tit">产品名称：</label>
				            <input class="input_text" type="text" name="name" id="name" req="true" label="投资换产品名称">&nbsp;<font color="red">*</font>
				          </li>
				        <li>
				            <label class="text_tit">活动编码：</label>
				            <input class="input_text" type="text" name="activityCode" id="activityCode" req="true" label="投资换产品编码">&nbsp;<font color="red">*</font>
				          </li>
			          
				          <li>
				            <label class="text_tit">价格：</label>
				            <input class="input_text" type="text" name="price" id="price" req="true" label="价格">&nbsp;<font color="red">*</font>
				          </li>
						  <li>
				            <label class="text_tit">url：</label>
				            <input class="input_text" type="text" name="url" id="url" req="true" label="URL">&nbsp;<font color="red">*</font>
				          </li>
				          <li>
				            <label class="text_tit">最低购买理财价格：</label>
				            <input class="input_text" type="text" name="productPriceLow" id="productPrizeLow" req="true" label="最低购买理财价格">&nbsp;<font color="red">*</font>
				          </li>
				          <li>
				            <label class="text_tit">收益率：</label>
				            <input class="input_text" type="text" name="rate" id="rate" req="true" label="收益率">&nbsp;<font color="red">*</font>
				          </li>
				          <li>
				            <label class="text_tit">总数：</label>
				            <input class="input_text" type="text" name="stockNum" id="stockNum" req="true" label="投资换产品总数">&nbsp;<font color="red">*</font>
				          </li>
				          <li>
	                        <label class="text_tit">微信活动图片：</label>
	                         <input class="input_text" type="file" name="activityImgForWX"  accept="image/*" > &nbsp;<font color="red">图片上传不能超过1M</font>
	                      </li>
                          <li>
                          <label class="text_tit">PC活动图片：</label>
                           <input class="input_text" type="file" name="activityImgForPC"  accept="image/*"> &nbsp;<font color="red">图片上传不能超过1M</font>
                          </li>
	                      <li>
				            <label class="text_tit">选择规则：</label>
				            <table>
				            	<c:forEach items="${ruleDtoList}" var="ruleDto" varStatus="num">
				            		<c:if test="${num.index%3==0}">
				            			<tr>
				            		</c:if>
				            				<td>
					            				<input class="ruleId" type="checkbox" value="${ruleDto.id }"/>
	                                                                                                                        规则序号：${ruleDto.id }; 规则名称： ${ruleDto.ruleName } <span style="width:30px"></span>
					            			</td>
			            			<c:if test="${num.index==2||num.index%3==2}">  
									 	</tr>
									 </c:if>
				            	</c:forEach>
				            	<input name="ruleIdStr" id="ruleIdStr" value="" style="display:none" />
				            </table>
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
