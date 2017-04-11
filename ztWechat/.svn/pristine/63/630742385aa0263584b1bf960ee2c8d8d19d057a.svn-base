      var yieldRateList=null;
      var yieldRateFixedList=null;
      var yieldRateOnceList=null;
$(function () {
      //立即购买click
      $(".btnBuy").click(function(){
        if(!compareRecordRate()){
          $("#mask").show();
          $("#alertLayer").show();
        }else{
          url=$(".singleBtn.fl").eq(1).attr('href');//和蒙层保持一致
          window.location.href=url;
        }
      });
      //ajax请求
      var policyId=$("#policyId").val();
      $.ajax({
        url : 'zt/policy/queryPolicyFundIncomeList',
        type : 'post',
        dataType : 'json',
        data : {policyId:policyId},
        error : function () {
        },
        success : function (data) {
          //1.初始化pie画图
          changePolicyFundList();
          //2.加载数据
          var dateParam=new Array();
          for(items in data.policyProductList){
            var param=new Array();
            param.push(data.policyProductList[items].productName);
            param.push(parseFloat( data.policyProductList[items].productProportion));
            dateParam.push(param);
          }
          var chart = $('#fundcontainer').highcharts();
          chart.series[0].setData(dateParam);
          //3.动态变化5年按钮模块
          if(data.yield<5){
            for(var i=data.yield;i<5;i++){
              $('.chart-tab.chart-tab-new').find('a').eq(i).attr('class','unClick');
              $('.chart-tab.chart-tab-new').find('a').eq(i).attr('href','javascript:void(0)');
            }
          }
          //3.初始化line画图
          incomeInit();
          //4.记录数据
          yieldRateList=data.yieldRateList;
          yieldRateFixedList=data.yieldRateFixedList;
          yieldRateOnceList=data.yieldRateOnceList;
          //5.首次加载默认显示一年数据
          var lastTerm= parseFloat($("#lastTerm").val());
          changeYearData(lastTerm>5?5:lastTerm);
          //6.首次加载默认显示一次性投入画图
          changeType(2);
          
        }
      
    });
    });
    /**
     * 画组合基金占比图
     * @param policyProductList
     */
function changePolicyFundList( ){
    $('#fundcontainer').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: 0,
            plotShadow: false,
            backgroundColor: 'rgba(255, 255, 255, 0)'
        },
        credits:{
            enabled:false
        },
        title: {
            text: '基金组合',
            align: 'center',
            verticalAlign: 'middle',
            y: 35,
            style:{
                color: '#444',
                fontSize: '.32rem',
                font: 'Microsoft Yahei'
            }
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                dataLabels: {
                    enabled: true,
                    distance: -20,
                    style: {
                        color: '#fff',
                        font: 'normal, 10px, Microsoft Yahei',
                        textShadow:false
                    },
                    format: '{point.percentage:.1f} %'
                },
                startAngle: -90,
                endAngle: 90,
                center: ['50%', '75%']
            },
        },
        series: [{
            type: 'pie',
            name: '组合占比',
            innerSize: '50%',
            colors:[//颜色组，可多 ps:默认8个已满足需求
                '#BBDCF4',
                '#B5BCF1',
                '#FFD4DA',
                '#FEC2B5',
                '#BBDCF4',
                '#B5BCF1',
                '#FFD4DA',
                '#FEC2B5',
            ],
            data: [ ['Firefox',   66.66],
                    ['IE',       26.66],
                    ['Chrome', 16.66],
                    ['Safari',    10.66]]
        }]
    });
}
/**
 * 初始化line画图
 */
function incomeInit(){
  $('#highcharts-0').highcharts({
    chart: {
      type: 'spline'
  },
    title: {
        text: ''
    },
    subtitle: {
        text: ''
    },
    xAxis: {
        tickInterval: 30 * 24 * 3600 * 1000, // 30 day
        tickWidth: 0,
        gridLineWidth: 1,
        ordinal: false,
        type: 'datetime',
        labels: {
          align:"left",
          autoRotation:[90],
//            step:2,
            formatter: function() {
//                return Highcharts.dateFormat("%y-%m", this.value);
              return Highcharts.dateFormat("", this.value);
            }
        }
    },
    yAxis: [{ // left y axis
        title: {
            text: null
        },
        labels: {
            align: 'left',
            x: 3,
            y: 16,
            format: '{value:.,0f}%'
        },
        showFirstLabel: false
    }],
    legend: {
      enabled:false
    },
    credits:{
      enabled:false
    },
    tooltip: {
      crosshairs:true,
      shared: true,
      valueDecimals: 4,//保留小数点
     formatter: function() {
                              var r = "";
                              r = '<span style="line-height: 20px;">时间：' + new Date(this.x).getFullYear()+"-"+(new Date(this.x).getMonth()+1) + '</span>';
                              $.each(this.points, function(i, point) {
                                if(this.point.series.name=='每月定投策略收益率'||this.point.series.name=='一次性投入策略收益率'){
                                  r += '<br><span style="line-height: 20px;color:#FF7300" >●</span> 策略收益率 ：'+this.point.y.toFixed(2)+'%';
                                  $("#incomePolicy").html(' <span class="dot-1 orange">●</span>策略收益率：<span class="orange num-1">'+this.point.y.toFixed(2)+'%</span>');
                                }else{
                                  r+= '<br><span style="line-height: 20px;color:#5D9CEC">●</span> 比较基准 ：'+this.point.y.toFixed(2)+'%';
                                  $("#incomeCompare").html(this.point.y.toFixed(2)+'%');
                                  $("#compareShow").show();
                                }
                              });
                              r += '</span><br>';
                              return r;
                          } 
  },
  plotOptions: {
    series: {
        marker: {
            enabled: false
        }
    }
},
    series: [{
        name: '比较基准',
        color:'#66a5df',
        lineWidth: 2,
        data:[],
        marker: {
            radius: 2
        }
    }, {
        name: '一次性投入策略收益率',
        color:'#FF7300',
        lineWidth: 2,
        data:[],
        marker: {
            radius: 2
        }
    }, {
      name: '每月定投策略收益率',
      color:'#FF7300',
      lineWidth: 2,
      data:[],
      marker: {
          radius: 2
      }
  }]
});
}
/**
* 最大回撤率验证
*/
function compareRecordRate(){
  var recordRate=$("#recordRate").val();
  var minYieldRate=$("#minYieldRate").val();
  if(parseFloat(minYieldRate)>parseFloat(recordRate)){
    return false;
  }else{
    return true;
  }
}
/**
 * 一次性投入/每月定投
 */
function changeType(type,obj){
  var incomeChart =$('#highcharts-0').highcharts();
  if(type==1){//一次性投入
    $(".tabs").find('a').eq(0).addClass('on');
    $(".tabs").find('a').eq(1).removeClass('on');
    incomeChart.series[2].hide();
    incomeChart.series[1].show();
  }else{//每月定投
    $(".tabs").find('a').eq(1).addClass('on');
    $(".tabs").find('a').eq(0).removeClass('on');
    incomeChart.series[1].hide();
    incomeChart.series[2].show();
  }
}

function changeYearData(year){
  //更改样式
  $(".chart-tab.chart-tab-new").find('a').removeClass('on');
  $(".chart-tab.chart-tab-new").find('a').eq(year-1).addClass('on');
  //加载对应数据
  var incomeChart =$('#highcharts-0').highcharts();
  incomeChart.series[0].setData(yieldRateList[year-1]);
  incomeChart.series[1].setData(yieldRateOnceList[year-1]);
  incomeChart.series[2].setData(yieldRateFixedList[year-1]);
}