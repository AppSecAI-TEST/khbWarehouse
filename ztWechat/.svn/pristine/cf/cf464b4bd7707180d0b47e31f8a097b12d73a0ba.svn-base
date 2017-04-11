$(function(){
  //TODO 可以优化 省去querybIll,用downAjax();待研究
  loaded();
//  queryBill('noArrival', 1);
  downAjax();
//  $("#noArrival").attr('title','noArrival');
//  $("#arrival").val('title','');
  $('#noArrival').click(function(){
    $('#arrival').removeClass('on');
   $("#noArrival").addClass('on');
//   $("#noArrival").attr('title','noArrival');
//   $("#arrival").val('title','');
   downAjax();
  });
  $('#arrival').click(function(){
    $('#noArrival').removeClass('on');
   $("#arrival").addClass('on');
//   $("#arrival").attr('title','arrival');
//   $("#noArrival").val('title','');
   downAjax();
  });
});


function clean(){
  $("#mask").hide();
  $("#alertLayer-8").hide();
}
