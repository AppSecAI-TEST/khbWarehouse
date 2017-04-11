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
    $(document).ready(function(){
      if($("#checkedIds").val().length > 0){
        var arry = $("#checkedIds").val().split("#");
        var ruleList = $(".ruleId");
        for(j = 0;j<ruleList.length;j++) {
          for(k=0;k<arry.length;k++) {
            if($(ruleList[j]).val() == arry[k]) {
              $(ruleList[j]).attr("checked",true);
            }
          }
        }
      }
    });
    
    
    
 	  $("#modifyGoods").submit(function(){
           return $(this).validateSubmit();
       });

	  DatePickerExt.date("validityDate");
	
	$("#selectAll").change(function() {
		selectAll();
	});
	
	//根据投资换产品的类型，初始化虚拟或者是实物的状态
	$(document).ready(function() {
	  var goodsOfType = $("#goodsOfType").val();
	  $("#goodsType").val(goodsOfType);
	});
	
	$(".ruleId").click(function(){
	  var $check = $(".ruleId");
	  var str="";
	  for(i=0;i<$check.length;i++) {
	    if($($check[i]).is(":checked")){
	      str = str+$($check[i]).val() + "#";
	    }
	  }
	  str = str.substring(0, str.length-1);
      $("#ruleIdStr").val(str);
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
					<h2 class="fw fleft f14">修改投资换产品</h2>
				</div>
			    <form name="modifyInvForProInfo" id="modifyInvForProInfo" action="modifyInvForProInfo" method="post" enctype="multipart/form-data">
			      <div class="input_cont">
			        <ul>
			          <li style="display:none">
			            <input  value="${invForProInfoDto.id }" name="id" id="id"  label="主键">
			            <input  value="${invForProInfoDto.version }" name="version" id="version"  label="版本号">
			          </li>
			          <li>
			            <label class="text_tit">活动编码：</label>
			            <input class="input_text" type="text" value="${invForProInfoDto.activityCode }" name="activityCode" id="activityCode" req="true" label="投资换产品编码">&nbsp;<font color="red">*</font>
			          </li>
			          <li>
			            <label class="text_tit">投资换产品名称：</label>
			            <input class="input_text" type="text" value="${invForProInfoDto.name }" name="name" id="name" req="true" label="投资换产品名称">&nbsp;<font color="red">*</font>
			          </li>
			           <li>
			            <label class="text_tit">价格：</label>
			            <input class="input_text" type="text" value="${invForProInfoDto.price }" name="price" id="price" req="true" label="价格">&nbsp;<font color="red">*</font>
			          </li>
			           <li>
			            <label class="text_tit">url：</label>
			            <input class="input_text" type="text" value="${invForProInfoDto.url }" name="url" id="url" req="true" label="url">&nbsp;<font color="red">*</font>
			          </li>
			           <li>
			            <label class="text_tit">最低购买理财价格：</label>
			            <input class="input_text" type="text" value="${invForProInfoDto.productPriceLow }" name="productPriceLow" id="productPriceLow" req="true" label="最低购买理财价格">&nbsp;<font color="red">*</font>
			          </li>
			          <li>
			            <label class="text_tit">收益率：</label>
			            <input class="input_text" type="text" value="${invForProInfoDto.rate }" name="rate" id="rate" req="true" label="收益率">&nbsp;<font color="red">*</font>
			          </li>
			          <li>
			            <label class="text_tit">投资换产品数量：</label>
			            <input class="input_text" type="text" name="stockNum" value="${invForProInfoDto.stockNum }" id="stockNum" req="true" label="投资换产品数量">&nbsp;<font color="red">*</font>
			          </li>
                      <li>
                        <label class="text_tit">投资换产品已用数量：</label>
                        <input class="input_text" type="text" name="usedNum" value="${invForProInfoDto.usedNum }" id="usedNum" req="true" label="投资换产品已用数量">&nbsp;<font color="red">*</font>
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
				            	<input name="ruleIdStr" id="ruleIdStr" value="${checkedIds }" style="display:none" />
				            	<input id="checkedIds" value="${checkedIds }" style="display:none" />
				            </table>
				          </li>
			          <li><label class="text_tit">微信活动图片：</label>
                          <img alt="" src="lookInvForProImg?id=${invForProInfoDto.id }&type=WX">
                       </li>
                       <li>
	                        <label class="text_tit">微信修改活动图片：</label>
	                         <input class="input_text" type="file" name="activityImgForWX"  accept="image/*"> &nbsp;<font color="red">图片上传不能超过1M</font>
	                      </li>
                        <li><label class="text_tit">PC活动图片：</label>
                          <img alt="" src="lookInvForProImg?id=${invForProInfoDto.id }&type=PC">
                       </li>
                       <li>
                          <label class="text_tit">PC修改活动图片：</label>
                           <input class="input_text" type="file" name="activityImgForPC"  accept="image/*"> &nbsp;<font color="red">图片上传不能超过1M</font>
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
