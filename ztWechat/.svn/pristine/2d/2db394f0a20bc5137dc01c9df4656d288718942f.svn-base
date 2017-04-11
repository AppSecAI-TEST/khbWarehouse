<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
    <title>银行限额</title>
</head>
<body>
<div id="box" class="bg-white">
    <div class="bankLimit">
        <p class="orange pb15">绑卡时仅支持银行借记卡，支持银行及充值限额列表如下：</p>
        <div class="limitTable">
            <table cellpadding="0" cellspacing="0" width="100%">
                <tbody>
                    <tr>
                        <th>银行名称</th>
                        <th>单笔限额</th>
                        <th>日限额</th>
                        <th>月限额
                    </tr>
                    <e:iterator list="@result.canBindCard" var="itmes">
                     <tr><td><e:property value="@itmes.platBankNo"/></td>
                    <e:if test="@itmes.channelType.toString().equals('AUTH_BANK_CARD')">
                    <td>不支持</td><td>不支持</td><td>不支持</td>
                    </e:if><e:elseif test="@itmes.channelType.toString().equals('NO_CARD')">
                    <td><e:if test="@itmes.singleQuato>9999"><e:property value="@Math.round(itmes.singleQuato/10000) "/>万</e:if>
                <e:else><e:property value="@Math.round(itmes.singleQuato/1000)"/>千</e:else></td>
                     <td><e:if test="@itmes.dayQuato>9999"><e:property value="@Math.round(itmes.dayQuato/10000) "/>万</e:if>
                <e:else><e:property value="@Math.round(itmes.dayQuato/1000)"/>千</e:else></td>
                      <td><e:if test="@itmes.monthQuato>9999"><e:property value="@Math.round(itmes.monthQuato/10000) "/>万</e:if>
                <e:else><e:property value="@Math.round(itmes.monthQuato/1000)"/>千</e:else></td>
                    </e:elseif><e:else></e:else>
                     </tr>
                    </e:iterator>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>