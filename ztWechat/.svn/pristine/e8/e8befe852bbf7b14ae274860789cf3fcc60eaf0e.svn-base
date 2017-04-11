<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
    <title>转入成功</title>
</head>
<body>
<div id="box" class="bg-white">
    <div class="pr ">
        <img src="static/images/rool-inSuccess.jpg" class="repeatImg">
        <div class="flowList pr">
            <span class="bg-t pa"></span>
            <span class="bg-b pa"></span>
            <ul class="">
                <li class="on"><i class="icon icon-circle"></i> <e:property value="@thisDay"/> 今天 成功买入生财宝<e:property value="@totalBalance"/> 元</li>
                <li><i class="icon icon-circle"></i> <e:property value="@firstIncomeDay"/> 计算收益</li>
                <li><i class="icon icon-circle"></i> <e:property value="@incomingDay"/> 收益到账</li>
            </ul>
        </div>
        <%-- <e:if test="${empty openId }">
        <img src="static/images/QR-code.jpg" class="repeatImg">
        </e:if> --%>
        
    </div>
    <e:if test="${empty alreadyConcerned && empty app }">
      <img src="static/images/QR-code.jpg" class="repeatImg">
    </e:if>
    <%-- <e:if test="@source != '0001' && source != 'APP00A001' && source != 'APP00S001'">
      <img src="static/images/QR-code.jpg" class="repeatImg">
    </e:if> --%>
    <e:else>
      <div class="btnMaskArea tc mt15">
        <a href="asset/toAsset">查看我的财富</a>
        <a href="scb/toScb">购买理财</a>
      </div>
    </e:else>
    <%-- <e:if test="${!empty openId }">
    <div class="btnMaskArea tc mt15">
        <a href="asset/toAsset">查看我的财富</a>
        <a href="scb/toScb">购买理财</a>
    </div>
    </e:if> --%>
</div>
</body>
</html>