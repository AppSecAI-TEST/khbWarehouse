<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ page contentType="text/html;charset=UTF-8"%> 
<html>
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<body>
<h2>Hello World!</h2>
<p><input type="text" name="userName" /></p>
<p><input type="text" name="passWord" /></p>
<p><input type="text" name="rePassWord" /></p>
<p><input type="text" id="email" name="email" /></p>
<p><input type="text" name="code" /></p>
</body>
<button id="button">注册</button>
<script type="text/javascript">
$("#button").click(function() {
  var email = $("#email").val();
  $.ajax({
    url:"${ctx}/account/sendEmailCode",
    dataType:'json',
    data: {
      email:email
    },
    type:'post',
    success:function(data){
      if(data.code =="success") {
        console.log(data.orderId);
      }
    },
  });
});
</script>
</html>
