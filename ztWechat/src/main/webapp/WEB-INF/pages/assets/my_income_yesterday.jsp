<%@page import="com.yeepay.g3.app.lmweChat.utils.LmConstants" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<%
  String sysVersion = LmConstants.sysVersion;
%>
<html>
<head lang="en">
   <link rel="stylesheet" href="static/css/assets/my_income_yesterday.css?v=<%=sysVersion %>">
   <link rel="stylesheet" href="static/css/LM-funds.css">
   <script type="text/javascript" src="static/js/iscroll.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/assets/my_income_yesterday_scroll.js?v=<%=sysVersion %>"></script>
   <script type="text/javascript" src="static/js/assets/my_income_yesterday.js?v=<%=sysVersion %>"></script> 
   <title>我的收益</title>
</head>
<body>
<input type="hidden" id="totalPage" value="" />
<input type="hidden" id="pageSize" value="" />
<input type="hidden" id="type" value="<e:property value="@type"/>" />
  <div id="box">
  <div class="bg-white">
        <div class="tabNav fundsNav">
            <ul>
              <li title="total" class="on">总资产收益</li>
	          <li title="scb" >活期收益</li>
	          <li title="fixed" >定期收益</li>
	          <li title="fund" >基金收益</li>
            </ul>
        </div>
    </div>
    <div class="tab_con pb15">
      <blockquote id="totalIncome" style="display: block">
        <div  class="incomeCard bg-white tc">
          <p class="font-14">昨日总收益（元）</p>
          <span class="font-40 orange"><e:property value="@_formater.formatNumber(yesterdayIncome)"/></span>
          </div>
         </blockquote>
         <blockquote id="scbIncome" style="display: none">
         <div  class="incomeCard bg-white tc">
          <p class="font-14">活期理财昨日收益（元）</p>
          <span class="font-40 orange"><e:property value="@_formater.formatNumber(incomeInfoResult)"/></span>
        </div>
          </blockquote>
          <blockquote id="fixedIncome" style="display: none">
         <div class="incomeCard bg-white tc">
          <p class="font-14">定期理财昨日收益（元）<i class="icon icon-questionmark flip"></i></p>
          <span class="font-40 orange"><e:property value="@_formater.formatNumber(fixedIncomeResult)"/></span>
          <div class="panel pa" style="display: none">
                            <span class="profitTip tc pr">
                                <i class="icon-arrow triangle-top"></i>
                                具体产品收益详情请进入【我的懒猫-我的总财富】中查看
                            </span>
                        </div>
        </div>
          </blockquote>
          <blockquote id="fundIncome" style="display: none">
         <div class="incomeCard bg-white tc">
          <p class="font-14">基金理财昨日收益（元）<i class="icon icon-questionmark flip"></i></p>
          <span class="font-40 orange"><e:property value="@_formater.formatNumber(fundYestodayResult)"/></span>
           <div class="panel pa" style="display: none">
                            <span class="profitTip tc pr">
                                <i class="icon-arrow triangle-top"></i>
                                具体基金收益详情请进入【我的懒猫-我的总财富】中查看
                            </span>
                        </div>
        </div>
          </blockquote>
        <div class="layou-04 pt10 pb10" style="dispaly:block">
          <div class="deal-meter">
            <div id="wrapper">
              <div id="scroller"></div>
            </div>
          </div>
        </div>
     
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