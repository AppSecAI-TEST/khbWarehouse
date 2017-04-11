<%@page import="com.yeepay.g3.app.lmweChat.utils.LmConstants" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
 <%@ taglib prefix="e" uri="/emvc-tags" %>
 <%@ page import="com.yeepay.g3.app.lmweChat.utils.LmConstants" %>
 <%
 String path = request.getContextPath();
 String basePath = request.getScheme() + "://"+ request.getServerName(); 
 if(request.getServerPort()!=80){
   basePath = basePath + ":" + request.getServerPort(); 
 }      
 basePath = basePath + path + "/";
 String sysVersion = LmConstants.sysVersion;
%>
<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--优先使用 IE 最新版本和 Chrome-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, maximum-scale=1.0, initial-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <!--设置苹果工具栏颜色-->
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
   <%--  <link rel="stylesheet" href="static/css/LM-common.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/LM-app.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/LM-funds.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/icon-style.css?v=<%=sysVersion %>"> --%>
    
     
   <%--  <link rel="stylesheet" href="static/css/LM-invest.css?v=<%=sysVersion %>"> 
    <script type="text/javascript" src="static/js/jquery-1.8.3.min.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/LM-app.js?v=<%=sysVersion %>"></script> --%>
    
     <script src="static/js/highcharts.js?v=<%=sysVersion %>" type="text/javascript"></script>
     
    <%-- <script src="static/js/fund_detail_current.js?v=<%=sysVersion %>" type="text/javascript"></script> --%>
    
    
    <script type="text/javascript" src="static/js/zt/policyDetailInfo.js?v=<%=sysVersion %>"></script>
    <title>${ztPolicyDto.policyName }</title>
