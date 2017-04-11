$(function () {
      //ajax请求
      $.ajax({
        url : 'zt/introduce/queryIncomeForProduct',
        type : 'post',
        dataType : 'json',
        error : function () {
        },
        success : function (data) {
          //1.初始化line画图
          incomeInit();
          //2.加载数据
          var chart=$('#curve').highcharts();
          chart.series[0].setData(data.yieldRatePreferredList);
          chart.series[1].setData(data.yieldRateEodList);
        }
      
    });
    });
/**
 * 初始化line画图
 */
function incomeInit(){
  $('#curve').highcharts({
    chart: {
      type: 'line',
      backgroundColor:'#fff1c9'
  },
  title: {
      text: ''
  },
  subtitle: {
      text: ''
  },
  legend: {
      enabled: false
  },
    xAxis: {
        tickInterval: 30 * 24 * 3600 * 1000, // 30 day
        tickWidth: 0,
        type: 'datetime',
      lineColor:'#fddf98',
      gridLineColor: '#fddf98',
      gridLineWidth: 1,
        labels: {
          y: 30, //x轴刻度往下移动20px
          style: {
              color: '#444',//颜色
              fontSize:'12px',  //字体
              tickColor:'#f6a700'
          },
          align:"left",
          autoRotation:[90],
            formatter: function() {
                return Highcharts.dateFormat('', this.value);
            }
        }
    },
    yAxis: {
      title: {
          text: ''
      },
      labels: {//y轴刻度文字标签
          x: -10, //x轴刻度往下移动20px
          style: {
              color: '#444',//颜色
              fontSize:'14px',  //字体
              tickColor:'#f6a700'
          },
          format: '{value:.,0f}%'
      },
      plotLines: [{//区域划分线，0刻度
          value: 0,
          width: 1
      }],
      gridLineColor: '#fddf98',
      gridLineWidth: 1
  },
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
                                if(this.point.series.name=='优选基金组合'){
                                  r += '<br><span style="line-height: 20px;color:#f6a800" >●</span> 优选基金组合 ：'+this.point.y+'%';
//                                  $("#incomePolicy").html(this.point.y+'%');
                                }else{
                                  r+= '<br><span style="line-height: 20px;color:#fc6d52">●</span> 上证综指 ：'+this.point.y+'%';
//                                  $("#incomeCompare").html(this.point.y+'%');
                                }
                              });
                              r += '</span><br>';
                              return r;
                          } 
  },
  /*plotOptions: {
    line: {
        dataLabels: {
            enabled: false
        },
        enableMouseTracking: false
    }
},*/
    series: [ {
        name: '优选基金组合',
        color:'#f6a800',
        lineWidth: 2,
        data:[],
        marker: {
            radius: 2
        }
    }, {
      name: '上证综指',
      color:'#fc6d52',
      lineWidth: 2,
      data:[],
      marker: {
          radius: 2
      }
  }]
});
}
