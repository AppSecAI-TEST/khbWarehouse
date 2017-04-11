<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags"%>
<html>
<head lang="en">
<script>
$(function(){
	var max=<e:property value="@max"/>;
	for( var i=0; i<$('meter').length; i++ ) {
        var val = $('meter').eq(i).val();
        if((val/max)<0.42){
        	$('meter').eq(i).next('p').width(42+'%');
        }else{
        $('meter').eq(i).next('p').width(val/max*100+'%');
    }
	}
});
</script>
<title>生财宝收益</title>
</head>
<body>
	<div id="box">
		<div class="tab_con pb15">
			<div class="incomeCard bg-white tc">
				<p class="font-14">生财宝累计收益（元）</p>
				<span class="font-40 orange"><e:property
						value="@accumulativeIncome" /></span>
			</div>
			<div class="layou-04 pt10 pb10">
				<div class="deal-meter">
					<e:iterator list="@list" var="items">
						<div class="meter-wrap">
							<meter low="0" high="0" max="<e:property value="@max"/>"
								optimum="0" value="<e:property value="@items.value"/>"></meter>
							<p class="value-wrap">
								<span class="cur-date"><e:property value="@items.date" /></span><span
									class="cur-money"><e:property value="@items.value" /></span>
							</p>
						</div>
					</e:iterator>
				</div>
			</div>
		</div>
	</div>
</body>
</html>