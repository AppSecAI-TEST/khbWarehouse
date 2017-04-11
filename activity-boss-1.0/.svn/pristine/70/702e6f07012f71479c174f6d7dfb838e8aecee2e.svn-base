<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>批量发放红包</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<%-- <script src="${resourceUrl}/js/product.js"></script> --%>
<script type="text/javascript">

function submitForm(){
	var addRedeemCodeForm = $("#addRedeemCodeForm");
	var uploadFile = $("#uploadFile").val();
 	if(!uploadFile){
       alert("上传文件不能为空");
       return;
	} 
  var relaGoodsIdFlag = $("[name='goods']:checked").val();
	if(relaGoodsIdFlag==undefined){
	  alert("必须选择一个商品");
    return;
	}
	var flag = $("#flag").val();
	if(flag=='true'){
	   	$("#flag").val(false);
	    document.getElementById('submitbtn').disabled=true //不可用
	    addRedeemCodeForm.submit();
	}else{
		alert("请稍后，正在处理");
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
					<h2 class="fw fleft f14">批量导入兑换码</h2>
				</div>
			    <form name="addRedeemCodeForm" id="addRedeemCodeForm" action="addRedeemCodeList" method="post" enctype="multipart/form-data" theme="simple">
			      <div class="input_cont">
			        <ul>
                      <li>
                        <label class="text_tit">文件选择：</label> 
                        <input type="file" class="input_text" id="uploadFile" name="uploadFile"/>&nbsp;<font color="red">* 只支持xls,xlsx</font>
   						<input type="hidden" id="flag" value="true"/>		 
                      </li>
                      <li>
                         <!-- 商品 -->
                       <label class="text_tit">商品选择：</label> 
                  <c:if test="${empty activityGoodsDTOList }">
                    <table border="1" id="goods" >
                      <tr><td>无相应商品</td></tr>
                    </table>
                  </c:if>
                  <c:if test="${!empty activityGoodsDTOList }">
                    <table border="1" id="goods" >
                      <tr><td></td><td>名称</td><td>剩余数量</td></tr>
                      <c:forEach var="goodsDto" items="${activityGoodsDTOList }" >
                        <tr>
                          <td><input name="goods" type="radio" value="${goodsDto.id }" class="radioChecked"/></td><td>${goodsDto.goodsName }</td>
                          <td>${goodsDto.totalCount- goodsDto.grantCount}</td>
                        </tr>
                        </c:forEach>
                    </table>
                  </c:if>
                </li>
                    </ul>
                  </div>
                  <div class="btn">
                      <input type="button" id="submitbtn" value="上 传" class="btn_sure fw" onclick="submitForm();" /> 
                      <input type="reset" value="重 置" class="btn_cancel fw" />
                  </div>
				  <div class="clearer"></div>
			    </form>
			  </div>
			<br />
		</div>
	</div>
</body>
</html>
