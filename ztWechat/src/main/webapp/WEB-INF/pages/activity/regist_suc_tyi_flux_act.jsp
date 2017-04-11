<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head>
    <%-- <meta http-equiv='refresh' content='3;url=account/card/toBindCard?activityCode=<e:property value="@activityCode"/>'> --%>
    <script type="text/javascript" src="static/js/LM-app.js"></script>
    <title>注册成功</title>
</head>
<body>
 <div id="box" class="bg-white">
    <div class="pr">
        <img src="static/images/regSuccess-1.jpg" class="repeatImg"/>
        <img src="static/images/reg-2.jpg" class="repeatImg"/>
        <img src="static/images/reg-3.jpg" class="repeatImg"/>
    </div>
    <div class="pr mt15">
        <a href='account/card/toBindCard?activityCode=<e:property value="@activityCode"/>'><img src="static/images/btn-tieCard.jpg" class="repeatImg"/></a>
    </div>
</div>
</body>
</html>