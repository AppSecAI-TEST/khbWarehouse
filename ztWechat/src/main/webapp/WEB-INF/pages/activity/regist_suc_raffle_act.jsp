<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head>
    <meta http-equiv='refresh' content='3;url=account/card/toBindCard?activityCode=<e:property value="@activityCode"/>'>
    <script type="text/javascript" src="static/js/LM-app.js"></script>
    <title>注册成功</title>
</head>
<body>
 <div id="box" class="bg-white">
    <div class="pr ">
        <img src="static/images/regSuccess.jpg" class="repeatImg">
        <img src="static/images/tieCard-1.jpg" class="repeatImg">
    </div>
    <div class="layou-04">
        <div id="progressBar" class="mt50">
            <div class="finish"></div>
        </div>
        <p class="font-16 orange mt20 tc">正在进入绑卡页面</p>
        <e:if test="${returnFlag == '' }">
        <p class="mt15">
            <a href="popularize/toPopularize" class="btnUncard orange fr">暂不绑卡</a>
        </p>
        </e:if>
    </div>

</div>
</body>
</html>