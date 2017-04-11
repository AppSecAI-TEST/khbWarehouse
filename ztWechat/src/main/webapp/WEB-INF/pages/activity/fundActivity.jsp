<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
    <link rel="stylesheet" href="static/css/LM-activity.css">
    <title>10元起开启基金理财之旅</title>
</head>
<body>
<div style="background: #ecf8ff;">
    <div class="pr">
        <img src="static/images/activity/fund-01.jpg" class="repeatImg"/>
        <img src="static/images/activity/fund-02.jpg" class="repeatImg"/>
        <img src="static/images/activity/fund-03.jpg" class="repeatImg"/>
        <img src="static/images/activity/fund-04.jpg" class="repeatImg"/>
        <img src="static/images/activity/fund-05.jpg" class="repeatImg"/>
        <img src="static/images/activity/fund-06.jpg" class="repeatImg"/>
        <img src="static/images/activity/fund-07.jpg" class="repeatImg"/>
        <img src="static/images/activity/fund-08.jpg" class="repeatImg"/>
    </div>
    <div class="fundArea">
            <div class="fundTable">
                <table cellpadding="0" cellspacing="0" width="100%">
                    <tbody>
                    <tr>
                        <th>基金代码</th>
                        <th width="28%">基金名称</th>
                        <th>近一年涨幅</th>
                        <th>操作</th>
                    </tr>
                    <e:iterator list="@fundList" var="items">
	                    <tr>
	                        <td><e:property value="@items.fundCode"/></td>
	                        <td><e:property value="@items.fundName"/></td>
	                        <td><e:property value="@items.yearIncrease"/>%</td>
	                        <td><a href='<e:property value="@fundSalesUrl"/>show/archivesIndex/<e:property value="@items.fundCode"/>?memberNo=<e:property value="@ascMemberNo"/>'>
	                       		 <img src="static/images/activity/fund-btn10.png"></a>
	                       	</td>
	                    </tr>
                    </e:iterator>
                    
                    </tbody>
                </table>
            </div>
            <p class="fundText tr">（基金有风险，投资需谨慎）</p>
        </div>
</div>
</body>
</html>