</head>
<body>
<input type="hidden" value="<e:property value="@ztPolicyDto.id"/>" id="policyId" />
<input type="hidden" value="<e:property value="@ztPolicyCalculateRecordDto.lastTerm"/>" id="lastTerm" />
<div class="detailArea" style="position: initial;">
    <div class="detailContent-1 bg-white">
        <div class="increaseArea combinationArea pr">
            <div class="bg-white tr">
                <p class="combinationIcon font-14 pa">
                    <i class="icon icon-questionmark flip" ></i>
                </p>
                <div class="panel pa" style="display: none">
                    <span class="detailTip tc pr">
                        <i class="icon-arrow triangle-top"></i>
                                              过去<e:property value="@ztPolicyCalculateRecordDto.lastTerm"/>年回报率=（过去<e:property value="@ztPolicyCalculateRecordDto.lastTerm"/>年每月投资该策略至今的总市值/过去<e:property value="@ztPolicyCalculateRecordDto.lastTerm"/>年总投资额-1）*100%<br>
                      （假设条件：过去<e:property value="@ztPolicyCalculateRecordDto.lastTerm"/>年的定投时间均为当月的第一个交易日，每月投资的金额相同。）<br>
                        过去<e:property value="@ztPolicyCalculateRecordDto.lastTerm"/>年最大回撤率=过去<e:property value="@ztPolicyCalculateRecordDto.lastTerm"/>年的定投中曾经达到过的最大亏损率<br>
                     （假设条件：过去<e:property value="@ztPolicyCalculateRecordDto.lastTerm"/>年的定投时间均为当月的第一个交易日，每月投资的金额相同。）
                    </span>
                </div>
            </div>
            <div class="net-worth fl tc">
                <p class="num-1 orange">
                <e:if test="@ztPolicyCalculateRecordDto.totalYieldRate>=0">
                <span>+</span>
                </e:if><e:else>
                <span>-</span>
                </e:else>
                <e:property value="@_formater.formatNumber(ztPolicyCalculateRecordDto.totalYieldRate*100)"/><span>%</span></p>
                <p>过去<e:property value="@ztPolicyCalculateRecordDto.lastTerm"/>年回报率</p>
            </div>
            <div class="net-worth-right fl tc">
                <table class="increase" width="100%">
                    <tr class="tr-1">
                    <td class="num num-2 td-1 td-2 size"><e:property value="@_formater.formatNumber(ztPolicyCalculateRecordDto.minYieldRate*100)"/>%</td>
                    </tr>
                    <tr>
                        <td  class="td-1 td-2">过去<e:property value="@ztPolicyCalculateRecordDto.lastTerm"/>年回撤率</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="clearfix"></div>
        <div class="purchase-amount">
            <ul>
                <li><e:property value="@_formater.formateMoney(ztPolicyDto.minPurchaseAmount)"/>元起购</li>
                <li>波动<e:property value="@ztPolicyDto.fluctuate"/></li>
            </ul>
        </div>
    </div>
    <!-- 曲线图 -->
    <div class="chart-wrap chart-wrap-new">
         <div class="combination-Title">
            <p class="p-1">收益曲线</p>

            <div class="tabs">
                <a href="javascript:changeType(1);" data-profit_type="1" class="on" >一次性投入</a>
                <a href="javascript:changeType(2);" data-profit_type="2" >每月定投</a>
            </div>

            <div class="profit pr">
                <div class="profit-left fl" id="incomePolicy">
                   
                </div>
                <div class="profit-right evalCard fr" id="compareShow" style="display : none">
                    <p class="expect expect-new">
                        <span class="dot-2">●</span>
                        比较基准
                        <i class="icon icon-questionmark flip"></i>：
                        <span class="green" id="incomeCompare"></span>
                    </p>
                    <div class="panel pa" style="display: none">
                        <span class="profitTip tc pr">
                        <i class="icon-arrow triangle-top pa"></i>
                        比较基准=<e:property value="@stock"/>%*上证综合指数+<e:property value="@bond"/>%*中证综合债指数
                        
                        </span>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class="chart-cnt">
            <div class="chart" id="chart" style="width:100%; data-highcharts-chart="0">
                <div class="highcharts-container" id="highcharts-0" style="position: relative; overflow: hidden; width: 100%; height: 270px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);">
                
            </div>
            <div class="chart-tab chart-tab-new">
                <a class="on" href="javascript:changeYearData(1);" data-duration="30" >一年</a>
                <a href="javascript:changeYearData(2);" data-duration="90" class="">两年</a>
                <a href="javascript:changeYearData(3);" data-duration="180">三年</a>
                <a href="javascript:changeYearData(4);" data-duration="365">四年</a>
                <a href="javascript:changeYearData(5);" data-duration="365">五年</a>
            </div>
            <p class="note">注：基金的过往业绩并不预示其未来表现</p>
        </div>
    </div> 
    
    </div> 
    <div class="detailContent briefContent">
        <h3 class="briefTitle-new">策略详情</h3>
        <div class="combinationMain pr">
            <div class="proportion">
                <ul>
                    <li class="fl">
                        <p class="num"><e:property value="@bond"/>%</p>
                        <p class="text">债基</p>
                    </li>
                    <li class="fl">
                        <p class="num"><e:property value="@stock"/>%</p>
                        <p class="text">股基</p>
                    </li>
                </ul>
            </div>
            <div id="fundcontainer" class="chart-round pa"></div>


        </div>
        <div class="ratioArea">
            <div class="ratio-title">配置比例</div>
            <div class="ratioContent">
            <e:iterator list="@policyProductList" var="items">
            <div class="ratioList">
                    <h3><span class="fl label-title ratioTitle-<e:property value="@(policyProductList.indexOf(items)%4)+1"/>">
                    <e:if test="@items.productType.toString()=='STOCK'">
                    股票型
                    </e:if><e:else>
                    债券型
                    </e:else></span>
                        <span class="name fl" href="#"><e:property value="@items.productName"/>  <e:property value="@items.productCode"/></span>
                        <span class="num fr"><e:property value="@items.productProportion*100"/>%</span></h3>
                    <div class="scaleBox pr">
                        <div class="scale scale-1 pa" style="width: <e:property value="@items.productProportion*100"/>%;"></div>
                    </div>
                </div>
            </e:iterator>
            </div>
        </div>
    </div>
    <div class="detailContent briefContent">
        <h3 class="briefTitle-new">动态策略</h3>
        <div class="briefText briefText-new">
            <p class="text"><e:property value="@policyDesc" escape="false"/> </p>
            <p class="tr more"><a href="zt/policy/toPolicyInfoMoreDetail?policyId=<e:property value="@ztPolicyDto.id"/>&type=policyDesc">查看更多</a></p>
        </div>
    </div>
    <div class="detailContent briefContent">
        <h3 class="briefTitle-new">相关费用</h3>
        <div class="briefText briefText-new">
            <p class="text"><e:property value="@costDesc" escape="false"/></p>
            <p class="tr more"><a href="zt/policy/toPolicyInfoMoreDetail?policyId=<e:property value="@ztPolicyDto.id"/>&type=costDesc">查看更多</a></p>
        </div>
    </div>
    <div class="detailContent briefContent">
        <h3 class="briefTitle-new">注意事项</h3>
        <div class="briefText briefText-new">
            <p class="text"><e:property value="@attentionDesc" escape="false"/></p>
            <p class="tr more"><a href="zt/policy/toPolicyInfoMoreDetail?policyId=<e:property value="@ztPolicyDto.id"/>&type=attentionDesc">查看更多</a></p>
        </div>
    </div>
<!--     <div class="h1-2"></div> -->
 
</body>
</html>