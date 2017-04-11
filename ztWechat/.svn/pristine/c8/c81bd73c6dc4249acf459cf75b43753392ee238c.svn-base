$(function() {
	/*菜单栏*/
	$('.icon_menu').click(function(){
		if($(this).hasClass('cur_menu')){
			$(this).removeClass('cur_menu');
		}else{
			$(this).addClass('cur_menu');
		}
		$(this).siblings('.submenu').slideToggle(50);
		$(this).parent().siblings().children('.submenu').hide(50);
		$(this).parent().siblings().find('.icon_menu').removeClass('cur_menu');
	});
	/*计算区域高度 控制菜单栏的显示隐藏*/
	var footH = $('.foo').height();
	var bodyH = $(document.body).height();
	var boxH = $('#box').height();
	boxH = bodyH-footH > boxH ? bodyH-footH : boxH;
	$('#box').height(boxH);
	//document.getElementById('box').addEventListener('touchstart',function(){
	//	$('.submenu').hide();
	//	$('.icon_menu').removeClass('on');
	//},false);
	/*计算推广区域高度*/
	var headH = $('header').height();
	var sectionH = bodyH - footH - headH;
	$('section').height(sectionH);
	//头部广告点击关闭
	$(".financial-ad .financialClosed").click(function(){
		$(".financial-ad").hide();
	});
	//切换效果
	$(".tab_nav li").click(function(){
		var n = $(this).index();
		$(this).addClass("on").siblings().removeClass("on");
		$(".tab_con blockquote:eq("+n+")").css({"display":"block"}).siblings().css({"display":"none"});
		//$(this).parent().parent().$(".tab_con blockquote:eq("+n+")").css({"display":"block"}).siblings().css({"display":"none"});
	});
	//懒猫金融服务协议checkbox
	$('.icon-checkbox').on('click',function(){
		if($(this).siblings("input[type='checkbox']").is(':checked')){
			$(this).removeClass('icon-unchecked');
			$(this).addClass('icon-unchecked');
			$(this).siblings("input[type='checkbox']").removeAttr('checked')
		}
		else{
			$(this).removeClass('icon-unchecked');
			$(this).siblings("input[type='checkbox']").attr('checked','checked')
		}
	});
	//显示/隐藏
	event.stopPropagation();
	/*$(".icon-questionmark").click(function(event){
		event.stopPropagation();
		if($(this).parent().siblings(".panel").css("display")=='none'){
			$(this).addClass("icon-questionmarked");
			$(this).parent().siblings(".panel").show();
		}else{
			$(this).parent().siblings(".panel").hide();
			$(".icon-questionmarked").removeClass("icon-questionmarked");
		}
		/!*$(".panel").hide();
		 $(".panel").eq(index).show();*!/
	});*/
	$(function(){
		$(".icon-questionmark").each(function(index){
			$(this).click(function(){
				event.stopPropagation();
				$(".panel").css('display','none');
				$(".panel").eq(index).css('display','block');
				$(".icon-questionmark").removeClass('icon-questionmarked');
				$(".icon-questionmark").eq(index).addClass('icon-questionmarked');
			})
		})
	})
	$(document).click(function(){
		$(".icon-questionmark").parent().siblings(".panel").hide();
		$(".icon-questionmarked").removeClass("icon-questionmarked");
	});
	//弹窗
	var x;
	var y;
	function xy(){
		x=($(window).width()-$("#alertLayer,#alertLayer-6,#alertLayer-7").width())/2;
		y=($(window).height()-$("#alertLayer,#alertLayer-6,#alertLayer-7").height())/2;
		$("#mask").height($(window).height());
		$("#alertLayer,#alertLayer-6,#alertLayer-7").animate({"left":x,"top":y},0);
	}
	xy();
	$(window).resize(function(){
		xy();
	});
	var c;
	var d;
	function cd(){
		c=($(window).width()-$("#alertLayer-3,#alertLayer-4,#alertLayer-5,#alertLayer-8").width())/2;
		d=($(window).height()-$("#alertLayer-3,#alertLayer-4,#alertLayer-5,#alertLayer-8").height())/3;
		$("#mask").height($(window).height());
		$("#alertLayer-3,#alertLayer-4,#alertLayer-5,#alertLayer-8").animate({"left":c,"top":d},0);
	}
	cd();
	$(window).resize(function(){
		cd();
	});
	var showDialogBottom = function(){
		$("#mask").css("height",$(document).height());
		$("#mask").css("width",$(document).width());
		$("#mask").show();
	};
	//显示/隐藏弹出层
	$(".btnClick").click(function(){
		showDialogBottom();
		$("#alertLayer,.alertLayer").show();
		$("body").css("position","fixed");
	});
	$(".btnClick0").click(function(){
		showDialogBottom();
		$(".alertLayer").show();
		$("body").css("position","fixed");
	});
	$(".btnClick-1").click(function(){
		showDialogBottom();
		$("#alertLayer-1,.alertLayer").show();
		$("body").css("position","fixed");
	});
	$(".btnClick-2").click(function(){
		showDialogBottom();
		$("#alertLayer-2").show();
		$("body").css("position","fixed");
	});
	$(".btnClick-3").click(function(){
		showDialogBottom();
		$("#alertLayer-3").show();
		$("body").css("position","fixed");
	});
	$(".btnClick-4").click(function(){
		showDialogBottom();
		$("#alertLayer-4").show();
		$("body").css("position","fixed");
	});
	$(".btnClick-5").click(function(){
		showDialogBottom();
		$("#alertLayer-5").show();
		$("body").css("position","fixed");
	});
	$(".btnClosed").click(function(){
		$("#my-alertLayer,#alertLayer,#alertLayer-1,#alertLayer-2,#alertLayer-3,#alertLayer-4,#alertLayer-5,#alertLayer-6,#alertLayer-7,#alertLayer-8,.alertLayer").hide();
		$("body").css("position","initial");
		$("#mask,#share").hide();
		$(".mask-group").hide();

	});
	$(".a-know").click(function(){
		$("#financialMask").hide();
		$("body").css("position","initial");
	});
    //进度条
	var i =0;
	var ms = 3000; //变量MS: 从0%到100%需要的毫秒数
	var time = setInterval(function(){
		$("#progressBar .finish").css("width",i+"%");i=i+(1000/ms);
		if(i>100){
			clearInterval(time);
		}},10);

	//点击分享消失
	$(".a-shareTo").click(function(){
		$(".shareTo").hide();
		$("body").css("position","initial");
	});
    //哆啦宝弹出层
	$(function(){
		$('.dlbDetail').click(function(){
			$('.dlb-popup').show();
		})
		$('.dlbClosed').click(function(){
			$('.dlb-popup').hide();
		})
	})
	//免费旅游"+""-"
	$(".numArea a").click(function(){
		var x = $(this).text();
		var num = Number($(".numArea .num").text());
		if(x=="+"){
			num++;
			$(".numArea .num").text(num);
		}
		if(x=="-" && num>1){
			num--;
			$(".numArea .num").text(num);
		}
		num = x == ("+") ? num++ : num--;
		$(".numArea .num").text(num);
	})
	$(".icon-plus").click(function(){
		var num = Number($(".numArea .num").text());
		num++;
		$(".numArea .num").text(num);
	});
	$(".icon-minus").click(function(){
		var num = Number($(".numArea .num").text());
		if(num>1){
			var num = Number($(".numArea .num").text());
			num--;
			$(".numArea .num").text(num);
		};
	});
	//免费旅游--下拉弹窗传值
	$('.ui-select .selectText').click(function(){
		$(this).siblings('.selectBox').show()
			   .siblings('.icon').addClass('icon-arrow-u').removeClass('icon-arrow-d')
	});
	$(".selectBox li").click(function(){
		$(this).parent().hide();
		$(this).parent().siblings(".icon").removeClass('icon-arrow-u').addClass('icon-arrow-d')
			   .siblings('.selectText').text($(this).text())
	})

});


