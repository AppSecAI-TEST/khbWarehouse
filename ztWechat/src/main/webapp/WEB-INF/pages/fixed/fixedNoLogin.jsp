<%@page import="com.yeepay.g3.app.lmweChat.utils.LmConstants" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<%
  String sysVersion = LmConstants.sysVersion;
%>
<html>
<head lang="en">
<link rel="stylesheet" href="static/css/swiper.min.css">
<script type="text/javascript" src="static/js/common/fixed_common.js?v=<%=sysVersion %>"></script>
<script type="text/javascript" src="static/js/swiper.min.js?v=<%=sysVersion %>"></script>
<%-- <script type="text/javascript" src="static/js/Swiper.js?v=<%=sysVersion %>"></script> --%>
<script type="text/javascript" src="static/js/fixed/fixedNoLogin.js?v=<%=sysVersion %>"></script>
<title>定期理财</title>
</head>
<body>
  <input id="cillAmount" type="hidden"
    value="<e:property value="@pdfwxrDto.cillAmount"/>">
  <input id="unitAmount" type="hidden"
    value="<e:property value="@pdfwxrDto.unitAmount"/>">
  <input id="accountAmount" type="hidden"
    value="<e:property value="@accountAmount"/>">
  <input id="surplus" type="hidden"
    value="<e:property value="@pdfwxrDto.surplusAmount"/>">
  <input id="termDay" type="hidden"
    value="<e:property value="@pdfwxrDto.termDay"/>">
  <input id="yearRate" type="hidden"
    value="<e:property  value="@pdfwxrDto.yearRate"/>" />
  <input id="status" type="hidden"
    value="<e:property value="@pdfwxrDto.status"/>">
  <input id="firstColumn" type="hidden"
    value="<e:property  value="@firstColumn"/>" />
  <input id=productId type="hidden"
    value="<e:property  value="@productId"/>" />
    
    <div class="bg-white">
    <div class="proSlider">
        <div class="slider">
       		<div class="financial-ad">
		        <img src="static/images/financial-ad.jpg" class="repeatImg"> <a
		          href="javascript:void(0)" class="btnClick pa"></a> <a
		          href="javascript:void(0)" class="financialClosed pa"></a>
	      	</div>
            <div class="slider-extra">
                <ul class="slider-nav">
                	<e:iterator list="@productList" var="items">
                		<li class="slider-item" data-proid='<e:property value="@items.productId"/>'><e:property value="@items.termDay"/>天</li>
                	</e:iterator>
                </ul>
            </div>
            <div class="sliderArea">
                <ul class="slider-main">
                    <e:iterator list="@productList" var="items">
	                    <li class="slider-panel">
	                        <div class="product-content">
	                            <h3><e:property value="@items.productName"/> - <span class="periodNo">第<e:property value="@pdfwxrDto.periodNo" />期</span></h3>
	                            <div class="energy-ball pr">
	                                <img src="static/images/energy-ball.jpg" alt=""/>
	                                <div class="energy-text">
	                                    <p class="orange p-1"><e:property value="@items.yearRate"/><span class="orange">%</span></p>
	                                    <p class="p-2 orange">预计年化收益率</p>
	                                    <p class="p-3 font-white">
	                                       <a href='fixed/productDetailInfo?productId=<e:property value="@items.productId"/>'>查看详情</a>
	                                    </p>
	                                </div>
	                                <div class="slider-page pa">
	                                    <a class="slider-pre" href="javascript:;;"><img src="static/images/slider-pre.png" alt=""/></a>
	                                    <a class="slider-next" href="javascript:;;"><img src="static/images/slider-next.png" alt=""/></a>
	                                </div>
	                            </div>
	                            <div class="scale pr">
	                                <img src="static/images/scale.png" alt=""/>
	                                <table class="pa">
	                                    <tr class="date-text">
	                                        <td width="25%">购买日</td>
	                                        <td width="25%">计息日</td>
	                                        <td width="25%">到期日</td>
	                                        <td>到账日</td>
	                                    </tr>
	                                    <tr class="date-num">
	                                        <td width="25%" class="buyDay"><e:property
				                      value="@_formater.formatDate(pdfwxrDto.buyDay)" /></td>
	                                        <td width="25%" class="incomeDay"><e:property
				                      value="@_formater.formatDate(pdfwxrDto.incomeDay)" /></td>
	                                        <td width="25%" class="expireDay"><e:property
				                      value="@_formater.formatDate(pdfwxrDto.expireDay)" /></td>
	                                        <td>两个工作日后</td>
	                                    </tr>
	                                </table>
	                            </div>
	                        </div>
	                        <div class="investment-cap mt10">产品剩余额度: <e:property value="@_formater.formateMoney(items.surplusAmount)"/> 元</div>
	                    </li>
                    </e:iterator>
                </ul>
            </div>
        </div>
    </div>
    
    
    <%-- <div class="bg-white layou-04">
        <ul class="modList modList1">
            <li class="pb5">
                <p class="orange">购买金额：<input type="number"
          class="amount-text" id="buyAmount" name=""
          onblur="getExpectIncome()"
          placeholder="起购金额<e:property value="@_formater.formateMoney(pdfwxrDto.cillAmount)"/>元,递增金额<e:property value="@_formater.formateMoney(pdfwxrDto.unitAmount)"/>元" />元</p>
                <p id="messageError" class="modTips font-10" style="display: none;"><i class="icon icon-error2 font-12"></i></p>
                <p class="modTips font-10"><i class="icon icon-tips font-12"></i> 请直接输入转入金额，账户余额不足将先进行充值</p>
            </li>
            <!-- 优惠券start -->
            <li id="voucher" class="orange " onclick="getVouchersInfo()"
	          style="display: none"></li>
	        <li id="modVouchered" class="modVouchered" style="display: none"></li>
	         <!-- 优惠券end -->
            <e:if test="@accountAmount==0">
	        </e:if>
	        <e:else>
	          <li>懒猫账户余额：<e:property
	              value="@_formater.formateMoney(accountAmount)" /> 元
	          </li>
	        </e:else>
            <li id="expectIncomeSpan" style="display: none">预计到账收益 <span class="amountA orange">500.00</span> 元</li>
        </ul>
    </div> --%>
    <div class="h1-6"></div>
