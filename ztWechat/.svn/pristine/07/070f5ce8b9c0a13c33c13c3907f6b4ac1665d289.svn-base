
$(function() {
    window._LIMIT_TOTAL_FEE = 1;
    var URL_PARAM = {};
    var Detail = {
        day1_profit_rate_arr: [],
        day7_profit_rate_arr: [],
        fund_type: 1, //基金类型
        /**
         * 万份收益
         */
        showDay1Profit: function(profit, fix) {
            return (parseInt(profit || 0, 10)/10000).toFixed(fix || 4);
        },
        /**
         * 七日年化收益
         */
        showDay7Profit: function(profit, fix) {
            return (parseInt(profit || 0, 10)/1000000).toFixed(fix || 4);
        },
        bindEvents: function() {
            var _this = this;
            /*基金的七日年化和万份收益切换展示*/
            $('.chart-nav').on('click', 'a', function(event) {
                event.preventDefault();
                $('.chart-nav a').removeClass('on');
                $(this).addClass('on');
                var chart = $('#chart').highcharts();
                $(".chart-tab a").removeClass('on').eq(2).addClass('on');
                if ($(this).data('profit_type') == 1) {
                    chart.series[0].update({
                        name: '万份收益：',
                        data: _this['day1_profit_rate_arr']
                    });
                } else {
                    chart.series[0].update({
                        name: '七日年化：',
                        data: _this['day7_profit_rate_arr']
                    });
                }
            });

            // 数据图tab操作
            $(".chart-tab").on('click', 'a', function(event) {
                event.preventDefault();
                $(this).addClass('on').siblings('a').removeClass('on');
                var duration = $(this).data('duration');
                var type = $('.chart-nav .on').index();
                var data_arr = [];
                if (type == 0) {
                    data_arr = duration == 7 ? _this['day1_profit_rate_arr'].slice(-7) : duration == 30 ? _this['day1_profit_rate_arr'].slice(-30) : _this['day1_profit_rate_arr'];
                } else {
                    data_arr = duration == 7 ? _this['day7_profit_rate_arr'].slice(-7) : duration == 30 ? _this['day7_profit_rate_arr'].slice(-30) : _this['day7_profit_rate_arr'];
                }
                var chart = $('#chart').highcharts();
                chart.series[0].update({
                    data: data_arr
                });
            });
        },

//        //渲染详情
        renderDetail: function(data) {
            var _this = this;
        var day1_profit_rate_arr = data['day1_profit_rate'];
        var day7_profit_rate_arr = data['day7_profit_rate'];
        var profit_rate_date_arr = data['profit_rate_date'];
        },



       //  画出基金走势图
        initSPChart:
        	function() {
            var _this = this;
            $.getJSON("scb/queryIncome", function(data) {
//                var retcode = parseInt(data.retcode || -1, 10);
//                var errcode = parseInt(data.errcode || 0, 10);
//                var retmsg = data.retmsg || '系统出了点小问题';
//                switch (retcode) {
//                    case 0:
                        window._FUND_DATA = data;
  //                      _this.renderDetail(data);
                        var data_arr = [];
                        //.split(',');
                        var day1_profit_rate_arr = data['day1_profit_rate'];
                        var day7_profit_rate_arr = data['day7_profit_rate'];
                        var profit_rate_date_arr = data['profit_rate_date'];
                        for (var i = day1_profit_rate_arr.length - 1; i >= 0; i--) {
                            temp = parseInt(day1_profit_rate_arr[i], 10);
                            temp1 = parseInt(day7_profit_rate_arr[i], 10);
                            _this['day7_profit_rate_arr'].push([new Date(profit_rate_date_arr[i].substring(0, 4), parseInt(profit_rate_date_arr[i].substring(4, 6), 10) - 1, profit_rate_date_arr[i].substring(6, 8)).getTime(), temp1]);
                            _this['day1_profit_rate_arr'].push([new Date(profit_rate_date_arr[i].substring(0, 4), parseInt(profit_rate_date_arr[i].substring(4, 6), 10) - 1, profit_rate_date_arr[i].substring(6, 8)).getTime(), temp]);
                        };
                        $('#chart').highcharts('StockChart', {
                            chart: {
                                borderColor: "#4572A7",
                                borderRadius: 5,
                                defaultSeriesType: "line",
                                ignoreHiddenSeries: !0,
                                spacing: [20, 10, 15, 10],
                                marginLeft: 40,
                                backgroundColor: "#FFFFFF",
                                plotBorderColor: "#C0C0C0",
                                resetZoomButton: {
                                    theme: {
                                        zIndex: 20
                                    },
                                    position: {
                                        align: "right",
                                        x: -10,
                                        y: 10
                                    }
                                }
                            },
                            credits: {
                                enabled: !1
                            },
                            legend: {
                                enabled: !1
                            },
                            navigator: {
                                enabled: !1
                            },
                            scrollbar: {
                                enabled: !1
                            },
                            rangeSelector: {
                                enabled: !1
                            },
                            tooltip: {
                                crosshairs: [{
                                    color: "#ffcbcc",
                                    width: 1
                                }, {
                                    color: "#ffcbcc",
                                    width: 1
                                }],
                                followPointer: !1,
                                useHTML: !0,
                                borderColor: "#ccc",
                                style: {},
                                formatter: function() {
                                    var r = "";
                                    r = '<span style="line-height: 20px;">时间：' + (new Date(this.x).getMonth()+1)+"-"+new Date(this.x).getDate() + '</span><br><span style="line-height: 20px;">';
                                    $.each(this.points, function(i, point) {
                                        r += this.series.name;
                                        r += this.series.name == "万份收益：" ? _this.showDay1Profit(point.y) : _this.showDay7Profit(point.y);
                                    });
                                    r += '</span><br>';
                                    return r;
                                }
                            },
                            series: [{
                                name: '万份收益：',
                                data: _this['day1_profit_rate_arr'],
                                type: 'area',
                                threshold: null,
                                tooltip: {
                                    valueDecimals: 2
                                },
                                fillColor: {
                                    linearGradient: {
                                        x1: 0,
                                        y1: 0,
                                        x2: 0,
                                        y2: 1
                                    },
                                    stops: [
                                        [0, Highcharts.getOptions().colors[0]],
                                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                                    ]
                                }
                            }],
                            xAxis: {
                                ordinal: false,
                                type: 'datetime',
                                labels: {
                                    formatter: function() {
                                        return Highcharts.dateFormat("%m-%d", this.value);
                                    }
                                }
                            },
                            yAxis: [{
                                offset: -550,
                                labels: {
                                    formatter: function() {
                                        var t = this.value >= 1000000 ? (this.value / 1000000).toFixed(1) + '%' : (this.value / 10000).toFixed(2); //大于等于7位数表示7日年化收益
                                        return '<span style="color:#606060">' + t + "</span>";
                                    }
                                },
                                showLastLabel: !0
                            }]
                        });
//                        break;
//                    default:
//                        $.showError(retmsg + '【' + retcode + '】');
//                        break;
//                }
            });
        },
            
            
        initShow: function() {
            var _this = this;
            // set highchats 全局设置
            Highcharts.setOptions({
                lang: {
                    rangeSelectorZoom: ""
                }
            });
            

            this.initSPChart();
        },
        init: function() {
//            URL_PARAM = $.getParameter();
           this.initShow();
            this.bindEvents();
       }
        
        
    };
    Detail.init();
 
        
//    CL_CallBack(function() {
//        window._FUND_DETAIL_COMMON.init();
//        window._FUND_DETAIL_COUPON.init();
//        Detail.init();
//    });

});
