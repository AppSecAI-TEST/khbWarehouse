<div class="activityList radius1 bg-white pr">
 <a href="activity/toRafflePrizeList?messageId=${messageId?c}&version=${versions?c}" class=" br-top">
	  <i class="${readStatusClass}"></i>
     <h2 class="newsTitle orange">中奖通知</h2>
     <p class="font-12">${createTime}</p>
     <p class="font-12 mt15">恭喜您获取奖品${prizeName}</p>
     <p class="font-12 mt15 mb15">
        活动：${activityName}<br/>
        奖品：${prizeName}
     </p>
    <a href="activity/toRafflePrizeList?messageId=${messageId?c}&version=${versions?c}" class="A-nav br-top">
         <i class="icon icon-arrow-right fr"></i>
         <i class="orange">立即查看</i>
         </a>
     </a>
</div>