</div>
    
    
    
    
    
  <%-- <div id="box" class="bg-white">
    <div class="bg-white proArea">
      <div class="financial-ad">
        <img src="static/images/financial-ad.jpg"> <a
          href="javascript:void(0)" class="btnClick pa"></a> <a
          href="javascript:void(0)" class="financialClosed pa"></a>
      </div>
      <div class="tab_li tabs">
            <ul class="tab_nav">
                <li id="month" class="on">月月盈</li>
                <li id="quarter">季季盈</li>
                <li id="year">年年盈</li>
            </ul>
        </div>
      <div id="tabs-container" class="swiper-container">
            <div class="swiper-wrapper">
                <div class="swiper-slide">
                    <div class="content-slide">
                        <div class="pro-tab font-white clearfix pr" style="margin: 0 auto">
                            <ul id="show_day" class="showQiuQiu">
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
				                  <td id="buyDay_MONY"><e:property
				                      value="@_formater.formatDate(pdfwxrDto.buyDay)" /></td>
				                  <td id="incomeDay_MONY"><e:property
				                      value="@_formater.formatDate(pdfwxrDto.incomeDay)" /></td>
				                  <td id="expireDay_MONY"><e:property
				                      value="@_formater.formatDate(pdfwxrDto.expireDay)" /></td>
				                  <td><span class="font-10">两工作日后</span></td>
				                   <td id="arrivalDay_MONY"><e:property
				                      value="@_formater.formatDate(pdfwxrDto.arrivalDay)" /></td>
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
                <div class="swiper-slide">
                    <div class="content-slide">
                        <div class="pro-tab font-white tc clearfix pr">
                            <ul id="show_month" class="showQiuQiu">
                                
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
				                  <td id="buyDay_QUAY"><e:property
				                      value="@_formater.formatDate(pdfwxrDto.buyDay)" /></td>
				                  <td id="incomeDay_QUAY"><e:property
				                      value="@_formater.formatDate(pdfwxrDto.incomeDay)" /></td>
				                  <td id="expireDay_QUAY"><e:property
				                      value="@_formater.formatDate(pdfwxrDto.expireDay)" /></td>
				                  <td><span class="font-10">两工作日后</span></td>
				                   <td id="arrivalDay_QUAY"><e:property
				                      value="@_formater.formatDate(pdfwxrDto.arrivalDay)" /></td>
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
                <div class="swiper-slide">
                    <div class="content-slide">
                        <div class="pro-tab font-white tc clearfix pr">
                            <ul id="show_year" class="showQiuQiu">
                                
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
				                  <td id="buyDay_YEAY"><e:property
				                      value="@_formater.formatDate(pdfwxrDto.buyDay)" /></td>
				                  <td id="incomeDay_YEAY"><e:property
				                      value="@_formater.formatDate(pdfwxrDto.incomeDay)" /></td>
				                  <td id="expireDay_YEAY"><e:property
				                      value="@_formater.formatDate(pdfwxrDto.expireDay)" /></td>
				                  <td><span class="font-10">两工作日后</span></td>
				                   <td id="arrivalDay_YEAY"><e:property
				                      value="@_formater.formatDate(pdfwxrDto.arrivalDay)" /></td>
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
            </div>
      </div>
      
      
      
      
      
      <div class="tab_con">
        <blockquote style="display: block">
          <div class="pro-tab font-white tc clearfix pr">
            <ul id="list">
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
                   <td id="arrivalDay"><e:property
                      value="@_formater.formatDate(pdfwxrDto.arrivalDay)" /></td>
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
        </blockquote>
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
  </div> --%>
  <div class="btnBot">
    <input id="submit" type="button" onclick="toBuyNoLogin()"
      value="立即购买" class="btnBuy" />
  </div>
  <!--弹出层-->
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
  <div id="alertLayer-8" class="unloginMask regMask rechargeMask"
    style="display: none; width: 100%; height: 40%;">
    <div class="pr">
      <img src="static/images/errorMask.png" class="repeatImg" alt="" />
      <a class="btnClosed font-white pa" href="javascript:void(0)"><i
        class="icon icon-error2" onclick="clean()"></i></a>
      <p class="errorCon red pa">系统异常，请稍后重试哦</p>
      <div class="btnMaskArea tc pa">
        <a href="javascript:void(0)" onclick="clean()">OK</a>
      </div>
    </div>
  </div>
  <a class="service" href="focus/toContactService"><i class="icon icon-service font-white"></i></a>
</body>
</html>