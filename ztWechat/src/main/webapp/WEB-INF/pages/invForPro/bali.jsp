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
    <title>免费旅游-产品详情页</title>
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
            {content: "<a href='javaScript:void(0)'><img src='static/images/tour/tourBanner-01.jpg'></a>"},
            {content: "<a href='javaScript:void(0)'><img src='static/images/tour/tourBanner-02.jpg'></a>"}
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
        <p class="orange font-16"><e:property value="@invForProInfoDto.name"/></p>
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
                <p class="tourTime">出发日期：9月4日，9月18日</p>
                <div class="tourIntro tourTrip">
                    <h3 class="pr"><i class="icon icon-direction orange pa"></i> <span class="orange">Day 1</span> 北京-香港-巴厘岛</h3>
                    <p>参考航班：HX305 07:50-11:30 转HX709 19:15-00:25+1</p>
                    <p>搭乘航班，飞赴印度尼西亚最令人陶醉的度假胜地—巴厘岛。</p>
                    <img src="static/images/tour/bld-Day1.jpg" class="repeatImg">
                    <div class="tourTips"><i class="icon icon-restaurant orange"></i> 晚（据当日具体航班时间调整）</div>
                </div>
                <div class="tourIntro tourTrip">
                    <h3 class="pr"><i class="icon icon-direction orange pa"></i> <span class="orange">Day 2</span> 海龟岛~南湾~乳胶厂~情人崖~洋洋下午茶~猫屎咖啡工厂~金巴兰海滩</h3>
                    <p>海龟岛-乘玻璃底船前往，亲密接触可爱的野生动物。</p>
                    <p>南湾水上中心-大玩特玩的水上活动，种类之多，可称世界之冠。</p>
                    <p>乌鲁瓦图情人崖-这里一望无际的海天一色，景色绝美。</p>
                    <p>金巴兰海滩-以海上日落著称，傍晚看着落日，享用烛光晚餐、海鲜烧烤。</p>
                    <img src="static/images/tour/bld-Day2.jpg" class="repeatImg">
                    <div class="tourTips"><i class="icon icon-restaurant orange"></i> 早：酒店自助 午：俱乐部午餐 晚：金巴兰BBQ</div>
                </div>
                <div class="tourIntro tourTrip">
                    <h3 class="pr"><i class="icon icon-direction orange pa"></i> <span class="orange">Day 3</span> 乌布~海神庙~土特产店~dfs免税店</h3>
                    <p>乌布市场-乌布是巴厘岛绘画和艺术重镇</p>
                    <p>乌布皇宫-处处的金箔装饰让整个气氛更显辉煌。</p>
                    <p>海神庙-是巴厘岛最重要的海边庙宇之一，始建于16世纪。</p>
                    <p>DFS免税店-免税店的里的品牌众多，可挑选心意礼品。</p>
                    <img src="static/images/tour/bld-Day3.jpg" class="repeatImg">
                    <div class="tourTips"><i class="icon icon-restaurant orange"></i> 早：酒店自助 午：脏鸭餐 晚：派对晚餐</div>
                </div>
                <div class="tourIntro tourTrip">
                    <h3 class="pr"><i class="icon icon-direction orange pa"></i> <span class="orange">Day 4</span> 自由活动</h3>
                    <p>今日全天自由活动，随心畅享自由人的精彩，惬意陶醉于巴厘岛上的各项风景，度过这惬意时光。</p>
                    <img src="static/images/tour/bld-Day4.jpg" class="repeatImg">
                    <div class="tourTips"><i class="icon icon-restaurant orange"></i> 自理</div>
                </div>
                <div class="tourIntro tourTrip">
                    <h3 class="pr"><i class="icon icon-direction orange pa"></i> <span class="orange">Day 5</span> 自由活动</h3>
                    <p>白天自由活动，根据航班时间前往巴厘岛登巴萨国际机场办理值机手续。</p>
                    <img src="static/images/tour/bld-Day5.jpg" class="repeatImg">
                    <div class="tourTips"><i class="icon icon-restaurant orange"></i> 自理</div>
                </div>
                <div class="tourIntro tourTrip">
                    <h3 class="pr"><i class="icon icon-direction orange pa"></i> <span class="orange">Day 6</span> 巴厘岛-香港-北京</h3>
                    <p>参考航班：HX708 01:25-06:30 转HX336 07:40-10:30</p>
                    <p>搭乘飞机返回，丰富的旅程圆满结束，回到温暖的家。</p>
                    <img src="static/images/tour/bld-Day6.jpg" class="repeatImg">
                    <div class="tourTips"><i class="icon icon-restaurant orange"></i> 自理</div>
                </div>
            </blockquote>
            <blockquote style="display: none">
                <div class="tourIntro">
                    <h3>费用包含</h3>
                    <p>1. 机票：北京-巴厘岛往返含税机票</p>
                    <p>2. 住宿：巴厘岛当地精品酒店住宿4晚（含早餐）</p>
                    <p>3. 用车：接送机及两日游行程用车及司机小费</p>
                    <p>4. 导游：当地中文导游服务及小费</p>
                    <p>5. 用餐：行程所列餐点4早4正（自理餐点除外）</p>
                    <p>6. 行程所列项目门票及套餐（自理项目除外）</p>
                    <h3>费用不含</h3>
                    <p>1. 自由活动期间所以费用</p>
                    <p>2. 各项目点服务人员小费</p>
                    <p>3. 各用餐固定餐点外的额外点餐，所产生的费用</p>
                    <p>4. 套餐所含项目之外开销费用</p>
                    <p>5. 需自理自费的项目、餐点</p>
                    <p>6. 推荐一日游的相关费用</p>
                    <p>7. 对酒店或公共设施造成的损害赔偿</p>
                    <p>8. 出入境个人物品海关征税，超重行了的托运费、保管费</p>
                    <p>9. 因交通延阻、罢工、天气、飞机、机器故障、航班取消或更改时间等不可抗原因所导致的额外费用</p>
                    <p>10. 单房差、加床费</p>
                </div>
            </blockquote>
            <blockquote style="display: none">
                <div class="tourIntro">
                    <img src="static/images/tour/tourism02.jpg" class="repeatImg">
                    <h3>巴厘岛</h3>
                    <p class="indent2">幽蓝海水绵白细沙，巴厘岛，就像经线纬线一样，定义了世界上“人间天堂”的存在。 巴厘岛被称作“神明之岛”、“恶魔之岛”、“天堂之岛”、“魔幻之岛”、“花之岛”等。</p>
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
