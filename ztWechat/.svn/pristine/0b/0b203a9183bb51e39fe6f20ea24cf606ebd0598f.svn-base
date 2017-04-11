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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--优先使用 IE 最新版本和 Chrome-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, maximum-scale=1.0, initial-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <!--设置苹果工具栏颜色-->
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="static/css/LM-common.css">
    <link rel="stylesheet" href="static/css/LM-app.css">
    <link rel="stylesheet" href="static/css/LM-tour.css">
    <link rel="stylesheet" href="static/css/icon-style.css">
    <link rel="stylesheet" href="static/css/islider.css">
    <script type="text/javascript" src="static/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="static/js/LM-activityTour.js"></script>
    <title>免费旅游-香港产品详情</title>
</head>
<body>
<input type="hidden" id="unLoginReturnUrlParam" value="<e:property value="@unLoginReturnUrlParam"/>">
<script type="text/javascript">
function clean(){
  $("#mask").hide();
  $("#alertLayer-8").hide();
}
</script>
<div id="box">
    <div class="banner tourbanner"><div id="iSlider-wrapper"></div></div>
    <script src="static/js/islider.js"></script>
    <script>
        var list = [
            {content: "<a href='javaScript:void(0)'><img src='static/images/tour/Hongkong-banner1.jpg'></a>"},
            {content: "<a href='javaScript:void(0)'><img src='static/images/tour/Hongkong-banner2.jpg'></a>"}
        ]
        var opts = {
            type: 'dom',
            data: list,
            dom: document.getElementById("iSlider-wrapper"),
            isLooping: true,
        };
        var	islider = new iSlider(opts);
        islider.addDot();
    </script>
    <input id="surplusNum" type="hidden" value="<e:property value="@surplusNum"/>" />
    <div class="bg-white tourInfo">
        <p class="orange font-16">香港3晚4天自由行</p>
        <p>已售：<e:property value="@invForProInfoDto.usedNum"/>份  剩余：<e:property value="@invForProInfoDto.stockNum-invForProInfoDto.usedNum"/>份</p>
        <input style="display:none" id="leftAmout" value='<e:property value="@invForProInfoDto.stockNum-invForProInfoDto.usedNum"/>' />
    </div>
    <div class="bg-white layou-04 tourInfoList">
         <ul>
            <li>
               <label>出行人数：</label>
               <div class="numArea fl">
                   <a href="javascript:void(0);" class="icon icon-plus js_numChange"></a>
                   <a href="javascript:void(0);" class="icon icon-minus js_numChange"></a>
                   <span class="num" id="buyNum">1</span>
                   <input style="display:none" id="productId" value='<e:property value="@invForProInfoDto.id"/>' />
               </div>
            </li>
            <li>
                <label>理财期限：</label>
                <div class="dropArea ui-select orange tc pr">
                    <i class="icon icon-arrow-d pa"></i>
                    <div class="dropTitle selectText radius4"><e:property value="@ruleXTDtoFirst.trem"/>天</div>
                    <ul class="dropContent selectBox">
	                    <e:iterator list="@ruleXTDtoList" var="items">
	                      <li data-id='<e:property value="@items.id"/>' 
	                          data-value='<e:property value="@items.price"/>'
	                          data-rate='<e:property value="@items.rate"/>' 
	                          class="trem">
	                          <e:property value="@items.trem"/>天
	                      </li>
	                    </e:iterator>
                    </ul>
                    <input style="display:none" value='<e:property value="@ruleXTDtoFirst.price"/>' id="singlePrice" />
                    <input style="display:none" value='<e:property value="@ruleXTDtoFirst.id"/>' id="ruleId" />
                    <e:if test="${!empty member }">
                    	<input id="login" style="display:none" />
                    </e:if>
                </div>
            </li>
            <li>
            	<label>预期年化：</label>
                <label id="productRate"><e:property value="@ruleXTDtoFirst.rate"/></label>%
            </li>
            <li>
                <label>理财金额：</label>
                <span class="orange" id="buyPrice"><e:property value="@ruleXTDtoFirst.price"/></span>  元
            </li>
        </ul>
    </div>
    <div class="tourLast bg-white">
        <div class="tab_nav tabNav tourNav">
            <ul>
                <li class="on">购买须知</li>
                <li>行程说明</li>
                <li>费用说明</li>
                <li>目的地介绍</li>
            </ul>
        </div>
        <div class="tab_con pb15">
            <blockquote style="display: block">
                <div class="activityRule">
                    <ul>
                        <li><span>1.</span>请在72小时内完成购买，过期订单将会被取消；</li>
                        <li><span>2.</span>所购买理财产品本身无收益，到期后本金如数奉还；</li>
                        <li><span>3.</span>由于移动端充值限额较低，建议用户可登录懒猫金服PC端（www.lanmao.com）通过网银进行充值后再由移动端进行订单支付；</li>
                        <li><span>4.</span>请确认以上购买信息，购买完成后将无法进行退款；</li>
                        <li><span>5.</span>购买完成后我们的工作人员会与您联系沟通具体的行程日期安排，请保持手机畅通；</li>
                        <li><span>6.</span>出境及港澳游需相关出行人员提供护照或港澳通行证等有效证件，请确保证件的有效性；</li>
                        <li><span>7.</span>活动最终解释权归北京懒猫金服信息服务有限公司所有；</li>
                    </ul>
                </div>
            </blockquote>
            <blockquote style="display: none">
                <p class="tourTime">出发日期：每周二、四出发</p>
                <div class="tourIntro tourTrip">
                    <h3 class="pr"><i class="icon icon-direction orange pa"></i> <span class="orange">Day 1</span> 北京-香港</h3>
                    <p>参考航班：HX305 07:50-11:30 HX313 20:00-23:55</p>
                    <p>搭乘航班，飞赴美丽的东方之珠—香港。</p>
                    <img src="static/images/tour/Hongkong-Day1.jpg" class="repeatImg">
                    <div class="tourTips"><i class="icon icon-restaurant orange"></i> 自理</div>
                </div>
                <div class="tourIntro tourTrip">
                    <h3 class="pr"><i class="icon icon-direction orange pa"></i> <span class="orange">Day 2</span> 自由活动</h3>
                    <p>全天自由活动，尽享美食。</p>
                    <img src="static/images/tour/Hongkong-Day2.jpg" class="repeatImg">
                    <div class="tourTips"><i class="icon icon-restaurant orange"></i> 自理</div>
                </div>
                <div class="tourIntro tourTrip">
                    <h3 class="pr"><i class="icon icon-direction orange pa"></i> <span class="orange">Day 3</span> 自由活动</h3>
                    <p>全天自由活动，体验人文。</p>
                    <img src="static/images/tour/Hongkong-Day3.jpg" class="repeatImg">
                    <div class="tourTips"><i class="icon icon-restaurant orange"></i> 自理</div>
                </div>
                <div class="tourIntro tourTrip">
                    <h3 class="pr"><i class="icon icon-direction orange pa"></i> <span class="orange">Day 4</span> 自由活动</h3>
                    <p>全天自由活动，购物天堂。</p>
                    <img src="static/images/tour/Hongkong-Day4.jpg" class="repeatImg">
                    <div class="tourTips"><i class="icon icon-restaurant orange"></i> 自理</div>
                </div>
                <div class="tourIntro tourTrip">
                    <h3 class="pr"><i class="icon icon-direction orange pa"></i> <span class="orange">Day 5</span> 香港-北京</h3>
                    <p>参考航班：HX304 20:20-23:40</p>
                    <p>搭乘航班，返回北京。</p>
                    <img src="static/images/tour/Hongkong-banner1.jpg" class="repeatImg">
                    <div class="tourTips"><i class="icon icon-restaurant orange"></i> 自理</div>
                </div>
            </blockquote>
            <blockquote style="display: none">
                <div class="tourIntro">
                    <h3>费用包含</h3>
                    <p>1. 机票：北京至香港往返含税机票。</p>
                    <p> 2. 酒店：3晚酒店住宿(未含早餐）</p>
                    <h3>费用不包含</h3>
                    <p>1. 单房差</p>
                    <p>2. 机场至酒店接送</p>
                    <p>3. 全程用餐</p>
                    <p>4. 因交通延阻、罢工、大风、大雾、航班取消或更改时间等不可抗力原因所引致的额外费用。</p>
                    <p>5. 一切个人消费及价格包含中未提及的任何费用。</p>
                </div>
            </blockquote>
            <blockquote style="display: none">
                <div class="tourIntro">
                    <img src="static/images/tour/Hongkong-Day1.jpg" class="repeatImg">
                    <h3>香港</h3>
                    <p class="indent2">香港是继纽约、伦敦后的世界第三大金融中心，齐称为“纽伦港”，在世界享有极高声誉。素有“东方之珠”、“美食天堂”和“购物天堂”等美誉，也是全球最富裕、经济最发达和生活水准最高的地区之一。</p>
                </div>
            </blockquote>
        </div>
    </div>
    <div class="clearfix"></div>
    <div class="h1-3"></div>
</div>
<div class="btnBot"><input type="button" value="立即购买" class="btnBuy"/></div>
<!--未登录弹出层-->
  <div id="alertLayer-5" class="unloginMask  radius1"
    style="display: none; width: 100%; height: 100%;">
    <div class="pr">
      <img src="static/images/unloginMask.png" class="repeatImg" alt="" />
      <a class="btnClosed font-white pa" href="javascript:void(0)">
        <i class="icon icon-error2" onclick="clean()"></i></a> 
        <a href='account/toRegister?returnFlag=toBuyInvForPro' class="btnReg pa"></a> 
        <a href='account/toLogin?returnUrl=<e:property value="@lastAccessUrl"/>' class="btnLogin pa"></a>
    </div>
  </div>
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
