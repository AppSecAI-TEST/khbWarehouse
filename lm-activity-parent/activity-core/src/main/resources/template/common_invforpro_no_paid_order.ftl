<div class="activityList radius1 bg-white pr">
<a href="invForPro/toOrderList?messageId=${messageId?c}&version=${versions?c}" class=" br-top">
     <i class="${readStatusClass}"></i>
     <h2 class="newsTitle orange">未完成订单</h2>
     <p class="font-12">${createTime}</p>
     <p class="font-12 mt15">你有未完成的购买订单</p>
     <p class="font-12 mt15 mb15">
        购买时间：${occurTime}<br/>
        购买产品：${productName} ${num}份
     </p>
     <a href="invForPro/toOrderList" class="A-nav br-top">
         <i class="icon icon-arrow-right fr"></i>
         <i class="orange">立即查看</i>
     </a>
      </a>
</div>