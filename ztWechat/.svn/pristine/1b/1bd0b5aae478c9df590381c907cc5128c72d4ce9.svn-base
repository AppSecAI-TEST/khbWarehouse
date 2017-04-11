<%@page import="com.yeepay.g3.app.lmweChat.utils.LmConstants" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<%
  String sysVersion = LmConstants.sysVersion;
%>
<html>
<head lang="en">
    <script type="text/javascript" src="static/js/account/card_info.js?v=<%=sysVersion %>"></script>
    <title>我的银行卡</title>
</head>
<body>
<div id="box">
    <div class="bankCard font-white pr" id="cardInfoDiv">
    <e:if test="@'GDB'.equals(BANK_ID)">
     <div class="bankImg bankCGB pa"></div>
      <p class="bankName">广发银行</p>
    </e:if><e:else>
    <div class="bankImg bank<e:property value="@BANK_ID"/> pa"></div>
         <p class="bankName"><e:property value="@BANK_NAME"/></p>
    </e:else>
        <p class="bankNum" id="CARD_NO"><e:property value="@CARD_NO"/></p>
    </div>
    <a href="account/card/toBindCard?returnFlag=toMyCard" class="add-bankCard orange" id="addCardDiv">
        <i class="icon icon-plus"></i>
        添加银行卡
    </a>
    <div class="layou-04 bg-white bankSafe" id="addCardDiv">
        <div class="titleInfo br-bottom">
            <h2 class="titleSafe">银行卡安全</h2>
        </div>
        <ul class="bankSafeTips mt15">
            <li>
                <i class="icon icon-circle"></i>
                为保障懒猫理财资金安全，只支持绑定一张银行卡，且只能提现至此卡。
            </li>
            <li>
                <i class="icon icon-circle"></i>
                如需解除绑定，请通过公众号联系懒猫金服客服人员。或拨打服务热线：<span class="orange">4001-500-882</span>
            </li>
        </ul>
    </div>
</div>
</body>
</html>