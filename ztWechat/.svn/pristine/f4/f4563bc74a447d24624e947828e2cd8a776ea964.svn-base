<%@page import="com.yeepay.g3.app.lmweChat.service.RequestParamBuilderService"%>
<%@page import="com.yeepay.g3.facade.activity.enums.ShareBizTypeEnum" %> 
<%@page import="com.yeepay.g3.app.lmweChat.utils.WXAPIUtils,java.util.Map" %>
<%@page import="com.yeepay.g3.app.lmweChat.utils.LmConstants" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
 <%@ taglib prefix="e" uri="/emvc-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>风险测评结果</title>
<link rel="stylesheet" href="static/css/LM-funds.css">
</head>
<!-- 头部结束 -->
<body>
<div class="coverArea bg-white">
	<div class="coverContent">
    <div class="progressBar">
        <div class="evolve"></div>
    </div>
    <div class="evaluationHeader resultHeader">
        		<e:if test="${reason == 'fund'}">
		            <p></p>
		              <p class="style">
		                                                 基金账户开通失败
		              </p>
		 		</e:if>
		         <e:if test="${reason == 'retreat'}">
		            <p></p>
		              <p class="style">
		                                                最大回报率评测失败
		              </p>
		 		</e:if>        
    </div>
    <div class="evaluationImg">
        <img src="static/images/fund/evaluationImg-0.png" alt=""/>
    </div>
    </div>
</div>
<div class="prompt">
    <img src="static/images/fund/prompt.png" alt=""/>
</div>
</body>
</html>