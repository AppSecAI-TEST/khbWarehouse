<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ page contentType="text/html;charset=UTF-8"%> 
<html>
	<head>
	<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
	<script type="text/javascript">
	$(document ).ajaxComplete(function(event,xhr,settings){
	  if(xhr.getResponseHeader("sessionstatus") == "timeout"){
	    location.href = location.href;
	  }
	});
	
	</script>
	</head>
</html>
