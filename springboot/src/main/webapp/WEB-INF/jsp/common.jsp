<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% String basePath = request.getContextPath();%>
<script type="text/javascript">
var ctx = "<%=basePath %>";
</script>
<script type="text/javascript" src="<%=basePath %>/static/js/plugin/jquery2.1.4.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/js/common/common.js"></script>
	<script type="text/javascript">
	$(document ).ajaxComplete(function(event,xhr,settings){
	  if(xhr.getResponseHeader("sessionstatus") == "timeout"){
	    location.href = location.href;
	  }
	});
	
	</script>
