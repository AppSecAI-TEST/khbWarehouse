<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags"%>
<html>
<head lang="en">
    <title>信托理财产品详情</title>
</head>
<body>
    <div class="bg-white">
      <div class="titleInfo br-bottom">
        <h2 class="titleSafe orange">产品介绍</h2>
      </div>
      <div class="prolastCon" >
        <div class="prolastInfo orange">
          <table cellpadding="0" cellspacing="0" width="100%">
            <tbody>
              <tr>
                <th class="33%"><span class="font-25"><e:property
                      value="@_formater.formatNumber(yearRate)" /></span>%</th>
                <th class="33%"><span class="font-25"><e:property
                      value="@termDay" /></span>天</th>
                <th rowspan="2"><span class="proInterest">保障<br />措施
                    <i class="icon icon-questionmark orange btnClick"></span></th>
              </tr>
              <tr>
                <td>年化收益率</td>
                <td>持有期限</td>
              </tr>
            </tbody>
          </table>
        </div>
       <table cellpadding="0" cellspacing="0" class="prolastTable prolastTable2" width="100%">
          <tbody>
            <e:if test="@'float'.equals(type)">
              <tr>
                <th width="33%">产品规模</th>
                <td><e:property
                    value="@_formater.formateMoney(totalAmount)" /> 元</td>
              </tr>
              <tr>
                <th width="33%">开售时间</th>
                <td><e:property
                    value="@_formater.formatDate(raiseStart)" /></td>
              </tr>
            </e:if>
            <tr>
              <th width="33%">回款方式</th>
              <td>到期一次性还本付息 <i class="icon icon-questionmark orange btnClick-3"></td>
            </tr>
            <tr>
              <th width="33%">起息日期</th>
              <td id="incomeDay"><e:property
                  value="@_formater.formatDate(pdfwxrDto.incomeDay)" /></td>
            </tr>
            <tr>
              <th width="33%">到期日期</th>
              <td id="expireDay"><e:property
                  value="@_formater.formatDate(pdfwxrDto.expireDay)" /></td>
            </tr>
            <tr>
              <th width="33%">到账日期</th>
              <td>到期后两个工作日</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="bg-white mt5">
      <div class="bg-white mt15">
        <div class="titleInfo br-bottom">
            <h2 class="titleSafe orange">产品详情 <span class="gray">/</span> 购买记录</h2>
        </div>
        <div class="prolastCon">
            <e:if test="@platform == 'APP'">
                <p class="font-18">更多信息请 <a href="account/toLogin?returnUrl=fixed/productDetailInfo?productId=<e:property value="@productId"/>" class="orange">登录</a> 后查看</p>
            </e:if>
            <e:else>
                <p class="font-18">更多信息请 <a href="account/toLogin?returnUrl=fixed/productDetailInfo?productId=<e:property value="@productId"/>" class="orange">登录</a> 或 <a href="account/toRegister?returnFlag=toProDetail&productId=<e:property value="@productId"/>" class="orange">注册</a> 后查看</p>
            </e:else>
            
        </div>
    </div>
    </div>
  <!--弹出层-->
  <div id="mask"></div>
  <div id="alertLayer" class="bg-white"
    style="display: none; width: 90%; min-height: 30%;">
    <h2 class="alertTitle orange br-bottom tc">保障措施</h2>
    <div class="withdrawIntro">
      <e:iterator list="@listInfo" var="itmes">
        <p class="pr">
          <i class="icon icon-circle orange pa"></i>
          <e:property value="@itmes" />
        </p>
      </e:iterator>
      <div class="tc mt15">
        <a href="javascript:void(0)" class="btnClosed btn-small">我知道了</a>
      </div>
    </div>
  </div>
  <div id="alertLayer-3" class="bg-white"
    style="display: none; width: 90%; min-height: 30%;">
    <h2 class="alertTitle orange br-bottom tc">一次性本息回款</h2>
    <div class="withdrawIntro">
      <div>
        持有产品到期后，<span class="orange">本金</span>和<span class="orange">收益</span>会自动的<span
          class="orange">一次性</span>返还到您的懒猫账户中。
      </div>
      <div class="tc mt30">
        <a href="javascript:void(0)" class="btnClosed btn-small">我知道了</a>
      </div>
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
  <!--弹出层end-->
</body>
</html>