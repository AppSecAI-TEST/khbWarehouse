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
    <title>懒猫送您免费去旅行</title>
</head>
<body>
<div class="travelArea travelArea-1">
    <div class="pr">
        <img src="static/images/tour/travel-2-01.jpg" class="repeatImg"/>
    </div>
    <div class="activityInfo pr">
        <img src="static/images/tour/travel-2-02.jpg" class="repeatImg"/>
        <div class="activityText pa">
            <p class="tc">即日起，购买懒猫金服指定定期理财产品，</p>
            <p class="tc">就可以免费去旅行啦！</p>
            <p class="tc">活动期间，还有一大波福利等着你！</p>
        </div>
        <div class="activityBtn pa">
            <a href="invForPro/toInvestForTravelList"></a>
        </div>
    </div>
    <div class="activityInfo pr">
        <img src="static/images/tour/travel-2-03.jpg" class="repeatImg"/>
        <div class="activityText activityPeriod pa">
            <p class="tl">活动期间，用户选择两人出行时，所需购买的定期理财金额，第一人全额，第二人减半！持有期限到期后，本金原数奉还（两人的出行订单需一起下单支付），仅有十个第二人半价的机会哦。</p>
        </div>
    </div>
    <div class="pr">
        <img src="static/images/tour/travel-2-04.jpg" class="repeatImg"/>
    </div>
    <div class="activityInfo pr">
        <img src="static/images/tour/travel-2-05.jpg" class="repeatImg"/>
        <e:if test="@list!=null&&list.size()!=0">
        <div id="demo" class="scroll activityScroll" style="top:55%;">
            <div class="lotteryScroll">
                <div id="demo1">
                    <ul>
                      <e:iterator list="@list" var="item">
                        <li><e:property value="@_formater.formatDate(item.createTime)"/> <e:property value="@_formater.maskCellphone(item.memberTel)"/> 获得300元京东卡一张</li>
                      </e:iterator>
                        <!-- <li>2016-06-02 136****4686 获得奖品华为P6</li>
                        <li>2016-05-02 136****4686 获得奖品华为P6</li>
                        <li>2016-03-02 136****4686 获得奖品华为P6</li>
                        <li>2016-01-02 136****4686 获得奖品华为P6</li> -->
                    </ul>
                </div>
                <div id="demo2"></div>
            </div>
        </div>
        </e:if>
        <div class="activityText activityPeriod-1 pa">
            <p class="tl">活动期间，用户选择1人出行时，支付成功后，可以获得300元京东卡一张！仅送十张，先到先得！</p>
        </div>
    </div>
    <div class="activityInfo pr">
        <img src="static/images/tour/travel-2-06.jpg" class="repeatImg"/>
        <div class="activityText activityPeriod-2 pa">
            <span class="give fl"><e:property value="@count"/></span>
            <span class="surplus fl"><e:property value="@leftCount"/></span>
        </div>
    </div>
    <div class="activityInfo">
        <div class="activityDescription activityDescription-1">
            <h2>活动规则：</h2>
            <p>活动时间：2016年8月24日-2016年9月7日</p>
            <ul>
                <li>1、十个“第二人半价”名额满时，第二人半价的活动将自动恢复原价，十个名额以用户支付成功时间为准。选择两人一起出行的用户旅行目的地必须相同，并且要在一笔订单里完成交易，分笔进行交易将不能享受第二人半价的优惠。</li>
                <li>2、一人出行送京东卡的活动，前十名获奖者以用户支付成功时间为准。</li>
                <li>3、若用户选择三人出行，则第一人需全额购买理财，第二人理财金额减半，第三人恢复购买理财金额为全额。若用户选择四人出行，则第一、三人需全额购买理财，第二、第四人理财金额减半，以此类推。</li>
                <li>4、指定理财产品需在免费去旅行活动页面购买。</li>
                <li>5、获得京东卡奖品的用户，请关注懒猫金服微信公众号(lanmaojf)，在后台留言即可。</li>
                <li>6、参与“免费去旅行”活动的用户支付成功后，将有客服主动联系您，确认旅行出行事宜。</li>
                <li>7、本活动最终解释权归北京懒猫金融信息服务有限公司所有。</li>
            </ul>
        </div>
    </div>
    <div class="pr">
        <img src="static/images/tour/travel-2-07.jpg" class="repeatImg"/>
    </div>
</div>
<div class="activityFixed">
    <a href="invForPro/toInvestForTravelList">
        <img src="static/images/tour/activityFixed-btn.png" class="repeatImg"/>
    </a>
</div>
<script type="text/javascript">
    var demo = document.getElementById("demo");
    var demo1 = document.getElementById("demo1");
    var demo2 = document.getElementById("demo2");
    if(demo1 != null){
      demo2.innerHTML=document.getElementById("demo1").innerHTML;
    }
    function Marquee(){
      if(demo2 != null){
        if(demo.scrollLeft-demo2.offsetWidth>=0){
            demo.scrollLeft-=demo1.offsetWidth;
        }
        else{
            demo.scrollLeft++;
        }
      }
       
    }
    setInterval(Marquee,30);
</script>
</body>
</html>