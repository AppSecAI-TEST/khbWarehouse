<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>批量发放优惠券</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<%-- <script src="${resourceUrl}/js/product.js"></script> --%>
<script type="text/javascript">
  $(function () {
	DatePickerExt.date("validityDate");
  });
  //取消
  cancel = function(){
    var action = "batchGrant/queryBatchGrantList";
 	 window.location.href = GV.ctxPath + action;
  }
  //提交审核前验证
  submitBox = function(){
    //优惠券标志
    var couponFlag = false;
    //判断是否选择了优惠券
    var couponId = document.getElementsByName("couponId");
    for(var i=0;i<couponId.length;i++){
      if(couponId[i].checked){
        couponFlag = true;
      }
    }
    if(couponFlag == false){
  	  MessageBoxExt.alert("请选择要发放的优惠券");
      return false;
    }
    //用户列表
    var memberNoList = $("#memberNoList").val();
    var perGrantCount = $("#perGrantCount").val();
    if(memberNoList == null || memberNoList.trim() == ""){
  	  MessageBoxExt.alert("请输入用户编号列表");
      return false;
    }
    //每人发放数量
    if(perGrantCount == null || perGrantCount.trim() == ""){
  	  MessageBoxExt.alert("请输入每人发放数量");
      return false;
    }
    //批量发放名称
    var batchGrantName = $("#batchGrantName").val();
    var memberNoList = $("#memberNoList").val();
    var perGrantCount = $("#perGrantCount").val();
    var couponId = $('input:radio[name=couponId]:checked').val();
    if(batchGrantName == null || batchGrantName.trim() == ""){
    	MessageBoxExt.alert("请输入批量发放名称");
      return false;
    }else{
      var action = "verifyBatchGrantName";
      //判断名称唯一性
      MessageBoxExt.ajax({
        url:GV.ctxPath + "batchGrant/" + action,
        type:"post",
        data:{"batchGrantName" : batchGrantName,
        	"memberNoList" : memberNoList,
        	"perGrantCount" : perGrantCount,
        	"couponId" : couponId,
        },
        success:function(result, txtStatus){
        	$("#addGrantCouponForm").submit();
        }
      });
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
					<h2 class="fw fleft f14">批量发放优惠券</h2>
				</div>
			    <form name="addGrantCouponForm" id="addGrantCouponForm" action="addGrantRecord" method="post">
			      <div class="input_cont">
			        <ul>
                      <li>
                        <label class="text_tit">批量发放名称：</label> 
                        <input class="input_text" id="batchGrantName" name="batchGrantName"/>&nbsp;<font color="red">*</font>
                      </li>
                      <li>
                        <label class="text_tit">发放优惠券：</label> 
                        <table>
			            	<c:forEach items="${acDtos}" var="acDto" varStatus="num">
			            		<c:if test="${num.index%3==0}">
			            			<tr>
			            		</c:if>
			            				<td>
				            				<input name="couponId" type="radio" value="${acDto.id }"/>${acDto.couponName }
				            			</td>
		            			<c:if test="${num.index%2==0&&num.index%3==0&&num.index!=0}">  
								 	</tr>
								 </c:if>
			            	</c:forEach>
			            </table>
<%--                         <c:forEach items="${acDtos }" var="acDto"> --%>
<%--                             <input type="radio" id="${acDto.id }" name="couponId"  value="${acDto.id }"/><c:out value="${acDto.couponName}"></c:out><br/> --%>
<%--                         </c:forEach> --%>
                      </li>
                      <li>
                        <label class="text_tit">发放用户：</label> 
                        <textarea rows="10" cols="70" id="memberNoList" name="memberNoList"></textarea>&nbsp;<font color="red">*</font>
                      </li>
                      <li>
                        <label class="text_tit">每人发放数量：</label> 
                        <input class="input_text" id="perGrantCount" name="perGrantCount"/>&nbsp;<font color="red">*</font>
                      </li>
                    </ul>
                  </div>
                  <div class="btn">
                      <input type="button" value="提交审核" class="btn_sure fw" onclick="submitBox();" /> 
                      <input type="button" value="取消" class="btn_cancel fw" onclick="cancel();" />
                  </div>
				  <div class="clearer"></div>
			    </form>
			  </div>
			<br />
		</div>
	</div>
</body>
</html>
