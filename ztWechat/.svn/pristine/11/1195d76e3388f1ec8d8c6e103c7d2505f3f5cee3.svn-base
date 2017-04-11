<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="e" uri="/emvc-tags"%>
<html>
<head lang="en">
    <title>系统异常</title>
</head>
<body>
<div id="box" class="bg-white">
    <div class="pr">
        <div class="buySuccess pr">
            <img src="static/images/unusual.jpg" class="repeatImg">
            <p class="errorCon red pa">系统异常，请稍后重试哦</p>
        </div>
        <div class="btnMaskArea btnPrev tc mt15">
            <a href="javascript:void(0)">点击回到上一页</a>
        </div>
    </div>
</div>
<script type="text/javascript">
var url='<%=request.getHeader("Referer")%>';
if(url=="null"){
  $(".btnMaskArea.btnPrev.tc.mt15").html('');
}else{
  $(".btnMaskArea.btnPrev.tc.mt15").html("<a href=\""+url+"\">点击回到上一页</a>");
}
</script>
</body>
</html>