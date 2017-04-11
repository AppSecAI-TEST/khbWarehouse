<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yeepay.g3.app.lmweChat.utils.LmConstants" %>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String sysVersion = LmConstants.sysVersion;
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
        <link rel="stylesheet" href="static/css/LM-common.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/LM-app.css?v=<%=sysVersion %>">
        <link rel="stylesheet" href="static/css/icon-style.css?v=<%=sysVersion %>">
    <link rel="stylesheet" href="static/css/layer.css?v=<%=sysVersion %>"/>
    <link rel="stylesheet" href="static/css/older/idangerous.swiper.css?v=<%=sysVersion %>"/>
    <link rel="stylesheet" href="static/css/older/pull.css?v=<%=sysVersion %>"/>
    <script type="text/javascript" src="static/js/jquery.js"></script>
    <script type="text/javascript" src="static/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="static/js/messages.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/scb/idangerous.swiper.min.js"></script>
    <script type="text/javascript" src="static/js/layer.m.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/scb/common.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/scb/iScroll.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/scb/pull.js?v=<%=sysVersion %>"></script>
    <script type="text/javascript" src="static/js/common/format_common.js?v=<%=sysVersion %>"></script>
    <link href="static/css/calendar/mobiscroll_002.css?v=<%=sysVersion %>" rel="stylesheet" type="text/css">
    <link href="static/css/calendar/mobiscroll.css?v=<%=sysVersion %>" rel="stylesheet" type="text/css">
    <link href="static/css/calendar/mobiscroll_003.css?v=<%=sysVersion %>" rel="stylesheet" type="text/css">
    <script src="static/js/calendar/mobiscroll_002.js?v=<%=sysVersion %>" type="text/javascript"></script>
    <script src="static/js/calendar/mobiscroll_004.js?v=<%=sysVersion %>" type="text/javascript"></script>
    <script src="static/js/calendar/mobiscroll.js?v=<%=sysVersion %>" type="text/javascript"></script>
    <script src="static/js/calendar/mobiscroll_003.js?v=<%=sysVersion %>" type="text/javascript"></script>
    <script src="static/js/calendar/mobiscroll_005.js?v=<%=sysVersion %>" type="text/javascript"></script>
  <title>生财宝交易记录</title>
    <style>
    #wrapper{ top:55px;}
    </style>
    <script type="text/javascript">
    var pageIndex = 1;
    var pageTotal = 1;
    $(function(){
	    $.post("scb/queryScbRecord",{pageIndex:pageIndex},function(data){
	    	pageTotal = data.pageTotal;
	    	if(data != '' && data.lstMaps != '' && data.lstMaps != null){
	    		if(pageTotal > pageIndex){
	    			$("#pullUp").attr("style","");
	    		}
		    	$.each(data.lstMaps,function(i,item){
		    		$("#scbRecord").append("<tr><td>"+item.TIME.replace(" ","<br/>")+"</td><td>"+item.TYPES+"</td><td class=\""+(item.TYPES=='转入'?"in":"out")+"\">"+(item.TYPES=='转入'?"<span class=\"orange\">+":"<span class=\"green\">-")+formatmoney(item.AMOUNT,2)+"</span></td><td>"+item.STATUS+"</td></tr>");
		    	});
	    	}else{
	    		$("#scbRecord").html("<tr><td colspan=\"4\">未查询到相关记录</td></tr>");
	    	}
		});
    });
    </script>
    
