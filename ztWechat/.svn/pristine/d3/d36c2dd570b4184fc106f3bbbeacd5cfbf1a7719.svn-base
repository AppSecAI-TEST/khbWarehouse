<%@page import="com.yeepay.g3.app.lmweChat.service.RequestParamBuilderService"%>
<%@page import="com.yeepay.g3.facade.activity.enums.ShareBizTypeEnum" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yeepay.g3.app.lmweChat.utils.WXAPIUtils,java.util.Map" %>
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
    <link rel="stylesheet" href="static/css/LM-common.css">
    <link rel="stylesheet" href="static/css/LM-app.css">
    <link rel="stylesheet" href="static/css/LM-tour.css">
    <link rel="stylesheet" href="static/css/icon-style.css">
    <link rel="stylesheet" href="static/css/islider.css">
    <script type="text/javascript" src="static/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="static/js/LM-app.js"></script>
    <title>免费出游</title>
    <script type="text/javascript">
    function login(){
      /* var platform = $("#platform").val();
      if(platform == "APP"){
        location.href = "";
      } */
      var u = navigator.userAgent;
      var isApp = /lanmao/i.test(u);
      if(isApp) {
  		location.href ="account/toLogin?returnUrl=invForPro/toInvestForTravelList";
  		return;
  	  }
      $("#mask").show();
      $("#alertLayer").show();
    }
    </script>
</head>
<body>
<input type="hidden" id="platform" name="platform" value="<e:property value="@platform"/>"/>
<div class="travelArea">
    <div class="travelHead">
        <div class="logo fl"><img src="static/images/tour/logo.png" alt=""/></div>
        <div class="travelLoad fr">
            <!--未登录情况-->
            <e:if test="${login == 'NO' }">
            <div id="mask" style="display: none"></div>
            <div id="alertLayer" class="unloginMask"
      style="display: none; width: 100%; height: 100%;">
      <div class="pr">
        <img src="static/images/unloginMask.png" class="repeatImg" alt="" />
        <a class="btnClosed font-white pa"><i class="icon icon-error2"></i></a>
        <a href="account/toRegister" class="btnReg pa"></a> <a href="account/toLogin?returnFlag=toBuyInvForPro" class="btnLogin pa"></a>
      </div>
    </div>
            	<a href="javaScript:void(0)" onclick="login()">登录<i class="icon icon-arrow-right"></i></a>
            </e:if>
            <!--已登录情况-->
             <e:if test="${login == 'YES' }">
                <e:if test="${haveOrder == 'YES' }">
                    <a href="invForPro/toOrderList" class="pr"><i class="icon icon-buy"></i><i class="icon icon-round pa"></i></a>
                </e:if>
                <e:if test="${haveOrder == 'NO' }">
                	<a href="invForPro/toOrderList" class="pr"><i class="icon icon-buy"></i></a>
                </e:if>
            	
            </e:if>
        </div>
    </div>
    <div class="pr">
        <img src="static/images/tour/travel-01.jpg" class="repeatImg"/>
        <img src="static/images/tour/travel-02.jpg" class="repeatImg"/>
    </div>
    <div class="travelLine">
      	<e:if test="${!empty invForProDtoList }">
	    	<e:iterator list="@invForProDtoList" var="items">
		        <div class="orderList voucherList voucherRed pr">
			        <a href='<e:property value="@items.url"/>&productId=<e:property value="@items.id"/> '>
			            <div class="orderTop">
			                <div class="orderImg fl">
			                    <img src='invForPro/lookProductImg?id=<e:property value="@items.id"/>' alt=""/>
			                </div>
			                <div class="orderText fl">
			                    <h3><e:property value="@items.name"/></h3>
			                    <p><span class="price">市场价：￥<e:property value="@items.price"/></span></p>
			                    <div class="travelMiddle">
			                        <div class="free fl">免费</div>
			                        <div class="purchase fl">
			                            <p>购买定期<span class="num"> ￥<e:property value="@items.productPriceLow"/></span></p>
			                            <p>年化收益价值<span class="num productRate" > <e:property value="@items.rate"/></span></p>
			                        </div>
			                    </div>
                           </div>
                           <div class="fl" style="width: 64%">
			                    <div class="travelBottom">
			                        <p class="sellInfo fl">
			                        	<span class="copy">已售<e:property value="@items.usedNum"/>份</span>
			                        	<span>剩余<e:property value="@items.stockNum-items.usedNum"/>份</span>
			                        </p>
			                        <i class="fr">马上抢</i>
			                    </div>
			               </div>
			                <div class="clearfix"></div>
			            </div>
		            </a>
		        </div>
	       </e:iterator>
       </e:if>
       <e:if test="${empty invForProDtoList }">
         <div class="orderList voucherList voucherRed pr">
		        <p>暂无产品列表</p>    
		 </div>
       </e:if>
    </div>
    <div class="pr">
        <img src="static/images/tour/travel-03.jpg" class="repeatImg"/>
        <img src="static/images/tour/travel-04.jpg" class="repeatImg"/>
        <img src="static/images/tour/travel-05.jpg" class="repeatImg"/>
    </div>
    <div class="activityInfo pr">
        <img src="static/images/tour/travel-06.jpg" class="repeatImg"/>
        <div class="activityDescription pa">
            <h2>活动说明</h2>
            <ul>
                <li>1、本活动由懒猫金服与够旅游联合出品；</li>
                <li>2、活动定期理财产品由懒猫金服提供，旅游线路由够旅游提供；</li>
                <li>3、可分别点击进入 <span><a style="color:#fdfeff" href="popularize/toPopularize">懒猫金服</a></span> 与 <span><a style="color:#fdfeff" href="http://m.goutrip.com">够旅游</a></span> 微网站查看详情。</li>
            </ul>
        </div>
    </div>
</div>
<script type="text/javascript">
var rateList = $(".num.productRate");
for(var i=0;i<rateList.length;i++){
  rateList.eq(i).text(Math.floor( rateList.eq(i).text()*10)/10+"%");
}
</script>
</body>
</html>