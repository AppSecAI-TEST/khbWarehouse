<div class="activityList radius1 bg-white pr">
<a href="activity/toRaffleActivity?messageId=${messageId?c}&version=${versions?c}" class=" br-top">
     <i class="${readStatusClass}"></i>
     <h2 class="newsTitle orange">获得${num}次抽奖机会</h2>
     <p class="font-12">${createTime}</p>
     <p class="font-12 mt15">获得了${num}次抽奖机会，快去抽奖吧！</p>
     <p class="font-12 mt15 mb15">
        赠送时间：${occurTime}<br/>
        赠送奖品：${num}次抽奖机会
     </p>
     <a href="activity/toRaffleActivity?messageId=${messageId?c}&version=${versions?c}" class="A-nav br-top">
         <i class="icon icon-arrow-right fr"></i>
         <i class="orange">立即抽奖</i>
     </a>
      </a>
</div>