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
<head lang="en">
    <link rel="stylesheet" href="static/css/LM-tour.css">
    <script type="text/javascript" src="static/js/LM-TourOrderDetail.js"></script>
    <title>订单详情</title>
</head>
<body>
<input id="isBankCard" type="hidden" value='<e:property value="@isBankCard"/>'>
<div id="box">
    <div class="bg-white orderHd">
        <div class="orderList voucherList voucherRed pr">
            <div class="orderTop">
                <a class="orderImg fl" href="javascript:void(0);"><img src="invForPro/lookProductImg?id=<e:property value='@result.activityInvForProInfoDTO.id'/>" alt=""/></a>
                <div class="orderText fl">
                    <h3><a href="javascript:void(0);"><e:property value="@result.activityInvForProInfoDTO.name"/></a></h3>
                    <p>
                    	<span>￥<e:property value="@result.activityInvForProInfoDTO.price"/></span>  
                    	出行人数X<e:property value="@result.activityInvForProOrderDTO.num"/>
                    </p>
                    <p><span>市场价：￥<e:property value="@marketValue"/></span></p>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="orderBottom pr">
                <img src="static/images/tour/orderText-bg.jpg" alt=""/>
                <div class="orderDetails pa">
                    <div class="orderNum fl">订单编号：<e:property value="@result.activityInvForProOrderDTO.orderCode"/></div>
                    <div class="orderFinish fr">
                    	<a href='<e:property value="@result.activityInvForProInfoDTO.url"/>&productId=<e:property value="@result.activityInvForProInfoDTO.id"/>'>
                    		查看详情<i class="icon icon-arrow-right"></i><i class="icon icon-arrow-right"></i>
                    	</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="bg-white layou-04 tourInfoList">
        <ul>
            <li>
                <label>产品期限：</label>
                <span class="orange"><e:property value="@lcProduct.termDay"/></span> 天
            </li>
            <li class="buyNum">
                <label>购买金额：</label>
                <span class="orange"><e:property value="@result.activityInvForProOrderDTO.total"/></span>  元
                <p><i class="icon icon-tip"></i> 请直接输入转入金额，账户余额不足将先进行充值</p>
            </li>
            <li>
                <label>账户余额：</label>
                <e:property value="@accountBalanceResult"/> 元
            </li>
        </ul>
    </div>
</div>
<div class="h1-3"></div>
<e:if test="${!empty overDue }">
	<div class="btnBot"><input id="toBuy" type="button" value='该订单已过期' class="btnBuy"/></div>
</e:if>
<e:if test="${empty overDue }">
	<e:if test="${!empty charge }">
	    <input  value='<e:property value="@charge"/>'   id="rechargeAmount"/>
		<div class="btnBot"><input id="toBuy" type="button" value='去充值<e:property value="@charge"/>' class="btnBuy"/></div>
	</e:if>
	<e:if test="${empty charge }">
		<div class="btnBot"><input id="toBuy" type="button" value='立即购买' class="btnBuy"/></div>
	</e:if>
</e:if>
<input  value='<e:property value="@result.activityInvForProOrderDTO.lcProductId"/>'   id="lcProductId"/>
<input  value='<e:property value="@result.activityInvForProOrderDTO.total"/>'   id="total"/>
<input  value='<e:property value="@orderNum"/>'   id="orderNum"/>
  <!--系统异常-弹出层-->
<div id="mask" style="display: none"></div>
<div id="alertLayer-8" class="unloginMask regMask rechargeMask" style="display: none; width: 100%; height:40%;">
    <div class="pr">
        <img src="static/images/errorMask.png" class="repeatImg" alt=""/>
        <a class="btnClosed font-white pa" href="javascript:void(0)"><i class="icon icon-error2" onclick="clean()"></i></a>
        <p class="errorCon red pa">系统异常，请稍后重试哦</p>
        <div class="btnMaskArea tc pa">
            <a href="javascript:void(0)" onclick="clean()">OK</a>
        </div>
    </div>
</div>
</body>
</html>