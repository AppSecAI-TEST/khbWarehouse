<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>应用错误</title>
</head>
<body>
<div class="Container">
  <div class="weberror">
    <div class="web_tit">
      <div class="web_l"></div>
      <h2>温馨提示</h2>

      <div class="web_r"></div>
    </div>
    <div class="web_con">
      <div class="web_right">
        <p><e:property value="@exception"/></p>
      </div>
    </div>
  </div>
</div>
</body>
</html>