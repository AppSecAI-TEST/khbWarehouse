<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.yeepay.g3.utils.web.ExceptionInfo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>出错页面</title>
</head>

<body>
<%
ExceptionInfo einfo = (ExceptionInfo)request.getAttribute("_exception_info" );
%>

      <div class="error404">
    	<div class="error404_b">
        	<div class="error404_con">
            	<h2>出错啦！</h2>
            		<% if(einfo!=null){%>
            		<p>异常编号：<%=einfo.getExceptionId()%></p>
                <p>异常类信息：<%=einfo.getExceptionClassInfo()%>|<%=einfo.getExceptionMessage()%></p>
                <p>异常描述：<%=einfo.getExceptionText()%></p>
                <p>您现在可以返回：<span><a href="javascript:history.back()">上一页</a></span></p>
                <%}%>
            </div>
        </div>
        </div>

</body>
</html>