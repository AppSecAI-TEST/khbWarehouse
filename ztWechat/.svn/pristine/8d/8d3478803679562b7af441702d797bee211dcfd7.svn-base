var myScroll, pullDownEl, pullDownOffset, pullUpEl, pullUpOffset, generatedCount = 0;

function loaded() {
    pullDownEl = document.getElementById('pullDown'); //下拉刷新对象
    pullDownOffset = pullDownEl.offsetHeight;
	if(document.getElementById('pullUp')){
		pullUpEl = document.getElementById('pullUp');     //上拉记载更多对象
	    pullUpOffset = pullUpEl.offsetHeight;

	    myScroll = new iScroll('wrapper', {
	        useTransition: true,          //表示是否使用css3中的过度效果，默认为true
	        topOffset: pullDownOffset,    //pullDown区间高度
	        momentum:true,                //启用或禁用惯性
	        hScrollbar:false,             //false为隐藏水平方向上的滚动条
	        vScrollbar: false,            //false为隐藏垂直方向上的滚动条
	        bounce:true,                  //启用或禁用边界的反弹
	        bounceLock:true,              //
	        //刷新时执行
	        onRefresh : function() {
	            /*if (pullDownEl.className.match('loading')) {
	                pullDownEl.className = '';
	                pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
	            } else */
	            if (pullUpEl.className.match('loading')) {
	                pullUpEl.className = '';
	                pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
	            }
	        },
	        //onScrollMove：主要表示根据用户下拉或上拉刷新的高度值，来显示不通的交互文字
	        onScrollMove : function() { //手指触摸事件
	          //this.y表示手指下拉的高度
	            /*if (this.y > 15 && !pullDownEl.className.match('flip')) {
	                //pullDownEl.className = 'flip';
	                //pullDownEl.querySelector('.pullDownLabel').innerHTML = '松手开始更新...';
	                this.minScrollY = 0;
	            } else if (this.y < 15 && pullDownEl.className.match('flip')) {
	                //pullDownEl.className = '';
	                //pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
	                this.minScrollY = -pullDownOffset;
	            } else */
	            if (this.y < (this.maxScrollY - 15) //15表示下拉或上拉的长度，长度大于15时就刷新，显示不同的提示
	                && !pullUpEl.className.match('flip')) {
	                pullUpEl.className = 'flip';
	                pullUpEl.querySelector('.pullUpLabel').innerHTML = '松手开始更新...';
	                this.maxScrollY = this.maxScrollY;
	            } else if (this.y > (this.maxScrollY + 15)
	                && pullUpEl.className.match('flip')) {
	                pullUpEl.className = '';
	                pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
	                this.maxScrollY = pullUpOffset;
	            }
	        },
	        //onScrollEnd：表示用户下拉刷新或上拉刷新完，放开手指时所显示的不同的文字交互
	        onScrollEnd : function() {
	            /*if (pullDownEl.className.match('flip')) {
	                //pullDownEl.className = 'loading';
	                //pullDownEl.querySelector('.pullDownLabel').innerHTML = '努力加载中...';
	                //pullDownAction();	// Execute custom function (ajax call?)
	            } else */
	            if (pullUpEl.className.match('flip')) {
	                pullUpEl.className = 'loading';
	                pullUpEl.querySelector('.pullUpLabel').innerHTML = '努力加载中...';
	                pullUpAction(); // Execute custom function (ajax call?)所要执行的刷新方法，分页
	            }
	        }
	    });
	}

    setTimeout(function () { document.getElementById('wrapper').style.left = '0'; }, 800);
}
//touchmove：表示手指在屏幕上滑动连续触发的事件
document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
//load事件：仅在所有资源都完全加载后再被触发。DOMContentLoaded：DOM加在之后及资源加载之前被触发
//document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded, 200); }, false);