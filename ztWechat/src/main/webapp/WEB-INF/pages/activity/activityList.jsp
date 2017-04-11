<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
    
    <title>活动专区</title>
</head>
<body>
<div id="box">
<e:if test="${empty activityList }">
		<div class="activityList radius1 bg-white">
	        <h2 class="activityTitle orange" style="text-align:center">暂无活动</h2>
	    </div>
</e:if>

<e:if test="${!empty activityList }">
   <e:iterator list="@activityList" var="items">
	    <div class="activityList radius1 bg-white">
       <a href='<e:property value="@items.activityUrl"/>' class=" br-top">
	        <h2 class="activityTitle orange"><e:property value="@items.activityName"/></h2>
	        <p class="font-12"><e:property value="@_formater.formatDate(items.startTime,'dateonly')"/>至<e:property value="@_formater.formatDate(items.endTime,'dateonly')"/></p>
	        <img src='activity/lookPicture?id=<e:property value="@items.id"/>' class="repeatImg"/>
	        <a href='<e:property value="@items.activityUrl"/>' class="A-nav br-top">
	            <i class="icon icon-arrow-right fr"></i>
	            <i class="orange">立即查看</i>
	        </a>
           </a>
	    </div>
    </e:iterator>
</e:if>
    <div class="h0-5"></div>
</div>
<script type="text/javascript">

</script>
</body>
</html>