</head>
<body>
<div id="box">
    <div class="statelist">
        <input type="hidden" value="0" name="state" id="tradeType"/>
        <input type="hidden" value="0" name="state" id="tradeStatus"/>
    </div>
    <div class="scbtransrecord tc">
        <!--div class="bg-gray selectime-wrap clearfix">
            <div class="fl ml3 iconfont">
                <span class="color-50 mr5 time-label">起止时间</span>
                <a class="input-time-wrap">
                    <input value="" class="input-time" readonly="readonly" name="appDate" id="appDateBegin" type="text">
                    <span class="icon icon-rili"></span>
                </a>
                <span >-</span>
                <a class="input-time-wrap">
                    <input value="" class="input-time" readonly="readonly" name="appDate" id="appDateEnd" type="text">
                    <span class="icon icon-rili"></span>
                </a>
            </div>
            <a href="javascript:void(0)" class="fr mr3 a-filter" id="filter">筛选</a>
        </div  -->
        
        <div class="date-bar scbDate-bar">
	         <span class="time-label">起止时间</span>
	         <a class="input-time-wrap">
	             <span class="icon icon-date"></span>
	             <input value="" class="input-time" readonly="readonly" name="appDate" id="appDateBegin" type="text">
	             
	         </a>
	         <span >-</span>
	         <a class="input-time-wrap">
	             <span class="icon icon-date"></span>
                 <input value="" class="input-time" readonly="readonly" name="appDate" id="appDateEnd" type="text">
	         </a>
	         <a href="javascript:void(0)" class="a-filter" id="filter">筛选</a>
	     </div>
        
        
        
        
        
        <div id="wrapper" style="bottom: 0px;">
    		<div id="scroller" class="scbDetail bg-white clearfix">
	        	<div id="pullDown"></div>
	        	<table id="thelist"  width="100%">
	           		<thead> 
		                <tr>
		                    <th width="28%">交易时间</th>
		                    <th width="25%">类型</th>
		                    <th>金额 (元)</th>
		                    <th width="25%">状态</th>
		                </tr>
	            	</thead>
	            	<tbody id="scbRecord">
	            	</tbody>
	        	</table>
		        <div id="pullUp" style="display:none;">
		            <span class="pullUpIcon"></span>
		            <span class="pullUpLabel">上拉加载更多...</span>
		        </div>
    		</div>
    	</div>
    </div>
</div>
</body>
<script>
	function pullUpAction () {
	    Ajax(++pageIndex);
	}
	
	function Ajax(pageIndex){
	    if(pageTotal >= pageIndex){
	    	var tradeType = $("#tradeType").val();
		    var tradeStatus = $("#tradeStatus").val();
		    var startTime = $("#appDateBegin").val();
		    var endTime = $("#appDateEnd").val();
		    $.post("scb/queryScbRecord",{tradeType:tradeType,tradeStatus:tradeStatus,startTime:startTime,endTime:endTime,pageIndex:pageIndex},function(data){
		    	if(data != '' && data.lstMaps != '' && data.lstMaps != null){
	    			$.each(data.lstMaps,function(i,item){
			    		$("#scbRecord").append("<tr><td>"+item.TIME.replace(" ","<br/>")+"</td><td>"+item.TYPES+"</td><td class=\""+(item.TYPES=='转入'?"in":"out")+"\">"+(item.TYPES=='转入'?"<span class=\"orange\">+":"<span class=\"green\">-")+formatmoney(item.AMOUNT,2)+"</span></td><td>"+item.STATUS+"</td></tr>");
			    	});
	    			myScroll.refresh();
		    	}
			});
		}else{
			$('.pullUpLabel').html('亲，没有更多记录了!');
    		$('.pullUpIcon').hide();
		}
	}
    $(function(){
        $('.swiper-wrapper').height('auto');
        $('.swiper-slide').height('200px');
        $('.swiper-slide-active').height('auto');
        var tabsSwiper = new Swiper('.swiper-container',{
            speed:500,
            onSlideChangeStart: function(){
                $(".tabs .active").removeClass('active');
                $(".tabs a").eq(tabsSwiper.activeIndex).addClass('active');
                $('.swiper-wrapper').height('auto');
                $('.swiper-slide').height('200px');
                $('.swiper-slide-active').height('auto');
            }
        });
        $(".tabs a").on('touchstart mousedown',function(e){
            e.preventDefault()
            $(".tabs .active").removeClass('active');
            $(this).addClass('active');
            tabsSwiper.swipeTo($(this).index());
        });

        $(".tabs a").click(function(e){
            e.preventDefault();
        });

        var onOff = true;
        $('#filter').click(function(){
            if(onOff){
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
                                            '<li><span>进行中</span></li>' +
                                            '<li><span>资金在途</span></li>'+
                                        '</ul>' +
                                    '</div>' +
                            '</div>',
                    end:function(){
                        onOff = true;
                        var tradeType = $("#tradeType").val();
                        var tradeStatus = $("#tradeStatus").val();
                        var startTime = $("#appDateBegin").val();
                        var endTime = $("#appDateEnd").val();
                        pageIndex = 1;
                        $.post("scb/queryScbRecord",{tradeType:tradeType,tradeStatus:tradeStatus,startTime:startTime,endTime:endTime,pageIndex:pageIndex},function(data){
                        	$("#scroller").css("top", "0px");
                        	$("#scbRecord").html("");
                        	$("#pullUp").attr("style","display:none;");
                        	$(".pullUpLabel").html("上拉加载更多...");
                        	pageTotal = data.pageTotal;
                        	if(data != '' && data.lstMaps != '' && data.lstMaps != null){
                        		if(pageTotal > pageIndex){
                	    			$("#pullUp").attr("style","");
                	    		}
                		    	$.each(data.lstMaps,function(i,item){
                		    		$("#scbRecord").append("<tr><td>"+item.TIME.replace(" ","<br/>")+"</td><td>"+item.TYPES+"</td><td class=\""+(item.TYPES=='转入'?"in":"out")+"\">"+(item.TYPES=='转入'?"<span class=\"orange\">+":"<span class=\"green\">-")+formatmoney(item.AMOUNT,2)+"<span></td><td>"+item.STATUS+"</td></tr>");
                		    	});
                	    	}else{
                	    		$("#scbRecord").html("<tr><td colspan=\"4\">未查询到相关记录</td></tr>");
                	    	}
                		});
                    }
                });
                onOff = false;
                $('.layermbox').css({'top':'50px'});
                $('.laymshade').css({'top':'50px'});

                /*事件代理*/
                $('body').delegate('.filt-dwcon li','click',function(){
                    $(this).parents('ul').children('li').removeClass('cur');
                    $(this).toggleClass('cur');
                    var index = $(this).index();
                    if($(this).hasClass('cur')){
                        if($(this).parents('ul').hasClass('filter-lft')){
                            $('.statelist input[name=state]').eq(0).val(index);
                        }else{
                            $('.statelist input[name=state]').eq(1).val(index);
                        }
                    }else{
                        if($(this).parents('ul').hasClass('filter-lft')){
                            $('.statelist input[name=state]').eq(0).val(index);
                        }else{
                            $('.statelist input[name=state]').eq(1).val(index);
                        }
                    }
                });
                $('.filt-dwcon li').click(function(){})
                for(var i=0; i<4; i++){
                    var state = $('.statelist input[name=state]').eq(i).val();
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
                        case '4' :
                            $('.filt-dwcon ul').eq(i).children('li').removeClass('cur');
                            $('.filt-dwcon ul').eq(i).children('li').eq(4).addClass('cur');
                            break;
                    }
                }
            }else{
                layer.closeAll();
                onOff = true;
            }
        });
    });
