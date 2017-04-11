<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head lang="en">
    <title>产品详情</title>
</head>
<body>
<div id="box">
    <div class="bg-white mt15">
        <div class="titleInfo br-bottom">
            <h2 class="titleSafe orange">生财宝介绍</h2>
        </div>
        <div class="prolastCon">
            <p class="indent2 justify">
                生财宝是懒猫金服为个人用户推出的现金余额型理财产品。把资金转入生财宝即向华夏基金申购“财富宝”货币基金。货币基金主要投资于国债、银行存款等安全性高、收益稳定的有价证券。总体来看，货币基金作为基金产品的一种，理论上存在亏损可能，但从历史数据来看收益稳定风险极小。
            </p>
        </div>
    </div>
    <div class="bg-white mt15">
        <div class="titleInfo br-bottom">
            <h2 class="titleSafe orange">交易规则</h2>
        </div>
        <div class="prolastCon justify">
            <ul>
                <li>
                    <span class="orange">转入:</span>
                    最低限额1 元，最高无限额。
                </li>
                <li class="mt5">
                    <span class="orange">转出:</span>
                    <p>生财宝转出分为快速转出和普通转出2种方式。</p>
                    <p>快速转出单日限制10万元，单月限制300万元，转出实时到账。</p>
                    <p>普通转出无额度限制，到账时间为T+1日，即当时操作转出，次日（工作日）到懒猫账户。</p>
                </li>
            </ul>
            <p class="mt5">
                注: 懒猫用户单次转出金额超出快速转出单日/单月限额，系统将自动把该次转出的全部金额转换为普通转出，即当日转出第2日到懒猫账户，且不占用该用户的快速转出限额额度。
            </p>
        </div>
    </div>
    <div class="bg-white mt15">
        <div class="titleInfo br-bottom">
            <h2 class="titleSafe orange">收益规则</h2>
        </div>
        <div class="prolastCon">
            <p class="orange">收益到账时间:</p>
            <p class="justify mt5">
                当天15:00之前转入的资金在第二个工作日由基金公司进行份额确认，对已确认的份额，预计会在次日的15点左右发放收益。15:00后转入的资金会顺延1个工作日确认。
            </p>
            <table cellpadding="0" cellspacing="0" class="prolastTable" width="100%">
                <tbody>
                    <tr>
                        <th>转入时间</th>
                        <th>首次<br/>计息时间</th>
                        <th>首次收益<br/>到账时间</th>
                    </tr>
                    <tr>
                        <td>周一 15:00～<br/>
                            周二 15:00</td>
                        <td>周三</td>
                        <td>周四</td>
                    </tr>
                    <tr>
                        <td>周二 15:00～<br/>
                            周三 15:00</td>
                        <td>周四</td>
                        <td>周五</td>
                    </tr>
                    <tr>
                        <td>周三 15:00～<br/>
                            周四 15:00</td>
                        <td>周四</td>
                        <td>周六</td>
                    </tr>
                    <tr>
                        <td>周四 15:00～<br/>
                            周五 15:00</td>
                        <td>下周一</td>
                        <td>下周二</td>
                    </tr>
                    <tr>
                        <td>周五 15:00～<br/>
                            下周一 15:00</td>
                        <td>下周二</td>
                        <td>下周三</td>
                    </tr>
                </tbody>
            </table>
            <p class="orange">每日收益计算方式：</p>
            <p class="justify mt5">当日收益=（生财宝已确认份额/10000）X 每万份收益。</p>
            <p class="justify">
                假设您已确认份额的资金为9000 元，当天的每万份收益为1.25 元，代入计算公式，您当日的收益为：1.13 元。
            </p>
        </div>
    </div>
    <div class="bg-white mt15">
        <div class="titleInfo br-bottom">
            <h2 class="titleSafe orange">风险提示</h2>
        </div>
        <div class="prolastCon">
            <p class="indent2 justify">
                过往业绩并不预示未来表现，基金管理人管理的其他基金业绩并不构成基金业绩表现的保证。投资人购买货币市场基金不等于资金作为存款存放在银行或存款类金融机构，基金管理公司不保证一定盈利，也不保证最低收益。
            </p>
        </div>
    </div>
    <div class="bg-white mt15 mb15">
        <div class="titleInfo br-bottom">
            <h2 class="titleSafe orange">常见问题</h2>
        </div>
        <div class="prolastCon questionCon justify">
            <ul>
                <li>
                    <span class="orange">Q：</span>
                    可以用借记卡直接购买生财宝吗？
                </li>
                <li>
                    <span class="orange">A：</span>
                    不支持，请先用懒猫账户绑定的银行卡充值到懒猫余额，再用余额转入生财宝。
                </li>
                <li class="mt5">
                    <span class="orange">Q：</span>
                    生财宝可以直接转出至银行卡吗？
                </li>
                <li>
                    <span class="orange">A：</span>
                    目前不支持。
                </li>
                <li class="mt5">
                    <span class="orange">Q：</span>
                    生财宝转出当天有收益吗？
                </li>
                <li>
                    <span class="orange">A：</span>
                    快速转出的资金转出当天起没有收益。普通转出的资金当天有收益 ，资金到账日没有收益。
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>