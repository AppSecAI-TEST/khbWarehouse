<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ page contentType="text/html;charset=UTF-8"%> 
<html>
<script type="text/javascript" src="${ctx}/static/jquery-1.8.3.min.js"></script>
<body>
<h2>Hello World!</h2>
<p>${user.id }</p>
<p>${user.userName }</p>
<p>${user.password }</p>
<p>${user.age }</p>
</body>
<button id="button">点击json</button>
<button id="buttonp">点击jsonp</button>
<script type="text/javascript">

$("#button").click(function() {
  $.ajax({
    url:"${ctx}/show/doOrder",
    dataType:'jsonp',
    data: {
      amount:100.27,
      userId:1,
    },
    type:'post',
    success:function(data){
      if(data.code =="success") {
        console.log(data.orderId);
      }
    },
  });
});

$("#buttonp").click(function() {
  $.ajax({
    url:"http://192.168.126.130:8090${ctx}/show/doOrder",
    dataType:'jsonp',
    data: {
      amount:100.27,
      userId:1,
      callback:"result"
      },
    type:'post'
  });
});

function result(data) {
  if(data.code =="success") {
    console.log(data.orderId);
  }
}

</script>
</html>
