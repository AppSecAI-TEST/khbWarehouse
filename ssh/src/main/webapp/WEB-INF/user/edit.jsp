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
	用户详情： <div>  姓名：${user.name }   年龄：${user.age }    电话： ${user.telephone }  <a target="_blank" href="user/userinfo!detail.action?id=${user.id}">json详情</a></div>
<form action="/ssh/hello/test.do?method=save" method="post">
<input type="text"class="form-control1" id="userName" name="users[0].name"/>
<input type="text"class="form-control1" id="userDesc" name="users[0].age" />
<input type="text"class="form-control1" id="userName" name="users[1].name"/>
<input type="text"class="form-control1" id="userDesc" name="users[1].age" />
<input type="text"class="form-control1" id="userDesc" name="num" />
<button type="submit">button</button>
</form>
</body>
</html>