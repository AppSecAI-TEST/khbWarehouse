<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
    <title>懒猫金服送投资券</title>
</head>
<body>
<div style="background: #f1f1f1;">
    <div class="pr">
        <img src="static/images/activity/give-present-1.jpg" class="repeatImg"/>
        <img src="static/images/activity/give-present-2.jpg" class="repeatImg"/>
    </div>
    <div class="pr">
        <img src="static/images/activity/give-present-3.jpg" class="repeatImg"/>
    </div>
    <div class="pr presentText">
        <p>即日起，懒猫金服为每位用户送上10元<br/>投资券一张，人人都可领取哦！使用投<br/>资券购买理财可以产生利息，到期还可<br/>以提现哦！</p>
    </div>
    <div class="pr">
        <img src="static/images/activity/give-present-4.jpg" class="repeatImg"/>
        <e:if test="@type=='ANDROID'">
        <a class="presentBtn block pa" href="https://beta.bugly.qq.com/api/download/pkg/ec368c5d-a1b4-47fe-ab0b-b6847e6dd8ab"></a>
        </e:if><e:elseif test="@type=='IOS'">
      <!--   <a class="presentBtn block pa" href="https://itunes.apple.com/cn/app/lan-mao-jin-fu/id1146670150?mt=8"></a> -->
      <a class="presentBtn block pa" href="http://a.app.qq.com/o/simple.jsp?pkgname=com.summer.lanmao"></a>
        </e:elseif><e:else>
          <a class="presentBtn block pa" href="http://a.app.qq.com/o/simple.jsp?pkgname=com.summer.lanmao"></a>
        </e:else>
    </div>
    <div class="pr">
        <img src="static/images/activity/give-present-5.jpg" class="repeatImg"/>
    </div>
</div>
</body>
</html>