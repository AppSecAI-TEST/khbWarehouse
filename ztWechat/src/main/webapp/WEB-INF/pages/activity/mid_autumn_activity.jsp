<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
    <title>懒猫与您庆中秋</title>
</head>
<body>
<script type="text/javascript">
function submit(){
  var productId = $("#productId").val();
  location.href="fixed/toPurchaseProduct?productId="+productId;
}
</script>
<input id="productId" type="hidden" value="<e:property value="@productDetailResultDto.productId"/>"/>
<div class="mid-autumn">
    <div class="pr">
        <img src="static/images/activity/mid-autumn-01.jpg" class="repeatImg"/>
        <img src="static/images/activity/mid-autumn-02.jpg" class="repeatImg"/>
    </div>
    <div class="pr">
        <img src="static/images/activity/mid-autumn-03.jpg" class="repeatImg"/>
        <!-- 有产品售卖显示立即抢购 -->
        <e:if test="@productDetailResultDto != null">
            <a class="mid-autumn-btn block pa" onclick="submit();"><img src="static/images/activity/mid-autumn-btn1.png" class="repeatImg"/></a>
        </e:if>
        <!-- 活动未开始或已售完 -->
        <e:else>
            <!-- 活动未开始，显示敬请期待 -->
            <e:if test="@flag == false">
                <a class="mid-autumn-btn block pa"><img src="static/images/activity/mid-autumn-btn2.png" class="repeatImg"/></a>
            </e:if>
            <!-- 已抢完 -->
            <e:else>
                <a class="mid-autumn-btn block pa"><img src="static/images/activity/mid-autumn-btn3.png" class="repeatImg"/></a>
            </e:else>
            <!-- 为0时表示活动已经结束 -->
            <e:if test="@showDay != 0">
                <div class="mid-autumn-text tc font-white pa"><e:if test="@showSeason == 0">下一场</e:if>开抢时间9月<e:property value="@showDay"/>日  14：00</div>
            </e:if>
        </e:else>
        
    </div>
    <div class="pr">
        <img src="static/images/activity/mid-autumn-04.jpg" class="repeatImg"/>
    </div>
    <div class="pr">
        <img src="static/images/activity/mid-autumn-05.jpg" class="repeatImg"/>
        <div class="activityRule pa">
            <h2>活动规则：</h2>
            <ul>
                <li><span>1、</span>"团圆盈" 产品总额度有限，售完为止；</li>
                <li><span>2、</span>抢购"团圆盈" 产品时，不能使用加息券、投资券等其他类型的懒猫金服优惠券；</li>
                <li><span>3、</span>本次活动最终解释权归北京懒猫金融信息服务有限公司所有。</li>
            </ul>
        </div>
    </div>
    <div class="pr">
        <img src="static/images/activity/mid-autumn-06.jpg" class="repeatImg"/>
        <img src="static/images/activity/mid-autumn-07.jpg" class="repeatImg"/>
    </div>
</div>
</body>
</html>