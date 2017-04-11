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
    <link rel="stylesheet" href="static/css/swiper.min.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/zt/incomePull.css?v=<%=sysVersion %>"/>
    <script type="text/javascript" src="static/js/scb/iScroll.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/zt/incomePull.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/common/format_common.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/zt/policyIncomeDetail.js?v=<%=sysVersion %>"></script>
    <title>灵机一投收益</title>
</head>
<body>
<input type="hidden" id="type" name="type" value="<e:property value="@type"/>"/>
<div id="box">
    <div class="bg-white">
        <div class="tabNav tabNav tabs">
            <ul>
                <li class="on">昨日收益</li>
                <li>累计收益</li>
            </ul>
        </div>
    </div>
    <div class="tab_con pb15">
        <blockquote id="yesterday" style="display: block;">
            <div class="incomeCard bg-white tc">
                <p class="font-14">灵机一投昨日收益（元）<i class="icon icon-questionmark flip"></i></p>
                <span class="font-40 orange"><e:property value="@_formater.formatNumber(yesterdayIncome)"/></span>
                <div class="panel pa" style="display: none">
                        <span class="profitTip tc pr">
                            <i class="icon-arrow triangle-top"></i>
                            单个投资计划收益详情请至【我的总财富-我的灵机一投】中查看
                        </span>
                </div>
            </div>
        </blockquote>
        <blockquote id="total" style="display: none;">
            <div class="incomeCard bg-white tc">
                <p class="font-14">灵机一投累计收益（元）<i class="icon icon-questionmark flip"></i></p>
                <span class="font-40 orange"><e:property value="@_formater.formatNumber(totalIncome)"/></span>
                <div class="panel pa" style="display: none">
                        <span class="profitTip tc pr">
                            <i class="icon-arrow triangle-top"></i>
                            累计收益为扣除手续费后总收益
                        </span>
                </div>
            </div>
        </blockquote>
        <!-- 前端原始页面 -->
        <!-- <div class="layou-04 pt10 pb10" style="dispaly:block">
            <div class="deal-meter">
                <div class="meter-wrap">
                    <meter low="0" high="0" min="0" max="100" optimum="0" value="88"></meter>
                    <p class="value-wrap"><span class="cur-date">2015-07-10</span><span class="cur-money">0.88</span></p>
                </div>
            </div>
        </div> -->
        <div class="layou-04 pt10 pb10" style="dispaly:block">
            <div class="deal-meter">
                <div id="wrapper">
                  <div id="scroller">
                    <!-- <div id="pullDown" style="display:none;" align="center">
                        <span class="pullDownIcon"></span>
                        <span class="pullDownLabel">下拉刷新...</span>
                    </div> -->
                    <div id="incomeRecord"></div>
                    <!-- 上拉加载更多  start-->
                    <div id="pullUp" style="display:none;" align="center">
                        <span class="pullUpIcon"></span>
                        <span class="pullUpLabel">上拉加载更多...</span>
                    </div>
                    <!-- 上拉加载更多  end-->
                  </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>