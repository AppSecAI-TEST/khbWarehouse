<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
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
<html>
<head>
	<base href="<%=basePath%>" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!--优先使用 IE 最新版本和 Chrome-->
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, maximum-scale=1.0, initial-scale=1.0, user-scalable=0" />
	<meta name="apple-mobile-web-app-capable" content="yes"/>
	<!--设置苹果工具栏颜色-->
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<link rel="stylesheet" href="static/css/LM-common.css?v=<%=sysVersion %>" />
	<link rel="stylesheet" href="static/css/LM-app.css?v=<%=sysVersion %>" />
	<link rel="stylesheet" href="static/css/icon-style.css?v=<%=sysVersion %>" />
    <link rel="stylesheet" href="static/css/LM-footer.css?v=<%=sysVersion %>" />
    <link rel="stylesheet" href="static/css/layer.css?v=<%=sysVersion %>"/>
	<script type="text/javascript" src="static/js/jquery.1.7.2.min.js" />
	<script type="text/javascript" src="static/js/jquery.validate.min.js" ></script>
	<script type="text/javascript" src="static/js/layer.m.js"></script>
	<script type="text/javascript" src="static/js/validate.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/LM-app.js?v=<%=sysVersion %>"></script>
	<style>
	    .post20{ margin-top:12%;}
	    #messageBox{ top:2%;}
	</style>
	<script>
		var _hmt = _hmt || [];
		(function() {
		  var hm = document.createElement("script");
		  hm.src = "//hm.baidu.com/hm.js?1f2caaa13ec099f0b33214daad8b8db0";
		  var s = document.getElementsByTagName("script")[0]; 
		  s.parentNode.insertBefore(hm, s);
		})();

	    //判断App或微信端访问，用于隐藏底部菜单
	    $(function(){
		    var u = navigator.userAgent;
		    var isApp = /lanmao/i.test(u);
		    if(isApp) {
		    	$("#foo").hide();
		    	$(".h1-6").hide();
		    }
	    });
	</script>
  <title><sitemesh:write property="title"/></title>
  <sitemesh:write property="head"/>
</head>
<body>
<sitemesh:write property="body"/>
<footer class="foo" id="foo">
    <ul class="mainmenu clearfix">
        <li class="icon_menu cur_menu">
            <a href="<%=basePath %>popularize/toPopularize" >
                <i class="icon icon-purse"></i>
                <div class="icon_font">购买理财</div>
            </a>
        </li>
        <li class="icon_menu">
            <a href="<%=basePath %>asset/toAsset" >
                <i class="icon icon-cat"></i>
                <div class="icon_font">我的懒猫</div>
            </a>
        </li>
        <li class="icon_menu">
            <a href="<%=basePath %>focus/toFoucs" >
                <i class="icon icon-aboutus"></i>
                <div class="icon_font">关于我们</div>
            </a>
        </li>
    </ul>
</footer>
</body>
</html>
