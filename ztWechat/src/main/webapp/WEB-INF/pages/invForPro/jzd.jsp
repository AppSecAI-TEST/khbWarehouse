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
    <title>免费旅游-济州岛产品详情</title>
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
            {content: "<a href='javaScript:void(0)'><img src='static/images/tour/jzd-banner1.jpg'></a>"},
            {content: "<a href='javaScript:void(0)'><img src='static/images/tour/jzd-banner2.jpg'></a>"}
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
        <p class="orange font-16">韩国济州岛4晚5天跟团游</p>
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
                <p class="tourTime">出发日期：当前日期至9月30日</p>
                <div class="tourIntro tourTrip">
                    <h3 class="pr"><i class="icon icon-direction orange pa"></i> <span class="orange">Day 1</span> 天津-济州岛</h3>
                    <p>天津集合：下午13点在天津滨海机场1号航站楼集合。</p>
                    <p>天津滨海国际机场 -> 济州国际机场BK2717。</p>
                    <p>请各位游客于天津滨海国际机场集合，搭乘国际航班直飞素有“韩国蜜月岛”之称的济州岛，导游接机后，享受特色【汗蒸幕】，入住酒店休息。</p>
                    <img src="static/images/tour/jzd-Day1.jpg" class="repeatImg">
                    <div class="tourTips"><i class="icon icon-restaurant orange"></i> 晚（据当日具体航班时间调整）</div>
                </div>
                <div class="tourIntro tourTrip">
                    <h3 class="pr"><i class="icon icon-direction orange pa"></i> <span class="orange">Day 2</span> </h3>
                    <p>城山日出峰~城邑民俗村~中文海水浴场~思索之苑~涂鸦秀</p>
                    <img src="static/images/tour/jzd-Day2.jpg" class="repeatImg">
                    <div class="tourTips"><i class="icon icon-restaurant orange"></i> 早 中 晚</div>
                </div>
                <div class="tourIntro tourTrip">
                    <h3 class="pr"><i class="icon icon-direction orange pa"></i> <span class="orange">Day 3</span> </h3>
                    <p>高丽参专卖店~化妆品专卖店~护肝宝店~神奇之路~汉拿林木园~3D趣味立体影像展示馆+冰雕冰雪世界体验馆~新罗免税店~莲洞步行街</p>
                    <img src="static/images/tour/jzd-Day3.jpg" class="repeatImg">
                    <div class="tourTips"><i class="icon icon-restaurant orange"></i> 早 中 晚</div>
                </div>
                <div class="tourIntro tourTrip">
                    <h3 class="pr"><i class="icon icon-direction orange pa"></i> <span class="orange">Day 4</span> 整天自由活动</h3>
                    <img src="static/images/tour/jzd-Day4.jpg" class="repeatImg">
                    <div class="tourTips"><i class="icon icon-restaurant orange"></i> 自理</div>
                </div>
                <div class="tourIntro tourTrip">
                    <h3 class="pr"><i class="icon icon-direction orange pa"></i> <span class="orange">Day 5</span></h3>
                    <p>龙头岩~药泉寺~泰迪熊博物馆~乐天DFS~土特产</p>
                    <p>集合驱车前往机场由领队带着贵宾搭乘航班BK2718返回天津，结束愉快行程！</p>
                    <p>北京客人乘坐大巴返回北京。</p>
                    <img src="static/images/tour/jzd-Day5.jpg" class="repeatImg">
                    <div class="tourTips"><i class="icon icon-restaurant orange"></i> 早（据当日具体航班时间调整）</div>
                </div>
            </blockquote>
            <blockquote style="display: none">
                <div class="tourIntro">
                    <h3>费用包含</h3>
                    <p>1. 往返团队机票含税</p>
                    <p>2. 景区的首道门票</p>
                    <p>3. 当地四花酒店住宿</p>
                    <p>4. 免签</p>
                    <p>5. 全程领队、中文领队陪同服务</p>
                    <p>6. 旅游团队桌餐，不含酒水</p>
                    <p>7. 当地空调旅行车</p>
                    <p>8. 旅行社责任险</p>
                    <h3>费用不包含:</h3>
                    <p>1. 护照制证及相关费用</p>
                    <p>2. 行程以外的一切自费项目及个人消费</p>
                    <p>3. 人身意外保险</p>
                    <p>4. 交通延阻、罢工及其人力不可抗拒的因素所引致的额外费用</p>
                    <p>5. 酒店单房差</p>
                </div>
            </blockquote>
            <blockquote style="display: none">
                <div class="tourIntro">
                    <img src="static/images/tour/jzd-Day1.jpg" class="repeatImg">
                    <h3>济州岛</h3>
                    <p class="indent2">海洋性气候的济州岛素有“韩国的夏威夷”之称。美丽的济州岛不仅具有海岛独特的美丽风光（瀛州十景），而且还继承了古耽罗王国特别的民俗文化。</p>
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
