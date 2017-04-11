<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
    + request.getServerName() + ":" + request.getServerPort()
    + path + "/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
	<base href=" <%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--优先使用 IE 最新版本和 Chrome-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, maximum-scale=1.0, initial-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <!--设置苹果工具栏颜色-->
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="static/css/older/reset.css">
    <link rel="stylesheet" href="static/css/older/common.css">
    <link rel="stylesheet" href="static/css/older/foot.css"/>
    <link rel="stylesheet" href="static/css/older/iconfont.css"/>
    <link rel="stylesheet" href="static/css/older/layer.css"/>
    <link rel="stylesheet" href="static/css/older/idangerous.swiper.css"/>
    <script type="text/javascript" src="static/js/account/old/jquery.js"></script>
    <script type="text/javascript" src="static/js/account/old/jquery.validate.min.js"></script>
    <script type="text/javascript" src="static/js/account/old/messages.js"></script>
    <script type="text/javascript" src="static/js/account/old/idangerous.swiper.min.js"></script>
    <script type="text/javascript" src="static/js/account/old/layer.m.js"></script>
    <script type="text/javascript" src="static/js/account/old/common.js"></script>
    <link href="static/css/old/mobiscroll_002.css" rel="stylesheet" type="text/css">
    <link href="static/css/old/mobiscroll.css" rel="stylesheet" type="text/css">
    <link href="static/css/old/mobiscroll_003.css" rel="stylesheet" type="text/css">
    <title>我的交易明细</title>
    <style>
        .tabs a{ display:block; float:left; width:33.33%; color:#f7a17f; text-align:center;}
        .tabs a.active{ color: #f46e26;}
        .swiper-container{ padding-top: 1.1rem;}
        .swiper-slide{ width:100%; background:none;}
    </style>
    <script type="text/javascript">
    function retryPay(orderNo){
    	$.post("old/purchaseUnPayProduct",{orderNo:orderNo},function(data){
    		if(data == 'SUCCESS'){
    			$("#"+orderNo).html("成功");
    			$("#"+orderNo).attr("onclick", "");
    		}else{
    			layer.open({
                    btn: ['OK'],
                    content:'<p class=\"color-yellow tc\">对不起，支付失败</p>'
                });
    		}
    	});
    }
    Date.prototype.Format = function (fmt) { // author: meizz
      var o = {
        "M+" : this.getMonth() + 1, // 月份
        "d+" : this.getDate(), // 日
        "h+" : this.getHours(), // 小时
        "m+" : this.getMinutes(), // 分
        "s+" : this.getSeconds(), // 秒
        "q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
        "S" : this.getMilliseconds()
      // 毫秒
      };
      if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
      for ( var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
          fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
              : (("00" + o[k]).substr(("" + o[k]).length)));
      return fmt;
    };
    function format_date (data) {//时间格式化
      if('string'==typeof(data)){
        data =  data.replace(/-/g,"/");
      }
      var date = new Date(data);
      return date.Format("yyyy-MM-dd hh:mm:ss");
    }
    var pageIndex = 1;
    var scbPageTotal = 1;
    var lmPageTotal = 1;
    var fixedPageTotal = 1;
    $(function(){
	    $.post("old/queryScbRecord",{pageIndex:pageIndex},function(data){
	    	scbPageTotal = data.pageTotal;
	    	if(scbPageTotal <= pageIndex){
				$("#scbRecord").parent().next().hide();
			}
	    	if(data != '' && data.lstMaps != '' && data.lstMaps != null){
		    	$.each(data.lstMaps,function(i,item){
		    		$("#scbRecord").append("<tr><td>"+item.TIME.replace(" ","<br/>")+"</td><td>"+item.TYPES+"</td><td class=\""+(item.TYPES=='转入'?"in":"out")+"\">"+(item.TYPES=='转入'?"+":"-")+formatmoney(item.AMOUNT,2)+"</td><td>"+item.STATUS+"</td></tr>");
		    	});
	    	}else{
	    		$("#scbRecord").html("<tr><td colspan=\"4\">未查询到相关记录</td></tr>");
	    		$("#scbRecord").parent().next().hide();
	    	}
		});
	    $.post("old/queryLmRecord",{pageIndex:pageIndex},function(data){
	    	lmPageTotal = data.totalPage;
	    	if(lmPageTotal <= pageIndex){
				$("#lmRecord").parent().next().hide();
			}
	    	if(data.result != '' && data.result != null){
		    	$.each(data.result,function(i,item){
		    		$("#lmRecord").append(
		    				"<tr><td>"+format_date(data.result[i].tradeTime).replace(" ","<br/>")+"</td><td>"
		    				+(data.result[i].tradeType=='LMACCOUNT_IN'?"充值":(data.result[i].tradeType=='LMACCOUNT_OUT'?"提现":(item.tradeType=='NETPAY_IN'?"充值":"")))
		    				+"</td><td class=\""+(data.result[i].tradeType=='LMACCOUNT_IN'?"in":(data.result[i].tradeType=='LMACCOUNT_OUT'?"out":(item.tradeType=='NETPAY_IN'?"in":"")))
		    				+"\">"+(data.result[i].tradeType=='LMACCOUNT_IN'?"+":(data.result[i].tradeType=='LMACCOUNT_OUT'?"-":(item.tradeType=='NETPAY_IN'?"+":"")))+formatmoney(data.result[i].amount,2)+"</td><td>"
		    				+(data.result[i].tradeStatus=='SUCCESS'?"成功":(data.result[i].tradeStatus=='FAIL'?"失败":"进行中"))+"</td></tr>");
		    	});
	    	}else{
	    		$("#lmRecord").append("<tr><td colspan=\"4\">未查询到相关记录</td></tr>");
	    		$("#lmRecord").parent().next().hide();
	    	}
		});
	    $.post("old/queryFixedTrade",{pageIndex:pageIndex},function(data){
	    	fixedPageTotal = data.totalPage;
	    	if(fixedPageTotal <= pageIndex){
				$("#fixedRecord").parent().next().hide();
			}
	    	if(data.result != '' && data.result != null){
		    	$.each(data.result,function(i,item){
		    		var text = "<tr><td>"+format_date(data.result[i].tradeTime).replace(" ","<br/>")+"</td>";
		    		if(data.result[i].type=='SALES'){
		    			text += "<td>"+"购买"+"</td><td class=\"in\">+";
		    		}else if (data.result[i].type=='PAYMENTS') {
		    			text += "<td>"+"兑付"+"</td><td class=\"out\">+";
		    		} else if (data.result[i].type=='REFUND') {
		    			text += "<td>"+"退款"+"</td><td class=\"out\">+";
		    		}
		    		text += formatmoney(data.result[i].amount,2)+"</td><td>";
		    		if(data.result[i].status=='SUCCESS'){
		    			text += "成功";
		    		}else if(data.result[i].status=='FAIL'){
		    			text += "失败";
		    		}else if(data.result[i].status=='PAYING'){
		    			text += "<a id="+data.result[i].orderNo+" href=\"javascript:void(0);\" onclick=\"retryPay('"+data.result[i].orderNo+"')\">待付款</a>";
		    		}else if(data.result[i].status=='CANCEL'){
		    			text += "取消";
		    		}else if(data.result[i].status=='CLOSED'){
		    			text += "关闭";
		    		}
		    		text += "</td></tr>";
		    		$("#fixedRecord").append(text);
		    	});
	    	}else{
	    		$("#fixedRecord").append("<tr><td colspan=\"4\">未查询到相关记录</td></tr>");
	    		$("#fixedRecord").parent().next().hide();
	    	}
		});
    });
    </script>
</head>
<body>
<div class="box" id="box">
    <ul class="statelist">
        <li>
            <input type="hidden" value="0" name="state" id="lmTradeType"/>
            <input type="hidden" value="0" name="state" id="lmTradeStatus"/>
        </li>
        <li>
            <input type="hidden" value="0" name="state" id="fixedTradeType"/>
            <input type="hidden" value="0" name="state" id="fixedTradeStatus"/>
        </li>
        <li>
            <input type="hidden" value="0" name="state" id="scbTradeType"/>
            <input type="hidden" value="0" name="state" id="scbTradeStatus"/>
        </li>
    </ul>
    <div class="topfixed">
        <div class="tabs topmenu clearfix bg-white">
            <a href="#" hidefocus="true" class="active">懒猫账户</a>
            <a href="#" hidefocus="true">信托理财</a>
            <a href="#" hidefocus="true">生财宝</a>
        </div>
    </div>
    <div class="swiper-container" style="height: auto;">
        <div class="swiper-wrapper">
            <div class="swiper-slide">
                <div class="content-slide tc">
                    <div class="bg-gray selectime-wrap clearfix">
                        <div class="fl ml3 iconfont">
                            <span class="color-50 mr5 time-label">起止时间</span>
                            <a class="input-time-wrap">
                            	<input value="" class="input-time" readonly="readonly" name="appDate" id="appDateBegin01" type="text">
                                <span class="icon icon-rili"></span>
                            </a>
                            <span >-</span>
                            <a class="input-time-wrap">
                            	<input value="" class="input-time" readonly="readonly" name="appDate" id="appDateEnd01" type="text">
                                <span class="icon icon-rili"></span>
                            </a>
                        </div>
                        <a href="javascript:void(0)" class="fr mr3 a-filter" id="filter01">筛选</a>
                    </div>
                    <table id="lmRecordTable" class="transRecord" pageNo="1">
                        <thead>
                            <tr>
                                <th><span>交易时间</span></th>
                                <th><span>类型</span></th>
                                <th><span>金额（元）</span></th>
                                <th><span>状态</span></th>
                            </tr>
                        </thead>
                        <tbody id="lmRecord">
                        </tbody>
                    </table>
                    <div class="spinner-wrap">
                        <div class="spinner-inner">
                            <div class="spinner">
                                <div class="spinner-container container1">
                                    <div class="circle1"></div>
                                    <div class="circle2"></div>
                                    <div class="circle3"></div>
                                    <div class="circle4"></div>
                                </div>
                                <div class="spinner-container container2">
                                    <div class="circle1"></div>
                                    <div class="circle2"></div>
                                    <div class="circle3"></div>
                                    <div class="circle4"></div>
                                </div>
                                <div class="spinner-container container3">
                                    <div class="circle1"></div>
                                    <div class="circle2"></div>
                                    <div class="circle3"></div>
                                    <div class="circle4"></div>
                                </div>
                            </div>
                            <span class="spinner-label">点击加载更多</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="swiper-slide">
                <div class="content-slide tc">
                    <div class="bg-gray selectime-wrap clearfix">
                        <div class="fl ml3 iconfont">
                            <span class="color-50 mr5 time-label">起止时间</span>
                            <a class="input-time-wrap">
                            	<input value="" class="input-time" readonly="readonly" name="appDate" id="appDateBegin02" type="text">
                                <span class="icon icon-rili"></span>
                            </a>
                            <span >-</span>
                            <a class="input-time-wrap">
                            	<input value="" class="input-time" readonly="readonly" name="appDate" id="appDateEnd02" type="text">
                                <span class="icon icon-rili"></span>
                            </a>
                        </div>
                        <a href="javascript:void(0)" class="fr mr3 a-filter" id="filter02">筛选</a>
                    </div>
                    <table id="fixedRecordTable" class="transRecord" pageNo="1">
                        <thead>
                        <tr>
                            <th><span>交易时间</span></th>
                            <th><span>类型</span></th>
                            <th><span>金额（元）</span></th>
                            <th><span>状态</span></th>
                        </tr>
                        </thead>
                        <tbody id="fixedRecord">
                        </tbody>
                    </table>
                    <div class="spinner-wrap">
                        <div class="spinner-inner">
                            <div class="spinner">
                                <div class="spinner-container container1">
                                    <div class="circle1"></div>
                                    <div class="circle2"></div>
                                    <div class="circle3"></div>
                                    <div class="circle4"></div>
                                </div>
                                <div class="spinner-container container2">
                                    <div class="circle1"></div>
                                    <div class="circle2"></div>
                                    <div class="circle3"></div>
                                    <div class="circle4"></div>
                                </div>
                                <div class="spinner-container container3">
                                    <div class="circle1"></div>
                                    <div class="circle2"></div>
                                    <div class="circle3"></div>
                                    <div class="circle4"></div>
                                </div>
                            </div>
                            <span class="spinner-label">点击加载更多</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="swiper-slide">
                <div class="content-slide tc">
                    <div class="bg-gray selectime-wrap clearfix">
                        <div class="fl ml3 iconfont">
                            <span class="color-50 mr5 time-label">起止时间</span>
                            <a class="input-time-wrap">
                            	<input value="" class="input-time" readonly="readonly" name="appDate" id="appDateBegin03" type="text">
                                <span class="icon icon-rili"></span>
                            </a>
                            <span >-</span>
                            <a class="input-time-wrap">
                            	<input value="" class="input-time" readonly="readonly" name="appDate" id="appDateEnd03" type="text">
                                <span class="icon icon-rili"></span>
                            </a>
                        </div>
                        <a href="javascript:void(0)" class="fr mr3 a-filter" id="filter03">筛选</a>
                    </div>
                    <table id="scbRecordTable" class="transRecord" pageNo="1">
                        <thead>
                        <tr>
                            <th><span>交易时间</span></th>
                            <th><span>类型</span></th>
                            <th><span>金额（元）</span></th>
                            <th><span>状态</span></th>
                        </tr>
                        </thead>
                        <tbody id="scbRecord">
                        </tbody>
                    </table>
                    <div class="spinner-wrap">
                        <div class="spinner-inner">
                            <div class="spinner">
                                <div class="spinner-container container1">
                                    <div class="circle1"></div>
                                    <div class="circle2"></div>
                                    <div class="circle3"></div>
                                    <div class="circle4"></div>
                                </div>
                                <div class="spinner-container container2">
                                    <div class="circle1"></div>
                                    <div class="circle2"></div>
                                    <div class="circle3"></div>
                                    <div class="circle4"></div>
                                </div>
                                <div class="spinner-container container3">
                                    <div class="circle1"></div>
                                    <div class="circle2"></div>
                                    <div class="circle3"></div>
                                    <div class="circle4"></div>
                                </div>
                            </div>
                            <span class="spinner-label">点击加载更多</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
	$('.spinner-wrap').click(function(){
	    var objid = $(this).prev().attr('id');
	    $("#"+objid).next().find('.spinner').addClass('spinner-active');
	    Ajax(objid);
	});
	function Ajax(objid){
		var pageIndex = $("#"+objid).attr("pageNo");
		pageIndex = ++pageIndex;
		if(objid == 'scbRecordTable'){
			$("#"+objid).attr("pageNo", pageIndex);
			var tradeType = $("#scbTradeType").val();
		    var tradeStatus = $("#scbTradeStatus").val();
		    var startTime = $("#appDateBegin03").val();
		    var endTime = $("#appDateEnd03").val();
			if(scbPageTotal >= pageIndex){
				$.post("scb/queryScbRecord",{tradeType:tradeType,tradeStatus:tradeStatus,startTime:startTime,endTime:endTime,pageIndex:pageIndex},function(data){
					if(data != '' && data.lstMaps != '' && data.lstMaps != null){
				    	$.each(data.lstMaps,function(i,item){
				    		$("#scbRecord").append("<tr><td>"+item.TIME.replace(" ","<br/>")+"</td><td>"+item.TYPES+"</td><td class=\""+(item.TYPES=='转入'?"in":"out")+"\">"+(item.TYPES=='转入'?"+":"-")+formatmoney(item.AMOUNT,2)+"</td><td>"+item.STATUS+"</td></tr>");
				    	});
			    	}else{
			    		$("#"+objid).next().find('.spinner-label').html('亲，没有更多记录了!');
						$("#"+objid).next().find('.spinner-inner').css('width','210px');
			    	}
				});
			}else{
				$("#"+objid).next().find('.spinner-label').html('亲，没有更多记录了!');
				$("#"+objid).next().find('.spinner-inner').css('width','210px');
			}
		}else if(objid == 'lmRecordTable'){
			$("#"+objid).attr("pageNo", pageIndex);
			var tradeType = $("#lmTradeType").val();
		    var tradeStatus = $("#lmTradeStatus").val();
		    var startTime = $("#appDateBegin01").val();
		    var endTime = $("#appDateEnd01").val();
			if(lmPageTotal >= pageIndex){
				$.post("old/queryLmRecord",{tradeType:tradeType,tradeStatus:tradeStatus,startTime:startTime,endTime:endTime,pageIndex:pageIndex},function(data){
					if(data.result != ''){
				    	$.each(data.result,function(i,item){
				    		$("#lmRecord").append(
				    				"<tr><td>"+format_date(data.result[i].tradeTime).replace(" ","<br/>")+"</td><td>"
				    				+(data.result[i].tradeType=='LMACCOUNT_IN'?"充值":(data.result[i].tradeType=='LMACCOUNT_OUT'?"提现":(item.tradeType=='NETPAY_IN'?"充值":"")))
				    				+"</td><td class=\""+(data.result[i].tradeType=='LMACCOUNT_IN'?"in":(data.result[i].tradeType=='LMACCOUNT_OUT'?"out":(item.tradeType=='NETPAY_IN'?"in":"")))
				    				+"\">"+(data.result[i].tradeType=='LMACCOUNT_IN'?"+":(data.result[i].tradeType=='LMACCOUNT_OUT'?"-":(item.tradeType=='NETPAY_IN'?"+":"")))+formatmoney(data.result[i].amount,2)+"</td><td>"
				    				+(data.result[i].tradeStatus=='SUCCESS'?"成功":(data.result[i].tradeStatus=='FAIL'?"失败":"进行中"))+"</td></tr>");
				    	});
			    	}else{
			    		$("#"+objid).next().find('.spinner-label').html('亲，没有更多记录了!');
						$("#"+objid).next().find('.spinner-inner').css('width','210px');
			    	}
				});
			}else{
				$("#"+objid).next().find('.spinner-label').html('亲，没有更多记录了!');
				$("#"+objid).next().find('.spinner-inner').css('width','210px');
			}
		}else if(objid == 'fixedRecordTable'){
			$("#"+objid).attr("pageNo", pageIndex);
			var tradeType = $("#fixedTradeType").val();
		    var tradeStatus = $("#fixedTradeStatus").val();
		    var startTime = $("#appDateBegin02").val();
		    var endTime = $("#appDateEnd02").val();
			if(fixedPageTotal >= pageIndex){
				$.post("old/queryFixedTrade",{tradeType:tradeType,tradeStatus:tradeStatus,startTime:startTime,endTime:endTime,pageIndex:pageIndex},function(data){
					if(data.result != null && data.result != ''){
				    	$.each(data.result,function(i,item){
				    		var text = "<tr><td>"+format_date(data.result[i].tradeTime).replace(" ","<br/>")+"</td>";
				    		if(data.result[i].type=='SALES'){
				    			text += "<td>"+"购买"+"</td><td class=\"in\">+";
				    		}else if (data.result[i].type=='PAYMENTS') {
				    			text += "<td>"+"兑付"+"</td><td class=\"out\">+";
				    		} else if (data.result[i].type=='REFUND') {
				    			text += "<td>"+"退款"+"</td><td class=\"out\">+";
				    		}
				    		text += formatmoney(data.result[i].amount,2)+"</td><td>";
				    		if(data.result[i].status=='SUCCESS'){
				    			text += "成功";
				    		}else if(data.result[i].status=='FAIL'){
				    			text += "失败";
				    		}else if(data.result[i].status=='PAYING'){
				    			text += "<a id="+data.result[i].orderNo+" href=\"javascript:void(0);\" onclick=\"retryPay('"+data.result[i].orderNo+"')\">待付款</a>";
				    		}else if(data.result[i].status=='CANCEL'){
				    			text += "取消";
				    		}else if(data.result[i].status=='CLOSED'){
				    			text += "关闭";
				    		}
				    		text += "</td></tr>";
				    		$("#fixedRecord").append(text);
				    	});
			    	}else{
			    		$("#"+objid).next().find('.spinner-label').html('亲，没有更多记录了!');
						$("#"+objid).next().find('.spinner-inner').css('width','210px');
			    	}
				});
			}else{
				$("#"+objid).next().find('.spinner-label').html('亲，没有更多记录了!');
				$("#"+objid).next().find('.spinner-inner').css('width','210px');
			}
		}
		$("#"+objid).next().find('.spinner').removeClass('spinner-active');
	}
	$(function(){
	    $('.swiper-wrapper').height('auto');
	    $('.swiper-slide').height('200px');
	    $('.swiper-slide-active').height('auto');
	})
    var tabsSwiper = new Swiper('.swiper-container',{
        speed:500,
        onSlideChangeStart: function(){
            $(".tabs .active").removeClass('active');
            $(".tabs a").eq(tabsSwiper.activeIndex).addClass('active');
            $('.swiper-wrapper').height('auto');
            $('.swiper-slide').height('200px');
            $('.swiper-slide-active').height('auto');
           /*  location.href="old/toRecord"; */
        }
    });
    $(".tabs a").on('touchstart mousedown',function(e){
        e.preventDefault()
        $(".tabs .active").removeClass('active');
        $(this).addClass('active');
        tabsSwiper.swipeTo($(this).index());
        layer.closeAll();
       /*  location.href="old/toRecord"; */
    });

    $(".tabs a").click(function(e){
        e.preventDefault();
    });

    var onOff = true;
    $('.a-filter').click(function(){
        var idx = $('.tabs a.active').index();
        if(onOff){
            if(idx==1){
                layer.open({
                    type: 1,
                    style: ' width:100%; box-shadow:none; border:.02rem solid #d3d3d3; text-align:center;',
                    fixed:false,
                    top: 0,
                    anim: false,
                    content:'<div class="layout-wrap-pad4 mb10">' +
                                '<ul class="filt-topname clearfix">' +
                                    '<li class="br-right">交易分类</li>' +
                                    '<li>交易状态</li>' +
                                '</ul>' +
                                '<div class="filt-dwcon clearfix mt8">' +
                                    '<div class="fl br-top br1-right">' +
                                        '<ul class="filter-lft br-right">' +
                                            '<li class="cur"><span>全部</span></li>' +
                                            '<li><span>购买</span></li>' +
                                            '<li><span>兑付</span></li>' +
                                            '<li></li>' +
                                        '</ul>' +
                                    '</div>' +
                                    '<div class="fr br-top">' +
                                        '<ul class="filter-rht">' +
                                            '<li class="cur"><span>全部</span></li>' +
                                            '<li><span>成功</span></li>' +
                                            '<li><span>失败</span></li>' +
                                            '<li>待付款</li>' +
                                        '</ul>' +
                                    '</div>' +
                                '</div>' +
                            '</div>',
                    end:function(){
                        onOff = true;
                        var tradeType = $("#fixedTradeType").val();
            		    var tradeStatus = $("#fixedTradeStatus").val();
            		    var startTime = $("#appDateBegin02").val();
            		    var endTime = $("#appDateEnd02").val();
                        pageIndex = 1;
                        $("#fixedRecordTable").attr("pageNo", pageIndex);
                        $.post("old/queryFixedTrade",{tradeType:tradeType,tradeStatus:tradeStatus,startTime:startTime,endTime:endTime,pageIndex:pageIndex},function(data){
                        	$("#fixedRecord").html("");
                        	$("#fixedRecordTable").next().find('.spinner-label').html('点击加载更多');
                        	$("#fixedRecord").parent().next().show();
                        	fixedPageTotal = data.totalPage;
                	    	if(fixedPageTotal <= pageIndex){
                				$("#fixedRecord").parent().next().hide();
                			}
                	    	if(data.result != null && data.result != ''){
                		    	$.each(data.result,function(i,item){
                		    		var text = "<tr><td>"+format_date(data.result[i].tradeTime).replace(" ","<br/>")+"</td>";
        				    		if(data.result[i].type=='SALES'){
        				    			text += "<td>"+"购买"+"</td><td class=\"in\">+";
        				    		}else if (data.result[i].type=='PAYMENTS') {
        				    			text += "<td>"+"兑付"+"</td><td class=\"out\">+";
        				    		} else if (data.result[i].type=='REFUND') {
        				    			text += "<td>"+"退款"+"</td><td class=\"out\">+";
        				    		}
        				    		text += formatmoney(data.result[i].amount,2)+"</td><td>";
                		    		if(data.result[i].status=='SUCCESS'){
                		    			text += "成功";
                		    		}else if(data.result[i].status=='FAIL'){
                		    			text += "失败";
                		    		}else if(data.result[i].status=='PAYING'){
                		    			text += "<a id="+data.result[i].orderNo+" href=\"javascript:void(0);\" onclick=\"retryPay('"+data.result[i].orderNo+"')\">待付款</a>";
                		    		}else if(data.result[i].status=='CANCEL'){
                		    			text += "取消";
                		    		}else if(data.result[i].status=='CLOSED'){
                		    			text += "关闭";
                		    		}
                		    		text += "</td></tr>";
                		    		$("#fixedRecord").append(text);
                		    	});
                	    	}else{
                	    		$("#fixedRecord").html("<tr><td colspan=\"4\">未查询到相关记录</td></tr>");
                	    		$("#fixedRecord").parent().next().hide();
                	    	}
                		});
                    }
                });
            }else if(idx==0){
                layer.open({
                    type: 1,
                    style: ' width:100%; box-shadow:none; border:.02rem solid #d3d3d3; text-align:center;',
                    fixed:false,
                    top: 0,
                    anim: false,
                    content:'<div class="layout-wrap-pad4 mb10">' +
                                '<ul class="filt-topname clearfix">' +
                                    '<li class="br-right">交易分类</li>' +
                                    '<li>交易状态</li>' +
                                '</ul>' +
                                '<div class="filt-dwcon clearfix mt8">' +
                                    '<div class="fl br-top br1-right">' +
                                        '<ul class="filter-lft br-right">' +
                                            '<li class="cur"><span>全部</span></li>' +
                                            '<li><span>充值</span></li>' +
                                            '<li><span>提现</span></li>' +
                                            '<li></li>' +
                                        '</ul>' +
                                    '</div>' +
                                    '<div class="fr br-top">' +
                                        '<ul class="filter-rht">' +
                                            '<li class="cur"><span>全部</span></li>' +
                                            '<li><span>成功</span></li>' +
                                            '<li><span>失败</span></li>' +
                                            '<li>进行中</li>' +
                                        '</ul>' +
                                    '</div>' +
                                '</div>' +
                            '</div>',
                    end:function(){
                        onOff = true;
                        var tradeType = $("#lmTradeType").val();
            		    var tradeStatus = $("#lmTradeStatus").val();
            		    var startTime = $("#appDateBegin01").val();
            		    var endTime = $("#appDateEnd01").val();
                        pageIndex = 1;
                        $("#lmRecordTable").attr("pageNo", pageIndex);
                        $.post("old/queryLmRecord",{tradeType:tradeType,tradeStatus:tradeStatus,startTime:startTime,endTime:endTime,pageIndex:pageIndex},function(data){
                        	$("#lmRecord").html("");
                        	$("#lmRecordTable").next().find('.spinner-label').html('点击加载更多');
                        	$("#lmRecord").parent().next().show();
                        	lmPageTotal = data.totalPage;
                	    	if(lmPageTotal <= pageIndex){
                				$("#lmRecord").parent().next().hide();
                			}
                	    	if(data.result != ''){
        				    	$.each(data.result,function(i,item){
        				    		$("#lmRecord").append(
        				    				"<tr><td>"+format_date(data.result[i].tradeTime).replace(" ","<br/>")+"</td><td>"
        				    				+(data.result[i].tradeType=='LMACCOUNT_IN'?"充值":(data.result[i].tradeType=='LMACCOUNT_OUT'?"提现":(item.tradeType=='NETPAY_IN'?"充值":"")))
        				    				+"</td><td class=\""+(data.result[i].tradeType=='LMACCOUNT_IN'?"in":(data.result[i].tradeType=='LMACCOUNT_OUT'?"out":(item.tradeType=='NETPAY_IN'?"in":"")))
        				    				+"\">"+(data.result[i].tradeType=='LMACCOUNT_IN'?"+":(data.result[i].tradeType=='LMACCOUNT_OUT'?"-":(item.tradeType=='NETPAY_IN'?"+":"")))+formatmoney(data.result[i].amount,2)+"</td><td>"
        				    				+(data.result[i].tradeStatus=='SUCCESS'?"成功":(data.result[i].tradeStatus=='FAIL'?"失败":"进行中"))+"</td></tr>");
        				    	});
        			    	}else{
                	    		$("#lmRecord").html("<tr><td colspan=\"4\">未查询到相关记录</td></tr>");
                	    		$("#lmRecord").parent().next().hide();
                	    	}
                		});
                    }
                });
            }else if(idx==2){
                layer.open({
                    type: 1,
                    style: ' width:100%; box-shadow:none; border:.02rem solid #d3d3d3; text-align:center;',
                    fixed:false,
                    top: 0,
                    anim: false,
                    content:'<div class="layout-wrap-pad4 mb10">' +
                                '<ul class="filt-topname clearfix">' +
                                    '<li class="br-right">交易分类</li>' +
                                    '<li>交易状态</li>' +
                                '</ul>' +
                                '<div class="filt-dwcon clearfix mt8">' +
                                    '<div class="fl br-top br1-right">' +
                                        '<ul class="filter-lft br-right">' +
                                            '<li class="cur"><span>全部</span></li>' +
                                            '<li><span>转入</span></li>' +
                                            '<li><span>转出</span></li>' +
                                            '<li></li>' +
                                        '</ul>' +
                                    '</div>' +
                                    '<div class="fr br-top">' +
                                        '<ul class="filter-rht">' +
                                            '<li class="cur"><span>全部</span></li>' +
                                            '<li><span>成功</span></li>' +
                                            '<li><span>失败</span></li>' +
                                            '<li>进行中</li>' +
                                        '</ul>' +
                                    '</div>' +
                                '</div>' +
                            '</div>',
                    end:function(){
                        onOff = true;
                        var tradeType = $("#scbTradeType").val();
            		    var tradeStatus = $("#scbTradeStatus").val();
            		    var startTime = $("#appDateBegin03").val();
            		    var endTime = $("#appDateEnd03").val();
                        pageIndex = 1;
                        $("#scbRecordTable").attr("pageNo", pageIndex);
                        $.post("scb/queryScbRecord",{tradeType:tradeType,tradeStatus:tradeStatus,startTime:startTime,endTime:endTime,pageIndex:pageIndex},function(data){
                        	$("#scbRecord").html("");
                        	$("#scbRecordTable").next().find('.spinner-label').html('点击加载更多');
                        	$("#scbRecord").parent().next().show();
                        	scbPageTotal = data.pageTotal;
                	    	if(scbPageTotal <= pageIndex){
                				$("#scbRecord").parent().next().hide();
                			}
                	    	if(data != '' && data.lstMaps != '' && data.lstMaps != null){
                		    	$.each(data.lstMaps,function(i,item){
                		    		$("#scbRecord").append("<tr><td>"+item.TIME.replace(" ","<br/>")+"</td><td>"+item.TYPES+"</td><td class=\""+(item.TYPES=='转入'?"in":"out")+"\">"+(item.TYPES=='转入'?"+":"-")+formatmoney(item.AMOUNT,2)+"</td><td>"+item.STATUS+"</td></tr>");
                		    	});
                	    	}else{
                	    		$("#scbRecord").html("<tr><td colspan=\"4\">未查询到相关记录</td></tr>");
                	    		$("#scbRecord").parent().next().hide();
                	    	}
                		});
                    }
                });
            }
            onOff = false;
            $('.layermbox').css({'top':'108px'});
            $('.laymshade').css({'top':'108px'});

            /*事件代理*/
            $('body').delegate('.filt-dwcon li','click',function(){
                $(this).parents('ul').children('li').removeClass('cur');
                $(this).toggleClass('cur');
                var index = $(this).index();
                var idx = $('.tabs a.active').index();
                if($(this).hasClass('cur')){
                    if($(this).parents('ul').hasClass('filter-lft')){
                        $('.statelist li').eq(idx).children('input').eq(0).val(index);
                    }else{
                        $('.statelist li').eq(idx).children('input').eq(1).val(index);
                    }
                }else{
                    if($(this).parents('ul').hasClass('filter-lft')){
                        $('.statelist li').eq(idx).children('input').eq(0).val(index);
                    }else{
                        $('.statelist li').eq(idx).children('input').eq(1).val(index);
                    }
                }
            });
            $('.filt-dwcon li').click(function(){});
            for(var i=0; i<3; i++){
                var state = $('.statelist li').eq(idx).children('input').eq(i).val();
                switch (state){
                    case '0' :
                        $('.filt-dwcon ul').eq(i).children('li').removeClass('cur');
                        $('.filt-dwcon ul').eq(i).children('li').eq(0).addClass('cur');
                        break;
                    case '1' :
                        $('.filt-dwcon ul').eq(i).children('li').removeClass('cur');
                        $('.filt-dwcon ul').eq(i).children('li').eq(1).addClass('cur');
                        break;
                    case '2' :
                        $('.filt-dwcon ul').eq(i).children('li').removeClass('cur');
                        $('.filt-dwcon ul').eq(i).children('li').eq(2).addClass('cur');
                        break;
                    case '3' :
                        $('.filt-dwcon ul').eq(i).children('li').removeClass('cur');
                        $('.filt-dwcon ul').eq(i).children('li').eq(3).addClass('cur');
                        break;
                }
            }
        }else{
            layer.closeAll();
            onOff = true;
        }
    });
</script>
<script src="static/js/account/old/calendar/mobiscroll_002.js" type="text/javascript"></script>
<script src="static/js/account/old/calendar/mobiscroll_004.js" type="text/javascript"></script>
<script src="static/js/account/old/calendar/mobiscroll.js" type="text/javascript"></script>
<script src="static/js/account/old/calendar/mobiscroll_003.js" type="text/javascript"></script>
<script src="static/js/account/old/calendar/mobiscroll_005.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        var currYear = (new Date()).getFullYear();
        var opt={};
        opt.date = {preset : 'date'};
        opt.defaulter = {
            theme: 'android-ics light', //皮肤样式
            display: 'modal', //显示方式
            mode: 'scroller', //日期选择模式
            dateFormat: 'yyyy-mm-dd',
            lang: 'zh',
            showNow: true,
            nowText: "今天",
            startYear: currYear - 10, //开始年份
            endYear: currYear + 10 //结束年份
        };
        $("#appDateBegin01").mobiscroll($.extend(opt['date'], opt['defaulter']));
        $("#appDateBegin02").mobiscroll($.extend(opt['date'], opt['defaulter']));
        $("#appDateBegin03").mobiscroll($.extend(opt['date'], opt['defaulter']));
        $("#appDateEnd01").focusin(function(){
            var vals=$('#appDateBegin01').val();
            if(vals==''){
                layer.open({
                    btn: ['OK'],
                    content:'<p class=\"color-yellow tc\">请选择开始日期</p>'
                })
            }else{
            	$("#appDateEnd01").unbind('focusin').mobiscroll($.extend(opt['date'], opt['defaulter']));
            	$(this).unbind('change');
                $(this).change(function(){
                	var tradeType = $("#lmTradeType").val();
        		    var tradeStatus = $("#lmTradeStatus").val();
        		    var startTime = $("#appDateBegin01").val();
        		    var endTime = $("#appDateEnd01").val();
                    pageIndex = 1;
                    $("#lmRecordTable").attr("pageNo", pageIndex);
                    $.post("old/queryLmRecord",{tradeType:tradeType,tradeStatus:tradeStatus,startTime:startTime,endTime:endTime,pageIndex:pageIndex},function(data){
                    	$("#lmRecord").html("");
                    	$("#lmRecordTable").next().find('.spinner-label').html('点击加载更多');
                    	$("#lmRecord").parent().next().show();
                    	lmPageTotal = data.totalPage;
            	    	if(lmPageTotal <= pageIndex){
            				$("#lmRecord").parent().next().hide();
            			}
            	    	if(data.result != ''){
    				    	$.each(data.result,function(i,item){
    				    		$("#lmRecord").append(
    				    				"<tr><td>"+format_date(data.result[i].tradeTime).replace(" ","<br/>")+"</td><td>"
    				    				+(data.result[i].tradeType=='LMACCOUNT_IN'?"充值":(data.result[i].tradeType=='LMACCOUNT_OUT'?"提现":(item.tradeType=='NETPAY_IN'?"充值":"")))
    				    				+"</td><td class=\""+(data.result[i].tradeType=='LMACCOUNT_IN'?"in":(data.result[i].tradeType=='LMACCOUNT_OUT'?"out":(item.tradeType=='NETPAY_IN'?"in":"")))
    				    				+"\">"+(data.result[i].tradeType=='LMACCOUNT_IN'?"+":(data.result[i].tradeType=='LMACCOUNT_OUT'?"-":(item.tradeType=='NETPAY_IN'?"+":"")))+formatmoney(data.result[i].amount,2)+"</td><td>"
    				    				+(data.result[i].tradeStatus=='SUCCESS'?"成功":(data.result[i].tradeStatus=='FAIL'?"失败":"进行中"))+"</td></tr>");
    				    	});
    			    	}else{
            	    		$("#lmRecord").html("<tr><td colspan=\"4\">未查询到相关记录</td></tr>");
            	    		$("#lmRecord").parent().next().hide();
            	    	}
            		});
                });
            }
        });
        $("#appDateEnd02").focusin(function(){
            var vals=$('#appDateBegin02').val();
            if(vals==''){
                layer.open({
                    btn: ['OK'],
                    content:'<p class=\"color-yellow tc\">请选择开始日期</p>'
                })
            }else{
                $("#appDateEnd02").unbind('focusin').mobiscroll($.extend(opt['date'], opt['defaulter']));
                $(this).unbind('change');
                $(this).change(function(){
                	var tradeType = $("#fixedTradeType").val();
        		    var tradeStatus = $("#fixedTradeStatus").val();
        		    var startTime = $("#appDateBegin02").val();
        		    var endTime = $("#appDateEnd02").val();
                    pageIndex = 1;
                    $("#fixedRecordTable").attr("pageNo", pageIndex);
                    $.post("old/queryFixedTrade",{tradeType:tradeType,tradeStatus:tradeStatus,startTime:startTime,endTime:endTime,pageIndex:pageIndex},function(data){
                    	$("#fixedRecord").html("");
                    	$("#fixedRecordTable").next().find('.spinner-label').html('点击加载更多');
                    	$("#fixedRecord").parent().next().show();
                    	fixedPageTotal = data.totalPage;
            	    	if(fixedPageTotal <= pageIndex){
            				$("#fixedRecord").parent().next().hide();
            			}
            	    	if(data.result != '' && data.result != null){
            		    	$.each(data.result,function(i,item){
            		    		var text = "<tr><td>"+format_date(data.result[i].tradeTime).replace(" ","<br/>")+"</td>";
    				    		if(data.result[i].type=='SALES'){
    				    			text += "<td>"+"购买"+"</td><td class=\"in\">+";
    				    		}else if (data.result[i].type=='PAYMENTS') {
    				    			text += "<td>"+"兑付"+"</td><td class=\"out\">+";
    				    		} else if (data.result[i].type=='REFUND') {
    				    			text += "<td>"+"退款"+"</td><td class=\"out\">+";
    				    		}
    				    		text += formatmoney(data.result[i].amount,2)+"</td><td>";
            		    		if(data.result[i].status=='SUCCESS'){
            		    			text += "成功";
            		    		}else if(data.result[i].status=='FAIL'){
            		    			text += "失败";
            		    		}else if(data.result[i].status=='PAYING'){
            		    			text += "<a id="+data.result[i].orderNo+" href=\"javascript:void(0);\" onclick=\"retryPay('"+data.result[i].orderNo+"')\">待付款</a>";
            		    		}else if(data.result[i].status=='CANCEL'){
            		    			text += "取消";
            		    		}else if(data.result[i].status=='CLOSED'){
            		    			text += "关闭";
            		    		}
            		    		text += "</td></tr>";
            		    		$("#fixedRecord").append(text);
            		    	});
            	    	}else{
            	    		$("#fixedRecord").html("<tr><td colspan=\"4\">未查询到相关记录</td></tr>");
            	    		$("#fixedRecord").parent().next().hide();
            	    	}
            		});
                });
            }
        });
        $("#appDateEnd03").focusin(function(){
            var vals=$('#appDateBegin03').val();
            if(vals==''){
                layer.open({
                    btn: ['OK'],
                    content:'<p class=\"color-yellow tc\">请选择开始日期</p>'
                })
            }else{
                $("#appDateEnd03").unbind('focusin').mobiscroll($.extend(opt['date'], opt['defaulter']));
                $(this).unbind('change');
                $(this).change(function(){
                	var tradeType = $("#scbTradeType").val();
        		    var tradeStatus = $("#scbTradeStatus").val();
        		    var startTime = $("#appDateBegin03").val();
        		    var endTime = $("#appDateEnd03").val();
                    pageIndex = 1;
                    $("#scbRecordTable").attr("pageNo", pageIndex);
                    $.post("scb/queryScbRecord",{tradeType:tradeType,tradeStatus:tradeStatus,startTime:startTime,endTime:endTime,pageIndex:pageIndex},function(data){
                    	$("#scbRecord").html("");
                    	$("#scbRecordTable").next().find('.spinner-label').html('点击加载更多');
                    	$("#scbRecord").parent().next().show();
                    	scbPageTotal = data.pageTotal;
            	    	if(scbPageTotal <= pageIndex){
            				$("#scbRecord").parent().next().hide();
            			}
            	    	if(data != '' && data.lstMaps != '' && data.lstMaps != null){
            		    	$.each(data.lstMaps,function(i,item){
            		    		$("#scbRecord").append("<tr><td>"+item.TIME.replace(" ","<br/>")+"</td><td>"+item.TYPES+"</td><td class=\""+(item.TYPES=='转入'?"in":"out")+"\">"+(item.TYPES=='转入'?"+":"-")+formatmoney(item.AMOUNT,2)+"</td><td>"+item.STATUS+"</td></tr>");
            		    	});
            	    	}else{
            	    		$("#scbRecord").html("<tr><td colspan=\"4\">未查询到相关记录</td></tr>");
            	    		$("#scbRecord").parent().next().hide();
            	    	}
            		});
                });
            }
        });
    });
</script>
</html>