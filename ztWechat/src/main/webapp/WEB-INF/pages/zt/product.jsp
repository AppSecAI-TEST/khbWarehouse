<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yeepay.g3.app.lmweChat.utils.LmConstants" %>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String sysVersion = LmConstants.sysVersion;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="static/css/LM-invest.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/LM-funds.css?v=<%=sysVersion %>">
    <script type="text/javascript" src="static/js/jquery-1.8.3.min.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/jquery.flexslider-min.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/typed.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/LM-app.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/highstock-min.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/zt/product.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript">
    $(document).ready(function() {
      $("#toSceneList").click(function() {
        location.href="zt/introduce/sceneList"
      })
    });
    </script>
<title>产品宣传</title>
</head>
<body>
<div style="background:#eee;  cursor:pointer;">
    <div class="pr">
        <img src="static/images/invest/product-promotion-1.jpg" class="repeatImg"/>
        <img src="static/images/invest/product-promotion-2.jpg" class="repeatImg"/>
    </div>
    <div class="pr">
        <img src="static/images/invest/product-promotion-3.jpg" class="repeatImg"/>
        <img src="static/images/invest/product-promotion-4.jpg" class="repeatImg"/>
    </div>
    <div class="pr">
        <img src="static/images/invest/product-promotion-5.jpg" class="repeatImg"/>
        <img src="static/images/invest/product-promotion-6.jpg" class="repeatImg"/>
    </div>
    <div class="pr">
        <img src="static/images/invest/product-promotion-7.jpg" class="repeatImg"/>
    </div>
    <div class="promotion">
        <div id="curve" style=" height: 240px;"></div>
    </div>
    <div class="pr">
        <img src="static/images/invest/product-promotion-8.jpg" class="repeatImg"/>

    </div>
    <div class="promotionArea">
        <img src="static/images/invest/product-promotion-9.jpg" class="repeatImg"/>
    </div>
    <div class="pr">
        <a href="account/toInsurance"><img src="static/images/invest/product-promotion-10.jpg" class="repeatImg"/></a>
    </div>
    <div class="h1-2"></div>
    <div class="btnBot" id="toSceneList">
    <input type="button" value="开启财富之旅" class="btnBuy"/>
    </div>
</div>

</body>
</html>