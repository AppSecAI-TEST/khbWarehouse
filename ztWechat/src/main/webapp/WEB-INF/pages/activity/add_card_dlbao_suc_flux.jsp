<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head>
<title>绑卡成功</title>
</head>
<body>
<div id="box" class="bg-white">
    <div class="pr ">
        <img src="static/images/tieCardSuccess-1.jpg" class="repeatImg"/>
        <img src="static/images/tie-card-04.jpg" class="repeatImg"/>
    </div>
    <div class="pr mt15">
        <a href='fixed/toPurchaseProduct?productId=<e:property value="@FRESHMANID"/>'><img src="static/images/btn-beginner.jpg" class="repeatImg"/></a>
    </div>
    <div class="pr mt20">
        <img src="static/images/tie-card-05.jpg" class="repeatImg"/>
    </div>
    <div class="pr mt15">
        <a href="popularize/toPopularize"><img src="static/images/btn-more.jpg" class="repeatImg"/></a>
    </div>
</div>
</body>
</html>