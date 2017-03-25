<div class="activityList radius1 bg-white pr">
 <a onclick="redeemCodeDetail()" class=" br-top">
 	<input id="messageId" type="hidden" value="${messageId?c}"/>
 	<input id="versions" type="hidden" value="${versions?c}"/>
	  <i class="${readStatusClass}"></i>
     <h2 class="newsTitle orange">${goodsName}</h2>
     <p class="font-12">${createTime}</p>
     <p class="font-12 mt15">恭喜您获得${prizeName}一张，兑换码：${redeemCode}</p>
    <a onclick="redeemCodeDetail()" class="A-nav br-top">
         <i class="icon icon-arrow-right fr"></i>
         <i class="orange">立即查看</i>
         </a>
     </a>
</div>

<script type="text/javascript">
	redeemCodeDetail = function(){
		var messageId = document.getElementById("messageId").value;
		var versions = document.getElementById("versions").value;
		$.ajax({
			url:'activity/toRedeemCodeMsg',
		    type:'post',
		    dataType:'json',
		    data:{messageId:messageId,version:versions},
		    success:function(data){
		    	location.href = data.link;
		    }
		      
		})
	}
	
</script>