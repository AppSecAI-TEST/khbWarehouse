<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>修改活动事件</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<script type="text/javascript">
  $(function () {
	var conList = $("#conList").val();
	var con = conList.split(";");
	var obj = document.getElementsByName("coupons");
	for(var i = 0; i < con.length; i ++){
	  for(var j = 0; j < obj.length; j ++){
	    if(con[i] == obj[j].value){
	      obj[j].checked = true;
	    }
	  }
	}
    $("#updateEventForm").submit(function(){
          return $(this).validateSubmit({
        	  eventCode: {
                  req: true,
                  label: "事件编码"
              },
              eventName: {
                  req: true,
                  label: "事件名称"
              },
              coupons: {
                  req: {
                      min: 1
                  },
                  label: "发放优惠券"
              }
          });
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
					<h2 class="fw fleft f14">活动事件修改</h2>
				</div>
			    <form name="updateEventForm" id="updateEventForm" action="updateEvent" method="post">
			      <div class="input_cont">
			        <ul>
                      <li>
                        <input type="hidden" id="id" name="id" value="${eventDto.id }"/>
                      </li>
                      <li>
                        <input type="hidden" id="conList" name="conList" value="${conList }"/>
                      </li>
			          <li>
			            <label class="text_tit">事件编码：</label>
			            <input class="input_text" type="text" name="eventCode" id="eventCode" value="${eventDto.eventCode }" readonly="readonly">&nbsp;<font color="red">*</font>
			          </li>
			          <li>
			            <label class="text_tit">事件名称：</label>
			            <input class="input_text" type="text" name="eventName" id="eventName" value="${eventDto.eventName }">&nbsp;<font color="red">*</font>
			          </li>
			          <li>
			            <label class="text_tit">发放优惠券：</label>
			            <table>
                          
			            	<c:forEach items="${couponList}" var="coupon" varStatus="num">
			            		<c:if test="${num.index%3==0}">
			            			<tr>
			            		</c:if>
		            				<td>
			            				<input name="coupons" type="checkbox"  value="${coupon.id }"/>${coupon.couponName }
                                    </td>
		            			<c:if test="${num.index%2==0&&num.index%3==0&&num.index!=0}">  
								 	</tr>
								 </c:if>
			            	</c:forEach>
                          
			            </table>
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
