$(function(){
  
  $("#expireDay").html(format_date_forward($("#expireDay").html()));
  $("#incomeDay").html(format_date_forward($("#incomeDay").html()));
//初始化iscroll
  loaded();
  downAjax();
  $(".icon.icon-questionmark.font-16.safeguards").eq(1).click(function(){
    showDialogBottom();
    $("#alertLayer").show();
//    $("body").css("position","fixed");
  });
  $(".back").eq(1).click(function(){
    showDialogBottom();
    $("#alertLayer-3").show();
//    $("body").css("position","fixed");
  });
  
  var showDialogBottom = function(){
    $("#mask").css("height",$(document).height());
    $("#mask").css("width",$(document).width());
    $("#mask").show();
  };
  
 
//  $("#arrivalDay").html(format_date_forward($("#arrivalDay").html()));
  
 
  
});

function format_date_forward(data){
  if('string'==typeof(data)){
    data =  data.replace(/-/g,"/");
  }
  date = new Date(data);
  return date.Format("yyyy-MM-dd");
}
function format_date_after(data){
  if('string'==typeof(data)){
    data =  data.replace(/-/g,"/");
  }
  date = new Date(data);
  return date.Format("hh:mm:ss");
}
function format_money_upgrade(s,n){
     n = n > 0 && n <= 20 ? n : 2;   
     s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
     var l = s.split(".")[0].split("").reverse(),   
     r = s.split(".")[1];   
     t = "";   
     for(i = 0; i < l.length; i ++ )   
     {   
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
     }   
     return t.split("").reverse().join("") + "." + r;   
}
function clean(){
  $("#mask").hide();
  $("#alertLayer-8").hide();
}
    