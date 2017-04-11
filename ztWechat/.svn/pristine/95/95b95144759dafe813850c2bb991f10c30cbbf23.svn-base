<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
    <title>昨日收益</title>
    <link rel="stylesheet" href="static/css/LM-funds.css">
</head>
<body>
<div id="box" class="scb">
    <div class="myassetCard font-white tc pr">
     <a href="asset/toMyIncomeForYesterday?type=total" class="font-white">
        <p><span class="font-tit-wealth"><e:property value="@_formater.formatNumber(incomeTotal)"/></span></p>
        <p class="font-tit-sm pt30">昨日总收益（元）</p>
        <i class="icon-arrow triangle-right pa"></i></a>
    </div>
    <div class="listArea bg-white">
        <ul>
            <li>
                <a href="asset/toMyIncomeForYesterday?type=fund" >
                <i class="icon icon-arrow-right fr"></i>
                基金理财昨日收益：<e:property value="@_formater.formatNumber(fundYestodayResult)"/>元</a>
            </li>
            <li>
                <a href="asset/toMyIncomeForYesterday?type=fixed" >
                <i class="icon icon-arrow-right fr"></i>
                定期理财昨日收益：<e:property value="@_formater.formatNumber(incomeFixed)"/>元</a>
            </li>
            <li>
                <a href="asset/toMyIncomeForYesterday?type=scb" >
                <i class="icon icon-arrow-right fr"></i>
                活期理财昨日收益：<e:property value="@_formater.formatNumber(incomeScb)"/>元</a>
            </li>
        </ul>
    </div>
</div>
</body>
</html>