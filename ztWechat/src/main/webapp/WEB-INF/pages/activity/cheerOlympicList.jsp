<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> --%>
<html>
<head lang="en">
    <title>实时助威榜</title>
</head>
<body>
<script type="text/javascript">
  $(function(){
   	var sumAmountList = $('.sumAmount');
   	for(var i=0;i<sumAmountList.length;i++){
   	 sumAmountList.eq(i).html(parseInt(sumAmountList.eq(i).html()));
   	}
  });
	
</script>
<div id="box" class="olympic2" style=" background: #a1d319">
    <div class="pr">
        <img src="static/images/activity/olympicList-01.jpg" class="repeatImg"/>
    </div>
    <div class="pr">
        <img src="static/images/activity/olympicList-02.jpg" class="repeatImg"/>
        <div class="zwTable olympicListTable pa">
            <table cellpadding="0" cellspacing="0" width="100%">
                <tbody>
                  <e:iterator list="@list" var="items">
                    <tr>
                        <td width="20%">
                          <e:if test="@items.rank <= 3">
                            <span class="num num0<e:property value="@items.rank"/>">
                              <e:property value="@items.rank"/>
                            </span>
                          </e:if>
                          <e:else>
                            <e:property value="@items.rank"/>
                          </e:else>
                        </td>
                        <td width="40%"><e:property value="@items.phoneNo"/></td>
                        <td><i class="sumAmount"><e:property value="@_formater.formatNumber(items.sumAmount)"/></i>元</td>
                    </tr>
                  </e:iterator>
                </tbody>
            </table>
        </div>
    </div>
    <div class="pr">
        <img src="static/images/activity/olympicList-03.jpg" class="repeatImg"/>
        <div class="olympicText olympicText4 pa">
            <p>注：若用户累计购买金额相同，则按达成相同金额的时间先后排名。</p>
            <p>购买金额需≥20万元才能获得前三名对应的奖品，否则按第四名发奖。</p>
        </div>
    </div>
</div>
</body>
</html>