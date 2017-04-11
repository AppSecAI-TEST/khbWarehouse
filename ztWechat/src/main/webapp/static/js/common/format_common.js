  //时间格式化算法
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
  
  //，除去后面的0，最多保留2位小数
  function format_money (data) {
    return Math.round(data * 100) / 100;
  }
  //年化率格式化，除去后面的0
  function format_yearRate (data) {
    return parseFloat(data);
  }
  //时间格式化，yyyy-MM-dd
  function format_date(data){
    if('string'==typeof(data)){
      data =  data.replace(/-/g,"/");
    }
    var date = new Date(data);
    return date.Format("yyyy-MM-dd");
  }
  //时间格式化，MM.dd
  function format_date_MMDD (data) {//时间格式化
    if('string'==typeof(data)){
      data =  data.replace(/-/g,"/");
    }
    var date = new Date(data);
    return date.Format("MM.dd");
  }
  //金额格式化
  function fmoney(s, n)   
  {   
     n = n > 0 && n <= 20 ? n : 2;   
     s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
     var l = s.split(".")[0].split("").reverse(),   
     r = s.split(".")[1];   
     t = "";   
     for(i = 0; i < l.length; i ++ )   
     {   
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
     }  
     if(r=='00'){
       return t.split("").reverse().join("") ;
     }else if(r.substr(1,2)=='0'){
       return t.split("").reverse().join("") + "." + r.substr(0,1);   
     }else{
       return t.split("").reverse().join("") + "." + r;   
     }
  } 
  //js保留小数 去尾法
  function formatNumberFloor (s,num) {
    return Math.floor(s * Math.pow(10, num)) / Math.pow(10, num);
    };
    //js保留小数 进一法
    function formatNumberCeil (s,num) {
      return Math.ceil(s * Math.pow(10, num)) / Math.pow(10, num);
      };
    
  
  