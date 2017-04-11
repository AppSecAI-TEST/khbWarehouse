<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
    <title>领取成功</title>
</head>
<body>
<div id="box" style="background: #e66929">
    <div class="pr">
        <img src="static/images/activity/receive-01.jpg" class="repeatImg"/>
    </div>
    <div class="pr">
        <img src="static/images/activity/receive-02.jpg" class="repeatImg"/>
        <e:if test="@type==true">
         <p class="receiveText tc pa">领取成功！10元投资券已放入您的卡券中</p>
        </e:if><e:else>
         <p class="receiveText tc pa">您已经领取过10元投资券了，不能重复领取哦</p>
        </e:else>
        <div class="receiveBtn tc pa"><a href="fixed/toMyCoupons">&nbsp;</a> <a href="fixed/toPurchaseProduct?productId=<e:property value="@productId"/>">&nbsp;</a></div>
    </div>
</div>
</body>
</html>