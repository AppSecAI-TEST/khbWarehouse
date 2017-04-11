<%@page import="com.yeepay.g3.app.lmweChat.utils.LmConstants" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
 <%@ taglib prefix="e" uri="/emvc-tags" %>
 <%
 String path = request.getContextPath();
 String basePath = request.getScheme() + "://"+ request.getServerName(); 
 if(request.getServerPort()!=80){
   basePath = basePath + ":" + request.getServerPort(); 
 }      
 basePath = basePath + path + "/";
 String sysVersion = LmConstants.sysVersion;
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--优先使用 IE 最新版本和 Chrome-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, maximum-scale=1.0, initial-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <!--设置苹果工具栏颜色-->
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="static/css/LM-common.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/LM-app.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/LM-funds.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/LM-invest.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/layer.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/icon-style.css?v=<%=sysVersion %>">
    <script type="text/javascript" src="static/js/jquery-1.8.3.min.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/LM-app.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/LM-funds.js?v=<%=sysVersion %>"></script>
    <title>相关费用</title>
</head>
<body>
<div class="managerCon bg-white">
        <h3 class="briefTitle">申购费率</h3>
        <div class="managerContent">
        <e:iterator list="@result" var="items">
         <ul>
                <li>
                    <e:property value="@items.fundName"/>   <e:property value="@items.fundCode"/>
                </li>
                <li>
                    <div class="rateTable light-gray tc radius1">
                        <table cellpadding="0" cellspacing="0" width="100%">
                            <tbody>
                            <tr>
                                <th>申购金额(RMB)</th>
                                <th>费率</th>
                            </tr>
                              <e:iterator list="@items.purchaseRatesList" var="item">
                              <tr>
                                <td><e:property value="@item.rangeDesciption"/></td>
                                <td><span class="font-text-note line-through">
                                <e:if test="@item.rate>99">
                                <e:property value="@_formater.formatNumber(item.rate)"/>元
                                </e:if><e:else>
                                <e:property value="@_formater.formatNumber(item.rate)"/>%
                                </e:else>
                                </span>
                                <e:if test="@item.rate>99">
                                <e:property value="@_formater.formatNumber(item.discountRate)"/>元
                                </e:if><e:else>
                                <e:property value="@_formater.formatNumber(item.discountRate)"/>%
                                </e:else>
                                </td>
                            </tr>
                              </e:iterator>
                            </tbody>
                        </table>
                    </div>
                </li>
            </ul>
        </e:iterator>
        </div>
    </div>

<div class="managerCon bg-white">
    <h3 class="briefTitle">赎回费率</h3>
    <div class="managerContent">
    <e:iterator list="@result" var="items">
        <ul>
            <li>
                <e:property value="@items.fundName"/>   <e:property value="@items.fundCode"/>
            </li>
            <li>
                <div class="rateTable light-gray tc radius1">
                    <table cellpadding="0" cellspacing="0" width="100%">
                        <tbody>
                        <tr>
                            <th>赎回期限</th>
                            <th>费率</th>
                        </tr>
                        <e:iterator list="@items.redeemRateList" var="item">
                              <tr>
                                <td><e:property value="@item.rangeDesciption"/></td>
                                <td><span class="font-text-note line-through">
                                <e:if test="@item.rate>99">
                                <e:property value="@_formater.formatNumber(item.rate)"/>元
                                </e:if><e:else>
                                <e:property value="@_formater.formatNumber(item.rate)"/>%
                                </e:else>
                                </span>
                                <e:if test="@item.rate>99">
                                <e:property value="@_formater.formatNumber(item.discountRate)"/>元
                                </e:if><e:else>
                                <e:property value="@_formater.formatNumber(item.discountRate)"/>%
                                </e:else>
                                </td>
                            </tr>
                              </e:iterator>
                        </tbody>
                    </table>
                </div>
            </li>
        </ul>
        </e:iterator>
    </div>
</div>
</body>
</html>