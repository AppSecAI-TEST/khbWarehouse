$(function () {
var data=$("#CARD_NO").text();
 if(data==null||data==""){
  $("#cardInfoDiv").remove();
  $("#addCardDiv").attr("style","");
}else{
  $("#addCardDiv").remove();
  $("#cardInfoDiv").attr("style","");
} 
});