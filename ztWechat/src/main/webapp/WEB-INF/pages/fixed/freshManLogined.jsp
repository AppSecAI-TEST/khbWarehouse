<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags"%>
<html>
<head lang="en">
<script type="text/javascript" src="static/js/fixed/freshManLogined.js"></script>
<title>新手专享</title>
</head>
<body>
<input id=isBankCard type="hidden" value="<e:property  value="@isBankCard"/>" />
  <input id="cillAmount" type="hidden"
    value="<e:property value="@pdfwxrDto.cillAmount"/>">
  <input id="unitAmount" type="hidden"
    value="<e:property value="@pdfwxrDto.unitAmount"/>">
  <input id="accountAmount" type="hidden"
    value="<e:property value="@accountAmount"/>">
  <input id="surplus" type="hidden"
    value="<e:property value="@pdfwxrDto.surplusAmount"/>">
  <input id="status" type="hidden"
    value="<e:property value="@pdfwxrDto.status"/>">
  <input id="termDay" type="hidden"
    value="<e:property value="@pdfwxrDto.termDay"/>">
  <input id="yearRate" type="hidden"
    value="<e:property  value="@pdfwxrDto.yearRate"/>" />
  <input id="surplusAmount" type="hidden"
    value="<e:property value="@pdfwxrDto.surplusAmount"/>">
  <input id="expectIncome" type="hidden" />
  <input id="rechargeAmount" type="hidden" value="0" />
  <input id=productId type="hidden"
    value="<e:property  value="@productId"/>" />
  <input id="unBuy" type="hidden" value="<e:property  value="@unBuy"/>" />
  <div id="box" class="bg-white">
    <div class="proArea cardArea">
      <div class="financial-ad">
        <img src="static/images/financial-ad.jpg"> <a
          href="javascript:void(0)" class="btnClick pa"></a> <a
          href="javascript:void(0)" class="financialClosed pa"></a>
      </div>
      <!--弹出层-->
      <div id="mask"></div>
      <div id="alertLayer" class="bg-white"
        style="display: none; width: 90%; min-height: 33%;">
        <div class="withdrawIntro">
          <div class="pb15">定期理财挂钩金融资产收益权。由银行、信托、保险等大型国有金融机构进行风险审查及兑付管理，具备较低风险、较低门槛、多层保障等特点，是您稳健投资的优质选择。</div>
          <div class="tc mt15">
            <a href="javascript:void(0)" class="btnClosed btn-small">我知道了</a>
          </div>
        </div>
      </div>
      <!--弹出层end-->
      <div class="returnCard pr bg-white tc">
        <div class="vouchers1 pa">
          <i class="icon icon-vouchers1 orange"></i> <span class="pa">赠<i
            class="font-20">1</i>%加息券
          </span>
        </div>
        <a id="newManActivity" href="fixed/toNewManActivity?productId=<e:property value='@productId'/>">
          <div class="beginnerRed pa"></div>
        </a>
        <a
          href="fixed/productDetailInfo?productId=<e:property value="@productId"/>"
          class="orange">
          <p class="font-16">预计年化收益率</p>
          <p id="yearRateP" class="font-50">
            <e:property value="@pdfwxrDto.yearRate" />
          </p>
          <p class="font-12">查看详情&gt;</p>
        </a>
        <div class="returnInfo orange br-top">
          <ul>
            <li>期限<span class="font-25"><e:property
                  value="@pdfwxrDto.termDay" /></span>天
            </li>
            <li>起购<span class="font-25"><e:property
                  value="@_formater.formateMoney(pdfwxrDto.cillAmount)" /></span> 元
            </li>
            <li>最高<span class="font-25">1</span>万
            </li>
          </ul>
        </div>
      </div>
      <div class="dateBar orange dateBarSmall pr bg-white clearfix">
        <table cellpadding="0" cellspacing="0" width="90%">
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
              <%--  <td id="arrivalDay"><e:property
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
    <h2 class="termTitle" id="periodNo">
      第
      <e:property value="@pdfwxrDto.periodNo" />
      期
    </h2>
    <div class="bg-white layou-04">
      <ul class="modList">
        <li id="surplusAmount">产品剩余额度：<e:property
            value="@_formater.formateMoney(pdfwxrDto.surplusAmount)" /> 元
        </li>
        <li class="pb5"><p class="orange">
            购买金额：<input type="number" class="amount-text" id="buyAmount"
              name="" onblur="getExpectIncome()"
              placeholder="起购金额<e:property value="@_formater.formateMoney(pdfwxrDto.cillAmount)"/>元,递增金额<e:property value="@_formater.formateMoney(pdfwxrDto.unitAmount)"/>元" />
             元
          </p>
          <p id="messageError" class="modTips moderror font-10"
            style="display: none;">
            <i class="icon icon-error2 font-12"></i>
          </p>
          <p class="modTips font-10">
            <i class="icon icon-tips font-12"></i>请直接输入购买金额，账户余额不足将先进行充值
          </p></li>
        <e:if test="@accountAmount==0">
          <span id="text"></span>
        </e:if>
        <e:else>
          <li>懒猫账户余额：<e:property
              value="@_formater.formateMoney(accountAmount)" /> 元
          </li>
        </e:else>
        <li id="expectIncomeSpan" class="bg-white" style="display: none">
        </li>
      </ul>
    </div>
    <div class="h1-6"></div>
  </div>
  <div class="btnBot">
    <e:if test="@'NO'.equals(unBuy)">
      <input id="submit" type="button" disabled="disabled"
        value="仅限首次购买定期理财用户" class="btnBuy btnBuy-gray" />
    </e:if>
    <e:else>
      <input id="submit" type="button" onclick="toBuy()" value="立即购买"
        class="btnBuy" />
    </e:else>
  </div>
  <a class="service" href="focus/toContactService"><i class="icon icon-service font-white"></i></a>
</body>
</html>