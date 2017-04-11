/*****************************************************************
 验证方法
 *****************************************************************/
//手机号规则验证
function isMobile(inputId){
  var mobile = document.getElementById(inputId).value;
  var rule = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
  var result = rule.test(mobile);
  if(result == false){
 //   $("#"+liId+"").attr("class","input-wrap-error");
 //   document.getElementById(divId).innerHTML="<i class='icon icon-error2'>手机号规则不正确</i>";
    return false;
  }else{
    return true;
  }
}
//密码验证规则，数字和字母
function isPwd(inputId){
  var pwd = document.getElementById(inputId).value;
  var rule = /^(?!([a-zA-Z]+|\d+)$)[a-zA-Z\d]{8,20}$/;
  return rule.test(pwd);
  
}

//身份证号码的验证规则
function isIdCardNo(inputId){
  var num = document.getElementById(inputId).value;
    //if (isNaN(num)) {alert("输入的不是数字！"); return false;}
    var len = num.length, re;
    if (len == 15)
        re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{2})(\w)$/);
    else if (len == 18)
        re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/);
    else {
        //alert("输入的数字位数不对。");
        return false;
    }
    var a = num.match(re);
    if (a != null)
        {
            if (len==15)
            {
                var D = new Date("19"+a[3]+"/"+a[4]+"/"+a[5]);
                var B = D.getYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];
            }
            else
            {
                var D = new Date(a[3]+"/"+a[4]+"/"+a[5]);
                var B = D.getFullYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];
            }
            if (!B) {
            //alert("输入的身份证号 "+ a[0] +" 里出生日期不对。");
            return false;
        }
        }
    if(!re.test(num)){
        //alert("身份证最后一位只能是数字和字母。");
        return false;
    }
    return true;
}
//身份证号规则验证
function callIdCodeValid(id){
  var code = document.getElementById(id).value;
  code = code.toUpperCase();
  var city = {11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",
              23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",
              37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",
              50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",
              63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};

          
  if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)?$/.test(code)){
      //tip = "身份证号格式错误";
      return false;
  }

  else if(!city[code.substr(0,2)]){
      //tip = "地址编码错误";
      return false;
  }

  else{
      //18位身份证需要验证最后一位校验位
      if(code.length == 18){
          code = code.split('');
          //加权因子
          var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
          //校验位
          var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
          var sum = 0;
          var ai = 0;
          var wi = 0;
          for (var i = 0; i < 17; i++){
              ai = code[i];
              wi = factor[i];
              sum += ai * wi;
          }
          var last = parity[sum % 11];
          if(parity[sum % 11] != code[17]){
              //tip = "校验位错误";
              return false;
          }
      }
  }
  return true;
}

//验证是数字
function isNum(inputId){
  var num = document.getElementById(inputId).value;
  var rule = /^[0-9]*$/;
  return rule.test(num);
}
//验证中文
function isChinese(inputId){
  var name = document.getElementById(inputId).value;
  var rule = /^[\u4e00-\u9fa5]*$/;
  return rule.test(name);
}