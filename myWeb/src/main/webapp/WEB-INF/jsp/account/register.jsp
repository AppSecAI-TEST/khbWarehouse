<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ page contentType="text/html;charset=UTF-8"%> 
<html>
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<body>
<h2>Hello World!</h2>
<p><input type="text" name="userName" /></p>
<p><input type="text" name="passWord" /></p>
<p><input type="text" name="rePassWord" /></p>
<p><input type="text" id="email" name="email" /><button id="sendEmailCode">获取邮箱验证码</button></p>
<p><input type="text" name="code" /></p>
</body>
<button id="button">注册</button>
<script type="text/javascript">
$("#sendEmailCode").click(function() {
  var email = $("#email").val();
  
  if(checkEmail(email)){
    $.ajax({
      url:"${ctx}/account/sendEmailCode",
      dataType:'json',
      data: {
        email:email
      },
      type:'post',
      success:function(data){
        if(data.code =="success") {
          alert("发送成功");
        } else {
          alert(data.message);
        }
      },
    });
  } else {
    alert("邮箱错误");
  }
  
});

function checkEmail(str){  
  var re=/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/  
  if(re.test(str)){  
      console.log("邮箱正确");  
      return true;
  }else{  
      return false;
  }  
}
</script>
</html>
