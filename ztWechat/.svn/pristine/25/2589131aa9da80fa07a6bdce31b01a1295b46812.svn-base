<%@page import="com.yeepay.g3.app.lmweChat.utils.LmConstants" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<%
  String sysVersion = LmConstants.sysVersion;
%>
<html>
<head lang="en">
    <link rel="stylesheet" href="static/css/layer.css?v=<%=sysVersion %>"/>
    <script type="text/javascript" src="static/js/account/account.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/layer.m.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <title>个人中心</title>
</head>
<body>
<div id="box">
    <a href="account/toVerifyMobile" class="A-nav">
        <i class="icon icon-arrow-right fr"></i>
        <i class="icon icons icon-phone"></i>
        <label class="uLabel">手机号</label>
        <e:property value="@loginName"/>
    </a>
    <a href="account/toModifyLoginPwd" class="A-nav">
        <i class="icon icon-arrow-right fr"></i>
        <i class="icon icons icon-password"></i>
        <label class="uLabel">登录密码</label>
        ********
    </a>
   	<e:if test="${bindCard }">
        <a href="account/toModifyOrResetTradePwd" class="A-nav">
   	</e:if>
   	<e:else>
        <a href="account/card/toBindCard" class="A-nav">
   	</e:else>
        <i class="icon icon-arrow-right fr"></i>
        <i class="icon icons icon-password"></i>
        <label class="uLabel">交易密码</label>
        <e:property value="@tradePwd"/>
    </a>
    <div class="A-nav">
        <span class="orange font-12 fr">不可修改</span>
        <i class="icon icons icon-name"></i>
        <label class="uLabel">个人姓名</label>
        <e:property value="@realName"/>
    </div>
    <div class="A-nav">
        <span class="orange font-12 fr">不可修改</span>
        <i class="icon icons icon-id"></i>
        <label class="uLabel">身份证号</label>
        <e:property value="@credentialsNo"/>
    </div>
    <div class="A-nav">
        <i class="icon icons icon-date"></i>
        <label class="uLabel">注册日期</label>
        <e:property value="@createDate"/>
    </div>
    <div class="layou-04 tc">
      <e:if test="${source==null }">
        <input type="button" class="btn-exit orange" id="relebound" name="relebound" onclick="unBind()" value="退出并解绑公众号"/>
      </e:if>
      <e:if test="${source!=null }">
        <input type="button" class="btn-exit orange" id="relebound" name="relebound" onclick="unBind()" value="退出登录"/>
      </e:if>
    </div>
</div>
</body>
</html>
