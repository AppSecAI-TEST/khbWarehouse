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
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="static/css/LM-invest.css?v=<%=sysVersion %>">
    <title>费率明细</title>
</head>
<body>
<div class="scb">
    <div class="layout-wrap scbCard rateCard font-white tc">
        <div class="layou-up assetArea pr">
            <a class="font-white pr">
                <span class="font-45"><e:property value="@totalFee"/></span>
                <p class="font-14">预估手续费（元）</p>
                <p class="font-text-note">该数值仅为预估值，实际费用请以基金公司为准</p>
            </a>
        </div>
    </div>
    <e:if test="${!empty feeList}">
    <e:iterator list="@feeList" var="items">
    <div class="myInvest groupList bg-white radius1">
        <h3 class="investTitle"><i><e:property value="@items.fundName"/>(<e:property value="@items.fundCode"/>)</i></h3>
        <div class="rateSum br-bottom">
            申购金额：<i class="orange"><e:property value="@_formater.formatNumber(items.purAmount)"/></i>元
            <span class="fr">预估手续费：<i class="orange"><e:property value="@items.realFareSx"/></i>元</span>
        </div>
        <div class="rateCon">
            <h3>申购费率</h3>
             <e:if test="@items.rateDetailList!=null">
            <div class="rateTable light-gray tc radius1">
                <table cellpadding="0" cellspacing="0" width="100%">
                    <tbody>
                    <tr>
                        <th>申购金额(RMB)</th>
                        <th>费率</th>
                    </tr>
            <e:iterator list="@items.rateDetailList" var="item">
                    <tr>
                        <td><e:property value="@item.rangeDesciption"/></td>
                         <e:if test="@item.rate<=99">
                        <td><span class="font-text-note line-through"><e:property value="@_formater.formatNumber(item.rate)"/>%    </span><e:property value="@_formater.formatNumber(item.discountRate)"/>%</td>
                        </e:if>
                        <e:if test="@item.rate>99">
                        <td><span class="font-text-note line-through"><e:property value="@_formater.formatNumber(item.rate)"/>元    </span><e:property value="@_formater.formatNumber(item.discountRate)"/>元</td>
                        </e:if>
                    </tr>
                    </e:iterator>
                    </tbody>
                </table>
            </div>
            </e:if>
        </div>
    </div>
    </e:iterator>
    </e:if>
</div>
<div class="rateTips light-gray tc">基金费率由基金公司提供，请以基金公司官网公布的数据为准</div>
</body>
</html>