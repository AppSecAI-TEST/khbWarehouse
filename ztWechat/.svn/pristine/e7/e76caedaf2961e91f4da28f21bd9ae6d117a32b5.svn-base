<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags"%>
<html>
<head lang="en">
<script type="text/javascript" src="static/js/fixed/freshManNoLogin.js"></script>
<title>新手专享</title>
</head>
<body>
  <input id=productId type="hidden"
    value="<e:property  value="@productId"/>" />
  <input id="status" type="hidden"
    value="<e:property value="@pdfwxrDto.status"/>">
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
        <a href="fixed/toNewManActivity?productId=<e:property value='@productId'/>">
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
    <h2 class="termTitle tc" id="periodNo">
      第
      <e:property value="@pdfwxrDto.periodNo" />
      期
    </h2>
    <div class="bg-white layou-04 mb18 tc">
      <ul class="modList">
        <li id="surplusAmount">产品剩余额度：<e:property
            value="@_formater.formateMoney(pdfwxrDto.surplusAmount)" /> 元
        </li>
      </ul>
    </div>
    <div class="h1-6"></div>
    <div id="messageError"></div>
  </div>
  <div class="btnBot">
    <input id="submit" type="button" onclick="toBuyNoLogin()"
      value="立即购买" class="btnBuy" />
  </div>
  <!--弹出层-->
  <div id="mask" style="display: none"></div>
  <!--未登录弹出层-->
  <div id="alertLayer-5" class="unloginMask  radius1"
    style="display: none; width: 100%; height: 100%;">
    <div class="pr">
      <img src="static/images/unloginMask.png" class="repeatImg" alt="" />
      <a class="btnClosed font-white pa" href="javascript:void(0)"><i
        class="icon icon-error2" onclick="clean()"></i></a> <a
        href="javascript:void(0)" class="btnReg pa"></a> <a
        href="javascript:void(0)" class="btnLogin pa"></a>
    </div>
  </div>
  <a class="service" href="focus/toContactService"><i class="icon icon-service font-white"></i></a>
</body>
</html>