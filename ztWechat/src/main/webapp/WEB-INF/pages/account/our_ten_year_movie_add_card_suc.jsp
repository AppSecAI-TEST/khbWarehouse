<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head>
<meta http-equiv='refresh' content='3;url=/lmweChat/<%=request.getAttribute("returnUrl")%>'> 
    <title>绑卡成功</title>
</head>
<body>
<div id="box" class="bg-white">
    <div class="pr ">
        <img src="static/images/tieCardSuccess.jpg" class="repeatImg">
        <div class="layou-04 mt15 mb15">
            <p class="orange font-18">已绑定银行卡</p>
            <div class="bankArea font-white mt15"><label><%=request.getAttribute("BANK_NAME")%></label> <%=request.getAttribute("CARD_NO")%></div>
            <p class="orange font-16 mt15">您已成功设置交易密码，交易密码用于您在平台交易时的操作验证，请牢记！</p>
        </div>
     <img src="static/images/tie-card-06.jpg" class="repeatImg">
    </div>
    <div class="layou-04">

        <div id="progressBar" class="mt50">
            <div class="finish"></div>
        </div>
        <p class="font-16 orange mt20 tc">正在进入绑卡页面</p>
    </div>
</div>
</body>
</html>