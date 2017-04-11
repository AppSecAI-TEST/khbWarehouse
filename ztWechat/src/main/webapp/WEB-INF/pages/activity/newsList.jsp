<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
  	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <title>消息公告</title>
</head>
<body>

<div id="box">
<e:if test="${empty userMessageList }">
  <div class="activityList radius1 bg-white pr">
        
        <h2 class="newsTitle orange" style="text-align:center">暂无消息列表</h2>
        
    </div>
</e:if>
<e:if test="${!empty userMessageList }">
<e:iterator list="@userMessageList" var="items">
    <e:property escape="false" value="@items.msgContent"/>
    </e:iterator>
    </e:if>
    <div class="h0-5"></div>
</div>
</body>
</html>