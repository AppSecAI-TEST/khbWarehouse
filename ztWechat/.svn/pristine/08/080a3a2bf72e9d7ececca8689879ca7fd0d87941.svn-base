  
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page
	import="com.yeepay.g3.app.lmweChat.utils.WXAPIUtils,java.util.Map"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName();	
	if(request.getServerPort()!=80){
		basePath = basePath + ":" + request.getServerPort(); 
	}			 
	basePath = basePath	+ path + "/";
	
	String queryString = request.getQueryString();
		if(queryString!=null && queryString.trim().length()>0){
			queryString = "?" + queryString;
		}else{
			queryString = "";
		}
	 String pagePath="scb/toTestWXJSSDK";
Map params = WXAPIUtils.getParam(com.yeepay.g3.app.lmweChat.utils.WXAPIUtils.APP_ID,com.yeepay.g3.app.lmweChat.utils.WXAPIUtils.APP_SECRET,basePath+pagePath+queryString);
%>
    <html>
<head>
<script type="text/javascript" src="static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	
   <script>    
     	 wx.config({
			debug : true,
			appId : '<%=com.yeepay.g3.app.lmweChat.utils.WXAPIUtils.APP_ID%>',
			timestamp :<%=params.get("time")%>,
			nonceStr :'<%=params.get("randomStr")%>',
			signature :'<%=params.get("signature")%>',
			jsApiList : ['getLocation']
		 });
		 
			wx.ready(function() {
				wx.getLocation({
				    type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
				    success: function (res) {
				        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
				        var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
				        var speed = res.speed; // 速度，以米/每秒计
				        var accuracy = res.accuracy; // 位置精度
				        alert("纬度="+latitude+"经度="+longitude+"速度="+speed+"位置精度="+accuracy);
				        document.getElementById('d').innerHTML=latitude;
				    }
				});			
    		});	
    </script>
</head>
<title>生财宝</title>
<body>
<p>222222</p>
<p id="d"></p>
</body>
</html>
    