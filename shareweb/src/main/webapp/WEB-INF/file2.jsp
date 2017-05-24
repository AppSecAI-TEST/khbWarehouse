<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ page contentType="text/html;charset=UTF-8"%> 
<% String basePath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<%=basePath %>/static/js/jquery.min.js"></script>
<script src="http://malsup.github.io/jquery.form.js"></script>
</head>
<head>
 
	<base href="<%=basePath%>">	
	<script>
		var basePath = "<%=basePath%>";
		
		var actionValue="add";
	</script>
	<title>文件上传</title>

	
<script type="text/javascript">  
    function submitData() {  
        // 上传设置  
        var options = {  
                // 规定把请求发送到那个URL  
                url: "http://192.168.18.22:8080/ROOT/crm/uploadResource2CDNAction.do?method=addRecordNewJson",  
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
    
	function callback(msg){
			
			if(msg=="该文件名已经存在"){
				
				$('#retValue').val("");
				alert("该文件名已经存在!");
				 $('#status').maskNew('文件上传中，请稍候...',false);
				 window.location.reload();
			}else{
				$('#retValue').val(msg);
				//alert(window.parent.name)
				//window.parent.postMessage(JSON.stringfy(msg),'*');
				crossIframe(msg);
				alert("文件上传完成!");
				window.close();
			}
	    	
		}
    
    $("#buttonp").click(function() {
      $.ajax({ 
        url:"http://172.19.61.153${ctx}/show/doOrder",
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
        console.log(data);
    }
</script>  
</head>
<style>

/*html5*/
article,aside,dialog,footer,header,section,footer,nav,figure,menu{display:block}
</style>
<body onLoad="WindowSollAuto()" onResize="WindowSollAuto()" onUnload="willReturnValue()" >
<div id="status">
	<iframe id="crossIframe" style="height:1px;width:1px;display:none;">  
        </iframe> 
	<div class="bosom_one">
		<div class="bosom_top">
			<span class="left"></span>
			<h4>文件上传</h4>
			<div class='MenuList'>
				<a href="javascript:submitData()">确定</a>
				<!-- <a href="javascript:window.close();">取消</a> -->
			</div>
			<span class="right"></span>
		</div>
		<div class="bosom_side">
			<div class="casing">
				<div class="caput">
					<span class="left"></span><span class="right"></span>
				</div>
				<div class="viscera" id="SollAuto">
					<div class="sheet_div">
					<form id="jvForm" method="post" enctype="multipart/form-data"  target="hidden_frame">
						<table class="list_add">
						  <tr>
						    <td class="formTable" width="1%" colspan=3>
						    	生成缩略图：<input type="checkbox" name="autoGenThumbailFlag" id="autoGenThumbailFlag"  value="1" style="width:15px;" />
						    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    	加密：<input type="checkbox" name="jmFlag" id="jmFlag" value="1" style="width:15px;" />
						    	修改文件名：<input type="checkbox" name="isModify" id="isModify" checked="checked" value="1" style="width:15px;" />
						    </td>
						  </tr>
						  <tr>
						    <td class="formTable" width="1%"><font color='red'>*</font>主文件</td>
						    <td align="left">
						    	<input type='file' style='width:600px;' name='file' id='mainfile'/>
						    </td>
						    <td>&nbsp;</td>
						  </tr>
						  <tr>
						    <td class="formTable" width="1%">缩略图</td>
						    <td align="left">
						    	<input type='file' style='width:600px;' name='file' id='thumbnail'/>
						    </td>
						    <td>&nbsp;</td>
						  </tr>
						</table>
						<input type='hidden' name="grp" id="grp" value='${grp}'>
						
						<input type='hidden' name="retValue" id="retValue" value=''>
						<input type='hidden' name="jmValue" id="jmValue" value=''>
				</form>
				</div>
				</div>
				<div class="trail">
					<span class="fleft"></span><span class="fright"></span>
				</div>
			</div>
		</div>
		<iframe name='hidden_frame' id="hidden_frame" style="display:none"></iframe>
		<div class="bosom_bottom">
			<span class="left"></span><span class="right"></span>
		</div>
	</div>
</div>
	
</body>
</html>
</html>




