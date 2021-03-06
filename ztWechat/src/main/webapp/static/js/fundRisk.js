var testing = true;

var currPage = 0;
var answer = [];
var question = [{
	ques:'1/10. 您属于哪个年龄阶层？',
	a1:'60岁以上/20岁以下',
	a2:'50至60岁',
	a3:'30至40岁',
	a4:'20至30岁',
	a5:'40至50岁'
},{
	ques:'2/10. 您家庭的年收入是多少？',
	a1:'2 万元以下',
	a2:'2 万元至10万元',
	a3:'10万元至20万元',
	a4:'20万元至50万元',
	a5:'50万元以上'
},{
	ques:'3/10. 请问您投资金融产品的时间有多长？',
	a1:'没有',
	a2:'有，少于1年',
	a3:'有，1-5年之间',
	a4:'有，5-10年之间',
	a5:'有，超过10年'
},{
	ques:'4/10. 您家庭月收入的多少百分比进行流动性非常强的资产投资？（流动性非常强的资产指银行储蓄（活期存款、定期存款）、保本型产品、货币型基金等）',
	a1:'小于10%',
	a2:'10%-30%',
	a3:'30%-50%',
	a4:'50%-90%',
	a5:'大于90%'
},{
	ques:'5/10. 如果您有一笔闲散资金，您计划偏好投资于那种产品？',
	a1:'现金、存款、货币型基金',
	a2:'债券、债券型基金',
	a3:'外币、结构型产品、投资型保单',
	a4:'股票、偏股型基金',
	a5:'金融衍生品(期权、期货、认股权证等)'
},{
	ques:'6/10. 如果您进行一项具有价值波动性的金融资产投资，由于一般证券投资都存在风险，在未来三个月内，您能够接受的？',
	a1:'不能亏损',
	a2:'0-10%',
	a3:'10%-30%',
	a4:'30%-50%',
	a5:'甚至更高'
},{
	ques:'7/10. 若有临时且非预期的事件发生时，请问您的备用金相当于您多长时间的家庭开销？',
	a1:'月光族',
	a2:'3个月以下',
	a3:'介于3个月至6个月',
	a4:'介于6个月至1年',
	a5:'1年以上'
},{
	ques:'8/10. 一般而言，投资持续期越长，金融资产价值的波动性增大，您通常可以接受的持有期间是多久？',
	a1:'3个月以内',
	a2:'6个月以内',
	a3:'9个月以内',
	a4:'12个月以内',
	a5:'超过12个月'
},{
	ques:'9/10. 您计划什么时候开始动用投资本金及投资收益？',
	a1:'随时动用',
	a2:'6个月以内',
	a3:'1年以内',
	a4:'3年以内',
	a5:'盈利不计入本金，随时提取盈利'
},{
  ques:'10/10. 您能承受的最大亏损是多少？',
  a1:'基本不亏损',
  a2:'本金最多可能亏损10%',
  a3:'本金最多可能亏损20%',
  a4:'本金最多可能亏损30%',
  a5:'本金最多可能亏损40%'
}];

$(document).ready(function() {
	//加载页面的选项
	updateQuestion();
	
	$('.answer li').each(function(){
	    $(this).click(function(){
	        $('.answer li').removeClass('active');
	        $(this).addClass('active');
	        if(currPage < 9){
	          setTimeout(function (){
	            $('.answer li').removeClass('active');
	          },150);
	        }
	        nextPage($(this).val());
	     })
	})
	
	$("#submitBtn").click(function() {
	  $("#form").submit();
	});

})


function updateQuestion(){
	if(currPage == 0){
		document.getElementById("lastPage").innerHTML = '';
	}else{
		document.getElementById("lastPage").innerHTML = '上一题';
	}
	document.getElementById("qus").innerHTML=question[currPage].ques;
	document.getElementById("a1").innerHTML=question[currPage].a1;
	document.getElementById("a2").innerHTML=question[currPage].a2;
	document.getElementById("a3").innerHTML=question[currPage].a3;
	document.getElementById("a4").innerHTML=question[currPage].a4;
	document.getElementById("a5").innerHTML=question[currPage].a5;
}
function lastPage(){
	if(currPage == 0){
		return;
	}
	// 设置页面,更改题目
	currPage--;
	updateQuestion();
	// 还原值
	lastValue=answer[answer.length-1];
	$('.answer li').removeClass('active');
	$("#l"+lastValue).attr('class','active');
	$("#evo").attr("class","evolve evolve-"+(currPage+1));
	answer.pop();
}

function nextPage(item){
  console.log(item)
	if(currPage >= 9){
		//提交
	  if(testing) {
//	    answer.push(item);
	    $("#answerArr").val(answer);
	    $("#retreatAnswer").val((item-1)/10);
	    $("#submitBtn").show();
	    testing = false;
	  }
	}
	//当前页面打钩
	// 设置页面，更改题目
	answer.push(item);
	currPage++;
	if(currPage <= 9) {
	  updateQuestion();
	}
	$("#evo").attr("class","evolve evolve-"+(currPage+1));
}



//跳过测评
function skipTest(str){
	$("form").abbr("action", "${ctx}/riskTest/justBuy/"+str);
	$("answers").val(answer);
	document.getElementById("form").submit();
}