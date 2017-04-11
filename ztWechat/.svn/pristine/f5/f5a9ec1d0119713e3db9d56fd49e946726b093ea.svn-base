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
<html>
<head lang="en">
    <title>基金申购成功</title>
</head>
<body>
<div id="box" class="bg-white">
    <div class="pr">
        <img src="static/images/fund/applySuccess.jpg" class="repeatImg">
        <div class="layou-04 mb0">
            <div class="br-bottom mt25 pb25 ">
                <p class="font-18"><e:property value="@fundName"/></p>
                <p class="mt5 font-16"><e:property value="@_formater.formatNumber(balance)"/>元</p>
            </div>
        </div>
        <div class="mb15">
            <div class="flowList pr">
                <span class="bg-t pa"></span>
                <span class="bg-b pa"></span>
                <ul class="applyFlow">
                    <li class="on">
                        <i class="icon icon-circle"></i> 申购成功，待基金公司确认
                        <p>预计1-2个工作日</p>
                    </li>
                    <li>
                        <i class="icon icon-circle"></i> 确认份额，计算收益
                        <p>预计1个工作日，各基金公司略有不同</p>
                    </li>
                    <li><i class="icon icon-circle"></i> 查看收益</li>
                </ul>
            </div>
        </div>
    </div>
    <!--<div class="btnMaskArea tc mt15">-->
        <!--<a href="#">查看我的财富</a>-->
        <!--<a href="#">继续投资</a>-->
    <!--</div>-->
    <div class="h1-6"></div>
    <div class="btn-group">
        <input type="button" value="我的灵机一投" class="btn-inout btnClick-1" onclick="location.href='zt/asset/toMyPolicyInvest'" />
        <input type="button" value="查看订单详情" class="btn-inout btn-in btnClick-2" onclick="location.href='zt/asset/toPolicyFundOrderDetail?policyOrderDetailId=<e:property value="@orderDetailId"/>'" />
    </div>
</div>
</body>
</html>