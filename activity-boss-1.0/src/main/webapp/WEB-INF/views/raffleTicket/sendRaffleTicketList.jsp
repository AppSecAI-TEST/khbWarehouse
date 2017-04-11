<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
<title>批量发放抽奖券</title>
<%@ include file="/WEB-INF/views/common/metas.jsp"%>
<%-- <script src="${resourceUrl}/js/product.js"></script> --%>
<script type="text/javascript">
  
  function submitBox() {
    var activityCode= $("#activityCode").val();
    var actionCode= $("#actionCode").val();
    var raffleTicketId= $("input:radio[name=raffleTicketId]:checked").val();
    var title=$("input:radio[name=raffleTicketId]:checked").attr('title');
    var version= $("input:radio[name=raffleTicketId]:checked").siblings('[title='+title+']').eq(0).val();
    var grantCount= $("input:radio[name=raffleTicketId]:checked").siblings('[title='+title+']').eq(1).val();
    var memberNoList= $("#memberNoList").val();
    var num= $("#num").val();
 /*    if(activityCode == null || activityCode.trim() == ""){
  	  MessageBoxExt.alert("请输入活动编码");
      return false;
    }
    if(actionCode == null || actionCode.trim() == ""){
  	  MessageBoxExt.alert("请输入事件编码");
      return false;
    }
    if(raffleTicketId == null || raffleTicketId.trim() == ""){
  	  MessageBoxExt.alert("请选择抽奖券");
      return false;
    }
    
    if(memberNoList == null || memberNoList.trim() == ""){
  	  MessageBoxExt.alert("请输入用户集合");
      return false;
    }
    if(num == null || num.trim() == ""){
  	  MessageBoxExt.alert("请输入每人发放数量");
      return false;
    } */
    MessageBoxExt.ajax({
      url:GV.ctxPath + "raffle/sendRaffleTicketList",
      type:"post",
      data:{"activityCode" : activityCode,
      	"actionCode" : actionCode,
      	"raffleTicketId" : raffleTicketId,
      	"version":version,
      	"grantCount":grantCount,
      	"memberNoList" : memberNoList,
      	"num":num
      },
      style:"none",
      success:function(result, txtStatus){
        if(result=='NOACTIVITY'){
          MessageBoxExt.alert("活动编码或事件编码错误");
         // alert("活动编码或事件编码错误");
        }else if(result=='NOMEMBER'){
          MessageBoxExt.alert("用户列表有错误");
        }else if(result=='SYSTEM'){
          MessageBoxExt.alert("系统异常");
        }else{
          location.href="queryRaffleTicketList";
        }
      }
    });
  }
/* $("#sendRaffleTicketListForm").submit(function(){
  return $(this).validateSubmit({
    activityCode: {
          req: true,
          label: "活动编码"
      },
      actionCode: {
        req: true,
        label: "事件编码"
    },
    raffleTicketId: {
      req: true,
      label: "抽奖券"
  },
  memberNoList: {
    req: true,
    label: "用户集合"
},
num: {
  req: true,
  label: "每人发送数量"
}
  });
}); */
</script>
</head>
<body>
	<div class="Container">
		<div class="Content fontText">
			<div class="information">
			    <div class="clear"></div>
			    <div class="search_tit">
					<h2 class="fw fleft f14">批量发放抽奖券</h2>
				</div>
			    <form name="sendRaffleTicketListForm" id="sendRaffleTicketListForm" method="post">
			      <div class="input_cont">
			        <ul>
			        <li>
			            <label class="text_tit">活动编码：</label>
			            <input class="input_text" type="text" name="activityCode" id="activityCode">&nbsp;<font color="red">*</font>
			          </li>
			          <li>
			            <label class="text_tit">事件编码：</label>
			            <input class="input_text" type="text" name="actionCode" id="actionCode" >&nbsp;<font color="red">*</font>
			          </li>
			          <li>
			            <label class="text_tit">抽奖券列表：</label>
                   <c:forEach items="${result}" var="item" varStatus="num">
			            		<c:if test="${num.index%3==0}">
			            			<tr>
			            		</c:if>
			            				<td>
				            				<input name="raffleTicketId" type="radio" value="${item.id }" title="${item.id }"/>${item.raffleTicketName }&nbsp;
                                            <input name="version" type="hidden" class='version' title="${item.id }" value="${item.version}" >
                                             <input name="grantCount" type="hidden" class='grantCount' title="${item.id }" value="${item.grantCount==null?0:item.grantCount}" >
				            			</td>
		            			<c:if test="${num.index%2==0&&num.index%3==0&&num.index!=0}">  
								 	</tr>
								 </c:if>
			            	</c:forEach>&nbsp;<font color="red">*</font>
			          </li>
                 <li>
                        <label class="text_tit">发放用户：</label> 
                        <textarea rows="10" cols="70" id="memberNoList" name="memberNoList"></textarea>&nbsp;<font color="red">*</font>
                      </li>
			           <li>
                  <label class="text_tit">每人发送数量：</label>
                  <input class="input_text" type="text" name="num" id="num" >&nbsp;<font color="red">*</font>
                     </li>
			        </ul>
			      </div>
			      <div class="btn">
					<input type="button" class="btn_sure fw" value="保存" onclick="submitBox();" />
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
