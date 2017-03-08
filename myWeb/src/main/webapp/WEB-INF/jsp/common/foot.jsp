<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ page contentType="text/html;charset=UTF-8"%> 
<html>
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<body>
<h2>Hello World!</h2>
<form id="form">
	<p>用户名<input type="text" name="userName" /></p>
	<p>密码<input type="text" name="passWord" /></p>
</form>
</body>
<button id="button">登录</button>
<a href="${ctx}/account/toRegister?returnUrl=${returnUrl}">去注册</button>
<script type="text/javascript">
$("#button").click(function(){
  $.ajax({
    url:"${ctx}/account/login",
    dataType:'json',
    data: $("#form").serialize(),
    type:'post',
    success:function(data){
      if(data.code =="success") {
        location.href="${returnUrl}";
      } else {
        alert(data.message);
      }
    },
  }); 
});
</script>
</html>
