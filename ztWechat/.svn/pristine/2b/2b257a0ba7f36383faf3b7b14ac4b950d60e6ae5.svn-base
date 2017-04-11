<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
 <script type="text/javascript" src="static/js/fixed/buySuccess.js"></script>
    <title>购买成功</title>
</head>
<body>
<input type="hidden" id="buyDay" value="<e:property value="@_formater.formatDate(pdfwxrDto.buyDay)"/>"> 
<input type="hidden" id="incomeDay" value="<e:property value="@_formater.formatDate(pdfwxrDto.incomeDay)"/>"> 
<input type="hidden" id="expireDay" value="<e:property value="@_formater.formatDate(pdfwxrDto.expireDay)"/>"> 
<input type="hidden" id="arrivalDay" value="<e:property value="@_formater.formatDate(pdfwxrDto.arrivalDay)"/>"> 
<input type="hidden" id="amount" value="<e:property value="@amount"/>"> 
<input type="hidden" id="productName" value="<e:property value="@pdfwxrDto.productName"/>"> 
<div class="bg-white">
        <div class="pr"><img src="static/images/buySuccess.jpg" class="repeatImg"/></div>
        <div class="layou-04 mb0">
            <div class="br-bottom mt25 pb25 tc">
                <p class="font-18 orange">到期后预计到账金额<e:property value="@_formater.formateMoney(ArrivalAmount)"/>元</p>
                <p class="mt5 font-12">已赠送您一次幸运抽奖机会和1%加息券，<br/>下次购买即可使用哦</p>
                <div class="pr mt15">
                    <a href="activity/toRaffleActivity"><img src="static/images/btn-draw.jpg" class="repeatImg"/></a>
                </div>
            </div>
        </div>
        <div class="flowList pr">
            <span class="bg-t pa"></span>
            <span class="bg-b pa"></span>
            <ul class="">
                <li id="buyDaySpan" class="on">  </li>
                <li id="incomeDaySpan"> </li>
                <li id="expireDaySpan"></li>
                <li id="arrivalDaySpan"><i class="icon icon-circle"></i> 到期后两个工作日 收益本金到账</li>
            </ul>
        </div>
        <div class="pr">
            <img src="static/images/buy_ad.jpg" class="repeatImg"/>
        </div>
        <div class="pr mt15">
            <a href="popularize/toPopularize"><img src="static/images/btn-buyPro.jpg" class="repeatImg"/></a>
        </div>
        <div class="pr mt25">
            <img src="static/images/QR-code-red.jpg" class="repeatImg">
        </div>

</div>


<!--购买成功-弹出层-->
<!--<div id="mask" style="display: block"></div>
 <div id="alertLayer-3" class="unloginMask regMask" style="display: block; width: 100%; height:40%;">
    <div class="pr">
        <img src="static/images/buySuccessMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed font-white pa" href="javascript:void(0)"><i class="icon icon-error2"></i></a>
        <div class="btnMaskArea tc pa">
            <a href="asset/myTotalWealth">查看我的财富</a>
            <a href="popularize/toPopularize">继续购买</a>
        </div>
    </div>
</div> -->
</body>
</html>