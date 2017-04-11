<%@page import="com.yeepay.g3.app.lmweChat.utils.LmConstants" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<%
  String sysVersion = LmConstants.sysVersion;
%>
<html>
<head lang="en">
    <link rel="stylesheet" href="static/css/miaosha/pull.css?v=<%=sysVersion %>"/>
    <script type="text/javascript" src="static/js/scb/iScroll.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/fixed/seckillPull.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/common/format_common.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/fixed/seckillRecord.js?v=<%=sysVersion %>"></script>
    <title>秒客盈</title>
</head>
<body>
    <div class="seckillCard bg-white" id="seckillNum">
        <h2 class="recordTitle tc">往期秒杀记录</h2>
        <%-- <p class="font-text-sm">总人数：<e:property value="@resultDto.totalNum"/>人 <span>|</span> 总金额：<e:property value="@_formater.formatNumber(resultDto.totalAmount)"/>元</p> --%>
    </div>
    <div id="wrapper">
        <div id="scroller">
        <div id="pullDown" style="display:none;" align="center">
            <span class="pullDownIcon"></span>
            <span class="pullDownLabel">下拉刷新...</span>
        </div>
        <div id="seckillRecord"></div>
        <!-- 上拉加载更多  start-->
        <div id="pullUp" style="display:none;" align="center">
            <span class="pullUpIcon"></span>
            <span class="pullUpLabel">上拉加载更多...</span>
        </div>
        <!-- 上拉加载更多  end-->
        </div>
    </div>
    <%-- <e:if test="@resultDto.seckillHistoryListDto != null && resultDto.seckillHistoryListDto.size() != 0">
        <e:iterator list="@resultDto.seckillHistoryListDto" var="item">
            <div class="seckill-list bg-white">
                <div class="secName tc fl">
                    <span class="red font-tit-sm block"><e:property value="@item.productName"/></span>
                    第期
                </div>
                <div class="timeList fl pr">
                    <span class="time-line red pa">|</span>
                    <ul>
                        <li><i class="icon icon-time red"></i> <e:property value="@_formater.formatDate(item.saleStart)"/><span class="font-text-note"> (发售)</span></li>
                        <li><i class="icon icon-time red"></i> <e:property value="@_formater.formatDate(item.saleEnd)"/><span class="font-text-note"> (售完)</span></li>
                    </ul>
                </div>
                <div class="clearfix"></div>
                <e:property value="@item.periodNo"/>
                <p class="secInfo"><span class="fr">秒杀人数：<e:property value="@item.seckillNum"/>人</span> 秒杀总额：<e:property value="@_formater.formatNumber(item.seckillAmount)"/>元</p>
            </div>
        </e:iterator>
    </e:if> --%>
    <div class="h0-5"></div>
</body>
</html>