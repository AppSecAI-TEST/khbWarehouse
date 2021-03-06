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
    <link rel="stylesheet" href="static/css/older/idangerous.swiper.css?v=<%=sysVersion %>"/>
    <link rel="stylesheet" href="static/css/zt/pull.css?v=<%=sysVersion %>"/>
    <script type="text/javascript" src="static/js/scb/idangerous.swiper.min.js"></script>
    <script type="text/javascript" src="static/js/scb/iScroll.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/zt/pull.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/zt/policyTradeRecord.js?v=<%=sysVersion %>"></script>
    <title>交易明细</title>
    <style>
    #wrapper{ top:50px;}
    </style>
</head>
<body>
    <input type="hidden" id="policyOrderId" name="policyOrderId" value="<e:property value="@policyOrderId"/>"/>
    <input type="hidden" id="orderType" name="orderType" value=""/>
    <input type="hidden" id="orderStatus" name="orderStatus" value=""/>
    <div class="tran-detail-Area">
            <div class="detail-list bg-white">
                <ul>
                    <li class="fl pr">
                        <span id="aSelectedText">交易类型</span>
                        <i class="trigger trigger-bottom"></i>
                    </li>
                    <li class="fl pr">
                        <span id="bSelectedText">确认状态</span>
                        <i class="trigger trigger-bottom"></i>
                    </li>
                </ul>
                <div class="detail-list-main bg-white pa">
                    <div class="style-list label" id="orderType">
                        <ul class="tc">
                            <li id="">全部交易类型</li>
                            <li id="PURCHASE">申购</li>
                            <li id="REDEEM">赎回</li>
                            <li id="CANCE_ORDER">撤单</li>
                        </ul>
                    </div>
                    <div class="confirm-list label" id="orderStatus">
                        <ul class="tc">
                            <li id="">所有</li>
                            <li id="CONFIRMED">全部已确认</li>
                            <li id="PART_CONFIRMED">部分已确认</li>
                            <li id="NO_CONFIRMED">全部未确认</li>
                        </ul>
                    </div>
                </div>
                <div id="wrapper" style="bottom: 0px;">
                <div id="scroller">
                <div id="pullDown" align="center"></div>
                <div id="tradeRecord">
                    <!-- <div class="detailContent bg-white">
                        <p class="detailPlan">购房购车 · 金石之策 <span class="fr">2016/09/22 08:24 <a href="#"><i class="icon icon-arrow-right fr"></i></a></span></p>
                        <p class="detailConfirm"><span class="orange"> +<i class="sum">1000000</i>.00</span><i class="fr">申购成功</i></p>
                    </div>
                    <div class="detailContent bg-white">
                        <p class="detailPlan">购房购车 · 金石之策 <span class="fr">2016/09/22 08:24 <a href="#"><i class="icon icon-arrow-right fr"></i></a></span></p>
                        <p class="detailConfirm"><span class="orange"> +<i class="sum">1000</i>.00</span><i class="fr">申购中...</i></p>
                    </div>
                    <div class="detailContent bg-white">
                        <p class="detailPlan">购房购车 · 金石之策 <span class="fr">2016/09/22 08:24 <a href="#"><i class="icon icon-arrow-right fr"></i></a></span></p>
                        <div class="detailConfirm-list"> 
                            <p class="detailConfirm"><span class="orange"> +<i class="sum">3000</i>.00</span><i class="fr">申购中...</i></p>
                            <p class="detailConfirm"><span class="orange"> +<i class="sum">1000</i>.00</span><i class="fr">申购失败</i></p>
                            <p class="detailConfirm"><span class="orange"> +<i class="sum">1000</i>.00</span><i class="fr">申购成功</i></p>
                        </div>
                    </div>
                    <div class="detailContent tc">
                        <p class="pullUpLabel">未查询到相关记录</p>
                    </div> -->
                </div>
                
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
</body>
</html>