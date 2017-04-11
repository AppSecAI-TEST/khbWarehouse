<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
    <!-- <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    优先使用 IE 最新版本和 Chrome
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, maximum-scale=1.0, initial-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    设置苹果工具栏颜色
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="../css/LM-common.css">
    <link rel="stylesheet" href="../css/LM-app.css">
    <link rel="stylesheet" href="../css/icon-style.css">
    <script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="../js/LM-app.js"></script> -->
    <title>抽奖记录</title>
</head>
<body>
<div id="box" style="background: #7a56ff">
    <div class="pr"><img src="static/images/activity/record-01.jpg" class="repeatImg"></div>
    <div class="pr">
        <img src="static/images/activity/record-02.jpg" class="repeatImg">
        <div class="lotteryTable pa">
            <table cellpadding="0" cellspacing="0" width="100%" class="tc font-white">
                <tbody>
                    <tr>
                        <th width="36%">抽奖时间</th>
                        <th width="32%">奖项名称</th>
                        <th width="">奖品</th>
                    </tr>
                </tbody>
            </table>
            <div class="lotteryconTable">
                <table cellpadding="0" cellspacing="0" width="92%" class="tc font-white">
                	<tbody>
                	<e:if test="${empty prizeDtoList }">
                	   <tr>
                	     <td></td>
                	     <td>暂无抽奖记录</td>
                	     <td></td>
                	   </tr>
                	 </e:if>
                	 <e:if test="${!empty prizeDtoList }">
	                  <e:iterator list="@prizeDtoList" var="items">
	                      <tr>
					        <td width="30%"><e:property value="@_formater.formatDate(items.createTime)"/></td>
	                        <td width="44%">
	                        <e:iterator list="@_textResource.getTextMap('activity_prize_level')" var="item">
	                        <e:if test="@Integer.parseInt(item.key) == items.prizeLevel"> 
	                         <e:property value="@item.value"/>
	                         </e:if> 
	                        
	                        </e:iterator>
	                       <%--  <e:property value="@items.prizeLevel"/> --%>
	                        
	                        </td>
	                        <td width=""><e:property value="@items.prizeName"/></td>
					       </tr>				       
	    			  </e:iterator>
	    			 </e:if>
                	</tbody>
            	</table>
            </div>
        </div>
    </div>
</div>
</body>
</html>