</script>
<script type="text/javascript">
    $(function () {
        var currYear = (new Date()).getFullYear();
        var opt={};
        opt.date = {preset : 'date'};
        opt.default = {
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
        $("#appDateBegin").mobiscroll($.extend(opt['date'], opt['default']));
        $("#appDateEnd").focusin(function(){
            var vals=$('#appDateBegin').val();
            if(vals==''){
                layer.open({
                    btn: ['OK'],
                    content:'<p class=\"color-yellow tc\">请选择开始日期</p>'
                })
            }else{
                $("#appDateEnd").unbind('focusin').mobiscroll($.extend(opt['date'], opt['default']));
                $(this).unbind('change');
                $(this).change(function(){
                	var tradeType = $("#tradeType").val();
                    var tradeStatus = $("#tradeStatus").val();
                    var startTime = $("#appDateBegin").val();
                    var endTime = $("#appDateEnd").val();
                    pageIndex = 1;
                    $.post("scb/queryScbRecord",{tradeType:tradeType,tradeStatus:tradeStatus,startTime:startTime,endTime:endTime,pageIndex:pageIndex},function(data){
                    	$("#scbRecord").html("");
                    	$("#pullUp").attr("style","display:none;");
                    	$(".pullUpLabel").html("上拉加载更多...");
                    	$("#scroller").css("top", "0px");
                    	pageTotal = data.pageTotal;
                    	if(data != '' && data.lstMaps != '' && data.lstMaps != null){
                    		if(pageTotal > pageIndex){
            	    			$("#pullUp").attr("style","");
            	    		}
            		    	$.each(data.lstMaps,function(i,item){
            		    		$("#scbRecord").append("<tr><td>"+item.TIME.replace(" ","<br/>")+"</td><td>"+item.TYPES+"</td><td class=\""+(item.TYPES=='转入'?"in":"out")+"\">"+(item.TYPES=='转入'?"<span class=\"orange\">+":"<span class=\"green\">-")+formatmoney(item.AMOUNT,2)+"<span></td><td>"+item.STATUS+"</td></tr>");
            		    	});
            	    	}else{
            	    		$("#scbRecord").html("<tr><td colspan=\"4\">未查询到相关记录</td></tr>");
            	    	}
            		});
                });
            }
        })
    });
</script>
</html>