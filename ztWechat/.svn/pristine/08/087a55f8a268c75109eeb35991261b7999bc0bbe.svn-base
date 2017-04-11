<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
    <title>懒猫送您10元投资券</title>
</head>
<body>
<div style="background: #f1f1f1;">
    <div class="pr">
        <img src="static/images/activity/give-present-01.jpg" class="repeatImg"/>
        <img src="static/images/activity/give-present-02.jpg" class="repeatImg"/>
    </div>
    <div class="pr">
        <img src="static/images/activity/give-present-03.jpg" class="repeatImg"/>
    </div>
    <div class="pr presentText">
        <p>即日起，懒猫金服为每位用户送上10元<br/>投资券一张，人人都可领取哦！</p>
    </div>
    <div class="pr">
        <img src="static/images/activity/give-present-04.jpg" class="repeatImg"/>
        
        <e:if test="@loginFlag == 'noLogin'">
          <a class="presentBtn block pa" href="account/toLogin?returnUrl=activity/appMarketingPromotion?productId=<e:property value="@productId"/>"></a>
        </e:if>
        <e:else>
          <a class="presentBtn block pa" href="activity/appMarketingPromotion?productId=<e:property value="@productId"/>"></a>
        </e:else>
    </div>
    <div class="pr">
        <img src="static/images/activity/give-present-05.jpg" class="repeatImg"/>
        <img src="static/images/activity/give-present-06.jpg" class="repeatImg"/>
        <img src="static/images/activity/give-present-07.jpg" class="repeatImg"/>
    </div>
</div>
</body>
</html>