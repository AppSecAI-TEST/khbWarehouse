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
   <%--  <link rel="stylesheet" href="static/css/LM-common.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/LM-app.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/LM-funds.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/LM-invest.css?v=<%=sysVersion %>"> 
    <link rel="stylesheet" href="static/css/layer.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/icon-style.css?v=<%=sysVersion %>">
    <script type="text/javascript" src="static/js/jquery-1.8.3.min.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/LM-app.js?v=<%=sysVersion %>"></script>--%>
    <script type="text/javascript" src="static/js/LM-funds.js?v=<%=sysVersion %>"></script>
    <e:if test="@type=='policyDesc'">
     <title>动态策略</title>
    </e:if><e:elseif test="@type=='costDesc'">
     <title>相关费用</title>
    </e:elseif><e:else>
     <title>注意事项</title>
    </e:else>
</head>
<body>
<div id="box" class="bg-white">
    <div class="managerCon briefCon">
    <e:if test="@type=='policyDesc'">
     <h3 class="briefTitle">动态策略</h3>
        <div class="managerContent">
            <ul>
                <li class="pt15 justify">
                    <e:property value="@ztPolicyDto.policyDesc" escape="false"/> 
                </li>
            </ul>
        </div>
    </e:if><e:elseif test="@type=='costDesc'">
     <h3 class="briefTitle">相关费用</h3>
        <div class="managerContent">
            <ul>
                <li class="pt15 justify">
                    <e:property value="@ztPolicyDto.costDesc" escape="false"/> 
                </li>
            </ul>
        </div>
    </e:elseif><e:else>
     <h3 class="briefTitle">注意事项</h3>
        <div class="managerContent">
            <ul>
                <li class="pt15 justify">
                    <e:property value="@ztPolicyDto.attentionDesc" escape="false"/> 
                </li>
            </ul>
        </div>
    </e:else>
       
    </div>
</div>
</body>
</html>