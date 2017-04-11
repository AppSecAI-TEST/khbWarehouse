<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"+ request.getServerName(); 
  if(request.getServerPort()!=80){
    basePath = basePath + ":" + request.getServerPort(); 
  }      
  basePath = basePath + path + "/";
%>
<html>
<head>
</head>
<body>

<script language="javascript" type="text/javascript">
           window.location.href='https://open.weixin.qq.com/connect/oauth2/authorize?appid=<%=com.yeepay.g3.app.lmweChat.service.RequestParamBuilderService.APP_ID%>&redirect_uri=<%=java.net.URLEncoder.encode(basePath+"activity/toInviteFriend", "utf-8")%>&response_type=code&scope=snsapi_userinfo&state=common&connect_redirect=1#wechat_redirect'; 
</script>
</body>
</html>