<div class="activityList radius1 bg-white pr">
<a href="activity/toRaffleActivity?messageId=${messageId?c}&version=${versions?c}" class=" br-top">
     <i class="${readStatusClass}"></i>
     <h2 class="newsTitle orange">获得${score}分幸运值</h2>
     <p class="font-12">${createTime}</p>
     <p class="font-12 mt15">您刚刚通过<i class="orange">${wxNickName!}</i>分享的链接注册并绑卡成为我们懒猫会员，获得了${score}分幸运值，幸运值越高获得高级奖品的几率越大！</p>
     <p class="font-12 mt15 mb15">
        绑卡时间：${occurTime}<br/>
        获得奖励：${score}分幸运值
     </p>
     <a href="activity/toRaffleActivity?messageId=${messageId?c}&version=${versions?c}" class="A-nav br-top">
         <i class="icon icon-arrow-right fr"></i>
         <i class="orange">立即查看</i>
     </a>
      </a>
</div>