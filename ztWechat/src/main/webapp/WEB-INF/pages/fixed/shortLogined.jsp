<%@page import="com.yeepay.g3.app.lmweChat.utils.LmConstants" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<%
  String sysVersion = LmConstants.sysVersion;
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
    <link rel="stylesheet" href="static/css/LM-common.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/LM-app.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/icon-style.css?v=<%=sysVersion %>">
    <script type="text/javascript" src="static/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="static/js/jquery.cloud9carousel.js"></script>
    <script type="text/javascript" src="static/js/jquery.reflection.js"></script>
    <script type="text/javascript" src="static/js/LM-app.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/fixed/shortLogined.js?v=<%=sysVersion %>"></script>
    <title>定期理财--超短期</title>
</head>
<body>
<input id=productId type="hidden" value="<e:property  value="@productId"/>" />
<input id="cillAmount" type="hidden" value="<e:property value="@pdfwxrDto.cillAmount"/>">
<input id="surplusAmount" type="hidden" value="<e:property value="@pdfwxrDto.surplusAmount"/>">
<input id="unitAmount" type="hidden" value="<e:property value="@pdfwxrDto.unitAmount"/>">
<input id="accountAmount" type="hidden" value="<e:property value="@accountAmount"/>">
<input id=isBankCard type="hidden" value="<e:property  value="@isBankCard"/>" />
 <input id="surplus" type="hidden" value="<e:property value="@pdfwxrDto.surplusAmount"/>"> 
 <input id="status" type="hidden"
    value="<e:property value="@pdfwxrDto.status"/>">
<input id="termDay" type="hidden" value="<e:property value="@pdfwxrDto.termDay"/>">
<input id="yearRate" type="hidden" value="<e:property  value="@pdfwxrDto.yearRate"/>" />
<input id="expectIncome" type="hidden" />
<input id="rechargeAmount" type="hidden" value="0" />
<input id="tradeUpper" type="hidden" value="<e:property  value="@pdfwxrDto.tradeUpper"/>"  />
<div id="box" class="bg-white">
    <div class="bg-white proArea">
       <div class="financial-ad">
            <img src="static/images/financial-ad.jpg">
            <a href="javascript:void(0)" class="btnClick pa"></a>
            <a href="javascript:void(0)" class="financialClosed pa"></a>
        </div>
       <!--弹出层-->
        <div id="mask"></div>
        <div id="alertLayer" class="bg-white" style="display: none; width: 90%; min-height: 33%;">
            <div class="withdrawIntro">
                <div class="pb15">定期理财挂钩金融资产收益权。由银行、信托、保险等大型国有金融机构进行风险审查及兑付管理，具备较低风险、较低门槛、多层保障等特点，是您稳健投资的优质选择。</div>
                <div class="tc mt15"><a href="javascript:void(0)" class="btnClosed btn-small">我知道了</a></div>
            </div>
        </div>
        <!--弹出层end-->
        <div class="tab_con">
            <div class="pro-tab font-white clearfix pr" style="width: 90%; margin: 0 auto">
                <ul>
                    <li class="on pro-short">
                      <a href="fixed/productDetailInfo?productId=<e:property value="@productId"/>">
                            <p class="font-16"><e:property value="@pdfwxrDto.termDay"/>天</p>
                            <p class="font-10">年化收益率</p>
                             <p id="yearRateP" class="font-40"><e:property value="@pdfwxrDto.yearRate"/></p>
                            <p class="font-10">查看详情&gt;</p>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="dateBar orange pr">
                <table cellpadding="0" cellspacing="0" width="100%">
                    <tbody>
                    <tr>
                        <th width="25%">购买日</th>
                        <th width="25%">计息日</th>
                        <th width="25%">到期日</th>
                        <th>到账日</th>
                    </tr>
                    <tr>
                  	  <td id="buyDay"><e:property
	                      value="@_formater.formatDate(pdfwxrDto.buyDay)" /></td>
	                  <td id="incomeDay"><e:property
	                      value="@_formater.formatDate(pdfwxrDto.incomeDay)" /></td>
	                  <td id="expireDay"><e:property
	                      value="@_formater.formatDate(pdfwxrDto.expireDay)" /></td>
                            <td><span class="font-10">两工作日后</span></td>
	                <%--   <td id="arrivalDay"><e:property
	                      value="@_formater.formatDate(pdfwxrDto.arrivalDay)" /></td> --%>
                    </tr>
                    </tbody>
                </table>
                <ul class="circleList pa">
                    <li><i class="icon icon-circle"></i></li>
                    <li><i class="icon icon-circle"></i></li>
                    <li><i class="icon icon-circle"></i></li>
                    <li><i class="icon icon-circle"></i></li>
                </ul>
            </div>

        </div>

    </div>

     <h2 class="termTitle"  id="periodNo">第<e:property value="@pdfwxrDto.periodNo"/>期</h2>
    <div class="bg-white layou-04">
      <ul class="modList">
        <li id="surplusAmount">产品剩余额度：<e:property value="@_formater.formateMoney(pdfwxrDto.surplusAmount)"/> 元</li>
         <li class="pb5"><p class="orange">购买金额：<input type="number"
          class="amount-text" id="buyAmount" name=""  onblur="getExpectIncome()"
          placeholder="起购金额<e:property value="@pdfwxrDto.cillAmount"/>元,递增金额<e:property value="@pdfwxrDto.unitAmount"/>元" /> 元</p>
           <p id="messageError" class="modTips moderror font-10"
            style="display: none;">
            <i class="icon icon-error2 font-12"></i>
          </p>
          <!-- 动态显示隐藏单笔购买笔数限额及金额限额 -->
          <e:if test="@pdfwxrDto.tradeUpper!=null&&pdfwxrDto.userMaxNum!=0">
            <p class="modTips font-10"><i class="icon icon-tips font-12"></i>
           本期限购<e:property value="@pdfwxrDto.userMaxNum"/>笔，单笔最高<e:property value="@_formater.formateMoney(pdfwxrDto.tradeUpper)"/>元
          </p>
          </e:if>
         <p class="modTips font-10"><i class="icon icon-tips font-12"></i>
            请直接输入购买金额，账户余额不足将先进行充值
          </p>
        </li>
        <e:if test="@accountAmount==0">
<span id="text"></span>
        </e:if><e:else>
        <li>懒猫账户余额：<e:property value="@_formater.formateMoney(accountAmount)"/> 元</li>
        </e:else>
        <li id="expectIncomeSpan" class="bg-white" style="display: none"> 
        </li>
      </ul>
    </div>
     <div class="h1-6"></div>
    <div id="messageError"></div>
  </div>
<div class="btnBot"><input id="submit" type="button" onclick="toBuy()" value="立即购买" class="btnBuy"/></div>
<a class="service" href="focus/toContactService"><i class="icon icon-service font-white"></i></a>
</body>
</html>