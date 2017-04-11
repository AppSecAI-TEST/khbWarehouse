//路径配置
require.config({
	paths: {
		echarts: 'http://echarts.baidu.com/build/dist'
	}
});
// 使用
require(
	[
		'echarts',
		'echarts/chart/line' // 按需加载
	],
	function (ec) {
		// 基于准备好的dom，初始化echarts图表
		var myChart = ec.init(document.getElementById('main'));
		//数据准备
		var mydata = {
			"time" : [],
			"money" : [3.085, 3.402, 3.295, 3.385, 3.181, 3.585,3.321]
		};

		//设置日期，当前日期的前七天
		var myDate = new Date(); //获取今天日期
		myDate.setDate(myDate.getDate() - 7);
		var dateTemp;
		var flag = 1;
		for (var i = 0; i < 7; i++) {
			dateTemp = (myDate.getMonth()+1)+"-"+myDate.getDate();
			mydata.time.push(dateTemp);
			myDate.setDate(myDate.getDate() + flag);
		}

		if (typeof Array.prototype['max'] == 'undefined') {
			Array.prototype.max = function() {//最大值
				var max = this[0];
				var len = this.length;
				for (var i = 1; i < len; i++){
					if (this[i] > max) {
						max = this[i];
					}
				}
				return max;
			}
			Array.prototype.min = function() {//最小值
				var min = this[0];
				var len = this.length;
				for (var i = 1; i < len; i++){
					if (this[i] < min){
						min = this[i];
					}
				}
				return min;
			}
		}
		var maxvalue = mydata.money.max();
		var minvalue = mydata.money.min();

		//配置
		option = {
			title : {
				text: '',
				textStyle:{
					fontSize: 12,
					color: '#a2a2a2'
				}
			},
			grid: {
				x: '15%',
				x2: '7.5%',
				y: '15%',
				y2: '10%'
			},
			xAxis : [
				{
					type : 'category',
					boundaryGap : false,
					axisLine:{
						lineStyle:{
							color: '#e7e7e7',
							width: 0
						}
					},
					axisTick:{
						lineStyle:{
							color: '#e7e7e7'
						}
					},
					axisLabel : {
						textStyle:{
							color: '#a2a2a2'
						}
					},
					splitLine:{
						lineStyle:{
							color: '#e7e7e7'
						}
					},
					data : mydata.time
				}
			],
			yAxis : [
				{
					type : 'value',
					axisLine:{
						lineStyle:{
							color: '#e7e7e7',
							width: 0
						}
					},
					axisTick:{
						show: true,
						lineStyle:{
							color: '#e7e7e7'
						}
					},
					axisLabel : {
						textStyle:{
							color: '#a2a2a2'
						}
					},
					splitLine:{
						lineStyle:{
							color: '#e7e7e7'
						}
					},
					splitNumber: 5,
					max: maxvalue+0.5,
					min: minvalue -0.5
				}
			],
			series : [
				{
					name:'今天',
					type:'line',
					data: mydata.money,
					markPoint : {
						clickable:false,
						data : [
							{
								name: '今天',
								xAxis: mydata.time[mydata.time.length-1],
								yAxis: mydata.money[mydata.money.length
								-1],
								value: mydata.money[mydata.money.length
								-1],
								symbol: '../images/tick-bg.png',
								symbolSize : [19,8],
								//symbolRotate : 8,
								itemStyle: {        // 数据级个性化折线样式
									normal: {
										color: '#f46e26'
									}
								}
							}
						]
					}
					/*itemStyle: {
					 normal: {
					 areaStyle: {
					 // 区域图，纵向渐变填充
					 color : (function (){
					 var zrColor = require('zrender/tool/color');
					 return zrColor.getLinearGradient(
					 0, 200, 0, 400,
					 [[0, 'rgba(249,108,60,0.8)'],[0.8, 'rgba(255,255,255,0.1)']]
					 )
					 })()
					 }
					 }
					 }*/
				}
			]
		};

		// 为echarts对象加载数据
		myChart.setOption(option);
	}
)