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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>风险评测</title>
<link rel="stylesheet" href="static/css/LM-funds.css?v=<%=sysVersion %>">
<script>
$(document).ready(function() {
    $(function(){
         $('.answer li').click(function(){
             $('.answer li').removeClass('active');
             $(this).addClass('active');
             $("#retreatAnswer").val($(this).data("value"));
         })
    })
    $("#submitBtn").click(function() {
	  $("#form").submit();
	});
  
});
</script>
</head>
<body style="background: #fff;">
<div class="subjectArea coverArea pr">
    <form action="zt/risk/doRetreatTest" id="form" method="get">
    <input type="text" id="sceneId" name="sceneId" value="${sceneId}" style="display:none" />
    <input type="text" id="retreatAnswer" name="retreatAnswer"  style="display:none" />
    </form>
    <div class="coverContent">
        <!--进度条开始-->
        <div class="progressBar">
            <div class="evolve evolve-1"></div>
        </div>
        <!--<div class="progressBar">
            <div class="evolve evolve-2"></div>
        </div>
        <div class="progressBar">
            <div class="evolve evolve-3"></div>
        </div>
        <div class="progressBar">
            <div class="evolve evolve-4"></div>
        </div>
        <div class="progressBar">
            <div class="evolve evolve-5"></div>
        </div>
        <div class="progressBar">
            <div class="evolve evolve-6"></div>
        </div>
        <div class="progressBar">
            <div class="evolve evolve-7"></div>
        </div>
        <div class="progressBar">
            <div class="evolve evolve-8"></div>
        </div>
        <div class="progressBar">
            <div class="evolve evolve-9"></div>
        </div>-->
        <!--进度条结束-->
        <div class="subjectHeader">
            <p class="question questionLast">您能承受的最大亏损是多少？</p>
        </div>
        <div class="answer">
	        <ul>
	            <li id='l1' data-value="0">
	                <div>
	                    <i class="icon">●</i>
	                    <span id='a1'>基本不亏损</span>
	                </div>
	
	            </li>
	            <li id='l2' data-value="0.1">
	                <div>
	                    <i class="icon">●</i>
	                    <span id='a2'>本金最多可能亏损10%</span>
	                </div>
	            </li>
	            <li id='l3' data-value="0.2">
	                <div>
	                    <i class="icon">●</i>
	                    <span id='a3'>本金最多可能亏损20%</span>
	                </div>
	            </li>
	            <li id='l4' data-value="0.3">
	                <div>
	                    <i class="icon">●</i>
	                    <span id='a4'>本金最多可能亏损30%</span>
	                </div>
	            </li>
	            <li id='l5' data-value="0.4">
	                <div>
	                    <i class="icon">●</i>
	                    <span id='a5'>本金最多可能亏损40%</span>
	                </div>
	            </li>
	        </ul>
        </div>
        <div class="submitBtn" id="submitBtn">
            <a href="javascript:void(0)">提交</a>
        </div>
    </div>
</div>
<div class="prompt">
    <p>市场有风险，投资需谨慎</p>
</div>

</body>

</html>