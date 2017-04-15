<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ page contentType="text/html;charset=UTF-8"%> 
<% String basePath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<%=basePath %>/static/js/jquery.min.js"></script>
<script src="http://malsup.github.io/jquery.form.js"></script>
</head>
<!-- Ajax异步上传图片 -->  
<script type="text/javascript">  
    function uploadPic() {  
        // 上传设置  
        var options = {  
                // 规定把请求发送到那个URL  
                url: "/shareweb/upload",  
                // 请求方式  
                type: "post",  
                // 服务器响应的数据类型  
                dataType: "json",  
                // 请求成功时执行的回调函数  
                success: function(data) {  
                    // 图片显示地址  
                    console.log(data)
                    $("#allUrl").attr("src", data.path);  
                }  
        };  
          
        $("#jvForm").ajaxSubmit(options);  
    }  
</script>  
  
<body>  
    <form id="jvForm" action="<%=basePath %>/upload" method="post" enctype="multipart/form-data">  
        <table>  
            <tbody>  
                <tr>  
                    <td width="20%">  
                        <span>*</span>  
                        上传图片(90x150尺寸):</td>  
                        <td width="80%">  
                        注:该尺寸图片必须为90x150。  
                    </td>  
                </tr>  
                <tr>  
                    <td width="20%"></td>  
                        <td width="80%">  
                        <img width="100" height="100" id="allUrl"/>  
                        <!-- 在选择图片的时候添加事件，触发Ajax异步上传 -->  
                        <input name="upload" type="file" onchange="uploadPic()"/>  
                    </td>  
                </tr>  
            </tbody>  
        </table>  
    </form>  
</body>
<%-- <form action="<%=basePath %>/upload" method="post" enctype="multipart/form-data">  
  <input name="upload" id="upload" type="file" />  
  <button type="submit">asd</button>
</form>
<img alt="" src="<%=basePath %>/showFile?id=80339e4793764f31854ef14a263f586d"> --%>
</html>




