<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head>
<meta http-equiv='refresh' content='3;url=/lmweChat/<e:property value="@returnUrl"/>'> 
<title>绑卡成功</title>
</head>
<body>
<div id="box" class="bg-white">
    <div class="pr ">
        <img src="static/images/tieCardSuccess.jpg" class="repeatImg">
        <div class="layou-04 mt15 mb15">
            <p class="orange font-18">已绑定银行卡</p>
            <div class="bankArea font-white mt15"><label><e:property value="@BANK_NAME"/></label> <e:property value="@CARD_NO"/></div>
        </div>
        <img src="static/images/vouchers.jpg" class="repeatImg">
    </div>
    <div class="layou-04">

        <div id="progressBar" class="mt50">
            <div class="finish"></div>
        </div>
        <p class="font-16 orange mt20 tc">正在跳转...</p>
    </div>
</div>
</body>
</html>