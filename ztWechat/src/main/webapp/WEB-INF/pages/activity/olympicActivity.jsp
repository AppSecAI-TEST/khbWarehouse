<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
    <title>助威奥运狂加息</title>
</head>
<body>
<script type="text/javascript">
$(function(){
  $("#increaseRate").html(format_yearRate($("#increaseRate").html()));
  $("#rate").html(format_yearRate($("#rate").html()));
});
function submit(){
  var productId = $("#productId").val();
  location.href="fixed/toPurchaseProduct?productId="+productId;
}
</script>
<input id="productId" type="hidden" value="<e:property value="@productDetailResultDto.productId"/>"/>
<div class="olympic" style="background: #ffe02e">
    <div class="pr">
        <img src="static/images/activity/olympic1-01.jpg" class="repeatImg"/>
        <div class="medal pa"><e:property value="@olympicMap.addInfo"/><br></div>
    </div>
    <div class="pr">
        <img src="static/images/activity/olympic-02.jpg" class="repeatImg"/>
    </div>
    <div class="pr">
        <img src="static/images/activity/olympic-03.jpg" class="repeatImg"/>
        <div class="yield pa">
            <e:if test="@productDetailResultDto != null">
              <p class="yieldHd">5.0<span>%</span>+<i id="increaseRate"><e:property value="@increaseRate"/></i><span>%</span>＝</p>
              <p class="yieldBt"><i id="rate"><e:property value="@productDetailResultDto.rate"/></i><span>%</span></p>
            </e:if>
            <e:else>
              <p class="yieldHd">5.0<span>%</span>+？<span>%</span>＝</p>
              <p class="yieldBt">？<span>%</span></p>
            </e:else>
            
        </div>
        <!-- 有产品，显示立即抢购 -->
        <e:if test="@productDetailResultDto != null">
          <div id="submit" class="olympicBtn pa" onclick="submit();"><img src="static/images/activity/olympicBtn-1.png" class="repeatImg"/></div>
        </e:if>
        <e:if test="@productDetailResultDto == null">
          <!-- 14:00前显示敬请期待 -->
          <e:if test="@flag == false">
            <div class="olympicBtn pa"><img src="static/images/activity/olympicBtn-2.png" class="repeatImg"/></div>
          </e:if>
          <!-- 14:00后显示已抢完 -->
          <e:else>
            <div class="olympicBtn pa"><img src="static/images/activity/olympicBtn-3.png" class="repeatImg"/></div>
          </e:else>
          <!-- 是否显示下一场开场时间 -->
          <e:if test="@olympicMap.show == 'yes'">
            <div class="olympicTimeArea">
                <div class="olympicTimebg pa"><img src="static/images/activity/olympicTime.png" class="repeatImg"/></div>
                <div class="olympicTime pa">
                    <span><e:property value="@olympicMap.nextMoth"/></span>
                    <span><e:property value="@olympicMap.nextDay"/></span>
                    <span class="date"><e:property value="@olympicMap.nextTime"/></span>
                </div>
            </div>
          </e:if>
        </e:if>
        <!-- <div class="olympicBtn pa"><img src="static/images/activity/olympicBtn-2.png" class="repeatImg"/></div> -->
        <!-- <div class="olympicBtn pa"><img src="static/images/activity/olympicBtn-3.png" class="repeatImg"/></div> -->
    </div>
    <div class="pr">
        <img src="static/images/activity/olympic1-04.jpg" class="repeatImg"/>
        <img src="static/images/activity/olympic1-05.jpg" class="repeatImg"/>
        <img src="static/images/activity/olympic-06.jpg" class="repeatImg"/>
    </div>
    <div class="pr">
        <div class="activityRule pa">
            <h2>活动规则：</h2>
            <ul>
                <li><span>1、</span>前一日中国队所获金牌总数统计时间以里约时间为准；</li>
                <li><span>2、</span>用户当日可进行多次购买，1000元起购；</li>
                <li><span>3、</span>投资券将于购买起两个工作日内发放至用户懒猫账户，购买任意定期理财产品均可使用，奥运盈系列产品除外；</li>
                <li><span>4、</span>奥运盈产品额度有限，售完为止；</li>
                <li><span>5、</span>本次活动最终解释权归北京懒猫金融信息服务有限公司所有。</li>
            </ul>
        </div>
        <img src="static/images/activity/olympic-07.jpg" class="repeatImg"/>
    </div>
    <div class="pr"><img src="static/images/activity/olympic-08.jpg" class="repeatImg"/></div>
</div>
</body>
</html>