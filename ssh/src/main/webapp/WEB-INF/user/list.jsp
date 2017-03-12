<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<title>userInfo</title>
</head>
<body>
	所有的用户信息：
	<c:forEach items="${ list}" var="user">
	    <div>  姓名：${user.name }   年龄：${user.age }    电话： ${user.telephone }  <a target="_blank" href="hello/test.do?method=detail&id=${user.id}">json详情</a></div>
	</c:forEach>

</body>
</html>