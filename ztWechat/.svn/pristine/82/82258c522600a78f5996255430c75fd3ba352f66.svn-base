$(function() {
	var tabsSwiper = new Swiper('#tabs-container', {
		speed: 500,
		onSlideChangeStart: function () {
			$(".tabs .on").removeClass('on')
			$(".tabs li,.tabs a").eq(tabsSwiper.activeIndex).addClass('on')
		}
	})
	$(".tabs li,.tabs a").on('touchstart mousedown', function (e) {
		e.preventDefault()
		$(".tabs .on").removeClass('on')
		$(this).addClass('on')
		tabsSwiper.slideTo($(this).index())
	})
	$(".tabs li,.tabs a").click(function (e) {
		e.preventDefault()
	})
});


