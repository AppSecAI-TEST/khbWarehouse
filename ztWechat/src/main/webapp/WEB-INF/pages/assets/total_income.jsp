<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
    <title>累计收益</title>
     <link rel="stylesheet" href="static/css/LM-funds.css">
</head>
<body>
<div id="box" class="scb">
    <div class="myassetCard font-white tc pr">
     <a href="asset/toMyIncome?type=total" class="font-white">
        <p><span class="font-tit-wealth"><e:property value="@_formater.formatNumber(totalIncome)"/></span></p>
        <p class="font-tit-sm pt30">累计总收益（元）</p>
        <i class="icon-arrow triangle-right pa"></i></a>
    </div>
    <div class="listArea bg-white">
        <ul>
            <li>
                <a href="asset/toMyIncome?type=fund" >
                <i class="icon icon-arrow-right fr"></i>
                基金理财累计收益：<e:property value="@_formater.formatNumber(fundSumIncome)"/>元</a>
            </li>
            <li>
                <a href="asset/toMyIncome?type=fixed" >
                <i class="icon icon-arrow-right fr"></i>
                定期理财累计收益：<e:property value="@_formater.formatNumber(xtTotalIncome)"/>元</a>
            </li>
            <li>
                <a href="asset/toMyIncome?type=scb" >
                <i class="icon icon-arrow-right fr"></i>
                活期理财累计收益：<e:property value="@_formater.formatNumber(sumAmountScb)"/>元</a>
            </li>
        </ul>
    </div>
</div>
</body>
</html>