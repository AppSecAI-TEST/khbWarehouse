<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
    <title>助威奥运拿大奖</title>
</head>
<body>
<div class="olympic2">
    <div class="pr">
        <img src="static/images/activity/olympic2-01.jpg" class="repeatImg"/>
        <img src="static/images/activity/olympic2-02.jpg" class="repeatImg"/>
        <a class="olympicBtn2 pa" href="popularize/toPopularize"></a>
    </div>
    <div class="pr">
        <img src="static/images/activity/olympic2-03.jpg" class="repeatImg"/>
        <div class="progressBarBg pa"><img src="static/images/activity/progressBarBg.png" class="repeatImg"/></div>
        <div class="progressBarLine pa"><img src="static/images/activity/progressBarLine.png" class="repeatImg"/></div>
        <div class="progressBar2 pa">
            <div class="finish2" style=" width: <e:property value="@percent"/>%"></div>
        </div>
        <div class="progressShow pa">
            <div class="progressText pa" style="left:<e:property value="@percent - 16"/>%"><e:property value="@_formater.formatNumber(sumAmount)"/>万</div>
        </div>
    </div>
    <div class="pr">
        <img src="static/images/activity/olympic2-04.jpg" class="repeatImg"/>
        <div class="olympicText pa">
            <p class="indent2">活动期间，购买定期理财（奥运盈、新手标除外)累计金额最多的前20名用户将显示在榜单里，活动截止时，根据榜单最终排名发放奖励（榜单实时更新）。</p>
            <p class="indent2">1.排名前三的用户可获得以下奖品：</p>
        </div>
    </div>
    <div class="pr">
        <img src="static/images/activity/olympic2-05.jpg" class="repeatImg"/>
        <div class="olympicText olympicText1 pa">
            <p class="indent2">2.第4-10名的用户每人将获得100元京东卡一张，第11-20名的用户每人将获得50元京东卡一张。</p>
        </div>
        <!-- 没有榜单按钮不加链接 -->
        <e:if test="@list.size() == 0">
            <a class="olympicBtn2 olympicBtn3 pa" href="javascript:void(0)"></a>
        </e:if>
        <e:else>
            <a class="olympicBtn2 olympicBtn3 pa" href="activity/toLookAwardList"></a>
        </e:else>
        
    </div>
    <div class="pr">
        <img src="static/images/activity/olympic2-06.jpg" class="repeatImg"/>
        <div class="olympicText olympicText2 pa">
            <p class="indent2">活动期间，所有用户在懒猫金服每购买一笔定期理财（奥运盈、新手标除外），就能为助威能量条贡献一份力量，即每产生一笔交易，助威能量条上的金额进度将相应增加。</p>
            <p class="indent2">1.活动截止时，将根据助威能量条达到的总金额，为在活动期间所有购买过定期理财（购买奥运盈、新手标除外）的用户发放该金额对应的奖励。</p>
            <p class="indent2">2.用户累计购买金额每满5000元，将获得一份奖励，购买越多，得到奖励的份数越多。</p>
            <p class="indent2">如用户累计购买金额为53000元，则可以获得奖励的份数为53000÷5000=10.6，取整数10，该用户将获得10份奖励（购买金额中不足5000元倍数的部分将不予奖励）</p>
        </div>
    </div>
    <div class="pr">
        <img src="static/images/activity/olympic2-07.jpg" class="repeatImg"/>
        <div class="zwTable pa">
            <table cellpadding="0" cellspacing="0" width="100%">
                <tbody>
                    <tr>
                        <td width="63%">200万</td>
                        <td>5元投资券</td>
                    </tr>
                    <tr>
                        <td width="63%">400万</td>
                        <td>10元话费</td>
                    </tr>
                    <tr>
                        <td width="63%">666万</td>
                        <td>20元话费</td>
                    </tr>
                    <tr>
                        <td width="63%">888万</td>
                        <td>50元京东卡</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="olympicText olympicText3 pa">
            注：活动截止时，以能量条达到的总金额对应的奖励发放，各总金额节点对应的奖励不叠加。
        </div>
    </div>
    <div class="pr">
        <div class="activityRule">
            <h2>活动规则：</h2>
            <ul>
                <li><span>1、</span>奖励份数根据用户累计购买金额计算，体验金、投资券金额不计算在累计金额内;</li>
                <li><span>2、</span>活动截止时，以助威能量条达到的总金额对应的奖励进行发放，各总金额节点对应的奖励不叠加。</li>
                <li><span>3、</span>购买“奥运盈”、“新手标”的用户不享受此活动的奖励;</li>
                <li><span>4、</span>奖品发放:
                    <p><i class="font-25">·</i> 投资券奖励于活动结束后两个工作日内直接发放到获奖用户注册的懒猫账户，</p>
                    <p><i class="font-25">·</i> 话费奖励于活动结束后三个工作日内直接充值到用户注册懒猫账户的手机号，</p>
                    <p><i class="font-25">·</i> 获得京东卡及实物类奖品的用户需在活动结束后联系懒猫客服留下邮寄地址进行兑奖。联系方式:关注懒猫金服微信公众号(lanmaojf)，在后台留言即可;</p>
                </li>
                <li><span>5、</span>能量进度条若超过888万，将不再针对之后交易的金额发放奖励;
                <li><span>6、</span>用户若获得多份奖励，奖品则合并发放。
                <li><span>7、</span>若用户购买金额相同，则按达成相同金额的时间先后排名;
                <li><span>8、</span>本活动实物类奖品以实物为主，图片仅供参考；
                <li><span>9、</span>本活动最终解释权归北京懒猫金融信息服务有限公司所有。
            </ul>
        </div>
    </div>
    <div class="pr"><img src="static/images/activity/olympic2-08.jpg" class="repeatImg"/></div>
    <div class="pr"><img src="static/images/activity/olympic2Fix.jpg" class="repeatImg"/></div>
    <div class="fixedBtn"><a href="popularize/toPopularize"><img src="static/images/activity/olympic2Fix.jpg" class="repeatImg"/></a></div>
</div>
</body>
</html>