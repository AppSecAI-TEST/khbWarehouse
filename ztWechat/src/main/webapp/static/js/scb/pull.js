var myScroll, pullDownEl, pullDownOffset, pullUpEl, pullUpOffset, generatedCount = 0;

function loaded() {
    pullDownEl = document.getElementById('pullDown');
    pullDownOffset = pullDownEl.offsetHeight;
	if(document.getElementById('pullUp')){
		pullUpEl = document.getElementById('pullUp');
	    pullUpOffset = pullUpEl.offsetHeight;

	    myScroll = new iScroll('wrapper', {
	        useTransition: true,
	        topOffset: pullDownOffset,
	        momentum:true, 
	        hScrollbar:false, 
	        vScrollbar: false,
	        bounce:true,
	        bounceLock:true,
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
	        onScrollMove : function() {
	            /*if (this.y > 15 && !pullDownEl.className.match('flip')) {
	                //pullDownEl.className = 'flip';
	                //pullDownEl.querySelector('.pullDownLabel').innerHTML = '松手开始更新...';
	                this.minScrollY = 0;
	            } else if (this.y < 15 && pullDownEl.className.match('flip')) {
	                //pullDownEl.className = '';
	                //pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
	                this.minScrollY = -pullDownOffset;
	            } else */
	            if (this.y < (this.maxScrollY - 15)
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
	        onScrollEnd : function() {
	            /*if (pullDownEl.className.match('flip')) {
	                //pullDownEl.className = 'loading';
	                //pullDownEl.querySelector('.pullDownLabel').innerHTML = '努力加载中...';
	                //pullDownAction();	// Execute custom function (ajax call?)
	            } else */
	            if (pullUpEl.className.match('flip')) {
	                pullUpEl.className = 'loading';
	                pullUpEl.querySelector('.pullUpLabel').innerHTML = '努力加载中...';
	                pullUpAction(); // Execute custom function (ajax call?)
	            }
	        }
	    });
	}

    setTimeout(function () { document.getElementById('wrapper').style.left = '0'; }, 800);
}

document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);

document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded, 200); }, false);