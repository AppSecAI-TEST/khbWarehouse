<%@page import="com.yeepay.g3.app.lmweChat.utils.LmConstants" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
 <%@ taglib prefix="e" uri="/emvc-tags" %>
 <%
 String path = request.getContextPath();
 String basePath = request.getScheme() + "://"+ request.getServerName(); 
 if(request.getServerPort()!=80){
   basePath = basePath + ":" + request.getServerPort(); 
 }      
 basePath = basePath + path + "/";
 String sysVersion = LmConstants.sysVersion;
%>
<!DOCTYPE html>
<html>
<head lang="en">
<base href="<%=basePath%>" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--优先使用 IE 最新版本和 Chrome-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, maximum-scale=1.0, initial-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <!--设置苹果工具栏颜色-->
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="static/css/LM-common.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/LM-app.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/icon-style.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/LM-invest.css?v=<%=sysVersion %>">
    <script type="text/javascript" src="static/js/jquery-1.8.3.min.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/LM-app.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/LM-invest.js?v=<%=sysVersion %>"></script>
    <title>常见问题</title>
</head >
<body >
<div class="bg-white">
    <div class="questionList">
        <ul>
            <li>
                <span class="dot pa"><i></i></span>
               <span class="quesTitle">1、“灵机一投”需要每月定投吗？和传统定投有什么区别？</span>
                <i class="trigger trigger-bottom"></i>
                <div class="quesCon none">
                   需要的。我们会根据您需要完成的心愿，为您制定投资计划，计划中包含您每月投资额和预期未来市值区间。并且建议您每月投资。<br/>
但与传统定投区别在于：<br/>
传统基金定投策略中，用户于每月固定的时间以固定的金额投资于固定的组合中。每月投资到组合中各只基金的比例保持不变。在“灵机一投”产品中，您可根据自己现金盈余的情况随时进行投资，系统也会实时跟踪您的投资计划的完成进度，对您的投资计划进行调整。可以助您实现强制储蓄的目的，并实现收益最大化。
                </div>
            </li>
            <li>
                <span class="dot pa"><i></i></span>
                <span class="quesTitle">2、“灵机一投”的动态策略是什么？</span>
                <i class="trigger trigger-bottom"></i>
                <div class="quesCon none">
                   您在首次投资时，每只基金的申购金额会根据策略中各基金占比分配。<br/>
在您每次追加投资时，系统会自动计算您持有的基金市值表现，时时为您调整每次追加的投资金额的股票基金比例，保持策略中股票型基金的初始比例。<br/>当股市上涨的时候，减少股票基金投资，降低投资成本；<br/>当股市下跌的时候，增加股票基金投资，以获得更多的份额。<br/>通过不断的调整每次投资的比例，在相同的投资周期和投资金额的前提下，灵机一投将比传统基金定投业务获得更多的基金份额，并获得更好的投资收益。
                </div>
            </li>
            <li>
                <span class="dot pa"><i></i></span>
               <span class="quesTitle"> 3、“灵机一投”都有哪些费用？除基金相关费用外，还有额外费用吗？</span>
                <i class="trigger trigger-bottom"></i>
                <div class="quesCon none">
                    灵机一投申购、赎回只按基金公司公告收取手续费，无额外费用。
                </div>
            </li>
            <li>
                <span class="dot pa"><i></i></span>
             <span class="quesTitle">  4、“灵机一投”是如何做到优选基金的？</span>
                <i class="trigger trigger-bottom"></i>
                <div class="quesCon none">
                    通过大数据的形式全市场采集各类专业机构对2000多支公募基金的评级结果，优中选优，并对策略中基金风格进行深度解析，最大限度地降低基金的相关性。
                </div>
            </li>
            <li>
                <span class="dot pa"><i></i></span>
               <span class="quesTitle">5、“灵机一投”可以随时申购和赎回吗？</span>
                <i class="trigger trigger-bottom"></i>
                <div class="quesCon none">
                    可以，您可根据自己现金盈余的情况随时进行申购。申购后已确认的份额，可以随时进行赎回。
                </div>
            </li>
            <li>
                <span class="dot pa"><i></i></span>
            <span class="quesTitle">  6、“灵机一投”有风险吗 </span>
                <i class="trigger trigger-bottom"></i>
                <div class="quesCon none">
                   基金不承诺保本和收益。市场有风险，投资需谨慎。
                </div>
            </li>
            <li>
                <span class="dot pa"><i></i></span>
              <span class="quesTitle"> 7、注意事项</span>
                <i class="trigger trigger-bottom"></i>
                <div class="quesCon none">
                    灵机一投包含了两大投资策略。第一：在进行投资的时候，分期分批投入可以分摊投资成本，进而达到分散风险平抑波动的目的。第二：在投资的时候切莫追涨杀跌，而应高抛低吸，但是人性的恐惧和贪婪往往会带来错误的投资决策，因此灵机一投采用系统化的算法和机器约束，来克服人性的弱点。<br/>
如果您选择了灵机一投产品，那么需要您本着长期投资，定期投资的理念，切莫中途放弃，多年以后，您会为您今天的决定点赞！
                </div>
            </li>
            <li>
                <span class="dot pa"><i></i></span>
             <span class="quesTitle"> 8、相关名词解释：</span>
                <i class="trigger trigger-bottom"></i>
                <div class="quesCon none">
                    过去*年回报率=（过去*年每月投资该策略至今的总市值/过去*年总投资额-1）*100%<br/>
（假设条件：过去*年的定投时间均为当月的第一个交易日，每月投资的金额相同。）<br/>
过去*年最大回撤率=过去*年的定投中曾经达到过的最大亏损率<br/>
（假设条件：过去*年的定投时间均为当月的第一个交易日，每月投资的金额相同。）<br/>
预期未来总市值：根据当前配置基金过去拟投资期内回报率和最大回撤率预测未来的盈亏表现。由于股票市场的不确定性，故这里给出了分布范围（数值并不代表实际许诺收益，还请用户注意）
                </div>
            </li>
        </ul>
    </div>
</div>

</body>
</html>